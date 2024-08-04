package test.web.provider;

import com.aventstack.extentreports.ExtentTest;
import constants.Constant;
import factory.TestDataProvider;
import model.MChatMessage;
import model.MPatient;
import model.MProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selenium.core.DriverManager;
import selenium.pageobject.common.InCallPage;
import selenium.pageobject.common.VisitStartPage;
import selenium.pageobject.provider.DashboardPage;
import selenium.pageobject.provider.LoginPage;
import selenium.pageobject.provider.VisitPage;
import test.web.BaseTest;
import utils.settings.TestConfig;

import java.lang.reflect.Method;
import java.util.List;

public class ProviderTest extends BaseTest {
    protected static ExtentTest test;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    InCallPage inCallPage;
    VisitStartPage visitStartPage;
    VisitPage visitPage;

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        inCallPage = new InCallPage();
        visitStartPage = new VisitStartPage();
        visitPage = new VisitPage();
        test = extent.createTest(method.getName(), "Test Description");
        TestConfig.test.set(test);
    }

    @Test
    @Parameters({"providerId", "patientId"})
    public void providerMakeCallAndSendChat(String providerId, String patientId)
            throws InterruptedException {

        List<MChatMessage> chatMessages = TestDataProvider.getChatMessages();

        MProvider provider = TestDataProvider.getProvider(providerId);
        MPatient patient = TestDataProvider.getPatient(patientId);
        loginPage.openPage(Constant.URL_PROVIDER_LOGIN);
        loginPage
                .verifyPageHeader()
                .enterUsername(provider.getEmail())
                .enterPassword(provider.getPassword())
                .clickOnLoginButton();

        dashboardPage.header.verifyProviderName(provider.getUsername());
        dashboardPage
                .waitForReadyForVisitList()
                //        .expandGettingReady()
                //        .waitForPatientInGettingReadyList(patient.getUsername())
                .waitForPatientInReadyForVisitList(patient.getUsername())
                .clickOnPatientCallButtonInReadyForVisitList(patient.getUsername());

        //    visitStartPage.verifyPageTitle().selectContinueOnThisBrowser();
        inCallPage
                .waitForAddParticipantButton()
                //        .clickOnJoinNowButton()
                .waitForParticipant(patient.getUsername())
                .clickOnVisitChatButton()
                .webchat
                .waitForWebChat();
        for (MChatMessage chatMessage : chatMessages) {
            if (chatMessage.getUserName().equalsIgnoreCase(provider.getUsername()))
                inCallPage.webchat.enterChatMessage(chatMessage.getChatMessage());
            else
                inCallPage.webchat.verifyChatMessage(
                        chatMessage.getUserName(), chatMessage.getChatMessage());
        }
        inCallPage
                .mouseOverSpeaker()
                .clickOnEndCallButton()
                .waitForEndCallModal()
                .clickOnEndVisitForAll();
        visitPage.waitForPatientInfo().header.waitForDashboardButton();

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        String base64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        test.addScreenCaptureFromBase64String(base64);
        extent.flush();
    }
}
