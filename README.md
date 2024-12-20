# EasyShop Capstone Project 🏬

## Description
EasyShop is an e-commerce platform designed to provide users with a seamless shopping experience. It allows users to explore and purchase products online, offering features like product browsing, category filtering, and login features. This project enhances the platform's backend API using **Spring Boot** and **MySQL**, with a focus on improving reliability and introducing new features.

---

## Features

### Major Bug Fixes
- Resolved issues causing duplicate entries in the database, ensuring data integrity.
- Fixed frontend price display bug where the minimum price was displayed twice.
- Added proper annotations to categories controller class as well as user privilges.
- Fixed search feature
- Ensured Postman requests recieved the right codes.

### API Annotations
Added various Spring annotations to ensure correct functionality, including:
- `@RestController`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`

### SQL Logic Improvements
Implemented and fixed missing logic in SqlDao Classes, ensuring:
- Proper database interaction.
- Accurate data retrieval and updates.

---

## Project Structure

### Directories
- **src/main/java/**: Contains Java source files for backend development.
- **src/main/resources/**: Stores configuration files and other static resources.

### Java Classes
- **ProductsController.java**: Manages endpoints for product-related operations like searching, adding, updating, and deleting products.
- **MySqlProductDao.java**: Implements database access methods for product operations.
- **MySqlCategoryDao.java**: Manages category-related database interactions.
- **Application.java**: Entry point of the Spring Boot application.

---
## New Lessons 
- **Postman Tests**:
- ![image](https://github.com/user-attachments/assets/6d2673b7-98b2-478c-b5ce-7b81e9ea8a35)

  

- **SpringBoot Annotations**:
- ![image](https://github.com/user-attachments/assets/0577059c-8cce-4004-81a8-f93f3262feaf)

  

- **FrontEnd**:
- ![image](https://github.com/user-attachments/assets/83edbaa2-c840-4827-b968-3f704733ed55)

  

---

## Lessons Learned
- **User Authentication and Permissions**: Improved user authentication and role-based access management in the project, ensuring that users had the appropriate privileges. 
- **API Testing**: Used Postman to validate API endpoints, ensuring the server processes requests correctly and returns expected results.
- **Spring Boot Framework**: Enhanced my understanding of the Spring Boot framework, especially with regard to using it to build RESTful APIs. Gained hands-on experience with the core concepts like controllers, mappings, and request handling, which significantly improved my backend development skills.
- **SQL Logic Refinement**: Improved SQL queries in SqlDao classes for better data retrieval and interaction with the MySQL database. 

---

## Future Improvements
- **Shopping Cart**: Add functionality to manage user carts, allowing addition, deletion, and updating of items.
- **Checkout Process**: Develop a complete checkout workflow to finalize orders.
- **Testing**: Introduce comprehensive unit testing to ensure better functionality.

---




