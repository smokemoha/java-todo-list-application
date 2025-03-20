package com.example.todo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // CREATE: Insert a new task into the database
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (description, due_date, is_completed) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, task.getDescription());
            pstmt.setDate(2, Date.valueOf(task.getDueDate()));
            pstmt.setBoolean(3, task.isCompleted());
            pstmt.executeUpdate();

            // Get the generated ID and set it to the task
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    task.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    // READ: Retrieve all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT id, description, due_date, is_completed FROM tasks ORDER BY id";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                boolean status = rs.getBoolean("is_completed");
                tasks.add(new Task(id, description, dueDate, status));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;
    }

    // UPDATE: Update the completion status of a task
    public void updateTaskStatus(int taskId, boolean isCompleted) {
        String sql = "UPDATE tasks SET is_completed = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setBoolean(1, isCompleted);
            pstmt.setInt(2, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    // DELETE: Delete a task from the database
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}
