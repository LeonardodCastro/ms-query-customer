package com.leonardocastro.ms.querycustomer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardocastro.ms.querycustomer.dtos.RequestDTO;
import com.leonardocastro.ms.querycustomer.dtos.ZipDTO;
import com.leonardocastro.ms.querycustomer.dtos.ZipMinDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class QueryZipService {
    @SneakyThrows
    public ZipMinDTO queryZip(RequestDTO requestDTO) {
        ZipDTO zipDTO = new ZipDTO();

        String uri = "https://zip-api.eu/api/v1/info/" + requestDTO.getCountryCode() + "-" + requestDTO.getZip();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                .uri(URI.create(uri))
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ZipDTO object = new ObjectMapper().readValue(response.body(), ZipDTO.class);

        zipDTO.setCountry_code(object.getCountry_code());
        zipDTO.setPostal_code(object.getPostal_code());
        zipDTO.setState(object.getState());
        zipDTO.setPlace_name(object.getPlace_name());

        ZipMinDTO zipMinDTO = new ZipMinDTO(zipDTO);

        return zipMinDTO;
    }

}
