package com.electricity.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonDataProvider — Reads JSON test data files for data-driven testing.
 * Place JSON files in src/test/resources/testdata/
 */
public class JsonDataProvider {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads a JSON array file and returns as Object[][] for TestNG @DataProvider.
     * Each JSON object in the array becomes one row.
     *
     * Usage:
     *   @DataProvider(name = "loginData")
     *   public Object[][] loginData() {
     *       return JsonDataProvider.getDataFromJson("login_data.json");
     *   }
     */
    public static Object[][] getDataFromJson(String fileName) {
        try {
            InputStream is = JsonDataProvider.class.getClassLoader()
                    .getResourceAsStream("testdata/" + fileName);
            if (is == null) {
                LoggerUtil.warn("⚠️  Test data file not found: " + fileName);
                return new Object[0][0];
            }
            JsonNode root = mapper.readTree(is);
            List<Object[]> dataList = new ArrayList<>();
            if (root.isArray()) {
                for (JsonNode node : root) {
                    dataList.add(new Object[]{node.toString()});
                }
            }
            return dataList.toArray(new Object[0][0]);
        } catch (Exception e) {
            LoggerUtil.error("❌ Error reading JSON data: " + e.getMessage());
            return new Object[0][0];
        }
    }

    /**
     * Parses a JSON string and extracts a field value.
     */
    public static String getField(String json, String field) {
        try {
            JsonNode node = mapper.readTree(json);
            JsonNode value = node.get(field);
            return (value != null) ? value.asText() : "";
        } catch (Exception e) {
            return "";
        }
    }
}
