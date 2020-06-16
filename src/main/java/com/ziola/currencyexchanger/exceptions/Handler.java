package com.ziola.currencyexchanger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> invalidInputs(MethodArgumentNotValidException e) {
        Error error = new Error("Fields cannot be empty");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Error> currencyNotFound(CurrencyNotFoundException e) {
        Error error = new Error("Currency: " + e.getCurrency() + ", has not been found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Error> valueNotNumber(NumberFormatException e) {
        Error error = new Error("Given value is not a number");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
