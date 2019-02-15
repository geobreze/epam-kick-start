package com.epam.geometry.observer;

import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.FigureParameters;

import java.util.HashMap;
import java.util.Map;

public class FigureObserver implements Observer {
    private final FigureLogic figureLogic;
    private final Map<Integer, FigureParameters> parametersMap = new HashMap<>();

    public FigureObserver(FigureLogic figureLogic) {
        this.figureLogic = figureLogic;
    }

    public FigureParameters getParameters(Integer id) {
        return parametersMap.get(id);
    }

    @Override
    public void update(FigureObservable item) {
        double newArea = figureLogic.countSquare(item);
        double newPerimeter = figureLogic.countPerimeter(item);
        FigureParameters parameters = new FigureParameters(newArea, newPerimeter);

        parametersMap.put(item.getId(), parameters);
    }
}
