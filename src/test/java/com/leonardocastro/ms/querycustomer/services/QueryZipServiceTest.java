package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@TestPropertySource(locations="classpath:application-test.properties")
@ContextConfiguration(classes = {QueryZipService.class})
@ExtendWith(SpringExtension.class)
class QueryZipServiceTest {
    @Autowired
    private QueryZipService queryZipService;

    @Test
    void testQueryZip() {
      CustomerEntity customer = CustomerEntity.builder()
              .name("Jorge")
              .age(40)
              .country_code("US")
              .zip("33130")
              .build();

        CustomerEntity actualQueryZipResult = this.queryZipService.queryZip(customer);

        Assertions.assertEquals(customer.getZip(), actualQueryZipResult.getZip());
        Assertions.assertEquals(customer.getCountry_code(), actualQueryZipResult.getCountry_code());
        Assertions.assertEquals(customer.getName(), actualQueryZipResult.getName());
        Assertions.assertEquals(customer.getAge(), actualQueryZipResult.getAge());
    }
}
