package com.leonardocastro.ms.querycustomer.controllers;

import com.leonardocastro.ms.querycustomer.dtos.RequestDTO;
import com.leonardocastro.ms.querycustomer.dtos.ZipMinDTO;
import com.leonardocastro.ms.querycustomer.services.QueryZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class QueryController {
    @Autowired
    QueryZipService queryZipService;

    @PostMapping("/query")
    public ZipMinDTO method(@RequestBody RequestDTO requestDTO) {
        ZipMinDTO result = queryZipService.queryZip(requestDTO);
        return result;
    }
}
