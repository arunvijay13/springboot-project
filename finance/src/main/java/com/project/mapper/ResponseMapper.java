package com.project.mapper;

import com.project.constants.ReturnCode;
import com.project.entity.Customer;
import com.project.entity.Principal;
import com.project.helper.DateHandler;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ResponseMapper {

    public ResponseEntity<Object> userResponseMapper(String username, String message,
                                                     HttpHeaders headers, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("username", username);
        body.put("message", message);
        if(headers != null) {
            body.put("Authorization", headers.get("token"));
        }
        body.put("status", httpStatus.value());
        body.put("returnCode", ReturnCode.SUCCESS);

        return new ResponseEntity<>(body, headers, httpStatus);
    }

    public ResponseEntity<Object> customerResponseMapper(Customer customer, String message,
                                                         HttpHeaders headers, HttpStatus httpStatus) {

        Principal principal = customer.getPrincipal();
        principal.setIssueDate(DateHandler.getFormattedDate(principal.getIssueDate()));
        principal.setDueDate(DateHandler.getFormattedDate(principal.getDueDate()));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("customerDetails", customer);
        body.put("message", message);
        body.put("status", httpStatus.value());
        body.put("returnCode", ReturnCode.SUCCESS);

        return new ResponseEntity<>(body, headers, httpStatus);
    }

    public ResponseEntity<Object> customerResponseMapper(Page<Customer> customers, String message,
                                                         HttpHeaders headers, HttpStatus httpStatus) {

        customers.forEach((customer) -> {
            Principal principal = customer.getPrincipal();
            principal.setIssueDate(DateHandler.getFormattedDate(principal.getIssueDate()));
            principal.setDueDate(DateHandler.getFormattedDate(principal.getDueDate()));
        });

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("customerDetails", customers);
        body.put("message", message);
        body.put("status", httpStatus.value());
        body.put("returnCode", ReturnCode.SUCCESS);

        return new ResponseEntity<>(body, headers, httpStatus);
    }

    public ResponseEntity<Object> customerResponseMapper(String message, HttpHeaders headers,
                                                         HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        body.put("status", httpStatus.value());
        body.put("returnCode", ReturnCode.SUCCESS);

        return new ResponseEntity<>(body, headers, httpStatus);
    }

}
