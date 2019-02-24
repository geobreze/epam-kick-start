package com.epam.geometry.model;

import com.epam.geometry.exceptions.OutOfBoundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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
        if(index >= coordinates.size()) {
            String message = "Point has " + coordinates.size() + "; " + index + "coordinate was requested";
            LOGGER.error(message);
            throw new OutOfBoundsException(message);
        }
        return coordinates.get(index);
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
        StringJoiner stringJoiner = new StringJoiner(",");
        for (double coordinate : coordinates) {
            stringJoiner.add(Double.toString(coordinate));
        }
        return stringJoiner.toString();
    }

}
