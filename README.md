# Java To-Do List Application

A robust, console-based To-Do List application built in Java that integrates with a PostgreSQL database. This application demonstrates full CRUD (Create, Read, Update, Delete) operations, secure configuration management using environment variables with dotenv-java, and input validation for a production-ready project.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [1. PostgreSQL Database Setup](#1-postgresql-database-setup)
  - [2. Eclipse Project Setup](#2-eclipse-project-setup)
  - [3. Adding the dotenv-java Library](#3-adding-the-dotenv-java-library)
  - [4. Environment Variable Configuration](#4-environment-variable-configuration)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Further Enhancements](#further-enhancements)
- [License](#license)

## Features

- **CRUD Operations:**  
  - **Create:** Add new tasks with a description and due date.
  - **Read:** View all tasks stored in the PostgreSQL database.
  - **Update:** Mark tasks as completed.
  - **Delete:** Remove tasks from the database.

- **Database Integration:**  
  - Uses PostgreSQL for persistent storage.
  - Utilizes JDBC for database connectivity.

- **Secure Configuration:**  
  - Loads database credentials from an external `.env` file using the [dotenv-java](https://github.com/cdimascio/dotenv-java) library.
  
- **Input Validation:**  
  - Robust handling of user input to avoid runtime exceptions.

## Prerequisites

- **Java JDK 8 or Later**
- **Eclipse IDE** (or your preferred Java IDE)
- **PostgreSQL** (with pgAdmin for database management)
- **PostgreSQL JDBC Driver**
- **dotenv-java Library** (version 3.2.0 as per Maven Central)

## Setup Instructions

### 1. PostgreSQL Database Setup

1. **Install PostgreSQL & pgAdmin:**
   - Download and install PostgreSQL from [PostgreSQL Downloads](https://www.postgresql.org/download/).
   - Launch pgAdmin from your applications menu.

2. **Create a New Database:**
   - In pgAdmin, connect to your PostgreSQL server.
   - Right-click on **Databases** and select **Create > Database...**.
   - Name your database (e.g., `todolist_db`) and click **Save**.

3. **Create the Tasks Table:**
   - Open the Query Tool in pgAdmin (right-click your new database and select **Query Tool**).
   - Execute the following SQL command:
     ```sql
     CREATE TABLE tasks (
         id SERIAL PRIMARY KEY,
         description VARCHAR(255) NOT NULL,
         due_date DATE,
         is_completed BOOLEAN DEFAULT FALSE
     );
     ```

### 2. Eclipse Project Setup

1. **Create a New Java Project:**
   - Open Eclipse and select **File > New > Java Project**.
   - Name the project (e.g., `ToDoApp`) and click **Finish**.

2. **Create Package and Classes:**
   - In the `src` folder, create a new package: `com.example.todo`.
   - Create the following classes:
     - `Task.java`
     - `DatabaseUtil.java`
     - `TaskDAO.java`
     - `TaskManager.java`
     - (Optional) `ConnectionTest.java` for testing the database connection.

### 3. Adding the dotenv-java Library

Since your project is non-Maven, download the implementation jar for dotenv-java manually:

1. **Download dotenv-java JAR:**
   - Visit [Maven Central for dotenv-java 3.2.0](https://search.maven.org/artifact/io.github.cdimascio/dotenv-java/3.2.0/jar) and download the jar (not the javadoc).

2. **Add the JAR to Your Project:**
   - Right-click your project in Eclipse, select **Build Path > Configure Build Path**.
   - Go to the **Libraries** tab and click **Add External JARs…**.
   - Select the downloaded `dotenv-java-3.2.0.jar` and click **Apply and Close**.

### 4. Environment Variable Configuration

1. **Create a `.env` File:**
   - At the root of your project (same level as your `src` folder), create a file named `.env`.
   - Add the following content to the `.env` file:
     ```env
     DB_URL=jdbc:postgresql://localhost:5432/todolist_db
     DB_USER=postgres
     DB_PASSWORD=your_password_here
     ```
   - **Important:** Add `.env` to your `.gitignore` to avoid exposing sensitive information.

2. **Update `DatabaseUtil.java`:**
   - Modify your `DatabaseUtil` class to load credentials from the `.env` file using dotenv-java (see code below).

## Running the Application

1. **Ensure PostgreSQL is Running:**
   - Start your PostgreSQL server and verify your database and table exist via pgAdmin.

2. **Run the Application in Eclipse:**
   - Right-click `TaskManager.java` and select **Run As > Java Application**.
   - Use the console menu to add, view, update, and delete tasks.
   - You can test the database connection separately using `ConnectionTest.java`.


