package com.electricity.tests;

import com.electricity.apis.PaymentApi;
import com.electricity.base.BaseTest;
import com.electricity.utils.ConfigManager;
import com.electricity.utils.LoggerUtil;
import com.electricity.utils.ResponseValidator;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/**
 * PaymentTest — Test cases for Payment APIs.
 * TC-5.1.x: Collect Payment
 * TC-5.2.x: Get Collection Summary
 */
public class PaymentTest extends BaseTest {

    // ══════════════════════════════════════════════
    // TC-5.1.1: Valid Payment — Full Amount, No Discount
    // ══════════════════════════════════════════════
    @Test(priority = 1,
          groups = {"Payment", "Positive"},
          description = "Valid payment full amount without discount should return 200")
    public void TC_PAY_001_ValidPaymentNoDiscount() {  ///runtime issue 
        extentTest.info("Testing valid payment without discount ");

        Response response = PaymentApi.collectPaymentValid(
                ConfigManager.EMPLOYEE_ID_2,
                123,
                505.00);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-PAY-001", "Valid Payment No Discount PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.1.2: Payment with Discount
    // ══════════════════════════════════════════════
    @Test(priority = 2,
          groups = {"Payment", "Positive"},
          description = "Payment with valid discount details should return 200")
    public void TC_PAY_002_PaymentWithDiscount() {
        extentTest.info("Testing payment with discount=50.00, discount_reason_id=1, approver_id=7");

        Response response = PaymentApi.collectPaymentWithDiscount(
                ConfigManager.EMPLOYEE_ID_2,
                123,
                505.00,
                "2024-12-22",
                "Payment reference",
                50.00,
                1,
                "Special discount",
                7);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-PAY-002", "Payment With Discount PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.1.3: Payment with Zero Amount
    // ══════════════════════════════════════════════
    @Test(priority = 3,
          groups = {"Payment", "Negative"},
          description = "Payment with zero amount should return 400 error")
    public void TC_PAY_003_PaymentZeroAmount() {      
        extentTest.info("Testing payment with amount=0.00");

        Response response = PaymentApi.collectPaymentZeroAmount(
                ConfigManager.EMPLOYEE_ID_2,
                123,
                "2024-12-22",
                "Zero amount test");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-PAY-003", "Payment Zero Amount PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.1.4: Payment with Negative Amount
    // ══════════════════════════════════════════════
    @Test(priority = 4,
          groups = {"Payment", "Negative"},
          description = "Payment with negative amount should return 400 error")
    public void TC_PAY_004_PaymentNegativeAmount() {
        extentTest.info("Testing payment with amount=-100.00");

        Response response = PaymentApi.collectPaymentNegativeAmount(
                ConfigManager.EMPLOYEE_ID_2,
                123,
                "2024-12-22",
                "Negative amount test");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-PAY-004", "Payment Negative Amount PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.1.5: Payment — Missing house_id
    // ══════════════════════════════════════════════
    @Test(priority = 5,
          groups = {"Payment", "Negative"},
          description = "Payment without house_id should return 400 MISSING_REQUIRED_FIELDS")
    public void TC_PAY_005_PaymentMissingHouseId() {
        extentTest.info("Testing payment without house_id field");

        Response response = PaymentApi.collectPaymentMissingHouseId(
                ConfigManager.EMPLOYEE_ID_2,
                505.00,
                "2024-12-22");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-PAY-005", "Payment Missing House ID PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.1.6: Payment — Missing payment_date
    // ══════════════════════════════════════════════
    @Test(priority = 6,
          groups = {"Payment", "Negative"},
          description = "Payment without payment_date should return 400 error")
    public void TC_PAY_006_PaymentMissingDate() {
        extentTest.info("Testing payment without payment_date field");

      PaymentApi.collectPaymentMissingDate();
    }
    
    // 5.1.4      without reason
    
    @Test(priority = 7,
            groups = {"Payment", "Negative"},
            description = "Payment without reason return 400 error")
    public void _Payment_overdiscount_5_1_4() {
    	   extentTest.info("Testing payment without payment_date field");
    	PaymentApi.tc_5_1_7_limitExceed();
    	
    }
    
    
    @Test(priority = 8,
            groups = {"Payment", "Negative"},
            description = "Payment without reason return 403 error")
    public void _Payment_without_reason_5_1_4() {
    	PaymentApi.tc_5_1_4_MISSING_DISCOUNT_REASON();
    	
    }
    
    
    @Test(priority = 9,
            groups = {"Payment", "Negative"},
            description = "Payment without reason return 403 error")
    public void _Paymentlimiexceed() {
    	
    	
    }
    
    @Test(priority = 10,
            groups = {"Payment", "Negative"},
            description = "Payment without reason return 400 error")
    public void _Payment_NOT_AUTHORIZED_5_1_6() {
    	PaymentApi.tc_5_1_6_AUTHORIZED_Discount();
    	
    }
    


@Test(priority = 11,
        groups = {"Payment", "Negative"},
        description = "Payment without discountreturn 400 error")
public void nonDsount_5_1_8() {
	PaymentApi.tc_5_1_8_NotAllowDiscount();
	
	
	
}



@Test(priority = 12,
groups = {"Payment", "Negative"},
description = "Payment without discountreturn 400 error")
public void unpaidInvoices_5_1_8_() {
PaymentApi.tc_5_1_9_NO_UNPAID_INVOICES ();

}
    
@Test(priority = 13,
groups = {"Payment", "Negative"},
description = "Payment without discountreturn 400 error")
public void overpayment_5_1_9_() {
PaymentApi.collectoverpayment ();

}
    



    
    
    
    
    

    // ════════════════════════════════}══════════════
    // TC-5.1.14: Full Payment with All Discount Fields
    // ══════════════════════════════════════════════
    @Test(priority = 7,
          groups = {"Payment", "Positive"},
          description = "Full payment with all discount fields should return 200")
    public void TC_PAY_007_FullPaymentWithAllDiscountFields() {
        extentTest.info("Testing full payment with all discount fields");

        Response response = PaymentApi.collectPaymentFullWithDiscount(
                ConfigManager.EMPLOYEE_ID_2,
                123,
                505.00,
                "2024-12-22",
                "Payment reference",
                50.00,
                1,
                "Special discount",
                7);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-PAY-007", "Full Payment With All Discount Fields PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.2.1: Valid Collection Summary
    // ══════════════════════════════════════════════
    @Test(priority = 8,
          groups = {"Payment", "Positive"},
          description = "Valid collection summary request should return 200")
    public void TC_PAY_008_ValidCollectionSummary() {
        extentTest.info("Testing collection summary: employee_id=12, date=2026-03-16");

        Response response = PaymentApi.getCollectionSummary(ConfigManager.EMPLOYEE_ID, "2026-03-16");

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-PAY-008", "Valid Collection Summary PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.2.2: Collection Summary — Missing Date
    // ══════════════════════════════════════════════
    @Test(priority = 9,
          groups = {"Payment", "Negative"},
          description = "Collection summary without date should return 400")
    public void TC_PAY_009_CollectionSummaryMissingDate() {
        extentTest.info("Testing collection summary without date field");

        Response response = PaymentApi.getCollectionSummaryMissingDate(ConfigManager.EMPLOYEE_ID);

        ResponseValidator.assertStatusCode(response, 200);
        LoggerUtil.pass("TC-PAY-009", "Collection Summary Missing Date PASSED");
    }

    // ══════════════════════════════════════════════
    // TC-5.2.3: Collection Summary — Invalid Date Format
    // ══════════════════════════════════════════════
    @Test(priority = 10,
          groups = {"Payment", "Negative"},
          description = "Invalid date format should return 400 error")
    public void TC_PAY_010_CollectionSummaryInvalidDate() {
        extentTest.info("Testing collection summary with invalid date format 'invalid-date'");

        Response response = PaymentApi.getCollectionSummaryInvalidDate(
                ConfigManager .EMPLOYEE_ID, "invalid-date");

        ResponseValidator.assertStatusCode(response, 400);
        LoggerUtil.pass("TC-PAY-010", "Collection Summary Invalid Date PASSED");
    }
    
    
    
    
    //  waiting payment 5.3 all
    	//5.3.1
    	
    	
    @Test(priority = 11,
            groups = {"Payment", "Negative"},
            description = "without filter format should return 200 error")
      public void TC_PAY_5_3_1_waitingPayement() {
    	  extentTest.info("Testing collection summary with invalid date format 'invalid-date'");
    	  
    	PaymentApi.tc_5_3_1_waitingPayment();
    
}
     
    
    //5.3.2
    
    @Test(priority = 12,
            groups = {"Payment", "Negative"},
            description = "with employee id format should return 200 error")
      public void TC_PAY_5_3_2_waitingPayementWithEmployeeId() {
    	  extentTest.info("Testing waiting payment with employee id'");
    	  
    	PaymentApi.tc_5_3_2_waitingPaymentWithEmployrrId();
    
}
    @Test(priority = 13,
            groups = {"Payment", "Negative"},
            description = "with employee id format should return 200 error")
      public void TC_PAY_5_3_3_waitingPayementWithDate() {
    	  extentTest.info("Testing waiting payment with Date'");
    	  
    	PaymentApi.tc_5_3_3_waitingPaymentWithDate();
    
}
    
    
    @Test(priority = 14,
            groups = {"Payment", "Negative"},
            description = "with employee id format should return 200 error")
      public void TC_PAY_5_3_4_waitingPayementWithApprover() {
    	  extentTest.info("Testing waiting payment with Date'");
    	  
    	PaymentApi.tc_5_3_4_waitingPaymentWithApprove();
    
}
    
    
    @Test(priority = 15,
            groups = {"Payment", "Negative"},
            description = "with non approver id format should return 200 error")
      public void TC_PAY_5_3_5_waitingPayementWithNonApprover() {
    	  extentTest.info("Testing waiting payment with Date'");
    	  
    	PaymentApi.tc_5_3_5_waitingPaymentWithNonApprove();
    
}
    
    
    @Test(priority = 16,
            groups = {"Payment", "Negative"},
            description = "with non limit and offset format should return 200 error")
      public void TC_PAY_5_3_6_waitingPayementWithLimitorOffset() {
    	  extentTest.info("Testing waiting paymentlimit and offset'");
    	  
    	PaymentApi.tc_5_3_6_waitingPaymentWithLimitOrOffset();
    
    }
    
    
    // Approved payment 5.4 all
    
    // 5.4.1
    
    @Test(priority = 17,
            groups = {"Payment", "Negative"},
            description = "with non limit and offset format should return 200 error")
      public void TC_PAY_5_4_1_approvedPaymentwithoutFilter() {
    	  extentTest.info("Testing Approved payment without filter '");
    	  
    	PaymentApi.tc_5_4_1_approvedPaymentwithoutFilter();
    
    }
    
    //5.4.2
    
    @Test(priority = 18,
            groups = {"Payment", "Negative"},
            description = "with employee Id format should return 200 error")
      public void TC_PAY_5_4_2_approvedPaymentemployeeId() {
    	  extentTest.info("Testing Approved payment employee id '");
    	  
    	PaymentApi.	tc_5_4_2_approvedPaymentEmployeeId();
    
    }
    
    
    @Test(priority = 19,
            groups = {"Payment", "Negative"},
            description = "with Zone Id format should return 200 error")
      public void TC_PAY_5_4_3_approvedPaymenWithZoneId() {
    	  extentTest.info("Testing Approved payment Zone id '");
    	  
    	PaymentApi.	tc_5_4_3_approvedPaymentZoneID();
    
    }
    
    // Reject payment 5.5 all
     
    @Test(priority = 20,
            groups = {"Payment", "Negative"},
            description = "with without filter format should return 200 error")
      public void TC_PAY_5_5_1_rejectPayment() {
    	  extentTest.info("Testing reject payment without filter ");
    	  
    	PaymentApi.	tc_5_5_1_rejectPayment();
    
    }
    
    //5.5.2
    
    
    @Test(priority = 20,
            groups = {"Payment", "Negative"},
            description = "with employee id format should return 200 error")
      public void TC_PAY_5_5_2_rejectPaymentEmployee() {
    	  extentTest.info("Testing reject payment with employee id ");
    	  
    	PaymentApi.	tc_5_5_2_rejectPaymeEmployee();
    
    }
    
    
    @Test(priority = 21,
            groups = {"Payment", "Negative"},
            description = "with employee id format should return 200 error")
      public void TC_PAY_5_5_3_rejectPaymentZoneId() {
    	  extentTest.info("Testing reject payment with zone id ");
    	  
    	PaymentApi.	tc_5_5_3_rejectPaymentZoneID();
    
    }
    
    
    @Test(priority = 22,
            groups = {"Payment", "Negative"},
            description = "with valid format should return 200 error")
      public void TC_PAY_5_6_1_approvePayment() {
    	  extentTest.info("Testing approve payment with valid ");
    	  
    	PaymentApi.tc_5_6_1_approvePayment();
    
    }
    //5.6.2
    
    @Test(priority = 23,
            groups = {"Payment", "Negative"},
            description = "with non approve format should return 403 error")
      public void TC_PAY_5_6_2_approvePaymentwithNonApprove() {
    	  extentTest.info("Testing approve payment with non approve ");
    	  
    	PaymentApi.tc_5_6_2_approvePaymentNonApprove();
    
    }
    
    //5.6.3
    @Test(priority = 24,
            groups = {"Payment", "Negative"},
            description = "with non assign format should return 403 error")
      public void TC_PAY_5_6_3_approvePaymentwithNonAssign() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_6_3_approvePaymentNonAssign();
    
    }
    
    //5.6.4
    @Test(priority = 25,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_6_4_approvePaymentwithallready() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_6_4_approvePaymentAllready();
    
    }
    
    //5.6.5
    @Test(priority = 26,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_6_5_approvePaymentwithInvalidPaymentId() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_6_5_approvePaymentInvalidPaymentId();
    
    }
    //--------------
    
    
    
    
   
    //Reject all payment  5.7
    // 5.7.1  
    @Test(priority = 27,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_7_1_rejectPaymentvalid() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_7_1_rejectPament();
    
    }
    
    
    //5.7.2 
    
    @Test(priority = 28,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_7_2_rejectPaymentNonApprover() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_7_2_rejectPaymentNonApprove();
    
    }
    
    //5.7.3
    @Test(priority = 29,
            groups = {"Payment", "Negative"},
            description = "with non assign format should return 422 error")
      public void TC_PAY_5_7_3_rejectPaymentNonassign() {
    	  extentTest.info("Testing reject payment with non assign ");
    	  
    	PaymentApi.tc_5_7_3_rejectPaymentNonAssign();
    
    }
    @Test(priority = 29,
            groups = {"Payment", "Negative"},
            description = "with non assign format should return 422 error")
      public void TC_PAY_5_7_4_rejectPaymentAllready() {
    	  extentTest.info("Testing reject payment with allready ");
    	  
    	PaymentApi.tc_5_7_4_rejectPaymentAllready();
    
    }
  //Post all payment  5.
    // 5.8.1  
    @Test(priority = 30,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_8_1_postPaymentvalid() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_8_1_postPayment();
    
    }
    
    @Test(priority = 31,
            groups = {"Payment", "Negative"},
            description = "with allready payment format should return 422 error")
      public void TC_PAY_5_8_2_postPaymentallready() {
    	  extentTest.info("Testing approve payment with nonassign ");
    	  
    	PaymentApi.tc_5_8_2_postPaymentallready();
    
    }
    
    //5.8.3
    @Test(priority = 32,
            groups = {"Payment", "Negative"},
            description = "with waiting format should return 422 error")
      public void TC_PAY_5_8_3_postPaymentWaiting() {
    	  extentTest.info("Testing post payment with waiting ");
    	  
    	PaymentApi.tc_5_8_3_postPaymentWaiting();
    
    }
    
    
    
    
}
