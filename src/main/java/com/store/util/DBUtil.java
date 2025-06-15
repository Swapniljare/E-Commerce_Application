package com.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Swapnil@2549"; // check your actual password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}

