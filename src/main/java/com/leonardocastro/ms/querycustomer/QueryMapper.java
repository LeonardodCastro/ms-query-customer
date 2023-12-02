package com.leonardocastro.ms.querycustomer;

import com.leonardocastro.ms.querycustomer.controllers.request.PostRequest;
import com.leonardocastro.ms.querycustomer.controllers.request.UpdateRequest;
import com.leonardocastro.ms.querycustomer.controllers.response.CustomerResponse;
import com.leonardocastro.ms.querycustomer.controllers.response.UpdateResponse;
import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springfox.documentation.spring.web.json.Json;

import java.net.http.HttpResponse;

@Mapper
public interface QueryMapper {

    QueryMapper INSTANCE = Mappers.getMapper(QueryMapper.class);

    @Mapping(source = "countryCode", target = "country_code")
    CustomerEntity toCustomer(PostRequest postRequest);

    CustomerResponse toCustomerResponse(CustomerEntity customer);

    @Mapping(source = "customer.id", target = "id")
    @Mapping(source = "customer.name", target = "name")
    @Mapping(source = "customer.age", target = "age")
    @Mapping(source = "customer.country_code", target = "country_code")
    @Mapping(source = "customer.zip", target = "zip")
    @Mapping(source = "apiResponse.state", target = "state")
    @Mapping(source = "apiResponse.place_name", target = "place_name")
    CustomerEntity toResponse(CustomerEntity apiResponse, CustomerEntity customer);


    //todo: faz o mesmo esquema do de cima
    @Mapping(source = "updateRequest.name",  target = "name")
    @Mapping(source = "updateRequest.age",  target = "age")
    @Mapping(source = "updateRequest.countryCode",  target = "country_code")
    @Mapping(source = "updateRequest.zip",  target = "zip")
    @Mapping(source = "customerEntity.id",  target = "id")
//    @Mapping(source = "customerEntity.state",  target = "state")
//    @Mapping(source = "customerEntity.place_name",  target = "place_name")
    CustomerEntity toUpdate(CustomerEntity customerEntity, UpdateRequest updateRequest);

    UpdateResponse toUpdateResponse(UpdateRequest request);

}
