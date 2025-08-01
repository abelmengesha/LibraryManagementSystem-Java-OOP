package library.Ui;

import library.models.Book;
import library.service.BookService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BookForm extends JFrame {

    private final BookService bookService = new BookService();
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookForm() {
        setTitle("Book Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JTextField titleField = new JTextField(15);
        JTextField authorField = new JTextField(15);
        JTextField isbnField = new JTextField(15);

        JButton addBtn = new JButton("Add Book");
        JButton deleteBtn = new JButton("Delete Book");

        addBtn.setBackground(new Color(0, 123, 255));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(120, 35));

        deleteBtn.setBackground(new Color(220, 53, 69));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setPreferredSize(new Dimension(120, 35));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Author:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("ISBN:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(isbnField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        inputPanel.add(addBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        inputPanel.add(deleteBtn, gbc);

        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "ISBN", "Available"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookTable = new JTable(tableModel);
        bookTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        loadBooks();

        addBtn.addActionListener((ActionEvent e) -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = bookService.addBook(title, author, isbn);
            if (success) {
                JOptionPane.showMessageDialog(this, "Book added successfully");
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
                loadBooks();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a book to delete.");
                return;
            }

            int bookId = (int) tableModel.getValueAt(selectedRow, 0);
            String title = (String) tableModel.getValueAt(selectedRow, 1);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete the book titled \"" + title + "\"?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = bookService.deleteBook(bookId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully.");
                    loadBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        List<Book> books = bookService.getAllBooks();
        if (books != null) {
            for (Book b : books) {
                tableModel.addRow(new Object[]{
                        b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.isAvailable() ? "Yes" : "No"
                });
            }
        }
    }
}
