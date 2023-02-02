package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BaseTest {


    @AndroidFindBy(accessibility = "test-Username") private WebElement usernameTxtFld;
    @AndroidFindBy (accessibility = "test-Password") private WebElement passwordTxtFld;
    @AndroidFindBy (accessibility = "test-LOGIN") private WebElement logintxtFld;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorTxtInvalidUsrnmPswd;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorTextLockedOutUser;

    String expectedErrorTextInvalidUsernamePasswrd = "Username and password do not match any user in this service.";

    String expectedErrorTextLockedOutUser = "Sorry, this user has been locked out.";


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

        return getAttribute(errorTxtInvalidUsrnmPswd, "text");

    }

    public String expectedErrorTextInvalidUsernamePassword(){

        return expectedErrorTextInvalidUsernamePasswrd;

    }

    public void assertInvalidUsernamePasswordError(){

        Assert.assertEquals(getInvalidUsernamePasswordErrorText(),expectedErrorTextInvalidUsernamePassword());

        System.out.println("Actual Error Text: " + getInvalidUsernamePasswordErrorText() + "Expected Error Text: " + expectedErrorTextInvalidUsernamePassword());


    }

    public String getLockedOutUserErrorText(){

        return getAttribute(errorTextLockedOutUser, "text");

    }

    public String expectedLockedOutUserText(){

        return expectedErrorTextLockedOutUser;

    }

public void assertLockedOutUserErrorText(){

        Assert.assertEquals(getLockedOutUserErrorText(), expectedLockedOutUserText());

        System.out.println("Actual locked out user error text : " + getLockedOutUserErrorText()  + "Expected locked put user error text : " + expectedLockedOutUserText());

}

}








