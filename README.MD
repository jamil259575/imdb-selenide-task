# IMDb UI Test Task

A UI test task framework built with **Java 17**, **Selenide**, **TestNG**, and **Allure**, designed to automate search and actor profile validation on [IMDb.com](https://www.imdb.com).

---

## Features

-  Page Object Model (POM)
-  Headless browser support (with stealth mode)
-  TestNG test lifecycle hooks
-  Screenshot capture on failure
-  Allure reporting
-  GitHub Actions CI integration
-  Configurable via `.properties` file

---

##  Tech Stack

| Tool         | Usage                            |
|--------------|----------------------------------|
| Java 17      | Programming language              |
| Selenide     | Browser automation (built on Selenium) |
| TestNG       | Test framework                    |
| Allure       | Test reporting                    |
| Gradle       | Build tool                        |
| GitHub Actions | Continuous integration         |

---

##  Project Structure

```
imdb-selenide-test/
├── src/test/java/com/example/
│   ├── pages/         # Page Object classes
│   ├── tests/         # Test and BaseTest classes
│   └── config/        # Config loader
├── src/test/resources/
│   └── config.properties
├── build.gradle
├── testng.xml
├── .github/workflows/test.yml
├── .gitignore
└── README.md
```

---

## Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/your-username/imdb-selenide-test.git
cd imdb-selenide-test
```

### 2. Configure Environment

Edit `src/test/resources/config.properties`:

```properties
baseUrl=https://www.imdb.com
browser=chrome
headless=true
timeout=10000
```

### 3. Run Tests

```bash
./gradlew clean test
```
---
##  Generate Allure Report

```bash
./gradlew allureReport
./gradlew allureServe
```

---

##  CI with GitHub Actions

This project includes a ready-to-use workflow:
```
.github/workflows/test.yml
```

It:
- Runs tests in headless mode
- Generates and uploads Allure report as artifact

---

## Cross-Browser Testing

Project uses **Selenide** with a default configuration for **Chrome**, including custom capabilities like user-agent, resolution, and GPU handling.  
Support for other browsers like **Firefox** and **Edge** can be easily added by extending the browser configuration logic in `BaseTest.java`.

To run tests in different browsers, pass the desired browser as a system property:

```bash
./gradlew clean test -Dbrowser=firefox
```

Also possible to enhance this setup in CI (e.g., GitHub Actions) to perform cross-browser tests automatically by configuring multiple jobs or matrix builds.

##  Screenshots on Failure

Screenshots are automatically attached to Allure reports if a test fails.

---