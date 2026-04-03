package com.electricity.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.electricity.utils.ExtentReportManager;
import com.electricity.utils.LoggerUtil;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * BaseTest — Parent class for all test classes.
 * Manages RestAssured base URI, ExtentReports lifecycle,
 * and logs pass/fail results automatically.
 */
public class BaseTest {

    protected ExtentTest extentTest;

    // ══════════════════════════════════════════════
    // Suite Setup / Teardown
    // ══════════════════════════════════════════════
    @BeforeSuite
    public void suiteSetup() {
        RestAssured.baseURI = "https://electent.spc.softprime.in";
        ExtentReportManager.initReport();
        LoggerUtil.info("═══════════════════════════════════════════════");
        LoggerUtil.info("  Electricity Billing API Automation Started   ");
        LoggerUtil.info("═══════════════════════════════════════════════");
    }

    @AfterSuite
    public void suiteTeardown() {
        ExtentReportManager.flushReport();
        LoggerUtil.info("═══════════════════════════════════════════════");
        LoggerUtil.info("  Test Suite Completed. Report Generated.       ");
        LoggerUtil.info("═══════════════════════════════════════════════");
    }

    // ══════════════════════════════════════════════
    // Test Setup / Teardown
    // ══════════════════════════════════════════════
    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        String testName = method.getName();
        String description = "";
        Test annotation = method.getAnnotation(Test.class);
        if (annotation != null) description = annotation.description();

        extentTest = ExtentReportManager.createTest(testName, description);
        LoggerUtil.info("──────────────────────────────────────────────");
        LoggerUtil.info("▶ Running: " + testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test == null) return;

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                test.log(Status.PASS, "✅ Test PASSED");
                LoggerUtil.pass(result.getName(), "PASSED");
                break;
            case ITestResult.FAILURE:
                test.log(Status.FAIL, "❌ Test FAILED: " + result.getThrowable());
                test.fail(result.getThrowable());
                LoggerUtil.fail(result.getName(), result.getThrowable().getMessage());
                break;
            case ITestResult.SKIP:
                test.log(Status.SKIP, "⚠️  Test SKIPPED");
                LoggerUtil.warn("SKIPPED: " + result.getName());
                break;
        }
    }
}
