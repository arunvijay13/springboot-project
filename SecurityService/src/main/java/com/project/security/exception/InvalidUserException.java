package com.project.security.exception;

public class InvalidUserException extends RuntimeException {
    public  InvalidUserException(String message) {
        super(message);
    }
}
