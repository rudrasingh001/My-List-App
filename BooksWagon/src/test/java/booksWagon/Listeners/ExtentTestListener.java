package booksWagon.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import booksWagon.Utils.ExtentReportManager;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
public class ExtentTestListener implements ITestListener {
    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
 
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }
 
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ " + result.getMethod().getMethodName() + " passed.");
    }
 
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ " + result.getMethod().getMethodName() + " failed.");
        test.get().log(Status.FAIL, result.getThrowable());
}
 
    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ " + result.getMethod().getMethodName() + " skipped.");
    }
 
    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Write the report to disk
    }
}