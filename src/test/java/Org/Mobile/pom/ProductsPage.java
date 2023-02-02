package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement productsPageTitle;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement SauceLabsBackPackIMG;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private WebElement SauceLabsBackPackTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private WebElement sauceLabsBackPackPrice;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView")
    private WebElement AddToCartBtn;

    String expectedProductsPageTitle = "PRODUCTS";
    String expectedSauceLabsBackPackTitle = "Sauce Labs Backpack";
    String expectedSauceLabsBackPackPrice = "$29.99";
    String expectedAddToCartBtnText = "ADD TO CART";


    public String getActualProductsPageTitle() {

        return getAttribute(productsPageTitle, "text");

    }

    public String ProductsPageExpectedTitle() {

        return expectedProductsPageTitle;

    }

    public ProductsPage assertProductsPageTitleText() {

        Assert.assertEquals(getActualProductsPageTitle(), ProductsPageExpectedTitle());

        System.out.println("Actual Products page title : " + getActualProductsPageTitle() + "Expected products page title : " + ProductsPageExpectedTitle());

        return this;

    }

    public ProductsPage assertProductPageTitleTextIsDisplayed() {

        assert productsPageTitle.isDisplayed();

        return this;
    }

    public ProductsPage assertSauceLabsBackPackIMGIsDisplayed() {

        assert SauceLabsBackPackIMG.isEnabled();
        assert SauceLabsBackPackIMG.isDisplayed();

        return this;

    }

    public String getSauceLabsBackPackTitle() {

        return getAttribute(SauceLabsBackPackTitle, "text");

    }

    public String sauceLabsBackPackExpectedTitle() {

        return expectedSauceLabsBackPackTitle;


    }

    public ProductsPage assertSauceLabsBackPackTitle() {

        Assert.assertEquals(getSauceLabsBackPackTitle(), sauceLabsBackPackExpectedTitle());

        return this;
    }

    public String getSauceLabsBackPackPrice() {

        return getAttribute(sauceLabsBackPackPrice, "text");
    }

    public String expectedPriceSauceLabsBackPack() {

        return expectedSauceLabsBackPackPrice;

    }

    public ProductsPage assertSauceLabsBackPackPrice() {

        Assert.assertEquals(getSauceLabsBackPackPrice(), expectedPriceSauceLabsBackPack());

        return this;

    }

    public String getAddToCartBtnText(){

        return getAttribute(AddToCartBtn, "text");
    }

    public String expectedAddToCartBtnText(){

        return expectedAddToCartBtnText;
    }

    public String getAddToCartBtnClickableValue(){

        return getAttribute(AddToCartBtn, "clickable");

    }

    public ProductsPage assertAddToCartBtn(){

        Assert.assertEquals(getAddToCartBtnText(), expectedAddToCartBtnText());
        assert AddToCartBtn.isDisplayed();
        assert AddToCartBtn.isEnabled();
        Assert.assertEquals(getAddToCartBtnClickableValue(), "false");
        return this;
    }


}