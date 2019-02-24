package com.epam.geometry.model;

import com.epam.geometry.exceptions.OutOfBoundsException;
import com.epam.geometry.generation.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public abstract class Figure {
    private static final Logger LOGGER = LogManager.getLogger(Figure.class);
    private final Integer id;
    private final List<Point> points;

    public Figure(Integer id, Point... points) {
        this.points = new ArrayList<>();
        this.points.addAll(Arrays.asList(points));
        this.id = id;
    }

    public Figure(IdGenerator idGenerator, List<Point> points) {
        this.points = points;
        this.id = idGenerator.generate();
    }

    protected void setPoints(Point... points) {
        this.points.addAll(Arrays.asList(points));
    }

    public Point getPoint(int index) {
        if (index >= points.size()) {
            String message = "Figure has " + points.size() + "; " + index + "point was requested";
            LOGGER.error(message);
            throw new OutOfBoundsException(message);
        }
        return points.get(index);
    }

    public List<Point> getPoints() {
        return points;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Figure figure = (Figure) o;

        return points.equals(figure.points);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        for (Point point : points) {
            result = prime * result + point.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for(Point point : points) {
            stringJoiner.add(point.toString());
        }
        return stringJoiner.toString();
    }

}
