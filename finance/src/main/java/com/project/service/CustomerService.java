package com.project.service;

import com.project.constants.CustomerStatus;
import com.project.entity.Customer;
import com.project.entity.Principal;
import com.project.exceptions.CustomerInvalidException;
import com.project.helper.DateHandler;
import com.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        Principal principal = customer.getPrincipal();
        // store the raw format of data to the database
        principal.setIssueDate(DateHandler.getUnFormattedIssueDate(new Date()));
        principal.setDueDate(DateHandler.getUnFormattedDueDate(principal.getTime()));
        principal.setAmountPaid("0");
        principal.setTotalAmount(interestCalculation(principal.getSum(), principal.getTime(), principal.getRate()));
        customer.setCustomerStatus(CustomerStatus.PENDING);
        customer.setPrincipal(principal);
        customerRepository.save(customer);
    }

    public Page<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Customer> pagedResult = customerRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        }
        throw new CustomerInvalidException("No customer record found",
                HttpStatus.NOT_FOUND);
    }

    public Customer fetchCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomerInvalidException(MessageFormat.format("could not found record with {0}", id),
                HttpStatus.NOT_FOUND);
    }

    public void deleteCustomer(long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            customerRepository.deleteById(id);
        }
    }

    public void updateCustomer(Customer customer) {
        System.out.println(customer);
        Principal principal = customer.getPrincipal();
        System.out.println(principal);
        principal.setIssueDate(DateHandler.getRawDate(principal.getIssueDate()));
        principal.setDueDate(DateHandler.calculateDate(principal.getIssueDate(), principal.getTime()));
        customer.setPrincipal(principal);
        customerRepository.save(customer);
    }

    public Page<Customer> findBySearchCriteria(Specification<Customer> spec, Pageable page) {
        Page<Customer> customerPage = customerRepository.findAll(spec, page);
        if (customerPage.hasContent()) {
            return customerPage;
        }
        throw new CustomerInvalidException("record not found", HttpStatus.NOT_FOUND);
    }

    private String interestCalculation(String sum, String time, String rate) {
        double result = (Integer.parseInt(sum) * Integer.parseInt(time) * Double.parseDouble(rate)) / (double) 100;
        return String.valueOf(result + Integer.parseInt(sum));
    }

    private String subtractTotalAmount (String totalamount, String amountPaid) {
        return String.valueOf(Double.parseDouble(totalamount) - Double.parseDouble(amountPaid));
    }
}
