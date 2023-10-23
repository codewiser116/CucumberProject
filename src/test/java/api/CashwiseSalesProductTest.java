package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CAshwiseAuthorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashwiseSalesProductTest {

    @Test
    public void verifyProductList() {
        // we need token
        String token = CAshwiseAuthorizationToken.getToken();
        // we need endpoint
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/products";
        //we need parameters
        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("size", 4);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }
}
