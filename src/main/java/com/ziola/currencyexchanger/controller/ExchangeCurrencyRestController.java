package com.ziola.currencyexchanger.controller;

import com.ziola.currencyexchanger.connector.NbpRates;
import com.ziola.currencyexchanger.dto.RequestCurrency;
import com.ziola.currencyexchanger.dto.ResponseCurrency;
import com.ziola.currencyexchanger.service.ExchangeServiceImpl;
import com.ziola.currencyexchanger.service.UpdateCurrenciesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class ExchangeCurrencyRestController {

    private final ExchangeServiceImpl exchangedService;
    private final UpdateCurrenciesServiceImpl updateCurrenciesService;

    @GetMapping(value = "/exchange", produces = "application/json")
    public ResponseCurrency exchangeCurrency(@Valid @RequestBody RequestCurrency requestCurrency) {

        String fromCurrency = requestCurrency.getFromCurrency();
        String toCurrency = requestCurrency.getToCurrency();
        BigDecimal exchangedValue = exchangedService.exchange(fromCurrency, toCurrency, requestCurrency.getValue());

        ResponseCurrency exchangedCurrency = new ResponseCurrency();

        exchangedCurrency.setPreValue(requestCurrency.getValue());
        exchangedCurrency.setFromCurrency(fromCurrency);
        exchangedCurrency.setToCurrency(toCurrency);
        exchangedCurrency.setActualValue(exchangedValue.toString());
        return exchangedCurrency;
    }

    @GetMapping(value = "/currencies")
    public Map<String, NbpRates> allCurrencies() {
        return updateCurrenciesService.showAllCurrencies();
    }
}
