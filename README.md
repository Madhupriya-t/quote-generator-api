# Quote Generator REST API

A RESTful Quote Generator API developed using Java 17 and Spring Boot.

## Features

- Retrieve a random inspirational quote.
- Retrieve a quote using its ID.
- Add new quotes.
- Check application health.
- Identify the pod or instance serving a request using the X-Served-By response header.

## Technologies Used

- Java 17
- Spring Boot
- Maven
- Git and GitHub
- JUnit 5

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /quote | Retrieve a random quote |
| GET | /quote/{id} | Retrieve a quote by ID |
| POST | /quote | Add a new quote |
| GET | /health | Check application health |

## Example Requests

### 1. Get a Random Quote

GET /quote

### 2. Get Quote by ID

GET /quote/1

### 3. Add a New Quote

POST /quote

Request Body:

{
  "text": "Great things take time.",
  "author": "Unknown"
}

### 4. Health Check

GET /health

Example Response:

{
  "status": "UP"
}

## Pod Identification

The API includes an X-Served-By response header to identify the instance or Kubernetes pod handling the request.

## Running the Application

Build the project:

mvn clean package

Run the application:

mvn spring-boot:run

The API will be available at:

http://localhost:8080

## Automated Testing

Run the automated tests using:

mvn clean test

## Note

Quotes are stored in memory. Newly added quotes are not persisted after the application restarts.
