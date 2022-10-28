package com.project.controller;

import com.project.constants.CustomerStatus;
import com.project.entity.Customer;
import com.project.mapper.ResponseMapper;
import com.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",  maxAge = 3600)
@RequestMapping ("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ResponseMapper responseMapper;

    @GetMapping
    public ResponseEntity<Object> fetchCompletedRecords (@RequestParam(name = "status") CustomerStatus customerStatus,
                                                         @RequestParam(defaultValue = "0") Integer pageNo,
                                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                                         @RequestParam(defaultValue = "customerId") String sortBy) {
        Page<Customer> customerList = menuService.completedRecordService(customerStatus, pageNo, pageSize, sortBy);
        return responseMapper.customerResponseMapper(customerList, "All records are fetched",
                null, HttpStatus.OK);
    }

}
