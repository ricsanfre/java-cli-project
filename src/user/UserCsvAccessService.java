package user;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class UserCsvAccessService implements UserDAO {

    private final int DBCAPACITY = 10;
    @Override
    public User[] getUsers() {
        User[] users = new User[DBCAPACITY];
        User[] result = null;
        int count=0;
        // Load users from CSV file
        File file = new File("src/users.csv");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] fields = scanner.nextLine().split(",");
                users[count] = new User(UUID.fromString(fields[0]),fields[1]);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Users CSV file does not exists!!");
        }

        if (count > 0) {
            result = Arrays.copyOf(users, count);
        }
        return result;
    }

}
