package com.epam.geometry.parse;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Point2D;
import com.epam.geometry.validation.FormatValidator;
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
            IllegalParseException illegalParseException = new IllegalParseException("Invalid points format");
            LOGGER.error(illegalParseException.getMessage(), illegalParseException);
            throw illegalParseException;
        }
        return points;
    }

    private Point parsePoint(String str) {
        String[] arguments = str.split(POINT_DELIMITER);
        return new Point2D(Double.parseDouble(arguments[0]), Double.parseDouble(arguments[1]));
    }
}
