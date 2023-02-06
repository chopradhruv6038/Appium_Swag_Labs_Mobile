package Org.Mobile.Tests;

import Org.Mobile.Base.BaseTest;
import Org.Mobile.pom.LoginPage;
import Org.Mobile.pom.ProductsPage;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream datais;
    JSONObject loginUsers;


    @BeforeClass
    public void beforeClass() throws IOException {

        try {

            String dataFileName = "Data/loginUsers.json";

            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);

            JSONTokener tokener = new JSONTokener(datais);

            loginUsers = new JSONObject(tokener);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (datais != null) {
                datais.close();
            }
        }


    }


    @BeforeMethod
    public void beforeMethod(Method m) throws IOException { // using argument method to print the test method name

        loginPage = new LoginPage();

        productsPage = new ProductsPage();

        System.out.println("\n" + "*******   Starting Test : LoginTests, Method Name " + m.getName() + "    ******");
        // This will give us the name of the test method that is currently executing.


    }


    @Test
    public void InvalidUserNameTests() throws IOException {

        loginPage.enterUserName(loginUsers.getJSONObject("invalidUserName").getString("userName"));

        loginPage.enterPassword(loginUsers.getJSONObject("invalidUserName").getString("password"));
        loginPage.pressLoginBtn();
        loginPage.assertInvalidUsernamePasswordError();

    }

    @Test
    public void invalidPasswordTests() {

        loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();

        loginPage.assertInvalidUsernamePasswordError();

    }

    @Test
    public void lockedOutUserTests() {

        loginPage.enterUserName(loginUsers.getJSONObject("lockedOutUser").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("lockedOutUser").getString("password"));
        loginPage.pressLoginBtn();

        loginPage.assertLockedOutUserErrorText();

    }


    @Test
    public void successfulLoginTests() {

        loginPage.enterUserName(loginUsers.getJSONObject("validUserNamePassword").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUserNamePassword").getString("password"));
        productsPage = loginPage.pressLoginBtn();

        productsPage.assertProductsPageTitleText();

    }


    @AfterClass
    public void afterClass(){



        terminateApp();

        launchApp();


    }

}
