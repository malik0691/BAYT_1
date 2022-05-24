package pages;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import runners.CucumberRunner;


//public class AfterClass {

//
//    static WebDriver driver=;
//    @After
//    public static void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshotBytes, "image/png");
//        }
//
//    }}