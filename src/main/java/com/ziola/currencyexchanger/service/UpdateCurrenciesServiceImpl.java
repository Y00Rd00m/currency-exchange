package com.ziola.currencyexchanger.service;

import com.ziola.currencyexchanger.connector.NbpConnector;
import com.ziola.currencyexchanger.connector.NbpRates;
import com.ziola.currencyexchanger.exceptions.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateCurrenciesServiceImpl {

    private NbpConnector nbpConnector;
    private Map<String, NbpRates> allCurrenciesRates;

    @Autowired
    public UpdateCurrenciesServiceImpl(NbpConnector nbpConnector) {
        this.nbpConnector = nbpConnector;
        updateCurrencies();
    }

    NbpRates findCurrency(String currencyName) {
        NbpRates nbpRatesFound = allCurrenciesRates.get(currencyName);
        if (nbpRatesFound == null) {
            throw new CurrencyNotFoundException(currencyName);
        }
        return nbpRatesFound;
    }

    public Map<String, NbpRates> showAllCurrencies() {
        return allCurrenciesRates;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void updateCurrencies() {
        allCurrenciesRates = nbpConnector.findAllCurrencies();
    }
}
