package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.QueryMapper;
import com.leonardocastro.ms.querycustomer.controllers.request.UpdateRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.controllers.response.UpdateResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.enums.Errors;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final QueryZipService queryZipService;
    private final CustomerRepository customerRepository;

    private static final QueryMapper MAPPER = QueryMapper.INSTANCE;

    public List<CustomerEntity> findAllCustomer() {
        return customerRepository.findAll();
    }

    public CustomerEntity findById(Long id) throws NotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException((String.format(Errors.QC101.getMessage(), id)), Errors.QC101.getErrorCode()));
    }

    public CustomerResponse saveCustomer(CustomerEntity customer) {
        CustomerEntity entity = queryZipService.queryZip(customer);
        customerRepository.save(entity);
        return MAPPER.toCustomerResponse(entity);
    }

    public UpdateResponse updateCustomerById(Long id, UpdateRequest updateRequest) throws NotFoundException {
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new NotFoundException((String.format(Errors.QC101.getMessage(), id)), Errors.QC101.getErrorCode())));
        if (customerEntity.isPresent()) {
            CustomerEntity updatedCustomerXPTO = MAPPER.toUpdate(customerEntity.get(), updateRequest);
            CustomerEntity customerUpdated;
            customerUpdated = customerEntity.get();
            customerUpdated.setName(updateRequest.name());
            customerUpdated.setAge(updateRequest.age());
            customerUpdated.setCountry_code(updateRequest.countryCode());
            customerUpdated.setZip(updateRequest.zip());
            customerRepository.save(customerUpdated);
        }
        return MAPPER.toUpdateResponse(updateRequest);
    }

    public HttpStatus deleteCustomerById(Long id) throws NotFoundException {
        Optional<CustomerEntity> optionalCustomer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(Errors.QC101.getMessage(), id), Errors.QC101.getErrorCode())));
        if (optionalCustomer.isPresent()) {
            CustomerEntity customerEntity = optionalCustomer.get();
            customerRepository.delete(customerEntity);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.CONFLICT;
    }
}