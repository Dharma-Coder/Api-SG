package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SG_NG_Users_APIs {
    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Users_APIs_Excel.xlsx").getAbsolutePath();

    @DataProvider(name = "Get_Users_list")
    public Object[][] Get_Users_list(){
        return TestDataExtractor.ExcelData(workbook, "Get_Users_list");
    }
    @Test(dataProvider ="Get_Users_list")
    @Story("Get_Users_list")
    public void GetUsersList(String score){
        String url ="https://devoutshade.sacredgroves.earth/api/users/level?score="+score;

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
    @DataProvider(name = "Get_User_ByID")
    public Object[][] Get_User_ByID(){
        return TestDataExtractor.ExcelData(workbook, "Get_User_ByID");
    }
    @Test(dataProvider = "Get_User_ByID")
    @Story("This story belongs to the Get_User_ByID")
    public void Get_User_ByID(String userid,String expected_status_code,String description,String S_No) {
        //Call to the method for Authorization Token

        adminLoginForAuth("dharma@frugaltesting.com", "Frugal@123");
        String url = "https://devoutshade.sacredgroves.earth/api/users/"+ userid;
        JSONObject request_body = new JSONObject();
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ authorizationToken)
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        response.then().log().all();
        // Assertion
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

    }
    @Test
    @Story("This story belongs to the GetUserByID_WithoutTokenAndUerCTN")
    public void GetUserByID_WithoutTokenAndUerCTN() {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/users/";
        JSONObject request_body = new JSONObject();
        // Response
        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(request_body.toString())
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        if (!(response.statusCode() ==401))
            Assert.fail("Expected status code is "+401+" but the actual status code is "+response.statusCode());

    }
//    @DataProvider(name ="Post_Create_User")
//    public Object[][] Post_Create_User(){
//        return TestDataExtractor.ExcelData(workbook,"Post_Create_User");
//    }
//Get_Find_User_Certificate

    @DataProvider(name ="Get_Find_User_Certificate")
    public Object[][] Get_Find_User_Certificate(){
        return TestDataExtractor.ExcelData(workbook,"Get_Find_User_Certificate");
    }
    @Test(dataProvider = "Get_Find_User_Certificate")
    @Story("This story belongs to the Get Find User Certificate")
    public void Get_Find_User_Certificate(String email_id,String expected_status_code,String description ) {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/users/download/certificate/" + email_id;
        JSONObject request_body = new JSONObject();

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);
    }
    @DataProvider(name ="Get_Find_User_Calender")
    public Object[][] Get_Find_User_Calender(){
        return TestDataExtractor.ExcelData(workbook,"Get_Find_User_Calender");
    }
    @Test(dataProvider = "Get_Find_User_Calender")
    @Story("This story belongs to the Get Find User Certificate")
    public void Get_Find_User_Calender(String email_id,String expected_status_code,String description ) {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/users/download/calendar/" + email_id;
        JSONObject request_body = new JSONObject();

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(request_body)
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);
    }

    @DataProvider(name ="Patch_User_Login")
    public Object[][] Patch_User_Login(){
        return TestDataExtractor.ExcelData(workbook,"Patch_User_Login");
    }

    @Test(dataProvider ="Patch_User_Login")
    @Story("This Story Patch_User_Login")
    public void Patch_User_Login(String email_id , String password ,String expected_status_code,String description) {

         String url = "https://devoutshade.sacredgroves.earth/api/users/login";
         JSONObject request_body = new JSONObject();
         request_body.put("email_id", email_id);
         request_body.put("password", password);

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

         // Assertion
         SoftAssert softAssert = new SoftAssert();
         if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
             softAssert.fail("Status code mismatch:\n" +
                     "Expected status code: " + expected_status_code + "\n" +
                     "Actual status code: " + response.statusCode());
         }

         softAssert.assertAll();
     }
     //Post_Find_User
    @DataProvider(name = "Post_Find_User")
    public Object[][] Post_Find_User(){
        return TestDataExtractor.ExcelData(workbook,"Post_Find_User");
    }
    @Test(dataProvider = "Post_Find_User")
    @Story("This story Post_Find_User")
    public void Post_Find_User(String email_id , String numbers,String expected_status_code,String description){

        String url ="https://devoutshade.sacredgroves.earth/api/users/find";
//        JSONObject request_body = new JSONObject();
//        request_body.put("email_id", email_id);
//        request_body.put("numbers", numbers);
        JSONObject request_body = new JSONObject();

        // Build emails array
        JSONArray emailArray = new JSONArray();
        emailArray.put(email_id);  // Just value, no []
        request_body.put("emails", emailArray);

        // Build numbers array
        JSONArray numberArray = new JSONArray();
        numberArray.put(numbers);  // Just value, no []
        request_body.put("numbers", numberArray);

//        JSONArray emailArray = new JSONArray();
//        JSONArray numberArray = new JSONArray();
//
//
//        if (email_id.equals("\"\"") || email_id.trim().isEmpty()) {
//            // If input is empty or "" — you may choose to add empty or skip adding email
//            emailArray.put("");
//        } else {
//            // Else add the input directly
//            emailArray.put(email_id);
//        }
//
//        if (numbers.equals("\"\"") || numbers.trim().isEmpty()) {
//            // If input is empty or ""
//            numberArray.put("");
//        } else if (numbers.matches("\\d+")) {
//            // If it's a numeric string — add as number
//            numberArray.put(Long.valueOf(numbers));
//        } else {
//            // If it's not purely numeric, add as string (handles bad inputs for negative tests)
//            numberArray.put(numbers);
//        }

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

        // Assertion
        SoftAssert softAssert = new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)) {
            softAssert.fail("Status code mismatch:\n" +
                    "Expected status code: " + expected_status_code + "\n" +
                    "Actual status code: " + response.statusCode());
        }

        softAssert.assertAll();
    }

    @DataProvider(name ="delete_User")
    public Object[][] delete_User() {
        return TestDataExtractor.ExcelData(workbook, "delete_User");
    }

    @Test(dataProvider ="delete_User")
    public void delete_User(String userid,String expected_status_code,String description,String S_No) {
        //Call to the method for Authorization Token

        patch_user_login_token("dharma@frugaltesting.com", "Frugal@123");
        String url = "https://devoutshade.sacredgroves.earth/api/admins/user/"+userid;
        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer"+patch_user_login_token)
                .contentType(ContentType.JSON)
                .log().all()
                .delete(url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode()+"", expected_status_code);

    }
    private String patch_user_login_token = "";
    public void patch_user_login_token(String email_phone, String password) {

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
            patch_user_login_token = jsonObject.get("data").getAsString();
            System.out.println("AuthToken: " + patch_user_login_token);
        }
    }
    //Send Otp
    //Post Verify Verified Otp
    //Post Forgot Password
    //Post Update Password
    //Patch Update User
    //Get User Detail using name

    @DataProvider(name ="Detail_using_name")
    public Object[][] Detail_using_name() {
        return TestDataExtractor.ExcelData(workbook, "Detail_using_name");
    }

    @Test(dataProvider ="Detail_using_name")
    public void detailUsingNameWithoutToken(String username){
        String url = "https://devoutshade.sacredgroves.earth/api/users/getUserDetails/"+ username;

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .log().all()
                .get(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        if(!(response.statusCode()==401))
            Assert.fail("Expected status code is "+401+"but the actual status code is "+response.statusCode());

    }

    @DataProvider(name = "PostSendOTP")
    public Object [][] postSendOTP(){
       return TestDataExtractor.ExcelData(workbook,"PostSendOTP");
    }

    @Test(dataProvider ="PostSendOTP")
    public void PostSendOTP(String newUser,String country_code, String email, String phone_number, String expected_status_code ,String description){

        String url ="https://devoutshade.sacredgroves.earth/api/users/sendOtp";

       JSONObject request_body = new JSONObject();
       request_body.put("newUser",newUser);
       request_body.put("country_code",country_code);
       request_body.put("email",email);
       request_body.put("phone_number",phone_number);

        if (newUser == null || newUser.isEmpty()) {
            request_body.put("newUser", "");
        } else {
            request_body.put("newUser", newUser);
        }

        if (country_code == null || country_code.isEmpty()) {
            request_body.put("country_code", "");
        } else {
            request_body.put("country_code", country_code);
        }

        if (email == null || email.isEmpty()) {
            request_body.put("email", "");
        } else {
            request_body.put("email", email);
        }

        if (phone_number == null || phone_number.isEmpty()) {
            request_body.put("phone_number", "");
        } else if (!phone_number.isEmpty()) {
            request_body.put("phone_number", phone_number.matches("\\d{10}") ? phone_number : phone_number);
        }


       Response response = given()
               .contentType(ContentType.JSON)
               .accept("application/Json")
               .header("Content-Type","application/Json")
               .body(request_body.toString())
               .log().all()
               .post(url);

        System.out.println("Status Code:" +response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "Expected status code:" + expected_status_code + "Actual status code:" + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "PostSendOTPv2")
    public Object [][] PostSendOTPv2(){
        return TestDataExtractor.ExcelData(workbook,"PostSendOTPv2");
    }

    @Test(dataProvider = "PostSendOTPv2")
    public void PostSendOT_Pv2(String newUser, String country_code, String phone_number, String ip_address ,String expected_status_code, String description){

        String url ="https://devoutshade.sacredgroves.earth/api/users/sendOtpV2";

        JSONObject request_body = new JSONObject();

        request_body.put("newUser",newUser);
        request_body.put("country_code",country_code);
        request_body.put("phone_number",phone_number);
        request_body.put("ip_address",ip_address);

        if (newUser == null || newUser.isEmpty()) {
            request_body.put("newUser", "");
        } else {
            request_body.put("newUser", newUser);
        }

        if (country_code == null || country_code.isEmpty()) {
            request_body.put("country_code", "");
        } else {
            request_body.put("country_code", country_code);
        }

        if (phone_number == null || phone_number.isEmpty()) {
            request_body.put("phone_number", "");
        } else if (!phone_number.isEmpty()) {
            request_body.put("phone_number", phone_number.matches("\\d{10}") ? phone_number : phone_number);
        }

        if (ip_address == null || ip_address.isEmpty()) {
            request_body.put("ip_address", "");
        } else {
            request_body.put("ip_address", ip_address);
        }

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println("Status code:"+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert= new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "Excepted Status code"+ expected_status_code+"Actual Status code"+ response.statusCode());
        }
    }


    @DataProvider(name = "PostVerifyOTP")
    public Object [][] PostVerifyOTP(){
       return TestDataExtractor.ExcelData(workbook,"PostVerifyOTP");
    }
    @Test(dataProvider ="PostVerifyOTP")
    public void PostVerify_OTP(String newUser, String country_code, String phone_number, String code ,String expected_status_code, String description){

        String url ="https://devoutshade.sacredgroves.earth/api/users/verifyOtp";

        JSONObject request_body =new JSONObject();

        request_body.put("newUser",newUser);
        request_body.put("country_code",country_code);
        request_body.put("phone_number",phone_number);
        request_body.put("code",code);


        if (newUser == null || newUser.isEmpty()) {
            request_body.put("newUser", "");
        } else {
            request_body.put("newUser", newUser);
        }

        if (country_code == null || country_code.isEmpty()) {
            request_body.put("country_code", "");
        } else {
            request_body.put("country_code", country_code);
        }

        if (phone_number == null || phone_number.isEmpty()) {
            request_body.put("phone_number", "");
        } else {
            request_body.put("phone_number", phone_number.matches("\\d{10}") ? phone_number : phone_number);
        }

        if (code == null || code.isEmpty()) {
            request_body.put("code", "");
        } else {
            request_body.put("code", code);
        }


        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println("Status code;"+response.statusCode());
        response.then().log().all();

        SoftAssert softAssert =new SoftAssert();
        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+"Excepted Status code"+expected_status_code+"Actual Status code"+response.statusCode());
        }
    }

  @DataProvider(name = "PostForgotPassword")
    public Object[][] PostForgotPassword(){
       return TestDataExtractor.ExcelData(workbook,"PostForgotPassword");
  }
  @Test(dataProvider = "PostForgotPassword")
    public void PostForgot_Password(String password, String expected_status_code, String description){

      PostVerifyOTP();

        String url ="https://devoutshade.sacredgroves.earth/api/users/forgotPassword";

        JSONObject request_body =new JSONObject();
        request_body.put("password",password);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

      System.out.println("Status code:"+response.statusCode());
      response.then().log().all();

      SoftAssert softAssert= new SoftAssert();
      if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
          softAssert.fail("Status code mismatch:"+"Expected Status code:"+expected_status_code+"Actual Status code"+response.statusCode());
      }
  }

  @DataProvider(name = "PostUpdatePassword")
    public Object[][] PostUpdatePassword(){
       return TestDataExtractor.ExcelData(workbook,"PostUpdatePassword");
  }
  @Test(dataProvider = "PostUpdatePassword")
    public void PostUpdate_Password(String currentPassword, String password, String phone_number, String country_code ,String expected_status_code, String description){

        String url ="https://devoutshade.sacredgroves.earth/api/users/updatePassword";
        JSONObject request_body =new JSONObject();

        request_body.put("currentPassword",currentPassword);
        request_body.put("password",password);
        request_body.put("country_code",country_code);
        request_body.put("phone_number",phone_number);

      if (currentPassword == null || currentPassword.isEmpty()) {
          request_body.put("currentPassword", "");
      } else {
          request_body.put("currentPassword", currentPassword);
      }

      if (password == null || password.isEmpty()) {
          request_body.put("password", "");
      } else {
          request_body.put("password", password);
      }

      if (country_code == null || country_code.isEmpty()) {
          request_body.put("country_code", "");
      } else {
          request_body.put("country_code", country_code);
      }

      if (phone_number == null || phone_number.isEmpty()) {
          request_body.put("phone_number", "");
      } else {
          request_body.put("phone_number", phone_number);
      }


        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

      System.out.println("status code:"+response.statusCode());
      response.then().log().all();

      SoftAssert softAssert =new SoftAssert();
      if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
          softAssert.fail("mismatch status code:"+"Expected Status Code:"+expected_status_code+"Actual status code:"+response.statusCode());
      }

  }


}
