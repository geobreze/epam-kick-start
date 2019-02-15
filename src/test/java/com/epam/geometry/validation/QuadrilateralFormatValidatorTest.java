package com.epam.geometry.validation;

import org.junit.Assert;
import org.junit.Test;

public class QuadrilateralFormatValidatorTest {
    private FormatValidator formatValidator = new QuadrilateralFormatValidator();

    @Test
    public void validateShouldReturnFalseForStringWithChar() {
        String data = "-1.0,1.0 1.0,-1.0 1.0z,1.0 3.0,1.0";
        boolean result = formatValidator.validate(data);
        Assert.assertFalse(result);
    }

    @Test
    public void validateShouldReturnFalseForStringWithThreeParameters() {
        String data = "-1.0,1.0 3.0,1.0 1.0,-1.0";
        boolean result = formatValidator.validate(data);
        Assert.assertFalse(result);
    }

    @Test
    public void validateShouldReturnFalseForStringWithFiveParameters() {
        String data = "-1.0,1.0 1.0,-1.0 1.0,1.0 -1.0,-1.0 5.3,10.4";
        boolean result = formatValidator.validate(data);
        Assert.assertFalse(result);
    }

    @Test
    public void validateShouldReturnFalseForStringWithInteger() {
        String data = "-1.0,1.0 1.0,-1.0 120,1.0 -1.0,-1.0";
        boolean result = formatValidator.validate(data);
        Assert.assertFalse(result);
    }

    @Test
    public void validateShouldReturnTrueForTrapezium() {
        String data = "-2.0,2.0 3.0,2.0 1.0,-1.0 -2.0,-1.0";
        boolean result = formatValidator.validate(data);
        Assert.assertTrue(result);
    }

    @Test
    public void validateShouldReturnTrueForDoubleDigits() {
        String data = "-24.4214,11.64 64.1,-71.4 1.0,-1.0 -2.0,-1.0";
        boolean result = formatValidator.validate(data);
        Assert.assertTrue(result);
    }

}
