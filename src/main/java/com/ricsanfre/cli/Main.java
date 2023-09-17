package com.ricsanfre.cli;

import com.ricsanfre.cli.booking.CarBooking;
import com.ricsanfre.cli.booking.CarBookingDAO;
import com.ricsanfre.cli.booking.CarBookingService;
import com.ricsanfre.cli.car.*;
import com.ricsanfre.cli.user.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Creating Objects and dependency injection
        CarBookingDAO carBookingDAO = new CarBookingDAO();
        // UserDAO userDAO = new UserCsvDataAccessService();
        UserDAO userDAO = new UserFakerDataAccessService();
        CarDAO carDAO = new CarDAO();
        UserService userService = new UserService(userDAO);
        CarService carService = new CarService(carDAO);
        CarBookingService carBookingService = new CarBookingService(carBookingDAO, carService);

        Scanner cliScanner = new Scanner(System.in);
        // com.ricsanfre.cli.Main loop cli options
        boolean keepLooping = true;
        while (keepLooping) {
            try {
                printMenuOptions();
                String input = cliScanner.nextLine();
                switch (Integer.parseInt(input)) {
                    case 2 -> displayBookingsbyUser(carBookingService, userService, cliScanner);
                    case 1 -> bookCar(carBookingService, userService, cliScanner);
                    case 3 -> displayAllBookings(carBookingService);
                    case 4 -> displayAllAvailableCars(carBookingService);
                    case 5 -> displayElectricAvailableCars(carBookingService);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepLooping = false;
                    default -> System.out.println("❌ Invalid Option: " + input);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayBookingsbyUser(CarBookingService carBookingService,
                                              UserService userService,
                                              Scanner cliScanner) {

        displayAllUsers(userService);
        System.out.println("Select a user Id:");
        String userId = cliScanner.nextLine();

        try {
            User user = userService.getUser(userId);
            System.out.println("Listing Bookings for: " + userId);
            List<CarBooking> carBookings = carBookingService.getBookingsByUser(user);
            if (carBookings == null) {
                System.out.println("No Bookings found!");
            } else {
                for (CarBooking carBooking: carBookings) {
                    System.out.println(carBooking);
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println("User Id not found!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllBookings(CarBookingService carBookingService) {
        System.out.println("Listing current Bookings...");
        List<CarBooking> carBookings = carBookingService.getAllBookings();
        if (carBookings == null) {
            System.out.println("No Bookings found!");
        } else {
            for (CarBooking carBooking: carBookings) {
                System.out.println(carBooking);
            }
        }
    }

    private static void bookCar(CarBookingService carBookingService, UserService userService, Scanner cliScanner) {
        System.out.println("Booking a car...");

        displayAllAvailableCars(carBookingService);
        System.out.println("Select a car to book (insert regNumber)");
        String regNumber= cliScanner.nextLine();

        displayAllUsers(userService);
        System.out.println("Select a user Id:");
        String userId = cliScanner.nextLine();

        try {
            User user = userService.getUser(userId);
            carBookingService.bookCar(regNumber, user);
        } catch (UserNotFoundException | CarNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllAvailableCars(CarBookingService carBookingService) {
        System.out.println("Displaying available cars:");
        List<Car> cars = carBookingService.getAllAvailableCars();
        if (cars == null) {
            System.out.println("No Cars available!");
        } else {
            for (Car car: cars ) {
                System.out.println(car);
            }
        }
    }

    private static void displayElectricAvailableCars(CarBookingService carBookingService) {
        System.out.println("Displaying available electric cars:");
        List<Car> cars = carBookingService.getAvailableCarsPerType(CarEngineType.ELECTRIC);
        if (cars == null) {
            System.out.println("No Cars available!");
        } else {
            for (Car car: cars ) {
                System.out.println(car);
            }
        }
    }

    private static void displayAllUsers(UserService userService) {
        System.out.println("Displaying all users");
        List<User> users = userService.getUsers();
        if(users != null) {
            for (User user: users ) {
                System.out.println(user);
            }
        }
    }

    static public void printMenuOptions() {
        System.out.println("CLI Commander");
        System.out.println("-------------");
        System.out.println("1⃣ - Book Car");
        System.out.println("2⃣ - View All User Booked Cars");
        System.out.println("3⃣ - View All Bookings");
        System.out.println("4⃣ - View Available Cars");
        System.out.println("5⃣ - View Available Electric Cars");
        System.out.println("6⃣ - View all users");
        System.out.println("7⃣ - Exit");
        System.out.println("--------------");
    }


}