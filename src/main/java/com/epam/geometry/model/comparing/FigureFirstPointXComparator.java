package com.epam.geometry.model.comparing;

import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;

import java.util.Comparator;

public class FigureFirstPointXComparator implements Comparator<Figure> {
    @Override
    public int compare(Figure first, Figure second) {
        Point firstFigurePoint = first.getPoint(0);
        Point secondFigurePoint = second.getPoint(0);
        double firstX = firstFigurePoint.getCoordinate(0);
        double secondX = secondFigurePoint.getCoordinate(0);
        return (int)(firstX - secondX);
    }
}
