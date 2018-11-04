package com.example.travelplanner.controller;

import com.example.travelplanner.exception.InvalidDatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(InvalidDatesException.class)
    public ResponseEntity handleInvalidDatesException(InvalidDatesException ex, HttpServletRequest request)  {
        logger.error(String.format("Request %s with method %s threw InvalidDatesException.\n Exception message: \n %s", request.getRequestURL(), request.getMethod(), ex.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid dates entered!");
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity handleDateTimeParseException(DateTimeParseException ex, HttpServletRequest  request) {
        logger.error(String.format("Request %s with method %s threw DateTimeParseException.\n Exception message: \n %s", request.getRequestURL(), request.getMethod(), ex.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date must be of dd.MM.yyyy format!");
    }

}
