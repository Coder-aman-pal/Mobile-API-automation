package com.electricity.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * TokenManager — Manages Bearer Token for API requests.
 * Supports both static token and dynamic login-based token.
 */
public class TokenManager {

    private static String authToken = ConfigManager.BEARER_TOKEN;

    /**
     * Returns the current Bearer token.
     */
    public static String getToken() {
        return authToken;
    }

    /**
     * Dynamically fetches a fresh token via login API.
     * Call this in @BeforeSuite if token expires frequently.
     */
    public static String fetchTokenByLogin(String mobile, String password) {
        try {
            JSONObject body = new JSONObject();
            body.put("mobile", mobile);
            body.put("password", password);

            Response response = RestAssured.given()
                    .baseUri(ConfigManager.BASE_URL)
                    .header("Content-Type", ConfigManager.CONTENT_TYPE)
                    .body(body.toString())
                    .when()
                    .post(ConfigManager.LOGIN_URL);

            if (response.getStatusCode() == 200) {
                String token = response.jsonPath().getString("data.token");
                if (token != null && !token.isEmpty()) {
                    authToken = token;
                    LoggerUtil.info("✅ Token fetched dynamically: " + token.substring(0, 8) + "...");
                } else {
                    LoggerUtil.warn("⚠️  Token not found in response, using static token.");
                }
            } else {
                LoggerUtil.warn("⚠️  Login failed (status=" + response.getStatusCode() + "), using static token.");
            }
        } catch (Exception e) {
            LoggerUtil.error("❌ Token fetch error: " + e.getMessage());
        }
        return authToken;
    }

    /**
     * Manually set a token (useful for test-specific overrides).
     */
    public static void setToken(String token) {
        authToken = token;
    }
}
