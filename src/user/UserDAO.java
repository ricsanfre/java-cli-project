package user;

import java.net.UnknownServiceException;
import java.util.UUID;

public class UserDAO {
    private static final User[] users;
    static {
        users = new User[]{
                new User(UUID.randomUUID(), "Pepe"),
                new User(UUID.randomUUID(), "Juan")
        };
    }

    public User[] getUsers() {
        return users;
    }

    public User getUserById(UUID uuid) {
        User findUser = null;
        for (User user: users) {
            if (user.getUserId().equals(uuid)) {
                findUser = user;
            }
        }
        return findUser;
    }
}
