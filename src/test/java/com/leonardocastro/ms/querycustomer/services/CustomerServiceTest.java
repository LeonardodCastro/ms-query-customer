package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
    List<CustomerEntity> expectedCustomers = new ArrayList<>();



    @BeforeEach
    void setUp() {
        customerEntity = CustomerEntity.builder()
                .id(3L)
                .name("Leo")
                .age(23)
                .country_code("BR")
                .zip("01000-000")
                .state("Sao Paulo")
                .place_name("Sao Paulo")
                .build();
        expectedCustomers.add(CustomerEntity.builder()
                .id(1L)
                .name("Mary")
                .age(32)
                .country_code("US")
                .zip("33130")
                .state("Flórida")
                .place_name("Miami")
                .build());
        expectedCustomers.add(CustomerEntity.builder()
                .id(2L)
                .name("José")
                .age(27)
                .country_code("AT")
                .zip("1010")
                .state("Wien")
                .place_name("Wien, Innere Stadt")
                .build());
    }

    @Test
    @DisplayName("findAllCustomer() should return a list when successful")
    @Order(1)
    public void findAllCustomer_returnListWhenSuccessful() {
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<CustomerEntity> actualCustomers = customerService.findAllCustomer();

        assertEquals(expectedCustomers, actualCustomers);
        assertEquals(2, actualCustomers.size());
        assertDoesNotThrow(() -> actualCustomers);

        verify(customerRepository).findAll();
    }

    @Test
    @DisplayName("findAllCustomer() should return an empty list")
    @Order(2)
    public void findAllCustomer_returnEmptyList() {
        List<CustomerEntity> emptyList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(emptyList);

        List<CustomerEntity> response = customerService.findAllCustomer();

        Assertions.assertEquals(response, emptyList);
        Assertions.assertEquals(0, response.size());

        verify(customerRepository).findAll();
    }

    @Test
    @DisplayName("findById() should return customer successful")
    @Order(3)
    void findById_returnCustomerSuccessful() throws NotFoundException {
        when(customerRepository.findById(expectedCustomers.get(0).getId())).thenReturn(Optional.ofNullable(expectedCustomers.get(0)));

        CustomerResponse customerFound = customerService.findById(expectedCustomers.get(0).getId());

        assertNotNull(customerFound);
        org.assertj.core.api.Assertions.assertThat(customerFound).hasNoNullFieldsOrProperties();
        assertEquals(1L, expectedCustomers.get(0).getId());
        assertDoesNotThrow(() -> customerFound);
    }

    @Test
    @DisplayName("Should throw an exception when Id doesn't exist")
    @Order(4)
    void findById_throwNotFoundExceptionWhenUnsuccess() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class, () -> customerService.findById(999L));
    }


    @Test
    void updateCustomerById() {
    }

    @Test
    void deleteCustomerById() {
    }
}