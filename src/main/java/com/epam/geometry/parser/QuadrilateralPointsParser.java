package com.epam.geometry.parser;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuadrilateralPointsParser implements PointsParser {
    private static final Logger LOGGER = LogManager.getLogger(QuadrilateralPointsParser.class);

    private static final String POINT_DELIMITER = ",";
    private static final String DELIMITER = " ";

    public QuadrilateralPointsParser() {
    }

    @Override
    public List<Point> parse(String str) {
        String[] arguments = str.split(DELIMITER);
        List<Point> points = new ArrayList<>();
        try {
            for (String argument : arguments) {
                points.add(parsePoint(argument));
            }
        } catch (IllegalArgumentException e) {
            String message = "Invalid points format";
            LOGGER.error(message, e);
            throw new IllegalParseException(message);
        }
        return points;
    }

    private Point parsePoint(String str) {
        String[] arguments = str.split(POINT_DELIMITER);
        return new Point2D(Double.parseDouble(arguments[0]), Double.parseDouble(arguments[1]));
    }
}
