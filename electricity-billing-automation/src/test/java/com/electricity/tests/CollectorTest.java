package com.electricity.tests;

import com.electricity.apis.CollectorApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * CollectorTest — Test cases for Collector/Customer APIs.
 * TC-2.1.x: Get Customers
 * TC-2.2.x: Search Customers
 */
public class CollectorTest extends BaseTest {

    // ══════════════════════════════════════════════
    // TC-2.1.1: Get All Customers
    // ══════════════════════════════════════════════
    @Test(priority = 1,
          groups = {"Collector", "Positive"},
          description = "employee_id only — should return all assigned customers with 200")
    public void TC_CUST_001_GetAllCustomers() {
        extentTest.info("Testing get all customers for employee_id=" + ConfigManager.EMPLOYEE_ID);

        Response response = CollectorApi.getAllCustomers(ConfigManager.EMPLOYEE_ID);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-001", "Get All Customers PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.2: Filter by Zone
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"Collector", "Positive"},
          description = "zone_id provided — should return zone-filtered customers with 200")
    public void TC_CUST_002_FilterByZone() {
        extentTest.info("Testing filter by zone_id=" + ConfigManager.ZONE_ID);

        Response response = CollectorApi.getCustomersByZone(ConfigManager.EMPLOYEE_ID, ConfigManager.ZONE_ID);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-002", "Filter By Zone PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.3: Filter by Sub-Zone
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Collector", "Positive"},
          description = "sub_zone_id provided — should return sub-zone customers with 200")
    public void TC_CUST_003_FilterBySubZone() {
        extentTest.info("Testing filter by sub_zone_id=90");

        Response response = CollectorApi.getCustomersBySubZone(ConfigManager.EMPLOYEE_ID, ConfigManager.SUB_ZONE_ID);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-003", "Filter By Sub-Zone PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.4: Search by Name
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"Collector", "Positive"},
          description = "search by name 'John' — should return matching customers")
    public void TC_CUST_004_SearchByName() {
        extentTest.info("Testing search customers by name='John'");

        Response response = CollectorApi.searchCustomerByName(ConfigManager.EMPLOYEE_ID, "John");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-004", "Search By Name PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.5: Search by Mobile
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"Collector", "Positive"},
          description = "search by mobile '12345' — should return matching customers")
    public void TC_CUST_005_SearchByMobile() {
        extentTest.info("Testing search customers by mobile='12345'");

        Response response = CollectorApi.searchCustomerByMobile(ConfigManager.EMPLOYEE_ID, "12345");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-005", "Search By Mobile PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.6: Search by Supply Number
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"Collector", "Positive"},
          description = "search by supply number 'ZONE-A' — should return matching records")
    public void TC_CUST_006_SearchBySupplyNumber() {
        extentTest.info("Testing search customers by supply number='ZONE-A'");

        Response response = CollectorApi.searchCustomerBySupplyNumber(ConfigManager.EMPLOYEE_ID, "ZONE-A");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-006", "Search By Supply Number PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.7: No Assigned Sub-Zones
    // ══════════════════════════════════════════════
    @Test(priority = 7,
          groups = {"Collector", "Negative"},
          description = "Employee with no sub-zones should return 403 NO_SUB_ZONES")
    public void TC_CUST_007_NoSubZones() {
        extentTest.info("Testing with employee who has no sub-zones assigned (employee_id=14)");

        Response response = CollectorApi.getCustomersNoSubZones(14);

        ResponseValidator.assertStatusCode(response, 403);
        LoggerUtil.pass("TC-CUST-007", "No Sub-Zones PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.8: Unauthorized Sub-Zone
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"Collector", "Negative"},
          description = "Sub-zone not assigned to collector should return 403 NO_ACCESS")
    public void TC_CUST_008_UnauthorizedSubZone() {
        extentTest.info("Testing with unauthorized sub_zone_id=9999");

        Response response = CollectorApi.getCustomersUnauthorizedSubZone(ConfigManager.EMPLOYEE_ID, 9999);

        ResponseValidator.assertStatusCode(response, 403);
        LoggerUtil.pass("TC-CUST-008", "Unauthorized Sub-Zone PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.9: Invalid zone_id
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Collector", "Negative"},
          description = "Invalid zone_id 'abc' should return 400 INVALID_PARAMETER")
    public void TC_CUST_009_InvalidZoneId() {
        extentTest.info("Testing with invalid zone_id='abc'");

        Response response = CollectorApi.getCustomersInvalidZoneId(ConfigManager.EMPLOYEE_ID, "abc");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-CUST-009", "Invalid Zone ID PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.1.10: Large Dataset Pagination
    // ══════════════════════════════════════════════
    @Test(priority = 10,
          groups = {"Collector", "Positive"},
          description = "Large dataset — should return paginated results with 200")
    public void TC_CUST_010_LargeDatasetPagination() {
        extentTest.info("Testing large dataset with pagination page=1, per_page=50");

        Response response = CollectorApi.getCustomersLargeDataset(ConfigManager.EMPLOYEE_ID, 1, 50);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-010", "Large Dataset Pagination PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.1: Search Customers — Valid Search
    // ══════════════════════════════════════════════
    @Test(priority = 11,
          groups = {"Collector", "Positive"},
          description = "Valid search term should return matching customers with 200")
    public void TC_CUST_011_SearchCustomersValid() {
        extentTest.info("Testing search-customers endpoint with term='ZONE-A-001'");

        Response response = CollectorApi.searchCustomers(ConfigManager.EMPLOYEE_ID, "ZONE-A-001");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-011", "Search Customers Valid PASSED");
    }

    
    
    
    // ══════════════════════════════════════════════
    // TC-2.2.2: Search Customers — With Zone Filter
    // ══════════════════════════════════════════════
    @Test(priority = 12,
          groups = {"Collector", "Positive"},
          description = "Search with zone filter should return filtered results with 200")
    public void TC_CUST_012_SearchCustomersWithZone() {
        extentTest.info("Testing search-customers with zone_id=" + ConfigManager.ZONE_ID);

        Response response = CollectorApi.searchCustomersWithZone(
                ConfigManager.EMPLOYEE_ID, ConfigManager.ZONE_ID, "test");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-012", "Search Customers With Zone PASSED");
    }
    
    
    
    
    // ══════════════════════════════════════════════
    // TC-2.2.3: Search by Supply Number
    // ══════════════════════════════════════════════
    @Test(priority = 13,
          groups = {"Collector", "Positive"},
          description = "Search by name='ZONE-A-001' should return 200 matching houses")
    public void TC_CUST_013_SearchBySupplyNumber() {
        extentTest.info("Testing search-customers by supply number: name='ZONE-A-001'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "ZONE-A-001", "", "", "", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-013", "Search By Supply Number PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.4: Search by Partner Name
    // ══════════════════════════════════════════════
    @Test(priority = 14,
          groups = {"Collector", "Positive"},
          description = "Search by partner_name='John' should return 200 customers named John")
    public void TC_CUST_014_SearchByPartnerName() {
        extentTest.info("Testing search-customers by partner_name='John'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "", "John", "", "", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-014", "Search By Partner Name PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.5: Search by Mobile
    // ══════════════════════════════════════════════
    @Test(priority = 15,
          groups = {"Collector", "Positive"},
          description = "Search by mobile='123' should return 200 matching customers")
    public void TC_CUST_015_SearchByMobile() {
        extentTest.info("Testing search-customers by mobile='123'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "", "", "123", "", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-015", "Search By Mobile PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.6: Search by Customer Type
    // ══════════════════════════════════════════════
    @Test(priority = 16,
          groups = {"Collector", "Positive"},
          description = "Search by customer_type='Commercial' should return 200 commercial customers")
    public void TC_CUST_016_SearchByCustomerType() {
        extentTest.info("Testing search-customers by customer_type='Commercial'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "", "", "", "Commercial", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-016", "Search By Customer Type PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.7: Combined Search — name + mobile
    // ══════════════════════════════════════════════
    @Test(priority = 17,
          groups = {"Collector", "Positive"},
          description = "Combined name + mobile search should return 200 results matching both")
    public void TC_CUST_017_CombinedSearch() {
        extentTest.info("Testing search-customers combined: name='ZONE-A-001' + mobile='1234567890'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "ZONE-A-001", "", "1234567890", "", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-017", "Combined Search PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.8: No Results — Non-Matching Search
    // ══════════════════════════════════════════════
    @Test(priority = 18,
          groups = {"Collector", "Positive"},
          description = "Non-matching search should return 200 with empty customers array")
    public void TC_CUST_018_NoResults() {
        extentTest.info("Testing search-customers with non-matching term: name='XYZNONEXISTENT999'");

        Response response = CollectorApi.searchCustomerAdvanced(
                ConfigManager.EMPLOYEE_ID_2,
                "XYZNONEXISTENT999", "", "", "", "", "", "", "", 50, 0);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-CUST-018", "No Results Empty Array PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-2.2.9: Missing Request Body
    // ══════════════════════════════════════════════
    @Test(priority = 19,
          groups = {"Collector", "Negative"},
          description = "Empty body {} should return 400 MISSING_REQUEST_BODY")
    public void TC_CUST_019_MissingRequestBody() {
        extentTest.info("Testing search-customers with empty request body {}");

        Response response = CollectorApi.searchCustomerEmptyBody();

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-CUST-019", "Missing Request Body PASSED");
    }
}

    

