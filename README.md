# Search Word Service

serach-word-service

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.

### Prerequisites

Things you need to get the project up and running on a local machine:

* Java 11
* Maven latest
* Docker Latest version

### Installing

Follow the below steps to build the application:


Step 1. Build the jar file using maven
  ```
    mvn clean install
  ```

Step 2. Build the docker image of the application by executing below command:
  ```
  docker build -f Dockerfile -t search-word-service .
  ```

### Running the application

* Running the jar from command line (if using maven to build the jar file)
  ```
  java -jar -Dspring.profiles.active=default target/search-word-service-0.0.1-SNAPSHOT.jar
  ```
  or
  ```
  mvn spring-boot:run
  ```
  or
  ```
  docker run -p 8080:8080 searcj-word-service
  ```


#### Swagger Endpoint

* Open 'http://localhost:8080/swagger-ui.html' in the web browser to open swagger to check REST apis.

#### REST services

* REST services can be accessed on: http://localhost:8080

#### Actuators

* Can be accessed on: http://localhost:8080/actuator

#### Postman Testing
* Sample package included for testing: IKEA_Warehouse_Inventory_Service.postman_collection