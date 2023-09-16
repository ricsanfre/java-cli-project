package booking;

import car.Car;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class CarBookingDAO {

    private List<CarBooking> db;
    private final int DBCAPACITY = 10;
    private int nextDbAvailableSlot= 0;
    public CarBookingDAO() {
        db = new ArrayList<>();
    }
    public boolean saveCarBooking(CarBooking carBooking) {
        System.out.println("Saving new booking");
        return db.add(carBooking);
    }
    public List<CarBooking> getAllCarBookings() {
        return db;
    }

    public CarBooking findBookingByCar(Car car) {
        CarBooking result = null;
        for (CarBooking carBooking: db) {
            if (carBooking.getCar().equals(car)) {
                result = carBooking;
            }
        }
        return result;
    }

    public List<CarBooking> getBookingsByUser(User user) {
        List<CarBooking> result = new ArrayList<>();
        for (CarBooking carBooking: db ) {
            if (carBooking.getUser().equals(user)) {
                result.add(carBooking);
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }
}
