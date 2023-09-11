package car;

import user.User;
import user.UserNotFoundException;

import java.util.UUID;

public class CarService {

    private CarDAO carDAO;

    public CarService() {
        carDAO = new CarDAO();
    }

    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarByRegNumber(String regNumber) throws CarNotFoundException {
        Car car = carDAO.getCarByRegNumber(regNumber);
        if (car!=null) {
            return car;
        } else {
            //throw new CarNotFoundException("Car: " + regNumber + " not found");
            throw new CarNotFoundException("Car not found");
        }
    }
}
