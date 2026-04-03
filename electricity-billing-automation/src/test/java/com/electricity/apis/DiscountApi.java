package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * DiscountApi — All Discount API methods.
 * Covers: Get Discount Reasons (TC-6.1.x), Get Discount Approvers (TC-6.2.x)
 */
public class DiscountApi {

    // ══════════════════════════════════════════════
    // GET DISCOUNT REASONS APIs
    // ══════════════════════════════════════════════

    /** TC-6.1.1: Get discount reasons — employee_id only */
    public static Response getDiscountOnlyEmployeeId(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        return RequestBuilder.post(ConfigManager.DISCOUNT_REASONS_URL, body.toString());
    }

    /** TC-6.1.2: Get discount reasons —  with reason*/
    public static Response getDiscountWithReasons(int employeeId, String reason) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("search",    reason);
        return RequestBuilder.post(ConfigManager.DISCOUNT_REASONS_URL, body.toString());
    }
    /** TC-6.1.3: Get discount active — with active  */
    public static Response getDiscountWithActive(int employeeId, Boolean active) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("active",    active);
        return RequestBuilder.post(ConfigManager.DISCOUNT_REASONS_URL, body.toString());
    }
    /** TC-6.1.4: Get discount company  — with company id */
    public static Response getDiscountWithCompanyId(int employeeId, int com_id) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("company_id",    com_id);
        return RequestBuilder.post(ConfigManager.DISCOUNT_REASONS_URL, body.toString());
    }
    /** TC-6.1.5: Get discount company  — with company id */
    public static Response getDiscountWithLimitAndOffset(int employeeId, int limit, int offset) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("limit",    limit);
        body.put("offset", offset);
        return RequestBuilder.post(ConfigManager.DISCOUNT_REASONS_URL, body.toString());
    }
    
    
    
    
    
    
    

    // ══════════════════════════════════════════════
    // GET DISCOUNT APPROVERS APIs
    // ══════════════════════════════════════════════

    /** TC-6.2.1: Get discount approvers — with discount amount */
    public static Response getDiscountApproversWithAmount(int employeeId, double discount) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("discount",    discount);
        return RequestBuilder.post(ConfigManager.DISCOUNT_APPROVERS_URL, body.toString());
    }

    /** TC-6.2.2: Get discount approvers — employee_id only (no discount) */
    public static Response getDiscountApprovers(int employeeId ,Double discount) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("discount", discount);
        return RequestBuilder.post(ConfigManager.DISCOUNT_APPROVERS_URL, body.toString());
    }

    /** TC-6.2.3: Get discount approvers — discount = 0 */
    public static Response getDiscountApproversZeroDiscount(int employeeId ,double discount) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("discount",    discount);
        return RequestBuilder.post(ConfigManager.DISCOUNT_APPROVERS_URL, body.toString());
    }

    /** TC-6.2.4: Get discount approvers — discount = -1 (invalid) */
    public static Response getDiscountApproversNegativeDiscount(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("discount",    -1);
        return RequestBuilder.post(ConfigManager.DISCOUNT_APPROVERS_URL, body.toString());
    }

    /** TC-6.2.5: Get discount approvers — only employee_id */
    public static Response getDiscountApproversOnlyEmployeeId(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        return RequestBuilder.post(ConfigManager.DISCOUNT_APPROVERS_URL, body.toString());
    }
}
