package com.ricsanfre.cli.booking;

import com.ricsanfre.cli.car.Car;
import com.ricsanfre.cli.car.CarEngineType;
import com.ricsanfre.cli.car.CarNotFoundException;
import com.ricsanfre.cli.car.CarService;
import com.ricsanfre.cli.user.User;

import java.time.LocalDateTime;
import java.util.*;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService) {
        this.carService = carService;
        this.carBookingDAO = carBookingDAO;
    }

    public List<CarBooking> getAllBookings() {
        return carBookingDAO.getAllCarBookings();
    }

    public List<CarBooking> getBookingsByUser(User user) {
        return carBookingDAO.getBookingsByUser(user);
    }

    public List<Car> getAllAvailableCars() {
        List<Car> result = new ArrayList<>();
        List<Car> allCars = carService.getAllCars();

        for (Car car: allCars) {
            if (!isCarBooked(car)){
                // Car is not already Booked
                // add it to the list
                result.add(car);
            }
        }
        if (!result.isEmpty()){
            return result;
        } else {
            return null;
        }
    }

    public List<Car> getAvailableCarsPerType(CarEngineType carEngineType) {
        List<Car> result = new ArrayList<>();
        List<Car> allCars = carService.getAllCars();
        for (Car car: allCars) {
            if (!isCarBooked(car) && car.getCarType().equals(carEngineType)){
                // Car is not already Booked and is of specific type
                // add it to the list
                result.add(car);
            }
        }
        if (!result.isEmpty()){
            return result;
        } else {
            return null;
        }
    }

    public void bookCar(String regNumber, User user) throws CarNotFoundException {

        // Check if regNumber is valid
        Car car = carService.getCarByRegNumber(regNumber);
        if (!isCarBooked(car)) {
            System.out.println("Creating new booking. regNum:" + regNumber + " userId: " + user.getUserId().toString());
            CarBooking booking = new CarBooking(UUID.randomUUID(), user, car, LocalDateTime.now());
            carBookingDAO.saveCarBooking(booking);
        }
    }

    public boolean isCarBooked(Car car) {
        boolean result = false;
        if (carBookingDAO.findBookingByCar(car) != null) {
            // Car already Booked
            result = true;
        }
        return result;
    }
}
