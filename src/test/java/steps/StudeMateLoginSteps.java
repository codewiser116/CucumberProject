package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.StudyMateLoginPage;
import utilities.Config;
import utilities.Driver;

public class StudeMateLoginSteps {

    WebDriver driver = Driver.getDriver();
    StudyMateLoginPage loginPage = new StudyMateLoginPage();

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
      driver.get(Config.getProperty("studyMateUrl"));
      Assert.assertEquals("StudyMate", driver.getTitle());
      System.out.println("user is navigated to Study mate login page");
    }

    @When("user enters email {string} to the email field")
    public void user_enters_email_to_the_email_field(String email) {
        loginPage.emailInput.sendKeys(email);
        System.out.println("user enters email: " + email);
    }

    @When("user enters password {string} to the password field")
    public void user_enters_password_to_the_password_field(String password) {
        loginPage.passwordInput.sendKeys(password);
        System.out.println("user enters password: " + password);
    }

    @Then("user performs click action on the login button")
    public void user_performs_click_action_on_the_login_button() {
        loginPage.loginButton.click();
        System.out.println("user clicked login button");
    }

    @Then("user should be logged in to the application")
    public void user_should_be_logged_in_to_the_application() throws InterruptedException {
        String expectedUrl = "https://codewiser.studymate.us/admin/analytics";
        Thread.sleep(4000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("user couldn't log in", expectedUrl, currentUrl);
        System.out.println("user successfully logged in");
    }

    @Then("user should see error message")
    public void user_should_see_error_message() {
        String expectedError = "Email is not valid!";
        //Thread.sleep(2);
        String actualError = loginPage.emailNotValidError.getText();
        System.out.println("actual error text is: " + actualError);
        Assert.assertEquals("error message is not displayed", expectedError, actualError);
        System.out.println("Expected error displayed");
    }
}
