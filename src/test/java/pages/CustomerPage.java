package pages;

import Helper.ReadCellData;
import Helper.Xls_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runners.CucumberRunner;

import java.util.HashMap;
import java.util.List;

public class CustomerPage extends CucumberRunner {

    By CustomerTab = By.xpath("//a[text()='New Customer']");
    By CustomerField = By.name("name");
    By GenderSelect = By.cssSelector("input[value='m']");
    By DateSelect = By.id("dob");
    By AddressSelect = By.name("addr");
    By CitySelect = By.name("city");
    By StateSelect = By.name("state");
    By PinSelect = By.name("pinno");
    By MobileSelect = By.name("telephoneno");

    By EmailSelect = By.name("emailid");
    By PassSelect = By.name("password");
    By SubmitSelect = By.cssSelector("input[value='Submit']");
    By CustPageValidate = By.xpath("//*[text()=\"Customer Registered Successfully!!!\"]");
    //    By Close = By.xpath("//*[contains(text(),'Close')]"); //Close Ad
    By Close = By.xpath("//div[@aria-label='Close ad' or @id='dismiss-button']");

    String path = System.getProperty("user.dir");
    String rel = path + "//src//test//java//TestData//userInputSS.xlsx";
    Xls_Reader x = new Xls_Reader(rel);

    public void selectNewCustomer() throws Exception {
        WebElement CustomerTabs = driver.findElement(CustomerTab);
        explicitWait(CustomerTabs);
        CustomerTabs.click();
    }

    public void PassCustomer(String cust) throws Exception {
        WebElement CustField = driver.findElement(CustomerField);
        String searchvaluePass = x.getCellData1("LoginAndSignup", cust, 2);
        CustField.clear();
        CustField.sendKeys(searchvaluePass);
        explicitWait(CustField);
    }

    public void removeAdd() throws Exception {
        Thread.sleep(5000);
        WebElement ParentFrame = driver.findElement(By.xpath("//iframe[@title='3rd party ad content']"));
        explicitWait(ParentFrame);
        driver.switchTo().frame(ParentFrame);

        WebElement ChildFrame = driver.findElement(By.xpath("//iframe[@title='Advertisement']"));

        if (!ChildFrame.isDisplayed()) {
            WebElement closeBtn = driver.findElement(Close);
            closeBtn.click();
//            driver.switchTo().frame(ChildFrame);
        } else {
            driver.switchTo().frame(ChildFrame);
            WebElement closeBtn = driver.findElement(Close);
            closeBtn.click();
        }

        driver.switchTo().defaultContent(); // Return to main window
    }


    public void selectGender() throws Exception {
        WebElement SelectGender = driver.findElement(GenderSelect);
        SelectGender.click();
    }

    public void selectDate(String dateF) throws Exception {
        WebElement DateField = driver.findElement(DateSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", dateF, 2);
        DateField.clear();
        DateField.sendKeys(enterValueDate);
    }

    public void selectAddress(String addressF) throws Exception {
        WebElement AddressField = driver.findElement(AddressSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", addressF, 2);
        AddressField.clear();
        AddressField.sendKeys(enterValueDate);
    }

    public void selectCity(String CityF) throws Exception {
        WebElement CityField = driver.findElement(CitySelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", CityF, 2);
        CityField.clear();
        CityField.sendKeys(enterValueDate);
    }

    public void selectState(String stateF) throws Exception {
        WebElement StateField = driver.findElement(StateSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", stateF, 2);
        StateField.clear();
        StateField.sendKeys(enterValueDate);
    }

    public void selectPin(String PinF) throws Exception {
        WebElement PinField = driver.findElement(PinSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", PinF, 2);
        PinField.clear();
        PinField.sendKeys(enterValueDate);
    }

    public void selectMobile(String MobileF) throws Exception {
        WebElement MobileField = driver.findElement(MobileSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", MobileF, 2);
        MobileField.clear();
        MobileField.sendKeys(enterValueDate);
    }

    public void enterEmail(String EmailF) throws Exception {
        WebElement EmailField = driver.findElement(EmailSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", EmailF, 2);
        EmailField.clear();
        EmailField.sendKeys(enterValueDate);
    }

    public void enterPass(String PassF) throws Exception {
        WebElement PassField = driver.findElement(PassSelect);
        String enterValueDate = x.getCellData1("LoginAndSignup", PassF, 2);
        PassField.clear();
        PassField.sendKeys(enterValueDate);
    }

    public void clickSubmit() throws Exception {
        WebElement SelectSubmit = driver.findElement(SubmitSelect);
        SelectSubmit.click();
        Thread.sleep(3000);
    }

    public void verifyCustomer() throws Exception {
        WebElement CustomerPage = driver.findElement(CustPageValidate);
        explicitWait(CustomerPage);

        Assert.assertTrue(CustomerPage.getText().contains("Successfully"), "Customer successfully registered");
    }

    public void validateRecord() throws Exception {

        //ReadDataFromWeb
        List<WebElement> RecordListValue = driver.findElements(By.xpath("//table[@id='customer']/tbody/tr/td[2]"));
        List<WebElement> RecoredListKey = driver.findElements(By.xpath("//table[@id='customer']/tbody/tr/td[1]"));
        System.out.println("Size of the List ::" + RecordListValue.size());
        HashMap<String, String> ValuesfromWEB = new HashMap<String, String>();
        for (int i = 0; i < RecoredListKey.size() - 1; i++) {
            try {

                ValuesfromWEB.put(RecoredListKey.get(i + 3).getText().trim(), RecordListValue.get(i).getText().trim());
                System.out.println(ValuesfromWEB);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid option");
            }
        }

        //ReadDataFromExcelUsingHashMap
        ReadCellData RD = new ReadCellData();
        String val = RD.getMapData("Mobile");
        System.out.println("Value of the keyword (City) is- " + val);


        //Compare the Results by placing any key in it
        if (RD.getMapData("MobileNumber").equals(ValuesfromWEB.get("Mobile No."))) {
            System.out.println("Successfully matched");
        }


    }
}
