# Expense Manager Backend

## 📌 Project Description

Expense Manager is a RESTful backend application built using Spring Boot that allows users to manage daily expenses.  
It provides APIs to create, update, delete, and retrieve expenses with proper validation and structured architecture.

This project demonstrates backend development skills including REST API design, layered architecture, database integration, and exception handling.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL / H2 Database
- Maven
- Lombok

---

## ✨ Features

- Create a new expense
- Update an existing expense
- Delete an expense
- Get all expenses
- Get expense by ID
- Filter expenses by category
- Filter expenses by date range
- Input validation using annotations
- Global exception handling
- Proper HTTP status codes

---

## 📡 REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|------------|
| POST   | /api/expenses | Create expense |
| GET    | /api/expenses | Get all expenses |
| GET    | /api/expenses/{id} | Get expense by ID |
| PUT    | /api/expenses/{id} | Update expense |
| DELETE | /api/expenses/{id} | Delete expense |

---

## 📄 Sample Request (Create Expense)

```json
{
  "title": "Groceries",
  "amount": 1500,
  "category": "Food",
  "date": "2026-02-26",
  "description": "Weekly grocery shopping"
}
#database configuration

spring.datasource.url=jdbc:mysql://localhost:3306/expense_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
