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

| Tool         | Usage                          |
|--------------|--------------------------------|
| Java 17      | Programming language            |
| Selenide     | Browser automation |
| TestNG       | Test framework                  |
| Allure       | Test reporting                  |
| Gradle       | Build tool                      |
| GitHub Actions | Continuous integration       |

---

##  Project Structure

```
imdb-selenide-test/
├── src/test/java/com/example/
│   ├── pages/         # Page Object classes
    ├── dataprovider/         # Data provider object
│   ├── tests/         # Test and BaseTest classes
    ├── utils/         # Utility class
│   └── config/        # Config loader
├── src/test/resources/
│   └── config.properties
    └── testng.xml
├── build.gradle
├── .github/workflows/test.yml
├── .gitignore
└── README.md
```

---

## Getting Started

#### Prerequisites

- Java 17  
- Gradle  
- Chrome & Firefox browsers  
- Browser drivers in your system `PATH`

#### Driver Setup (Linux/Mac)

1. Download:
    - [ChromeDriver](https://sites.google.com/chromium.org/driver/)
    - [GeckoDriver](https://github.com/mozilla/geckodriver/releases)

1. Download:
    - [ChromeDriver](https://sites.google.com/chromium.org/driver/)
    - [GeckoDriver](https://github.com/mozilla/geckodriver/releases)

2. Extract & move:

```bash
tar -xvzf geckodriver-v0.34.0-linux64.tar.gz
sudo mv geckodriver /usr/local/bin/
sudo chmod +x /usr/local/bin/geckodriver
```

Repeat for `chromedriver`.

3. Ensure path:

```bash
# Add to shell profile if not already
export PATH=$PATH:/usr/local/bin
```

4. Verify:

```bash
chromedriver --version
geckodriver --version
```
---
#### Driver Setup Windows

- Download:
    - [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)
    - [GeckoDriver](https://github.com/mozilla/geckodriver/releases)
- Extract and place in a directory like 'C:\WebDriver\bin'
- Add this directory to your system's **Environment Variables > Path**

```bash
set PATH=%PATH%;C:\WebDriver\bin
```
---

### 1. Clone the Repo

```bash
git clone https://github.com/your-username/imdb-selenide-test.git
cd imdb-selenide-test
```

### 2. Configure Environment

Edit `src/test/resources/config.properties`:

```properties
baseUrl=https://www.imdb.com/
browser=chrome
headless=true
timeout=10000
```

### 3. Run Tests

```bash
./gradlew clean test
```
```bash
./gradlew clean test -browser=firefox
```
```bash
./gradlew clean test -browser=chrome
```
For headed mode pass parameter:
```bash
./gradlew clean test -Dheadless=false
```
---
##  Generate Allure Report

```bash
./gradlew allureReport
./gradlew allureServe
```

---

##  CI with GitHub Actions

This project includes a workflow:
```
.github/workflows/test.yml
```

It:
- Runs tests in headless mode
- Generates and uploads Allure report as artifact

---

## Cross-Browser Testing

Project has configuration for **Chrome** and **Mozilla Firefox**, including custom capabilities like user-agent, resolution, and GPU handling.  
Support for other browsers can be easily added by extending the browser configuration logic in `BaseTest.java`. Tests run in parallel in CI.
YAML file can be updated based on added browser configuration.
##  Allure Reports with BDD-Style Steps

This project uses [Allure Report](https://docs.qameta.io/allure/) with BDD-style steps for rich, human-readable test reporting.

Each test step is annotated using `@Step` (from Allure) to give detailed execution trace in the report, including:
- Setup and teardown
- Test data parameters
- Step durations
- Visual breakdown of execution hierarchy


To generate and open the Allure report:

```bash
./gradlew clean test
allure serve build/allure-results
```