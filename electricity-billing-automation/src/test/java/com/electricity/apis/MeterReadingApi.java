package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * MeterReadingApi — All Meter Reading API methods.
 * Covers: Create Meter Reading (TC-3.1.x)
 */
public class MeterReadingApi {

    // ══════════════════════════════════════════════
    // CREATE METER READING APIs
    // ══════════════════════════════════════════════

    /** TC-3.1.1: Valid meter reading — new > old (positive units) */
    public static Response createReadingValid(int employeeId, int houseId, double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.2: New reading lower than previous reading */
    public static Response createReadingLowerThanPrevious(int employeeId, int houseId,
                                                           double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.3: Same reading as previous (zero consumption) */
    public static Response createReadingSameAsPrevious(int employeeId, int houseId,
                                                        double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.4: Missing / empty house_id */
    public static Response createReadingMissingHouseId(int employeeId, double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     "");
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.5: Missing / empty new_reading */
    public static Response createReadingMissingReading(int employeeId, int houseId, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("new_reading",  "");
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.6: Invalid house_id (non-existent house) */
    public static Response createReadingInvalidHouse(int employeeId, int invalidHouseId,
                                                      double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     invalidHouseId);
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.7: House state is not 'active' */
    public static Response createReadingInactiveHouse(int employeeId, int inactiveHouseId,
                                                       double newReading, String readingDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     inactiveHouseId);
        body.put("new_reading",  newReading);
        body.put("reading_date", readingDate);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }

    /** TC-3.1.8: Reading with total_amount instead of reading_date */
    public static Response createReadingWithTotalAmount(int employeeId, int houseId,
                                                         double newReading, double totalAmount) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("house_id",    houseId);
        body.put("new_reading", newReading);
        body.put("total_amount", totalAmount);
        return RequestBuilder.post(ConfigManager.METER_READING_URL, body.toString());
    }
}
