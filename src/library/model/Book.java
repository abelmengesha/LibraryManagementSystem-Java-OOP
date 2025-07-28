public class Book{
    private int id;
    private String Title;
    private String Author;
    private String isbn;
    private boolean Available;
      public Book(int id, String Title, String Author, String isbn, boolean Available) {
        this.id = id;
        this.Title = Title;
        this.Author = Author;
        this.isbn = isbn;
        this.Available = Available;
    }
     public Book(String title, String author, String isbn) {
        this(-1, title, author, isbn, true);
     }
      public int getId() {
         return id;
         }
    public void setId(int id) { 
        this.id = id; 
    }
     public String getTitle() {
         return Title;
         }
    public void setTitle(String title) {
         this.Title = title; 
        }
     public String getAuthor() {
         return Author; 
        }
    public void setAuthor(String author) {
         this.Author = author; 
        }
     public String getIsbn() { 
        return isbn; 
    }
    public void setIsbn(String isbn) {
         this.isbn = isbn; 
        }
 public boolean isAvailable() { 
    return Available;
 }
    public void setAvailable(boolean available) { 
        this.Available = available; }
       @Override
    public String toString() {
        return Title + " by " + Author + (Available ? " [Available]" : " [Not Available]");
    }  
}