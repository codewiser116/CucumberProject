package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CAshwiseAuthorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashwiseSellerTest {

    @Test
    public void getSingleSeller() {
        int id = 1730;
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers/" + id;

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void getAllSellers() {
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", true);
        params.put("page", 1);
        params.put("size", 4);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void createSeller() throws JsonProcessingException {
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers/";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("MCDonalds");
        requestBody.setSeller_name("Joshua");
        requestBody.setEmail("Joshua@gmail.com");
        requestBody.setPhone_number("1234567896");
        requestBody.setAddress("22 S MCdonalds street");

        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        System.out.println("seller id : " + customResponses.getSeller_id());
    }


}
