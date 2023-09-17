package booking;

import car.Car;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class CarBookingDAO {

    final private List<CarBooking> db = new ArrayList<>();
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
