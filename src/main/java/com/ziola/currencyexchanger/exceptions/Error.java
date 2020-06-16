package com.ziola.currencyexchanger.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Error {

    @JsonFormat(pattern = "yyyy-MM-dd - HH:mm:ss")
    private LocalDateTime time;
    private String message;

    public Error(String message) {
        this.message = message;
        updateDate();
    }

    private void updateDate() {
        time = LocalDateTime.now();
    }
}
