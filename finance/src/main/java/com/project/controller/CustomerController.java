package com.project.controller;

import com.project.DTO.CustomerRequest;
import com.project.DTO.CustomerSearchDto;
import com.project.converter.CustomerConverter;
import com.project.entity.Customer;
import com.project.helper.CustomerSpecificationBuilder;
import com.project.helper.SearchCriteria;
import com.project.mapper.ResponseMapper;
import com.project.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",  maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> customer(@Valid @RequestBody CustomerRequest customerRequest) {
        log.info("customer creation request started");
        Customer customer = CustomerConverter.convert(customerRequest);
        customerService.createCustomer(customer);
        log.info(MessageFormat.format("customer successfully created with record id of {0}",
                customer.getCustomerId()));
        return responseMapper.customerResponseMapper(customer, "customer successfully created",
                null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> fetchHandler(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "customerId") String sortBy) {
        log.info("Get all the customer");
        Page<Customer> customerList = customerService.getAllCustomers(pageNo, pageSize, sortBy);
        log.info("All the customers are fetched successfully");
        return responseMapper.customerResponseMapper(customerList, "All records are fetched",
                null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> fetchHandler(@PathVariable long id) {
        log.info("processing the particular customer");
        Customer customer = customerService.fetchCustomerById(id);
        log.info("customer fetched successfully");
        return responseMapper.customerResponseMapper(customer, "customer successfully fetched",
                null, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> updateHandler(@RequestBody Customer customer) {

        log.info("started processing the customer update request");
        customerService.updateCustomer(customer);
        return responseMapper.customerResponseMapper(customer, "customer successfully updated",
                null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHandler(@PathVariable long id) {
        log.info("deleteHandler is called");
        customerService.deleteCustomer(id);
        String message = "record is deleted successfully";
        log.info("record is deleted successfully");
        return responseMapper.customerResponseMapper(message,
                null, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchHandler(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestBody CustomerSearchDto customerSearchDto) {

        CustomerSpecificationBuilder builder = new CustomerSpecificationBuilder();
        List<SearchCriteria> criteriaList = customerSearchDto.getSearchCriteriaList();

        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(customerSearchDto.getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Customer> employeePage = customerService.findBySearchCriteria(builder.build(), page);

        return responseMapper.customerResponseMapper(employeePage,"record are fetched successfully",
                null, HttpStatus.OK);
    }

}
