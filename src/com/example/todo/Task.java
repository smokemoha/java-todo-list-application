// Defines the package for this class, organizing it under com.example.todo
package com.example.todo;

// Importing LocalDate from java.time for handling dates
import java.time.LocalDate;

// The Task class represents a single task in the To-Do List application
public class Task {
    // Private fields to store task properties
    private int id;                // Unique identifier for the task (auto-assigned by the database)
    private String description;    // Description of the task
    private LocalDate dueDate;     // Due date for the task
    private boolean isCompleted;   // Status indicating whether the task is completed

    // Constructor for creating new tasks (ID will be auto-assigned later)
    public Task(String description, LocalDate dueDate) {
        this.description = description; // Set the task description
        this.dueDate = dueDate;         // Set the due date
        this.isCompleted = false;       // New tasks are initially not completed
    }

    // Constructor for tasks loaded from the database (includes ID and completion status)
    public Task(int id, String description, LocalDate dueDate, boolean isCompleted) {
        this.id = id;                   // Set the task ID (from the database)
        this.description = description; // Set the task description
        this.dueDate = dueDate;         // Set the due date
        this.isCompleted = isCompleted; // Set the completion status
    }

    // Getter for the task ID
    public int getId() {
        return id; // Returns the task's unique identifier
    }

    // Getter for the task description
    public String getDescription() {
        return description; // Returns the task's description
    }

    // Getter for the due date
    public LocalDate getDueDate() {
        return dueDate; // Returns the task's due date
    }

    // Getter for the completion status
    public boolean isCompleted() {
        return isCompleted; // Returns whether the task is completed
    }

    // Setter for the task ID (used when assigning ID after creation)
    public void setId(int id) {
        this.id = id; // Sets the task's ID
    }

    // Setter for the task description
    public void setDescription(String description) {
        this.description = description; // Updates the task's description
    }

    // Setter for the due date
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate; // Updates the task's due date
    }

    // Setter for the completion status
    public void setCompleted(boolean completed) {
        this.isCompleted = completed; // Updates whether the task is completed
    }

    // Overrides the toString method to provide a readable string representation of the task
    @Override
    public String toString() {
        // Formats the task details into a string: "ID: Description | Due: DueDate | Status"
        return id + ": " + description + " | Due: " + dueDate + " | " + (isCompleted ? "Completed" : "Pending");
    }
}