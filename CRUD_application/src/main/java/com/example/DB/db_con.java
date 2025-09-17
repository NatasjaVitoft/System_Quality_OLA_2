package com.example.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_con {

    private static final String URL = "jdbc:mysql://localhost:3306/notes"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Wrap it in an unchecked exception
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
