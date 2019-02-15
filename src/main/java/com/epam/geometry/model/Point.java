package com.epam.geometry.model;

import com.epam.geometry.exceptions.OutOfBoundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Point {
    private static final Logger LOGGER = LogManager.getLogger(Point.class);

    private final List<Double> coordinates;

    public Point(Double... coordinates) {
        this.coordinates = new ArrayList<>();
        this.coordinates.addAll(Arrays.asList(coordinates));
    }

    public Point(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public double getCoordinate(int index) {
        if (index < coordinates.size()) {
            return coordinates.get(index);
        } else {
            OutOfBoundsException outOfBoundsException =
                    new OutOfBoundsException("Point has " + coordinates.size() + "; " + index + "coordinate was requested");
            LOGGER.error(outOfBoundsException.getMessage(), outOfBoundsException);
            throw outOfBoundsException;
        }
    }

    public List<Double> getCoordinates() {
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
        Point point = (Point) o;
        return coordinates.equals(point.coordinates);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        for (double coordinate : coordinates) {
            result = prime * result + Double.valueOf(coordinate).hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (double coordinate : coordinates) {
            stringBuilder.append(coordinate).append(",");
        }
        if (',' == stringBuilder.charAt(stringBuilder.length() - 1)) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

}
