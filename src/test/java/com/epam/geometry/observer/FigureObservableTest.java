package com.epam.geometry.observer;

// verify update() call

import com.epam.geometry.model.Point2D;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class FigureObservableTest {
    private static final Integer ID = 10;

    @Test
    public void setPointsShouldInvokeUpdate() {
        Observer observer = mock(Observer.class);
        QuadrilateralObservable observable = new QuadrilateralObservable(
                ID,
                new Point2D(-2, 2),
                new Point2D(3, 2),
                new Point2D(1, -1),
                new Point2D(-2, -1));
        observable.addObserver(observer);
        observable.setPoints(new Point2D(-1, 2),
                new Point2D(3, 2),
                new Point2D(1, -1),
                new Point2D(-2, -1));
        verify(observer).update(observable);
        verifyNoMoreInteractions(observer);
    }
}
