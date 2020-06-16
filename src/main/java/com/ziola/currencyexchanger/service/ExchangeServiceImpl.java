package com.ziola.currencyexchanger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl {

    private final UpdateCurrenciesServiceImpl updateCurrenciesService;

    public BigDecimal exchange(String fromCurrency, String toCurrency, String value) {
        BigDecimal valueBD = new BigDecimal(value);
        BigDecimal targetCurrencyMid = updateCurrenciesService.findCurrency(toCurrency).getMid();
        BigDecimal fromCurrencyMid = updateCurrenciesService.findCurrency(fromCurrency).getMid();
        return valueBD.divide(fromCurrencyMid, RoundingMode.HALF_UP).multiply(targetCurrencyMid).setScale(2, RoundingMode.HALF_UP);
    }
}
