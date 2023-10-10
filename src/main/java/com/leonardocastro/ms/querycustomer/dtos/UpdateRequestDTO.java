package com.leonardocastro.ms.querycustomer.dtos;

public record UpdateRequestDTO(
         String name,
         Integer age,
         String countryCode,
         String zip
){}
