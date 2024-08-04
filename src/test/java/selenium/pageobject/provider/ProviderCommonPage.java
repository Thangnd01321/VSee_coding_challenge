package selenium.pageobject.provider;

import selenium.pageobject.common.CommonPage;
import selenium.pageobject.common.Webchat;

import java.net.MalformedURLException;

public class ProviderCommonPage<T extends ProviderCommonPage<T>> extends CommonPage<T> {
  public ProviderHeader header;
  public Webchat webchat;

  public ProviderCommonPage()  {
    super();
    header = new ProviderHeader();
    webchat = new Webchat();
  }
}
