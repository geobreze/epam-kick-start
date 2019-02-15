package com.epam.geometry.observer;

import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Point;
import com.sun.org.glassfish.gmbal.ManagedObject;

public class QuadrilateralObservable extends FigureObservable {
    public QuadrilateralObservable(IdGenerator idGenerator, Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super(idGenerator, firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public void setPoints(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super.setPoints(firstPoint, secondPoint, thirdPoint, fourthPoint);
    }
}
