package com.epam.geometry.observer;

import com.epam.geometry.model.Point;

public class QuadrilateralObservable extends FigureObservable {
    public QuadrilateralObservable(Integer id, Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super(id, firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public void setPoints(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super.setPoints(firstPoint, secondPoint, thirdPoint, fourthPoint);
    }
}
