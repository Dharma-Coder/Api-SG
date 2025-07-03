package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SG_NG_SacredSecond_APIs {

    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\SacredSecond_APIs_Excel.xlsx").getAbsolutePath();


    @DataProvider(name = "Gift_Sgcs")
    public Object[][] Gift_Sgcs(){
          return TestDataExtractor.ExcelData(workbook,"Gift_Sgcs");
    }
    @Test(dataProvider = "Gift_Sgcs")
    @Story("This Story Gift_Sgcs")
    public  void GiftSgcs( String email, String quantity, String message ,String date , String scenario, String expected_status_code,String description){
        adminLoginForAuth("dharma@frugaltesting.com", "Frugal@123");

        String url ="https://devoutshade.sacredgroves.earth/api/sutra/gift";

        JSONObject request_body = new JSONObject();

        request_body.put("email",email);
        request_body.put("quantity",quantity);
        request_body.put("message",message);
        request_body.put("date",date);
        request_body.put("scenario",scenario);

        if (email == null || email.isEmpty()) {
            request_body.put("email", "");
        } else {
            request_body.put("email", email);
        }

        if (message == null || message.isEmpty()) {
            request_body.put("message", "");
        } else {
            request_body.put("message", message);
        }

        if (date == null || date.isEmpty()) {
            request_body.put("date", "");
        } else {
            request_body.put("date", date);
        }

        if (scenario == null || scenario.isEmpty()) {
            request_body.put("scenario", "");
        } else {
            request_body.put("scenario", scenario);
        }

        if (quantity == null || quantity.isEmpty()) {
            request_body.put("quantity", "");
        } else if (!quantity.isEmpty()) {
            request_body.put("quantity", quantity.matches("-?\\d+") ? Integer.valueOf(quantity) : quantity);
        }

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println("Status Code: " + response.statusCode());
        response.then().log().all();

        SoftAssert softAssert = new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "expected status code" + expected_status_code +"Actual status code:" +response.statusCode());
        }
        softAssert.assertAll();
    }

    private String authorizationToken = "";
    public void adminLoginForAuth(String email_phone, String password) {

        // Request Body
        JSONObject request_body = new JSONObject();
        request_body.put("email_phone", email_phone);
        request_body.put("password", password);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .post("https://devoutshade.sacredgroves.earth/api/users/login");
        String responseString = response.getBody().asString();
        response.then().log().all();
        Gson gson = new Gson();

        if (response.getStatusCode() == 200) {
            JsonObject jsonObject = gson.fromJson(responseString, JsonObject.class);
            authorizationToken = jsonObject.get("data").getAsString();
            System.out.println("AuthToken: " + authorizationToken);
        }
    }

    @Test
    @Story("This Get SGCs Owned WithoutToken")
    public void GetSGCsOwnedWithoutToken(){
        String url = "https://devoutshade.sacredgroves.earth/api/sutra/cluster";

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println("Status Code: "+response.statusCode());
        response.then().log().all();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }

    @DataProvider(name = "Cities")
    public Object[][] Cities(){
     return TestDataExtractor.ExcelData(workbook,"Cities");
    }

    @Test(dataProvider ="Cities" )
    @Story("This Story Cities")
    public void Cities(String numbers,String expected_status_code,String description ){

        String url ="https://devoutshade.sacredgroves.earth/api/general/getCities/"+numbers;

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println("Status Code: "+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+expected_status_code+"Actual status code:"+response.statusCode());
        }
         softAssert.assertAll();
    }

    @DataProvider(name = "States")
    public Object[][] States(){
        return TestDataExtractor.ExcelData(workbook,"States");
    }

    @Test(dataProvider ="States" )
    @Story("This Story States")
    public void States(String numbers,String expected_status_code,String description ){

        String url ="https://devoutshade.sacredgroves.earth/api/general/getStates/"+numbers;

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println("Status Code: "+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+expected_status_code+"Actual status code:"+response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name ="ledger")
    public Object[][]ledger(){
     return TestDataExtractor.ExcelData(workbook,"ledger");
    }

    @Test(dataProvider = "ledger")
    @Story("This Story ledger")
    public void Ledger(String startDate,String endDate,String expected_status_code,String description){
        adminLoginForAuth("dharma@frugaltesting.com", "Frugal@123");
        String url ="https://devoutshade.sacredgroves.earth/api/sutra/transaction/ledger";

        JSONObject request_body =new JSONObject();

        request_body.put("startDate",startDate);
        request_body.put("endDate",endDate);

        if (startDate == null || startDate.isEmpty()) {
            request_body.put("startDate", "");
        } else {
            request_body.put("startDate", startDate);
        }

        if (endDate == null || endDate.isEmpty()) {
            request_body.put("endDate", "");
        } else {
            request_body.put("endDate", endDate);
        }

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .log().all()
                .get(url);

        System.out.println("Status Code:"+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+expected_status_code +"Actual status code:"+ response.statusCode());
        }
        softAssert.assertAll();

    }
    @DataProvider(name = "City")
    public Object[][] City(){
        return TestDataExtractor.ExcelData(workbook,"City");
    }

    @Test(dataProvider ="City" )
    @Story("This Story City")
    public void City(String numbers,String expected_status_code,String description ){

        String url ="https://devoutshade.sacredgroves.earth/api/general/getCities/"+numbers;

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println("Status Code: "+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+expected_status_code+"Actual status code:"+response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "Create_Payment_Session")
    public  Object [][] Create_Payment_Session(){
       return TestDataExtractor.ExcelData(workbook,"Create_Payment_Session");
    }

    @Test(dataProvider = "Create_Payment_Session")
    @Story("This Story Create_Payment_Session")
    public void CreatePaymentSession(String currency, String unit_amount ,String name, String quantity ,String success_url, String cancel_url, String expected_status_code,String description){
        adminLoginForAuth("dharma@frugaltesting.com", "Frugal@123");

        String url ="https://devoutshade.sacredgroves.earth/api/payment/session";

        JSONObject request_body = new JSONObject();

        request_body.put("currency",currency);
        request_body.put("unit_amount",unit_amount);
        request_body.put("name",name);
        request_body.put("quantity",quantity);
        request_body.put("success_url",success_url);
        request_body.put("cancel_url",cancel_url);

        if (currency == null || currency.isEmpty()) {
            request_body.put("currency", "");
        } else {
            request_body.put("currency", currency);
        }

        if (unit_amount == null || unit_amount.isEmpty()) {
            request_body.put("unit_amount", "");
        } else if (!unit_amount.isEmpty()) {
            request_body.put("unit_amount", unit_amount.matches("-?\\d+") ? Integer.valueOf(unit_amount) : unit_amount);
        }

        if (name == null || name.isEmpty()) {
            request_body.put("name", "");
        } else {
            request_body.put("name", name);
        }

        if (quantity == null || quantity.isEmpty()) {
            request_body.put("quantity", "");
        } else if (!quantity.isEmpty()) {
            request_body.put("quantity", quantity.matches("-?\\d+") ? Integer.valueOf(quantity) : quantity);
        }

        if (success_url == null || success_url.isEmpty()) {
            request_body.put("success_url", "");
        } else {
            request_body.put("success_url", success_url);
        }

        if (cancel_url == null || cancel_url.isEmpty()) {
            request_body.put("cancel_url", "");
        } else {
            request_body.put("cancel_url", cancel_url);
        }


        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println("Status Code:" +response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+ expected_status_code + "actual status code" +response.statusCode());
        }
        softAssert.assertAll();
    }

    @Test
    @Story("This Get SGCs Giftable count WithoutToken")
    public void GetSGCsGiftableCountWithoutToken(){
        String url = "https://devoutshade.sacredgroves.earth/api/sutra/giftable/1";

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println("Status Code: "+response.statusCode());
        response.then().log().all();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());
    }
    @Test
    @Story("This Story update user privacy settings")
    public void updateUserPrivacySettings (){
        String url ="https://devoutshade.sacredgroves.earth/api/users/:type";

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .patch(url);
        System.out.println("status code:"+response.statusCode());
        response.then().log().all();
        if (!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());
    }

    @DataProvider(name = "forest_details")
    public Object[][] forest_details(){
     return TestDataExtractor.ExcelData(workbook,"forest_details");
    }

    @Test(dataProvider = "forest_details")
    @Story("This Story forest_details")
    public  void forestDetails (String forestid,String expected_status_code,String description){
        adminLoginForAuth("dharma@frugaltesting.com", "Frugal@123");
        String url ="https://devoutshade.sacredgroves.earth/api/sutra/forest/details/"+forestid;
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .log().all()
                .get(url);

        System.out.println("Status Code:" +response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"expected status code"+ expected_status_code + "actual status code" +response.statusCode());
        }
        softAssert.assertAll();

    }

    @Test
    @Story("This Story LedgerWithoutToken")
    public void LedgerWithoutToken (){
        String url ="https://devoutshade.sacredgroves.earth/api/sutra/transaction/ledger";
        String requestBody ="{\"startDate\": \"2024-01-01\", \"endDate\":\"2024-02-01\" }";
        System.out.println(requestBody);
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(requestBody)
                .log().all()
                .post(url);
        System.out.println("status code:"+response.statusCode());
        response.then().log().all();
        if (!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());
    }

    //State
}
