package com.ziola.currencyexchanger.connector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NbpRates {

    private String currency;
    private String code;
    private BigDecimal mid;
}
