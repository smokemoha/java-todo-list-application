// Defines the package for this class, organizing it under com.example.todo
package com.example.todo;

// Importing necessary classes for SQL operations, date handling, and list management
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// The TaskDAO class manages database operations for tasks (Data Access Object)
public class TaskDAO {

    // CREATE: Insert a new task into the database
    public void addTask(Task task) {
        // SQL query to insert a new task into the 'tasks' table
        String sql = "INSERT INTO tasks (description, due_date, is_completed) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();  // Get a database connection
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  // Prepare the SQL statement with auto-generated keys
             
            // Set the values for the prepared statement from the Task object
            pstmt.setString(1, task.getDescription());  // Set description
            pstmt.setDate(2, Date.valueOf(task.getDueDate()));  // Convert LocalDate to SQL Date
            pstmt.setBoolean(3, task.isCompleted());  // Set completion status
            pstmt.executeUpdate();  // Execute the insert query

            // Retrieve the auto-generated task ID
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    task.setId(keys.getInt(1));  // Set the generated ID to the task object
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and print an error message
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    // READ: Retrieve all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();  // Initialize an empty list to hold tasks
        // SQL query to select all tasks ordered by ID
        String sql = "SELECT id, description, due_date, is_completed FROM tasks ORDER BY id";
        try (Connection conn = DatabaseUtil.getConnection();  // Get a database connection
             Statement stmt = conn.createStatement();  // Create a statement object
             ResultSet rs = stmt.executeQuery(sql)) {  // Execute the select query
             
            // Loop through the result set and create Task objects
            while (rs.next()) {
                int id = rs.getInt("id");  // Get task ID
                String description = rs.getString("description");  // Get description
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();  // Convert SQL Date to LocalDate
                boolean status = rs.getBoolean("is_completed");  // Get completion status
                tasks.add(new Task(id, description, dueDate, status));  // Add task to the list
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and print an error message
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;  // Return the list of tasks
    }

    // UPDATE: Update the completion status of a task
    public void updateTaskStatus(int taskId, boolean isCompleted) {
        // SQL query to update the completion status of a task by ID
        String sql = "UPDATE tasks SET is_completed = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();  // Get a database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  // Prepare the SQL statement
             
            // Set the values for the prepared statement
            pstmt.setBoolean(1, isCompleted);  // Set new completion status
            pstmt.setInt(2, taskId);  // Set task ID to identify the task
            pstmt.executeUpdate();  // Execute the update query
        } catch (SQLException e) {
            // Handle SQL exceptions and print an error message
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    // DELETE: Delete a task from the database
    public void deleteTask(int taskId) {
        // SQL query to delete a task by ID
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();  // Get a database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {  // Prepare the SQL statement
             
            // Set the task ID for the prepared statement
            pstmt.setInt(1, taskId);  // Identify the task to delete
            pstmt.executeUpdate();  // Execute the delete query
        } catch (SQLException e) {
            // Handle SQL exceptions and print an error message
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}