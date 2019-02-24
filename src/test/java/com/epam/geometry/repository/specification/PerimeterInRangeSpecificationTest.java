package com.epam.geometry.repository.specification;

import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Quadrilateral;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PerimeterInRangeSpecificationTest {
    private static final FigureLogic LOGIC = mock(FigureLogic.class);
    private static final Integer ID = 10;
    private static final double MIN_PERIMETER = 9.0;
    private static final double MAX_PERIMETER = 13.0;
    private static final Figure TEST_TRAPEZIUM = new Quadrilateral(
            ID,
            new Point2D(-2, 2),
            new Point2D(3, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));
    private static final Figure TEST_SQUARE = new Quadrilateral(
            ID,
            new Point2D(-2, 2),
            new Point2D(1, 2),
            new Point2D(1, -1),
            new Point2D(-2, -1));
    private final FigureSpecification specification = new PerimeterInRangeSpecification(LOGIC, MIN_PERIMETER, MAX_PERIMETER);

    @BeforeClass
    public static void initMocks() {
        when(LOGIC.countPerimeter(TEST_TRAPEZIUM)).thenReturn(15.0);
        when(LOGIC.countPerimeter(TEST_SQUARE)).thenReturn(12.0);
    }

    @Test
    public void specifiedShouldReturnTrueWhenSquareSupplied() {
        boolean result = specification.specified(TEST_SQUARE);
        Assert.assertTrue(result);
        verify(LOGIC).countPerimeter(TEST_SQUARE);
    }

    @Test
    public void specifiedShouldReturnFalseWhenTrapeziumSupplied() {
        boolean result = specification.specified(TEST_TRAPEZIUM);
        Assert.assertFalse(result);
        verify(LOGIC).countPerimeter(TEST_TRAPEZIUM);
    }

    @AfterClass
    public static void verifyMocks() {
        verifyNoMoreInteractions(LOGIC);
    }
}