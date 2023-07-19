package com.leonardocastro.ms.querycustomer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipDTO {
    private String country_code;
    private String postal_code;
    private String state;
    private String place_name;
    private String lat;
    private String lng;
}

