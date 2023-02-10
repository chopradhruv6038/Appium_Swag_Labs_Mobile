package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]")

    private WebElement swagLabsLogoLoginPg;

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(id = "test-Username")
    private WebElement usernameTxtFld;

    @AndroidFindBy (accessibility = "test-Password")
    @iOSXCUITFindBy(id ="test-Password")
    private WebElement passwordTxtFld;

    @AndroidFindBy (accessibility = "test-LOGIN")
    @iOSXCUITFindBy(id = "test-LOGIN")
    private WebElement logintxtFld;

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    @iOSXCUITFindBy(id = "Username and password do not match any user in this service.")
    private WebElement errorTxtInvalidUsrnmPswd;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    @iOSXCUITFindBy(id = "Sorry, this user has been locked out.")
    private WebElement errorTextLockedOutUser;

    //Static text.

    String expectedErrorTextInvalidUsernamePasswrd = "Username and password do not match any user in this service.";

    String expectedErrorTextLockedOutUser = "Sorry, this user has been locked out.";


    //methods:

    public LoginPage assertSwagLabsLogoOnLoginPage(){

        Assert.assertTrue(swagLabsLogoLoginPg.isDisplayed());
        Assert.assertTrue(swagLabsLogoLoginPg.isEnabled());

        return this;
    }



    public LoginPage enterUserName (String username){
        sendkeys(usernameTxtFld, username);
        return this;
    }

    public LoginPage enterPassword (String password){
        sendkeys(passwordTxtFld, password);
        return this;
    }

    public ProductsPage pressLoginBtn () {
        logintxtFld.click();
        return new ProductsPage();

    }

    public String getInvalidUsernamePasswordErrorText(){

        return getText(errorTxtInvalidUsrnmPswd);

    }

    public String expectedErrorTextInvalidUsernamePassword(){

        return expectedErrorTextInvalidUsernamePasswrd;

    }

    public void assertInvalidUsernamePasswordError(){

        Assert.assertEquals(getInvalidUsernamePasswordErrorText(),expectedErrorTextInvalidUsernamePassword());

        System.out.println("Actual Error Text: " + getInvalidUsernamePasswordErrorText() + "Expected Error Text: " + expectedErrorTextInvalidUsernamePassword());


    }

    public String getLockedOutUserErrorText(){

        return getText(errorTextLockedOutUser);

    }

    public String expectedLockedOutUserText(){

        return expectedErrorTextLockedOutUser;

    }

public void assertLockedOutUserErrorText(){

        Assert.assertEquals(getLockedOutUserErrorText(), expectedLockedOutUserText());

        System.out.println("Actual locked out user error text : " + getLockedOutUserErrorText()  + "Expected locked put user error text : " + expectedLockedOutUserText());

}

}








