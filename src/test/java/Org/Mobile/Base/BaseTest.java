package Org.Mobile.Base;

import Org.Mobile.Utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

    protected static Properties props;

    InputStream inputStream;

    public BaseTest() {

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);


    }


    @Parameters({"platformName", "platformVersion", "deviceName", "avd", "udid"})
    @BeforeTest
    public void startDriver(String platformName, String platformVersion, String deviceName,
                            String avd, String udid) throws IOException {

        try {

            props = new Properties();

            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            props.load(inputStream); // with this the properties file is loaded.

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("platformName", platformName);
            caps.setCapability("automationName", props.getProperty("androidAutomationName"));
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("udid", udid);
            caps.setCapability("appPackage", props.getProperty("androidAppPackage")); // global parameter in config file
            caps.setCapability("appActivity", props.getProperty("androidAppActivity")); // global parameter in config file
            caps.setCapability("avd", avd);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("avdLaunchTimeout", "300000");
            caps.setCapability("avdReadyTimeout", "300000");
            caps.setCapability("newCommandTimeout", 500);

            URL url = new URL(props.getProperty("appiumURL")); // global parameter in config file

            driver = new AndroidDriver(url, caps);

            System.out.println("Session ID : " + driver.getSessionId());

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }



    public void waitForVisibility(WebElement e) {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));

        wait.until(ExpectedConditions.visibilityOf(e));
    }


    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }



    public void sendkeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }



    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);

    }


    @AfterTest
    public void quitDriver() {

      // driver.quit();


    }


}
