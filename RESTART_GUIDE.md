# Project Restart Guide

This document provides instructions on how to restart the E-Commerce Service project components.

## 1. Application Restart (Maven)

To restart the Spring Boot application locally:

1.  **Stop the running application** (usually by pressing `Ctrl+C` in the terminal where it's running).
2.  **Clean and build the project**:
    ```bash
    mvn clean install
    ```
3.  **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

## 2. Infrastructure Restart (Docker Compose)

The project uses Kafka and Zookeeper for messaging. To restart these services:

1.  **Stop and remove containers**:
    ```bash
    docker-compose down
    ```
2.  **Start services in background**:
    ```bash
    docker-compose up -d
    ```
3.  **Verify services**:
    ```bash
    docker-compose ps
    ```
    - Kafka UI is available at: `http://localhost:8081`

## 3. Kubernetes Deployment Restart

If the project is deployed on Kubernetes:

1.  **Restart the application deployment**:
    ```bash
    kubectl rollout restart deployment ecommerce-service
    ```
2.  **Restart Kafka/Zookeeper (if deployed via manifests)**:
    ```bash
    kubectl delete -f k8s/kafka-deployment.yaml
    kubectl apply -f k8s/kafka-deployment.yaml
    ```
3.  **Verify Pods**:
    ```bash
    kubectl get pods
    ```

## 4. Troubleshooting

- **Port Conflicts**: Ensure ports 8080 (App), 9092 (Kafka), and 2181 (Zookeeper) are not in use.
- **Database**: The application uses an in-memory H2 database by default. Data is reset on every application restart.
