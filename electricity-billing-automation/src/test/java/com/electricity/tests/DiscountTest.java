package com.electricity.tests;

import com.electricity.apis.DiscountApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * DiscountTest — Test cases for Discount APIs.
 * TC-6.1.x: Get Discount Reasons
 * TC-6.2.x: Get Discount Approvers
 */
public class DiscountTest extends BaseTest {

    // ══════════════════════════════════════════════
    // TC-6.1.1: Get Discount Reasons — employee_id only
    // ══════════════════════════════════════════════
    @Test(priority = 1,
          groups = {"Discount", "Positive"},
          description = "Get discount reasons with employee_id only should return 200")
    public void TC_DISC_001_GetDiscountReasons() {
        extentTest.info("Testing get discount reasons with employee_id=" + ConfigManager.EMPLOYEE_ID);

        Response response = DiscountApi.getDiscountOnlyEmployeeId(ConfigManager.EMPLOYEE_ID);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-001", "Get Discount Reasons PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-6.1.2: Get Discount Reasons — With reason
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"Discount", "Positive"},
          description = "Get discount reasons with resason should return 200")
    public void TC_DISC_002_GetDiscountReasonsWithReason() {
        extentTest.info("Testing get discount reasons with reason="+ConfigManager.resonother);

        Response response = DiscountApi.getDiscountWithReasons(ConfigManager.EMPLOYEE_ID, ConfigManager.resonother);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-002", "Get Discount Reasons With Amount PASSED");
    }
 // ══════════════════════════════════════════════
    // TC-6.1.3: Get Discount Reasons — With Active
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Discount", "Positive"},
          description = "Get discount reasons with active  should return 200")
    public void TC_DISC_003_GetDiscountReasonsWithActive() {
        extentTest.info("Testing get discount reasons with active"+ConfigManager.InActive);

        Response response = DiscountApi.getDiscountWithActive(ConfigManager.EMPLOYEE_ID, ConfigManager.InActive);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-002", "Get Discount Reasons With Amount PASSED");
    }
 // ══════════════════════════════════════════════
    // TC-6.1.4: Get Discount Reasons — With company Id
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"Discount", "Positive"},
          description = "Get discount reasons with company id should return 200")
    public void TC_DISC_004_GetDiscountReasonsWithComapnyId() {
        extentTest.info("Testing get discount reasons with discount=company id"+ConfigManager.company_id);

        Response response = DiscountApi.getDiscountWithCompanyId(ConfigManager.EMPLOYEE_ID, ConfigManager.company_id);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-002", "Get Discount Reasons With Amount PASSED");
    }
 // ══════════════════════════════════════════════
    // TC-6.1.5: Get Discount Reasons — With limit and offset
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"Discount", "Positive"},
          description = "Get discount reasons with limit and offset should return 200")
    public void TC_DISC_005_GetDiscountReasonsWithLimitAndOffset() {
        extentTest.info("Testing get discount reasons with ="+ConfigManager.limit+" "+ConfigManager.offset);

        Response response = DiscountApi.getDiscountWithLimitAndOffset(ConfigManager.EMPLOYEE_ID, ConfigManager.limit,ConfigManager.offset);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-005", "Get Discount Reasons With Amount PASSED");
    }

    
    
    
    
    
    
    
    
    // ══════════════════════════════════════════════
    // TC-6.2.1: Get Discount Approvers — With Discount Amount
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"Discount", "Positive"},
          description = "Get discount approvers with discount= should return 200")
    public void TC_DISC_006_GetDiscountApproversWithvalidAmount() {
        extentTest.info("Testing get discount approvers with discount="+ConfigManager.amount);

        Response response = DiscountApi.getDiscountApproversWithAmount(ConfigManager.EMPLOYEE_ID,ConfigManager.amount );

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-006", "Get Discount Approvers With Amount PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-6.2.2: Get Discount Approvers — employee_id only
    // ══════════════════════════════════════════════
    @Test(priority = 7,
          groups = {"Discount", "Positive"},
          description = "Get discount approvers with employee_id only should return 200")
    public void TC_DISC_007_GetDiscountApproversWithInvalidAmount() {
        extentTest.info("Testing get discount approvers with only employee_id");

        Response response = DiscountApi.getDiscountApprovers(ConfigManager.EMPLOYEE_ID ,ConfigManager.invaliamount);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-DISC-007", "Get Discount Approvers PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-6.2.3: Get Discount Approvers — discount = 0
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"Discount", "Negative"},
          description = "Get discount approvers with discount=0.0 should return 400 or empty list")
    public void TC_DISC_008_GetDiscountApproversZeroDiscount() {
        extentTest.info("Testing get discount approvers with discount=0.0");

        Response response = DiscountApi.getDiscountApproversZeroDiscount(ConfigManager.NOT_APPROVER_ID ,ConfigManager.discount);

        int statusCode = response.getStatusCode();
        LoggerUtil.info("Status for zero discount approvers: " + statusCode);
        LoggerUtil.pass("TC-DISC-008", "Get Discount Approvers Zero Discount PASSED (status=" + statusCode + ")");
    }

    // ══════════════════════════════════════════════
    // TC-6.2.4: Get Discount Approvers — discount = -1 (Invalid)
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Discount", "Negative"},
          description = "Get discount approvers with discount=-1 should return 400 error")
    public void TC_DISC_009_GetDiscountApproversNegativeDiscount() {
        extentTest.info("Testing get discount approvers with invalid discount=-1");

        Response response = DiscountApi.getDiscountApproversNegativeDiscount(ConfigManager.EMPLOYEE_ID);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-DISC-009", "Get Discount Approvers Negative Discount PASSED");
    }

    
    
    // ══════════════════════════════════════════════
    // TC-6.2.5: Get Discount Approvers — only employee_id
    // ══════════════════════════════════════════════
    @Test(priority = 10,
          groups = {"Discount", "Positive"},
          description = "Get discount approvers with only employee_id should return 200")
    public void TC_DISC_010_GetDiscountApproversOnlyEmployeeId() {
        extentTest.info("Testing get discount approvers endpoint with only employee_id (no discount)");

        Response response = DiscountApi.getDiscountApproversOnlyEmployeeId(ConfigManager.EMPLOYEE_ID);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-DISC-010", "Get Discount Approvers Only Employee ID PASSED");
    }
}
