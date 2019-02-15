package com.epam.geometry.exceptions;

public class NullUpdateException extends RuntimeException {
    public NullUpdateException(String message) {
        super(message);
    }

    public NullUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
