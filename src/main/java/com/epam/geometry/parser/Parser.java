package com.epam.geometry.parser;

public interface Parser<T> {
    T parse(String str);
}
