package com.epam.geometry.exceptions;

public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException() {
    }

    public OutOfBoundsException(String message) {
        super(message);
    }

    public OutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
