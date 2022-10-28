package com.project.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerInvalidException extends RuntimeException {

    private HttpStatus status;

    public CustomerInvalidException () {
        super();
    }

    public CustomerInvalidException (String message) {
        super(message);
    }

    public CustomerInvalidException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomerInvalidException (String message, Throwable cause) {
        super(message,cause);
    }
}
