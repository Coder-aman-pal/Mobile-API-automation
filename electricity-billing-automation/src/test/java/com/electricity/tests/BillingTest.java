package com.electricity.tests;

import com.aventstack.extentreports.util.Assert;
import com.electricity.apis.BillingApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.RequestBuilder;
import com.electricity.utils.ResponseValidator;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.crypto.Cipher;

import org.testng.annotations.Test;

/**
 * BillingTest — Test cases for Billing APIs.
 * TC-4.1.x: Generate Bill
 * TC-4.2.x: Download Bill PDF
 */
public class BillingTest extends BaseTest {
	Response response;
    // ══════════════════════════════════════════════
    // TC-4.1.1: Generate Bill — Zero Rate
    // ══════════════════════════════════════════════
	
    @Test(priority = 1,
          groups = {"Billing", "Positive"},
          description = "Generate bill with rate=0 should return 200")
    public void TC_BILL_001_GenerateBillZeroRate() {
        extentTest.info("Testing generate bill with 0 rate");

         response = BillingApi.generateBillZeroRate(
                ConfigManager.EMPLOYEE_ID_3,
                ConfigManager.HOUSE_ID_BILL,
                ConfigManager.new_reading, ConfigManager.old_reading, 0.0,
                ConfigManager.date, "");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-BILL-001", "Generate Bill Zero Rate PASSED");
    }
    @Test(priority = 1,
            groups = {"Billing", "Positive"},
            description = "Generate bill with rate=0 should return 200")
      public void TC_BILL_001_GenerateBillvalid() {
          extentTest.info("Testing generate bill with valid");

           response = BillingApi.generateBillValid(
                  ConfigManager.EMPLOYEE_ID,
                  ConfigManager.HOUSE_ID,
                  ConfigManager.new_reading, ConfigManager.old_reading, ConfigManager.rate,
                  ConfigManager.date, "");
        
         
          ResponseValidator.assertStatusCode(response, 200);
          LoggerUtil.pass("TC-BILL-001", "Generate Bill Zero Rate PASSED");
      }

    // ══════════════════════════════════════════════
    // TC-4.1.2: Generate Bill — Valid with Rate > 0
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"Billing", "Positive"},
          description = "Generate bill with valid ")
    public void TC_BILL_002_GenerateBillLessthanoldReading() {
        extentTest.info("Testing generate bill ");

         response = BillingApi.generateBillValid(
                ConfigManager.EMPLOYEE_ID_2,
                ConfigManager.HOUSE_ID,
                ConfigManager.old_reading_geaterthan_new, 
                ConfigManager.new_reading_lessthan_old,
                ConfigManager.rate,
                ConfigManager.date, "");

        ResponseValidator.assertStatusCode(response, 422);
        LoggerUtil.pass("TC-BILL-002", "Generate Bill Valid Rate PASSED");
    }
    
    
    @Test(priority = 2,
            groups = {"Billing", "Positive"},
            description = "Generate bill with valid  should return 409")
      public void generateBillalready() {
          extentTest.info("Testing generate bill with rAllready");

           response = BillingApi.generateBillValid(
                  ConfigManager.EMPLOYEE_ID_2,
                  ConfigManager.HOUSE_ID,
                  ConfigManager.old_reading, 
                  ConfigManager.new_reading,
                  ConfigManager.rate,
                  ConfigManager.date, "");

          ResponseValidator.assertStatusCode(response, 409);
          LoggerUtil.pass("TC-BILL-002", "Generate Bill Valid Rate PASSED");
      }

    // ══════════════════════════════════════════════
    // TC-4.1.3: Generate Bill — New Reading < Old Reading
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Billing", "Negative"},
          description = "New reading < old reading (negative units) should return 400")
    public void TC_BILL_003_GenerateBillNegativeUnits() {
        extentTest.info("Testing generate bill where new_reading=0.1 < old_reading=1.0");

         response = BillingApi.generateBillNegativeUnits(
                ConfigManager.EMPLOYEE_ID,
                ConfigManager.HOUSE_ID,
                ConfigManager.old_reading, ConfigManager.negative_old_reading, ConfigManager.rate,
                ConfigManager.date, "");

        ResponseValidator.assertStatusCode(response, 422);
        LoggerUtil.pass("TC-BILL-003", "Generate Bill Negative Units PASSED");
    }
 // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Billing", "Negative"},
          description = "New reading < old reading (negative units) should return 400")
    public void TC_BILL_003_GenerateBill_oldreading_0_to_100() {
        extentTest.info("Testing generate bill where new_reading=0.1 < old_reading=1.0");

       BillingApi.generateBillvalid_OldReading_0();
    }

    // ══════════════════════════════════════════════
    // TC-4.1.4: Generate Bill — Zero Rate with Valid Readings
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"Billing", "Positive"},
          description = "Generate bill with rate=0 and valid readings should return 400")
    public void TC_BILL_004_GenerateBillZeroRateValidReadings() {
        extentTest.info("Testing generate bill: old=1.0, new=2.0, rate=0.0");

         response = BillingApi.generateBillWithZeroRateValidReadings(
                ConfigManager.EMPLOYEE_ID,
                ConfigManager.HOUSE_ID,
               ConfigManager.old_reading,
                ConfigManager.new_reading,
                0.0,
                ConfigManager.date,
               "" );

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-BILL-004", "Generate Bill Zero Rate Valid Readings PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-4.1.5: Generate Bill — Missing house_id
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"Billing", "Negative"},
          description = "Missing house_id should return 400 MISSING_REQUIRED_FIELDS")
    public void TC_BILL_005_GenerateBillMissingHouseId() {
        extentTest.info("Testing generate bill without house_id field");

         response = BillingApi.generateBillMissingHouseId(
                ConfigManager.EMPLOYEE_ID_2,
                ConfigManager.old_reading, ConfigManager.new_reading,ConfigManager.rate,
                ConfigManager.date);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-BILL-005", "Generate Bill Missing House ID PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-4.1.6: Generate Bill — Missing billing_date
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"Billing", "Negative"},
          description = "Missing billing_date should return 400 error")
    public void TC_BILL_006_GenerateBillMissingDate() {
        extentTest.info("Testing generate bill without billing_date field");

         response = BillingApi.generateBillMissingoldreading(
                ConfigManager.EMPLOYEE_ID_2,
                ConfigManager.HOUSE_ID_BILL,
                
                ConfigManager.new_reading, ConfigManager.rate);

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-BILL-006", "Generate Bill Missing Date PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-4.1.7: Generate Bill — Bill
    // ══════════════════════════════════════════════
    
   
    @Test(priority = 7,
          groups = {"Billing", "Negative"},
          description = "Generating bill that already exists should return 400 or 409")
    public void TC_BILL_007_generateBillsamereading() {
        extentTest.info("Testing generate bill that already exists for this billing period");

         response = BillingApi.generateBillsamereading(
                ConfigManager.EMPLOYEE_ID_2,
                ConfigManager.HOUSE_ID,
                ConfigManager.new_reading, ConfigManager.new_reading, ConfigManager.rate,
                ConfigManager.date);

        int statusCode = response.getStatusCode();
        LoggerUtil.info("Status for duplicate bill: " + statusCode);
        LoggerUtil.pass("TC-BILL-007", "Generate Bill Already Exists PASSED (status=" + statusCode + ")");
    }
    // ══════════════════════════════════════════════
    // TC-4.1.8: Missing Rate
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"Bill", "Negative"},
          description = "Missing rate should return 400 MISSING_REQUIRED_FIELDS")
    public void TC_BILL_008_MissingRate() {
    	 extentTest.info("Testing generate bill that missing rate period");
    	 
    	  response = BillingApi.TC_BILL_008_MissingRate(
                  ConfigManager.EMPLOYEE_ID_2,
                  ConfigManager.HOUSE_ID,
ConfigManager.new_reading, ConfigManager.old_reading, 
                  ConfigManager.date);
    }

    // ══════════════════════════════════════════════
    // TC-4.1.9: generateBillAlreadyExists
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Bill", "Negative"},
          description = "Invalid house_id should return ")
    public void TC_BILL_009_generateBillmissingnewreading() {
    	   extentTest.info("Testing generate bill that already exists for this billing period");

           BillingApi.generateBillMissingNewReading();
               

         
      }

    
    
    
    

    
    
    
    // ══════════════════════════════════════════════
    // TC-4.2.1: Download Bill PDF — Valid
    // ══════════════════════════════════════════════
    @Test(priority = 8,
    	      groups = {"Billing", "Positive"},
    	      description = "Download bill PDF with valid bill_id should return 200 and save file")
    	public void TC_BILL_008_DownloadBillValid() {
    	    extentTest.info("Testing download bill PDF with valid bill_id=1");
    	    BillingApi.downloadBillValid(6, "2026-03-24");
    	   
    	}


    // ══════════════════════════════════════════════
    // TC-4.2.2: Download Bill PDF — Invalid bill_id
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Billing", "Negative"},
          description = "Download bill PDF with invalid bill_id should return 404")
    public void TC_BILL_009_DownloadBillInvalid() {
        extentTest.info("Testing download bill PDF with invalid bill_id=99999");

        Response response = BillingApi.downloadBillInvalid(ConfigManager.EMPLOYEE_ID_3);

        ResponseValidator.assertStatusCode(response, 404);
        LoggerUtil.pass("TC-BILL-009", "Download Bill Invalid PASSED");
    }
    
    
    
    
    
    @Test(priority = 10,
            groups = {"Billing", "Negative"},
            description = "Download bill PDF with invalid bill_id should return 404")
     
    public void TC_BILL_010_missingEmployeeId() {
    	 extentTest.info("Testing download bill PDF with without imployee Id");
    	 BillingApi.tc_4_2_2_missingEmployeeId();
    	 
    	
    }
    @Test(priority = 11,
            groups = {"Billing", "Negative"},
            description = "Download bill PDF with invalid bill_id should return 404")
     
    public void TC_BILL_01_invalidApiKey() {
    	BillingApi.tc_4_2_4_invalidApiKey();
    	
    }
    
}
