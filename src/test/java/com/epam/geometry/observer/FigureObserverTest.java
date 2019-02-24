package com.epam.geometry.observer;

import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.FigureParameters;
import com.epam.geometry.model.Point2D;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FigureObserverTest {
    private static final double EPSILON = 10e-3;
    private static final double TEST_AREA_AND_PERIMETER = 10.0;
    private static final Integer ID = 0;
    private static final FigureObservable QUADRILATERAL_OBSERVABLE = new QuadrilateralObservable(
            ID,
            new Point2D(-1.0, 1.0),
            new Point2D(1.0, 1.0),
            new Point2D(1.0, -1.0),
            new Point2D(-1.0, -1.0)
    );

    @Test
    public void updateShouldUpdateAreaAndPerimeterWhenFigureSupplied() {
        FigureLogic figureLogic = mock(FigureLogic.class);
        when(figureLogic.countSquare(QUADRILATERAL_OBSERVABLE)).thenReturn(TEST_AREA_AND_PERIMETER);
        when(figureLogic.countPerimeter(QUADRILATERAL_OBSERVABLE)).thenReturn(TEST_AREA_AND_PERIMETER);
        FigureObserver.initInstance(figureLogic);
        FigureObserver figureObserver = FigureObserver.getInstance();

        figureObserver.update(QUADRILATERAL_OBSERVABLE);
        FigureParameters actual = figureObserver.getParameters(ID);

        Assert.assertEquals(TEST_AREA_AND_PERIMETER, actual.getArea(), EPSILON);
        Assert.assertEquals(TEST_AREA_AND_PERIMETER, actual.getPerimeter(), EPSILON);
        verify(figureLogic).countSquare(QUADRILATERAL_OBSERVABLE);
        verify(figureLogic).countPerimeter(QUADRILATERAL_OBSERVABLE);
        verifyNoMoreInteractions(figureLogic);
    }
}
