package com.leonardocastro.ms.querycustomer;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QueryMapper {

    QueryMapper INSTANCE = Mappers.getMapper(QueryMapper.class);

    @Mapping(source = "countryCode", target = "country_code")
    CustomerEntity toCustomer(PostRequest postRequest);

    CustomerResponse toCustomerResponse(CustomerEntity customer);

    CustomerEntity jsonToCustomer(String json);

    @Mapping(source = "customer.name", target = "name")
    @Mapping(source = "customer.state", target = "state")
    @Mapping(source = "customer.id", target = "id")
    @Mapping(source = "customer.age", target = "age")
    @Mapping(source = "customer.country_code", target = "country_code")
    @Mapping(source = "apiResponse.zip", target = "zip")
    @Mapping(source = "apiResponse.place_name", target = "place_name")
    CustomerEntity toResponse(CustomerEntity apiResponse, CustomerEntity customer);

}
