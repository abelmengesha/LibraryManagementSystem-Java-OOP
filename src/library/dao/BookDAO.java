package library.dao;
import library.models.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private final String url = "jdbc:sqlite:db/library.db";
    
    public boolean addBook(Book book){
        String sql = "INSERT INTO books(title, author, isbn, available) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, book.getIsbn());
                stmt.setBoolean(4, book.isAvailable());

                return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public List<Book> getAllBooks(){ // Used to display in the UI in table list
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
          while (rs.next()){
            Book book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getBoolean("available")
            );
            books.add(book);
          }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }
    public Book getBookById(int id) {
    String sql = "SELECT * FROM books WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getBoolean("available")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    public boolean updateBookAvailability(int id, boolean available){
        String sql = "UPDATE books SET available = ? WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setBoolean(1, available);
                stmt.setInt(2, id);

                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

    }

    public boolean deleteBook(int id){
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
    }
    
}
