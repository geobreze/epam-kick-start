package com.epam.geometry;

import com.epam.geometry.model.Figure;

import java.util.List;

public interface FigureDirector {
    List<Figure> process(String source);
}
