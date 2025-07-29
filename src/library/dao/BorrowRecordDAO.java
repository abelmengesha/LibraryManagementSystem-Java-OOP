package library.dao;

import library.model.BorrowRecord;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAO {

    private final String url = "jdbc:sqlite:db/library.db";

    public boolean borrowBook(int userID, int bookID) {
        String sql = "INSERT INTO borrow_records (user_id, book_id, borrow_date,  is_returned) VALUES (?, ?, ?, 0)";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, userID);
                stmt.setInt(2, bookID);
                stmt.setString(3, LocalDate.now().toString());

                return stmt.executeUpdate() > 0;

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
    }

    public boolean returnBook(int recordId){
        String sql = "UPDATE borrow_records SET return_date = ?, is_returned = 1 WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, LocalDate.now().toString());
                stmt.setInt(2, recordId);

                return stmt.executeUpdate() > 0;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
    }

    public List<BorrowRecord> getActiveBorrowsByUser(int userId) {

        List<BorrowRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records WHERE user_id = ? AND is_returned = 0";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){
                    BorrowRecord record = new BorrowRecord(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        null,
                        false
                    );
                    list.add(record);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

            return list;

    }

    public List<BorrowRecord> getAllBorrowRecords() {
        List<BorrowRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    BorrowRecord record = new BorrowRecord(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                        rs.getBoolean("is_returned")
                    );
                    list.add(record);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }

            return list;
    }
    
}
