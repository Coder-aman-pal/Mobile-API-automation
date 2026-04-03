package com.electricity.utils;

import io.restassured.response.Response;
import org.testng.Assert;

/**
 * ResponseValidator — Reusable response assertion helper.
 * Validates status codes, JSON fields, and error messages.
 */
public class ResponseValidator {

    /**
     * Assert HTTP status code matches expected.
     */
    public static void assertStatusCode(Response response, int expectedStatus) {
        int actual = response.getStatusCode();
        Assert.assertEquals(actual, expectedStatus,
                "❌ Status code mismatch! Expected: " + expectedStatus + ", Got: " + actual);
    }

    /**
     * Assert a JSON field equals expected value (String).
     */
    public static void assertField(Response response, String jsonPath, String expectedValue) {
        String actual = response.jsonPath().getString(jsonPath);
        Assert.assertEquals(actual, expectedValue,
                "❌ Field [" + jsonPath + "] mismatch! Expected: " + expectedValue + ", Got: " + actual);
    }

    /**
     * Assert a JSON field equals expected value (int).
     */
    public static void assertField(Response response, String jsonPath, int expectedValue) {
        int actual = response.jsonPath().getInt(jsonPath);
        Assert.assertEquals(actual, expectedValue,
                "❌ Field [" + jsonPath + "] mismatch! Expected: " + expectedValue + ", Got: " + actual);
    }

    /**
     * Assert a JSON field is NOT null and NOT empty.
     */
    public static void assertNotNull(Response response, String jsonPath) {
        String actual = response.jsonPath().getString(jsonPath);
        Assert.assertNotNull(actual, "❌ Field [" + jsonPath + "] is null!");
        Assert.assertFalse(actual.isEmpty(), "❌ Field [" + jsonPath + "] is empty!");
    }

    /**
     * Assert a JSON field is null or absent.
     */
    public static void assertNull(Response response, String jsonPath) {
        Object actual = response.jsonPath().get(jsonPath);
        Assert.assertNull(actual, "❌ Field [" + jsonPath + "] should be null but got: " + actual);
    }

    /**
     * Assert response body contains a keyword.
     */
    public static void assertBodyContains(Response response, String keyword) {
        String body = response.asString();
        Assert.assertTrue(body.contains(keyword),
                "❌ Response body does not contain: " + keyword + "\nBody: " + body);
    }

    /**
     * Combined: status + error field check (for negative tests).
     */
    public static void assertError(Response response, int statusCode, String errorValue) {
        assertStatusCode(response, statusCode);
        // Try common error field paths
        String error = response.jsonPath().getString("error");
        if (error == null) error = response.jsonPath().getString("message");
        if (error == null) error = response.jsonPath().getString("errors[0]");
        Assert.assertNotNull(error, "❌ No error field found in response: " + response.asString());
        LoggerUtil.info("Error field value: " + error);
    }

    /**
     * Assert list is not empty.
     */
    public static void assertListNotEmpty(Response response, String jsonPath) {
        java.util.List<?> list = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull(list, "❌ List at [" + jsonPath + "] is null");
        Assert.assertFalse(list.isEmpty(), "❌ List at [" + jsonPath + "] is empty");
    }
}
