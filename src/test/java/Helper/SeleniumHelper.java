package Helper;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    protected WebDriver driver;
    public SeleniumHelper() throws IOException {
        this.wait = new WebDriverWait(driver, 15);
        jsExecutor = ((JavascriptExecutor) driver);
    }


    /**********************************************************************************/
}
