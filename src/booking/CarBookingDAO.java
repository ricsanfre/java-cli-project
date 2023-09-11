package booking;

import car.Car;
import user.User;

import java.util.Arrays;

public class CarBookingDAO {

    private CarBooking[] db;
    private final int DBCAPACITY = 10;
    private int nextDbAvailableSlot= 0;
    public CarBookingDAO() {
        db = new CarBooking[DBCAPACITY];
    }
    public boolean saveCarBooking(CarBooking carBooking) {
        if (nextDbAvailableSlot >= (DBCAPACITY - 1)) {
            // Not enough space in array db
            System.out.println("Cannot Add new CarBooking. Not enough capacity");
            return false;
        }
        System.out.println("Saving new booking");
        db[nextDbAvailableSlot] = carBooking;
        nextDbAvailableSlot++;
        return true;
    }
    public CarBooking[] getAllCarBookings() {
        CarBooking[] result = null;
        if (nextDbAvailableSlot > 0) {
            result = Arrays.copyOf(db, nextDbAvailableSlot);
        }
        return result;
    }

    public CarBooking findBookingByCar(Car car) {
        CarBooking result = null;
        for (int i = 0; i < nextDbAvailableSlot; i++) {
            if (db[i].getCar().equals(car)) {
                result = db[i];
            }
        }
        return result;
    }

    public CarBooking[] getBookingsByUser(User user) {
        CarBooking[] result = new CarBooking[DBCAPACITY];
        int counter = 0;
        for (int i = 0; i < nextDbAvailableSlot; i++) {
            if (db[i].getUser().equals(user)) {
                result[counter] = db[i];
                counter++;
            }
        }
        if (counter> 0) {
            return Arrays.copyOf(result, counter);
        } else {
            return null;
        }
    }
}
