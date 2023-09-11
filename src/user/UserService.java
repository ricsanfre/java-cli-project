package user;

import java.util.UUID;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User[] getUsers() {
       return userDAO.getUsers();
    }

    public boolean isValidUser(String userId) {
        boolean isValid = false;
        UUID uuid = UUID.fromString(userId);
        User user = userDAO.getUserById(uuid);

        if (user!=null) {
            isValid = true;
        }
        return isValid;
    }

    public User getUser(String userId) throws UserNotFoundException {
        try {
            UUID uuid = UUID.fromString(userId);
            User user = userDAO.getUserById(uuid);
            if (user != null) {
                return user;
            } else {
                throw new UserNotFoundException("User: " + userId + " not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new UserNotFoundException("User: " + userId + " not found");
        }
    }
}
