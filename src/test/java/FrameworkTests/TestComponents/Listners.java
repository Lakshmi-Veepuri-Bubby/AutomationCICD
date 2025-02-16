package FrameworkTests.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportsResult;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent=ExtentReportsResult.getReportsObject();
    ThreadLocal<ExtentTest> threadLocal=new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test =extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        test.log(Status.PASS,"Test is passed");
        threadLocal.get().log(Status.PASS,"Test is passed");
        try {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path=null;
        try {
             path = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());
        threadLocal.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        //test.log(Status.FAIL,"Test is failed");
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //test.fail(result.getThrowable());
        threadLocal.get().fail(result.getThrowable());
        String path= null;
        try {
             path=getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        threadLocal.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
