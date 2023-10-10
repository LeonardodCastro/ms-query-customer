package com.leonardocastro.ms.querycustomer.controllers.request;

public record PostRequest(
        String name,
        Integer age,
        String countryCode,
        String zip
) {
}