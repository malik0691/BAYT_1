package pages;

import Helper.Xls_Reader;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Runner;
import org.openqa.selenium.*;
import org.testng.Assert;
import runners.CucumberRunner;

import java.io.File;
import java.util.Random;

public class HomePage extends CucumberRunner {

    By AboutLink = By.xpath("//*[@id=\"footer\"]//following::a[contains(text(),\"About Us\")]");
    By JobSelect = By.xpath("//*[@class=\"card is-stretched u-shadow\"]//child::a[contains(text(),\"Sales Specialist\")]");
    By EasyApplyBtn = By.xpath("//*[@class=\"card\"]//div[@id=\"jobToolsHeader\"]//child::a[contains(text(),\"Easy apply\")]");

    By EmailFi= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::input[@placeholder='Email Address']");
    By FirstnameF= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::input[@placeholder='First Name']");
    By LastnameF= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::input[@placeholder='Last Name']");
    By PasswordF= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::input[@placeholder='Password']");
    By MobileF= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::input[@placeholder='Your mobile number']");
    By SubmitB= By.xpath("//form[@id='jsApplicantRegisterFormID']//child::button[@name='submit']");

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

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile= new File("JobPage.png");
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot for full page is captured successfully!");
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

    public void checkValidation(String EmailF) throws Exception
    {
        WebElement EmailField = driver.findElement(EmailFi);
        WebElement FirstnameField = driver.findElement(FirstnameF);
        WebElement LastnameField = driver.findElement(LastnameF);
        WebElement MobileField = driver.findElement(MobileF);
        WebElement PasswordField = driver.findElement(PasswordF);
        WebElement submitBn = driver.findElement(SubmitB);

        String searchvalueEmail = x.getCellData1("LoginAndSignup", EmailF, 2);
        EmailField.clear();
        EmailField.sendKeys(searchvalueEmail);
        explicitWait(EmailField);

        FirstnameField.clear();
        FirstnameField.sendKeys("qa");

        LastnameField.clear();
        LastnameField.sendKeys("test");

        MobileField.clear();
        MobileField.sendKeys("3001231231");

        PasswordField.clear();
        PasswordField.sendKeys("KTM@1234567");

        jsClick(submitBn);
        //expected error text
        String exp = "This email is already registered.";
        //identify actual error message
        WebElement m = driver.findElement(By.xpath("//form[@id='jsApplicantRegisterFormID']//div[contains(text(),'This email is already registered. ')]"));
        String act = m.getText();
        System.out.println("Error message is: "+ act);
        //verify error message with Assertion
        Assert.assertEquals(exp, act);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile= new File("RegistrationValidationPage.png");
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot for full page is captured successfully!");
    }

    public void submitRegistration() throws Exception
    {
        WebElement EmailField = driver.findElement(EmailFi);
        WebElement FirstnameField = driver.findElement(FirstnameF);
        WebElement LastnameField = driver.findElement(LastnameF);
        WebElement MobileField = driver.findElement(MobileF);
        WebElement PasswordField = driver.findElement(PasswordF);
        WebElement submitBn = driver.findElement(SubmitB);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        EmailField.clear();
        EmailField.sendKeys("username"+ randomInt +"@gmail.com");
        explicitWait(EmailField);

        FirstnameField.clear();
        FirstnameField.sendKeys("qa");

        LastnameField.clear();
        LastnameField.sendKeys("test");

        MobileField.clear();
        MobileField.sendKeys("3001231231");

        PasswordField.clear();
        PasswordField.sendKeys("KTM@1234567");

        jsClick(submitBn);

        //identify Page title
        WebElement m = driver.findElement(By.xpath("//form[@data-validate-form= 'completeYourCv']//p[contains(text(), 'Tell us about yourself')]"));
        explicitWait(m);
        Assert.assertTrue(m.isDisplayed());

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile= new File("AfterRegistrationPage.png");
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot for full page is captured successfully!");
    }

}
