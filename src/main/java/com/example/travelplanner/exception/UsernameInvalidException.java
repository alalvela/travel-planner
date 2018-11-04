package com.example.travelplanner.exception;

public class UsernameInvalidException extends RuntimeException  {

    public UsernameInvalidException() {
    }

    public UsernameInvalidException(String message) {
        super(message);
    }
}
