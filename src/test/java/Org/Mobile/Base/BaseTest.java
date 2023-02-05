package Org.Mobile.Base;

import Org.Mobile.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static AppiumDriver driver;

    protected static String platformname;

    protected static Properties props;

    InputStream inputStream;

    public BaseTest() {

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }


    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void startDriver(String platformName, String platformVersion, String deviceName) throws Exception {

        platformname = platformName;

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
                caps.setCapability("newCommandTimeout", 500);

                url = new URL(props.getProperty("appiumURL")); // global parameter in config file
                driver = new AndroidDriver(url, caps);
                System.out.println("Session ID : " + driver.getSessionId());

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

    public void clear(WebElement e){
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


    @AfterTest
    public void quitDriver() {

        // driver.quit();


    }


}
