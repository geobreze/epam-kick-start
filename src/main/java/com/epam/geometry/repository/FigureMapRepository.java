package com.epam.geometry.repository;

import com.epam.geometry.exceptions.NullUpdateException;
import com.epam.geometry.model.Figure;
import com.epam.geometry.repository.specification.FigureSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class FigureMapRepository implements FigureRepository {
    private static final Logger LOGGER = LogManager.getLogger(FigureMapRepository.class);
    private final Map<Integer, Figure> figures = new HashMap<>();

    @Override
    public void addFigure(Figure figure) {
        Integer id = figure.getId();
        figures.put(id, figure);
    }

    @Override
    public void removeFigure(Integer id) {
        figures.remove(id);
    }

    @Override
    public void updateFigure(Integer id, Figure updatedFigure) {
        if(!figures.containsKey(id)) {
            String message = "Repository doesn't store given figure";
            LOGGER.error(message);
            throw new NullUpdateException(message);
        }
        figures.put(id, updatedFigure);
    }

    @Override
    public List<Figure> query(FigureSpecification specification) {
        return figures
                .values()
                .stream()
                .filter(specification::specified)
                .collect(Collectors.toList());
    }

    @Override
    public List<Figure> query(FigureSpecification specification, Comparator<Figure> comparator) {
        return figures
                .values()
                .stream()
                .filter(specification::specified)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
