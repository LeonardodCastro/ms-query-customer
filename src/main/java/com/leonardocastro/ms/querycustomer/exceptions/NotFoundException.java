package com.leonardocastro.ms.querycustomer.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    String message;
    String errorCode;
}
