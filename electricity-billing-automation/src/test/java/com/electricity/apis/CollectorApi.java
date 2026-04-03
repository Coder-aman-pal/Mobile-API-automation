package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;

/**
 * CollectorApi — All Collector/Customer API methods.
 * Covers: Get Customers, Search Customers
 */
public class CollectorApi {

    // ══════════════════════════════════════════════
    // GET CUSTOMERS APIs
    // ══════════════════════════════════════════════

    /** TC-2.1.1: Get all customers — employee_id only */
    public static Response getAllCustomers(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.2: Filter customers by zone_id */
    public static Response getCustomersByZone(int employeeId, int zoneId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("zone_id",     zoneId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.3: Filter customers by sub_zone_id */
    public static Response getCustomersBySubZone(int employeeId, int subZoneId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("sub_zone_id", subZoneId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.4: Search customers by name */
    public static Response searchCustomerByName(int employeeId, String name) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("search",      name);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.5: Search customers by mobile */
    public static Response searchCustomerByMobile(int employeeId, String mobile) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("search",      mobile);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.6: Search customers by supply number */
    public static Response searchCustomerBySupplyNumber(int employeeId, String supplyNumber) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("search",      supplyNumber);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.7: Employee with no sub-zones assigned */
    public static Response getCustomersNoSubZones(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.8: Unauthorized sub-zone access */
    public static Response getCustomersUnauthorizedSubZone(int employeeId, int unauthorizedSubZoneId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("sub_zone_id", unauthorizedSubZoneId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.9: Invalid zone_id (non-numeric string) */
    public static Response getCustomersInvalidZoneId(int employeeId, String invalidZoneId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("zone_id",     invalidZoneId);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    /** TC-2.1.10: Large dataset — with pagination */
    public static Response getCustomersLargeDataset(int employeeId, int page, int perPage) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("page",        page);
        body.put("per_page",    perPage);
        return RequestBuilder.post(ConfigManager.CUSTOMERS_URL, body.toString());
    }

    
    /** TC-2.2.1
    
    // ══════════════════════════════════════════════
    // SEARCH CUSTOMERS APIs
    // ══════════════════════════════════════════════

    /** Search customers via search-customers endpoint */
    public static Response searchCustomers(int employeeId, String searchTerm) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("search",      searchTerm);
        return RequestBuilder.post(ConfigManager.SEARCH_CUSTOMERS_URL, body.toString());
    }

    
    
    /** TC-2.2.2
    /** Search customers with zone filter */
    public static Response searchCustomersWithZone(int employeeId, int zoneId, String searchTerm) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("zone_id",     zoneId);
        body.put("search",      searchTerm);
        return RequestBuilder.post(ConfigManager.SEARCH_CUSTOMERS_URL, body.toString());
    }
    
    
 // ══════════════════════════════════════════════
    // TC-2.2.3 to 2.2.8: Advanced Search — All Fields
    // ══════════════════════════════════════════════
    public static Response searchCustomerAdvanced(
            int    employeeId,
            String name,
            String partnerName,
            String mobile,
            String customerType,
            String customerSubType,
            String zone,
            String subZone,
            String village,
            int    limit,
            int    offset) {

        JSONObject body = new JSONObject();
        body.put("employee_id",       employeeId);
        body.put("name",              name);
        body.put("partner_name",      partnerName);
        body.put("mobile",            mobile);
        body.put("customer_type",     customerType);
        body.put("customer_sub_type", customerSubType);
        body.put("zone",              zone);
        body.put("sub_zone",          subZone);
        body.put("village",           village);
        body.put("limit",             limit);
        body.put("offset",            offset);
        return RequestBuilder.post(ConfigManager.SEARCH_CUSTOMERS_URL, body.toString());
    }

    // ══════════════════════════════════════════════
    // TC-2.2.9: Empty Body
    // ══════════════════════════════════════════════
    public static Response searchCustomerEmptyBody() {
        return RequestBuilder.post(ConfigManager.SEARCH_CUSTOMERS_URL, new JSONObject().toString());
    }
}


