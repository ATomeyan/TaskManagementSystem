package com.processing.taskmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
public class ExceptionResponse {

    private UUID guid;
    private String errorResponse;
    private String message;
    private int httpStatusCode;
    private String statusName;
    private String path;
    private String method;
    private LocalDateTime dateTime;
}