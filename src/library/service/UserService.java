package library.service;

import java.util.List;

import library.dao.UserDAO;
import library.models.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean addUser(String name, String email, String role) {
        if (name == null || email == null || role == null) return false;

        name = name.trim();
        email = email.trim();
        role = role.trim();

        if (name.isEmpty() || email.isEmpty()) return false;


        User user = new User(name, email, role);
        return userDAO.addUser(user);
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

    public boolean registerUser(String name, String email, String role) {
        return addUser(name, email, role);
    }
}
