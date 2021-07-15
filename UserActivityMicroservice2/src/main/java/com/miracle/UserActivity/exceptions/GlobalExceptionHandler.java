package com.miracle.UserActivity.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log =LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> employeeNotFound(EmployeeNotFoundException exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        log.error(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> badRequest(Exception exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        log.error(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
