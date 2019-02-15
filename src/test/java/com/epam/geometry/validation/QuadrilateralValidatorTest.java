package com.epam.geometry.validation;

import com.epam.geometry.logic.GeometryAlgorithms;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.parse.PointsParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class QuadrilateralValidatorTest {
    private static final int ANGLES = 4;
    private static final PointsParser POINTS_PARSER = mock(PointsParser.class);
    private static final GeometryAlgorithms GEOMETRY_ALGORITHMS_SAME_LINE_POINTS = mock(GeometryAlgorithms.class);
    private static final GeometryAlgorithms GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS = mock(GeometryAlgorithms.class);

    @BeforeClass
    public static void initAlgorithmsAndParser() {
        when(GEOMETRY_ALGORITHMS_SAME_LINE_POINTS.arePointsBelongOneLine(any(Point.class), any(Point.class),
                any(Point.class))).thenReturn(true);
        when(GEOMETRY_ALGORITHMS_SAME_LINE_POINTS.arePointsUnique(ArgumentMatchers.<Point>anyList())).thenReturn(true);

        when(GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS.arePointsBelongOneLine(any(Point.class), any(Point.class),
                any(Point.class))).thenReturn(false);
        when(GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS.arePointsUnique(ArgumentMatchers.<Point>anyList())).thenReturn(true);

        when(POINTS_PARSER.parse(anyString())).thenReturn(Arrays.asList(
                (Point) new Point2D(1, 1),
                new Point2D(1, 1),
                new Point2D(1, 1),
                new Point2D(1, 1)
        ));
    }

    @Test
    public void validateShouldReturnTrueWhenLinesCross() {
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
        String data = "-1.0,1.0 1.0,-1.0 1.0,1.0 -1.0,-1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertTrue(result);

        invokeVerify(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
    }

    @Test
    public void validateShouldReturnFalseWhenPointsImposed() {
        GeometryAlgorithms samePointsAlgorithms = mock(GeometryAlgorithms.class);
        when(samePointsAlgorithms.arePointsUnique(ArgumentMatchers.<Point>anyList())).thenReturn(false);
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, samePointsAlgorithms);
        String data = "-1.0,1.0 1.0,-1.0 -1.0,1.0 -1.0,-1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertFalse(result);

        invokeVerify(POINTS_PARSER, samePointsAlgorithms);
    }

    @Test
    public void validateShouldReturnFalseWhenLinesImposed() {
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, GEOMETRY_ALGORITHMS_SAME_LINE_POINTS);
        String data = "-1.0,1.0 1.0,-1.0 1.0,1.0 3.0,1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertFalse(result);

        invokeVerify(POINTS_PARSER, GEOMETRY_ALGORITHMS_SAME_LINE_POINTS);
    }

    @Test
    public void validateShouldReturnFalseWhenPointsAtSameLine() {
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, GEOMETRY_ALGORITHMS_SAME_LINE_POINTS);
        String data = "-1.0,1.0 1.0,1.0 3.0,1.0 1.0,-1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertFalse(result);

        invokeVerify(POINTS_PARSER, GEOMETRY_ALGORITHMS_SAME_LINE_POINTS);
    }

    @Test
    public void validateShouldReturnTrueWhenTrapeziumSupplied() {
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
        String data = "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertTrue(result);

        invokeVerify(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
    }

    @Test
    public void validateShouldReturnTrueWhenSquareSupplied() {
        FigureValidator quadrilateralValidator = new QuadrilateralValidator(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
        String data = "-1.0,1.0 -1.0,-1.0 1.0,-1.0 1.0,1.0";
        boolean result = quadrilateralValidator.validate(data);
        Assert.assertTrue(result);

        invokeVerify(POINTS_PARSER, GEOMETRY_ALGORITHMS_DIFFERENT_LINE_POINTS);
    }

    private void invokeVerify(PointsParser pointsParser, GeometryAlgorithms geometryAlgorithms) {
        verify(pointsParser).parse(anyString());
        verify(geometryAlgorithms, atMost(ANGLES)).arePointsBelongOneLine(any(Point.class), any(Point.class), any(Point.class));
        verify(geometryAlgorithms).arePointsUnique(ArgumentMatchers.<Point>anyList());
        verifyNoMoreInteractions(pointsParser, geometryAlgorithms);
        clearInvocations(pointsParser, geometryAlgorithms);
    }
}
