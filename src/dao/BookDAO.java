package dao;
import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    int testt;
    public void addBook(String title, String author, String publisher, int quantity) {
        String query = "INSERT INTO books(title, author, publisher, quantity) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, quantity);

            ps.executeUpdate();
            System.out.println("Book Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public java.util.List<Object[]> getAllBooks() {

        java.util.List<Object[]> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean reduceQuantity(int bookId) {

        String checkSql = "SELECT quantity FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            PreparedStatement updatePs = con.prepareStatement(updateSql)) {

            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");

                if (quantity > 0) {
                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void increaseQuantity(int bookId) {

        String sql = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.util.List<Object[]> searchBooks(String keyword) {

        java.util.List<Object[]> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteBook(int bookId) {

        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(int bookId, String title, String author, String publisher, int quantity) {

        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, quantity = ? WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, quantity);
            ps.setInt(5, bookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}