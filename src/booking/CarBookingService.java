package booking;

import car.Car;
import car.CarEngineType;
import car.CarNotFoundException;
import car.CarService;
import user.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService) {
        this.carService = carService;
        this.carBookingDAO = carBookingDAO;
    }

    public CarBooking[] getAllBookings() {
        return carBookingDAO.getAllCarBookings();
    }

    public CarBooking[] getBookingsByUser(User user) {
        return carBookingDAO.getBookingsByUser(user);
    }

    public Car[] getAllAvailableCars() {
        Car[] result = new Car[100];
        int counter=0;
        Car[] allCars = carService.getAllCars();
        for (Car car: allCars) {
            if (!isCarBooked(car)){
                // Car is not already Booked
                // add it to the list
                result[counter]=car;
                counter++;
            }
        }
        if (counter>0){
            return Arrays.copyOf(result, counter);
        } else {
            return null;
        }
    }

    public Car[] getAvailableCarsPerType(CarEngineType carEngineType) {
        Car[] result = new Car[100];
        int counter=0;
        Car[] allCars = carService.getAllCars();
        for (Car car: allCars) {
            if (!isCarBooked(car) && car.getCarType().equals(carEngineType)){
                // Car is not already Booked and is of specific type
                // add it to the list
                result[counter]=car;
                counter++;
            }
        }
        if (counter>0){
            return Arrays.copyOf(result, counter);
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
