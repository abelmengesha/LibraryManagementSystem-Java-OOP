package library.service;

import library.dao.BookDAO;
import library.model.Book;
import library.util.FileLogger;

import java.util.List;

public class BookService {
    private final BookDAO bookDAO = new BookDAO();

    public boolean addBook(String title, String author, String isbn) {
        if (title.isEmpty()  author.isEmpty()  isbn.isEmpty()) {
            return false;
        }

        Book book = new Book(title, author, isbn);
        boolean result = bookDAO.addBook(book);

        if (result) {
            FileLogger.log("Book added: " + book.getTitle());
        }

        return result;
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public boolean updateAvailability(int bookId, boolean available) {
        boolean result = bookDAO.updateBookAvailability(bookId, available);
        if (result) {
            FileLogger.log("Book ID " + bookId + " availability set to " + available);
        }
        return result;
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }
}