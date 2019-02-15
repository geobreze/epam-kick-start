package com.epam.geometry.generation;

public class IdGenerator {
    private Integer id;

    IdGenerator() {
        id = 0;
    }

    public Integer generate() {
        return ++id;
    }
}
