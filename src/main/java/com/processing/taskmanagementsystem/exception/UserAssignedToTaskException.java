package com.processing.taskmanagementsystem.exception;


public class UserAssignedToTaskException extends RuntimeException {
    public UserAssignedToTaskException() {
    }

    public UserAssignedToTaskException(String message) {
        super(message);
    }
}