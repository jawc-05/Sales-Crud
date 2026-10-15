[![pt-BR](https://img.shields.io/badge/lang-pt--BR-green)](README.pt-br.md)

# Sales Management System

A robust sales and operations management system developed under the Jakarta EE 10 (Web Profile) specification.

This project simulates a corporate environment focused on data integrity, featuring a monolithic architecture strongly decoupled into layers, a reactive web interface, and relational persistence guaranteed by container-managed transactions.

The main proposal is to demonstrate best practices in corporate software engineering in Java environments, including the application of design patterns (such as Rich Domain Model and Generic DAO), separation of concerns, database transactionality, and user interface componentization.

## System Architecture

The application follows an architecture structured in well-defined logical layers, ensuring internal decoupling and facilitating testability and maintenance:

### 1. View / Controller

Responsible for user interaction:

- Reactive web interface built with Jakarta Faces (JSF) and PrimeFaces;
- Partial update of visual components via AJAX calls;
- Input handling, mask application (CPF/Phone), and data sanitization before sending to the backend;
- Direct communication with the service layer.

### 2. Service

Responsible for business orchestration:

- Business logic and flow coordination;
- Automatic transactional control managed by the server via Jakarta Enterprise Beans (EJB `@Stateless`) and JTA;
- Guarantee of security and atomicity for read and write operations.

### 3. Domain

Responsible for the core and rules of the application:

- Business entities based on the *Rich Domain Model* pattern;
- Autonomous management of the workflow and sale state machine (`STARTED`, `COMPLETED`, `CANCELLED`);
- Encapsulated dynamic calculations (such as automatic total recalculation based on the mutation of the item list).

### 4. Data Access

Responsible for persistence and I/O:

- Database operations using Jakarta Persistence (JPA) and Hibernate;
- Implementation of the *Generic DAO* pattern using Java Generics;
- Significant reduction of *boilerplate code* and standardization of PostgreSQL queries.

## Main Flow

The system's operational flow during an order's lifecycle follows this logic:

1. The user accesses the web interface and enters the customer and new sale data;
2. The Controller layer sanitizes the information and forwards the request to the Service;
3. The Service creates the entity, instantiating its initial state (`STARTED`);
4. Upon adding products, the entity itself recalculates totals dynamically;
5. The user confirms the operation, and the sale is finalized, evolving to the `COMPLETED` state;
6. Future modifications to this sale are architecturally blocked by the state machine;
7. The JTA transaction ensures secure persistence of all affected tables in PostgreSQL via Generic DAO;
8. The interface updates the feedback to the user reactively and asynchronously.

## Enterprise Design Patterns

The project stands out for distancing itself from the "anemic model" anti-pattern and for its efficiency in data access.

**Rich Domain Model and State Machine:**
The `Sale` class is responsible for managing itself. The logic is not scattered across superficial services; if an item is added or removed, the entity takes charge of maintaining financial consistency. The workflow prevents logical flaws, such as altering items in an already canceled sale.

**Generic DAO Pattern:**
Abstracting database operations through Generics creates a solid read/write foundation, avoiding the creation of dozens of repetitive classes (CRUD) and centralizing the maintenance of Hibernate queries and statements.

## Infrastructure and Deployment

The project's execution is based on corporate Java application servers.

Infrastructure and configuration require:

- WildFly / JBoss Application Server;
- Connection pool (DataSource) previously configured via JNDI;
- Active PostgreSQL 15+ instance.

The system leverages `persistence.xml` configured for `update`, automatically structuring the tables in the database during the first deployment.

## Technologies Used

- Java / Jakarta EE 10 (Web Profile);
- Jakarta Faces (JSF);
- PrimeFaces;
- Jakarta Enterprise Beans (EJB);
- Jakarta Persistence (JPA) / Hibernate;
- JTA (Java Transaction API);
- JNDI;
- PostgreSQL 15+;
- WildFly / JBoss;
- Maven.

## How to Run Locally

### Prerequisites

- Git;
- Java 17 or higher;
- Apache Maven;
- PostgreSQL 15+;
- WildFly Server (configured and running locally).

### Configure Database on Server

In the WildFly administration panel, configure a connection pool pointing to your PostgreSQL with the JNDI name expected by the system:

```text
java:jboss/datasources/PostgresDS
```

### Clone the project

```bash
git clone https://github.com/jawc-05/Sales-Crud.git
cd Sales-Crud
```

### Compile and package

```bash
mvn clean install
```

### Deploy

1. Locate the generated `.war` file in the project's `target/` folder.
2. Copy this file to your WildFly server's `deployments` directory.
3. Access the application on the server's default web port (usually `http://localhost:8080/war-name`).

## Possible Improvements

- Addition of unit and integration tests with JUnit and Mockito;
- Complete containerization with Docker Compose (simultaneous WildFly + PostgreSQL);
- Creation of a RESTful API layer with JAX-RS for mobile integration;
- Corporate transaction and log monitoring.

## Author

Developed by **João Alfredo Williges Cunha**.

Project focused on demonstrating layered architecture, software engineering, and corporate backend development with the Java ecosystem.

## 📝 License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.
