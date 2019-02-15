package com.epam.geometry.repository.specification;

import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;

public class FirstOrthantSpecification implements FigureSpecification {
    @Override
    public boolean specified(Figure figure) {
        boolean result = true;
        for (Point point : figure.getPoints()) {
            for (double coordinate : point.getCoordinates()) {
                result = result && coordinate >= 0;
            }
        }
        return result;
    }
}
