package selenium.pageobject.common;

import java.time.Duration;
import org.openqa.selenium.By;
import selenium.core.WebLocator;

public class InCallPage extends CommonPage<InCallPage> {
  Page page;
  Header header;
  EndCallModal endCallModal;

  public InCallPage() {
    super();
    page = new Page();
    header = new Header();
    endCallModal = new EndCallModal();
  }

  public InCallPage clickOnContinueOnReminderModel() {
    page.modalReminder.findElement(page.btnContinue).waitUntilClickable().click();
    return this;
  }

  public InCallPage waitForPatientName(String value) {
    page.modalReminder.findElement(page.lblPatientName(value)).waitUntilVisible();
    return this;
  }

  public InCallPage clickOnVisitChatButton() {
    header.btnVisitChat.waitUntilClickable().click();
    return this;
  }

  public InCallPage waitForAddParticipantButton(){
    header.btnAddParticipants.waitUntilVisible(Duration.ofMinutes(2));
    return this;
  }

  public InCallPage clickOnJoinNowButton() {
    switchToIframe(page.iframeVideoCall);

    page.btnJoinNow.waitUntilClickable(Duration.ofMinutes(2)).click();
    switchToMainContent();
    return this;
  }

  public InCallPage clickOnEndCallButton() {
    switchToIframe(page.iframeVideoCall);

    page.iconEndCall.waitUntilClickable().click();
    switchToMainContent();
    return this;
  }

  public InCallPage mouseOverSpeaker() {
    switchToIframe(page.iframeVideoCall);

    page.sectionVideoCall.waitUntilVisible().moveToElement();
    switchToMainContent();
    return this;
  }

  public InCallPage waitForParticipant(String name) {
    switchToIframe(page.iframeVideoCall);

    page.lblSpeakerName(name).waitUntilVisible(Duration.ofMinutes(2));
    switchToMainContent();
    return this;
  }

  public InCallPage waitForEndCallModal() {
    endCallModal.modal.waitUntilVisible();
    return this;
  }

  public InCallPage clickOnEndVisitForAll() {
    endCallModal
        .modal
        .findElement(endCallModal.btnEndVisitForAll)
        .waitUntilVisible()
        .waitUntilClickable()
        .click();
    return this;
  }

  static class Page {
    public String iframeVideoCall = "jitsiConferenceFrame0";
    public WebLocator iconMicrophone = new WebLocator(By.xpath("//div[@class='audio-preview']"));
    public WebLocator iconCamera = new WebLocator(By.xpath("//div[@class='video-preview']"));
    public WebLocator iconShare =
        new WebLocator(By.xpath("//div[@aria-label='Start / Stop sharing your screen']"));
    public WebLocator iconChat =
        new WebLocator(By.xpath("//div[@aria-label='Toggle chat window']"));

    public WebLocator iconToggleMaximizeView =
        new WebLocator(By.xpath("//div[@aria-label='Toggle maximize view']"));

    public WebLocator iconMore = new WebLocator(By.xpath("//div[@aria-label='More actions']"));
    public WebLocator iconEndCall =
        new WebLocator(By.xpath("//div[@aria-label='Leave the meeting']"));

    public WebLocator modalReminder = new WebLocator(By.id("ReminderModal"));
    public WebLocator btnContinue =
        new WebLocator(By.xpath("//button[normalize-space()='Continue']"));
    public WebLocator btnJoinNow = new WebLocator(By.xpath("//div[text()='Join Now']"));
    public WebLocator sectionVideoCall = new WebLocator(By.id("videospace"));

    public WebLocator lblPatientName(String value) {
      return new WebLocator(By.xpath(String.format("//h4[text()='Welcome %s!']", value)));
    }

    public WebLocator lblSpeakerName(String value) {
      return new WebLocator(
          By.xpath(
              String.format(
                  "//div[contains(@class, 'stage-participant-label')]//div[text()='%s']", value)));
    }
  }

  static class Header {
    public WebLocator header = new WebLocator(By.id("visit-participants"));
    public WebLocator btnVisitChat =
        new WebLocator(By.xpath("//button[normalize-space()='Visit Chat']"));
    public WebLocator btnAddParticipants =
        new WebLocator(By.xpath("//button[normalize-space()='Add participants']"));
  }

  static class EndCallModal {
    public WebLocator modal = new WebLocator(By.id("EndAllForm"));
    public WebLocator btnLeaveTheCall =
        new WebLocator(By.xpath("//a[@data-action='Visit.leaveTheCall']"));
    public WebLocator btnEndVisitForAll =
        new WebLocator(By.xpath("//button[text()='End Visit for all']"));
    public WebLocator btnCancel =
        new WebLocator(By.xpath("//button[@data-action='EndVisit.onCancel']"));
  }
}
