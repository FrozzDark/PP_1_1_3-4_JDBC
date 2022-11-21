package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static Connection connection = null;

    static {
        String URL = "jdbc:mysql://localhost:3306/mydbtest";
        String USERNAME = "root";
        String PASSWORD = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
