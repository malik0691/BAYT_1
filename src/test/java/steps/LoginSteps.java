package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.LoginPage;
import runners.CucumberRunner;

public class LoginSteps {

    LoginPage lg = new LoginPage();
    CucumberRunner c = new CucumberRunner();

    @And("^Enter \"([^\"]*)\" data inside Email field for Login$")
    public void enterDataInsideEmailFieldForLogin(String EmailF)  throws Throwable {
        lg.EmailLogin(EmailF);
    }

    @And("^Enter \"([^\"]*)\" data inside Password field for Login$")
    public void enterDataInsidePasswordFieldForLogin(String PassF)  throws Throwable {
        lg.PassLogin(PassF);
    }

    @Then("^click on Login button$")
    public void clickOnLoginButton()throws Throwable {
        lg.ButtonLogin();
    }

    @Then("^Verify that user successfully gets login$")
    public void verifyThatUserSuccessfullyGetsLogin()throws Throwable {
        lg.VerifyUserLoogedIn();
    }

    @Given("^i am on Guru99 page$")
    public void iAmOnGuruPage() throws Exception {

        c.setEnv();
    }
}
