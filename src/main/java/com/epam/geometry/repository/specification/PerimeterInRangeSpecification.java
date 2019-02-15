package com.epam.geometry.repository.specification;

import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.Figure;

public class PerimeterInRangeSpecification implements FigureSpecification {
    private final FigureLogic logic;
    private final double min;
    private final double max;

    PerimeterInRangeSpecification(FigureLogic logic, double min, double max) {
        this.logic = logic;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean specified(Figure figure) {
        double perimeter = logic.countPerimeter(figure);
        return  perimeter >= min && perimeter <= max;
    }
}
