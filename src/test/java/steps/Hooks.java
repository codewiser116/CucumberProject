package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario () {
        System.out.println("my before method");
    }

    @After
    public void afterScenario () {
        System.out.println("my after method");
    }

}
