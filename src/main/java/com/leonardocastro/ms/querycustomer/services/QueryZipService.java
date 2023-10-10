package com.leonardocastro.ms.querycustomer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardocastro.ms.querycustomer.dtos.PostRequest;
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
    public CustomerEntity queryZip(PostRequest postRequest) {
        String uri = "https://zip-api.eu/api/v1/info/" + postRequest.countryCode() + "-" + postRequest.zip();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerEntity object = new ObjectMapper().readValue(response.body(), CustomerEntity.class);
        customerEntity.setCountry_code(postRequest.countryCode());
        customerEntity.setZip(postRequest.zip());
        customerEntity.setName(postRequest.name());
        customerEntity.setAge(postRequest.age());
        customerEntity.setState(object.getState());
        customerEntity.setPlace_name(object.getPlace_name());
        return customerEntity;
    }
}
