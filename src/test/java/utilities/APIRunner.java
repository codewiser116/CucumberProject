package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.BankAccount;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;

public class APIRunner {

    @Getter
    private static CustomResponses customResponses;

    private static BankAccount[] bankAccounts;

    //create method for HTTP GET

    public static void runGET(String path) {
        //url domain and path
        //paramater and no parameters we will use overloading
        //token
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CAshwiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token)
                .get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            try {
                bankAccounts = mapper.readValue(response.asString(), BankAccount[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void runGET(String path, Map<String, Object> params) {
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CAshwiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).params(params)
                .get(url);
        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely List of response");
        }
    }

    public static void runPOST(String path, RequestBody requestBody) {
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CAshwiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely List of response");
        }
    }

    public static void runPUT(String path, RequestBody requestBody) {
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CAshwiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).put(url);

        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely List of response");
        }
    }
}
