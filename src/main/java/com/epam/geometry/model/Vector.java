package com.epam.geometry.model;

public abstract class Vector {
    private final Point coordinates;

    public Vector(Point coordinates) {
        this.coordinates = coordinates;
    }

    public double getCoordinate(int index) {
        return coordinates.getCoordinate(index);
    }

    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return coordinates.equals(vector.coordinates);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + coordinates.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return coordinates.toString();
    }
}
