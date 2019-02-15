package com.epam.geometry.logic;

import com.epam.geometry.model.Point;
import com.epam.geometry.model.Vector;

import java.util.List;

public interface GeometryAlgorithms {
    double countVectorProduct(Vector first, Vector second);

    double countScalarProduct(Vector first, Vector second);

    double countDistance(Point first, Point second);

    boolean areLinesParallel(Point firstBegin, Point firstEnd, Point secondBegin, Point secondEnd);

    boolean areLinesPerpendicular(Point firstBegin, Point firstEnd, Point secondBegin, Point secondEnd);

    boolean arePointsBelongOneLine(Point first, Point second, Point third);

    boolean arePointsUnique(List<Point> points);
}
