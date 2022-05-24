package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.CustomerPage;
import pages.LoginPage;
import runners.CucumberRunner;

public class CustomerSteps {

    CucumberRunner c = new CucumberRunner();
    LoginPage lg = new LoginPage();
    CustomerPage cp= new CustomerPage();


    @Given("^i am on customer page$")
    public void iAmOnCustomerPage() throws Throwable {
        lg.VerifyUserLoogedIn();
    }

    @Then("^click on NewCustomer tab$")
    public void clickonNewCustomertab() throws Throwable {
        cp.selectNewCustomer();
    }

    @And("^Enter \"([^\"]*)\" data inside CustomerName field$")
    public void enterDataInsideCustomerNameField(String cust) throws Throwable {
        cp.PassCustomer(cust);
    }

    @And("^removed the unnecessary popup$")
    public void removedTheUnnecessaryPopup() throws Throwable{
        cp.removeAdd();
    }

    @And("^Select radio button$")
    public void selectRadioButton() throws Throwable {
        cp.selectGender();
    }


    @And("^Select \"([^\"]*)\" accordingly$")
    public void selectAccordingly(String dateF) throws Throwable{
        cp.selectDate(dateF);
    }

    @And("^Enter \"([^\"]*)\" data inside Address field$")
    public void enterDataInsideAddressField(String AddressF) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        cp.selectAddress(AddressF);
    }

    @And("^Enter \"([^\"]*)\" data inside City field$")
    public void enterDataInsideCityField(String CityF) throws Throwable {
        cp.selectCity(CityF);
    }

    @And("^Enter \"([^\"]*)\" data inside State field$")
    public void enterDataInsideStateField(String StateF) throws Throwable {
        cp.selectState(StateF);
    }

    @And("^Enter \"([^\"]*)\" data inside PIN field$")
    public void enterDataInsidePINField(String PinF) throws Throwable {
        cp.selectPin(PinF);
    }

    @And("^Enter \"([^\"]*)\" data inside MobileNumber field$")
    public void enterDataInsideMobileNumberField(String MobileF) throws Throwable {
        cp.selectMobile(MobileF);
    }

    @And("^Enter \"([^\"]*)\" data inside Email field$")
    public void enterDataInsideEmailField(String EmailF) throws Throwable {
        cp.enterEmail(EmailF);
    }

    @And("^Enter \"([^\"]*)\" data inside Password field$")
    public void enterDataInsidePasswordField(String PassF) throws Throwable {
        cp.enterPass(PassF);
    }

    @Then("^click on Submit button$")
    public void clickOnSubmitButton() throws Throwable {
        cp.clickSubmit();
    }

    @Then("^Verify that user successfully gets created$")
    public void verifyThatUserSuccessfullyGetsCreated() throws Throwable{
        cp.verifyCustomer();
    }

    @Then("^validate the records$")
    public void validateTheRecords()throws Throwable {
        cp.validateRecord();
    }
}
