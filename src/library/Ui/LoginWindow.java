package library.Ui;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        setTitle("Library Login");
        setSize(500, 300);  // increased size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        // Set layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // light gray background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 51, 102)); // dark blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel roleLabel = new JLabel("Select Your Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setForeground(new Color(80, 80, 80)); // medium gray
        gbc.gridy = 1;
        panel.add(roleLabel, gbc);

        JButton studentBtn = new JButton("Student");
        JButton librarianBtn = new JButton("Librarian");

        // Button colors and styling
        studentBtn.setBackground(new Color(0, 123, 255)); // blue
        studentBtn.setForeground(Color.WHITE);
        studentBtn.setFocusPainted(false);
        studentBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 90, 190)));

        librarianBtn.setBackground(new Color(40, 167, 69)); // green
        librarianBtn.setForeground(Color.WHITE);
        librarianBtn.setFocusPainted(false);
        librarianBtn.setBorder(BorderFactory.createLineBorder(new Color(30, 140, 50)));

        Dimension btnSize = new Dimension(140, 50);
        studentBtn.setPreferredSize(btnSize);
        librarianBtn.setPreferredSize(btnSize);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(studentBtn, gbc);

        gbc.gridx = 1;
        panel.add(librarianBtn, gbc);

        add(panel);

        // Action Listeners
        studentBtn.addActionListener(e -> {
            new Dashboard("Student").setVisible(true);
            dispose();
        });

        librarianBtn.addActionListener(e -> {
            new Dashboard("Librarian").setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}
