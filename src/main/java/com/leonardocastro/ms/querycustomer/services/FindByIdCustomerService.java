package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByIdCustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Optional<CustomerEntity> findById(Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        return customer;
    }
}
