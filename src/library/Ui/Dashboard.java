package library.Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

    public Dashboard(String role) {
        setTitle(role + " Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JButton registerUserBtn = new JButton("Register User");
        JButton manageBooksBtn = new JButton("Manage Books");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton exitBtn = new JButton("Exit");

        registerUserBtn.addActionListener(e -> new RegistrationForm().setVisible(true));

        manageBooksBtn.addActionListener((ActionEvent e) -> {
            new BookForm().setVisible(true);
        });

        borrowBtn.addActionListener((ActionEvent e) -> {
            new BorrowForm().setVisible(true);
        });

        returnBtn.addActionListener((ActionEvent e) -> {
            new ReturnForm().setVisible(true);
        });

        exitBtn.addActionListener((ActionEvent e) -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(manageBooksBtn);
        panel.add(borrowBtn);
        panel.add(returnBtn);
        panel.add(exitBtn);
        panel.add(registerUserBtn);


        add(panel);
    }
}