package com.leonardocastro.ms.querycustomer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_entity")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String country_code;
    private String zip;
    private String state;
    private String place_name;
}
