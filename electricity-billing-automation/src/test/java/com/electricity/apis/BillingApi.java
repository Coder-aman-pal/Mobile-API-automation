package com.electricity.apis;

import com.electricity.utils.ConfigManager;
import com.electricity.utils.RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;

/**
 * BillingApi — All Billing API methods. Covers: Generate Bill (TC-4.1.x),
 * Download Bill PDF (TC-4.2.x)
 */
public class BillingApi {

	// ══════════════════════════════════════════════
	// GENERATE BILL APIs
	// ══════════════════════════════════════════════

	/** TC-4.1.1: Generate bill — rate = 0 (zero rate) */
	public static Response generateBillZeroRate(int employeeId, int houseId, double oldReading, double newReading,
			double rate, String billingDate, String remarks) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", remarks);
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	
	/** TC-4.1.2: Generate bill — valid with rate > 0 */
	public static Response generateBillValid(int employeeId, int houseId, double  oldReading, double newReading,
			double rate, String billingDate, String remarks) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", remarks);
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}
	
	
	public static Response generateBillValidminimumbilling(int employeeId, int houseId, double oldReading, double newReading,
			double rate, String billingDate, String remarks) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", remarks);
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}
	public static Response generateBillalready(int employeeId, int houseId, double oldReading, double newReading,
			double rate, String billingDate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	/** TC-4.1.3: Generate bill — new reading < old reading */
	public static Response generateBillNegativeUnits(int employeeId, int houseId, double oldReading, double newReading,
			double rate, String billingDate, String remarks) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", remarks);
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	/** TC-4.1.4: Generate bill — rate = 0, valid readings */
	public static Response generateBillWithZeroRateValidReadings(int employeeId, int houseId, double oldReading,
			double newReading, double rate, String billingDate, String remarks) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", 0.0);
		body.put("billing_date", billingDate);
		body.put("remarks", "");
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	/** TC-4.1.5: Generate bill — missing house_id */
	public static Response generateBillMissingHouseId(int employeeId, double oldReading, double newReading, double rate,
			String billingDate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", "");
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	/** TC-4.1.6: Generate bill — missing billing_date */
	public static Response generateBillMissingoldreading(int employeeId, int houseId, double newReading, double rate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("remarks", "");
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	/** TC-4.1.7: Generate bill — bill already exists for this period */
	public static Response generateBillsamereading(int employeeId, int houseId, double oldReading, double newReading,
			double rate, String billingDate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("rate", rate);
		body.put("billing_date", billingDate);
		body.put("remarks", "");
		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
	}

	// ══════════════════════════════════════════════
	// TC-4.1.8: Missing Rate
	// ══════════════════════════════════════════════

	public static  Response TC_BILL_008_MissingRate(int employeeId, int houseId, double oldReading, double newReading,
			String billingDate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("billing_date", billingDate);
		body.put("remarks", "");

		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());

	}

	// ══════════════════════════════════════════════
	// TC-4.1.9: Invalid House ID
	// ══════════════════════════════════════════════

	public static  Response TC_BILL_009_InvalidHouseId(int employeeId, int houseId, double oldReading, double newReading,
			String billingDate) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		body.put("house_id", houseId);
		body.put("old_reading", oldReading);
		body.put("new_reading", newReading);
		body.put("billing_date", billingDate);
		body.put("remarks", "");

		return RequestBuilder.post(ConfigManager.GENERATE_BILL_URL, body.toString());
		
	
	}
	public static void generateBillMissingNewReading() {
		
		String body="{ \n"
				+ " \"employee_id\": 5, \n"
				+ " \"house_id\": 59, \n"
				+ " \"old_reading\": 403, \n"
				+ " \"rate\": 0.3, \n"
				+ " \"billing_date\": \"2026-03-25\"\n"
				+ "}";
		 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
           		.body(body).when().post(ConfigManager.GENERATE_BILL_URL).then()
                   .statusCode(400)  // Expected status code
                   .extract().response();
        String lastresp= response.asPrettyString ();
           System.out.println(lastresp);
		
	}
public static void generateBillvalid_OldReading_0() {
		
		String body="{ \n"
				+ " \"employee_id\": 12, \n"
				+ " \"house_id\": 2821, \n"
				+ " \"old_reading\": 0.0, \n"
				+ " \"new_reading\": 100,\n"
				+ " \"rate\": 0.3,\n"
				+ " \"billing_date\": \"2026-03-27\",\n"
				+ " \"remarks\": \"\"\n"
				+ "}";
		 Response response=  RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).header("Content-Type", "application/json")
           		.body(body).when().post(ConfigManager.GENERATE_BILL_URL).then()
                   .statusCode(400)  // Expected status code
                   .extract().response();
        String lastresp= response.asPrettyString ();
           System.out.println(lastresp);
		
	}
	
	
	
	
	// ══════════════════════════════════════════════
	// DOWNLOAD BILL PDF APIs
	// ══════════════════════════════════════════════

	/** TC-4.2.1: Download bill PDF — valid bill_id */
	public static void downloadBillValid(int employeeId, String downloaddate) {

		String body = "{\n" + "  \"employee_id\": " + employeeId + "\n" + "}";

		Response response = RestAssured.given().header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN)
				.header("Content-Type", "application/json").body(body).when()
				.post(ConfigManager.BASE_URL + ConfigManager.DownloadBillValidBillId).then().extract().response();

		// ══════════════════════════════════════════════
		// Step 1 — Status Check
		// ══════════════════════════════════════════════
		System.out.println("📡 Status Code  : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200, "❌ Status code mismatch");

		// ══════════════════════════════════════════════
		// Step 2 — Content-Type Check
		// ══════════════════════════════════════════════
		String contentType = response.getContentType();
		System.out.println("📄 Content-Type : " + contentType);
		Assert.assertTrue(contentType.contains("pdf"), "❌ Expected PDF but got: " + contentType);

		// ══════════════════════════════════════════════
		// Step 3 — PDF Bytes nikalo
		// ══════════════════════════════════════════════
		byte[] pdfBytes = response.asByteArray();
		System.out.println("📦 PDF Size     : " + pdfBytes.length + " bytes");
		Assert.assertTrue(pdfBytes.length > 0, "❌ PDF is empty!");

		// ══════════════════════════════════════════════
		// Step 4 — Folder banao
		// ══════════════════════════════════════════════
		java.io.File folder = new java.io.File("test-output/home/download");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		System.out.println("📁 Save Folder  : " + folder.getAbsolutePath());

		// ══════════════════════════════════════════════
		// Step 5 — File save karo
		// ══════════════════════════════════════════════
		String fileName = "Bill_employee_" + employeeId + "_"
				+ new java.text.SimpleDateFormat(downloaddate).format(new java.util.Date()) + ".pdf";

		java.io.File pdfFile = new java.io.File(folder, fileName);

		try {
			java.nio.file.Files.write(pdfFile.toPath(), pdfBytes);
		} catch (java.io.IOException e) {
			Assert.fail("❌ PDF save error: " + e.getMessage());
		}

		// ══════════════════════════════════════════════
		// Step 6 — Verify file save hui
		// ══════════════════════════════════════════════
		Assert.assertTrue(pdfFile.exists(), "❌ PDF file exist nahi karti: " + pdfFile.getAbsolutePath());
		Assert.assertTrue(pdfFile.length() > 0, "❌ PDF file empty hai!");
		Assert.assertEquals(pdfFile.length(), pdfBytes.length, "❌ PDF size mismatch!");

		// ══════════════════════════════════════════════
		// Step 7 — Success Print
		// ══════════════════════════════════════════════
		System.out.println("╔══════════════════════════════════════════════╗");
		System.out.println("  ✅ PDF Downloaded Successfully!");
		System.out.println("  📄 File Name    : " + fileName);
		System.out.println("  📁 Full Path    : " + pdfFile.getAbsolutePath());
		System.out.println("  📦 File Size    : " + pdfFile.length() + " bytes");
		System.out.println("  ✅ File Exists  : " + pdfFile.exists());
		System.out.println("╚══════════════════════════════════════════════╝");

		System.out.println("✅ TC_BILL_DOWNLOAD_001 PASS");
	}

	/** TC-4.2.2: Download bill PDF — invalid/non-existent bill_id */
	public static Response downloadBillInvalid(int employeeId) {
		JSONObject body = new JSONObject();
		body.put("employee_id", employeeId);
		return RequestBuilder.post(ConfigManager.DOWNLOAD_BILL_INVALIDURL, body.toString());
	}

	public static void tc_4_2_2_missingEmployeeId() {
		Map<String, Object> body = new HashMap<>();
		// employee_id intentionally omitted

		Response res = RestAssured.given().contentType("Application/json")
				.header("Authorization", "Bearer " + ConfigManager.BEARER_TOKEN).body(body).when()
				.post(ConfigManager.BASE_URL + ConfigManager.DownloadBillValidBillId).then().statusCode(400).extract()
				.response();

		System.out.println(res.asPrettyString());

		System.out.println("TC 4.2.2 PASSED – 400 returned for missing employee_id");
	}

	// TC 4.2.3 – Invalid (non-existent) bill number → 404 BILL_NOT_FOUND

	// TC 4.2.4 – Invalid API key → 401 INVALID_API_KEY

	public static void tc_4_2_4_invalidApiKey() {
		Map<String, Object> body = new HashMap<>();
		body.put("employee_id", ConfigManager.EMPLOYEE_ID_2);

		Response res = RestAssured.given().contentType("Application/json")
				.header("Authorization", "Bearer " + ConfigManager.INVALID_BEARER_TOKEN).body(body).when()
				.post(ConfigManager.BASE_URL + ConfigManager.DownloadBillValidBillId).then().statusCode(401).extract()
				.response();

		System.out.println(res.asPrettyString());
		System.out.println("TC 4.2.4 PASSED – 401 returned for invalid API key");
	}

//_________________________________________

}
