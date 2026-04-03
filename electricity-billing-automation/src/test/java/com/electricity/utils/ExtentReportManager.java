package com.electricity.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager — Manages ExtentReports lifecycle.
 * Creates and flushes the HTML report after each test run.
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    /**
     * Initializes the ExtentReports instance.
     * Call once in @BeforeSuite.
     */
    public static void initReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ConfigManager.REPORT_PATH);
        sparkReporter.config().setDocumentTitle("Electricity Billing API — Test Report");
        sparkReporter.config().setReportName("API Automation Results");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project",     "Electricity Meter Billing API");
        extent.setSystemInfo("Base URL",    ConfigManager.BASE_URL);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester",      System.getProperty("user.name"));

        LoggerUtil.info("📊 ExtentReport initialized → " + ConfigManager.REPORT_PATH);
    }

    /**
     * Creates a new test entry in the report.
     * Call in @BeforeMethod.
     */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThread.set(test);
        return test;
    }

    /**
     * Returns the current thread's ExtentTest.
     */
    public static ExtentTest getTest() {
        return testThread.get();
    }

    /**
     * Flushes and saves the report to disk.
     * Call in @AfterSuite.
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            LoggerUtil.info("✅ ExtentReport saved → " + ConfigManager.REPORT_PATH);
        }
    }
}
