package com.epam.geometry.observer;

import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.model.Figure;
import com.epam.geometry.model.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class FigureObservable extends Figure implements Observable {
    private List<Observer> observerList = new ArrayList<>();

    public FigureObservable(IdGenerator idGenerator, Point... points) {
        super(idGenerator, points);
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
