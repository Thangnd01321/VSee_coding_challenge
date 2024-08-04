package test.patient;

import constants.Constant;
import factory.TestDataProvider;
import java.util.List;
import java.util.Map;

import model.MChatMessage;
import model.MPatient;
import model.MProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import selenium.core.DriverManager;
import selenium.pageobject.common.InCallPage;
import selenium.pageobject.patient.WaitingRoomPage;
import utils.enums.Browser;

public class PatientTest {

  WaitingRoomPage waitingRoomPage;
  InCallPage inCallPage;

  @BeforeClass
  @Parameters("browser")
  public void setup(String browser) throws Exception {
    DriverManager.startBrowser(Browser.lookup(browser));
  }

  @BeforeMethod
  public void beforeMethod() {
    waitingRoomPage = new WaitingRoomPage();
    inCallPage = new InCallPage();
  }

  @Test
  @Parameters({"providerId", "patientId"})
  public void patientWalkinCallAndReceiveChat(String providerId, String patientId) {
    List<MChatMessage> chatMessages = TestDataProvider.getChatMessages();
    MProvider provider = TestDataProvider.getProvider(providerId);
    MPatient patient = TestDataProvider.getPatient(patientId);

    waitingRoomPage.openPage(Constant.URL_WAITING_ROOM_03);
    waitingRoomPage
        .waitForRoomName(Constant.ROOM_NAME)
        .enterPatientName(patient.getUsername())
        .enterReason(Constant.VISIT_REASON_01)
        .clickOnGiveConsentCheckbox()
        .clickOnEnterWaitingRoomButton();
    // Move to in call page

    inCallPage
//        .waitForPatientName(patient.getUsername())
//        .clickOnContinueOnReminderModel()
//        .clickOnJoinNowButton()
        .waitForParticipant(provider.getUsername())
        .webchat
        .waitForWebChat();
    for (MChatMessage chatMessage : chatMessages) {
      if (chatMessage.getUserName().equalsIgnoreCase(patient.getUsername()))
        inCallPage.webchat.enterChatMessage(chatMessage.getChatMessage());
      else
        inCallPage.webchat.verifyChatMessage(
            chatMessage.getUserName(), chatMessage.getChatMessage());
    }
    waitingRoomPage
        .waitForSurveyModal()
        .clickOnEndVisitButton()
        .waitForRoomName(Constant.ROOM_NAME);
  }

  @AfterClass
  public void afterClass() {
    DriverManager.quitDriver();
  }
}
