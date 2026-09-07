# TxAutomate Web Automation Framework

A lightweight, robust, and clean BDD-based Web Automation Testing Framework built with **Java 11**, **Selenium WebDriver**, **Cucumber JVM**, **TestNG**, and **ExtentReports**. 

This framework is specifically tailored for **Web Application Testing** and **Automated HTML Reporting**.

---

## 🚀 Key Features

- **Web UI Automation**: Supports local browser execution (Chrome, Firefox) managed automatically via `WebDriverManager`, as well as cloud execution (SauceLabs/Remote grid).
- **BDD with Cucumber**: Gherkin syntax feature files paired with clean step definitions.
- **TestNG Integration**: Flexible suite configuration via `testng.xml` and standard TestNG test lifecycle hooks.
- **Rich HTML Reporting**: Integrated with **ExtentReports** to generate detailed HTML test execution reports with automatic Base64 screenshot attachments for failed tests.
- **Data-Driven Testing**: Configurable test data reading from Excel (`.xlsx`/`.xls`) and automatic Excel-to-CSV runtime conversion.
- **Page Object Model (POM)**: Structured page objects for maintainability and reusable web element interactions.
- **Logging**: Configured with Log4j2 for structured debug, info, and error logs (`ExecutionReports/Logs`).

---

## 📁 Project Structure

```text
Tx_WWP/
├── lib/                             # Local framework JARs
├── src/
│   ├── main/resources/
│   │   ├── Config/
│   │   │   ├── config.properties    # Central configuration (URLs, timeouts, report paths)
│   │   │   ├── extent-config.xml    # ExtentReports theme & visual styling
│   │   │   └── mail.properties      # Email configuration settings
│   │   └── log4j2.xml               # Log4j2 logging configuration
│   └── test/
│       ├── java/
│       │   ├── constants/web/       # Framework constants (Browser, Environment, Base URL)
│       │   ├── pages/web/           # Page Object Model classes
│       │   ├── stepDefinitions/     # Cucumber Step Definitions & Hooks
│       │   │   ├── Hooks.java       # Scenario setup/teardown & failure screenshot capture
│       │   │   └── RunCukesTest.java# TestNG Cucumber Test Runner
│       │   └── utilities/           # Utility classes
│       │       ├── CommonSettings.java   # Execution configuration model
│       │       ├── ConfigReader.java     # Properties file loader
│       │       ├── DriverUtil.java       # WebDriver initialization & manager
│       │       ├── ExcelDataUtil.java    # Excel data reader & control sheet parser
│       │       ├── ExcelToCSVConverter.java# Excel sheet to CSV converter
│       │       ├── GlobalUtil.java       # Global state & WebDriver holder
│       │       ├── HTMLReportUtil.java   # ExtentReports helper & screenshot utilities
│       │       ├── JsonConversion.java   # Configuration JSON helper
│       │       ├── KeywordUtil.java      # Reusable Selenium Web action keywords
│       │       └── LogUtil.java          # Thread-safe Log4j2 logger wrapper
│       └── resources/
│           ├── ExcelFiles/          # Automation control sheets
│           ├── CsvFiles/            # Generated CSV data files
│           ├── JsonData/            # Generated JSON config files
│           ├── features/            # Cucumber Gherkin feature files
│           └── testData/            # Test data Excel files
├── ExecutionReports/                # Generated reports & logs (Ignored by Git)
│   ├── HTMLReports/                 # Primary Extent HTML report
│   ├── HTMLReportsBackup/           # Backed up execution reports
│   ├── FailedScreenshots/           # Screenshots captured on test failures
│   └── Logs/                        # Execution logs
├── testng.xml                       # TestNG runner configuration
└── pom.xml                          # Maven build configuration & dependencies
```

---

## 🛠️ Prerequisites

1. **Java Development Kit (JDK)**: Version 11 or higher.
2. **Apache Maven**: Version 3.6.x or higher.
3. **Web Browsers**: Google Chrome / Mozilla Firefox installed locally.

---

## ⚙️ Configuration Setup

Central framework configurations are managed in `src/main/resources/Config/config.properties`:

- `BASE_URL`: Target web application URL.
- `defaultBrowser`: Execution browser (`chrome` or `firefox`).
- `defaultExecutionEnvironment`: Execution mode (`Local` or `Remote`).
- `explicit_timeout` & `implicitlyWait`: Selenium wait durations in seconds.
- `AutomationControlExcelPath`: Path to the execution control sheet (`AutomationControlSheet.xlsx`).
- `testDataExcelPath`: Path to the test data file (`TestData.xlsx`).

---

## 🧪 Running Tests

### 1. Compile the Framework
To verify dependencies and compile the source code:
```bash
mvn test-compile
```

### 2. Run Tests via Maven
Execute tests tagged with `@Web` using Maven:
```bash
mvn clean test
```

### 3. Run Tests via TestNG
Execute the test suite using TestNG:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reports & Logs

After test execution, reports and logs are automatically generated in the `ExecutionReports/` directory:

- **Extent HTML Report**: `ExecutionReports/HTMLReports/extentReport.html`
- **Cucumber HTML Report**: `target/cucumber-html-report.html`
- **Failure Screenshots**: `ExecutionReports/FailedScreenshots/` (embedded directly into Extent HTML reports as Base64 images)
- **Execution Logs**: `ExecutionReports/Logs/appLog.txt` and `ExecutionReports/Logs/errorLog.txt`

---

## 🏷️ Cucumber Tagging System

Tests are organized using Cucumber tags in feature files:

- `@Web`: Denotes Web UI test scenarios.

To execute specific tagged scenarios, update the `tags` parameter in [`RunCukesTest.java`](file:///c:/Users/gurwinder.singh1/Downloads/Tx_WWP_Latestframework/Tx_WWP_Latestframework/Tx_WWP/src/test/java/stepDefinitions/RunCukesTest.java):
```java
@CucumberOptions(
    features = "classpath:features",
    plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber.json"},
    tags = "@Web",
    monochrome = true,
    dryRun = false
)
```
