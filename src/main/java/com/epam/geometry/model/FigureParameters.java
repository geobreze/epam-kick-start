package com.epam.geometry.model;

public class FigureParameters {
    private final double area;
    private final double perimeter;

    public FigureParameters(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}
