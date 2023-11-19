package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @DisplayName("findAllCustomer positive case")
    public void findAllCustomer() {
        List<CustomerEntity> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new CustomerEntity());
        expectedCustomers.add(new CustomerEntity());
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<CustomerEntity> actualCustomers = customerService.findAllCustomer();

        assertEquals(expectedCustomers, actualCustomers);
        assertEquals(2, actualCustomers.size());

        verify(customerRepository).findAll();
    }

    @Test
    @DisplayName("findAllCustomer empty list case")
    public void findAllCustomer_EmptyListCase(){
        List<CustomerEntity> emptyList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(emptyList);

        List<CustomerEntity> response = customerService.findAllCustomer();

        Assertions.assertEquals(response,emptyList);
        Assertions.assertEquals(0,response.size());

        verify(customerRepository).findAll();
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