package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.dtos.RequestDTO;
import com.leonardocastro.ms.querycustomer.services.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class QueryController {
    @Autowired
    RegisterCustomerService registerCustomerService;

    @PostMapping("/register")
    public ResponseEntity<?> saveCustomer(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(registerCustomerService.saveCustomer(requestDTO));
    }
}
