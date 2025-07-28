public class Student extends User {
     private String Department;

    public Student(int id, String Name, String Email, String Department) {
        super(id, Name, Email, "Student");
        this.Department = Department;
    }
    public Student(String Name, String Email, String Department) {
        super(Name, Email, "Student");
        this.Department = Department;
    }
     public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    @Override
    public String toString() {
        return super.toString() + " - Dept: " + Department;
}