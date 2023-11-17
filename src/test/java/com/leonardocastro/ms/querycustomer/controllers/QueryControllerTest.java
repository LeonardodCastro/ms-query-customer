package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.request.UpdateRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

class QueryControllerTest {
    @InjectMocks
    QueryController queryController;
    @Mock
    CustomerService customerService;
    private Long id;
    private PostRequest postRequest;
    private UpdateRequest updateRequest;
    private CustomerEntity customerEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = 1L;
        postRequest = new PostRequest("name", 20, "US", "33130");
        updateRequest = new UpdateRequest("name", 20, "US", "33130");
        customerEntity = new CustomerEntity();
    }

    @Test
    @DisplayName("Returning an empty list of customers")
    void findAllCustomersTest() {
        List<CustomerEntity> expectedCustomers = customerService.findAllCustomer();
        ResponseEntity<?> response = queryController.findAllCustomers();
        Assertions.assertEquals(expectedCustomers, response.getBody());
    }

    @Test
    @DisplayName("Returning a list of customers")
    void findAllCustomersTest_01() {
        List<CustomerEntity> expectedCustomers = new ArrayList<>();
        ResponseEntity<List<CustomerEntity>> allCustomers = queryController.findAllCustomers();
        Assertions.assertEquals(expectedCustomers, allCustomers.getBody());
    }

    @Test
    @DisplayName("Returning the same response")
    void findAllCustomersTest_02() {
        ResponseEntity<?> response = queryController.findAllCustomers();
        Assertions.assertEquals(ResponseEntity.ok(customerService.findAllCustomer()), response);
    }

    @Test
    @DisplayName("Not throwing an exception")
    void findAllCustomersTest_03() {
        ResponseEntity<?> allCustomers = queryController.findAllCustomers();
        assertDoesNotThrow(() -> allCustomers);
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
    @DisplayName("Throwing NotFoundException")
    void findByIdCustomer_03() throws NotFoundException {
        when(customerService.findById(null)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> queryController.findByIdCustomer(null));
    }

    @Test
    @DisplayName("Not throwing NotFoundException")
    void findByIdCustomer_04() throws NotFoundException {
        assertDoesNotThrow(() -> queryController.findByIdCustomer(id));
    }

    @Test
    @DisplayName("Returning the expected response")
    void saveCustomer_01() {
        ResponseEntity<?> response = queryController.saveCustomer(postRequest);
        Assertions.assertEquals(ResponseEntity.ok(customerService.saveCustomer(postRequest)), response);
    }

    @Test
    @DisplayName("Returning same body")
    void saveCustomer_02() {
        ResponseEntity<?> response = queryController.saveCustomer(postRequest);
        Assertions.assertEquals(queryController.saveCustomer(postRequest).getBody(), response.getBody());
    }

    @Test
    @DisplayName("Not throwing Exception")
    void saveCustomer_03() throws Exception {
        assertDoesNotThrow(() -> queryController.saveCustomer(postRequest));
    }

    @Test
    @DisplayName("Returning the expected response")
    void updateCustomer_01() throws NotFoundException {
        ResponseEntity<?> response = queryController.updateCustomer(id, updateRequest);
        Assertions.assertEquals(ResponseEntity.ok(customerService.updateCustomerById(id, updateRequest)), response);

    }

    @Test
    @DisplayName("Not throwing an exception")
    void updateCustomer_02() throws NotFoundException {
        Assertions.assertDoesNotThrow(() -> queryController.updateCustomer(id, updateRequest));
    }

    @Test
    @DisplayName("Returning the expected response")
    void deleteCustomer_01() throws NotFoundException {
        ResponseEntity<?> response = queryController.deleteCustomer(id);
        Assertions.assertEquals(queryController.deleteCustomer(id), response);
    }

    @Test
    @DisplayName("Not throwing an exception")
    void deleteCustomer_02() throws NotFoundException {
        Assertions.assertDoesNotThrow(() -> queryController.deleteCustomer(id));
    }
}