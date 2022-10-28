package com.project.repository;

import com.project.constants.CustomerStatus;
import com.project.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Override
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

    Page<Customer> findByCustomerStatus (Pageable pageable, CustomerStatus customerStatus);
}
