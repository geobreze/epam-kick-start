package com.epam.geometry.logic;

import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Vector;
import com.epam.geometry.model.Vector2D;
import org.junit.Assert;
import org.junit.Test;

public class Geometry2DAlgorithmsTest {
    private static final double EPSILON = 10e-3;
    private static final Vector ABOVE_X_LINE = new Vector2D(new Point2D(6, 1));
    private static final Vector UNDER_X_LINE = new Vector2D(new Point2D(10, -5));
    private static final Point LEFT_POINT = new Point2D(-4.5, 5.1);
    private static final Point RIGHT_POINT = new Point2D(9.3, -6.7);
    private final GeometryAlgorithms geometryAlgorithms = new Geometry2DAlgorithms();

    @Test
    public void countVectorProductShouldReturnNegativeFortyForTestVectors() {
        double result = geometryAlgorithms.countVectorProduct(ABOVE_X_LINE, UNDER_X_LINE);

        Assert.assertEquals(-40, result, EPSILON);
    }

    @Test
    public void countVectorProductShouldReturnFortyForTestVectorsReversedOrder() {
        double result = geometryAlgorithms.countVectorProduct(UNDER_X_LINE, ABOVE_X_LINE);

        Assert.assertEquals(40, result, EPSILON);
    }

    @Test
    public void countScalarProductShouldReturnNegativeFiftyFiveForTestVectors() {
        double result = geometryAlgorithms.countScalarProduct(ABOVE_X_LINE, UNDER_X_LINE);

        Assert.assertEquals(55, result, EPSILON);
    }

    @Test
    public void countDistanceApproximateForTestPoints() {
        double result = geometryAlgorithms.countDistance(LEFT_POINT, RIGHT_POINT);

        Assert.assertEquals(18.157, result, EPSILON);
    }

    @Test
    public void areLinesParallelShouldReturnFalseForPerpendicular() {
        Point firstBegin = new Point2D(1, 0);
        Point firstEnd = new Point2D(1, 6);
        Point secondBegin = new Point2D(-2, 2);
        Point secondEnd = new Point2D(10, 2);

        boolean result = geometryAlgorithms.areLinesParallel(firstBegin, firstEnd, secondBegin, secondEnd);

        Assert.assertFalse(result);
    }

    @Test
    public void areLinesParallelShouldReturnTrue() {
        Point firstBegin = new Point2D(-7, 6);
        Point firstEnd = new Point2D(1, 6);
        Point secondBegin = new Point2D(-2, 2);
        Point secondEnd = new Point2D(10, 2);

        boolean result = geometryAlgorithms.areLinesParallel(firstBegin, firstEnd, secondBegin, secondEnd);

        Assert.assertTrue(result);
    }

    @Test
    public void areLinesPerpendicularShouldReturnTrue() {
        Point firstBegin = new Point2D(1, 0);
        Point firstEnd = new Point2D(1, 6);
        Point secondBegin = new Point2D(-2, 2);
        Point secondEnd = new Point2D(10, 2);

        boolean result = geometryAlgorithms.areLinesPerpendicular(firstBegin, firstEnd, secondBegin, secondEnd);

        Assert.assertTrue(result);
    }

    @Test
    public void areLinesPerpendicularShouldReturnTrueForSquare() {
        Point firstBegin = new Point2D(1, 2);
        Point firstEnd = new Point2D(1, -1);
        Point secondBegin = new Point2D(1, -1);
        Point secondEnd = new Point2D(-2, -1);

        boolean result = geometryAlgorithms.areLinesPerpendicular(firstBegin, firstEnd, secondBegin, secondEnd);

        Assert.assertTrue(result);
    }

    @Test
    public void areLinesPerpendicularShouldReturnFalseForParallel() {
        Point firstBegin = new Point2D(-7, 6);
        Point firstEnd = new Point2D(1, 6);
        Point secondBegin = new Point2D(-2, 2);
        Point secondEnd = new Point2D(10, 2);
        boolean result = geometryAlgorithms.areLinesPerpendicular(firstBegin, firstEnd, secondBegin, secondEnd);
        Assert.assertFalse(result);
    }

    @Test
    public void arePointsBelongOneLineShouldReturnFalseForTestPoints() {
        boolean result = geometryAlgorithms.arePointsBelongOneLine(RIGHT_POINT, UNDER_X_LINE.getCoordinates(), LEFT_POINT);

        Assert.assertFalse(result);
    }

    @Test
    public void arePointsBelongOneLineShouldReturnTrueForPointsWithOrdinateOne() {
        Point firstPoint = new Point2D(-1, 1);
        Point secondPoint = new Point2D(1, 1);
        Point thirdPoint = new Point2D(3, 1);

        boolean result = geometryAlgorithms.arePointsBelongOneLine(firstPoint, secondPoint, thirdPoint);

        Assert.assertTrue(result);
    }

    @Test
    public void arePointsBelongOneLineShouldReturnTrueForTwoImposedPoints() {
        Point firstPoint = new Point2D(-1, 1);
        Point secondPoint = firstPoint;
        Point thirdPoint = new Point2D(3, 1);

        boolean result = geometryAlgorithms.arePointsBelongOneLine(firstPoint, secondPoint, thirdPoint);

        Assert.assertTrue(result);
    }
}
