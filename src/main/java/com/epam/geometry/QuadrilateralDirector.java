package com.epam.geometry;

import com.epam.geometry.exceptions.WrongDataException;
import com.epam.geometry.model.Figure;
import com.epam.geometry.parse.FigureParser;
import com.epam.geometry.read.Reader;
import com.epam.geometry.validation.FigureValidator;
import com.epam.geometry.validation.FormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuadrilateralDirector implements FigureDirector {
    private static final Logger LOGGER = LogManager.getLogger(QuadrilateralDirector.class);

    private final FigureValidator quadrilateralValidator;
    private final Reader fileReader;
    private final FigureParser quadrilateralParser;
    private final FormatValidator formatValidator;

    public QuadrilateralDirector(FormatValidator formatValidator, FigureValidator quadrilateralValidator,
                                 Reader fileReader, FigureParser quadrilateralParser) {
        this.formatValidator = formatValidator;
        this.quadrilateralValidator = quadrilateralValidator;
        this.fileReader = fileReader;
        this.quadrilateralParser = quadrilateralParser;
        LOGGER.trace("Director created");
    }

    @Override
    public List<Figure> process(String source) {
        LOGGER.trace("Processing started");
        List<Figure> quadrilaterals = new ArrayList<>();
        try {
            List<String> lines = fileReader.read(source);
            for (String line : lines) {
                if (formatValidator.validate(line) && quadrilateralValidator.validate(line)) {
                    quadrilaterals.add(quadrilateralParser.parse(line));
                }
            }
            LOGGER.trace("Processing has completed successfully");
        } catch (WrongDataException e) {
            LOGGER.error("Invalid filename supplied", e);
        }
        return quadrilaterals;
    }
}
