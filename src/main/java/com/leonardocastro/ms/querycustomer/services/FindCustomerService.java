package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerEntity> findAllCustomer() {
        List<CustomerEntity> responseDTOS = customerRepository.findAll();
        return responseDTOS;
    }
}
