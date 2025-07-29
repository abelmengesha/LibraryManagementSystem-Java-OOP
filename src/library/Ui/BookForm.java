package library.Ui;

import library.model.Book;
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
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Top input fields
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField isbnField = new JTextField(10);
        JButton addBtn = new JButton("Add Book");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("ISBN:"));
        inputPanel.add(isbnField);
        inputPanel.add(addBtn);

        // Table to display books
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "ISBN", "Available"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Refresh books table
        loadBooks();

        // Add book on button click
        addBtn.addActionListener((ActionEvent e) -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();

            if (title.isEmpty()  author.isEmpty()  isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required");
                return;
            }

            boolean success = bookService.addBook(title, author, isbn);
            if (success) {
                JOptionPane.showMessageDialog(this, "Book added!");
                loadBooks();
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book");
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        List<Book> books = bookService.getAllBooks();
        for (Book b : books) {
            tableModel.addRow(new Object[]{
                b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.isAvailable() ? "Yes" : "No"
            });
        }
    }
}