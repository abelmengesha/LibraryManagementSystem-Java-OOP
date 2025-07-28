public class Librarian extends User{
     private String staffId;

    public Librarian(int id, String name, String email, String staffId) {
        super(id, name, email, "Librarian");
        this.staffId =staffId;
    }
 public Librarian(String name, String email, String staffId) {
        super(name, email, "Librarian");
        this.staffId = staffId;
 }
   public String getStaffId() {
        return staffId;
    }
  public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return super.toString() + " - Staff ID: " + staffId;
    }
}
