package com.leonardocastro.ms.querycustomer.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotFoundException extends Exception {
    String message;
    String errorCode;
}
