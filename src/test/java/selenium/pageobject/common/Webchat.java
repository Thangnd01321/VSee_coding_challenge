package selenium.pageobject.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selenium.core.BasePage;
import selenium.core.WebLocator;

import java.net.MalformedURLException;

public class Webchat extends BasePage {
  public Page page;

  public Webchat() {
    super();
    page = new Page();
  }

  public Webchat waitForWebChat() {
    page.container.findElement(page.chatbox).waitUntilVisible();
    return this;
  }

  public Webchat enterChatMessage(String message) {
    page.container
        .findElement(page.chatbox)
        .findElement(page.inputChatMessage)
        .waitUntilClickable()
        .sendKeys(message + Keys.ENTER);
    return this;
  }

  public Webchat verifyChatMessage(String username, String message) {
    page.container
        .findElement(page.chatbox)
        .findElement(page.lblChatMessage(username, message))
        .waitUntilVisible();
    return this;
  }

  static class Page {
    public WebLocator container = new WebLocator(By.id("webchat-container"));

    public WebLocator chatbox =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'webchatbox') and .//h4[contains(text(), 'Visit #')]]"));
    public WebLocator inputChatMessage =
        new WebLocator(By.xpath("//input[@placeholder='Type your message here']"));

    public WebLocator lblChatMessage(String username, String message) {
      String xpath =
          String.format(
              "//li[@class = \"webchat-message\" and .//label[contains(@class, \"name\") and contains(text(), \"%s\")]]"
                  + "//div[@class = \"webchat-message-bubble\" and normalize-space()=\"%s\"]",
              username, message);
      return new WebLocator(By.xpath(xpath));
    }
  }
}
