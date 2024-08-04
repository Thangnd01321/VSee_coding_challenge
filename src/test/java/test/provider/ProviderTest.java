package test.provider;

import constants.Constant;
import factory.TestDataProvider;
import java.util.List;
import model.MChatMessage;
import model.MPatient;
import model.MProvider;
import org.testng.annotations.*;
import selenium.core.DriverManager;
import selenium.pageobject.common.InCallPage;
import selenium.pageobject.common.VisitStartPage;
import selenium.pageobject.provider.DashboardPage;
import selenium.pageobject.provider.LoginPage;
import selenium.pageobject.provider.VisitPage;
import utils.enums.Browser;

public class ProviderTest {
  LoginPage loginPage;
  DashboardPage dashboardPage;
  InCallPage inCallPage;
  VisitStartPage visitStartPage;
  VisitPage visitPage;

  @BeforeClass
  @Parameters("browser")
  public void setup(String browser) throws Exception {
    DriverManager.startBrowser(Browser.lookup(browser));
  }

  @BeforeMethod
  public void beforeMethod() {
    loginPage = new LoginPage();
    dashboardPage = new DashboardPage();
    inCallPage = new InCallPage();
    visitStartPage = new VisitStartPage();
    visitPage = new VisitPage();
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
    visitPage.waitForPatientInfo();
  }

  @AfterClass
  public void afterClass() {
    DriverManager.quitDriver();
  }
}
