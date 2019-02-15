package com.epam.geometry.parse;

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
            Figure figure = new Quadrilateral(idGenerator, points.get(0), points.get(1), points.get(2), points.get(3));
            return figure;
        } catch (RuntimeException e) {
            IllegalParseException illegalParseException = new IllegalParseException("Given shape must be quadrilateral", e);
            LOGGER.error(illegalParseException.getMessage(), illegalParseException);
            throw illegalParseException;
        }
    }
}
