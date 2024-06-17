# Partner Universities Management System

This project is a RESTful client/server system to manage partner universities and their modules. It includes CRUD operations, filtering, pagination, and ordering for university records. The backend is built with Spring Boot, and the frontend interacts with the backend using REST principles.

---

## Setup and Running
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Hardik-26/Portfolio-3.git
<br>

2. **Load the repository as a Maven project**
<br>

3. **Start The Server:**
   - Locate the ApiApplication.java file under: src/main/java/com/portfolio/api/ApiApplication.java
   - Run the main function of this java file.

    
    **! NOTE: The server needs to be running to execute the integration tests !**

<br>

4. **Run The Integration Tests:**
   ```sh
   mvn verify
<br>

5. **The Project Runs On http://localhost:8080/portfolio**
<br>

6. **Thats All.** 

---

Endpoints
---------

### University Endpoints

-   **Create a University:** `POST: /universities`
-   **Get a University by ID:** `GET: /universities/{id}`
-   **Update a University:** `PUT: /universities/{id}`
-   **Delete a University:** `DELETE: /universities/{id}`
-   **Search Universities:** `GET: /universities`

### Module Endpoints

-   **Create a Module:** `POST: /modules`
-   **Get a Module by ID:** `GET: /modules/{id}`
-   **Update a Module:** `PUT: /modules/{id}`
-   **Delete a Module:** `DELETE: /modules/{id}`

Example Search Queries
----------------------

-   **Filter by name:** `/universities?name=Test`
-   **Filter by country:** `/universities?country=India`
-   **Pagination:** `/universities?page=0&size=10`
-   **Sorting:** `/universities?sort=name,asc`

---

## Features

- Manage partner universities:
  - Create, read, update, and delete universities
  - Search with filtering, pagination, and ordering
- Manage modules:
  - Create, read, update, and delete modules
- RESTful client interface for easy interaction
- Integration tests for all functionalities

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Docker


