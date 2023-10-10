package com.leonardocastro.ms.querycustomer.dtos;

public record PostRequest(
        String name,
        Integer age,
        String countryCode,
        String zip
) {
}