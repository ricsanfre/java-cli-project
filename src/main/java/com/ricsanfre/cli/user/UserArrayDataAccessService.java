package com.ricsanfre.cli.user;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class UserArrayDataAccessService implements UserDAO {
    private static final List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(), "Pepe"));
        users.add(new User(UUID.randomUUID(), "Juan"));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

}
