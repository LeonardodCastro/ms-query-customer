package com.leonardocastro.ms.querycustomer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private String name;
    private Integer age;
    private String countryCode;
    private String zip;
}
