package library.Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        setTitle("Library Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Select Role:");
        JButton studentBtn = new JButton("Student");
        JButton librarianBtn = new JButton("Librarian");

        studentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard("Student").setVisible(true);
                dispose();
            }
        });

        librarianBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard("Librarian").setVisible(true);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(studentBtn);
        panel.add(librarianBtn);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}