package library.service;

import library.dao.BorrowRecordDAO;
import library.models.BorrowRecord;
import library.util.FileLogger;

import java.util.List;

public class BorrowService {
    private final BorrowRecordDAO borrowDAO = new BorrowRecordDAO();

    public boolean borrowBook(int userId, int bookId) {
        boolean result = borrowDAO.borrowBook(userId, bookId);
        if (result) {
            FileLogger.log("User ID " + userId + " borrowed Book ID " + bookId);
        }
        return result;
    }

    public boolean returnBook(int recordId) {
        boolean result = borrowDAO.returnBook(recordId);
        if (result) {
            FileLogger.log("Return confirmed for Borrow Record ID: " + recordId);
        }
        return result;
    }

    public List<BorrowRecord> getUserActiveBorrows(int userId) {
        return borrowDAO.getActiveBorrowsByUser(userId);
    }

    public List<BorrowRecord> getAllRecords() {
        return borrowDAO.getAllBorrowRecords();
    }
}