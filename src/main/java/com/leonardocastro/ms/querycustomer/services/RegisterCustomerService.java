package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.dtos.RequestDTO;
import com.leonardocastro.ms.querycustomer.dtos.ResponseDTO;
    import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerService {
    @Autowired
    QueryZipService queryZipService;
    @Autowired
    CustomerRepository customerRepository;

    public ResponseDTO saveCustomer(RequestDTO requestDTO) {
        CustomerEntity entity = queryZipService.queryZip(requestDTO);
        customerRepository.save(entity);
        ResponseDTO responseDTO = new ResponseDTO(entity);
        return responseDTO;
    }
}
