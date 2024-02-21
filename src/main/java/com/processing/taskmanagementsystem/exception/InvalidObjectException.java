package com.processing.taskmanagementsystem.exception;

public class InvalidObjectException extends RuntimeException {
    public InvalidObjectException() {
    }

    public InvalidObjectException(String message) {
        super(message);
    }
}