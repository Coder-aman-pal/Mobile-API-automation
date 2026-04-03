package com.electricity.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * RequestBuilder — Reusable RestAssured request builder.
 * Builds authenticated and unauthenticated requests consistently.
 */
public class RequestBuilder {

    /**
     * Returns a pre-configured request spec WITH Bearer token.
     */
    public static RequestSpecification withAuth() {
        return RestAssured.given()
                .baseUri(ConfigManager.BASE_URL)
                .header("Content-Type", ConfigManager.CONTENT_TYPE)
                .header("Authorization", "Bearer " + TokenManager.getToken());
    }

    /**
     * Returns a pre-configured request spec WITHOUT token (for auth tests).
     */
    public static RequestSpecification withoutAuth() {
        return RestAssured.given()
                .baseUri(ConfigManager.BASE_URL)
                .header("Content-Type", ConfigManager.CONTENT_TYPE);
    }

    /**
     * POST with auth and JSON body.
     */
    public static Response post(String endpoint, String body) {
        LoggerUtil.step("POST " + endpoint);
        LoggerUtil.info("Request Body: " + body);
        Response response = RestAssured.given()
        		.header("Authorization", "Bearer " + TokenManager.getToken())
                .body(body)
                .when()
                .post(ConfigManager.BASE_URL+endpoint);
        LoggerUtil.info("Response [" + response.getStatusCode() + "]: " + response.asPrettyString());
        return response;
    }

    /**
     * POST without auth and JSON body.
     */
    public static Response postNoAuth(String endpoint, String body) {
        LoggerUtil.step("POST (no-auth) " + endpoint);
        LoggerUtil.info("Request Body: " + body);
        Response response = withoutAuth()
                .body(body)
                .when()
                .post(endpoint);
        LoggerUtil.info("Response [" + response.getStatusCode() + "]: " + response.asString());
        return response;
    }

    /**
     * GET with auth and query params.
     */
    public static Response get(String endpoint) {
        LoggerUtil.step("GET " + endpoint);
        Response response = withAuth()
                .when()
                .get(endpoint);
        LoggerUtil.info("Response [" + response.getStatusCode() + "]: " + response.asString());
        return response;
    }
}
