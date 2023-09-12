package user;

import java.util.UUID;

public class UserArrayDataAccessService implements UserDAO {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.randomUUID(), "Pepe"),
                new User(UUID.randomUUID(), "Juan")
        };
    }

    @Override
    public User[] getUsers() {
        return users;
    }

}
