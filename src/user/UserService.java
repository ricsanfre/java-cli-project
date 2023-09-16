package user;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUser(String userId) throws UserNotFoundException {
        try {
            UUID uuid = UUID.fromString(userId);
            User result = null;
            List<User> allUsers = userDAO.getUsers();

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
