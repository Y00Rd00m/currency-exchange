package com.ziola.currencyexchanger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCurrency {

    @NotBlank(message = "Field cannot be empty")
    String fromCurrency;
    @NotBlank(message = "Field cannot be empty")
    String toCurrency;
    @NotBlank(message = "Field cannot be empty")
    String value;
}
