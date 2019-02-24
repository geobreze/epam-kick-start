package com.epam.geometry.observer;

import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class FigureObservable extends Figure implements Observable {
    private final List<Observer> observerList = new ArrayList<>();

    public FigureObservable(Integer id, Point... points) {
        super(id, points);
    }

    @Override
    protected void setPoints(Point... points) {
        super.setPoints(points);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    private void notifyObservers() {
        observerList.forEach(observer -> observer.update(this));
    }
}
