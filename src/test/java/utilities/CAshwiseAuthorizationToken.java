package utilities;

import api.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.Config;

public class CAshwiseAuthorizationToken {

    public static String getToken() {
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("emailCashwise"));
        requestBody.setPassword(Config.getProperty("passwordCashwise"));
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(Config.getProperty("cashWiseURI") +
                "/api/myaccount/auth/login");
        response.prettyPrint();
        String token = response.jsonPath().getString("jwt_token");
        System.out.println("token : " + token);
        return token;
    }
}
