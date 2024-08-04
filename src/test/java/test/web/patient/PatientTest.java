package test.web.patient;

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
import selenium.pageobject.patient.WaitingRoomPage;
import test.web.BaseTest;
import utils.settings.TestConfig;

import java.lang.reflect.Method;
import java.util.List;

public class PatientTest extends BaseTest {
    protected static ExtentTest test;
    WaitingRoomPage waitingRoomPage;
    InCallPage inCallPage;

    @BeforeMethod
    public void beforeMethod(Method method) {
        waitingRoomPage = new WaitingRoomPage();
        inCallPage = new InCallPage();
        test = extent.createTest(method.getName(), "Test Description");
        TestConfig.test.set(test);
    }

    @Test
    @Parameters({"providerId", "patientId"})
    public void patientWalkinCallAndReceiveChat(String providerId, String patientId) {
        List<MChatMessage> chatMessages = TestDataProvider.getChatMessages();
        MProvider provider = TestDataProvider.getProvider(providerId);
        MPatient patient = TestDataProvider.getPatient(patientId);

        waitingRoomPage.openPage(Constant.URL_WAITING_ROOM_03);
        waitingRoomPage.waitForRoomName(Constant.ROOM_NAME).enterPatientName(patient.getUsername()).enterReason(Constant.VISIT_REASON_01).clickOnGiveConsentCheckbox().clickOnEnterWaitingRoomButton();
        // Move to in call page

        inCallPage
//        .waitForPatientName(patient.getUsername())
//        .clickOnContinueOnReminderModel()
//        .clickOnJoinNowButton()
                .waitForParticipant(provider.getUsername()).webchat.waitForWebChat();
        for (MChatMessage chatMessage : chatMessages) {
            if (chatMessage.getUserName().equalsIgnoreCase(patient.getUsername()))
                inCallPage.webchat.enterChatMessage(chatMessage.getChatMessage());
            else inCallPage.webchat.verifyChatMessage(chatMessage.getUserName(), chatMessage.getChatMessage());
        }
        waitingRoomPage.waitForSurveyModal().clickOnEndVisitButton().waitForRoomName(Constant.ROOM_NAME);
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        String base64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        test.addScreenCaptureFromBase64String(base64);
        extent.flush();
    }
}
