package library.Ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(String role) {
        setTitle(role + " Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JButton registerUserBtn = new JButton("Register User");
        JButton manageBooksBtn = new JButton("Manage Books");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton exitBtn = new JButton("Exit");

        Color btnBlue = new Color(0, 123, 255);
        Color btnGreen = new Color(40, 167, 69);
        Color btnRed = new Color(220, 53, 69);
        Color btnTextColor = Color.WHITE;

        JButton[] allButtons = {registerUserBtn, manageBooksBtn, borrowBtn, returnBtn, exitBtn};
        for (JButton btn : allButtons) {
            btn.setForeground(btnTextColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(160, 50));
        }

        registerUserBtn.setBackground(btnBlue);
        manageBooksBtn.setBackground(btnGreen);
        borrowBtn.setBackground(new Color(0, 153, 204));
        returnBtn.setBackground(new Color(0, 153, 102));
        exitBtn.setBackground(btnRed);

        registerUserBtn.addActionListener(e -> new RegistrationForm().setVisible(true));
        manageBooksBtn.addActionListener(e -> new BookForm().setVisible(true));
        borrowBtn.addActionListener(e -> new BorrowForm().setVisible(true));
        returnBtn.addActionListener(e -> new ReturnForm().setVisible(true));
        exitBtn.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (role.equalsIgnoreCase("Librarian")) {
            panel.add(registerUserBtn, gbc);
            gbc.gridx = 1;
            panel.add(manageBooksBtn, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(borrowBtn, gbc);
            gbc.gridx = 1;
            panel.add(returnBtn, gbc);
        } else {
            panel.add(borrowBtn, gbc);
            gbc.gridx = 1;
            panel.add(returnBtn, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(exitBtn, gbc);

        add(panel);
    }
}
