package com.weatherpincode.info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PincodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePincodeNotFound(PincodeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(WeatherApiException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleWeatherApiError(WeatherApiException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex) {
        return "Internal server error: " + ex.getMessage();
    }
}