package com.epam.geometry.repository;

import com.epam.geometry.exceptions.NullUpdateException;
import com.epam.geometry.model.Figure;
import com.epam.geometry.repository.specification.FigureSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FigureSetRepository implements FigureRepository {
    private static final Logger LOGGER = LogManager.getLogger(FigureSetRepository.class);
    private final Set<Figure> figures = new HashSet<>();

    @Override
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    @Override
    public void removeFigure(Figure figure) {
        figures.remove(figure);
    }

    @Override
    public void updateFigure(Figure figure) {
        if (!figures.contains(figure)) {
            NullUpdateException nullUpdateException = new NullUpdateException("Repository doesn't store given figure");
            LOGGER.error(nullUpdateException.getMessage(), nullUpdateException);
            throw nullUpdateException;
        }
        figures.add(figure);
    }

    @Override
    public List<Figure> query(FigureSpecification specification) {
        return figures.stream().filter(specification::specified).collect(Collectors.toList());
    }
}
