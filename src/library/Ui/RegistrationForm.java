package library.Ui;

import library.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationForm extends JFrame {

    private final UserService userService = new UserService();

    public RegistrationForm() {
        setTitle("Register User");
        setSize(450, 300); // increased size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        String[] roles = {"Student", "Librarian"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(0, 123, 255));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setPreferredSize(new Dimension(120, 40));

        // Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Email Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Role Label and ComboBox
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Role:"), gbc);

        gbc.gridx = 1;
        panel.add(roleCombo, gbc);

        // Register Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerBtn, gbc);

        registerBtn.addActionListener((ActionEvent e) -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String role = ((String) roleCombo.getSelectedItem()).trim();

            // Normalize role string to match DB CHECK constraint
            if (role.equalsIgnoreCase("student")) {
                role = "Student";
            } else if (role.equalsIgnoreCase("librarian")) {
                role = "Librarian";
            } else {
                JOptionPane.showMessageDialog(this, "Invalid role selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean result = userService.registerUser(name, email, role);
            if (result) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                nameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }
}
