package com.leonardocastro.ms.querycustomer.dtos;

public record UpdateRequest(
         String name,
         Integer age,
         String countryCode,
         String zip
){}
