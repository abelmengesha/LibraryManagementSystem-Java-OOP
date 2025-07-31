package library.Ui;

import library.models.BorrowRecord;
import library.models.User;
import library.service.BorrowService;
import library.service.UserService;
import library.service.BookService;
import javax.swing.*;
import java.awt.*;
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
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 248, 255));

        // Form panel with card style
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Return Book Details"),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Styled components
        userComboBox = new JComboBox<>();
        styleComboBox(userComboBox);
        borrowComboBox = new JComboBox<>();
        styleComboBox(borrowComboBox);
        JButton returnBtn = createStyledButton("Return Book", new Color(244, 67, 54));

        // User selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(createStyledLabel("Select User:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(userComboBox, gbc);

        // Borrowed book selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(createStyledLabel("Borrowed Book:"), gbc);
        gbc.gridx = 1;
        formPanel.add(borrowComboBox, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(returnBtn, gbc);

        loadUsers();

        userComboBox.addActionListener(e -> loadBorrowedBooks());

        returnBtn.addActionListener((ActionEvent e) -> {
            BorrowRecord selectedRecord = (BorrowRecord) borrowComboBox.getSelectedItem();
            if (selectedRecord == null) {
                showError("No book to return");
                return;
            }

            boolean returned = borrowService.returnBook(selectedRecord.getId());
            if (returned) {
                bookService.updateAvailability(selectedRecord.getBookId(), true);
                showSuccess("Book returned successfully");
                loadBorrowedBooks();
            } else {
                showError("Failed to return book");
            }
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(value.toString());
                }
                return this;
            }
        });
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(33, 33, 33));
        return label;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
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