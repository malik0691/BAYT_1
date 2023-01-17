package pages;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v105.emulation.Emulation;
import runners.CucumberRunner;

import java.io.File;
import java.util.Optional;

public class MobilePage extends CucumberRunner {

    By CountryField= By.xpath("(//*[@class='input-pack']//following::input[@placeholder='Select Country'])[2]");
    By SearchCountry= By.xpath("(//*[@class='list-menu-title '])[2]//following::input");
    By CountrySpecific= By.xpath("(//div[@data-placement= 'bl'])[5]//li[@data-value='uae']");

    By JobField= By.xpath("(//*[@class='input-pack']//following::input[@placeholder='Enter job title, skills, etc.'])[2]");
    By SearchJob= By.xpath("(//div[@data-placement= 'bl'])[5]//following::input[@placeholder='Search ...']");
    By JobSpecific= By.xpath("(//div[@data-placement= 'bl'])[5]//li[@data-text='software quality assurance']");

    By JobBtn= By.xpath("//button[@id='search_icon_submit_1']");
    By EasyApplyBtn = By.xpath("(//div[@id=\"results_inner_card\"]//child::a[contains(text(),\"Easy apply\")])[2]");
    By ApplyNowBtn = By.xpath("//button[@name='submit']");


    public void resizeToMobile() throws Exception
    {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setDeviceMetricsOverride(300, 900, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
    }

    public void searchJob() throws Exception
    {
        WebElement SearchPlaceholder = driver.findElement(CountryField);
        explicitWait(SearchPlaceholder);
        jsClick(SearchPlaceholder);

        WebElement inputCountry = driver.findElement(SearchCountry);
        inputCountry.sendKeys("United");
        Thread.sleep(2000);

        WebElement selectCountry = driver.findElement(CountrySpecific);
        jsClick(selectCountry);

        //---------------------------------------------------------------
        WebElement SearchPlaceholder2 = driver.findElement(JobField);
        explicitWait(SearchPlaceholder2);
        jsClick(SearchPlaceholder2);

        WebElement inputJob = driver.findElement(SearchJob);
        inputJob.sendKeys("Software Quality Assurance");
        Thread.sleep(2000);

        WebElement selectJob = driver.findElement(JobSpecific);
        jsClick(selectJob);

        WebElement selectJobBtn = driver.findElement(JobBtn);
        explicitWait(selectJobBtn);
        jsClick(selectJobBtn);

    }

    public void applyJob() throws Exception
    {
        WebElement selectEasyApplyBtn = driver.findElement(EasyApplyBtn);
        explicitWait(selectEasyApplyBtn);
        jsClick(selectEasyApplyBtn);
    }

    public void verifyRegistration() throws Exception
    {
        WebElement selectApplyBtn = driver.findElement(ApplyNowBtn);
        explicitWait(selectApplyBtn);
        Assert.assertTrue(selectApplyBtn.isDisplayed());

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile= new File("MobileRegistrationPage.png");
        FileUtils.copyFile(screenshot, destFile);
        System.out.println("Screenshot for full page is captured successfully!");
        }
}

