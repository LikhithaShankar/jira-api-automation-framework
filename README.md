# LS – BDD API Automation Framework (Cucumber + REST Assured + Java)

This repository contains an end-to-end API automation framework built using:

- Java 17  
- REST Assured  
- Cucumber BDD  
- JUnit / TestNG  
- Maven  
- Page Object + API Utils design patterns  

The framework is designed for testing REST APIs (Jira APIs in the current implementation) using clean, maintainable, and reusable components.

---

## 📂 Project Structure

```
src
 ├── main
 └── test
      ├── stepdefinitions
      ├── utils
      ├── pojo
      ├── runners
      └── resources
             ├──  features
             ├──  testdata
             ├──   Schemas
             ├──   config.properties
```

---

## 🚀 How to Run Tests

### ▶ Run Full Test Suite

```
mvn clean test
```

### ▶ Run Specific Tagged Tests

```
mvn test -Dcucumber.filter.tags="@smoke"
```

---

## 🛠 Technologies Used

| Component  | Description |
|-----------------------|----------------------------- |
| Java 17               | Programming Language         |
| REST Assured          | API Testing Library          |
| Cucumber BDD          | Scenarios & Step Definitions |
| Maven                 | Dependency & Test Runner     |
| JSON Schema Validator | Response Validation          |

---

## 🔧 Configuration

All environment configurations are stored in:

```
src/test/resources/Config.properties
```

Example:

```
jira.base.url = https://your-domain.atlassian.net
jira.user     = your-email
jira.token    = your-api-token
```

---

## 📝 Sample Cucumber Feature

```gherkin
Feature: Jira Issue Creation

  Scenario: Create a Jira Issue
    Given I have an authenticated Jira request specification
    When I create a new issue with summary "Test Summary" and description "Demo"
    Then the API status code should be 201
    And I remember the issue key
```

---

## 🏗 Framework Highlights

- Reusable APIUtils for request specifications  
- Centralized request body builders  
- Data-driven scenarios using Scenario Outline  
- JSON schema validation  
- End-to-end Jira workflows (create → update → comment → transition)  
- Modular and scalable folder structure  

---

## 📌 How to Extend the Framework

You can extend this framework by:

- Adding new feature files under `/features`  
- Adding new step definitions under `/stepdefinitions`  
- Creating new request/response POJOs under `/model`  
- Adding reusable methods inside `APIUtils`  

---

## 🧑‍💻 Author

**Likhitha Shankar**  
