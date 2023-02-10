package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"PRODUCTS\"]")
    private WebElement productsPageTitle;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"\uDB81\uDF41\"])[1]/XCUIElementTypeOther/XCUIElementTypeImage")
    private WebElement SauceLabsBackPackIMG;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Sauce Labs Backpack\"")
    private WebElement productSLB;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    private WebElement SauceLabsBackPackTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    private WebElement sauceLabsBackPackPrice;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]")
    private WebElement AddToCartBtn;

    @AndroidFindBy(accessibility = "test-Menu")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    private WebElement burgerMenuProdPg;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGOUT\"]")
    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutLnkProdPg;


    String expectedProductsPageTitle = "PRODUCTS";
    String expectedSauceLabsBackPackTitle = "Sauce Labs Backpack";
    String expectedSauceLabsBackPackPrice = "$29.99";
    String expectedAddToCartBtnText = "ADD TO CART";


    public String getActualProductsPageTitle() {

        return getText(productsPageTitle);

    }

    public String ProductsPageExpectedTitle() {

        return expectedProductsPageTitle;

    }

    public ProductsPage assertProductsPageTitleText() {

        Assert.assertEquals(getActualProductsPageTitle(), ProductsPageExpectedTitle());

        System.out.println("\n" + "Actual Products page title : " + getActualProductsPageTitle() + "\n" + "Expected products page title : " + ProductsPageExpectedTitle() + "\n");

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

        return getText(SauceLabsBackPackTitle);

    }

    public String sauceLabsBackPackExpectedTitle() {

        return expectedSauceLabsBackPackTitle;


    }

    public ProductsPage assertSauceLabsBackPackTitle() {

        Assert.assertEquals(getSauceLabsBackPackTitle(), sauceLabsBackPackExpectedTitle());

        System.out.println("\n" + "Actual SLB Title : " + getSauceLabsBackPackTitle() + "\n" + "Expected SLB Title : " + sauceLabsBackPackExpectedTitle());

        return this;
    }

    public String getSauceLabsBackPackPrice() {

        return getText(sauceLabsBackPackPrice);
    }

    public String expectedPriceSauceLabsBackPack() {

        return expectedSauceLabsBackPackPrice;

    }

    public ProductsPage assertSauceLabsBackPackPrice() {

        Assert.assertEquals(getSauceLabsBackPackPrice(), expectedPriceSauceLabsBackPack());

        System.out.println("\n" + "Actual SLB Price : " + getSauceLabsBackPackPrice() + "\n" + "Expected SLB Price : " + expectedPriceSauceLabsBackPack());

        return this;

    }

    public String getAddToCartBtnText() {

        return getText(AddToCartBtn);
    }

    public String expectedAddToCartBtnText() {

        return expectedAddToCartBtnText;
    }

    public String getAddToCartBtnClickableValue() {

        return getText(AddToCartBtn);

    }

    public ProductsPage assertAddToCartBtn() {

        Assert.assertEquals(getAddToCartBtnText(), expectedAddToCartBtnText());
        assert AddToCartBtn.isDisplayed();
        assert AddToCartBtn.isEnabled();
        //Assert.assertEquals(getAddToCartBtnClickableValue(), "false");
        return this;
    }

    public ProductsDescriptionPage clickSlbProduct() {

        click(productSLB);

        return new ProductsDescriptionPage();
    }

    public ProductsPage clickBurgerMenu() {



        click(burgerMenuProdPg);

        return this;
    }

    public LoginPage clickLogoutBtn(){

        click(logoutLnkProdPg);

    return new LoginPage();
    }

}