package com.leonardocastro.ms.querycustomer.controllers.response;

import java.util.List;

public record ErrorResponse(
        Integer httpStatus,
        String message,
        String internalCode,
        List<FieldErrorResponse> errors

) {
}