package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class ProductSteps {

    @Given("API path {string} and parameters page {int} size {int}")
    public void api_path_and_parameters_page_size(String path, Integer page, Integer size) {

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        APIRunner.runGET(path, params);
    }

    @Then("verify there is a service type and category for each product")
    public void verify_there_is_a_service_type_and_category_for_each_product() {
        System.out.println("response: " + APIRunner.getCustomResponses().getResponseBody());
        int size = APIRunner.getCustomResponses().getResponses().size();
        for (int i = 0; i < size; i++) {
            Assert.assertNotNull(APIRunner.getCustomResponses().getResponses().get(i).getCategory());
            Assert.assertNotNull(APIRunner.getCustomResponses().getResponses().get(i).getService_type());
            System.out.println("assertions passed");
        }
    }

    @Then("verify service type to be {string} or {string}")
    public void verify_service_type_to_be_or(String service, String product) {
        int size = APIRunner.getCustomResponses().getResponses().size();
        for (int i = 0; i < size; i++) {
            int serviceID = APIRunner.getCustomResponses().getResponses().get(i).getService_type().getService_type_id();
            String serviceType = APIRunner.getCustomResponses().getResponses().get(i).getService_type().getService_type();
            if (serviceID == 1) {
                Assert.assertEquals(service, serviceType.trim());
            }
            if (serviceID == 2) {
                Assert.assertEquals(product, serviceType.trim());
            }
        }
    }
}

