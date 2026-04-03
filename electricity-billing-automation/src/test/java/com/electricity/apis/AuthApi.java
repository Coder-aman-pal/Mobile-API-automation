package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * AuthApi — All Authentication API methods.
 * Covers: Collector Login, Change Password
 */
public class AuthApi {

    // ══════════════════════════════════════════════
    // LOGIN APIs
    // ══════════════════════════════════════════════

    /** TC-1.1.1: Valid login with mobile + password */
    public static Response loginValid(String mobile, String password) {
        JSONObject body = new JSONObject();
        body.put("mobile", mobile);
        body.put("password", password);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
        
    }

    /** TC-1.1.2: Login with missing mobile */
    public static Response loginMissingMobile(String password) {
        JSONObject body = new JSONObject();
        body.put("password", password);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    /** TC-1.1.3: Login with missing password */
    public static Response loginMissingPassword(String mobile) {
        JSONObject body = new JSONObject();
        body.put("mobile", mobile);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    /** TC-1.1.4: Login with wrong password */
    public static Response loginWrongPassword(String mobile, String wrongPassword) {
        JSONObject body = new JSONObject();
        body.put("mobile", mobile);
        body.put("password", wrongPassword);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    /** TC-1.1.5: Login with non-existent mobile */
    public static Response loginInvalidMobile(String invalidMobile, String password) {
        JSONObject body = new JSONObject();
        body.put("mobile", invalidMobile);
        body.put("password", password);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    /** TC-1.1.6: Login with empty body */
    public static Response loginEmptyBody() {
        JSONObject body = new JSONObject();
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    /** TC-1.1.7: Login with invalid JSON */
    public static Response loginInvalidJson() {
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, "{invalid json}");
    }

    /** TC-1.1.8: Login — non-collector employee */
    public static Response loginNonCollector(String mobile, String password) {
        JSONObject body = new JSONObject();
        body.put("mobile", mobile);
        body.put("password", password);
        return RequestBuilder.postNoAuth(ConfigManager.LOGIN_URL, body.toString());
    }

    // ══════════════════════════════════════════════
    // CHANGE PASSWORD APIs
    // ══════════════════════════════════════════════

    /** TC-1.2.1: Valid password change */
    public static Response changePasswordValid(int employeeId, String oldPassword, String newPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", oldPassword);
        body.put("new_password", newPassword);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.2: Wrong old password */
    public static Response changePasswordWrongOld(int employeeId, String wrongOld, String newPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", wrongOld);
        body.put("new_password", newPassword);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.3: New password too short (< 6 chars) */
    public static Response changePasswordShortNew(int employeeId, String oldPassword, String shortNew) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", oldPassword);
        body.put("new_password", shortNew);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.4: Missing old_password field */
    public static Response changePasswordMissingOld(int employeeId, String newPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("new_password", newPassword);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.5: Missing new_password field */
    public static Response changePasswordMissingNew(int employeeId, String oldPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", oldPassword);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.6: Empty old_password value */
    public static Response changePasswordEmptyOld(int employeeId, String newPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", "");
        body.put("new_password", newPassword);
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }

    /** TC-1.2.7: Empty new_password value */
    public static Response changePasswordEmptyNew(int employeeId, String oldPassword) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("old_password", oldPassword);
        body.put("new_password", "");
        return RequestBuilder.post(ConfigManager.CHANGE_PASSWORD_URL, body.toString());
    }
}
