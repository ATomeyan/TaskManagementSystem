package com.processing.taskmanagementsystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
        BindingResult result = ex.getBindingResult();
        String errorMsg = result.getFieldErrors().stream().iterator().next().getDefaultMessage();

        ExceptionResponse response = new ExceptionResponse(
                UUID.randomUUID(),
                BAD_REQUEST.getReasonPhrase(),
                errorMsg,
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserAssignedToTaskException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(UserAssignedToTaskException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(UserAlreadyExistException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                NOT_FOUND.value(),
                NOT_FOUND.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(InvalidObjectException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(EmptyException e, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                UUID.randomUUID(),
                BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                BAD_REQUEST.value(),
                BAD_REQUEST.name(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}