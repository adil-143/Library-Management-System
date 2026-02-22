# 📚 Library Management System

A Desktop-based Library Management System built using **Java Swing**, **JDBC**, and **MySQL**.  
This application allows efficient management of books, members, and transactions with a fine calculation system for late returns.

---

## 🚀 Features

### 📖 Book Management (Full CRUD)
- Add Book
- View All Books
- Search Books (by title or author)
- Update Book Details
- Delete Book
- Automatic Quantity Management

### 👥 Member Management
- Add Member
- View Members

### 🔄 Transaction System
- Issue Book
- Return Book
- Automatic Fine Calculation
- Prevent Double Return
- Transaction History View

### 💰 Fine System
- 7 Days allowed without fine
- ₹5 per extra day after due period
- Fine stored in database
- Fine displayed during return
- Visible in transaction history

---

## 🛠️ Technologies Used

- Java (JDK 8+)
- Java Swing (GUI)
- JDBC
- MySQL
- Git & GitHub

---

## 🏗️ Project Structure
Library-Management-System/<br>
│<br>
├── src/<br>
│ ├── db/ # Database connection<br>
│ ├── dao/ # Data Access Objects<br>
│ ├── ui/ # Swing UI Frames<br>
│ └── Main.java<br>
│<br>
├── .gitignore<br>
└── README.md<br>
<br>

---

## 🗄️ Database Setup

### 1️⃣ Create Database
```SQL
CREATE DATABASE library_db;
USE library_db;
```

### 2️⃣ Create Books Table
```SQL
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    publisher VARCHAR(100),
    quantity INT
);
```

###3️⃣ Create Members Table
```SQL
CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20)
);
```

###4️⃣ Create Transactions Table
```SQL
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    fine DOUBLE DEFAULT 0
);
```

##▶️ How to Run

###1️⃣ Compile
```Bash
javac -cp ".;mysql-connector-j-9.6.0.jar" -d . src\db\*.java src\dao\*.java src\ui\*.java src\Main.java
```

###2️⃣ Run
```Bash
java -cp ".;mysql-connector-j-9.6.0.jar" Main
```

⚠ Make sure:

MySQL server is running

Database name matches in DBConnection.java

MySQL connector JAR is in project root

🎯 Key Highlights

Proper DAO Architecture

PreparedStatement for secure queries

Dynamic JTable refresh after CRUD operations

Layered structure (UI → DAO → DB)

Business logic separated from UI

Fine calculation using date difference logic

📌 Future Improvements

Login Authentication

Role-based access

Member Update/Delete

Export to CSV

Maven Conversion

Modern UI Styling

👨‍💻 Author

Mohd Adil Ansari

⭐ If you found this project useful, feel free to star the repository!

