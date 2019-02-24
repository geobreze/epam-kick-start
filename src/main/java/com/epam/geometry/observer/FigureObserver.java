package com.epam.geometry.observer;

import com.epam.geometry.exceptions.SingletonException;
import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.FigureParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FigureObserver implements Observer {
    private static final Logger LOGGER = LogManager.getLogger(FigureObserver.class);
    private final Map<Integer, FigureParameters> parametersMap = new HashMap<>();
    private final FigureLogic figureLogic;
    private static FigureObserver instance;

    private FigureObserver(FigureLogic figureLogic) {
        this.figureLogic = figureLogic;
    }

    public static void initInstance(FigureLogic figureLogic) {
        if(instance == null) {
            instance = new FigureObserver(figureLogic);
        } else {
            String message = "Illegal attempt of singleton reinitialization";
            LOGGER.error(message);
            throw new SingletonException(message);
        }
    }

    public static FigureObserver getInstance() {
        if(instance != null) {
            return instance;
        } else {
            String message = "Singleton is not initialized";
            LOGGER.error(message);
            throw new SingletonException(message);
        }
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
