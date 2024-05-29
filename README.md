# Merchant Management Software - Spring Boot Project

This is a basic merchant management software built with Spring Boot that provides REST APIs for managing merchants and categories.

## Description

The application allows an admin user to perform CRUD operations on merchants and categories. Admins can add new merchants, list all merchants, delete a merchant, and perform other operations after logging in. Each merchant is associated with a category through a foreign key relationship. The system ensures that every merchant has a unique registration number. Authentication for the APIs is implemented using JWT tokens. MySQL is used as the database for storing merchant and category information. Postman collection is provided to document all APIs.

## Terminology

- **Merchant**: A shop entity containing information such as shop name, phone number, owner name, and category.
- **Category**: Represents the type of products sold by the merchants, such as restaurant, bakery, grocery, etc.

## List of APIs

1. **Add Merchants** (POST): Endpoint to add a new merchant to the system.
2. **Add Categories** (POST): Endpoint to add a new category for merchants.
3. **Get List of Merchants** (GET): Endpoint to retrieve the list of all merchants.
4. **Login API for Admin** (POST): Endpoint for admin login to authenticate and access other APIs.
5. **Get Single Merchant Details** (GET): Endpoint to retrieve details of a specific merchant.
6. **Delete Merchant** (DELETE): Endpoint to delete a merchant from the system.

## Technologies Used

- Spring Boot
- MySQL
- JWT for authentication

## Usage

1. Clone the repository.
2. Set up MySQL database and configure database properties in `application.properties`.
3. Run the application using Maven or your preferred IDE.
4. Use Postman or any other REST client to access the APIs.

## Documentation

- Detailed documentation for each API is available in the provided [Postman collection](https://documenter.getpostman.com/view/34082519/2sA3QteX8K).


