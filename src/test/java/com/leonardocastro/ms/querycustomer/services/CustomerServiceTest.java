package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findAllCustomer() {
        List<CustomerEntity> expectedCustomers = new ArrayList<>();

        List<CustomerEntity> allCustomer = customerService.findAllCustomer();
        Assertions.assertEquals(expectedCustomers,allCustomer);
    }

    @Test
    void findById() {
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void updateCustomerById() {
    }

    @Test
    void deleteCustomerById() {
    }
}