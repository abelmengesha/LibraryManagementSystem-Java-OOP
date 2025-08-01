package library.Ui;

import javax.swing.*;
import library.util.DBInitializer; 

public class Main {
    public static void main(String[] args) {
        
        DBInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}   