package user;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UserCsvAccessService implements UserDAO {

    private final int DBCAPACITY = 10;
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        // Load users from CSV file
        File file = new File("src/users.csv");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] fields = scanner.nextLine().split(",");
                users.add(new User(UUID.fromString(fields[0]),fields[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Users CSV file does not exists!!");
        }

        if (!users.isEmpty()) {
            return users;
        } else {
            return null;
        }
    }

}
