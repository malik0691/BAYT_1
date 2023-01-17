package runners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.cucumber.listener.ExtentCucumberFormatter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
                tags = {"@TC_01"}
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25000));
        wait.until(ExpectedConditions.visibilityOf((WebElement) element));
    }

    public void explicitWait(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
            wait.until(ExpectedConditions.visibilityOf(element));
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

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}
