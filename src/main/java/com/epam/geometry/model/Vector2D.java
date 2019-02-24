package com.epam.geometry.model;

public class Vector2D extends Vector {

    private Vector2D(Point begin, Point end) {
        super(new Point2D(end.getCoordinate(0) - begin.getCoordinate(0),
                end.getCoordinate(1) - begin.getCoordinate(1)));
    }

    public Vector2D(Point coordinates) {
        super(coordinates);
    }

    public static Vector2D createByBeginAndEnd(Point begin, Point end) {
        return new Vector2D(begin, end);
    }
}
