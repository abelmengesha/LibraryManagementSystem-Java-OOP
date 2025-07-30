package library.models;

import library.abstracts.Account;
import library.interfaces.Loggable;

public class User extends Account implements Loggable {
    private String role;

    public User(int id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public User(String name, String email, String role) {
        super(name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String getLogMessage() {
       return "User " + getName() + " (" + getRole() + ")";
    }
}