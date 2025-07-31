package library.Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public Dashboard(String username) {
        setTitle("Library Dashboard - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font headerFont = new Font("SansSerif", Font.BOLD, 22);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(headerFont);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBooksBtn = createStyledButton("📚 View Books");
        JButton issueBookBtn = createStyledButton("📖 Issue Book");
        JButton returnBookBtn = createStyledButton("🔄 Return Book");
        JButton registerUserBtn = createStyledButton("👤 Register User");
        JButton logoutBtn = createStyledButton("🚪 Logout");

        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);

        // Button actions
        viewBooksBtn.addActionListener(e -> {
            new BookForm().setVisible(true);
        });

        issueBookBtn.addActionListener(e -> {
            new BorrowForm().setVisible(true);
        });

        returnBookBtn.addActionListener(e -> {
            new ReturnForm().setVisible(true);
        });

        registerUserBtn.addActionListener(e -> {
            new RegistrationForm().setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginWindow().setVisible(true);
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        mainPanel.setBackground(new Color(245, 245, 250));

        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(viewBooksBtn);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(issueBookBtn);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(returnBookBtn);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(registerUserBtn);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(logoutBtn);

        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(250, 40));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(30, 144, 255));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 123, 255));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("Admin").setVisible(true));
    }
}