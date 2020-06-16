package com.ziola.currencyexchanger.exceptions;

import lombok.Getter;

@Getter
public class CurrencyNotFoundException extends RuntimeException {

    private String currency;

    public CurrencyNotFoundException(String currency) {
        super(currency);
        this.currency = currency;
    }
}
