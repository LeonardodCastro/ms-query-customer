package com.leonardocastro.ms.querycustomer.exceptions;


import com.leonardocastro.ms.querycustomer.controllers.response.ErrorResponse;
import com.leonardocastro.ms.querycustomer.controllers.response.FieldErrorResponse;
import com.leonardocastro.ms.querycustomer.enums.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.message,
                ex.errorCode,
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> argumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                Errors.QC201.getMessage(),
                Errors.QC201.getErrorCode(),
                ex.getBindingResult().getFieldErrors().stream()
    .map(fieldError -> new FieldErrorResponse(fieldError.getDefaultMessage(),(fieldError.getField())))
    .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}