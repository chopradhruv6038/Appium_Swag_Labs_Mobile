package Org.Mobile.Listeners;
import Org.Mobile.Base.BaseTest;
import Org.Mobile.Reports.ExtentReports;
import Org.Mobile.pom.ProductsPage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result) {

        if (result.getThrowable() != null) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());

        }
    }


    //Writing Extent reports methods in below methods.

   /* @Override
    public void onTestStart(ITestResult result){ //this method executes before any of our test method executes.
        BaseTest baseTest = new BaseTest();
        ExtentReports.startTest(result.getName(), result.getMethod().getDescription()); // result.getname() gives the test ng method name. result.getMethod().getDescription() gives the method and its description.
        //above loc will start our test.



    }

    @Override
    public void onStart(ITestContext context){ //this method executes before any of our test class executes.


    }
*/
}
