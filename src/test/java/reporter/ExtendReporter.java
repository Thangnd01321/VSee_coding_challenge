package reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReporter {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter htmlReporter;

    public static ExtentReports getInstance() {
        if (extent == null) {
            htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Test Report");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Host Name", "Your Host");
            extent.setSystemInfo("Environment", "Your Environment");
            extent.setSystemInfo("User Name", "Your Name");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
}

