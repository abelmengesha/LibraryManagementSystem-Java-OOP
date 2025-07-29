package library.Ui;

import library.model.BorrowRecord;
import library.model.User;
import library.service.BorrowService;
import library.service.UserService;
import library.service.BookService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ReturnForm extends JFrame {

    private final UserService userService = new UserService();
    private final BorrowService borrowService = new BorrowService();
    private final BookService bookService = new BookService();

    private JComboBox<User> userComboBox;
    private JComboBox<BorrowRecord> borrowComboBox;

    public ReturnForm() {
        setTitle("Return Book");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        userComboBox = new JComboBox<>();
        borrowComboBox = new JComboBox<>();
        JButton returnBtn = new JButton("Return");

        panel.add(new JLabel("Select User:"));
        panel.add(userComboBox);
        panel.add(new JLabel("Borrowed Book:"));
        panel.add(borrowComboBox);
        panel.add(returnBtn);

        loadUsers();

        // Load records when a user is selected
        userComboBox.addActionListener(e -> loadBorrowedBooks());

        returnBtn.addActionListener((ActionEvent e) -> {
            BorrowRecord selectedRecord = (BorrowRecord) borrowComboBox.getSelectedItem();
            if (selectedRecord == null) {
                JOptionPane.showMessageDialog(this, "No book to return");
                return;
            }

            boolean returned = borrowService.returnBook(selectedRecord.getId());
            if (returned) {
                bookService.updateAvailability(selectedRecord.getBookId(), true);
                JOptionPane.showMessageDialog(this, "Book returned successfully");
                loadBorrowedBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to return book");
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

    private void loadBorrowedBooks() {
        borrowComboBox.removeAllItems();
        User selectedUser = (User) userComboBox.getSelectedItem();
        if (selectedUser == null) return;

        List<BorrowRecord> records = borrowService.getUserActiveBorrows(selectedUser.getId());
        for (BorrowRecord r : records) {
            borrowComboBox.addItem(r);
        }
    }
}