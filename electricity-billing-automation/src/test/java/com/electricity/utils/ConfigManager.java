package com.electricity.utils;

/**
 * ConfigManager — Central configuration constants
 * All base URLs, tokens, and employee IDs are managed here.
 */
public class ConfigManager {

    // ── Base URL ──────────────────────────────────────
    public static final String BASE_URL = "https://electent.spc.softprime.in";

    // ── API Endpoints ─────────────────────────────────
    public static final String LOGIN_URL             = "/api/v19/collector/login";
    public static final String CHANGE_PASSWORD_URL   = "/api/v19/employee/change-password";
    public static final String CUSTOMERS_URL         = "/api/v19/collector/customers";
    public static final String SEARCH_CUSTOMERS_URL  = "/api/v19/collector/search-customers";
    public static final String METER_READING_URL     = "/api/v19/meter/reading";
    public static final String GENERATE_BILL_URL     = "/api/v19/bill/generate";
    public static final String DOWNLOAD_BILL_INVALIDURL  = "/api/v19/bill/download/EBILL/2026/";
    public static final String COLLECT_PAYMENT_URL   = "/api/v19/payment/collect";
    public static final String COLLECTION_SUMMARY_URL= "/api/v19/payment/collection-summary";
    public static final String DISCOUNT_REASONS_URL  = "/api/v19/discount/reasons";
    public static final String DISCOUNT_APPROVERS_URL= "/api/v19/discount/approvers";
    public static final String waiting_payment_url="https://electent.spc.softprime.in/api/v19/payment/waiting-approval";
    public static final String Approved_payment_url="https://electent.spc.softprime.in/api/v19/payment/approved";
    
    public static String Rejected_payment_URl="https://electent.spc.softprime.in/api/v19/payment/rejected";
    public static final String Approve_payment_url="https://electent.spc.softprime.in/api/v19/payment/approve";
    public static String Rejecte_payment_URl  ="https://electent.spc.softprime.in/api/v19/payment/reject";
    public static String postPaymentURL="https://electent.spc.softprime.in/api/v19/payment/post";
     
    public static final String DownloadBillValidBillId=     "/api/v19/bill/download/EBILL/2026/00363";
    public static String BillNumber="EBILL/2026/00363";
    public static double amount =60;
    public static double amountpay =60;
    public static double old_reading_geaterthan_new=1450.00;
    public static double new_reading_lessthan_old=1450.00;
    public static double rate=0.3;
    public static double old_reading=100;
    public static double new_reading=150;
    public static double negative_old_reading=-150;
    
    public static String date ="2026-03-06";
    public static double invaliamount =1100;
    public static String resonother="High Consumption";
    public static Boolean InActive=false;
    public static boolean active=true;
    public static int offset=0;
    public static int limit =50;
    public static int company_id=1;
    public static double discount=0.0;
    public static double discount2=7;
    public static int NonDiscountEmployeeId=3;
    public static int nondiscountHouse=70;
    public static double  nondiscountamount=20;
      
    public static double discountover=1200;
    
    public static int Employee_approve_id =14;
    public static int Employee_Non_approve_id =3;
    public static int payment_id=741;
    public static int waiting_payment_id=766;
    //
    public static String FromDate="2020-10-01";
    public static String ToDate="2026-03-17";

    // ── Auth Token ────────────────────────────────────
    public static final String BEARER_TOKEN = "199099a2ef34a445b100fe664199db9d6dd75f9f";
    public static final String INVALID_BEARER_TOKEN = "199099a2ef34a445b100fe664199db9d6dd75f";

    // ── Test Data ─────────────────────────────────────
    public static final String VALID_MOBILE    = "987654321";
    public static final String VALID_PASSWORD  = "123456";
    public static final int    EMPLOYEE_ID     = 12;
    public static final int NOT_APPROVER_ID=14;
   
    
    public static int reject_payment_id=763;
    public static int Approve_payment_id=763;
    public static final int non_approve_id=12;
    public static final int NON_SUBZONE_EMPLOYEE=13;
    public static final int    EMPLOYEE_ID_2   = 5;
    public static final int    EMPLOYEE_ID_3   = 6;
    public static final int    HOUSE_ID        = 59;
    public static int over_payment_house=2717;
    public static int over_payment=10000;
    
    public static final int    HOUSE_ID_BILL   = 2705;
    public static int house_id=2717;
    public static int discount_reason_id= 1;
    public static int HOUSE_ID_2=1711;
    public static double amount_2= 8.0;
    public static final int    ZONE_ID         = 44;
    public static final int    SUB_ZONE_ID     = 90;
    public static final int    INVALID_HOUSE_ID        = 1920;
    public static int non_assign_payment_id=658;
    public static int payment_Id=741;
    public static int Invalid_payment_Id=17527;
    public static int post_payment_id=763;
    
    
    
    
    

    // ── Content Type ──────────────────────────────────
    public static final String CONTENT_TYPE    = "application/json";

    // ── Report Path ───────────────────────────────────
    public static final String REPORT_PATH     = "test-output/reports/ElectricityAPI_Report.html";
}
