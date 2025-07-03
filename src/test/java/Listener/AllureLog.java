package Listener;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;
import static io.restassured.filter.log.ResponseLoggingFilter.logResponseTo;

public class AllureLog implements ITestListener {

    private static ByteArrayOutputStream requestLog;
    private static ByteArrayOutputStream responseLog;
    private static String requestUrl = null;
    private static String requestBody = null;
    private static String responseBody = null;
    private static int statusCode = -1;

    @Override
    public void onTestStart(ITestResult result) {
        // Initialize the logs
        requestLog = new ByteArrayOutputStream();
        responseLog = new ByteArrayOutputStream();

        // Configure filters to log request and response
        RestAssured.filters(
                logRequestTo(new PrintStream(requestLog)),
                logResponseTo(new PrintStream(responseLog))
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logStatusCode(); // Log status code to Allure
        attachRequestUrl(requestUrl);
        attachRequestBody(requestBody);
        attachResponseBody(responseBody);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logStatusCode(); // Log status code to Allure
        attachRequestUrl(requestUrl);
        attachRequestBody(requestBody);
        attachResponseBody(responseBody);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logStatusCode(); // Log status code to Allure
        attachRequestUrl(requestUrl);
        attachRequestBody(requestBody);
        attachResponseBody(responseBody);
    }

    // Attachments for Allure Report
    @Attachment(value = "Request URL", type = "text/plain")
    public String attachRequestUrl(String url) {
        return url != null ? url : "No URL captured";
    }

    @Attachment(value = "Request Body", type = "application/json")
    public String attachRequestBody(String body) {
        return body != null ? formatJson(body) : "No Request Body captured";
    }

    @Attachment(value = "Response Body", type = "application/json")
    public String attachResponseBody(String response) {
        return response != null ? formatJson(response) : "No Response Body captured";
    }

    // Log the status code in Allure
    private void logStatusCode() {
        if (statusCode != -1) {
            Allure.step("Response Status Code: " + statusCode);
        } else {
            Allure.step("Response Status Code: Not Available");
        }
    }

    // Helper method to format JSON
    private String formatJson(String json) {
        try {
            if (json.trim().startsWith("{")) {
                org.json.JSONObject jsonObject = new org.json.JSONObject(json);
                return jsonObject.toString(4);
            } else if (json.trim().startsWith("[")) {
                org.json.JSONArray jsonArray = new org.json.JSONArray(json);
                return jsonArray.toString(4);
            }
        } catch (Exception e) {
            return json; // Return raw string if not a valid JSON
        }
        return json;
    }

    // Static methods to capture request details
    public static void setRequestUrl(String url) {
        if (url != null && !url.isEmpty()) {
            requestUrl = url;
        }
    }

    public static void setRequestBody(String body) {
        if (body != null && !body.isEmpty()) {
            requestBody = body;
        }
    }

    public static void setResponseDetails(Response response) {
        if (response != null) {
            responseBody = response.getBody().asString();
            statusCode = response.getStatusCode();
        }
    }
}