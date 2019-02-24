package com.epam.geometry.parser;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class QuadrilateralPointsParserTest {
    private final PointsParser pointsParser = new QuadrilateralPointsParser();

    @DataProvider
    public static Object[][] wrongDataProvider() {
        return new Object[][]{
                {"-2.0,2.0 3fd.0f,2.0 1.0,-1.0 -2.0,-1.0"},
                {"-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0 1,a"},
                {"-2.0,2.0 3.0,2.-576780 1.0,-1.0"}
        };
    }

    @Test
    public void parseShouldReturnCorrectlyOrderedList() {
        String data = "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0";
        List<Point> parsedData = Arrays.asList(new Point2D(-2, 2),
                new Point2D(3, 2),
                new Point2D(1, -1),
                new Point2D(-2, -1));
        List<Point> result = pointsParser.parse(data);
        Assert.assertEquals(parsedData, result);
    }

    @Test(expected = IllegalParseException.class)
    @UseDataProvider("wrongDataProvider")
    public void parseShouldThrowIllegalParseExceptionWhenIncorrectPointsSupplied(String data) {
        List<Point> result = pointsParser.parse(data);
    }

}
