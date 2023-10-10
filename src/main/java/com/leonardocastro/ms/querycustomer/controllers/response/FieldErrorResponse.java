package com.leonardocastro.ms.querycustomer.controllers.response;

public record FieldErrorResponse(
        String message,
        String field
) {
}