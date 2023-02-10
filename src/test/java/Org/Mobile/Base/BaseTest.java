package Org.Mobile.Base;

import Org.Mobile.Utils.TestUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;


public class BaseTest {

    protected static AppiumDriver driver;

    protected static String platformname;

    protected static Properties props;

    protected static String dateTime;

    InputStream inputStream;

    public BaseTest() {

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }


    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void startDriver(String platformName, String platformVersion, String deviceName) throws Exception {

        platformname = platformName;

        TestUtils testUtils = new TestUtils();
        dateTime = testUtils.getDateTime();

        URL url;
        props = new Properties();

        String propFileName = "config.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        props.load(inputStream); // with this the properties file is loaded.

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("deviceName", deviceName);


        switch (platformName) {

            case "android":

                caps.setCapability("automationName", props.getProperty("androidAutomationName"));
                caps.setCapability("appPackage", props.getProperty("androidAppPackage")); // global parameter in config file
                caps.setCapability("appActivity", props.getProperty("androidAppActivity")); // global parameter in config file
                caps.setCapability("avd", props.getProperty("andoidAvd"));
                caps.setCapability("udid", props.getProperty("androidUdid"));
                caps.setCapability("avdLaunchTimeout", "300000");
                caps.setCapability("avdReadyTimeout", "300000");
                caps.setCapability("newCommandTimeout", 1000);

                url = new URL(props.getProperty("appiumURL")); // global parameter in config file
                driver = new AndroidDriver(url, caps);
                System.out.println("Session ID Android : " + driver.getSessionId());

                break;

            case "iOS":

                caps.setCapability("automationName", props.getProperty("iOSAutomationName"));
                caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                caps.setCapability("appLocation", props.getProperty("iOSAppLocation"));
                //caps.setCapability("udid", props.getProperty("iOSUdid")); //ios starts emulator automatically if it is not already started. this command is optional
                caps.setCapability("newCommandTimeout", 500);


                // String iosAppURL = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
                //caps.setCapability("app", iosAppURL); // optional if there is no bundle id of the app available and user need to install the app from .app file

                url = new URL(props.getProperty("appiumURL"));

                driver = new IOSDriver(url, caps);

                System.out.println("Session ID IOS : " + driver.getSessionId());

                break;

            default:
                throw new Exception("invalid platform : " + platformName);

        }


    }


    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement e) {
        clear(e);
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendkeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.clear();
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);

    }

    //Get text method to support IOS and Android as both platforms have different attributes to return text.

    public String getText(WebElement e) {

        switch (platformname) {
            case "android":
                return getAttribute(e, "text");


            case "iOS":
                return getAttribute(e, "label");
        }
        return null;
    }


    public WebElement androidScrollToElement() { //not working need to check

        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".description(\"test-Inventory item page\")).scrollIntoView("
                        + "new UiSelector().description(\"test-ADD TO CART\"));"));

    }


    public void iOSScrollToElement() {
        RemoteWebElement element = (RemoteWebElement) driver.findElement(By.name("test-ADD TO CART"));//Parent element which should be scrollable
        //class name in above statement is the locator
        String elementID = element.getId(); //getting the id of the parent element
        HashMap<String, String> scrollObject = new HashMap<String, String>();//creating a hashmap object
        scrollObject.put("element", elementID); //passing the element id using the element key.
        scrollObject.put("direction", "down"); //direction of the scroll, not needed when child element is declared
        // scrollObject.put("predicateString", "label == \"ADD TO CART\""); //child element to where you want to scroll the page.
        //scrollObject.put("name", "test-ADD TO CART");
        scrollObject.put("toVisible", "Random Text"); // this is needed when you have the child element accessibility id.
        driver.executeScript("mobile:scroll", scrollObject); //using execute script option and using mobile:scroll command we will perform the scrolling for parent element
        //it will scroll upto the height of the scrollable element.

        //Note: if you know the accessibility id of the child element then you don't need to find the parent element
        //replace parent by element and find By.name(accessibility id) and also use to visible key.
    }


    //below methods to terminate and launch apps in after methods so that classes can execute independently.

    public void terminateApp() {

        switch (platformname) {

            case "android":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) driver).terminateApp(props.getProperty("iOSBundleId"));

        }


    }

    public void launchApp() {
        switch (platformname) {

            case "android":
                ((InteractsWithApps) driver).activateApp(props.getProperty("androidAppPackage"));
                break;

            case "iOS":
                ((InteractsWithApps) driver).activateApp(props.getProperty("iOSBundleId"));


        }


    }

    @Parameters({"platformName", "deviceName"})
    @AfterMethod
    public void afterMethod(ITestResult result, String platformName, String deviceName) throws IOException {


        if (result.getStatus() == ITestResult.FAILURE) {

            File destFile = new File("Screenshots" + File.separator + platformName + "_" + deviceName + File.separator + getDateTime() + "_" +
                    result.getTestClass().getRealClass().getSimpleName() + "_"
                    + result.getMethod().getMethodName() + ".png");

            getScreenshot(destFile);

        }

    }


    public void getScreenshot(File destFile) throws IOException {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);

    }


    public String getDateTime(){

        return dateTime;

    }



    @AfterTest
    public void quitDriver() {


        //driver.quit();


    }


}