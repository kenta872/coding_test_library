## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 17 or higher installed.
- Docker installed.
- Docker Compose installed.

## Running the Application

### Using Docker

1. Start the application:
    ```sh
    docker-compose up -d
    ```

2. To stop the application:
    ```sh
    docker-compose down
    ```

## API Endpoints

### Borrow Book

- **URL:** `/borrow`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "employeeId": 1,
        "bookIdList": [1, 2, 3]
    }
    ```
- **Responses:**
    - `200 OK` - Successfully borrowed books.
    - `400 Bad Request` - Invalid request data.

### Return Book

- **URL:** `/return`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "employeeId": 1,
        "bookId": 1
    }
    ```
- **Responses:**
    - `200 OK` - Successfully returned books.
    - `400 Bad Request` - Invalid request data.

### Return All Book
- **URL:** `/return/all`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "employeeId": 1
    }
    ```
- **Responses:**
    - `200 OK` - Successfully returned books.
    - `400 Bad Request` - Invalid request data.
