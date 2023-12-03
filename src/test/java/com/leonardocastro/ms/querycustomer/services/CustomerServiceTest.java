package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @DisplayName("findAllCustomer() should return a list when successful")
    @Order(1)
    public void findAllCustomer_returnListWhenSuccessful() {
        List<CustomerEntity> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new CustomerEntity());
        expectedCustomers.add(new CustomerEntity());
        when(customerService.findAllCustomer()).thenReturn(expectedCustomers);

        List<CustomerEntity> actualCustomers = customerService.findAllCustomer();

        assertEquals(expectedCustomers, actualCustomers);
        assertEquals(2, actualCustomers.size());
        assertDoesNotThrow(() -> actualCustomers);
    }

    @Test
    @DisplayName("findAllCustomer empty list case")
    public void findAllCustomer_EmptyListCase() {
        List<CustomerEntity> emptyList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(emptyList);

        List<CustomerEntity> response = customerService.findAllCustomer();

        Assertions.assertEquals(response, emptyList);
        Assertions.assertEquals(0, response.size());

        verify(customerRepository).findAll();
    }

    @Test
    @DisplayName("Should return an Id")
    void findById() throws NotFoundException {
        customerEntity.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));

        CustomerResponse service = customerService.findById(1L);

        assertNotNull(service);
        assertEquals(1L, customerEntity.getId());
        assertDoesNotThrow(() -> service);
    }

    @Test
    @DisplayName("Should throw an exception when Id doesn't exist")
    void findById_NegativeCase() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class, () -> customerService.findById(999L));
    }

    @Test
    @DisplayName("should save a customer")
    public void saveCustomer() {
        PostRequest postRequest = new PostRequest("Jhon", 25, "US", "33130");
        when(queryZipService.queryZip(new CustomerEntity())).thenReturn(customerEntity);

        CustomerResponse response = customerService.saveCustomer(new CustomerEntity());

        assertDoesNotThrow(() -> response);
        assertNotNull(response);
    }

    @Test
    public void saveCustomer_NegativeCase() {
        PostRequest postRequest = new PostRequest("", 0, "INVALID", "NONE");
        when(queryZipService.queryZip(new CustomerEntity())).thenReturn(new CustomerEntity());
        CustomerResponse response = customerService.saveCustomer(new CustomerEntity());

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