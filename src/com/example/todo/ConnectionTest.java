package com.example.todo;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Successfully connected to PostgreSQL!");
            } else {
                System.out.println("Failed to connect to PostgreSQL.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
