package com.example.todo;

import java.time.LocalDate;

public class Task {
    private int id;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    // Constructor for new tasks (id will be auto-assigned)
    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    // Constructor for tasks loaded from the database
    public Task(int id, String description, LocalDate dueDate, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return isCompleted; }

    public void setId(int id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return id + ": " + description + " | Due: " + dueDate + " | " + (isCompleted ? "Completed" : "Pending");
    }
}
