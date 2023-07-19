package com.leonardocastro.ms.querycustomer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipMinDTO {
    private String country_code;
    private String postal_code;
    private String state;
    private String place_name;

    public ZipMinDTO(ZipDTO zipDTO) {
        this.setCountry_code(zipDTO.getCountry_code());
        this.setPostal_code(zipDTO.getPostal_code());
        this.setState(zipDTO.getState());
        this.setPlace_name(zipDTO.getPlace_name());
    }
}
