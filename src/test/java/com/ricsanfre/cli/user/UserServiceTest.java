package com.ricsanfre.cli.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class UserServiceTest {
    @Test
    void canGetExistingUserFromCSV(){
        UserDAO userDAO = new UserCsvDataAccessService();
        UserService userService = new UserService(userDAO);
        try {
            User user = userService.getUser("0236e9db-8c46-45a1-8fef-718d12e271f3");
            User expectedUser = new User(UUID.fromString("0236e9db-8c46-45a1-8fef-718d12e271f3"), "Bond");
            Assertions.assertEquals(expectedUser, user);
        } catch (UserNotFoundException e) {
            Assertions.fail("User not found when it should!");
        }

   }

    @Test
    void canDetectNonExistingUserFromCSV() {
        UserDAO userDAO = new UserCsvDataAccessService();
        UserService userService = new UserService(userDAO);
        try {
            User user = userService.getUser("0236e9db-8c46-45a1-8fef-718d12e271f4");
            Assertions.assertFalse(user!=null && !user.getUserName().isEmpty(),
                          "User is found when it should not!");
        } catch (UserNotFoundException e) {
            Assertions.assertTrue(true);
        }
    }
}
