package com.ricsanfre.cli.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static final List<Car> cars;

    static {
        cars = new ArrayList<>();
        cars.add(new Car("0001", CarBrand.MERCEDES, CarEngineType.DIESEL, new BigDecimal("70.0")));
        cars.add(new Car("0002", CarBrand.TOYOTA, CarEngineType.ELECTRIC, new BigDecimal("60.0")));
        cars.add(new Car("0003", CarBrand.RENAULT, CarEngineType.DIESEL, new BigDecimal("50.0")));
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Car getCarByRegNumber(String regNumber) {
        Car findCar = null;
        for (Car car: cars ) {
            if (car.getRegNumber().equals(regNumber)) {
                findCar = car;
            }
        }
        return findCar;
    }
}
