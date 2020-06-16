package com.ziola.currencyexchanger.connector;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NbpConnector {

    public Map<String, NbpRates> findAllCurrencies()  {

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> requestEntityA = RequestEntity.get(URI.create("http://api.nbp.pl/api/exchangerates/tables/A"))
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RequestEntity<Void> requestEntityB = RequestEntity.get(URI.create("http://api.nbp.pl/api/exchangerates/tables/B"))
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<List<NbpResponse>> responseEntityA = restTemplate.exchange(requestEntityA, new ParameterizedTypeReference<>() {
        });
        ResponseEntity<List<NbpResponse>> responseEntityB = restTemplate.exchange(requestEntityB, new ParameterizedTypeReference<>() {
        });

        List<NbpRates> ratesA = responseEntityA.getBody().stream().findFirst().get().getRates();
        List<NbpRates> ratesB = responseEntityB.getBody().stream().findFirst().get().getRates();

        return Stream.concat(ratesA.stream(), ratesB.stream())
                .collect(Collectors.toMap(NbpRates::getCode, Function.identity()));
    }
}
