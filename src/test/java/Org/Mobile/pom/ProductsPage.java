package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductsPage extends BaseTest {

@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView") private WebElement productsPageTitle;

String expectedProductsPageTitle = "PRODUCTS";


public String getActualProductsPageTitle(){

    return getAttribute(productsPageTitle, "text");

}

public String ProductsPageExpectedTitle(){

    return expectedProductsPageTitle;

}

public ProductsPage assertProductsPageTitleText(){

    Assert.assertEquals(getActualProductsPageTitle(), ProductsPageExpectedTitle());

    System.out.println("Actual Products page title : " + getActualProductsPageTitle() + "Expected products page title : " + ProductsPageExpectedTitle());

    return this;

}

public ProductsPage assertProductPageTitleTextIsDisplayed(){

    assert productsPageTitle.isDisplayed();

    return this;
}





}
