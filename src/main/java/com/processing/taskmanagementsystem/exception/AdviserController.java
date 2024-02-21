package com.processing.taskmanagementsystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AssignedException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(AssignedException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(AlreadyExistException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(InvalidObjectException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}