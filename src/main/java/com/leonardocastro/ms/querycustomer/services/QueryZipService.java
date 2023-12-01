package com.leonardocastro.ms.querycustomer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardocastro.ms.querycustomer.QueryMapper;
import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class QueryZipService {
    QueryMapper mapper = QueryMapper.INSTANCE;
    @Value("${api-endpoint}")
    private String endpoint;

    @SneakyThrows
    public CustomerEntity queryZip(CustomerEntity customer) {
        String uri = endpoint + customer.getCountry_code() + "-" + customer.getZip();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        CustomerEntity body = mapper.jsonToCustomer(response.body());
        return mapper.toResponse(body, customer);
    }
}