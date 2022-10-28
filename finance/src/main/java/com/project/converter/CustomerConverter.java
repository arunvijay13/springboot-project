package com.project.converter;

import com.project.DTO.CustomerRequest;
import com.project.entity.Customer;

public class CustomerConverter {
    public static Customer convert(CustomerRequest customerRequest) {
        return Customer.builder().firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .gender(customerRequest.getGender())
                .addresses(customerRequest.getAddresses())
                .phoneNumbers(customerRequest.getPhoneNumbers())
                .principal(customerRequest.getPrincipal())
                .build();
    }
}
