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
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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


        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "ISBN", "Available"}, 0);
        bookTable = new JTable(tableModel);
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
