package library.service;

import library.dao.BookDAO;
import library.models.Book;
import library.util.FileLogger;

import java.util.List;

public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public boolean addBook(String title, String author, String isbn) {
        if (title == null || author == null || isbn == null ||
            title.trim().isEmpty() || author.trim().isEmpty() || isbn.trim().isEmpty()) {
            return false;
        }

        Book book = new Book(title, author, isbn);
        boolean result = bookDAO.addBook(book);

        if (result) {
            FileLogger.log("Book added: " + title + " by " + author);
        }

        return result;
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public boolean deleteBook(int bookId) {
        boolean result = bookDAO.deleteBook(bookId);
        if (result) {
            FileLogger.log("Book deleted. ID: " + bookId);
        }
        return result;
    }

    public boolean updateAvailability(int bookId, boolean available) {
        boolean result = bookDAO.updateBookAvailability(bookId, available);
        if (result) {
            FileLogger.log("Book availability updated. ID: " + bookId + ", Available: " + available);
        }
        return result;
    }
}
