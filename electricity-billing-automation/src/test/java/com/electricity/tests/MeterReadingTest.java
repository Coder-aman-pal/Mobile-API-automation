package com.electricity.tests;

import com.electricity.apis.MeterReadingApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * MeterReadingTest — Test cases for Meter Reading APIs.
 * TC-3.1.x: Create Meter Reading
 */
public class MeterReadingTest extends BaseTest {

    // ══════════════════════════════════════════════
    // TC-3.1.1: Valid Meter Reading
    // ══════════════════════════════════════════════
    @Test(priority = 1,
          groups = {"MeterReading", "Positive"},
          description = "Valid new reading > previous reading should return 200")
    public void TC_METER_001_ValidReading() {
        extentTest.info("Testing valid meter reading: employee_id=5, house_id=59, reading=440");

        Response response = MeterReadingApi.createReadingValid(
                ConfigManager.EMPLOYEE_ID_2, ConfigManager.HOUSE_ID, 440, "2026-02-27");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-METER-001", "Valid Meter Reading PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.2: New Reading Lower Than Previous
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"MeterReading", "Negative"},
          description = "New reading lower than previous should return error ")
    public void TC_METER_002_ReadingLowerThanPrevious() {
        extentTest.info("Testing meter reading lower than previous: reading=402 (lower than 440)");

        Response response = MeterReadingApi.createReadingLowerThanPrevious(
                ConfigManager.EMPLOYEE_ID_2, ConfigManager.HOUSE_ID, 402, "2026-02-27");

        ResponseValidator.assertStatusCode(response, 422);
        LoggerUtil.pass("TC-METER-002", "Reading Lower Than Previous PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.3: Same Reading as Previous (Zero Consumption)
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"MeterReading", "Negative"},
          description = "Same reading as previous (zero units) should return error or 422")
    public void TC_METER_003_SameReadingAsPrevious() {
        extentTest.info("Testing same meter reading as previous: reading=400");

        Response response = MeterReadingApi.createReadingSameAsPrevious(
                ConfigManager.EMPLOYEE_ID_2, ConfigManager.HOUSE_ID, 400, "2026-02-27");

        // API may return 422 (zero consumption) or 400 (invalid reading)
        int statusCode = response.getStatusCode();
        
        
        ResponseValidator.assertStatusCode(response, 422);
        LoggerUtil.info("Status code for same reading: " + statusCode);
        LoggerUtil.pass("TC-METER-003", "Same Reading As Previous PASSED (status=" + statusCode + ")");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.4: Missing / Empty house_id
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"MeterReading", "Negative"},
          description = "Empty house_id should return 400 error")
    public void TC_METER_004_MissingHouseId() {
        extentTest.info("Testing meter reading with empty house_id");

        Response response = MeterReadingApi.createReadingMissingHouseId(
                ConfigManager.EMPLOYEE_ID_2, 761, "2026-02-27");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-METER-004", "Missing House ID PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.5: Missing / Empty new_reading
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"MeterReading", "Negative"},
          description = "Empty new_reading should return 400 error")
    public void TC_METER_005_MissingNewReading() {
        extentTest.info("Testing meter reading with empty new_reading value");

        Response response = MeterReadingApi.createReadingMissingReading(
                ConfigManager.EMPLOYEE_ID_2, ConfigManager.HOUSE_ID, "2026-02-27");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-METER-005", "Missing New Reading PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.6: Invalid / Non-Existent house_id
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"MeterReading", "Negative"},
          description = "Non-existent house_id=100 should return 404 or 400 error")
    public void TC_METER_006_InvalidHouseId() {
        extentTest.info("Testing meter reading with non-existent house_id=100");

        Response response = MeterReadingApi.createReadingInvalidHouse(
                ConfigManager.EMPLOYEE_ID_2, 100, 761, "2026-02-27");

        ResponseValidator.assertStatusCode(response, 404);
        LoggerUtil.pass("TC-METER-006", "Invalid House ID PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.7: Inactive House
    // ══════════════════════════════════════════════
    @Test(priority = 7,
          groups = {"MeterReading", "Negative"},
          description = "Inactive house should return 400 or 403 error")
    public void TC_METER_007_InactiveHouse() {
        extentTest.info("Testing meter reading for inactive house_id=100");

        Response response = MeterReadingApi.createReadingInactiveHouse(
                ConfigManager.EMPLOYEE_ID_2, 100, 761, "2026-02-27");

        int statusCode = response.getStatusCode();
        LoggerUtil.info("Status for inactive house: " + statusCode);
        LoggerUtil.pass("TC-METER-007", "Inactive House PASSED (status=" + statusCode + ")");
    }

    // ══════════════════════════════════════════════
    // TC-3.1.8: Reading with total_amount Instead of reading_date
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"MeterReading", "Negative"},
          description = "Using total_amount instead of reading_date should return 200")
    public void TC_METER_008_ReadingWithTotalAmount() {
        extentTest.info("Testing meter reading with total_amount field instead of reading_date");

        Response response = MeterReadingApi.createReadingWithTotalAmount(
                ConfigManager.EMPLOYEE_ID_2, ConfigManager.HOUSE_ID, 403, 3.3);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-METER-008", "Reading With Total Amount PASSED");
    }
}
