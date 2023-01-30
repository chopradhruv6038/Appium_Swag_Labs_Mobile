package Org.Mobile.Tests;

import Org.Mobile.Base.BaseTest;
import Org.Mobile.pom.LoginPage;
import Org.Mobile.pom.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {



    LoginPage loginPage;
    ProductsPage productsPage;



    @BeforeMethod
    public void beforeMethod(Method m){ // using argument method to print the test method name

        loginPage = new LoginPage();

        System.out.println("\n" + "*******   Starting Test" + m.getName() + "******" + "\n");
        // This will give us the name of the test method that is currently executing.
    }

    @AfterMethod
    public void AfterMethod(){

    }


    @Test
    public void InvalidUserNameTest() {

        loginPage.enterUserName("invalidusername");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginBtn();


        String actualErrorText = loginPage.getErrorText();
        String ExpectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual Error Text for invalid username : " + actualErrorText + "\n" + "Actual Error Text for invalid username :"  + ExpectedErrorText);

        Assert.assertEquals(actualErrorText, ExpectedErrorText);
    }



    @Test
    public void InvalidPasswordTest() {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalidpassword");
        loginPage.pressLoginBtn();


        String actualErrorText = loginPage.getErrorText();
        String ExpectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual Error Text for invalid username : " + actualErrorText + "\n" + "Actual Error Text for invalid username :"  + ExpectedErrorText);
        // Using TestNG assert class to validate our test case.
        Assert.assertEquals(actualErrorText, ExpectedErrorText);
    }
    @Test
    public void successfullLoginTest() {

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        productsPage = loginPage.pressLoginBtn(); // in this case we are returning to the products page.



        String ActualProductText = productsPage.getProductsTitle();
        String ExpectedProductText = "PRODUCTS";
        System.out.println("Actual Product text : " + ActualProductText + "\n" + "Expected Product Text" + "\n" + ExpectedProductText );
        Assert.assertEquals(ActualProductText, ExpectedProductText);


    }





}
