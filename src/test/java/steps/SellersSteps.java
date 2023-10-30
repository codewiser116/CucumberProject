package steps;

import entities.CustomResponses;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class SellersSteps {

    @Given("user hits get all sellers api with {string} and parameters: isArchid false, page {int}, size {int}")
    public void user_hits_get_all_sellers_api_with_and_parameters_is_archid_false_page_size(String isArchived, Integer page, Integer size) {
        String path = "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", page);
        params.put("size", size);
        APIRunner.runGET(path, params);
    }
    @Then("user gets all sellers' phone number")
    public void user_gets_all_sellers_phone_number() {
        int counter = 0;
        for (CustomResponses cr: APIRunner.getCustomResponses().getResponses()) {
            System.out.println("seller's phone number: " + cr.getPhone_number());
            counter ++;
            Assert.assertNotNull(cr.getPhone_number());
        }
        System.out.println("total: " + counter);
    }
}
