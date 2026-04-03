package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.AuthenticationSpecification;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.Authenticator;

import org.json.JSONObject;

/**
 * PaymentApi — All Payment API methods.
 * Covers: Collect Payment (TC-5.1.x), Get Collection Summary (TC-5.2.x)
 */
public class PaymentApi {

    // ══════════════════════════════════════════════
    // COLLECT PAYMENT APIs
    // ══════════════════════════════════════════════

    /** TC-5.1.1: Valid payment — full amount, no discount */
    public static Response collectPaymentValid(int employeeId, int houseId, double amount
                                                ) {
        JSONObject body = new JSONObject();
        body.put("employee_id",   employeeId);
        body.put("house_id",      houseId);
        body.put("amount",        amount);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    /** TC-5.1.2: Payment with discount */
    public static Response collectPaymentWithDiscount(int employeeId, int houseId, double amount,
                                                       String paymentDate, String reference,
                                                       double discount, int discountReasonId,
                                                       String discountRemark, int approverId) {
        JSONObject body = new JSONObject();
        body.put("employee_id",                    employeeId);
        body.put("house_id",                       houseId);
        body.put("amount",                         amount);
        body.put("payment_date",                   paymentDate);
        body.put("reference",                      reference);
        body.put("discount",                       discount);
        body.put("discount_reason_id",             discountReasonId);
        body.put("discount_remark",                discountRemark);
        body.put("discount_collector_approver_id", approverId);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    /** TC-5.1.3: Payment with zero amount */
    public static Response collectPaymentZeroAmount(int employeeId, int houseId,
                                                     String paymentDate, String reference) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("amount",       0.0);
        body.put("payment_date", paymentDate);
        body.put("reference",    reference);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    /** TC-5.1.4: Payment with negative amount */
    public static Response collectPaymentNegativeAmount(int employeeId, int houseId,
                                                         String paymentDate, String reference) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("house_id",     houseId);
        body.put("amount",       -100.00);
        body.put("payment_date", paymentDate);
        body.put("reference",    reference);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    /** TC-5.1.5: Payment — missing house_id */
    public static Response collectPaymentMissingHouseId(int employeeId, double amount,
                                                          String paymentDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id",  employeeId);
        body.put("amount",       amount);
        body.put("payment_date", paymentDate);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    /** TC-5.1.6: Payment — missing payment_date */
    public static void collectPaymentMissingDate() {
  String body="{ \n"
  		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID_2+", \n"
  		+ " \"house_id\": "+ConfigManager.HOUSE_ID+", \n"
  		+ " \"amount\": "+ConfigManager.amount+"\n"
  		+ "}\n"
  		+ "";
        Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
          		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                  .statusCode(422)  // Expected status code
                  .extract().response();
       String lastresp= response.asPrettyString ();
          System.out.println(lastresp);
    }
    
    
 
    
    
    
    
    // TC 5.1.4 – Discount without reason → 400 MISSING_DISCOUNT_REASON
    public static void tc_5_1_4_MISSING_DISCOUNT_REASON() {
    String body="{ \n"
    		+ " \"employee_id\":"+ConfigManager.EMPLOYEE_ID+", \n"
    		+ " \"house_id\": "+ConfigManager.HOUSE_ID_2+", \n"
    		+ " \"amount\": "+ConfigManager.amountpay+",\n"
    		+ " \"discount\": "+ConfigManager.discount2+"\n"
    		+ "}";
        // discount_reason_id intentionally omitted
 
      Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
        		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                .statusCode(400)  // Expected status code
                .extract().response();
     String lastresp= response.asPrettyString ();
        System.out.println(lastresp);
    }
    
    public static void tc_5_1_5_overDiscount() {
    	 String body="{ \n"
    	 		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID_2+", \n"
    	 		+ " \"house_id\": "+ConfigManager.house_id+", \n"
    	 		+ " \"amount\": "+ConfigManager.amount_2+", \n"
    	 		+ " \"discount\": "+ConfigManager.discountover+",\n"
    	 		+ "  \"discount_reason_id\": "+ConfigManager.discount_reason_id+"\n"
    	 		+ "}";
            // discount_reason_id intentionally omitted
     
          Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
            		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                    .statusCode(400)  // Expected status code
                    .extract().response();
         String lastresp= response.asPrettyString ();
            System.out.println(lastresp);
        }
    
    public static void tc_5_1_6_AUTHORIZED_Discount() {
   	 String body="{ \n"
   	 		+ " \"employee_id\":"+ConfigManager.EMPLOYEE_ID_2+", \n"
   	 		+ " \"house_id\":  "+ConfigManager.house_id+", \n"
   	 		+ " \"amount\":"+ConfigManager.amount_2+", \n"
   	 		+ " \"discount\": "+ConfigManager.discountover+",\n"
   	 		+ "  \"discount_reason_id\": "+ConfigManager.discount_reason_id+",\n"
   	 		+ "   \"discount_collector_approver_id\": "+ConfigManager.EMPLOYEE_ID+"\n"
   	 		+ "}";
           // discount_reason_id intentionally omitted
    
         Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
           		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                   .statusCode(403)  // Expected status code
                   .extract().response();
        String lastresp= response.asPrettyString ();
           System.out.println(lastresp);
       }
    
    public static void tc_5_1_7_limitExceed() {
      	 String body="{ \n"
      	 		+ " \"employee_id\":"+ConfigManager.EMPLOYEE_ID_2+", \n"
      	 		+ " \"house_id\":  "+ConfigManager.house_id+", \n"
      	 		+ " \"amount\":"+ConfigManager.amount_2+", \n"
      	 		+ " \"discount\": "+ConfigManager.discountover+",\n"
      	 		+ "  \"discount_reason_id\": "+ConfigManager.discount_reason_id+",\n"
      	 		+ "   \"discount_collector_approver_id\": "+ConfigManager.NOT_APPROVER_ID+"\n"
      	 		+ "}";
              // discount_reason_id intentionally omitted
       
            Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
              		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                      .statusCode(422)  // Expected status code
                      .extract().response();
           String lastresp= response.asPrettyString ();
              System.out.println(lastresp);
          }
    
    
    public static void tc_5_1_8_NotAllowDiscount() {
        String body="{ \n"
    	 		+ " \"employee_id\": "+ConfigManager.NonDiscountEmployeeId+", \n"
    	 		+ " \"house_id\": "+ConfigManager.nondiscountHouse+", \n"
    	 		+ " \"amount\": "+ConfigManager.nondiscountamount+", \n"
    	 		+ " \"discount\": "+ConfigManager.discountover+",\n"
    	 		+ "  \"discount_reason_id\": "+ConfigManager.discount_reason_id+"\n"
    	 		+ "}";
            // discount_reason_id intentionally omitted
     
          Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
            		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                    .statusCode(403)  // Expected status code
                    .extract().response();
         String lastresp= response.asPrettyString ();
            System.out.println(lastresp);
        }
    

    public static void tc_5_1_9_NO_UNPAID_INVOICES() {
        String body="{ \n"
        		+ " \"employee_id\": 3, \n"
        		+ " \"house_id\": 24, \n"
        		+ " \"amount\":4300,\n"
        		+ " \"discount\":5,\n"
        		+ " \"discount_reason_id\": 1\n"
        		+ "}";
            // discount_reason_id intentionally omitted
     
          Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
            		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
                    .statusCode(422)  // Expected status code
                    .extract().response();
         String lastresp= response.asPrettyString ();
            System.out.println(lastresp);
        }
    public static void collectoverpayment() {
    	  String body="{ \n"
    	  		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID_2+", \n"
    	  		+ " \"house_id\": "+ConfigManager.over_payment_house+", \n"
    	  		+ " \"amount\": "+ConfigManager.over_payment+"\n"
    	  		+ "}\n"
    	  		+ "";
    	        Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
    	          		.body(body).when().post(ConfigManager.COLLECT_PAYMENT_URL).then()
    	                  .statusCode(200)  // Expected status code
    	                  .extract().response();
    	       String lastresp= response.asPrettyString ();
    	          System.out.println(lastresp);
    	    }
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    /** TC-5.1.14: Full payment with discount details */
    public static Response collectPaymentFullWithDiscount(int employeeId, int houseId, double amount,
                                                           String paymentDate, String reference,
                                                           double discount, int discountReasonId,
                                                           String discountRemark, int approverId) {
        JSONObject body = new JSONObject();
        body.put("employee_id",                    employeeId);
        body.put("house_id",                       houseId);
        body.put("amount",                         amount);
        body.put("payment_date",                   paymentDate);
        body.put("reference",                      reference);
        body.put("discount",                       discount);
        body.put("discount_reason_id",             discountReasonId);
        body.put("discount_remark",                discountRemark);
        body.put("discount_collector_approver_id", approverId);
        return RequestBuilder.post(ConfigManager.COLLECT_PAYMENT_URL, body.toString());
    }

    // ══════════════════════════════════════════════
    // COLLECTION SUMMARY APIs
    // ══════════════════════════════════════════════

    /** TC-5.2.1: Valid collection summary */
    public static Response getCollectionSummary(int employeeId, String date) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("date",        date);
        return RequestBuilder.post(ConfigManager.BASE_URL+ ConfigManager.COLLECTION_SUMMARY_URL, body.toString());
    }

    /** TC-5.2.2: Collection summary — missing date */
    public static Response getCollectionSummaryMissingDate(int employeeId) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        return RequestBuilder.post(ConfigManager.BASE_URL+ ConfigManager.COLLECTION_SUMMARY_URL, body.toString());
    }

    /** TC-5.2.3: Collection summary — invalid date format */
    public static Response getCollectionSummaryInvalidDate(int employeeId, String invalidDate) {
        JSONObject body = new JSONObject();
        body.put("employee_id", employeeId);
        body.put("date",        invalidDate);
        return RequestBuilder.post(ConfigManager.COLLECTION_SUMMARY_URL, body.toString());
    
    }
    
    
    //waiting payment  5.3.1
    
    public  static void tc_5_3_1_waitingPayment() {
    	
    	String body="{ \n"
    			+ "} \n"
    			+ "";
    	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
         		.body(body).when().post(ConfigManager.waiting_payment_url).then()
                 .statusCode(200)  // Expected status code
                 .extract().response();
      String lastresp= response.asPrettyString ();
         System.out.println(lastresp);
    	
    }
    
    
    //waiting payment 5.4.2
    
 public  static void tc_5_3_2_waitingPaymentWithEmployrrId() {
    	
    	String body="{ \n"
    			+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID+"\n"
    			+ "} ";
    	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
         		.body(body).when().post(ConfigManager.waiting_payment_url).then()
                 .statusCode(200)  // Expected status code
                 .extract().response();
      String lastresp= response.asPrettyString ();
         System.out.println(lastresp);
    	
    }
 
 // waiting payment with date
 public  static void tc_5_3_3_waitingPaymentWithDate() {
 	
 	String body="{ \n"
 			+ " \"date_from\": \""+ConfigManager.FromDate+"\", \n"
 			+ " \"date_to\": \""+ConfigManager.ToDate+"\"\n"
 			+ "} ";
 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
      		.body(body).when().post(ConfigManager.waiting_payment_url).then()
              .statusCode(200)  // Expected status code
              .extract().response();
   String lastresp= response.asPrettyString ();
      System.out.println(lastresp);
 	
 }
 // waiting payment approver 5.3.4
 
 public  static void tc_5_3_4_waitingPaymentWithApprove() {
	 	
	 	String body="{ \n"
    			+ " \"employee_id\": "+ConfigManager.Employee_approve_id+"\n"
    			+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.waiting_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 //non approver waiting payment 5.3.5
 
 public  static void tc_5_3_5_waitingPaymentWithNonApprove() {
	 	
	 	String body="{ \n"
 			+ " \"employee_id\": "+ConfigManager.Employee_Non_approve_id+"\n"
 			+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post("https://electent.spc.softprime.in/api/v19/payment/waiting-approval").then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 public  static void tc_5_3_6_waitingPaymentWithLimitOrOffset() {
	 String body ="{ \n"
		 		+ "  \"limit\": "+ConfigManager.limit+", \n"
		 		+ " \"offset\": "+ConfigManager.offset+"\n"
		 		+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.waiting_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 // approved payment 5.4 all 
 
 public  static void tc_5_4_1_approvedPaymentwithoutFilter() {
	 String body ="{ \n"
	 		+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approved_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 // 5.4.2 approved payment 
 
 public  static void tc_5_4_2_approvedPaymentEmployeeId() {
	 String body ="{ \n"
	 		+ "\"employee_id\":"+ConfigManager.EMPLOYEE_ID+"\n"
	 		+ "}";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approved_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 //5.4.3
 public  static void tc_5_4_3_approvedPaymentZoneID() {
	 String body ="{ \n"
	 		+ "\"employee_id\":"+ConfigManager.EMPLOYEE_ID+",\n"
	 		+ " \"zone_id\": "+ConfigManager.ZONE_ID+"\n"
	 		+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approved_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 //  get Rejected  payment 5.5 all
 public  static void tc_5_5_1_rejectPayment() {
	 String body ="{ \n"
	 		+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejected_payment_URl).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 //5.5.2
 
 public  static void tc_5_5_2_rejectPaymeEmployee() {
	 String body ="{ \n"
	 		+ "\"employee_id\":"+ConfigManager.EMPLOYEE_ID+"\n"
	 		+ "}";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejected_payment_URl).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 public  static void tc_5_5_3_rejectPaymentZoneID() {
	 String body ="{ \n"
	 		+ "\"employee_id\":"+ConfigManager.EMPLOYEE_ID+",\n"
	 		+ " \"zone_id\": "+ConfigManager.ZONE_ID+"\n"
	 		+ "} ";
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejected_payment_URl).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
	 	
	 }
 
 //payment approve 5.6 all
 //5.6.1 valid 
 
 public  static void tc_5_6_1_approvePayment() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.Approve_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
      
 }
 
 //5.6.2
 
 public  static void tc_5_6_2_approvePaymentNonApprove() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(403)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 public  static void tc_5_6_3_approvePaymentNonAssign() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.non_assign_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(403)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 // 5.6.5 
 
 public  static void tc_5_6_5_approvePaymentInvalidPaymentId() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.Invalid_payment_Id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(404)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 // 5.6.4
 public  static void tc_5_6_4_approvePaymentAllready() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.payment_Id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(422)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 // 5.6.6.
 
 
 
 
 //5.7 reject payment
 public  static void tc_5_7_1_rejectPament() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.reject_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejecte_payment_URl).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
      
 }
 
 //5.6.2
 
 public  static void tc_5_7_2_rejectPaymentNonApprove() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejecte_payment_URl).then()
	              .statusCode(403)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 public  static void tc_5_7_3_rejectPaymentNonAssign() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.non_assign_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Rejecte_payment_URl).then()
	              .statusCode(403)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 //5.7.4
 
 public  static void tc_5_7_4_rejectPaymentAllready() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.payment_Id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.Approve_payment_url).then()
	              .statusCode(422)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 //post payment all 5.8
 
 //5.8.1
 public  static void tc_5_8_1_postPayment() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.post_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.postPaymentURL).then()
	              .statusCode(200)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
      
 }
 
 //5.6.2
 
 public  static void tc_5_8_2_postPaymentallready() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.EMPLOYEE_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.post_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.postPaymentURL).then()
	              .statusCode(422)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 public  static void tc_5_8_3_postPaymentWaiting() {
	 String body ="{ \n"
	 		+ " \"employee_id\": "+ConfigManager.NOT_APPROVER_ID+", \n"
	 		+ " \"payment_id\": "+ConfigManager.waiting_payment_id+" \n"
	 		+ "} \n"
	 		+ "";
			 
	 	 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
	      		.body(body).when().post(ConfigManager.postPaymentURL).then()
	              .statusCode(422)  // Expected status code
	              .extract().response();
	   String lastresp= response.asPrettyString ();
	      System.out.println(lastresp);
 
 }
 
 
 
 

 
 
 
}
