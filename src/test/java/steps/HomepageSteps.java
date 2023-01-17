package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.HomePage;
import pages.LoginPage;
import runners.CucumberRunner;

public class HomepageSteps {

    HomePage hg = new HomePage();
    CucumberRunner c = new CucumberRunner();

    @Given("^i am on About page$")
    public void iAmOnAboutPage() throws Exception{
        c.setEnv();
    }

    @And("^scrolldown to the footer and click on About link$")
    public void scrolldownToTheFooterAndClickOnAboutLink() throws Exception{
        hg.aboutUsLink();
    }

    @And("^user click on the specific job$")
    public void userClickOnTheSpecificJob() throws Exception{
        hg.clickSpecificJob();
    }

    @And("^user apply to the particular job$")
    public void userApplyToTheParticularJob() throws Exception{
        hg.applyToJob();
    }

    @And("^verify that validation msg gets occur on providing existing \"([^\"]*)\" email$")
    public void verifyThatValidationMsgGetsOccurOnProvidingExistingEmail(String EmailF) throws Exception {
        hg.checkValidation(EmailF);
    }

    @And("^user gets successfully registered upon giving the random email$")
    public void userGetsSuccessfullyRegisteredUponGivingTheRandomEmail() throws Exception{
        hg.submitRegistration();
    }
}
