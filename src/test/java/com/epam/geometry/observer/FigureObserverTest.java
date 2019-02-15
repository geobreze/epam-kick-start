package com.epam.geometry.observer;

import com.epam.geometry.generation.IdGenerator;
import com.epam.geometry.logic.FigureLogic;
import com.epam.geometry.model.FigureParameters;
import com.epam.geometry.model.Point2D;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FigureObserverTest {
    private static final double EPSILON = 10e-3;
    private static final double TEST_AREA_AND_PERIMETER = 10.0;
    private static final Integer ID = 0;
    private static final IdGenerator ID_GENERATOR = mock(IdGenerator.class);
    private static final FigureObservable QUADRILATERAL_OBSERVABLE = new QuadrilateralObservable(
            ID_GENERATOR,
            new Point2D(-1.0, 1.0),
            new Point2D(1.0, 1.0),
            new Point2D(1.0, -1.0),
            new Point2D(-1.0, -1.0)
    );

    @BeforeClass
    public static void initGenerator() {
        when(ID_GENERATOR.generate()).thenReturn(ID);
    }

    @Test
    public void updateShouldUpdateAreaAndPerimeterWhenFigureSupplied() {
        FigureLogic figureLogic = mock(FigureLogic.class);
        when(figureLogic.countSquare(QUADRILATERAL_OBSERVABLE)).thenReturn(TEST_AREA_AND_PERIMETER);
        when(figureLogic.countPerimeter(QUADRILATERAL_OBSERVABLE)).thenReturn(TEST_AREA_AND_PERIMETER);
        FigureObserver figureObserver = new FigureObserver(figureLogic);

        figureObserver.update(QUADRILATERAL_OBSERVABLE);
        FigureParameters actual = figureObserver.getParameters(ID);

        Assert.assertEquals(TEST_AREA_AND_PERIMETER, actual.getArea(), EPSILON);
        Assert.assertEquals(TEST_AREA_AND_PERIMETER, actual.getPerimeter(), EPSILON);
        verify(figureLogic).countSquare(QUADRILATERAL_OBSERVABLE);
        verify(figureLogic).countPerimeter(QUADRILATERAL_OBSERVABLE);
        verifyNoMoreInteractions(figureLogic);
    }
}
