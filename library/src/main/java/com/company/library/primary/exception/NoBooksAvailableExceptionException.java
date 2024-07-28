package com.company.library.primary.exception;

public class NoBooksAvailableExceptionException extends RuntimeException {
    public NoBooksAvailableExceptionException(String message) {
        super(message);
    }
}
