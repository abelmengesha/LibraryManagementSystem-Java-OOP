package library.Ui;

import library.models.Book;
import library.models.User;
import library.service.BookService;
import library.service.BorrowService;
import library.service.UserService;

import javax.swing.*;
import java.awt.*;
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
        setSize(500, 300); // slightly bigger window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        userComboBox = new JComboBox<>();
        bookComboBox = new JComboBox<>();
        JButton borrowBtn = new JButton("Borrow");

        // Style button
        borrowBtn.setBackground(new Color(0, 123, 255));
        borrowBtn.setForeground(Color.WHITE);
        borrowBtn.setFocusPainted(false);
        borrowBtn.setPreferredSize(new Dimension(120, 40));

        // Add components with labels and spacing
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Select User:"), gbc);

        gbc.gridx = 1;
        panel.add(userComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Select Book:"), gbc);

        gbc.gridx = 1;
        panel.add(bookComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(borrowBtn, gbc);

        loadUsers();
        loadBooks();

        borrowBtn.addActionListener((ActionEvent e) -> {
            User selectedUser = (User) userComboBox.getSelectedItem();
            Book selectedBook = (Book) bookComboBox.getSelectedItem();

            if (selectedUser == null || selectedBook == null) {
                JOptionPane.showMessageDialog(this, "Select a user and a book", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!selectedBook.isAvailable()) {
                JOptionPane.showMessageDialog(this, "Book is not available", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean borrowed = borrowService.borrowBook(selectedUser.getId(), selectedBook.getId());
            if (borrowed) {
                bookService.updateAvailability(selectedBook.getId(), false);
                JOptionPane.showMessageDialog(this, "Book borrowed successfully");
                loadBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to borrow book", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }

    private void loadUsers() {
        userComboBox.removeAllItems();
        List<User> users = userService.getAllUsers();
        if(users != null) {
            for (User u : users) {
                userComboBox.addItem(u);
            }
        }
    }

    private void loadBooks() {
        bookComboBox.removeAllItems();
        List<Book> books = bookService.getAllBooks();
        if(books != null) {
            for (Book b : books) {
                if (b.isAvailable()) {
                    bookComboBox.addItem(b);
                }
            }
        }
    }
}
