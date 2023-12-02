package com.leonardocastro.ms.querycustomer.controllers.response;

public record UpdateResponse(
        String name,
        Integer age,
        String countryCode,
        String zip
) {
}