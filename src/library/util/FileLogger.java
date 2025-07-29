package library.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger {

    private static final String LOG_FILE = "log.txt";

    public static void log(String message) {
        String timestamp = "[" + LocalDateTime.now() + "]" ;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))){
            writer.write(timestamp + message);
            writer.newLine();

        } catch (IOException e){

            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
    
