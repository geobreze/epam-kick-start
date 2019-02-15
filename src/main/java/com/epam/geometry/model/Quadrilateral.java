package com.epam.geometry.model;

import com.epam.geometry.generation.IdGenerator;

public class Quadrilateral extends Figure {

    public Quadrilateral(IdGenerator idGenerator, Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super(idGenerator, firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public void setPoints(Point firstPoint, Point secondPoint, Point thirdPoint,Point fourthPoint) {
        super.setPoints(firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

}
