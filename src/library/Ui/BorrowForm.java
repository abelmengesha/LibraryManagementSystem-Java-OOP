package library.ui;

import library.model.Book;
import library.model.User;
import library.service.BookService;
import library.service.BorrowService;
import library.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BorrowForm extends JFrame {

    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    private final BorrowService borrowService = new BorrowService();

    private JComboBox<User> userComboBox;
    private JComboBox<Book> bookComboBox;

    public BorrowForm() {
        setTitle("Borrow Book");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        userComboBox = new JComboBox<>();
        bookComboBox = new JComboBox<>();
        JButton borrowBtn = new JButton("Borrow");

        // Load data into dropdowns
        loadUsers();
        loadBooks();

        panel.add(new JLabel("Select User:"));
        panel.add(userComboBox);
        panel.add(new JLabel("Select Book:"));
        panel.add(bookComboBox);
        panel.add(borrowBtn);

        borrowBtn.addActionListener((ActionEvent e) -> {
            User selectedUser = (User) userComboBox.getSelectedItem();
            Book selectedBook = (Book) bookComboBox.getSelectedItem();

            if (selectedUser == null || selectedBook == null) {
                JOptionPane.showMessageDialog(this, "Select a user and a book");
                return;
            }

            if (!selectedBook.isAvailable()) {
                JOptionPane.showMessageDialog(this, "Book is not available");
                return;
            }

            boolean borrowed = borrowService.borrowBook(selectedUser.getId(), selectedBook.getId());
            if (borrowed) {
                bookService.updateAvailability(selectedBook.getId(), false);
                JOptionPane.showMessageDialog(this, "Book borrowed successfully");
                loadBooks(); // refresh list
            } else {
                JOptionPane.showMessageDialog(this, "Failed to borrow book");
            }
        });

        add(panel);
    }

    private void loadUsers() {
        userComboBox.removeAllItems();
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            userComboBox.addItem(u);
        }
    }

    private void loadBooks() {
        bookComboBox.removeAllItems();
        List<Book> books = bookService.getAllBooks();
        for (Book b : books) {
            if (b.isAvailable()) {
                bookComboBox.addItem(b);
            }
        }
    }
}