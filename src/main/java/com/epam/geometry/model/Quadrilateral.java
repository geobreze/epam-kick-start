package com.epam.geometry.model;

public class Quadrilateral extends Figure {

    public Quadrilateral(Integer id, Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super(id, firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public void setPoints(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
        super.setPoints(firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

}
