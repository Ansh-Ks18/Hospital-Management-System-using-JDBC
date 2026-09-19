# Hospital Management System using JDBC

A console-based **Hospital Management System** developed using **Core Java, JDBC, and MySQL**. The project demonstrates how Java applications can interact with a relational database to manage patients, doctors, appointments, and prescriptions.

## 🚀 Features

* Patient Management

  * Add patient
  * View patient details
  * View all patients
  * Update patient information

* Doctor Management

  * Add doctor
  * View doctor details
  * View all doctors
  * Update doctor information

* Appointment Management

  * Create appointments
  * View appointment details
  * Manage patient-doctor appointments

* Prescription Management

  * Add prescriptions
  * View prescription details
  * Manage prescription records

* Database Integration

  * MySQL database
  * JDBC connectivity
  * PreparedStatement for SQL operations
  * DAO and Service layer architecture

## 🛠️ Technologies Used

* Java
* JDBC
* MySQL
* SQL
* Eclipse IDE

## 🏗️ Project Structure

```text
Hospital_Mgmt_Sys/
│
├── src/
│   ├── Appointment/
│   │   ├── Appointment.java
│   │   ├── AppointmentDao.java
│   │   └── AppointmentService.java
│   │
│   ├── Doctor_Jdbc/
│   │   ├── Doctor.java
│   │   ├── DoctorDAO.java
│   │   └── DoctorService.java
│   │
│   ├── jdpc_hospital/
│   │   ├── DBConnection.java
│   │   ├── Main.java
│   │   ├── Patient.java
│   │   ├── PatientDAO.java
│   │   └── PatientService.java
│   │
│   └── prescription_jdbc/
│       ├── Prescription.java
│       ├── PrescriptionDAO.java
│       └── PrescriptionService.java
│
└── README.md
```

## 🧩 Architecture

The project follows a simple layered architecture:

```text
Main
  ↓
Service Layer
  ↓
DAO Layer
  ↓
JDBC
  ↓
MySQL Database
```

### Model

Contains Java classes representing database entities such as:

* Patient
* Doctor
* Appointment
* Prescription

### DAO

The DAO layer handles database operations such as:

* INSERT
* SELECT
* UPDATE
* DELETE

### Service

The Service layer contains application/business logic and communicates with the DAO layer.

### DBConnection

`DBConnection.java` is responsible for establishing the JDBC connection with MySQL.

## 🗄️ Database

The application uses **MySQL** as the relational database.

Before running the project:

1. Install MySQL.
2. Create the required database.
3. Create the required tables.
4. Update the database connection details in `DBConnection.java`.

Example JDBC connection:

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/hospital",
    "root",
    "your_password"
);
```

> Replace the database name, username, and password with your local MySQL configuration.

## 📦 JDBC Driver

Add the **MySQL Connector/J** driver to the project classpath.

The application uses JDBC classes such as:

```java
Connection
PreparedStatement
ResultSet
DriverManager
SQLException
```

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Ansh-Ks18/Hospital-Management-System-using-JDBC.git
```

### 2. Open the project in Eclipse

Import the project into Eclipse as an existing Java project.

### 3. Configure MySQL

Create the database and required tables.

### 4. Configure database credentials

Update:

```text
DBConnection.java
```

with your MySQL username, password, and database name.

### 5. Add MySQL Connector/J

Make sure the MySQL JDBC driver is included in the project's build path.

### 6. Run the application

Run:

```text
Main.java
```

and follow the console menu.

## 📚 Concepts Practiced

This project helped me practice:

* Core Java
* OOP
* Encapsulation
* Collections
* Exception Handling
* JDBC
* SQL
* MySQL
* PreparedStatement
* ResultSet
* DAO Pattern
* Service Layer
* CRUD Operations
* Database Connectivity

## 🔮 Future Improvements

Possible future enhancements:

* Add a web interface using Servlets/JSP
* Convert the backend to Spring Boot
* Add authentication and authorization
* Add a React frontend
* Implement validation
* Add logging and better exception handling
* Add REST APIs

## 👨‍💻 Author

**Anshu Kumar**

This project was developed as part of my Java Full Stack learning journey.
