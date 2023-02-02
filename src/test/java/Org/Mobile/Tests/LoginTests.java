package Org.Mobile.Tests;

import Org.Mobile.Base.BaseTest;
import Org.Mobile.pom.LoginPage;
import Org.Mobile.pom.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;


    @BeforeMethod
    public void beforeMethod(Method m) throws IOException { // using argument method to print the test method name

        loginPage = new LoginPage();

        productsPage = new ProductsPage();

        System.out.println("\n" + "*******   Starting Test    " + m.getName() + "    ******" + "\n");
        // This will give us the name of the test method that is currently executing.


    }


    @Test
    public void InvalidUserNameTests() throws IOException {

        loginPage.enterUserName("invalid");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginBtn();
        loginPage.assertInvalidUsernamePasswordError();

    }

    @Test
    public void invalidPasswordTests() {

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalid password");
        loginPage.pressLoginBtn();

        loginPage.assertInvalidUsernamePasswordError();

    }

    @Test
    public void lockedOutUserTests() {

        loginPage.enterUserName("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginBtn();

        loginPage.assertLockedOutUserErrorText();

    }



    @Test
    public void successfulLoginTests(){

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        productsPage = loginPage.pressLoginBtn();

        productsPage.assertProductsPageTitleText().assertProductPageTitleTextIsDisplayed();

    }

}
