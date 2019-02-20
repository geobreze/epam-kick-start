package com.epam.geometry.repository.specification;

import com.epam.geometry.model.Figure;

public class AllFiguresSpecification implements FigureSpecification {
    @Override
    public boolean specified(Figure figure) {
        return true;
    }
}
