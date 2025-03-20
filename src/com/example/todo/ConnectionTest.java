// Defines the package for this class, organizing it under com.example.todo
package com.example.todo;

// Importing necessary classes from the Java SQL library
import java.sql.Connection;  // Represents a connection to the database
import java.sql.SQLException; // Exception thrown for SQL-related errors

// The ConnectionTest class contains the main method to test the database connection
public class ConnectionTest {
    // The main method is the entry point of the application
    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            // Attempt to get a connection using DatabaseUtil.getConnection()
            // The try-with-resources ensures the connection is closed automatically after use
            if (conn != null && !conn.isClosed()) {
                // Check if the connection is not null and is not closed
                System.out.println("Successfully connected to PostgreSQL!");
            } else {
                // If the connection is null or closed, indicate failure
                System.out.println("Failed to connect to PostgreSQL.");
            }
        } catch (SQLException e) {
            // Catch any SQL exceptions that occur during the connection attempt
            System.out.println("Error: " + e.getMessage()); // Print the error message
        }
    }
}