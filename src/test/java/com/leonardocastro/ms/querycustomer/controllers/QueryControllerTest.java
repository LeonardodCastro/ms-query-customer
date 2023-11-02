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
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

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
    @DisplayName("Returning the same response")
    void findAllCustomersTest_02() {
        ResponseEntity<?> response = queryController.findAllCustomers();
        Assertions.assertEquals(ResponseEntity.ok(customerService.findAllCustomer()), response);
    }

    @Test
    @DisplayName("Returning not the same response")
    void findAllCustomersTest_03() {
        ResponseEntity<?> response = queryController.findAllCustomers();
        assertNotEquals(ResponseEntity.ok(), response);
    }

    @Test
    @DisplayName("Not throwing an exception")
    void findAllCustomersTest_04() {
        ResponseEntity<?> allCustomers = queryController.findAllCustomers();
        Assertions.assertDoesNotThrow(() -> allCustomers);
    }

    @Test
    @DisplayName("Returning a costumer")
    void findByIdCustomer_01() throws NotFoundException {
        CustomerEntity expectedCustomer = customerService.findById(id);
        ResponseEntity<?> response = queryController.findByIdCustomer(id);
        Assertions.assertEquals(expectedCustomer, response.getBody());
    }

    @Test
    @DisplayName("Returning the same response")
    void findByIdCustomer_02() throws NotFoundException {
        ResponseEntity<?> response = queryController.findByIdCustomer(id);
        Assertions.assertEquals(ResponseEntity.ok(customerService.findById(id)), response);
    }

    @Test
    @DisplayName("Returning not the same response")
    void findByIdCustomer_03() throws NotFoundException {
        ResponseEntity<?> response = queryController.findByIdCustomer(id);
        assertNotEquals(ResponseEntity.ok(), response);
    }

    @Test
    @DisplayName("Throwing NotFoundException")
    void findByIdCustomer_04() throws NotFoundException {
        when(customerService.findById(null)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> queryController.findByIdCustomer(null));
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