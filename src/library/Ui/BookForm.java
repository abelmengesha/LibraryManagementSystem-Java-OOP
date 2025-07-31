package library.Ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BookForm extends JFrame {
    private JTextField titleField, authorField, isbnField;
    private JButton saveButton, cancelButton;

    public BookForm() {
        setTitle("Book Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 330);
        setLocationRelativeTo(null);
        setResizable(false);

        Color primaryColor = new Color(33, 150, 243); // Light blue
        Color backgroundColor = new Color(232, 245, 253);
        Color borderColor = new Color(100, 181, 246);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(backgroundColor);

        JLabel headerLabel = new JLabel("📚 Register a New Book");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerLabel.setForeground(primaryColor);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2),
                "Book Details", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), primaryColor));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);

        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(20);

        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField(20);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        titleLabel.setFont(labelFont);
        authorLabel.setFont(labelFont);
        isbnLabel.setFont(labelFont);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(authorLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(authorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(isbnField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);

        saveButton = new JButton("💾 Save");
        cancelButton = new JButton("❌ Cancel");

        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        saveButton.setBackground(primaryColor);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookForm().setVisible(true);
        });
    }
}
