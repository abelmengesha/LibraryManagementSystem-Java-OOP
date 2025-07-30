package library.Ui;

import library.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RegistrationForm extends JFrame {

    private final UserService userService = new UserService();

    public RegistrationForm() {
        setTitle("Register User");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        String[] roles = {"Student", "Librarian"}; // Correct casing
        JComboBox<String> roleCombo = new JComboBox<>(roles);

        JButton registerBtn = new JButton("Register");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleCombo);
        panel.add(registerBtn);

        registerBtn.addActionListener((ActionEvent e) -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String role = ((String) roleCombo.getSelectedItem()).trim();

            // Normalize to match DB CHECK constraint
            if (role.equalsIgnoreCase("student")) {
                role = "Student";
            } else if (role.equalsIgnoreCase("librarian")) {
                role = "Librarian";
            } else {
                JOptionPane.showMessageDialog(this, "Invalid role selected.");
                return;
            }

            boolean result = userService.registerUser(name, email, role);
            if (result) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                nameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please check your input.");
            }
        });

        add(panel);
    }
}
