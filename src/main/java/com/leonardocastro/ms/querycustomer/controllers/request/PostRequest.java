package com.leonardocastro.ms.querycustomer.controllers.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PostRequest(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotNull(message = "Age is mandatory")
        Integer age,
        @NotBlank(message = "Country code is mandatory")
        String countryCode,
        @NotBlank(message = "Zip code is mandatory")
        String zip
) {
}