package com.epam.geometry.logic;

import com.epam.geometry.model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuadrilateralLogicTest {
    private static final double EPSILON = 10e-3;
    private static final Integer ID = 10;
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
    private static final Figure TEST_RHOMB = new Quadrilateral(
            ID,
            new Point2D(-2, 0),
            new Point2D(0, 4),
            new Point2D(2, 0),
            new Point2D(0, -4));
    private static final Figure TEST_UNCONVEX = new Quadrilateral(
            ID,
            new Point2D(-1, 0),
            new Point2D(2, 0),
            new Point2D(0, -1),
            new Point2D(-1, -3));
    private static final GeometryAlgorithms GEOMETRY_ALGORITHMS = mock(GeometryAlgorithms.class);
    private final FigureLogic quadrilateralLogicGlobal = new QuadrilateralLogic(GEOMETRY_ALGORITHMS);

    @BeforeClass
    public static void initGlobalMockitos() {
        when(GEOMETRY_ALGORITHMS.countVectorProduct(any(Vector.class), any(Vector.class))).thenReturn(4.5);
        when(GEOMETRY_ALGORITHMS.countDistance(any(Point.class), any(Point.class))).thenReturn(3.0);
        when(GEOMETRY_ALGORITHMS.countVectorProduct(new Vector2D(TEST_UNCONVEX.getPoint(0)),
                new Vector2D(TEST_UNCONVEX.getPoint(1)))).thenReturn(-4.5);
    }

    @Test
    public void countSquareShouldReturnNineForSquare() {
        double result = quadrilateralLogicGlobal.countSquare(TEST_SQUARE);
        Assert.assertEquals(9.0, result, EPSILON);
    }

    @Test
    public void countPerimeterShouldReturnTwelveForSquare() {
        double result = quadrilateralLogicGlobal.countPerimeter(TEST_SQUARE);
        Assert.assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void isConvexShouldReturnFalseForUnconvex() {
        boolean result = quadrilateralLogicGlobal.isConvex(TEST_UNCONVEX);
        Assert.assertFalse(result);
    }

    @Test
    public void isConvexShouldReturnTrueForRhomb() {
        boolean result = quadrilateralLogicGlobal.isConvex(TEST_RHOMB);
        Assert.assertTrue(result);
    }

    @Test
    public void isSquareShouldReturnTrueForSquare() {
        GeometryAlgorithms geometryAlgorithms = mock(GeometryAlgorithms.class);
        when(geometryAlgorithms.areLinesPerpendicular(any(Point.class), any(Point.class), any(Point.class),
                any(Point.class))).thenReturn(true);
        when(geometryAlgorithms.countDistance(any(Point.class), any(Point.class))).thenReturn(3.0);
        QuadrilateralLogic quadrilateralLogic = new QuadrilateralLogic(geometryAlgorithms);

        boolean result = quadrilateralLogic.isSquare(TEST_SQUARE);
        Assert.assertTrue(result);
    }

    @Test
    public void isSquareShouldReturnFalseForUnconvex() {
        GeometryAlgorithms geometryAlgorithms = mock(GeometryAlgorithms.class);
        when(geometryAlgorithms.areLinesPerpendicular(any(Point.class), any(Point.class), any(Point.class),
                any(Point.class))).thenReturn(false);
        QuadrilateralLogic quadrilateralLogic = new QuadrilateralLogic(geometryAlgorithms);

        boolean result = quadrilateralLogic.isSquare(TEST_UNCONVEX);

        Assert.assertFalse(result);
    }

    @Test
    public void isRhombShouldReturnTrueForRhomb() {
        boolean result = quadrilateralLogicGlobal.isRhomb(TEST_RHOMB);
        Assert.assertTrue(result);
    }

    @Test
    public void isTrapeziumShouldReturnTrueForTrapezium() {
        GeometryAlgorithms geometryAlgorithms = mock(GeometryAlgorithms.class);
        when(geometryAlgorithms.areLinesParallel(any(Point.class), any(Point.class), any(Point.class),
                any(Point.class))).thenReturn(true);
        when(geometryAlgorithms.areLinesParallel(TEST_TRAPEZIUM.getPoint(3), TEST_TRAPEZIUM.getPoint(0),
                TEST_TRAPEZIUM.getPoint(2), TEST_TRAPEZIUM.getPoint(1))).thenReturn(false);
        QuadrilateralLogic quadrilateralLogic = new QuadrilateralLogic(geometryAlgorithms);

        boolean result = quadrilateralLogic.isTrapezium(TEST_TRAPEZIUM);
        Assert.assertTrue(result);
    }

    @Test
    public void isTrapeziumShouldReturnFalseForUnconvex() {
        boolean result = quadrilateralLogicGlobal.isTrapezium(TEST_UNCONVEX);
        Assert.assertFalse(result);
    }

}
