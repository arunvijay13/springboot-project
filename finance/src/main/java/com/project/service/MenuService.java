package com.project.service;

import com.project.constants.CustomerStatus;
import com.project.entity.Customer;
import com.project.exceptions.CustomerInvalidException;
import com.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> completedRecordService(CustomerStatus status, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Customer> pagedResult = customerRepository.findByCustomerStatus(paging, status);
        if (pagedResult.hasContent()) {
            return pagedResult;
        }
        throw new CustomerInvalidException("No customer record found",
                HttpStatus.NOT_FOUND);
    }

}
