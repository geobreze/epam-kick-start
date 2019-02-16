package com.epam.geometry.generation;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdGeneratorTest {
    private final IdGenerator generator = new IdGenerator();

    @Test
    public void generateShouldReturnArithmeticalSequenceOfNumbers() {
        List<Integer> generatedIds = new ArrayList<>();
        generatedIds.add(generator.generate());
        generatedIds.add(generator.generate());
        generatedIds.add(generator.generate());
        List<Integer> expected = Arrays.asList(1, 2, 3);
        Assert.assertEquals(expected, generatedIds);
    }
}