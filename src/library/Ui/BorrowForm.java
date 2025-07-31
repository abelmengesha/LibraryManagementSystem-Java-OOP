package library.Ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BorrowForm extends JFrame {
    private JTextField memberField, bookField, dateField;
    private JButton borrowButton, cancelButton;

    public BorrowForm() {
        setTitle("Borrow Book Form");
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

        JLabel headerLabel = new JLabel("📖 Borrow a Book");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerLabel.setForeground(primaryColor);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2),
                "Borrow Details", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), primaryColor));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel memberLabel = new JLabel("Member Name:");
        memberField = new JTextField(20);

        JLabel bookLabel = new JLabel("Book Title:");
        bookField = new JTextField(20);

        JLabel dateLabel = new JLabel("Borrow Date:");
        dateField = new JTextField(20);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        memberLabel.setFont(labelFont);
        bookLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(memberLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(memberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(bookLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(bookField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);

        borrowButton = new JButton("📅 Borrow");
        cancelButton = new JButton("❌ Cancel");

        borrowButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        borrowButton.setBackground(primaryColor);
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setFocusPainted(false);

        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        buttonPanel.add(borrowButton);
        buttonPanel.add(cancelButton);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BorrowForm().setVisible(true);
        });
    }
}
