package com.ziola.currencyexchanger.dto;

import lombok.Data;

@Data
public class ResponseCurrency {

    private String fromCurrency;
    private String toCurrency;
    private String preValue;
    private String actualValue;
}
