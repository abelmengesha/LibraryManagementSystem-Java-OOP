package library.dao;

import library.model.User;
import library.model.Student;
import library.model.Librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String url = "jdbc:sqlite:db/library.db";


    public boolean addUser (User user){
        
        String sql = "INSERT INTO users(name, email, role) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, user.getName());
                stmt.setSting(2, user.getEmail());
                stmt.setString(3, user.getRole());

                return stmt.executeUpdate() > 0;

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                String role = rs.getString("role");
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                if ("Student".equalsIgnoreCase(role)) {
                    users.add(new Student(id, name, email, "N/A"));
                } else if ("Librarian".equalsIgnoreCase(role)) {
                    users.add(new Student (id, name, email, "N/A"));
                } else {
                    users.add(new User(id, name, email, role));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(url));
             PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");

                    if("Student".equalsIgnoreCase(role)){
                        return new Student(id, name, email, "N/A");
                    }else if ("Librarian".equals(role)) {
                        return new Student(id, name, email, "N/A");
                    }else {
                        return new User(id, name, email, role);
                    }
                }
             }catch (SQLException e){
                e.printStackTrace();

             }

             return null;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    

    
}
