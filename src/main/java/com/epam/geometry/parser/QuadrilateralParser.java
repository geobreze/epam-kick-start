package com.epam.geometry.parser;

import com.epam.geometry.exceptions.IllegalParseException;
import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Quadrilateral;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class QuadrilateralParser implements FigureParser {
    private static final Logger LOGGER = LogManager.getLogger(QuadrilateralParser.class);

    private final PointsParser pointParser;
    private final IdGenerator idGenerator;

    public QuadrilateralParser(IdGenerator idGenerator, PointsParser pointParser) {
        this.pointParser = pointParser;
        this.idGenerator = idGenerator;
    }

    @Override
    public Figure parse(String str) {
        try {
            List<Point> points = pointParser.parse(str);
            Integer id = idGenerator.generate();
            return new Quadrilateral(id, points.get(0), points.get(1), points.get(2), points.get(3));
        } catch (IndexOutOfBoundsException e) {
            String message = "Given shape must be quadrilateral";
            LOGGER.error(message, e);
            throw new IllegalParseException(message);
        }
    }
}
