package com.ricsanfre.cli.car;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
