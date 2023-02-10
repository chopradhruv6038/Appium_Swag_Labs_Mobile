package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductsDescriptionPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"$29.99\"")
    private WebElement SLBPrice;


//Methods:

    public ProductsDescriptionPage scrollToSLBPrice(){ //Android Scroll not working need to check

        androidScrollToElement();

        System.out.println("Executing scroll");

        return this;
    }

    public ProductsDescriptionPage scrollToSLBPriceIOS(){ //IOS Scroll

        iOSScrollToElement();

        //System.out.println("Executing scroll");

        return this;
    }


    public String expectedSBLPrice(String txt) {

        return txt;

    }

    public String getSLBPrice() {

        return getText(SLBPrice);

    }

    public ProductsDescriptionPage assertSLBPriceProdDetailsPg(String txt) {

        Assert.assertEquals(getSLBPrice(), expectedSBLPrice(txt));

        System.out.println("\n" + "Actual Price for SLB : " + getSLBPrice() + "\n" + "Expected Price for SLB : " + expectedSBLPrice(txt));

        return this;

    }

}