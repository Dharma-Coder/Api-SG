package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SG_NG_Analytics {

    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Analytics_Ng_Excel.xlsx").getAbsolutePath();

    private void updateAllureTestCase(String Description) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setName(Description);
            testResult.setDescription(Description);
        });
    }

    @DataProvider(name = "All_Forest_Weather_Details")
    public Object[][] All_Forest_Weather_Details() {
        return TestDataExtractor.ExcelData(workbook, "All_Forest_Weather_Details");
    }
    @Test(dataProvider = "All_Forest_Weather_Details")
    @Story("This story belongs to the All_Forest_Weather_Details")
    public void AllForestWeatherDetails(String page_number,String expected_status_code,String description) {
        //Call to the method for Authorization Token

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/weather/details/view/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("page_number", page_number);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode() + "", expected_status_code);
    }
    @DataProvider(name ="Trend_line")
    public Object[][] Trend_line(){
        return TestDataExtractor.ExcelData(workbook,"Trend_line");
    }
    @Test(dataProvider = "Trend_line")
    @Story("This story belongs to the Trend_line")
    public void TrendLine(String userId,String expected_status_code,String description){

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/trend/line/details/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("userId", userId);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode() + "", expected_status_code);
    }
    @DataProvider(name ="Specific_Forest_Weather_Details")
    public Object[][] Specific_Forest_Weather_Details(){
        return TestDataExtractor.ExcelData(workbook,"Specific_Forest_Weather_Details");
    }
    @Test(dataProvider = "Specific_Forest_Weather_Details")
    @Story("This story belongs to the Specific_Forest_Weather_Details")
    public void SpecificForestWeatherDetails(String forest_id,String page_number, String expected_status_code,String description){

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/weather/details/view/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("forest_id", forest_id);
        request_body.put("page_number", page_number);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode() + "", expected_status_code);
    }
    @DataProvider(name ="Ownership_details_by_Forest_ID")
    public Object[][] Ownership_details_by_Forest_ID(){
        return TestDataExtractor.ExcelData(workbook,"Ownership_details_by_Forest_ID");
    }
    @Test(dataProvider = "Ownership_details_by_Forest_ID")
    @Story("This story belongs to the OwnershipDetailsByForestID")
    public void OwnershipDetailsByForestID(String ownership_id,String page_number, String expected_status_code,String description){

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/forest/cluster/view/by/ids/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("ownership_id", ownership_id);
        request_body.put("page_number", page_number);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
//        Assert.assertEquals(response.getStatusCode() + "", expected_status_code);
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }

        softAssert.assertAll();
    }
    @DataProvider(name ="All_Forest_Details")
    public Object[][] All_Forest_Details(){
        return TestDataExtractor.ExcelData(workbook,"All_Forest_Details");
    }
    @Test(dataProvider = "All_Forest_Details")
    @Story("This story belongs to the All_Forest_Details")
    public void All_Forest_Details(String page_number,String expected_status_code,String description) {
        //Call to the method for Authorization Token

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/weather/details/view/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("page_number", page_number);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }
    @DataProvider(name ="Specific_Forest_Details")
    public Object[][] Specific_Forest_Details(){
        return TestDataExtractor.ExcelData(workbook,"Specific_Forest_Details");
    }
    @Test(dataProvider = "Specific_Forest_Details")
    public  void Specific_Forest_Details(String forest_id, String page_number,String expected_status_code,String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/forecast/temperature/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("forest_id", forest_id);
        request_body.put("page_number", page_number);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name ="Ownership_Details_Cluster")
    public Object[][] Ownership_Details_Cluster() {
        return TestDataExtractor.ExcelData(workbook, "Ownership_Details_Cluster");
    }
    @Test(dataProvider = "Ownership_details_by_Forest_ID")
    @Story("This story belongs to the OwnershipDetailsByForestID")
    public void OwnershipDetailsCluster(String ownership_id,String page_number, String expected_status_code,String expected_Status_body,String description ){

        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/cluster/view/by/ids/" ;
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("ownership_id", ownership_id);
        request_body.put("page_number", page_number);
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        System.out.println(response.getBody());
        response.then().log().all();
        response.prettyPrint();
//        Assert.assertEquals(response.getStatusCode() + "", expected_status_code);
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }

        softAssert.assertAll();
    }
    @DataProvider(name ="Unassigned_Cluster")
    public Object[][] Unassigned_Cluster(){
        return TestDataExtractor.ExcelData(workbook,"Unassigned_Cluster");
    }
    @Test(dataProvider = "Unassigned_Cluster")
    public  void Unassigned_Cluster(String forest_id, String page_number,String expected_status_code,String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/unassigned/cluster/view/all/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("forest_id", forest_id);
        request_body.put("page_number", page_number);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name ="Best_AQI_Details")
    public Object[][] Best_AQI_Details() {
        return TestDataExtractor.ExcelData(workbook, "Best_AQI_Details");
    }
    @Test(dataProvider = "Best_AQI_Details")
    @Story("This story belongs to the BestAQIDetails")
    public void BestAQIDetails(String top_country, String expected_status_code, String Status,String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/global/best/aqi/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();

        if (top_country.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!top_country.isEmpty()) {
        //    request_body.put("top_country", top_country.matches("\\d+") ? Integer.parseInt(top_country) : top_country);
        }

        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        // Status code validation
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }

        // Response body validation
        if (!String.valueOf(response.jsonPath().getString("status")).equals(Status)) {
            softAssert.fail("Response body mismatch:\n" +
                    "Expected Status body: " + Status + "\n" +
                    "Actual status body: " + response.jsonPath().getString("status"));
        }

        softAssert.assertAll();
    }
    @DataProvider(name ="Worst_Aqi_Details")
    public Object[][] Worst_Aqi_Details() {
        return TestDataExtractor.ExcelData(workbook, "Worst_Aqi_Details");
    }
    @Test(dataProvider = "Worst_Aqi_Details")
    @Story("This story belongs to the WorstAqiDetails")
    public void WorstAqiDetails(String top_country, String expected_status_code, String Status,String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/global/worst/aqi/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();

        if (top_country.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!top_country.isEmpty()) {
           // request_body.put("top_country", top_country.matches("\\d+") ? Integer.parseInt(top_country) : top_country);
        }

        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        // Status code validation
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }

        // Response body validation
        if (!String.valueOf(response.jsonPath().getString("status")).equals(Status)) {
            softAssert.fail("Response body mismatch:\n" +
                    "Expected Status body: " + Status + "\n" +
                    "Actual status body: " + response.jsonPath().getString("status"));
        }

        softAssert.assertAll();
    }
    @DataProvider(name ="Southern_Hemisphere_Sea_Ice_Ind")
    public Object[][] Southern_Hemisphere_Sea_Ice_Ind() {
        return TestDataExtractor.ExcelData(workbook, "Southern_Hemisphere_Sea_Ice_Ind");
    }
    @Test(dataProvider = "Southern_Hemisphere_Sea_Ice_Ind")
    @Story("This story belongs to the Southern_Hemisphere_Sea_Ice_Ind")
    public void Southern_Hemisphere_Sea_Ice_Ind(String number_of_year, String expected_status_code, String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/global/seaiceSH/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();

        if (number_of_year.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!number_of_year.isEmpty()) {
            request_body.put("number_of_year", number_of_year.matches("-?\\d+") ? Integer.valueOf(number_of_year) : number_of_year);
        }
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }
    @DataProvider(name = "Northern_Hemisphere_Sea_Ice_Ind")
    public Object[][] Northern_Hemisphere_Sea_Ice_Ind(){
        return TestDataExtractor.ExcelData(workbook, "Northern_Hemisphere_Sea_Ice_Ind");
    }
    @Test(dataProvider = "Northern_Hemisphere_Sea_Ice_Ind")
    public  void NorthernHemisphereSeaIceInd(String number_of_year, String expected_status_code,String Status, String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/global/seaiceNH/details/view/";
        updateAllureTestCase(description);

        JSONObject request_body = new JSONObject();

        if (number_of_year.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!number_of_year.isEmpty()) {
            request_body.put("number_of_year", number_of_year.matches("-?\\d+") ? Integer.valueOf(number_of_year) : number_of_year);
        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code) ||
                !String.valueOf(response.jsonPath().getString("status")).equals(Status)) {

            softAssert.fail("Test failed due to mismatch:\n" +
                    "Expected Status Code: " + expected_status_code + ", Actual Status Code: " + response.statusCode() + "\n" +
                    "Expected Status Body: " + Status + ", Actual Status Body: " + response.jsonPath().getString("status"));
        }
        softAssert.assertAll();

    }
    @DataProvider(name = "Global_Temperature_Details")
    public Object[][] Global_Temperature_Details(){
        return TestDataExtractor.ExcelData(workbook, "Global_Temperature_Details");
    }
    @Test(dataProvider = "Global_Temperature_Details")
    public  void Global_Temperature_Details(String number_of_year, String expected_status_code,String Status, String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/global/temperature/details/view/";
        updateAllureTestCase(description);

        JSONObject request_body = new JSONObject();

        if (number_of_year.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!number_of_year.isEmpty()) {
            request_body.put("number_of_year", number_of_year.matches("-?\\d+") ? new java.math.BigInteger(number_of_year) : number_of_year);

        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code) ||
                !String.valueOf(response.jsonPath().getString("status")).equals(Status)) {

            softAssert.fail("Test failed due to mismatch:\n" +
                    "Expected Status Code: " + expected_status_code + ", Actual Status Code: " + response.statusCode() + "\n" +
                    "Expected Status Body: " + Status + ", Actual Status Body: " + response.jsonPath().getString("status"));
        }
        softAssert.assertAll();

    }
    @DataProvider(name = "Caron_Dioxide_Details")
    public Object[][] Caron_Dioxide_Details(){
        return TestDataExtractor.ExcelData(workbook, "Caron_Dioxide_Details");
    }
    @Test(dataProvider = "Caron_Dioxide_Details")
    public  void Caron_Dioxide_Details(String number_of_year, String expected_status_code,String Status, String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/global/co2/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();

        if (number_of_year.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!number_of_year.isEmpty()) {
            request_body.put("number_of_year", number_of_year.matches("-?\\d+") ? new java.math.BigInteger(number_of_year) : number_of_year);

        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code) ||
                !String.valueOf(response.jsonPath().getString("status")).equals(Status)) {

            softAssert.fail("Test failed due to mismatch:\n" +
                    "Expected Status Code: " + expected_status_code + ", Actual Status Code: " + response.statusCode() + "\n" +
                    "Expected Status Body: " + Status + ", Actual Status Body: " + response.jsonPath().getString("status"));
        }
        softAssert.assertAll();

    }
    @DataProvider(name = "Deforestation_Details")
    public Object[][] Deforestation_Details(){
        return TestDataExtractor.ExcelData(workbook, "Deforestation_Details");
    }
    @Test(dataProvider = "Deforestation_Details")
    public  void Deforestation_Details(String number_of_year, String expected_status_code,String Status, String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/global/deforestation/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();

        if (number_of_year.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!number_of_year.isEmpty()) {
            request_body.put("number_of_year", number_of_year.matches("-?\\d+") ? new java.math.BigInteger(number_of_year) : number_of_year);

        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.prettyPrint();
        response.then().log().all();

        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code) ||
                !String.valueOf(response.jsonPath().getString("status")).equals(Status)) {

            softAssert.fail("Test failed due to mismatch:\n" +
                    "Expected Status Code: " + expected_status_code + ", Actual Status Code: " + response.statusCode() + "\n" +
                    "Expected Status Body: " + Status + ", Actual Status Body: " + response.jsonPath().getString("status"));
        }
        softAssert.assertAll();

    }
    @DataProvider(name = "Specific_Forest_Temperature")
    public Object[][] Specific_Forest_Temperature(){
        return TestDataExtractor.ExcelData(workbook, "Specific_Forest_Temperature");
    }
    @Test(dataProvider = "Specific_Forest_Temperature")
    public  void Specific_Forest_Temperature(String forest_id, String page_number,String expected_status_code,String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/forecast/temperature/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("forest_id", forest_id);
        request_body.put("page_number", page_number);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }
    @DataProvider(name = "All_Forest_Temperature_Forecast")
    public Object[][] All_Forest_Temperature_Forecast(){
        return TestDataExtractor.ExcelData(workbook, "All_Forest_Temperature_Forecast");
    }
    @Test(dataProvider = "All_Forest_Temperature_Forecast")
    public  void All_Forest_Temperature_Forecast(String page_number,String expected_status_code,String description){
        String url ="https://devanalytics.sacredgroves.earth/api/clustermgmt/forecast/temperature/details/view/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("page_number", page_number);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }

    //Forest Map
    //Cluster Map
    //Total Clusters by list of Guardians

    @DataProvider(name ="Forest_Map" )
    public  Object [][] Forest_Map(){
      return  TestDataExtractor.ExcelData(workbook,"Forest_Map");
    }
    @Test(dataProvider = "Forest_Map")
    public  void Forest_Map(String iframe_height,String iframe_width, String zoom_start,String font_weight_head,String font_size_head,
                          String font_weight_body,String font_size_body, String full_screen,String page_number, String expected_status_code,String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/map/forest/details/html/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("iframe_height", iframe_height);
        request_body.put("iframe_width", iframe_width);
        request_body.put("zoom_start", zoom_start);
        request_body.put("font_weight_head", font_weight_head);
        request_body.put("font_size_head", font_size_head);
        request_body.put("font_weight_body", font_weight_body);
        request_body.put("font_size_body", font_size_body);
        request_body.put("full_screen", full_screen);
        request_body.put("page_number", page_number);


//        if (iframe_height == null || iframe_height.isEmpty()) {
//            request_body.put("iframe_height", "");
//        } else if (!iframe_height.isEmpty()) {
//            request_body.put("iframe_height", iframe_height.matches("-?\\d+") ? Integer.valueOf(iframe_height) : iframe_height);
//        }
//
//        if (iframe_width == null || iframe_width.isEmpty()) {
//            request_body.put("iframe_width", "");
//        } else if (!iframe_width.isEmpty()) {
//            request_body.put("iframe_width", iframe_width.matches("-?\\d+") ? Integer.valueOf(iframe_width) : iframe_width);
//        }
//
//        if (zoom_start == null || zoom_start.isEmpty()) {
//            request_body.put("zoom_start", "");
//        } else if (!zoom_start.isEmpty()) {
//            request_body.put("zoom_start", zoom_start.matches("-?\\d+") ? Integer.valueOf(zoom_start) : zoom_start);
//        }
//
//        if (font_weight_head == null || font_weight_head.isEmpty()) {
//            request_body.put("font_weight_head", "");
//        } else if (!font_weight_head.isEmpty()) {
//            request_body.put("font_weight_head", font_weight_head.matches("-?\\d+") ? Integer.valueOf(font_weight_head) : font_weight_head);
//        }
//
//        if (font_size_head == null || font_size_head.isEmpty()) {
//            request_body.put("font_size_head", "");
//        } else if (!font_size_head.isEmpty()) {
//            request_body.put("font_size_head", font_size_head.matches("-?\\d+") ? Integer.valueOf(font_size_head) : font_size_head);
//        }
//
//        if (font_weight_body == null || font_weight_body.isEmpty()) {
//            request_body.put("font_weight_body", "");
//        } else if (!font_weight_body.isEmpty()) {
//            request_body.put("font_weight_body", font_weight_body.matches("-?\\d+") ? Integer.valueOf(font_weight_body) : font_weight_body);
//        }
//
//        if (font_size_body == null || font_size_body.isEmpty()) {
//            request_body.put("font_size_body", "");
//        } else if (!font_size_body.isEmpty()) {
//            request_body.put("font_size_body", font_size_body.matches("-?\\d+") ? Integer.valueOf(font_size_body) : font_size_body);
//        }
//
//        if (full_screen == null || full_screen.isEmpty()) {
//            request_body.put("full_screen", "");
//        } else {
//            request_body.put("full_screen", full_screen);
//        }
//
//        if (page_number == null || page_number.isEmpty()) {
//            request_body.put("page_number", "");
//        } else if (!page_number.isEmpty()) {
//            request_body.put("page_number", page_number.matches("-?\\d+") ? Integer.valueOf(page_number) : page_number);
//        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:" + "Expected status code: " + expected_status_code + "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name ="Cluster_Map" )
    public  Object [][] Cluster_Map(){
        return  TestDataExtractor.ExcelData(workbook,"Cluster_Map");
    }
    @Test(dataProvider = "Cluster_Map")
    @Story("This Story is Cluster_Map")
    @Parameters({"userId", "forestId","iframe_height","iframe_width","zoom_start","font_weight_head","font_size_head","font_weight_body","font_size_body","full_screen","page_number"})
    public  void Cluster_Map(String userId, String forestId, String iframe_height,String iframe_width, String zoom_start,String font_weight_head,String font_size_head,
                            String font_weight_body,String font_size_body, String full_screen,String page_number, String expected_status_code,String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/map/cluster/details/html/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("userId", userId);
        request_body.put("forestId", forestId);
        request_body.put("iframe_height", iframe_height);
        request_body.put("iframe_width", iframe_width);
        request_body.put("zoom_start", zoom_start);
        request_body.put("font_weight_head", font_weight_head);
        request_body.put("font_size_head", font_size_head);
        request_body.put("font_weight_body", font_weight_body);
        request_body.put("font_size_body", font_size_body);
        request_body.put("full_screen", full_screen);
        request_body.put("page_number", page_number);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:" + "Expected status code: " + expected_status_code + "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name ="Total_Clusters_by_List_of_Guard" )
    public  Object [][] Total_Clusters_by_List_of_Guard(){
        return  TestDataExtractor.ExcelData(workbook,"Total_Clusters_by_List_of_Guard");
    }

    @Test(dataProvider = "Total_Clusters_by_List_of_Guard")
    @Story("This Story is Total_Clusters_by_List_of_Guard")
    @Parameters({"ownership_id", "page_number"})
    public  void Total_Clusters_by_List_of_Guard(String ownership_id, String page_number,String expected_status_code,String description) {
        String url = "https://devanalytics.sacredgroves.earth/api/clustermgmt/cluster/bulk/view/by/ids/";
        updateAllureTestCase(description);
        JSONObject request_body = new JSONObject();
        request_body.put("ownership_id", ownership_id);
        request_body.put("page_number", page_number);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println("Status Code:" +response.statusCode());
        response.then().log().all();
        SoftAssert softAssert = new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:" + "Expected status code: " + expected_status_code + "Actual status code: " + response.statusCode());
        }
        softAssert.assertAll();
    }
}
