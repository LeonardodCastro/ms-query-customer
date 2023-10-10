package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.dtos.PostRequest;
import com.leonardocastro.ms.querycustomer.dtos.UpdateRequest;
import com.leonardocastro.ms.querycustomer.services.FindByIdCustomerService;
import com.leonardocastro.ms.querycustomer.services.FindCustomerService;
import com.leonardocastro.ms.querycustomer.services.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class QueryController {
    @Autowired
    RegisterCustomerService registerCustomerService;
    @Autowired
    FindCustomerService findCustomerService;
    @Autowired
    FindByIdCustomerService findByIdCustomerService;

    @GetMapping("/")
    public ResponseEntity<?> findAllCustomer() {
        return ResponseEntity.ok(findCustomerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdCustomer(@PathVariable @RequestBody Long id) {
        return ResponseEntity.ok(findByIdCustomerService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveCustomer(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(registerCustomerService.saveCustomer(postRequest));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody UpdateRequest updateRequest){
        return ResponseEntity.ok(registerCustomerService.updateCustomerById(id, updateRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return ResponseEntity.ok(registerCustomerService.deleteCustomerById(id));
    }
}
