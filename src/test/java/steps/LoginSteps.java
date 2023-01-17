package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.LoginPage;
import runners.CucumberRunner;

public class LoginSteps {

    LoginPage lg = new LoginPage();
    CucumberRunner c = new CucumberRunner();


    @And("^scrolldown to the footer and click on AboutUs link$")
    public void scrolldownToTheFooterAndClickOnAboutUsLink() throws Exception{
        lg.aboutUsLink();
    }

    @And("^user click the specific job$")
    public void userClickTheSpecificJob() throws Exception {
        lg.clickSpecificJob();
    }

    @And("^user apply to the job$")
    public void userApplyToTheJob() throws Exception {
        lg.applyToJob();
    }

    @Given("^i am on Login page$")
    public void iAmOnLoginPage() throws Exception{
        lg.hitLoginURL();
    }

    @And("^navigate to the Account Settings$")
    public void navigateToTheAccountSettings() throws Exception {
        lg.accountSettings();
    }

    @And("^scroll down to delete existing account$")
    public void scrollDownToDeleteExistingAccount() throws Exception{
        lg.removeAccount();
    }
}
