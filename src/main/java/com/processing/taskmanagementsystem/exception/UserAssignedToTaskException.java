package com.processing.taskmanagementsystem.exception;

public class UserAssignedToTaskException extends RuntimeException {
    public UserAssignedToTaskException(String message) {
        super(message);
    }
}