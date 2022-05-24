package pages;

import Helper.Xls_Reader;
//import cucumber.api.junit.Cucumber;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runners.CucumberRunner;

public class LoginPage extends CucumberRunner {

    By EmailFLogin= By.name("uid");
    By PassFLogin= By.name("password");
    By LoginBtn= By.name("btnLogin");
    By Dash= By.xpath("//*[@class=\"heading3\" and text()=\"Welcome To Manager's Page of Guru99 Bank\"]");

    String path = System.getProperty("user.dir");
    String rel = path + "//src//test//java//TestData//userInputSS.xlsx";
    Xls_Reader x = new Xls_Reader(rel);

    public void EmailLogin(String EmailF) throws Exception
    {
        WebElement EmailField= driver.findElement(EmailFLogin);
        String searchvalueEmail = x.getCellData1("LoginAndSignup", EmailF, 2);
        EmailField.clear();
        EmailField.sendKeys(searchvalueEmail);
        explicitWait(EmailField);

    }

    public void PassLogin(String PassF) throws Exception
    {
        WebElement PassField= driver.findElement(PassFLogin);
        String searchvaluePass = x.getCellData1("LoginAndSignup", PassF, 2);
        PassField.clear();
        PassField.sendKeys(searchvaluePass);
        explicitWait(PassField);
    }

    public void ButtonLogin() throws Exception
    {
        WebElement lgnBtn= driver.findElement(LoginBtn);
        jsClick(lgnBtn);
        explicitWait(lgnBtn);
    }

    public void VerifyUserLoogedIn() throws Exception
    {
        WebElement Dashboard= driver.findElement(Dash);
        explicitWait(Dashboard);

        Assert.assertTrue(Dashboard.getText().contains("Welcome"), "Dashboard successfully appeared");
    }
}
