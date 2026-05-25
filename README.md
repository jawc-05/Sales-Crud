# Sales-Crud
[![pt-BR](https://img.shields.io/badge/lang-pt--BR-green)](README.pt-br.md)


# Sales-Crud

A robust sales management system developed under the **Jakarta EE 10** specification. This project focuses on decoupled architecture, data integrity, and the application of enterprise design patterns for Java environments.

## 🛠 Tech Stack

*   **Runtime:** Jakarta EE 10 (Web Profile).
*   **UI Components:** Jakarta Faces (JSF) + PrimeFaces.
*   **Business Logic:** Jakarta Enterprise Beans (EJB) @Stateless.
*   **Persistence:** Jakarta Persistence (JPA) with Hibernate.
*   **Database:** PostgreSQL 15+.
*   **Application Server:** WildFly / JBoss (Configured via JTA/JNDI).
*   **Build Tool:** Maven.

## 🏗 Architecture & Technical Highlights

*   **Rich Domain Model:** Business logic is encapsulated directly within the entities. The `Sale` class manages its own states and calculations, effectively avoiding the anemic domain model anti-pattern.
*   **Generic DAO Pattern:** Implementation of an abstract persistence layer using Generics, significantly reducing boilerplate code and standardizing I/O operations.
*   **JTA Transaction Management:** Use of stateless EJBs to ensure the atomicity of write operations, automatically managed by the container.
*   **Layered Architecture:** Clear separation between Domain, DAO, Service, and Controller layers, facilitating testability and long-term maintenance.

## 🚀 Key Features

*   **Sales Workflow:** An integrated state machine (`STARTED`, `COMPLETED`, `CANCELLED`) that locks modifications on finalized or cancelled orders.
*   **Dynamic Calculation:** Automatic recalculation of total values triggered by any mutation in the sale's item list.
*   **Data Sanitization:** Input handling and mask processing (CPF/Phone) performed directly in the controller layer prior to persistence.
*   **Reactive UI:** AJAX-based interface for partial component updates, significantly improving user experience and performance.

## ⚙️ Configuration and Deployment

1.  **DataSource:** Configure the connection pool in WildFly pointing to the JNDI: `java:jboss/datasources/PostgresDS`.
2.  **Persistence:** The `persistence.xml` file is set to `update` mode, automatically generating the database schema on the first deploy.
3.  **Build:**
    ```bash
    mvn clean install
4.**Deploy:** Copy the generated .war file from the /target directory to your server's deployment folder.

Developed by: [João Alfredo Cunha](https://github.com/jawc-05)

