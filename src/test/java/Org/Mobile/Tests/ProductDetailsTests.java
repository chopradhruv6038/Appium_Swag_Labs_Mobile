package Org.Mobile.Tests;

import Org.Mobile.Base.BaseTest;
import Org.Mobile.pom.LoginPage;
import Org.Mobile.pom.ProductsDescriptionPage;
import Org.Mobile.pom.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductDetailsTests extends BaseTest {



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

        System.out.println("\n" + " +*** Starting Test : ProductsDetailsTests, Method Name : " + m.getName() + " ****");

    }


    @Test
    public void productsDetailsValidations() {

        loginPage.enterUserName(loginUsers.getJSONObject("validUserNamePassword").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUserNamePassword").getString("password"));
        loginPage.pressLoginBtn();

        productsDescriptionPage = productsPage.clickSlbProduct();
        productsDescriptionPage.scrollToSLBPriceIOS()
                .assertSLBPriceProdDetailsPg(loginUsers.getJSONObject("SLBDetails").getString("SLBPrice"));


    }

    @AfterClass
    public void afterClass() {

         terminateApp();
         launchApp();

    }







}
