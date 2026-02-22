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
