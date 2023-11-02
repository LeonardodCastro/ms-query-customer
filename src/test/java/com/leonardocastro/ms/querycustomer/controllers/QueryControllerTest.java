package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

class QueryControllerTest {
    @InjectMocks
    QueryController queryController;
    @Mock
    CustomerService customerService;
    private Long id;
    private CustomerEntity customer;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = 1L;
        customer = new CustomerEntity();
    }

    @Test
    @DisplayName("Returning a list of customers")
    void findAllCustomersTest_01() {
        List<CustomerEntity> expectedCustomers = customerService.findAllCustomer();
        ResponseEntity<?> response = queryController.findAllCustomers();
        Assertions.assertEquals(expectedCustomers, response.getBody());
    }
    @Test
    void saveCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}