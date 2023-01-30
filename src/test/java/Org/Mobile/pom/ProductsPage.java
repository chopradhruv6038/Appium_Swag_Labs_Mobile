package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BaseTest {



    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView") private WebElement productTitleTxt;



    public String getProductsTitle(){

        return getAttribute(productTitleTxt, "text");



    }









}
