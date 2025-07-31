package library.Ui;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RegistrationForm extends JFrame {
    private JTextField nameField, emailField, phoneField;
    private JButton registerButton, cancelButton;

    public RegistrationForm() {
        setTitle("User Registration Form");
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

        JLabel headerLabel = new JLabel("📝 Register New User");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerLabel.setForeground(primaryColor);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor, 2),
                "User Details", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), primaryColor));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        nameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);

        registerButton = new JButton("✅ Register");
        cancelButton = new JButton("❌ Cancel");

        registerButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        registerButton.setBackground(primaryColor);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationForm().setVisible(true);
        });
    }
}

