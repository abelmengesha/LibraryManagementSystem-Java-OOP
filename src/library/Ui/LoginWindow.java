package library.Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        setTitle("Library Login");
        setSize(450, 400); // Increased height to accommodate new button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(0, 180, 216);
                Color color2 = new Color(0, 119, 182);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // White card panel for content
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        cardPanel.setBackground(new Color(255, 255, 255, 220));
        cardPanel.setOpaque(true);

        // Title
        JLabel titleLabel = new JLabel("LIBRARY LOGIN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Username field
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        userPanel.setBackground(new Color(255, 255, 255, 0));
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        userPanel.add(userLabel);
        userPanel.add(userField);

        // Password field
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        passPanel.setBackground(new Color(255, 255, 255, 0));
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passPanel.add(passLabel);
        passPanel.add(passField);

        // Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 150, 199));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Registration button
        JButton registerButton = new JButton("Create New Account");
        registerButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        registerButton.setForeground(new Color(0, 150, 199));
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effects
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(new Color(0, 119, 182));
            }
            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(new Color(0, 150, 199));
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                registerButton.setForeground(new Color(0, 100, 150));
            }
            public void mouseExited(MouseEvent evt) {
                registerButton.setForeground(new Color(0, 150, 199));
            }
        });

        // Login action
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            char[] password = passField.getPassword();
            
            if (!username.isEmpty() && password.length > 0) {
                dispose();
                new Dashboard(username).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both username and password", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Registration action
        registerButton.addActionListener(e -> {
            dispose();
            new RegistrationForm().setVisible(true);
        });

        // Add components to card
        cardPanel.add(titleLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(userPanel);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(passPanel);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(loginButton);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(registerButton);

        // Add card to main panel
        mainPanel.add(cardPanel);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}