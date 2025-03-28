# Liveasy Loads API

A backend system for logistics load management built with Spring Boot and PostgreSQL. This API facilitates CRUD operations for handling loads, including filtering based on shipper details, product type, and facility locations.

## API Project Documentation for Liveasy

**Note:** This README has been refined using AI to enhance clarity and structure. The initial notes were informal, and AI assistance was used to professionalize the documentation.

**Note:** Lombok was originally utilized in this project; however, due to some inconsistencies in generating getters and setters, they were manually added to ensure the smooth operation of the system.

### Project Overview

This project serves as a backend API for managing loads within a logistics platform. It is developed using **Spring Boot** and employs **PostgreSQL** as the relational database management system. The API supports standard CRUD operations to handle logistics loads efficiently.

### Database Configuration

- The **`application.properties`** file contains the required database configurations.
- A **password** has been included, and Hibernate is configured to update the database schema dynamically, as PostgreSQL follows a structured relational model. If MongoDB were used instead, schema updates would not be necessary due to its schema-less nature.

### Project Structure

#### 1. **Entity Layer (`entity/`)**
   - **`Loads.java`**: Represents the primary entity `Loads`, containing all necessary attributes.
   - **`Facility.java`**: An embedded entity within `Loads`, responsible for storing facility-related information.

#### 2. **Controller Layer (`controller/`)**
   - **`LoadController.java`**: Manages API endpoints for load operations.
   - Available endpoints:
     - `POST /load` - Add a new load entry.
     - `GET /load` - Retrieve a list of all loads.
     - `GET /load/{loadId}` - Fetch details of a specific load using its ID.
     - `GET /load/shipper/{shipperId}` - Get loads filtered by `shipperId`.
     - `PUT /load/{loadId}` - Modify an existing load entry.
     - `DELETE /load/{loadId}` - Remove a load from the database.

#### 3. **Repository Layer (`repository/`)**
   - **`LoadRepository.java`**: Extends `JpaRepository` to manage database transactions.
   - Includes custom query methods like `findByShipperId` and `findByFacilityLoadingPoint` for specific filtering needs.

#### 4. **Service Layer (`service/`)**
   - **`LoadsService.java`**: Implements business logic for handling loads.
   - Manages operations such as creating, retrieving, updating, and deleting loads.

#### 5. **Exception Handling (`exception/`)**
   - **`GlobalExceptionHandler.java`**: Utilizes `@ControllerAdvice` to handle exceptions across the application.
   - **`ErrorResponse.java`**: Defines a structured format for error messages.

### Development Process
1. **Defined entity classes (`Loads`, `Facility`)** to structure the data model.
2. **Developed repository (`LoadRepository`)** to enable database interactions.
3. **Created service methods (`LoadsService`)** to implement the core business logic.
4. **Built REST endpoints (`LoadController`)** to expose functionalities.
5. **Implemented global exception handling (`GlobalExceptionHandler`)** for improved error management.
6. **Validated APIs using Postman or cURL** to ensure correctness and functionality.

This README provides a clear overview of the project, its architecture, and the development process. 🚀

