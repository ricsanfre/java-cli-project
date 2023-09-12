package user;

import java.util.UUID;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserCsvAccessService();
    }

    public User[] getUsers() {
        return userDAO.getUsers();
    }

    public User getUser(String userId) throws UserNotFoundException {
        try {
            UUID uuid = UUID.fromString(userId);
            User result = null;
            User[] allUsers = userDAO.getUsers();
            for (User user : allUsers) {
                if (user.getUserId().equals(uuid)) {
                    result = user;
                }
            }
            if (result != null) {
                return result;
            } else {
                throw new UserNotFoundException("User: " + userId + " not found");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new UserNotFoundException("User: " + userId + " not found");
        }
    }
}
