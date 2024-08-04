package test.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.Constant;
import factory.TestDataProvider;
import model.MChatMessage;
import model.MPatient;
import model.MProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.core.DriverManager;
import selenium.pageobject.common.InCallPage;
import selenium.pageobject.common.VisitStartPage;
import selenium.pageobject.provider.DashboardPage;
import selenium.pageobject.provider.LoginPage;
import selenium.pageobject.provider.VisitPage;
import utils.enums.Browser;
import utils.settings.TestConfig;

import java.lang.reflect.Method;
import java.util.List;

public class BaseTest {
    protected static ExtentReports extent;

    @BeforeSuite
    public void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    @Parameters({"browser", "hub_url"})
    public void setup(String browser, String hub) throws Exception {
        DriverManager.setHubUrl(hub);
        DriverManager.startBrowser(Browser.lookup(browser));
    }

    @AfterClass
    public void afterClass() {
        DriverManager.quitDriver();
    }

}
