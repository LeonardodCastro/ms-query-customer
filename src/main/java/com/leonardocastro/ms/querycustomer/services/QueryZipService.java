package com.leonardocastro.ms.querycustomer.services;

import com.leonardocastro.ms.querycustomer.QueryMapper;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class QueryZipService {
    private static final QueryMapper MAPPER = QueryMapper.INSTANCE;
    @Value("${api-endpoint}")
    private String endpoint;

    @SneakyThrows
    public CustomerEntity queryZip(CustomerEntity customer) {
        String uri = endpoint + customer.getCountry_code() + "-" + customer.getZip();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        CustomerEntity body = MAPPER.jsonToCustomer(response.body());
        return MAPPER.toResponse(body, customer);
    }
}