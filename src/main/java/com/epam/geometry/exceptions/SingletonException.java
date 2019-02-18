package com.epam.geometry.exceptions;

public class SingletonException extends RuntimeException {
    public SingletonException() {
    }

    public SingletonException(String message) {
        super(message);
    }

    public SingletonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SingletonException(Throwable cause) {
        super(cause);
    }
}
