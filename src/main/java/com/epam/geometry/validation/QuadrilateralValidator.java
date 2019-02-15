package com.epam.geometry.validation;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.logic.GeometryAlgorithms;
import com.epam.geometry.model.Point;
import com.epam.geometry.parse.PointsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuadrilateralValidator implements FigureValidator {
    private static final Logger LOGGER = LogManager.getLogger(QuadrilateralValidator.class);
    private static final int ANGLES = 4;
    private final PointsParser pointsParser;
    private final GeometryAlgorithms geometryAlgorithms;

    public QuadrilateralValidator(PointsParser pointsParser, GeometryAlgorithms geometryAlgorithms) {
        this.pointsParser = pointsParser;
        this.geometryAlgorithms = geometryAlgorithms;
    }

    @Override
    public boolean validate(String str) {
        try {
            List<Point> pointList = pointsParser.parse(str);
            return pointList.size() >= ANGLES && geometryAlgorithms.arePointsUnique(pointList) && arePointsAtDifferentLines(pointList);
        } catch (IllegalParseException e) {
            LOGGER.info("Line with invalid format was supplied");
            return false;
        }
    }

    private boolean arePointsAtDifferentLines(List<Point> points) {
        boolean result = true;
        for (int i = 0; i < points.size(); ++i) {
            for (int j = i + 1; j < points.size(); ++j) {
                for (int k = j + 1; k < points.size(); ++k) {
                    if (geometryAlgorithms.arePointsBelongOneLine(points.get(i), points.get(j), points.get(k))) {
                        LOGGER.debug("These points belong one line: {}; {}; {}", points.get(i), points.get(j), points.get(k));
                        result = false;
                    }
                }
            }
        }
        return result;
    }

}
