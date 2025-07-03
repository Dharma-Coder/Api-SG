package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;

import static io.restassured.RestAssured.given;

public class SG_NG_Admin {
    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Admin_Excel.xlsx").getAbsolutePath();

    @DataProvider(name = "PostLogin")
    public Object[][] PostLogin() {
        return TestDataExtractor.ExcelData(workbook, "PostLogin");
    }
    @DataProvider(name = "particular_User")
    public Object[][] particular_User() {
        return TestDataExtractor.ExcelData(workbook, "particular_User");
    }
    @DataProvider(name ="Delete")
    public Object[][] Delete() {
        return TestDataExtractor.ExcelData(workbook, "Delete");
    }
    @DataProvider(name ="send_Invite")
    public Object[][] send_Invite(){
        return TestDataExtractor.ExcelData(workbook, "send_Invite");
    }

    private void updateAllureTestCase(String S_No, String Description) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setName(S_No + ":" + Description);
            testResult.setDescription(Description);
        });
    }
    private void updateAllureTestCases(String Description) {
        Allure.getLifecycle().updateTestCase(testResult -> {
            testResult.setName(Description);
            testResult.setDescription(Description);
        });
    }
    private String authorizationToken = "";
    public void adminLoginForAuth(String user_id, String password) {

        // Request Body
        JSONObject request_body = new JSONObject();
        request_body.put("user_id", user_id);
        request_body.put("password", password);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .post("https://devoutshade.sacredgroves.earth/api/admins/login");
        updateAllureTestCases("adminLoginForAuth");
        String responseString = response.getBody().asString();
        response.then().log().all();
        Gson gson = new Gson();

        if (response.getStatusCode() == 200) {
            JsonObject jsonObject = gson.fromJson(responseString, JsonObject.class);
            authorizationToken = jsonObject.get("data").getAsString();
            System.out.println("AuthToken: " + authorizationToken);
        }
    }

    @Test(dataProvider = "PostLogin")
    @Story("This story belongs to the PostLogin")
    public void Login(String user_id, String password, String expected_status_code,String Description,String S_No) {
        // URL
        updateAllureTestCase(S_No, Description);
        String url = "https://devoutshade.sacredgroves.earth/api/admins/login";

        // Request Body
        JSONObject request_body = new JSONObject();
        request_body.put("user_id", user_id);
        request_body.put("password", password);

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
        response.prettyPrint();
        // Print Statements
//        System.out.println("Response : " + response.asString());
//        System.out.println("Respon
//
//        se Body : " + response.getBody().asString());
//        System.out.println("Status Code : " + response.getStatusCode());
        response.then().log().all();

        // Assertion
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

//        Assert.assertEquals(response.getStatusCode(), expected_status_code);

    }
    @Test(dataProvider = "particular_User")
    @Story("This story belongs to the particular_User")
    public void particular_user(String userid,String expected_status_code,String Description,String S_No) {
        //Call to the method for Authorization Token
        adminLoginForAuth("Admin", "Founders@1MillionAcres");
        updateAllureTestCase(S_No, Description);
        String url = "https://devoutshade.sacredgroves.earth/api/admins/user/"+ userid;
        JSONObject request_body = new JSONObject();
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .contentType(ContentType.JSON)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        // Print Statements
//        System.out.println("Response : " + response.asString());
//        System.out.println("Response Body : " + response.getBody().asString());
//        System.out.println("Status Code : " + response.getStatusCode());
        response.then().log().all();

        // Assertion
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

    }
    @Test
    @Story("This story belongs to the particular_user_WithoutTokenAndUerCTN")
    public void particular_user_WithoutTokenAndUerCTN() {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/admins/user/";
        updateAllureTestCases("particular_user_WithoutTokenAndUerCTN");
        JSONObject request_body = new JSONObject();
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        response.then().log().all();
        if (!(response.statusCode() ==401))
            Assert.fail("Expected status code is "+401+" but the actual status code is "+response.statusCode());

    }
    @Test
    public void particular_user_WithoutTokenID( ) {
        //Call to the method for Authorization Token
        String userid ="60701d52-5686-4d59-bc83-dd2cc8b74160";
        String url = "https://devoutshade.sacredgroves.earth/api/admins/user/"+ userid;
        updateAllureTestCases("particular_user_WithoutTokenID");
        JSONObject request_body = new JSONObject();
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        if (!(response.statusCode() ==401))
            Assert.fail("Expected status code is "+401+" but the actual status code is "+response.statusCode());

    }
    @Test
    public void DeleteUser_WithoutTokenID(){
        String userid ="f13d7a28-a399-41ae-8f57-fddb0855c3da";
        String url ="https://devoutshade.sacredgroves.earth/api/admins/user/"+ userid;
        updateAllureTestCases("DeleteUser_WithoutTokenID");
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .delete(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());


    }
    @Test
    public void DeleteUser_WithoutTokenAndUerCTN(){
        String url ="https://devoutshade.sacredgroves.earth/api/admins/user/";
        updateAllureTestCases("DeleteUser_WithoutTokenAndUerCTN");
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .delete(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }
    @Test(dataProvider ="Delete")
    public void DeleteUser(String userid,String expected_status_code,String Description,String S_No) {
        //Call to the method for Authorization Token

        adminLoginForAuth("Admin", "Founders@1MillionAcres");
        updateAllureTestCase(S_No, Description);
        String url = "https://devoutshade.sacredgroves.earth/api/admins/user/"+userid;
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer"+authorizationToken)
                .contentType(ContentType.JSON)
                .log().all()
                .delete(url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

    }

    @Test
    public void GrovePoints_WithoutToken(){
        String url ="https://devoutshade.sacredgroves.earth/api/admins/grovepoints";
        updateAllureTestCases("GrovePoints_WithoutToken");
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }

    @Test
    public void Squads_withoutToken(){
        String url ="https://devoutshade.sacredgroves.earth/api/admins/squads";
        updateAllureTestCases("Squads_withoutToken");
        Response response=given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }

    @Test(dataProvider = "send_Invite")
    public void SendInvites(String userid,String expected_status_code,String Description,String S_No) {
        //Call to the method for Authorization Token
        adminLoginForAuth("Admin", "Founders@1MillionAcres");
        updateAllureTestCase(S_No, Description);

        String url = "https://devoutshade.sacredgroves.earth/api/admins/invite";
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authorizationToken)
                .contentType(ContentType.JSON)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

    }
    @Test
    public void SendInvitesWithoutToken(){
        String url = "https://devoutshade.sacredgroves.earth/api/admins/invite";
        updateAllureTestCases("SendInvitesWithoutToken");
        String requestBody = "{\"email\":[\"adreno1mailinator.com\" ,\"adreno2mailinator.com\"]}";

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }

}