# Account Service

This is the API for handling customer and account operations.

## Features

- **Exception Handling:** Custom exception classes and global exception handling ensure robust error management.
- **Logging:** Utilizes a logging framework/library to capture application events and errors effectively.
- **WebClient:** WebClient facilitates making HTTP requests to external services with ease and provides flexible error handling.
- **JUnit:** Employs JUnit for unit testing, ensuring code reliability through comprehensive test coverage.
- **Controller & Service layer:** Controller layer handles incoming requests and delegates business logic to service layer for processing.
- **DAO/Repository:** Data access layers manage database interactions, including CRUD operations and ORM framework usage.
- **H2 In-Memory Database:** H2 provides an in-memory database solution for development and testing, simplifying setup and teardown.
- **Swagger:** Integration with Swagger enables API documentation generation and interactive API testing.

## Installation

### Prerequisites

- Ensure you have Java 21 installed.
- Ensure you have Maven installed.

### Steps

1. Clone the repository:
git clone https://github.com/Capgemini-Assessment/account-service.git
cd account-service

2. Build the project:
mvn clean install

3. Run the project:
mvn spring-boot:run


The service will start on [http://localhost:8084](http://localhost:8084).

For API related information, visit [Swagger UI](http://localhost:8084/swagger-ui/index.html#/).

