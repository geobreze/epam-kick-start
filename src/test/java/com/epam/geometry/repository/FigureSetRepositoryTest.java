package com.epam.geometry.repository;

import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Quadrilateral;
import com.epam.geometry.repository.specification.FigureSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FigureSetRepositoryTest {
    private static final IdGenerator ID_GENERATOR = mock(IdGenerator.class);
    private static final Figure TEST_TRAPEZIUM = new Quadrilateral(
            ID_GENERATOR,
            new Point2D(-2, 2),
            new Point2D(3, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));
    private static final Figure TEST_SQUARE = new Quadrilateral(
            ID_GENERATOR,
            new Point2D(-2, 2),
            new Point2D(1, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));
    private final FigureRepository repository = new FigureSetRepository();

    @Test
    public void queryShouldReturnSquareWhenSpecificationSupplied() {
        FigureSpecification specification = mock(FigureSpecification.class);
        when(specification.specified(TEST_SQUARE)).thenReturn(true);
        when(specification.specified(TEST_TRAPEZIUM)).thenReturn(false);
        repository.addFigure(TEST_TRAPEZIUM);
        repository.addFigure(TEST_SQUARE);

        List<Figure> expected = Arrays.asList(TEST_SQUARE);
        List<Figure> actual = repository.query(specification);

        Assert.assertEquals(expected, actual);
        verify(specification, times(2)).specified(any(Figure.class));
        verifyNoMoreInteractions(specification);
    }
}
