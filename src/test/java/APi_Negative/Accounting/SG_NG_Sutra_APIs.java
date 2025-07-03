package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SG_NG_Sutra_APIs {
    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Sutra_APIs_Excel.xlsx").getAbsolutePath();

    @DataProvider(name = "Post_Buy_Offline")
    public Object[][] PostBuyOffline() {
        return TestDataExtractor.ExcelData(workbook, "Post_Buy_Offline");
    }
    @Test(dataProvider = "Post_Buy_Offline")
    @Story("This story belongs to the Post_Buy_Offline")
    public void PostBuyOffline(String userId,String quantity ,String scenario ,String id, String currency, String amount, String email, String expected_status_code,String description) {

        String url = "https://devoutshade.sacredgroves.earth/api/sutra/buyoffline";
        JSONObject request_body = new JSONObject();
        request_body.put("userId", userId);
        request_body.put("quantity", quantity);
        request_body.put("scenario", scenario);
        request_body.put("id", id);
        request_body.put("currency", currency);
        request_body.put("amount", amount);
        request_body.put("email", email);


        if (userId == null || userId.isEmpty()) {
            request_body.put("user_id", "");
        } else {
            request_body.put("userId", userId);
        }

        if (quantity == null || quantity.isEmpty()) {
            request_body.put("quantity", "");
        } else if (!quantity.isEmpty()) {
            request_body.put("quantity", quantity.matches("-?\\d+") ? Integer.valueOf(quantity) : quantity);
        }

        if (scenario == null || scenario.isEmpty()) {
            request_body.put("scenario", "");
        } else {
            request_body.put("scenario", scenario);
        }

        if (id == null || id.isEmpty()) {
            request_body.put("id", "");
        } else {
            request_body.put("id", id);
        }

        if (currency == null || currency.isEmpty()) {
            request_body.put("currency", "");
        } else {
            request_body.put("currency", currency);
        }

        if (amount == null || amount.isEmpty()) {
            request_body.put("amount", "");
        } else if (!amount.isEmpty()) {
            request_body.put("amount", amount.matches("-?\\d+(\\.\\d+)?") ? Double.valueOf(amount) : amount);
        }

        if (email == null || email.isEmpty()) {
            request_body.put("email", "");
        } else {
            request_body.put("email", email);
        }

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
    @Test
    @Story("This story belongs to the Get Forest Map")
    public void GetForestMap_WithoutToken() {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/sutra/map/forest?forestId=UNI_GIGR_00001&iframe_height=250&iframe_width=450&zoom_start=17";
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
    @Test
    @Story("This story belongs to the Get Map Cluster")
    public void GetMapCluster_WithoutToken() {
        //Call to the method for Authorization Token

        String url = "https://devoutshade.sacredgroves.earth/api/sutra/map/cluster?forestId=UNI_GIGR_00001&iframe_height=250&iframe_width=450&zoom_start=17";
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
}
