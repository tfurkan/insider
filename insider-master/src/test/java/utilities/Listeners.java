package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.File;

public class Listeners extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log4jManager.info("==============="+"Logging started for"+" "+iTestResult.getMethod().getMethodName()+"==================");
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"PASSED");
        Log4jManager.info("==============="+"Logging ended for"+" "+iTestResult.getMethod().getMethodName()+"==================");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);

            try{
                FileHandler.copy(source, new File("./ScreenShots/"+iTestResult.getName()+".png"));
                System.out.println("Screenshot taken");
            }
            catch (Exception e){
                System.out.println(e.getMessage());

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        Log4jManager.info(iTestResult.getMethod().getMethodName()+":"+"FAILED");
        Log4jManager.error("Falied error thrown", iTestResult.getThrowable());
        Log4jManager.info("==============="+"Logging ended for"+" "+iTestResult.getMethod().getMethodName()+"==================");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
