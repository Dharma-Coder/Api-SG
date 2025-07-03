package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SG_NG_Cluster_Accounting {

    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Cluster_Accounting_Excel.xlsx").getAbsolutePath();

    @DataProvider(name = "Check_Gifting_Eligibility")
    public Object [][] Check_Gifting_Eligibility(){
        return TestDataExtractor.ExcelData(workbook,"Check_Gifting_Eligibility");
    }
    @Test(dataProvider ="Check_Gifting_Eligibility")
    @Story("Check_Gifting_Eligibility")
    public void Check_Gifting_Eligibility(String userId,String requestedSgc , String expected_status_code,String Description){

        String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/eligible/gifting/stored/process";
        JSONObject request_body = new JSONObject();
        request_body.put("userId", userId);
        request_body.put("requestedSgc",requestedSgc);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);
        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode()+"",expected_status_code);
    }

    @DataProvider(name ="Holdings_Ledger")
    public Object[][] Holdings_Ledger(){
        return TestDataExtractor.ExcelData(workbook,"Holdings_Ledger");
    }
    @Test(dataProvider = "Holdings_Ledger")
    @Story("the Holdings_Ledger")
    public void Holdings_Ledger(String userId,String forestId,String expected_status_code,String Description ){

       String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/user/cluster/holdings";
       JSONObject request_body = new JSONObject();
       request_body.put("userId",userId);
       request_body.put("forestId",forestId);

       Response response =given()
               .contentType(ContentType.JSON)
               .accept("application/json")
               .header("Content-Type","application/json")
               .body(request_body.toString())
               .log().all()
               .post(url);

       System.out.println(response.statusCode());
       response.then().log().all();
       response.prettyPrint();
       Assert.assertEquals(response.getStatusCode()+"",expected_status_code);
    }

    @DataProvider(name ="Transaction_Ledger")
    public Object[][] Transaction_Ledger(){
        return TestDataExtractor.ExcelData(workbook,"Transaction_Ledger");
    }
    @Test(dataProvider = "Transaction_Ledger")
    @Story("the Transaction_Ledger")
    public void Transaction_Ledger(String userId,String startDate,String endDate ,String expected_status_code,String Description ){

        String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/user/transaction/ledger";
        JSONObject request_body = new JSONObject();
        request_body.put("userId",userId);
        request_body.put("startDate",startDate);
        request_body.put("endDate",endDate);

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode()+"",expected_status_code);
    }

    @DataProvider(name = "Gifting_Status")
    public Object[][] Gifting_Status(){
        return TestDataExtractor.ExcelData(workbook,"Gifting_Status");
    }

    @Test(dataProvider = "Gifting_Status")
    @Story("This Story Gifting_Status")
    public void Gifting_Status(String userId,String expected_status_code,String Description){

        String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/user/gifting/status";
        JSONObject request_body =new JSONObject();
        request_body.put("userId",userId);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode()+"",expected_status_code);

    }
    @DataProvider(name = "Cluster_Status")
    public Object[][] Cluster_Status(){
       return TestDataExtractor.ExcelData(workbook,"Cluster_Status");
    }
    @Test(dataProvider ="Cluster_Status")
    @Story("This Story is Cluster Status")
    public void ClusterStatus(String userId,String expected_status_code,String Description){

        String url = "https://devaccounting.sacredgroves.earth/api/clustermgmt/user/cluster/status";
        JSONObject request_body =new JSONObject();
        request_body.put("userId",userId);

        Response response = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode()+"",expected_status_code);
    }

    @DataProvider(name="Lock_Cluster")
    public Object[][] Lock_Cluster(){
      return TestDataExtractor.ExcelData(workbook,"Lock_Cluster");
    }
    @Test(dataProvider = "Lock_Cluster")
    @Story("This Story Lock Cluster")
    public void LockCluster(String userId,String requestedSgc,String scenario ,String expected_status_code,String Description ){

        String url = "https://devaccounting.sacredgroves.earth/api/clustermgmt/lock/cluster/";

        JSONObject request_body =new JSONObject();
        request_body.put("userId",userId);
        request_body.put("requestedSgc",requestedSgc);
        request_body.put("scenario",scenario);

        if (requestedSgc.equals("\"\"")) {
            request_body.put("min_price", "");
        } else if (!requestedSgc.isEmpty()) {
            request_body.put("requestedSgc", requestedSgc.matches("-?\\d+") ? Integer.valueOf(requestedSgc) : requestedSgc);
        }

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "Expected status code:" + expected_status_code + "Actual status code:" + response.statusCode());
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "Purchase")
    public Object [][] Purchase(){
        return TestDataExtractor.ExcelData(workbook,"Purchase");
    }
    @Test(dataProvider ="Purchase")
    @Story("This Story is Purchase")
    public  void Purchases(String userId, String transactionRefNo, String transactionTimestamp, String contractStartDate, String giftTakerId , String noOfSGC,
                           String newPurchase, String contractPeriod, String giftGiverId , String isGiftFlag, String countryOfContract,String expected_status_code,String Description){

        Lock_Cluster();

        String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/transaction/listener/stored/process/";

        JSONObject request_body = new JSONObject();

        request_body.put("userId",userId);
        request_body.put("transactionRefNo",transactionRefNo);
        request_body.put("transactionTimestamp",transactionTimestamp);
        request_body.put("contractStartDate",contractStartDate);
        request_body.put("giftTakerId",giftTakerId);
        request_body.put("noOfSGC",noOfSGC);
        request_body.put("newPurchase",newPurchase);
        request_body.put("contractPeriod",contractPeriod);
        request_body.put("giftGiverId",giftGiverId);
        request_body.put("isGiftFlag",isGiftFlag);
        request_body.put("countryOfContract",countryOfContract);


        if (userId == null || userId.isEmpty()) {
            request_body.put("userId", "");
        } else {
            request_body.put("userId", userId);
        }

        if (transactionRefNo == null || transactionRefNo.isEmpty()) {
            request_body.put("transactionRefNo", "");
        } else {
            request_body.put("transactionRefNo", transactionRefNo);
        }

        if (transactionTimestamp == null || transactionTimestamp.isEmpty()) {
            request_body.put("transactionTimestamp", "");
        } else {
            request_body.put("transactionTimestamp", transactionTimestamp);
        }

        if (contractStartDate == null || contractStartDate.isEmpty()) {
            request_body.put("contractStartDate", "");
        } else {
            request_body.put("contractStartDate", contractStartDate);
        }

        if (giftTakerId == null || giftTakerId.isEmpty()) {
            request_body.put("giftTakerId", "");
        } else {
            request_body.put("giftTakerId", giftTakerId);
        }

        if (noOfSGC == null || noOfSGC.isEmpty()) {
            request_body.put("noOfSGC", "");
        } else if (!noOfSGC.isEmpty()) {
            request_body.put("noOfSGC", noOfSGC.matches("-?\\d+") ? Integer.valueOf(noOfSGC) : noOfSGC);
        }

        if (newPurchase == null || newPurchase.isEmpty()) {
            request_body.put("newPurchase", "");
        } else {
            request_body.put("newPurchase", newPurchase);
        }

        if (contractPeriod == null || contractPeriod.isEmpty()) {
            request_body.put("contractPeriod", "");
        } else if (!contractPeriod.isEmpty()) {
            request_body.put("contractPeriod", contractPeriod.matches("-?\\d+") ? Integer.valueOf(contractPeriod) : contractPeriod);
        }

        if (giftGiverId == null || giftGiverId.isEmpty()) {
            request_body.put("giftGiverId", "");
        } else {
            request_body.put("giftGiverId", giftGiverId);
        }

        if (isGiftFlag == null || isGiftFlag.isEmpty()) {
            request_body.put("isGiftFlag", "");
        } else {
            request_body.put("isGiftFlag", isGiftFlag);
        }

        if (countryOfContract == null || countryOfContract.isEmpty()) {
            request_body.put("countryOfContract", "");
        } else {
            request_body.put("countryOfContract", countryOfContract);
        }

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "Expected status code:" + expected_status_code + "Actual status code:" + response.statusCode());
        }
        softAssert.assertAll();

    }

// User - Registration //Gift After Scenario 2


    @DataProvider(name = "Gift_After_Scenario2")
    public Object [][] Gift_After_Scenario2(){
        return TestDataExtractor.ExcelData(workbook,"Gift_After_Scenario2");
    }
    @Test(dataProvider ="Gift_After_Scenario2")
    @Story("This Story is Gift_After_Scenario2")
    public  void Gift_After_Scenario2(String userId, String transactionRefNo, String transactionTimestamp, String contractStartDate, String giftTakerId , String noOfSGC,
                           String newPurchase, String contractPeriod, String giftGiverId , String isGiftFlag, String countryOfContract,String expected_status_code,String Description){


        String url ="https://devaccounting.sacredgroves.earth/api/clustermgmt/transaction/listener/stored/process/";

        JSONObject request_body = new JSONObject();

        request_body.put("userId",userId);
        request_body.put("transactionRefNo",transactionRefNo);
        request_body.put("transactionTimestamp",transactionTimestamp);
        request_body.put("contractStartDate",contractStartDate);
        request_body.put("giftTakerId",giftTakerId);
        request_body.put("noOfSGC",noOfSGC);
        request_body.put("newPurchase",newPurchase);
        request_body.put("contractPeriod",contractPeriod);
        request_body.put("giftGiverId",giftGiverId);
        request_body.put("isGiftFlag",isGiftFlag);
        request_body.put("countryOfContract",countryOfContract);


        if (userId == null || userId.isEmpty()) {
            request_body.put("userId", "");
        } else {
            request_body.put("userId", userId);
        }

        if (transactionRefNo == null || transactionRefNo.isEmpty()) {
            request_body.put("transactionRefNo", "");
        } else {
            request_body.put("transactionRefNo", transactionRefNo);
        }

        if (transactionTimestamp == null || transactionTimestamp.isEmpty()) {
            request_body.put("transactionTimestamp", "");
        } else {
            request_body.put("transactionTimestamp", transactionTimestamp);
        }

        if (contractStartDate == null || contractStartDate.isEmpty()) {
            request_body.put("contractStartDate", "");
        } else {
            request_body.put("contractStartDate", contractStartDate);
        }

        if (giftTakerId == null || giftTakerId.isEmpty()) {
            request_body.put("giftTakerId", "");
        } else {
            request_body.put("giftTakerId", giftTakerId);
        }

        if (noOfSGC == null || noOfSGC.isEmpty()) {
            request_body.put("noOfSGC", "");
        } else if (!noOfSGC.isEmpty()) {
            request_body.put("noOfSGC", noOfSGC.matches("-?\\d+") ? Integer.valueOf(noOfSGC) : noOfSGC);
        }

        if (newPurchase == null || newPurchase.isEmpty()) {
            request_body.put("newPurchase", "");
        } else {
            request_body.put("newPurchase", newPurchase);
        }

        if (contractPeriod == null || contractPeriod.isEmpty()) {
            request_body.put("contractPeriod", "");
        } else if (!contractPeriod.isEmpty()) {
            request_body.put("contractPeriod", contractPeriod.matches("-?\\d+") ? Integer.valueOf(contractPeriod) : contractPeriod);
        }

        if (giftGiverId == null || giftGiverId.isEmpty()) {
            request_body.put("giftGiverId", "");
        } else {
            request_body.put("giftGiverId", giftGiverId);
        }

        if (isGiftFlag == null || isGiftFlag.isEmpty()) {
            request_body.put("isGiftFlag", "");
        } else {
            request_body.put("isGiftFlag", isGiftFlag);
        }

        if (countryOfContract == null || countryOfContract.isEmpty()) {
            request_body.put("countryOfContract", "");
        } else {
            request_body.put("countryOfContract", countryOfContract);
        }

        Response response =given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type","application/json")
                .body(request_body.toString())
                .log().all()
                .post(url);

        System.out.println(response.statusCode());
        response.then().log().all();
        response.prettyPrint();

        SoftAssert softAssert =new SoftAssert();

        if (!String.valueOf(response.statusCode()).equals(expected_status_code)){
            softAssert.fail("Status code mismatch:"+ "Expected status code:" + expected_status_code + "Actual status code:" + response.statusCode());
        }
        softAssert.assertAll();

    }

}
