package library.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBConfigReader {
    public static String getDBPath() {
        String defaultPath = "jdbc:sqlite:db/library.db";
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            String path = reader.readLine();
            return (path != null && !path.trim().isEmpty()) ? path.trim() : defaultPath;
        } catch (IOException e) {
            return defaultPath;
        }
    }
}
