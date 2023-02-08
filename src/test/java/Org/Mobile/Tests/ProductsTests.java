package Org.Mobile.Tests;

import Org.Mobile.Base.BaseTest;
import Org.Mobile.pom.LoginPage;
import Org.Mobile.pom.ProductsDescriptionPage;
import Org.Mobile.pom.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductsTests extends BaseTest {

    ProductsPage productsPage;
    LoginPage loginPage;
    ProductsDescriptionPage productsDescriptionPage;
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
    public void beforeMethod(Method m) {

        productsPage = new ProductsPage();
        loginPage = new LoginPage();
        productsDescriptionPage = new ProductsDescriptionPage();

        System.out.println("\n" + " +*** Starting Test : ProductsTests, Method Name : " + m.getName() + " ****");

    }


    @Test
    public void productsValidations() {

        loginPage.enterUserName(loginUsers.getJSONObject("validUserNamePassword").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUserNamePassword").getString("password"));
        loginPage.pressLoginBtn();

        productsPage.assertProductsPageTitleText().assertProductPageTitleTextIsDisplayed()
                .assertSauceLabsBackPackIMGIsDisplayed().assertSauceLabsBackPackTitle()
                .assertSauceLabsBackPackPrice().assertAddToCartBtn();

/*

        productsDescriptionPage = productsPage.clickSlbProduct();
        productsDescriptionPage.scrollToSLBPriceIOS()
                .assertSLBPriceProdDetailsPg(loginUsers.getJSONObject("SLBDetails").getString("SLBPrice"));
*/


    }

    @AfterClass
    public void afterClass() {

         terminateApp();
         launchApp();

    }


}
