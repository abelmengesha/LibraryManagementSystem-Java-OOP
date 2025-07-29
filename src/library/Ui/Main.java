package library.ui;

import javax.swing.*;
import library.util.DBInitializer; // If you created this

public class Main {
    public static void main(String[] args) {
        // Optional: Initialize DB
        // DBInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}