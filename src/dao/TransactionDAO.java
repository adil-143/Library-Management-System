package dao;
import db.DBConnection;
import java.sql.*;
import java.time.LocalDate;

public class TransactionDAO {

    public void issueBook(int bookId, int memberId) {

        String sql = "INSERT INTO transactions(book_id, member_id, issue_date) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setDate(3, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double returnBook(int transactionId, int bookId) {

        String selectSql = "SELECT issue_date FROM transactions WHERE transaction_id = ? AND return_date IS NULL";
        String updateSql = "UPDATE transactions SET return_date = ?, fine = ? WHERE transaction_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement selectPs = con.prepareStatement(selectSql);
            PreparedStatement updatePs = con.prepareStatement(updateSql)) {

            selectPs.setInt(1, transactionId);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {

                Date issueDate = rs.getDate("issue_date");
                Date returnDate = Date.valueOf(LocalDate.now());

                long days = (returnDate.getTime() - issueDate.getTime()) / (1000 * 60 * 60 * 24);

                double fine = 0;
                if (days > 7) {
                    fine = (days - 7) * 5;   // ₹5 per extra day
                }

                updatePs.setDate(1, returnDate);
                updatePs.setDouble(2, fine);
                updatePs.setInt(3, transactionId);
                updatePs.executeUpdate();

                // 🔥 Increase book quantity here
                BookDAO bookDAO = new BookDAO();
                bookDAO.increaseQuantity(bookId);

                return fine;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public java.util.List<Object[]> getAllTransactions() {

        java.util.List<Object[]> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("transaction_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date"),
                        rs.getDouble("fine")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}