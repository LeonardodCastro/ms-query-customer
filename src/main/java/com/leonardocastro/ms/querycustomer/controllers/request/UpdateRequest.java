package com.leonardocastro.ms.querycustomer.controllers.request;

public record UpdateRequest(
        String name,
        Integer age,
        String countryCode,
        String zip
) {
}