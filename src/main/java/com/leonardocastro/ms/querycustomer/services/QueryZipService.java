package com.leonardocastro.ms.querycustomer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardocastro.ms.querycustomer.dtos.RequestDTO;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class QueryZipService {
    @SneakyThrows
    public CustomerEntity queryZip(RequestDTO requestDTO) {
        String uri = "https://zip-api.eu/api/v1/info/" + requestDTO.getCountryCode() + "-" + requestDTO.getZip();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerEntity object = new ObjectMapper().readValue(response.body(), CustomerEntity.class);
        customerEntity.setCountry_code(requestDTO.getCountryCode());
        customerEntity.setZip(requestDTO.getZip());
        customerEntity.setName(requestDTO.getName());
        customerEntity.setAge(requestDTO.getAge());
        customerEntity.setState(object.getState());
        customerEntity.setPlace_name(object.getPlace_name());
        return customerEntity;
    }
}
