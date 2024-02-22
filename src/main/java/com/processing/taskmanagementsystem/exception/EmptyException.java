package com.processing.taskmanagementsystem.exception;

/**
 * @author Artur Tomeyan
 * @date 22.02.2024
 */
public class EmptyException extends RuntimeException {
    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }
}