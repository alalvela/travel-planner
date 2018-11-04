package com.example.travelplanner.exception;

public class InvalidDatesException extends RuntimeException {

    public InvalidDatesException() {
    }

    public InvalidDatesException(String message) {
        super(message);
    }
}
