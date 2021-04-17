package com.fourgcapital.weather.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String fieldName) {
        super(fieldName + " not found in weather input file");
    }
}
