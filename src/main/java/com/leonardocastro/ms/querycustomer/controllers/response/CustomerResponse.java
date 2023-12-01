package com.leonardocastro.ms.querycustomer.controllers.response;

public record CustomerResponse(
        String name,
        Integer age,
        String zip
) {
}
