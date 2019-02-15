package com.epam.geometry.logic;

import com.epam.geometry.model.Point;
import com.epam.geometry.model.Vector;
import com.epam.geometry.model.Vector2D;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Geometry2DAlgorithms implements GeometryAlgorithms {

    public Geometry2DAlgorithms() {
    }

    @Override
    public double countVectorProduct(Vector first, Vector second) {
        double minuend = first.getCoordinate(0) * second.getCoordinate(1);
        double subtrahend = first.getCoordinate(1) * second.getCoordinate(0);
        return minuend - subtrahend;
    }

    @Override
    public double countScalarProduct(Vector first, Vector second) {
        return first.getCoordinate(0) * second.getCoordinate(0) +
                first.getCoordinate(1) * second.getCoordinate(1);
    }

    @Override
    public double countDistance(Point first, Point second) {
        Vector vector = Vector2D.createByBeginAndEnd(first, second);
        return Math.sqrt(vector.getCoordinate(0) * vector.getCoordinate(0)
                + vector.getCoordinate(1) * vector.getCoordinate(1));
    }

    @Override
    public boolean areLinesParallel(Point firstBegin, Point firstEnd, Point secondBegin, Point secondEnd) {
        Vector firstVector = Vector2D.createByBeginAndEnd(firstBegin, firstEnd);
        Vector secondVector = Vector2D.createByBeginAndEnd(secondBegin, secondEnd);
        return Double.compare(Math.abs(countVectorProduct(firstVector, secondVector)), 0) == 0;
    }

    @Override
    public boolean areLinesPerpendicular(Point firstBegin, Point firstEnd, Point secondBegin, Point secondEnd) {
        Vector firstVector = Vector2D.createByBeginAndEnd(firstBegin, firstEnd);
        Vector secondVector = Vector2D.createByBeginAndEnd(secondBegin, secondEnd);
        return Double.compare(Math.abs(countScalarProduct(firstVector, secondVector)), 0) == 0;
    }

    @Override
    public boolean arePointsBelongOneLine(Point first, Point second, Point third) {
        return Double.compare((third.getCoordinate(0) - first.getCoordinate(0))
                        * (second.getCoordinate(1) - first.getCoordinate(1)),
                (third.getCoordinate(1) - first.getCoordinate(1))
                        * (second.getCoordinate(0) - first.getCoordinate(0))) == 0;
    }

    @Override
    public boolean arePointsUnique(List<Point> points) {
        Set<Point> pointSet = new HashSet<>(points);

        return pointSet.size() == points.size();
    }

}
