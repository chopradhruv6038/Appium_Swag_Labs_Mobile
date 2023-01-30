package Org.Mobile.pom;

import Org.Mobile.Base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {


    @AndroidFindBy(accessibility = "test-Username") private WebElement usernameTxtFld;
    @AndroidFindBy (accessibility = "test-Password") private WebElement passwordTxtFld;
    @AndroidFindBy (accessibility = "test-LOGIN") private WebElement logintxtFld;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorTxt;



    public LoginPage enterUserName (String username){
        sendkeys(usernameTxtFld, username);
        return this;
    }

    public LoginPage enterPassword (String password){
        sendkeys(passwordTxtFld, password);
        return this;
    }



    public ProductsPage pressLoginBtn () {
        click(logintxtFld);

        return new ProductsPage();

    }

    public String getErrorText(){

        return getAttribute(errorTxt, "text");

    }

}








