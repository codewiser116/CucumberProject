package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CAshwiseAuthorizationToken;
import utilities.Config;

public class CashWiseCategoriesTest {

    @Test
    public void createCategory() throws JsonProcessingException {
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("teaching");
        requestBody.setCategory_description("income from teaching");
        requestBody.setFlag(true);
        //above we have ready request body that we build now we can hit API

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

        String jsonResponse = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponse = mapper.readValue(jsonResponse, CustomResponses.class);

        //lines 30-32, we are storing our response as a String, then we are using mapper which comes from Jackson
        //library, we are mapping that response that we stored as String to our POJO CustomResponse Class.
        //Now, we can get any variable in CustomResponse class using getters and setters. Getters and
        //Setters generated behind the scene with the help of lombok plugin and lombok dependancy.

        System.out.println("category id : " + customResponse.getCategory_id());
        System.out.println("created : " + customResponse.getCreated());
    }
}
