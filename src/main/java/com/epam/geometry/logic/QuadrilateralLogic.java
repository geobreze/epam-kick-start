package com.epam.geometry.logic;

import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;
import com.epam.geometry.model.Vector;
import com.epam.geometry.model.Vector2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadrilateralLogic implements FigureLogic {
    private final GeometryAlgorithms geometryAlgorithms;

    public QuadrilateralLogic(GeometryAlgorithms geometryAlgorithms) {
        this.geometryAlgorithms = geometryAlgorithms;
    }

    @Override
    public double countSquare(Figure figure) {
        // formula is abs[(sum(x_i * y_(i + 1) - y_i * x_(i + 1)) + x_n * y_1 - y_n * x_1) / 2], i from 1 to n-1
        List<Vector> vectorsToPoints = getVectorsToPoints(figure.getPoints());

        double sumOfVectorProducts = 0;
        for (int i = 1; i < vectorsToPoints.size() + 1; ++i) {
            sumOfVectorProducts += geometryAlgorithms.countVectorProduct(vectorsToPoints.get(i - 1),
                    vectorsToPoints.get(i % vectorsToPoints.size()));
        }

        return Math.abs(sumOfVectorProducts) / 2;
    }

    @Override
    public double countPerimeter(Figure figure) {
        List<Point> points = figure.getPoints();

        double perimeter = 0;
        for (int i = 1; i < points.size() + 1; ++i) {
            perimeter += geometryAlgorithms.countDistance(points.get(i - 1),
                    points.get(i % points.size()));
        }

        return perimeter;
    }

    @Override
    public boolean isConvex(Figure figure) {
        List<Vector> vectorsToPoints = getVectorsToPoints(figure.getPoints());
        List<Double> vectorProductSignums = new ArrayList<>();

        for (int i = 1; i < vectorsToPoints.size() + 1; ++i) {
            vectorProductSignums.add(Math.signum(geometryAlgorithms.countVectorProduct(vectorsToPoints.get(i - 1),
                    vectorsToPoints.get(i % vectorsToPoints.size()))));
        }

        return checkNumbersForEquality(vectorProductSignums);
    }

    @Override
    public boolean isSquare(Figure figure) {
        List<Point> points = figure.getPoints();

        boolean perpendicularity = true;
        for (int i = 1; i < points.size(); ++i) {
            perpendicularity = perpendicularity &&
                    geometryAlgorithms.areLinesPerpendicular(points.get(i - 1), points.get(i), points.get(i),
                            points.get((i + 1) % points.size()));
        }

        return isRhomb(figure) && perpendicularity;
    }

    @Override
    public boolean isRhomb(Figure figure) {
        List<Point> points = figure.getPoints();
        List<Double> distances = new ArrayList<>();

        for (int i = 1; i < points.size(); ++i) {
            distances.add(geometryAlgorithms.countDistance(points.get(i - 1), points.get(i)));
        }

        return checkNumbersForEquality(distances);
    }

    @Override
    public boolean isTrapezium(Figure figure) {
        Point firstPoint = figure.getPoint(0);
        Point secondPoint = figure.getPoint(1);
        Point thirdPoint = figure.getPoint(2);
        Point fourthPoint = figure.getPoint(3);
        return geometryAlgorithms.areLinesParallel(firstPoint, secondPoint, fourthPoint, thirdPoint) ^
                geometryAlgorithms.areLinesParallel(fourthPoint, firstPoint, thirdPoint, secondPoint);
    }

    private boolean checkNumbersForEquality(List<Double> numbers) {
        Set<Double> numbersSet = new HashSet<>(numbers);
        return numbersSet.size() <= 1;
    }

    private List<Vector> getVectorsToPoints(List<Point> points) {
        List<Vector> vectors = new ArrayList<>();
        for (Point point : points) {
            vectors.add(new Vector2D(point));
        }
        return vectors;
    }

}
