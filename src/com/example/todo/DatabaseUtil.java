// Defines the package for this class, organizing it under com.example.todo
package com.example.todo;

// Importing the Dotenv library to load environment variables from a .env file
import io.github.cdimascio.dotenv.Dotenv;
// Importing necessary classes from the Java SQL library for database connections
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// The DatabaseUtil class provides a utility method to get a database connection
public class DatabaseUtil {
    // Load environment variables from the .env file using Dotenv
    private static final Dotenv dotenv = Dotenv.load();

    // Retrieve database credentials from environment variables
    // These values are fetched from the .env file for security (e.g., not hardcoding sensitive info)
    private static final String URL = dotenv.get("DB_URL");       // Database URL
    private static final String USER = dotenv.get("DB_USER");     // Database username
    private static final String PASSWORD = dotenv.get("DB_PASSWORD"); // Database password

    // Method to establish and return a connection to the database
    public static Connection getConnection() throws SQLException {
        // Optional: Load the PostgreSQL JDBC Driver (for compatibility with older JDBC versions)
        // This step is not strictly necessary for JDBC 4.0+ drivers, but it's included for robustness
        try {
            Class.forName("org.postgresql.Driver"); // Load the PostgreSQL driver class
        } catch (ClassNotFoundException e) {
            // If the driver class is not found, print an error message
            System.out.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        }
        // Use DriverManager to get a connection to the database using the URL, USER, and PASSWORD
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}