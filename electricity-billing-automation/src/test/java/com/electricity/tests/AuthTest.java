package com.electricity.tests;

import com.electricity.apis.AuthApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * AuthTest — Test cases for Authentication APIs.
 * TC-1.1.x: Collector Login
 * TC-1.2.x: Change Password
 */
public class AuthTest extends BaseTest {

    // ══════════════════════════════════════════════
    // TC-1.1.1: Valid Login
    // ══════════════════════════════════════════════
    @Test(priority = 1,
          groups = {"Auth", "Positive"},
          description = "Valid mobile + password should return 200 with token")
    public void TC_AUTH_001_ValidLogin() {
        extentTest.info("Testing valid login with mobile: " + ConfigManager.VALID_MOBILE);

        Response response = AuthApi.loginValid(ConfigManager.VALID_MOBILE, ConfigManager.VALID_PASSWORD);

        ResponseValidator.assertStatusCode(response, 200);
        response.asPrettyString();
        
        LoggerUtil.pass("TC-AUTH-001", "Valid Login PASSED");
        
        
    }

    // ══════════════════════════════════════════════
    // TC-1.1.2: Missing Mobile
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"Auth", "Negative"},
          description = "Missing mobile field should return 4xx error")
    public void TC_AUTH_002_MissingMobile() {
        extentTest.info("Testing login without mobile field");

        Response response = AuthApi.loginMissingMobile(ConfigManager.VALID_PASSWORD);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-002", "Missing Mobile PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.3: Missing Password
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Auth", "Negative"},
          description = "Missing password field should return 4xx error")
    public void TC_AUTH_003_MissingPassword() {
        extentTest.info("Testing login without password field");

        Response response = AuthApi.loginMissingPassword(ConfigManager.VALID_MOBILE);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-003", "Missing Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.4: Wrong Password
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"Auth", "Negative"},
          description = "Wrong password should return 401 AUTHENTICATION_FAILED")
    public void TC_AUTH_004_WrongPassword() {
        extentTest.info("Testing login with wrong password");

        Response response = AuthApi.loginWrongPassword(ConfigManager.VALID_MOBILE, "wrongpass999");

        ResponseValidator.assertStatusCode(response, 401);
        LoggerUtil.pass("TC-AUTH-004", "Wrong Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.5: Invalid / Non-existent Mobile
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"Auth", "Negative"},
          description = "Non-existent mobile should return 401")
    public void TC_AUTH_005_InvalidMobile() {
        extentTest.info("Testing login with non-existent mobile");

        Response response = AuthApi.loginInvalidMobile("0000000000", ConfigManager.VALID_PASSWORD);

        ResponseValidator.assertStatusCode(response, 401);
        LoggerUtil.pass("TC-AUTH-005", "Invalid Mobile PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.6: Empty Request Body
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"Auth", "Negative"},
          description = "Empty body should return 400 error")
    public void TC_AUTH_006_EmptyBody() {
        extentTest.info("Testing login with empty request body {}");

        Response response = AuthApi.loginEmptyBody();

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-006", "Empty Body PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.7: Invalid JSON
    // ══════════════════════════════════════════════
    @Test(priority = 7,
          groups = {"Auth", "Negative"},
          description = "Invalid JSON body should return 400 error")
    public void TC_AUTH_007_InvalidJson() {
        extentTest.info("Testing login with malformed JSON body");

        Response response = AuthApi.loginInvalidJson();

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-007", "Invalid JSON PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.1.8: Non-Collector Employee
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"Auth", "Negative"},
          description = "Non-collector employee should return 401")
    public void TC_AUTH_008_NonCollectorEmployee() {
        extentTest.info("Testing login with non-collector employee mobile");

        Response response = AuthApi.loginNonCollector("987654323", ConfigManager.VALID_PASSWORD);

        ResponseValidator.assertStatusCode(response, 401);
        LoggerUtil.pass("TC-AUTH-008", "Non-Collector Employee PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.1: Valid Change Password
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Auth", "Positive"},
          description = "Valid old + new password should return 200 success")
    public void TC_AUTH_009_ValidChangePassword() {
        extentTest.info("Testing valid password change for employee_id=6");

        Response response = AuthApi.changePasswordValid(6, "123456", "123456");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-AUTH-009", "Valid Change Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.2: Wrong Old Password
    // ══════════════════════════════════════════════
    @Test(priority = 10,
          groups = {"Auth", "Negative"},
          description = "Wrong old password should return 401")
    public void TC_AUTH_010_WrongOldPassword() {
        extentTest.info("Testing change password with wrong old password");

        Response response = AuthApi.changePasswordWrongOld(6, "123454", "123456");

        ResponseValidator.assertStatusCode(response, 401);
        LoggerUtil.pass("TC-AUTH-010", "Wrong Old Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.3: Short New Password
    // ══════════════════════════════════════════════
    @Test(priority = 11,
          groups = {"Auth", "Negative"},
          description = "New password < 6 chars should return 400")
    public void TC_AUTH_011_ShortNewPassword() {
        extentTest.info("Testing change password with new_password < 6 chars");

        Response response = AuthApi.changePasswordShortNew(6, "123456", "12345");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-011", "Short New Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.4: Missing old_password
    // ══════════════════════════════════════════════
    @Test(priority = 12,
          groups = {"Auth", "Negative"},
          description = "Missing old_password field should return 400")
    public void TC_AUTH_012_MissingOldPassword() {
        extentTest.info("Testing change password without old_password field");

        Response response = AuthApi.changePasswordMissingOld(6, "123456");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-012", "Missing Old Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.5: Missing new_password
    // ══════════════════════════════════════════════
    @Test(priority = 13,
          groups = {"Auth", "Negative"},
          description = "Missing new_password field should return 400")
    public void TC_AUTH_013_MissingNewPassword() {
        extentTest.info("Testing change password without new_password field");

        Response response = AuthApi.changePasswordMissingNew(6, "123456");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-013", "Missing New Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.6: Empty old_password
    // ══════════════════════════════════════════════
    @Test(priority = 14,
          groups = {"Auth", "Negative"},
          description = "Empty old_password value should return 400")
    public void TC_AUTH_014_EmptyOldPassword() {
        extentTest.info("Testing change password with empty old_password value");

        Response response = AuthApi.changePasswordEmptyOld(6, "123456");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-014", "Empty Old Password PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-1.2.7: Empty new_password
    // ══════════════════════════════════════════════
    @Test(priority = 15,
          groups = {"Auth", "Negative"},
          description = "Empty new_password value should return 400")
    public void TC_AUTH_015_EmptyNewPassword() {
        extentTest.info("Testing change password with empty new_password value");

        Response response = AuthApi.changePasswordEmptyNew(6, "123456");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-AUTH-015", "Empty New Password PASSED");
    }
}
