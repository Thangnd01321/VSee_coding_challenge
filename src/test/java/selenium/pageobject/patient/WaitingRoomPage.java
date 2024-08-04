package selenium.pageobject.patient;

import org.openqa.selenium.By;
import selenium.core.WebLocator;
import selenium.pageobject.common.CommonPage;

public class WaitingRoomPage extends CommonPage<WaitingRoomPage> {
  Page page;
  Survey survey;

  public WaitingRoomPage() {
    page = new Page();
    survey = new Survey();
  }

  public WaitingRoomPage enterPatientName(String value) {
    page.inputPatientName.waitUntilClickable().clear().sendKeys(value);
    return this;
  }

  public WaitingRoomPage enterReason(String value) {
    page.inputReason.waitUntilClickable().clear().sendKeys(value);
    return this;
  }

  public WaitingRoomPage clickOnGiveConsentCheckbox() {
    page.chkGiveConsent.waitUntilClickable().click();
    return this;
  }

  public WaitingRoomPage clickOnEnterWaitingRoomButton() {
    page.btnEnterWaitingRoom.waitUntilClickable().click();
    return this;
  }

  public WaitingRoomPage waitForSurveyModal() {
    survey.modal.waitUntilVisible();
    return this;
  }

  public WaitingRoomPage clickOnEndVisitButton() {
    survey.modal.findElement(survey.btnEndCall).waitUntilClickable().click();
    return this;
  }

  public WaitingRoomPage waitForRoomName(String value) {
    page.lblRoomName.waitUntilVisible().waitUntilElementTextContains(value);
    return this;
  }

  class Page {
    public WebLocator lblRoomName =
        new WebLocator(By.xpath("//h3[contains(@data-bind, 'waitingRoomName')]"));

    public WebLocator inputPatientName = new WebLocator(By.name("first_name"));
    public WebLocator inputReason = new WebLocator(By.name("reason_for_visit"));
    public WebLocator chkGiveConsent = new WebLocator(By.id("jsonform-1-elt-consent"));
    public WebLocator btnEnterWaitingRoom =
        new WebLocator(By.xpath("//input[@value='Enter Waiting Room']"));
  }

  class Survey {
    public WebLocator modal = new WebLocator(By.xpath("//div[@class='modal-content has-survey']"));
    public WebLocator btnEndCall = new WebLocator(By.xpath("//a[text()='End Visit']"));
  }
}
