// Defines the package for this class, organizing it under com.example.todo
package com.example.todo;

// Importing necessary classes from the Java standard library
import java.time.LocalDate;  // For handling dates in the format YYYY-MM-DD
import java.util.List;      // For managing a collection of tasks
import java.util.Scanner;   // For reading user input from the console

// The TaskManager class is the core of the To-Do List application
public class TaskManager {
    // Private field to hold the TaskDAO object, responsible for data operations (e.g., storing/retrieving tasks)
    private TaskDAO taskDAO;
    // Private field for the Scanner object, used to capture user input from the console
    private Scanner scanner;

    // Constructor to initialize the TaskManager object
    public TaskManager() {
        taskDAO = new TaskDAO();         // Creates a new TaskDAO instance for task data management
        scanner = new Scanner(System.in); // Initializes the Scanner to read from standard input (keyboard)
    }

    // The run method is the main loop that drives the application
    public void run() {
        boolean running = true; // Controls the loop; true keeps the app running, false exits
        while (running) {
            // Display the menu options to the user
            System.out.println("\n---Welcome To Java To-Do List ---");
            System.out.println("1) Add Task");
            System.out.println("2) View Tasks");
            System.out.println("3) Mark Task as Completed");
            System.out.println("4) Delete Task");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");

            // Check if the user has entered an integer
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt(); // Read the integer input
                scanner.nextLine();             // Consume the leftover newline character to avoid input issues

                // Switch statement to handle the user's menu selection
                switch (choice) {
                    case 1:
                        addTask();       // Call method to add a new task
                        break;
                    case 2:
                        viewTasks();     // Call method to display all tasks
                        break;
                    case 3:
                        markTaskCompleted(); // Call method to mark a task as completed
                        break;
                    case 4:
                        deleteTask();    // Call method to delete a task
                        break;
                    case 5:
                        running = false; // Set running to false to exit the loop
                        System.out.println("Exiting application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again."); // Handle invalid menu options
                }
            } else {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input to prevent an infinite loop
            }
        }
        scanner.close(); // Close the Scanner to free up system resources when the app exits
    }

    // Method to add a new task to the to-do list
    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine(); // Read the task description from the user
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();     // Read the due date as a string
        LocalDate dueDate = LocalDate.parse(dateStr); // Convert the string to a LocalDate object
        Task task = new Task(description, dueDate);   // Create a new Task object with the description and due date
        taskDAO.addTask(task);                       // Add the task to storage via TaskDAO
        System.out.println("Task added with ID: " + task.getId()); // Confirm addition with the task's ID
    }

    // Method to display all tasks in the to-do list
    private void viewTasks() {
        List<Task> tasks = taskDAO.getAllTasks(); // Retrieve all tasks from the TaskDAO
        if (tasks.isEmpty()) {
            System.out.println("No tasks found."); // Inform the user if there are no tasks
        } else {
            // Loop through and print each task (assumes Task has a toString method)
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    // Method to mark a task as completed based on its ID
    private void markTaskCompleted() {
        viewTasks(); // Show all tasks so the user can see IDs
        System.out.print("Enter the task ID to mark as completed: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt(); // Read the task ID
            scanner.nextLine();         // Consume the newline character
            taskDAO.updateTaskStatus(id, true); // Update the task’s status to completed in storage
            System.out.println("Task marked as completed."); // Confirm the action
        } else {
            // Handle invalid input (non-integer)
            System.out.println("Invalid input. Please enter a valid task ID.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    // Method to delete a task based on its ID
    private void deleteTask() {
        viewTasks(); // Show all tasks so the user can see IDs
        System.out.print("Enter the task ID to delete: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt(); // Read the task ID
            scanner.nextLine();         // Consume the newline character
            taskDAO.deleteTask(id);     // Remove the task from storage via TaskDAO
            System.out.println("Task deleted."); // Confirm the deletion
        } else {
            // Handle invalid input (non-integer)
            System.out.println("Invalid input. Please enter a valid task ID.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    // The main method is the entry point of the application
    public static void main(String[] args) {
        new TaskManager().run(); // Create a TaskManager instance and start the application
    }
}