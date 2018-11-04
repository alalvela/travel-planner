package com.example.travelplanner.exception;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException() {
    }

    public PasswordInvalidException(String message) {
        super(message);
    }
}
