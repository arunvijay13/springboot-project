package com.project.exceptions;

import com.project.constants.ReturnCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

      @Override
      protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers, HttpStatus status, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", status.value());

            Map<String, Object> errors = new LinkedHashMap<>();
            List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();

            for(FieldError fieldError : fieldErrorList ) {
                  System.out.println(fieldError.getField());
                  errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            body.put("message", errors);
            body.put("returnCode", ReturnCode.FAILURE);

            return new ResponseEntity<>(body, headers, status);

      }

      @ExceptionHandler (InvalidUserException.class)
      public ResponseEntity<Object> userExceptionHandler(InvalidUserException exception) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("message", exception.getMessage());
            body.put("returnCode", ReturnCode.FAILURE);

            return new ResponseEntity<>(body, null, HttpStatus.BAD_REQUEST);
      }

      @ExceptionHandler (CustomerInvalidException.class)
      public ResponseEntity<Object> customerExceptionHandler(CustomerInvalidException exception) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", exception.getStatus().value());
            body.put("message", exception.getMessage());
            body.put("returnCode", ReturnCode.FAILURE);

            return new ResponseEntity<>(body, null, exception.getStatus());
      }
}
