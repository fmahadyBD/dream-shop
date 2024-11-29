---

# **Dream Shop**

---

## **How to Run**
1. **Clone the repository:**
   ```bash
   git clone https://github.com/fmahadyBD/dream-shop.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd dream-shop
   ```

3. **Start the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Test the endpoints:**  
   Use tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/).

---

## **User Management API Documentation**

### **Endpoints**

#### **1. User Registration**

- **Endpoint:**  
  `POST http://localhost:8080/registration`

- **Description:**  
  Adds a new user to the system.

- **Request Body:**
  ```json
  {
    "first_name": "Mahady Hasan",
    "last_name": "Fahim",
    "email": "fmahadybd@gmail.com",
    "password": "12345",
    "userRole": "ADMIN"
  }
  ```

- **Response:**
    - **Success:**  
      **HTTP Status:** `200 OK`
      ```json
      {
        "message": "Added new user",
        "data": {
          "userid": 2,
          "first_name": "Mahady Hasan",
          "last_name": "Fahim",
          "email": "fmahadybd@gmail.com",
          "password": "$2a$10$IhDAzGDF7TtZgXrfqfwJM.qZs0fIQUjPeV0JR4ugJxYP7QXDkqHsq",
          "userRole": "ADMIN"
        }
      }
      ```

    - **Error:**  
      **HTTP Status:** `400 Bad Request`
      ```json
      {
        "error": "Validation error",
        "details": "Email is already in use"
      }
      ```

---

#### **2. User Login**

- **Endpoint:**  
  `POST http://localhost:8080/authenticate`

- **Description:**  
  Authenticates an existing user.

- **Request Body:**
  ```json
  {
    "email": "fmahady01@gmail.com",
    "password": "12345"
  }
  ```

- **Response:**
    - **Success:**  
      **HTTP Status:** `200 OK`
      ```json
      {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmbWFoYWR5MDFAZ21haWwuY29tIiwiaWF0IjoxNzMyNDAxMzk5LCJleHAiOjE3MzI0MDE0MTl9.VrX0t8oqmbMqMzIjjUDcyUoZo-2HuaFUc-m3y7OiAra0SM4-xIKNhDc6Wyt32se5qp870KZ8ts2wyDYpp8cJpg"
      }
      ```

    - **Error:**  
      **HTTP Status:** `403 Forbidden`
      ```json
      {
        "error": "Invalid credentials"
      }
      ```

---

## **Notes**

- The `password` field is hashed using BCrypt when stored in the database.
- Role-based access control is implemented via the `userRole` field (`ADMIN`, `USER`, `MODERATOR`).
- Use the authentication token from the login response to access protected routes.

---

## **Category Management API Documentation**

### **Endpoints**

#### **1. Add Category**

- **Endpoint:**  
  `POST http://localhost:8080/admin/api/v1/categories/add`

- **Authentication:**  
  Set the Bearer Token from the authentication response.

- **Request Body:**
  ```json
  {
    "name": "Art"
  }
  ```

- **Response:**
    - **Success:**  
      **HTTP Status:** `200 OK`
      ```json
      {
        "message": "Add Category Successfully",
        "data": {
          "id": 1,
          "name": "Art"
        }
      }
      ```

---

#### **2. Get All Categories**

- **Endpoint:**  
  `GET http://localhost:8080/admin/api/v1/categories/all`

- **Authentication:**  
  Set the Bearer Token from the authentication response.

- **Response:**  
  **HTTP Status:** `200 OK`
  ```json
  {
    "message": "Found",
    "data": [
      {
        "id": 1,
        "name": "Art"
      }
    ]
  }
  ```

---

#### **3. Get Category by ID**

- **Endpoint:**  
  `GET http://localhost:8080/admin/api/v1/categories/category/id/{id}`

- **Response:**  
  **HTTP Status:** `200 OK`
  ```json
  {
    "message": "Found",
    "data": {
      "id": 2,
      "name": "Art"
    }
  }
  ```

---

#### **4. Get Category by Name**

- **Endpoint:**  
  `GET http://localhost:8080/admin/api/v1/categories/category/name/{name}`

- **Response:**  
  **HTTP Status:** `200 OK`
  ```json
  {
    "message": "Found",
    "data": {
      "id": 2,
      "name": "Art"
    }
  }
  ```

---

#### **5. Update Category by ID**

- **Endpoint:**  
  `PUT http://localhost:8080/admin/api/v1/categories/category/update/id/{id}`

- **Request Body:**
  ```json
  {
    "name": "The Art Color"
  }
  ```

- **Response:**
    - **Success:**  
      **HTTP Status:** `200 OK`
      ```json
      {
        "message": "Update Category Successfully",
        "data": {
          "id": 2,
          "name": "The Art Color"
        }
      }
      ```

    - **Error:**  
      **HTTP Status:** `404 Not Found`
      ```json
      {
        "message": "Category Not Found!",
        "data": null
      }
      ```

---

#### **6. Delete Category by ID**

- **Endpoint:**  
  `DELETE http://localhost:8080/admin/api/v1/categories/category/delete/id/{id}`

- **Response:**
    - **Success:**  
      **HTTP Status:** `200 OK`
      ```json
      {
        "message": "Delete Category Successfully",
        "data": null
      }
      ```

    - **Error:**  
      **HTTP Status:** `404 Not Found`
      ```json
      {
        "message": "Category Not Found!",
        "data": null
      }
      ```

---