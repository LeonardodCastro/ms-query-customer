package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.controllers.request.UpdateRequest;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    QueryZipService queryZipService;
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerEntity> findAllCustomer() {
        return customerRepository.findAll();
    }

 public CustomerEntity findById(Long id) throws NotFoundException {
    return customerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Customer [%d] does not exist", id), "0001"));
}

    public CustomerResponse saveCustomer(PostRequest postRequest) {
        CustomerEntity entity = queryZipService.queryZip(postRequest);
        customerRepository.save(entity);
        return new CustomerResponse(entity.getName(), entity.getAge(), entity.getZip());
    }

    public UpdateRequest updateCustomerById(Long id, UpdateRequest updateRequest) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (customerEntity.isPresent()) {
            CustomerEntity customerUpdated;
            customerUpdated = customerEntity.get();
            customerUpdated.setName(updateRequest.name());
            customerUpdated.setAge(updateRequest.age());
            customerUpdated.setCountry_code(updateRequest.countryCode());
            customerUpdated.setZip(updateRequest.zip());
            customerRepository.save(customerUpdated);
        }
        return updateRequest;
    }

    public HttpStatus deleteCustomerById(Long id) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            CustomerEntity customerEntity = optionalCustomer.get();
            customerRepository.delete(customerEntity);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.CONFLICT;
    }
}