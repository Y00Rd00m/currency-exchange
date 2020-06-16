package com.ziola.currencyexchanger.connector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NbpResponse {

    private String table;
    private String no;
    private LocalDate effectiveDate;
    private List<NbpRates> rates;
}
