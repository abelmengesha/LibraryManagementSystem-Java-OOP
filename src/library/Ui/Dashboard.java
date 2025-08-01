package library.Ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(String role) {
        setTitle(role + " Dashboard");
        setSize(500, 350);  // increased size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Buttons
        JButton registerUserBtn = new JButton("Register User");
        JButton manageBooksBtn = new JButton("Manage Books");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton exitBtn = new JButton("Exit");

        // Button colors (simple and consistent)
        Color btnBlue = new Color(0, 123, 255);
        Color btnGreen = new Color(40, 167, 69);
        Color btnRed = new Color(220, 53, 69);
        Color btnTextColor = Color.WHITE;

        registerUserBtn.setBackground(btnBlue);
        registerUserBtn.setForeground(btnTextColor);
        registerUserBtn.setFocusPainted(false);

        manageBooksBtn.setBackground(btnGreen);
        manageBooksBtn.setForeground(btnTextColor);
        manageBooksBtn.setFocusPainted(false);

        borrowBtn.setBackground(new Color(0, 153, 204));
        borrowBtn.setForeground(btnTextColor);
        borrowBtn.setFocusPainted(false);

        returnBtn.setBackground(new Color(0, 153, 102));
        returnBtn.setForeground(btnTextColor);
        returnBtn.setFocusPainted(false);

        exitBtn.setBackground(btnRed);
        exitBtn.setForeground(btnTextColor);
        exitBtn.setFocusPainted(false);

        Dimension btnSize = new Dimension(160, 50);
        registerUserBtn.setPreferredSize(btnSize);
        manageBooksBtn.setPreferredSize(btnSize);
        borrowBtn.setPreferredSize(btnSize);
        returnBtn.setPreferredSize(btnSize);
        exitBtn.setPreferredSize(btnSize);

        // Action Listeners
        registerUserBtn.addActionListener(e -> new RegistrationForm().setVisible(true));
        manageBooksBtn.addActionListener(e -> new BookForm().setVisible(true));
        borrowBtn.addActionListener(e -> new BorrowForm().setVisible(true));
        returnBtn.addActionListener(e -> new ReturnForm().setVisible(true));
        exitBtn.addActionListener(e -> dispose());

        // Layout with GridBagLayout for neat arrangement
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(registerUserBtn, gbc);

        gbc.gridx = 1;
        panel.add(manageBooksBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(borrowBtn, gbc);

        gbc.gridx = 1;
        panel.add(returnBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(exitBtn, gbc);

        add(panel);
    }
}
