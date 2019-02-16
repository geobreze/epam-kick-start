package com.epam.geometry.repository.specification;

import com.epam.geometry.model.Figure;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IdSpecificationTest {
    private static final Integer ID = 10;
    private final FigureSpecification specification = new IdSpecification(ID);

    @Test
    public void specifiedShouldReturnTrueWhenIdsAreEqual() {
        Figure figure = mock(Figure.class);
        when(figure.getId()).thenReturn(ID);
        boolean result = specification.specified(figure);
        Assert.assertTrue(result);
    }

    @Test
    public void specifiedShouldReturnFalseWhenIdsAreNotEqual() {
        Figure figure = mock(Figure.class);
        when(figure.getId()).thenReturn(ID + 1);
        boolean result = specification.specified(figure);
        Assert.assertFalse(result);
    }
}