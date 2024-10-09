# API Automation for Verifying Todos Completion in FanCode City

## Overview
This project automates the validation that all users in the city identified as "FanCode" (determined by latitude and longitude) have completed more than 50% of their todos.

## Prerequisites
- Java 11 or higher
- Maven

## How to Run the Project

1. Clone the repository:
git clone <repository-url>

2. Navigate to the project directory:
cd <project-directory>

3. Run the tests using Maven:

The test will fetch user and todo data from `http://jsonplaceholder.typicode.com` and validate that users from FanCode city have completed more than 50% of their tasks.

## Technologies Used
- Java
- RestAssured (for API interactions)
- JUnit 5 (for testing)
