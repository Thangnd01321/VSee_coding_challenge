package selenium.pageobject.common;

import selenium.core.BasePage;

import java.net.MalformedURLException;

public class CommonPage<T extends CommonPage<T>> extends BasePage {
  public Webchat webchat;

  public CommonPage() {
    super();
    webchat = new Webchat();
  }
}
