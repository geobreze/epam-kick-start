package com.epam.geometry.exceptions;

public class IllegalParseException extends RuntimeException {
    public IllegalParseException() {
    }

    public IllegalParseException(String message) {
        super(message);
    }

    public IllegalParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
