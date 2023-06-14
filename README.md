# Sprint Boot API Project #

This project build GET and POST API using SpringBoot to create and fetch weather information of a city using Redis cache based on basic authentication.
***

## Project Tools Used
- **Programming Language:** Java (JDK 17)
- **Framework and Tools Used:** SpringBoot (Version 2.3.1), Redis, Maven
- **IDE:** Intellij Idea

## Project Structure

1. In our */src/main/java* folder:
 - We have *api* package which contains the api controllers
 - We have *repository* package wheich we use to store, read and initiate our data
 - We have *utils* package where we have put our *Basic Authentication* and *Redis* configuration classes
2. Unit tests are written in */src/main/test* folder

## Prerequisites

- You need to have the following installations in your machine:
    - JDK
    - Maven
    - Redis Server running on port: 6379
    - Any IDE of your choice that supports Java
- For windows machine, you will need the following added to your system environment variables:
    - JDK
    - Gradle

## API Structure

### POST Weather API

- Endpoint: /weather
- Method: POST
- Supported Media Type: Json
- Request Body: 
    - city: String values berlin/munich/hamburg
    - date: Date in format dd/MM/yyyy
    - temperature: any numeric value
- Response Body: 
   - id
   - city
   - date
   - temperature

### GET Weather API

- Endpoint: /weather
- Method: GET
- Request Param: 
    - city: String values berlin/munich/hamburg
    - date: Date in format dd/MM/yyyy
- Response Body: Array containing objects of:
   - id
   - city
   - date
   - temperature

### Error API

- Endpoint: /error
- Method: GET
- Response code is always *500 Internal Server Error*

## Project Import
1. Clone the project from GitHub or download the project and unzip it
2. Open your IDE and import the project as a *Maven* project

## How to Run
- You can run the project from your IDE by following the steps:
    1. Open the Java class **APIApplication.java** from the path: *src/main/java/com/assignment/*
    2. Run this class
- You can run the project from command prompt or terminal by executing the following command from your *{project_root}*:
    > mvn spring-boot:run

## Testing the APIs

- After the project is initiated it will be running on port 8080
- You can use Postman to run the APIs with basic authentication using username: *user* and password: *password*
- Using terminal, you can test the APIs with curl command. A few examples are given below:
  1. A success case with POST Weather API:
    > curl -d '{"city":"berlin","date":"25/12/2023","temperature":-5.5}' -H 'Authorization: Basic dXNlcjpwYXNzd29yZA==' -H 'Content-Type: application/json' http://localhost:8080/weather
  2. 401 unauthorized case with POST Weather API:
    > curl -d '{"city":"berlin","date":"23/12/2023","temperature":30}' -H 'Content-Type: application/json' http://localhost:8080/weather
  3. 415 media type not supported case with POST Weather API:
