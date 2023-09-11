package car;

import user.User;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDAO {
    private static final Car[] cars;

    static {
        cars = new Car[]{
                    new Car("0001", CarBrand.MERCEDES, CarEngineType.DIESEL, new BigDecimal("70.0")),
                    new Car("0002", CarBrand.TOYOTA, CarEngineType.ELECTRIC, new BigDecimal("60.0")),
                    new Car("0003", CarBrand.RENAULT, CarEngineType.DIESEL, new BigDecimal("50.0"))
        } ;
    }

    public Car[] getAllCars() {
        return cars;
    }

    public Car getCarByRegNumber(String regNumber) {
        Car findCar = null;
        for (Car car: cars) {
            if (car.getRegNumber().equals(regNumber)) {
                findCar = car;
            }
        }
        return findCar;
    }
}
