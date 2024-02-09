package com.processing.taskmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class ExceptionResponse {

    private LocalDateTime dateTime;
    private int httpStatusCode;
    private String errorResponse;
    private String message;
}