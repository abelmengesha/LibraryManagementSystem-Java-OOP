package library.abstracts;

public abstract class Account {
    private int id;
    private String name;
    private String email;

    public Account(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Account(String name, String email) {
        this(-1, name, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
    public abstract String getRole();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " <" + email + ">";
    }
}
