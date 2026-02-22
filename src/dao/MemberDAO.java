package dao;
import db.DBConnection;
import java.sql.*;

public class MemberDAO {

    public void addMember(String name, String email, String phone) {

        String sql = "INSERT INTO members(name, email, phone) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            ps.executeUpdate();
            System.out.println("Member Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.util.List<Object[]> getAllMembers() {

        java.util.List<Object[]> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM members";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}