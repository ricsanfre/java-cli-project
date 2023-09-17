package com.ricsanfre.cli.car;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private String regNumber;
    private CarBrand model;
    private CarEngineType carType;
    private BigDecimal rentalPricePerDay;

    public Car(String regNumber, CarBrand model, CarEngineType carType, BigDecimal rentalPricePerDay) {
        this.regNumber = regNumber;
        this.model = model;
        this.carType = carType;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public CarBrand getModel() {
        return model;
    }

    public void setModel(CarBrand model) {
        this.model = model;
    }

    public CarEngineType getCarType() {
        return carType;
    }

    public void setCarType(CarEngineType carType) {
        this.carType = carType;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(regNumber, car.regNumber) && model == car.model && carType == car.carType && Objects.equals(rentalPricePerDay, car.rentalPricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, model, carType, rentalPricePerDay);
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", model=" + model +
                ", carType=" + carType +
                ", rentalPricePerDay=" + rentalPricePerDay +
                '}';
    }
}
