package reporter;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtendReporterTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtendReporter.getInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtendReporter.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtendReporter.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtendReporter.createTest(result.getMethod().getMethodName()).log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtendReporter.createTest(result.getMethod().getMethodName()).log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtendReporter.createTest(result.getMethod().getMethodName()).log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }
}
