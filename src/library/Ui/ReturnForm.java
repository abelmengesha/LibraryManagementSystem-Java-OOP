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
            setSize(600, 300);  // increased size
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(240, 240, 240));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            userComboBox = new JComboBox<>();
            borrowComboBox = new JComboBox<>();
            JButton returnBtn = new JButton("Return");

            // Style button
            returnBtn.setBackground(new Color(220, 53, 69)); // bootstrap red
            returnBtn.setForeground(Color.WHITE);
            returnBtn.setFocusPainted(false);
            returnBtn.setPreferredSize(new Dimension(100, 50));

            // Add components with labels
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Select User:"), gbc);

            gbc.gridx = 1;
            panel.add(userComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Borrowed Book:"), gbc);

            gbc.gridx = 1;
            panel.add(borrowComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(returnBtn, gbc);

            loadUsers();

            userComboBox.addActionListener(e -> loadBorrowedBooks());

            returnBtn.addActionListener((ActionEvent e) -> {
                BorrowRecord selectedRecord = (BorrowRecord) borrowComboBox.getSelectedItem();
                if (selectedRecord == null) {
                    JOptionPane.showMessageDialog(this, "No book to return", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean returned = borrowService.returnBook(selectedRecord.getId());
                if (returned) {
                    bookService.updateAvailability(selectedRecord.getBookId(), true);
                    JOptionPane.showMessageDialog(this, "Book returned successfully");
                    loadBorrowedBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to return book", "Error", JOptionPane.ERROR_MESSAGE);
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
