package pages;

import Helper.Xls_Reader;
//import cucumber.api.junit.Cucumber;
import org.apache.commons.io.FileUtils;
import org.mortbay.log.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.Assert;
import runners.CucumberRunner;

import java.io.File;

public class LoginPage extends CucumberRunner {

    By AboutLink = By.xpath("//*[@id=\"footer\"]//following::a[contains(text(),\"About Us\")]");
    By JobSelect = By.xpath("//*[@class=\"card is-stretched u-shadow\"]//child::a[contains(text(),\" Front End Engineer\t\")]");
    By EasyApplyBtn = By.xpath("//*[@class=\"card\"]//div[@id=\"jobToolsHeader\"]//child::a[contains(text(),\"Easy apply\")]");

    By HamburgerIcon= By.xpath("(//*[@id='baytNav']//following::li[@class='is-first']//li[@class='popover-owner'])[4]");
    By AccountSettingOption= By.xpath("(//*[@id='baytNav']//following::div[@data-placement='br'])[4]//a[contains(text(),'Account')]");
    By DeleteAccount= By.xpath("//div[@class='card-content']//a[contains(text(),'Delete')]");
    By DeleteAccountBtn= By.xpath("//button[contains(text(),'Delete')]");
    By YesBtn= By.xpath("//div[@class='modal-foot']//button[contains(text(), 'Yes')]");
    By Login= By.xpath("//*[contains(text(),'Log In')]");

    String path = System.getProperty("user.dir");
    String rel = path + "//src//test//java//TestData//userInputSS.xlsx";
    Xls_Reader x = new Xls_Reader(rel);


    public void aboutUsLink() throws Exception {
        WebElement AboutLinkBtn = driver.findElement(AboutLink);
        explicitWait(AboutLinkBtn);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", AboutLinkBtn);
        jsClick(AboutLinkBtn);

    }

    public void clickSpecificJob() throws Exception {
        WebElement JobLinkBtn = driver.findElement(JobSelect);
        explicitWait(JobLinkBtn);
        jsClick(JobLinkBtn);
    }

    public void applyToJob() throws Exception {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!winHandle.equals(winHandleBefore)) {
                driver.switchTo().window(winHandle);

                WebElement ApplyBtn = driver.findElement(EasyApplyBtn);
                explicitWait(ApplyBtn);
                jsClick(ApplyBtn);
            }
        }
    }



    public void hitLoginURL() throws Exception {
        driver.get("https://www.bayt.com/en/login/");
        logger.info("******** Navigating to the Login page*********");
    }

    public void accountSettings() throws Exception
    {
        WebElement HamIcon = driver.findElement(HamburgerIcon);
        explicitWait(HamIcon);
        jsClick(HamIcon);

        WebElement AccountOpt = driver.findElement(AccountSettingOption);
        explicitWait(AccountOpt);
        jsClick(AccountOpt);
    }

    public void removeAccount() throws Exception
    {
        WebElement deleteBtn = driver.findElement(DeleteAccount);
        explicitWait(deleteBtn);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", deleteBtn);
        jsClick(deleteBtn);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile= new File("DeleteAccountPage.png");
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot for full page is captured successfully!");

        WebElement deleteAccountPermanently = driver.findElement(DeleteAccountBtn);
        explicitWait(deleteAccountPermanently);
        jsClick(deleteAccountPermanently);
        File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile2= new File("AfterDeletion.png");
        FileUtils.copyFile(screenshot2, destFile2);
        System.out.println("Screenshot for full page is captured successfully!");

        WebElement yesOption = driver.findElement(YesBtn);
        explicitWait(yesOption);
        jsClick(yesOption);

        WebElement LoginBtn= driver.findElement(Login);
        explicitWait(LoginBtn);
        Assert.assertTrue(LoginBtn.isDisplayed());
        System.out.print("User successfully gets deleted");
    }
}