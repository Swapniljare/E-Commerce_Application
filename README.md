# E-Commerce Application

The **E-Commerce Application** is a Java-based web project built using JSP, Servlets, and MySQL. It provides a simple interface for browsing products, adding them to a shopping cart, and placing orders. The application follows the **DAO (Data Access Object) pattern** for database operations and uses JSP pages styled with CSS for a clean user experience.

---

## 🚀 Features Implemented

* Display product catalog with details (name, description, price, image).
* Add products to shopping cart.
* View shopping cart with selected items.
* Update product quantities (+/−) in the cart.
* Remove items from the cart.
* Calculate total price dynamically.
* Place order and view confirmation page.
* Responsive JSP pages styled with CSS.
* Persistent storage using **MySQL database**.

---

## 🛠️ Technology Stack

* **Frontend:** JSP, HTML, CSS
* **Backend:** Java Servlets
* **Database:** MySQL
* **Build Tool:** Apache Maven
* **Server:** Apache Tomcat

---

## ⚙️ Setup Instructions

1. Clone the repository:

   bash
   git clone https://github.com/Swapniljare/E-Commerce_Application.git

2. Import the project into **Eclipse** as a Maven project.

3. Create the MySQL database:

   sql
   CREATE DATABASE ecommerce_db;

   sql
   CREATE TABLE products (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100),
       description TEXT,
       price DECIMAL(10,2),
       image_url VARCHAR(255)
   );

4. Update database credentials in the `DBUtil.java` file.

5. Deploy the project on **Apache Tomcat**.

6. Open in browser:

   http://localhost:8080/E-Commerce_Application/

---

## 📄 Conclusion

This project demonstrates the core features of an e-commerce platform such as product listing, shopping cart functionality, and order placement using **JSP, Servlets, and MySQL**. It serves as a foundation that can be extended with advanced features like user authentication, admin dashboard, and payment integration.

---
