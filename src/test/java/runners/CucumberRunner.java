package runners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cucumber.listener.ExtentCucumberFormatter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions
        (
                plugin = {"json:target/positive/cucumber.json", "pretty", "html:target/positive/cucumber.html", "com.cucumber.listener.ExtentCucumberFormatter"},
                features = "src/test/resources/features",
                glue = "steps",
                tags = {"@TC_01, @TC_02"}
        )
public class CucumberRunner extends AbstractTestNGCucumberTests {
    public static Properties config = null;
    public static WebDriver driver;
    public static Logger logger;
    public static WebDriverWait wait;


    @BeforeClass
    public void generateHTMLReports() {
//		      Initiates the extent report and generates the output in the output/Run_<unique timestamp>/report.html file by default.
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date curDate = new Date();
        String strDate = sdf.format(curDate);
        String fileName = System.getProperty("user.dir") + "\\ExtentReport\\Godiva " + strDate + ".html";
        File newFile = new File(fileName);
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile, true);
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

        //static report name
//		String fileName = System.getProperty("user.dir")+"\\ExtentReport\\extentreports.html";
//		File newFile = new File(fileName);
//		ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile,true);
        // Loads the extent config xml to customize on the report.
        //ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

        // User can add the system information as follows

        ExtentCucumberFormatter.addSystemInfo("Browser Name", "Chrome ");
        ExtentCucumberFormatter.addSystemInfo("Browser version", "Latest");
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v3.53.0");

    }

    public void LoadConfigProperty() throws IOException {

        logger = Logger.getLogger("Guru99"); //Added logger
        PropertyConfigurator.configure("Log4j.properties");//Added logger

        config = new Properties();
        FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
        config.load(ip);
    }

    public void configureDriverPath() throws IOException {
        if (System.getProperty("os.name").startsWith("Linux")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (System.getProperty("os.name").startsWith("Mac")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (System.getProperty("os.name").startsWith("Windows")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    public void openBrowser() throws Exception {
        // loads the config options
        LoadConfigProperty();
        // configures the driver path
        configureDriverPath();
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void implicitWait(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void explicitWaitBy(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 25000);
        wait.until(ExpectedConditions.visibilityOf((WebElement) element));
    }

    public void explicitWait(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30000);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }
    }

    public void explicitWaitClick(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5000);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }
    }

    public void pageLoad(int time) {
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void setEnv() throws Exception {
        LoadConfigProperty();
        String baseUrl = config.getProperty("siteUrl");
        driver.get(baseUrl);
        logger.info("******** Launching Browser*********");
    }

    public static String SplitNumbers(String split) {

        String regex = "\\d+";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(split);
        System.out.println("Digits in the given string are: ");
        while (matcher.find()) {
            System.out.print(matcher.group() + "");
            String a = (matcher.group() + "");

            return a;
        }
        return null;
    }

    public static String currentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String cal1 = dateFormat.format(cal.getTime());
        return cal1;
    }

    public void selectDropDownValueByIndex(WebElement element, int index) throws Exception {
        implicitWait(120);
        Select dropdown = new Select((element));
        dropdown.selectByIndex(index);
        logger.info("******** Selected Most Recent Date *********");
    }

    public void selectDropDownValueByValue(WebElement element, String Value) throws Exception {
        implicitWait(120);
        Select dropdown = new Select((element));
        dropdown.selectByVisibleText(Value);
        logger.info("******** DropDown Value Selected *********");

    }


    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setUp() throws Exception {
        openBrowser();
        maximizeWindow();
        implicitWait(120);
        deleteAllCookies();
    }


    public void JsClick(WebElement element) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jse.executeScript("arguments[0].click();", element);


    }

    public void Iframe(String element) throws InterruptedException {
        driver.switchTo().frame(element);


    }

    @AfterSuite
    public void quit() throws IOException, InterruptedException {
        driver.quit();
    }
//	public void JsSendKeys(WebElement element) throws InterruptedException {
//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		Thread.sleep(3000);
//		jse.executeScript("arguments[0].value=�Avinash Mishra�;", element);
//
//
//	}
	/*
//	********************************************************************************************
//	*******************************************************************************************/
    //todo reusable methods

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     **JS METHODS & JS SCROLL
     **********************************************************************************/
    public void scrollToElementByWebElementLocator(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
            System.out.println("Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsPageScroll(int numb1, int numb2) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
            System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
        } catch (Exception e) {
            System.out.println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
            Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
        }
    }

    public void waitAndclickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
            System.out.println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement staleElement = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
            if (elementPresent == true) {
                js.executeScript("arguments[0].click();", elementPresent);
                System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
            Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


}
