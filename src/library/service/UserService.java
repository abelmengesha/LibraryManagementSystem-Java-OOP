package library.service;

import library.dao.UserDAO;
import library.models.User;
import library.util.FileLogger;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean addUser(User user) {
        boolean result = userDAO.addUser(user);
        if (result) {
            FileLogger.log("User added: " + user.getName() + " (" + user.getRole() + ")");
        }
        return result;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}