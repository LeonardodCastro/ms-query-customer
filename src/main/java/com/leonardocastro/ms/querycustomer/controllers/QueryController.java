package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.QueryMapper;
import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.request.UpdateRequest;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import com.leonardocastro.ms.querycustomer.exceptions.NotFoundException;
import com.leonardocastro.ms.querycustomer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class QueryController {

    private final CustomerService customerService;
    QueryMapper mapper = QueryMapper.INSTANCE;

    @GetMapping("/")
    public ResponseEntity<List<CustomerEntity>> findAllCustomers() {
        List<CustomerEntity> allCustomer = customerService.findAllCustomer();
        return ResponseEntity.ok(allCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdCustomer(@PathVariable @RequestBody Long id) throws NotFoundException {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveCustomer(@RequestBody @Validated PostRequest postRequest) {
        CustomerEntity customer = mapper.toCustomer(postRequest);
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody UpdateRequest updateRequest) throws NotFoundException {
        return ResponseEntity.ok(customerService.updateCustomerById(id, updateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }
}