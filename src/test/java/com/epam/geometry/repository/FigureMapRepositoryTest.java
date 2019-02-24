package com.epam.geometry.repository;

import com.epam.geometry.exceptions.NullUpdateException;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Quadrilateral;
import com.epam.geometry.repository.specification.AllFiguresSpecification;
import com.epam.geometry.repository.specification.FigureSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.*;

public class FigureMapRepositoryTest {
    private static final Integer TRAPEZIUM_ID = 10;
    private static final Integer SQUARE_ID = 42;
    private static final Figure TEST_TRAPEZIUM = new Quadrilateral(
            TRAPEZIUM_ID,
            new Point2D(-2, 2),
            new Point2D(3, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));
    private static final Figure TEST_SQUARE = new Quadrilateral(
            SQUARE_ID,
            new Point2D(-2, 2),
            new Point2D(1, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));

    @Test
    public void queryShouldReturnSquareWhenSpecificationSupplied() {
        FigureSpecification specification = mock(FigureSpecification.class);
        when(specification.specified(TEST_SQUARE)).thenReturn(true);
        when(specification.specified(TEST_TRAPEZIUM)).thenReturn(false);
        FigureRepository repository = new FigureMapRepository();
        repository.addFigure(TEST_TRAPEZIUM);
        repository.addFigure(TEST_SQUARE);

        List<Figure> expected = Collections.singletonList(TEST_SQUARE);
        List<Figure> actual = repository.query(specification);

        Assert.assertEquals(expected, actual);
        verify(specification, times(2)).specified(any(Figure.class));
        verifyNoMoreInteractions(specification);
    }

    @Test
    public void queryShouldSortOrderedByIdFigures() {
        Comparator<Figure> comparator = mock(Comparator.class);
        FigureSpecification specification = mock(FigureSpecification.class);
        when(specification.specified(any(Figure.class))).thenReturn(true);
        when(comparator.compare(TEST_SQUARE, TEST_TRAPEZIUM)).thenReturn(1);
        when(comparator.compare(TEST_TRAPEZIUM, TEST_SQUARE)).thenReturn(-1);
        FigureRepository repository = new FigureMapRepository();
        repository.addFigure(TEST_TRAPEZIUM);
        repository.addFigure(TEST_SQUARE);

        List<Figure> expected = Arrays.asList(TEST_TRAPEZIUM, TEST_SQUARE);
        List<Figure> actual = repository.query(specification, comparator);

        Assert.assertEquals(expected, actual);
        verify(specification, times(2)).specified(any(Figure.class));
        verifyNoMoreInteractions(specification);
    }

    @Test(expected = NullUpdateException.class)
    public void updateShouldThrowNullUpdateExceptionWhenRepositoryIsEmpty() {
        Integer wrongId = 10;
        FigureRepository repository = new FigureMapRepository();
        repository.updateFigure(wrongId, TEST_SQUARE);
    }

    @Test
    public void addShouldAddElement() {
        FigureRepository repository = new FigureMapRepository();
        repository.addFigure(TEST_SQUARE);
        List<Figure> expected = Collections.singletonList(TEST_SQUARE);
        List<Figure> actual = repository.query(new AllFiguresSpecification());
        Assert.assertEquals(expected, actual);
    }
}
