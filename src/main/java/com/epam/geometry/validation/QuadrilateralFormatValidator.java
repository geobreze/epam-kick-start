package com.epam.geometry.validation;

public class QuadrilateralFormatValidator implements FormatValidator {
    private static final String POINT_REGEXP = "(-?\\d+\\.\\d+,-?\\d+\\.\\d+)";
    private static final String QUADRILATERAL_REGEXP = "^(" + POINT_REGEXP + " ){3}" + POINT_REGEXP + "$";

    @Override
    public boolean validate(String str) {
        return str.matches(QUADRILATERAL_REGEXP);
    }
}
