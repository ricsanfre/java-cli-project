package com.ricsanfre.cli.user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakerDataAccessService implements UserDAO {
    private static final List<User> users;
    static {
        users = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 20 ; i++) {
            users.add(new User(UUID.randomUUID(), faker.name().firstName()));
        }
    }
    @Override
    public List<User> getUsers() {
        return users;
    }
}
