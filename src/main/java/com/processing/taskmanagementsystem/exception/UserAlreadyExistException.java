package com.processing.taskmanagementsystem.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}