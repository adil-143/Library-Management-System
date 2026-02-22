package db;
import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";//Replace with your actual password

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connected DB: " + con.getCatalog());

            return con;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}