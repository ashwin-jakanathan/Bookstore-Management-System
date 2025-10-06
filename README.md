# Bookstore Management Application

A JavaFX-based bookstore system developed for **COE528 (Object-Oriented Analysis and Design)** at Toronto Metropolitan University.  
This project demonstrates strong software design principles, object-oriented programming, and the implementation of multiple design patterns including the **State Pattern**.

---

## 🧭 Overview

The **Bookstore Management Application** simulates a real-world online bookstore that supports two types of users:
- **Admin (Owner)** — manages books and customers.
- **Customer** — browses, purchases, and earns reward points.

The system integrates multiple features, including secure login, cart management, points redemption, and cash checkout.  
It’s built entirely in **Java** using **JavaFX** for the GUI and text file persistence for data storage.

---

## 🎯 Features

### For Customers
- **Login/Logout** using username and password.
- **View Available Books** with title and price.
- **Add/Remove Books from Cart** dynamically.
- **Checkout Options:**
  - Purchase with **cash** (earns points).
  - Redeem **reward points** for discounts or full payments.
- **Automatic Tier Upgrades**:
  - Silver → Gold based on accumulated points.

### For Admin
- **Add or Remove Books** from the system.
- **Manage Users:**
  - Add new customers.
  - Update balances.
  - Remove existing users.
- **Update Inventory** directly via `book.txt` file integration.

---

## 🧩 Design Patterns Used

- **State Pattern** — manages customer reward tiers (`SilverState`, `GoldState`) dynamically based on point thresholds.  
- **Encapsulation & Inheritance** — shared logic in abstract `User` class extended by `Customer` and `Owner`.  
- **Aggregation** — `ShoppingCart` maintains a list of `Book` objects without ownership.  
- **Modular Design** — classes like `Book`, `ShoppingCart`, and `CustomerState` promote scalability and maintainability.

---

## 🧱 Class Structure

| Class | Responsibility |
|-------|----------------|
| `BookStore` | Main application entry point |
| `User` | Abstract class for shared user data and login credentials |
| `Customer` | Handles points, balance, and state transitions |
| `Owner` | Manages books and customers |
| `Book` | Represents book data |
| `ShoppingCart` | Manages added books, total price, and checkout |
| `CustomerState`, `GoldState`, `SilverState` | Implement the State Pattern logic |

Full UML diagrams are included in the `/docs` folder:
- `BookStoreClassDiagram.pdf`
- `UseCaseDiagram.pdf`

---

## 💻 How to Run

1. **Clone this repository**
   ```bash
   git clone https://github.com/ashwinjakanathan/Bookstore-Management-App.git
   cd Bookstore-Management-App
