package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    QueryZipService queryZipService;
    CustomerEntity customerEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerEntity = new CustomerEntity();
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
        assertDoesNotThrow(()-> actualCustomers);

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
    @DisplayName("Should return an Id")
    void findById() throws NotFoundException {
        customerEntity.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));

        CustomerEntity service = customerService.findById(1L);

        assertNotNull(service);
        assertEquals(1L, service.getId());
        assertDoesNotThrow(()-> service);
    }
    @Test
    @DisplayName("Should throw an exception when Id doesn't exist")
    void findById_NegativeCase() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class, ()-> customerService.findById(999L));
    }

    @Test
    @DisplayName("should save a customer")
    public void saveCustomer() {
        PostRequest postRequest = new PostRequest("Jhon", 25, "US", "33130");
        when(queryZipService.queryZip(postRequest)).thenReturn(customerEntity);

        CustomerResponse response = customerService.saveCustomer(postRequest);

        assertDoesNotThrow(() -> response);
        assertNotNull(response);
    }
    @Test
    public void saveCustomer_NegativeCase(){
        PostRequest postRequest = new PostRequest("", 0, "INVALID", "NONE");
        when(queryZipService.queryZip(postRequest)).thenReturn(new CustomerEntity());
        CustomerResponse response = customerService.saveCustomer(postRequest);

        assertNull(response.name());
        assertNull(response.age());
        assertNull(response.zip());
    }

    @Test
    void updateCustomerById() {
    }

    @Test
    void deleteCustomerById() {
    }
}