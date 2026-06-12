# E-Commerce Service - Monolithic Architecture

A Spring Boot e-commerce application built with a legacy monolithic (layered) architecture that can be easily migrated to microservices in the future.

## Architecture Overview

This project follows a traditional **layered architecture pattern**:

```
┌─────────────────────────────────────┐
│         REST Controllers            │
├─────────────────────────────────────┤
│          Service Layer              │
├─────────────────────────────────────┤
│         Repository Layer            │
├─────────────────────────────────────┤
│          Entity Models              │
├─────────────────────────────────────┤
│         H2 Database                 │
└─────────────────────────────────────┘
```

## Project Structure

```
src/main/java/com/ecommerce/
├── controller/          # REST Controllers
│   ├── UserController
│   ├── ProductController
│   └── OrderController
├── service/            # Business Logic Layer
│   ├── UserService
│   ├── ProductService
│   └── OrderService
├── repository/         # Data Access Layer
│   ├── UserRepository
│   ├── ProductRepository
│   └── OrderRepository
├── model/              # Entity Models
│   ├── User
│   ├── Product
│   ├── Order
│   └── OrderItem
├── dto/                # Data Transfer Objects
│   ├── CreateOrderRequest
│   └── OrderResponse
└── ECommerceApplication.java  # Main Application
```

## Core Features

### 1. User Management
- Create new users with validation
- Retrieve users by ID, email, or username
- Update user profiles and addresses
- Delete user accounts

### 2. Product Catalog
- Add products with descriptions and pricing
- Manage product inventory/stock
- Categorize products
- Mark products as active/inactive
- Search products by category

### 3. Order Management
- Create orders with multiple items
- Automatic stock deduction
- Order status tracking (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, REFUNDED)
- Order cancellation with stock restoration
- Track order details and timestamps

## Technology Stack

- **Framework**: Spring Boot 3.1.5
- **Language**: Java 17
- **Database**: H2 (In-Memory - Development)
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Validation**: Jakarta Validation API
- **Additional**: Lombok (for boilerplate reduction)

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Installation

1. Navigate to the project directory:
```bash
cd /Users/arshd/Documents/Erick/SpringbootApplication/untitled
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### H2 Database Console (Development)
Access the H2 database console:
```
http://localhost:8080/api/h2-console
```

## API Endpoints

### User Endpoints
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/email/{email}` - Get user by email
- `GET /api/users` - Get all users
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Product Endpoints
- `POST /api/products` - Create a new product
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/sku/{sku}` - Get product by SKU
- `GET /api/products` - Get all products (with activeOnly filter)
- `GET /api/products/category/{category}` - Get products by category
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Order Endpoints
- `POST /api/orders` - Create a new order
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders` - Get all orders
- `GET /api/orders/user/{userId}` - Get orders by user
- `GET /api/orders/status/{status}` - Get orders by status
- `PUT /api/orders/{id}/status/{status}` - Update order status
- `PUT /api/orders/{id}/cancel` - Cancel an order

## Example Usage

### Create a User
```json
POST /api/users
{
  "username": "john.doe",
  "email": "john@example.com",
  "password": "secure123",
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "phoneNumber": "555-1234"
}
```

### Create a Product
```json
POST /api/products
{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "stockQuantity": 50,
  "sku": "LAP-001",
  "category": "Electronics",
  "imageUrl": "https://example.com/laptop.jpg",
  "isActive": true
}
```

### Create an Order
```json
POST /api/orders
{
  "userId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ],
  "shippingAddress": "456 Oak Ave, New York, NY 10002",
  "billingAddress": "456 Oak Ave, New York, NY 10002"
}
```

## Future Microservices Migration

This monolithic application has been designed with microservices migration in mind:

### Suggested Service Boundaries
1. **User Service** - User management and authentication
2. **Product Service** - Product catalog and inventory
3. **Order Service** - Order processing and fulfillment
4. **Payment Service** - Payment processing
5. **Notification Service** - Email/SMS notifications

### Migration Steps
1. Extract each service's repository, model, and service layers
2. Create independent Spring Boot applications
3. Implement inter-service communication (REST/gRPC/Message Queue)
4. Set up API Gateway (Zuul, Spring Cloud Gateway)
5. Implement service discovery (Eureka, Consul)
6. Add distributed tracing and monitoring

## Configuration

Edit `src/main/resources/application.properties` to modify:
- Server port
- Database connection
- JPA/Hibernate settings
- H2 console access

## Error Handling

The application includes comprehensive error handling for:
- Resource not found (404)
- Validation errors (400)
- Business logic errors (400)
- Server errors (500)

## Future Enhancements

- [ ] Authentication & Authorization (Spring Security)
- [ ] Payment gateway integration
- [ ] Email notifications
- [ ] Search functionality (Elasticsearch)
- [ ] Caching layer (Redis)
- [ ] API documentation (Swagger/OpenAPI)
- [ ] Unit & Integration tests
- [ ] CI/CD pipeline

## License

This project is for educational purposes.

