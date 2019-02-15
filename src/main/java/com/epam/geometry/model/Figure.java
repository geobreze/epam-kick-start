package com.epam.geometry.model;

import com.epam.geometry.exceptions.OutOfBoundsException;
import com.epam.geometry.generation.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Figure {
    private static final Logger LOGGER = LogManager.getLogger(Figure.class);
    private final IdGenerator idGenerator;
    private final Integer id;
    private final List<Point> points;

    public Figure(IdGenerator idGenerator, Point... points) {
        this.idGenerator = idGenerator;
        this.points = new ArrayList<>();
        this.points.addAll(Arrays.asList(points));
        this.id = idGenerator.generate();
    }

    public Figure(IdGenerator idGenerator, List<Point> points) {
        this.idGenerator = idGenerator;
        this.points = points;
        this.id = idGenerator.generate();
    }

    protected void setPoints(Point... points) {
        this.points.addAll(Arrays.asList(points));
    }

    public Point getPoint(int index) {
        if (index < points.size()) {
            return points.get(index);
        } else {
            OutOfBoundsException outOfBoundsException =
                    new OutOfBoundsException("Figure has " + points.size() + "; " + index + "point was requested");
            LOGGER.error(outOfBoundsException.getMessage(), outOfBoundsException);
            throw outOfBoundsException;
        }
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Point point : points) {
            stringBuilder.append(point.toString()).append(" ");
        }
        if (' ' == stringBuilder.charAt(stringBuilder.length() - 1)) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

}
