package api;

import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;

public class CashWiseProfileTest {
    @Test
    public void verifyProfileFields() {

        String path = "/api/myaccount/editors/get/profile";
        APIRunner.runGET(path);
        System.out.println(APIRunner.getCustomResponses().getResponseBody());

        System.out.println("english title: " + APIRunner.getCustomResponses().getBusiness_area().getEnTitle());

        Assert.assertNotNull(APIRunner.getCustomResponses().getBusiness_area().getEnTitle());
        Assert.assertNotNull(APIRunner.getCustomResponses().getBusiness_area().getRuTitle());

        Assert.assertFalse(APIRunner.getCustomResponses().getBusiness_area().getEnTitle().isEmpty());
        Assert.assertFalse(APIRunner.getCustomResponses().getBusiness_area().getRuTitle().isEmpty());
    }
}
