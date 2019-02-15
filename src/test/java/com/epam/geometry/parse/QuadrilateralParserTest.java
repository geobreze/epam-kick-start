package com.epam.geometry.parse;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.model.Quadrilateral;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class QuadrilateralParserTest {
    private static final IdGenerator ID_GENERATOR = mock(IdGenerator.class);
    private static final PointsParser POINTS_PARSER = mock(QuadrilateralPointsParser.class);
    private static final FigureParser QUADRILATERAL_PARSER = new QuadrilateralParser(ID_GENERATOR, POINTS_PARSER);

    @Test
    public void parseShouldReturnCorrectQuadrilateralWhenCorrectDataSupplied() {
        String data = "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0";
        List<Point> pointsList = Arrays.asList(
                new Point2D(-2, 2),
                new Point2D(3, 2),
                new Point2D(1, -1),
                new Point2D(-2, -1));
        when(POINTS_PARSER.parse(anyString())).thenReturn(pointsList);
        Figure parsedQuadrilateral = new Quadrilateral(
                ID_GENERATOR,
                pointsList.get(0),
                pointsList.get(1),
                pointsList.get(2),
                pointsList.get(3));
        Figure result = QUADRILATERAL_PARSER.parse(data);
        Assert.assertEquals(result, parsedQuadrilateral);
    }

    @Test(expected = IllegalParseException.class)
    public void parseShouldThrowIllegalParseExceptionWhenIncorrectPointsSupplied() {
        String data = "-2.0,2.0 3Ahg.0,2.0 1.0,-1.0 -2.0,-1.0";
        Figure result = QUADRILATERAL_PARSER.parse(data);
    }

    @After
    public void verifyPointsParser() {
        verify(POINTS_PARSER).parse(anyString());
        verifyNoMoreInteractions(POINTS_PARSER);
        clearInvocations(POINTS_PARSER);
    }
}
