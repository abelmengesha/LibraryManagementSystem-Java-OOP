package library.models;

public class Student extends User {

    private String department;

    public Student(int id, String name, String email, String department) {
        super(id, name, email, "Student");
        this.department = department;
    }

    public Student(String name, String email, String department) {
        super(name, email, "Student");
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + " - Dept: " + department;
    }
}