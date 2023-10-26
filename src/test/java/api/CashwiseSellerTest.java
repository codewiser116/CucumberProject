package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;
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
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers";
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

        //we hit createSeller API, which returns us created seller with seller id, now we want to hit
        //getSeller API with that id that we received from response
        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        System.out.println("seller id : " + customResponses.getSeller_id());
        Assert.assertEquals(200, response.statusCode());
    }

    //create 15 users
    @Test
    public void createManySeller() {
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers";
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();
        for (int i = 0; i < 10; i++) {
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setAddress(faker.address().fullAddress());

            Response response = RestAssured.given().auth().oauth2(token)
                    .contentType(ContentType.JSON).body(requestBody).post(url);

            // System.out.println("status code: " + response.statusCode());
            response.prettyPrint();
        }
    }

    @Test
    public void getEmailsOfAlSellers() throws JsonProcessingException {
        String token = CAshwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 2);
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(params)
                .get(url);

        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        CustomResponses customResponse = mapper.readValue(response.asString(), CustomResponses.class);

        int size = customResponse.getResponses().size();

        for (int i = 0; i < size; i++) {
            System.out.println("user's email: " + customResponse.getResponses().get(i)
                    .getEmail());
        }
    }

    @Test
    public void getSeller() {
        String path = "/api/myaccount/sellers/1853";
        APIRunner.runGET(path);
        System.out.println("seller's name: " + APIRunner.getCustomResponses().getSeller_name());
        System.out.println("seller's email: " + APIRunner.getCustomResponses().getEmail());
    }

    @Test
    public void getSellersList() {
        String path = "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 3);
        params.put("size", 10);

        APIRunner.runGET(path, params);

        System.out.println(APIRunner.getCustomResponses().getResponseBody());
        int counter = 0;
        for (CustomResponses cr: APIRunner.getCustomResponses().getResponses()) {
            //System.out.println("company name: " + cr.getCompanyName());
            counter ++;
        }
        System.out.println("total: " + counter);
    }

    @Test
    public void createNewSeller() {
        Faker faker = new Faker();
        String path = "/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setAddress(faker.address().fullAddress());

        APIRunner.runPOST(path, requestBody);
        System.out.println(APIRunner.getCustomResponses().getResponseBody());
    }

    //create seller -> hit create seller API
    //get response and from that response get seller id
    //get seller -> hit get seller API using seller id from response of previous API hit
    //cross check if seller id in create API and seller id in get API are matching.

    @Test
    public void singSellerCreation () {
        Faker faker = new Faker();
        String path = "/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        String company = faker.company().name();
        String sellerName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String address = faker.address().fullAddress();

        requestBody.setCompany_name(company);
        requestBody.setSeller_name(sellerName);
        requestBody.setEmail(email);
        requestBody.setPhone_number(phoneNumber);
        requestBody.setAddress(address);

        APIRunner.runPOST(path, requestBody);

        //getting seller id from above API hit
        int id = APIRunner.getCustomResponses().getSeller_id();
        System.out.println("seller id from create seller API: " + id);

        //now hit get seller API

        String pathForGetSeller = "/api/myaccount/sellers/" + id;
        APIRunner.runGET(pathForGetSeller);

        Assert.assertEquals(company, APIRunner.getCustomResponses().getCompanyName());
        Assert.assertEquals(sellerName, APIRunner.getCustomResponses().getSeller_name());
        Assert.assertEquals(email, APIRunner.getCustomResponses().getEmail());
        Assert.assertEquals(phoneNumber, APIRunner.getCustomResponses().getPhone_number());
        Assert.assertEquals(address, APIRunner.getCustomResponses().getAddress());
    }
}

