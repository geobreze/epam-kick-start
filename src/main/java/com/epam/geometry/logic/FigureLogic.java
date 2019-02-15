package com.epam.geometry.logic;

import com.epam.geometry.model.Figure;

public interface FigureLogic {
    double countSquare(Figure figure);

    double countPerimeter(Figure figure);

    boolean isConvex(Figure figure);

    boolean isSquare(Figure figure);

    boolean isRhomb(Figure figure);

    boolean isTrapezium(Figure figure);
}
