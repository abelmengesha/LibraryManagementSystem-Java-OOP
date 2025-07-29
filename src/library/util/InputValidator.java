package library.util;

public class InputValidator {
    
    public static boolean isEmpty(String input) {
        return input == null ||  input.trim().isEmpty();
        
    }

    public static boolean isVaildEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
    public static boolean isNumberic(String input){
        return input != null && input.matches("\\d+");
    }
}
