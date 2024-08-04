package selenium.pageobject.common;

import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class VisitStartPage extends BasePage {
  Page page;

  public VisitStartPage() {
    super();
    page = new Page();
  }

  public VisitStartPage verifyPageTitle() {
    page.lblPageTitle.waitUntilVisible();
    return this;
  }

  public VisitStartPage clickOnRememberMyChoiceCheckbox() {
    page.chkRememberMyChoice.waitUntilClickable().click();
    return this;
  }

  public VisitStartPage selectContinueOnThisBrowser() {
    page.btnContinueOnThisBrowser.moveToElement().click();
    return this;
  }

  public VisitStartPage selectJoinFromVSeeApp() {
    page.btnJoinFromVSeeApp.waitUntilClickable().click();
    return this;
  }

  static class Page {
    public WebLocator lblPageTitle =
        new WebLocator(By.xpath("//h2[text()='How do you want to meet today?']"));
    public WebLocator chkRememberMyChoice = new WebLocator(By.id("calling_method"));
    public WebLocator btnContinueOnThisBrowser =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'call_options') and ./div/h3[text()='Continue on this browser']]"));
    public WebLocator btnJoinFromVSeeApp =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'call_options') and ./div/h3[text()='Join from VSee Messenger app']]"));

    public WebLocator imgRightArrow =
        new WebLocator(By.xpath("//img[contains(@src, 'right_arrow')]"));
  }
}
