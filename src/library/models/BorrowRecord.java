package library.models;

import java.time.LocalDate;
import library.interfaces.Loggable;


public class BorrowRecord implements Loggable {
    private int id;
    private int userId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public BorrowRecord(int id, int userId, int bookId, LocalDate borrowDate, LocalDate returnDate, boolean isReturned) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public BorrowRecord(int userId, int bookId) {
        this(-1, userId, bookId, LocalDate.now(), null, false);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }

    @Override
    public String toString() {
        return "Record ID: " + id + ", Book ID: " + bookId + ", User ID: " + userId + ", Borrowed: " + borrowDate +
                (isReturned ? ", Returned: " + returnDate : ", Not yet returned");
    }
    @Override
    public String getLogMessage() {
       return "User " + getUserId() + " (" + getBookId() + getBorrowDate() + ")";
    }

   



}