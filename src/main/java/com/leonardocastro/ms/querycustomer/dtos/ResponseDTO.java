package com.leonardocastro.ms.querycustomer.dtos;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String name;
    private Integer age;
    private String zip;
    public ResponseDTO(CustomerEntity customerEntity){
        this.name = customerEntity.getName();
        this.age = customerEntity.getAge();
        this.zip = customerEntity.getZip();
    }
}
