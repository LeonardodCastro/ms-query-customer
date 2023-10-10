package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.dtos.PostRequest;
import com.leonardocastro.ms.querycustomer.dtos.ResponseDTO;
import com.leonardocastro.ms.querycustomer.dtos.UpdateRequest;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RegisterCustomerService {
    @Autowired
    QueryZipService queryZipService;
    @Autowired
    CustomerRepository customerRepository;

    public ResponseDTO saveCustomer(PostRequest postRequest) {
        CustomerEntity entity = queryZipService.queryZip(postRequest);
        customerRepository.save(entity);
        ResponseDTO responseDTO = new ResponseDTO(entity);
        return responseDTO;
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
        if (optionalCustomer.isPresent()){
            CustomerEntity customerEntity = optionalCustomer.get();
            customerRepository.delete(customerEntity);
            return HttpStatus.NO_CONTENT;
        }
        return  HttpStatus.CONFLICT;
    }
}