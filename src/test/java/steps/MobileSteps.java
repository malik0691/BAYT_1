package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.LoginPage;
import pages.MobilePage;
import runners.CucumberRunner;

public class MobileSteps {

    MobilePage mg = new MobilePage();
    CucumberRunner c = new CucumberRunner();


    @Then("^resize the position to iPhonemobile$")
    public void resizeThePositionToIPhonemobile() throws Exception{
        mg.resizeToMobile();
    }

    @And("^search the specific job$")
    public void searchTheSpecificJob() throws Exception {
        mg.searchJob();
    }

    @And("^apply to the job$")
    public void applyToTheJob() throws Exception {
        mg.applyJob();
    }

    @Then("^verify that registration page appears$")
    public void verifyThatRegistrationPageAppears() throws Exception {
        mg.verifyRegistration();
    }

    @Given("^i am on Home page$")
    public void iAmOnHomePage() throws Exception {
        c.setEnv();
    }
}
