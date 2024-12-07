---

# **Dream Shop**

---

## **How to Run**

1. **Clone the repository:**  
   Use the following command to clone the repository:
   ```bash
   git clone https://github.com/fmahadyBD/dream-shop.git
   ```

2. **Set up the database:**  
   Follow these steps to configure your database:
    1. Start the MySQL database server.
    2. The database will be automatically created.
    3. If MySQL is running on a port other than `3306`, update the port in the configuration.
    4. Ensure the correct database username and password are set in the `application.properties` file:
       ```properties
       spring.datasource.url=jdbc:mysql://localhost:3305/shopping-card?createDatabaseIfNotExist=true
       spring.datasource.username=root
       spring.datasource.password=
       ```

3. **Navigate to the project directory:**
   ```bash
   cd dream-shop
   ```

4. **Start the Spring Boot application:**  
   Use Maven to run the application:
   ```bash
   mvn spring-boot:run
   ```

5. **Test the endpoints:**  
   Test the application's endpoints using tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/).

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

## **Product API Documentation**

### Add New Product
**URL:** `http://localhost:8080/admin/api/v1/products/add`  
**Method:** POST

#### Request Body:
```json
{
  "name": "Laptop",
  "price": 1200.00,
  "brand": "Dell",
  "inventory": 50,
  "description": "High-performance Dell Laptop",
  "category": {
    "name": "Electronics"
  }
}
```
> **Note:** If the category with the specified name does not exist in the database, a new category will be created.

#### Error Response:
```json
{
  "message": "Error",
  "data": "Cannot invoke \"com.fahim.shoppingcard.model.Category.getName()\" because the return value of \"com.fahim.shoppingcard.request.AddProductRequest.getCategory()\" is null"
}
```

#### Success Response:
```json
{
  "message": "Added new Product",
  "data": {
    "id": 1,
    "name": "Laptop",
    "price": 1200.00,
    "brand": "Dell",
    "inventory": 50,
    "description": "High-performance Dell Laptop",
    "category": {
      "id": 102,
      "name": "Electronics"
    },
    "images": []
  }
}
```

---

### Get All Products
**URL:** `http://localhost:8080/admin/api/v1/products/all`  
**Method:** GET

#### Response:
```json
{
  "message": "Found!",
  "data": [
    {
      "id": 1,
      "name": "Laptop",
      "price": 1200.00,
      "brand": "Dell",
      "inventory": 50,
      "description": "High-performance Dell Laptop",
      "images": []
    }
  ]
}
```

---

### Get Product By ID
**URL:** `http://localhost:8080/admin/api/v1/products/product/id/{id}`  
**Method:** GET

#### Example:
**URL:** `http://localhost:8080/admin/api/v1/products/product/id/1`  
**Response:**
```json
{
  "message": "Found!",
  "data": {
    "id": 1,
    "name": "Laptop",
    "price": 1200.00,
    "brand": "Dell",
    "inventory": 50,
    "description": "High-performance Dell Laptop",
    "images": []
  }
}
```