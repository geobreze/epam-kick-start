package com.epam.geometry.repository;

import com.epam.geometry.model.Figure;
import com.epam.geometry.repository.specification.FigureSpecification;

import java.util.List;

public interface FigureRepository {
    void addFigure(Figure figure);
    void removeFigure(Figure figure);
    void updateFigure(Figure figure);

    List<Figure> query(FigureSpecification specification);
}
