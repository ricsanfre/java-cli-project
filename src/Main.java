import booking.CarBooking;
import booking.CarBookingService;
import car.Car;
import car.CarEngineType;
import car.CarNotFoundException;
import user.User;
import user.UserNotFoundException;
import user.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Creating Objects
        UserService userService = new UserService();
        CarBookingService carBookingService = new CarBookingService();
        Scanner cliScanner = new Scanner(System.in);
        // Main loop cli options
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
            CarBooking[] carBookings = carBookingService.getBookingsByUser(user);
            if (carBookings == null) {
                System.out.println("No Bookings found!");
            } else {
                for (CarBooking carBooking : carBookings) {
                    System.out.println(carBooking);
                }
            }
        } catch (UserNotFoundException  e) {
            System.out.println("User Id not found!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllBookings(CarBookingService carBookingService) {
        System.out.println("Listing current Bookings...");
        CarBooking[] carBookings = carBookingService.getAllBookings();
        if (carBookings == null) {
            System.out.println("No Bookings found!");
        } else {
            for (CarBooking carBooking : carBookings) {
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
        Car[] cars = carBookingService.getAllAvailableCars();
        if (cars == null) {
            System.out.println("No Cars available!");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    private static void displayElectricAvailableCars(CarBookingService carBookingService) {
        System.out.println("Displaying available electric cars:");
        Car[] cars = carBookingService.getAvailableCarsPerType(CarEngineType.ELECTRIC);
        if (cars == null) {
            System.out.println("No Cars available!");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    private static void displayAllUsers(UserService userService) {
        System.out.println("Displaying all users");
        User[] users = userService.getUsers();
        if(users != null) {
            for (User user : users) {
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