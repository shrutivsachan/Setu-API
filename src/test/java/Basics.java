import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Description;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class Basics {
    public static void main(String[] args) {

        RestAssured.baseURI= "https://cdn-api.co-vin.in/api/v2/auth/public/generateOTP";
        RequestSpecification request = RestAssured.given();
        JSONObject json = new JSONObject();
        json.put("mobile", "9876543210");
        request.header("Content-Type","application/json");
        request.body(json);
        Response response= request.post("/v2/auth/public/generateOTP");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,"200");
        String successcode = response.jsonPath().get("SuccessCode");

        ResponseBody description = response.getBody();
        Assert.assertEquals(description,"successful operation");
        String desc = response.jsonPath().get("description");

        ResponseBody txnid = response.getBody();
        Assert.assertEquals(txnid,"3fa85f64-5717-4562-b3fc-2c963f66afa6");
        String txnId = response.jsonPath().get("txnId");

        System.out.println(response);


    }

}
