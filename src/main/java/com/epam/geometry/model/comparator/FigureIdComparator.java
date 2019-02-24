package com.epam.geometry.model.comparator;

import com.epam.geometry.model.Figure;

import java.util.Comparator;

public class FigureIdComparator implements Comparator<Figure> {
    @Override
    public int compare(Figure first, Figure second) {
        Integer firstId = first.getId();
        Integer secondId = second.getId();
        return firstId.compareTo(secondId);
    }
}
