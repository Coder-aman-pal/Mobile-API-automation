# ⚡ Electricity Meter Billing — API Automation Framework

## 🏗️ Project Structure

```
electricity-billing-automation/
├── src/test/java/com/electricity/
│   ├── base/
│   │   └── BaseTest.java               ← Setup, ExtentReports lifecycle
│   ├── apis/
│   │   ├── AuthApi.java                ← Login + Change Password methods
│   │   ├── CollectorApi.java           ← Get/Search Customers methods
│   │   ├── MeterReadingApi.java        ← Meter Reading methods
│   │   ├── BillingApi.java             ← Generate/Download Bill methods
│   │   ├── PaymentApi.java             ← Collect Payment + Summary methods
│   │   └── DiscountApi.java            ← Discount Reasons + Approvers methods
│   ├── tests/
│   │   ├── AuthTest.java               ← TC-1.1.x + TC-1.2.x
│   │   ├── CollectorTest.java          ← TC-2.1.x + TC-2.2.x
│   │   ├── MeterReadingTest.java       ← TC-3.1.x
│   │   ├── BillingTest.java            ← TC-4.1.x + TC-4.2.x
│   │   ├── PaymentTest.java            ← TC-5.1.x + TC-5.2.x
│   │   ├── DiscountTest.java           ← TC-6.1.x + TC-6.2.x
│   │   └── DataDrivenLoginTest.java    ← JSON-based data-driven tests
│   └── utils/
│       ├── ConfigManager.java          ← Base URL, endpoints, constants
│       ├── TokenManager.java           ← Bearer token management
│       ├── RequestBuilder.java         ← Reusable RestAssured builder
│       ├── ResponseValidator.java      ← Reusable assertion helpers
│       ├── ExtentReportManager.java    ← HTML report management
│       ├── JsonDataProvider.java       ← JSON file reader for data-driven
│       └── LoggerUtil.java             ← Console logging
├── src/test/resources/testdata/
│   ├── login_data.json                 ← Login test data
│   └── meter_reading_data.json         ← Meter reading test data
├── test-output/reports/
│   └── ElectricityAPI_Report.html      ← Generated after mvn test
├── testng.xml                          ← TestNG suite configuration
└── pom.xml                             ← Maven dependencies
```

---

## 📦 Tech Stack

| Tool            | Version  | Purpose                  |
|----------------|----------|--------------------------|
| Java            | 11+      | Programming language     |
| RestAssured     | 5.4.0    | API testing library      |
| TestNG          | 7.9.0    | Test framework           |
| ExtentReports   | 5.1.1    | HTML test reporting      |
| Jackson         | 2.17.0   | JSON data-driven testing |
| Apache POI      | 5.2.5    | Excel data-driven testing|
| Maven           | 3.x      | Build tool               |

---

## ▶️ How to Run

### Run All Tests
```bash
mvn test
```

### Run Specific Group
```bash
mvn test -Dgroups="Auth"
mvn test -Dgroups="Billing"
mvn test -Dgroups="Payment"
mvn test -Dgroups="Positive"
mvn test -Dgroups="Negative"
```

### Run Single Test Class
```bash
mvn test -Dtest=AuthTest
mvn test -Dtest=BillingTest
```

---

## 📊 Test Report

After running `mvn test`, the HTML report is generated at:
```
test-output/reports/ElectricityAPI_Report.html
```
Open this file in any browser.

---

## 📋 Test Cases Coverage

| Module        | Total TCs | Positive | Negative |
|--------------|-----------|----------|----------|
| Auth (Login) | 8         | 1        | 7        |
| Auth (Change Password) | 7 | 1     | 6        |
| Collector    | 12        | 7        | 5        |
| Meter Reading| 8         | 1        | 7        |
| Billing      | 9         | 4        | 5        |
| Payment      | 10        | 4        | 6        |
| Discount     | 7         | 5        | 2        |
| **Total**    | **61**    | **23**   | **38**   |

---

## ⚙️ Configuration

Edit `ConfigManager.java` to update:
```java
public static final String BASE_URL      = "https://electent.spc.softprime.in";
public static final String BEARER_TOKEN  = "your-new-token-here";
public static final int    EMPLOYEE_ID   = 12;
```

---

## 📁 Add More Test Data

Add JSON files in `src/test/resources/testdata/` and use:
```java
@DataProvider(name = "myData")
public Object[][] myData() {
    return JsonDataProvider.getDataFromJson("my_data.json");
}
```
