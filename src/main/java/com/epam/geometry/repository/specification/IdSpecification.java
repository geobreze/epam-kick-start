package com.epam.geometry.repository.specification;

import com.epam.geometry.model.Figure;

public class IdSpecification implements FigureSpecification {
    private final Integer id;

    public IdSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public boolean specified(Figure figure) {
        return id.equals(figure.getId());
    }
}
