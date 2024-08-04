package selenium.pageobject.provider;

import org.openqa.selenium.By;
import selenium.core.WebLocator;
import selenium.pageobject.common.CommonPage;

public class VisitPage extends ProviderCommonPage<VisitPage> {
  Page page;

  public VisitPage() {
    super();
    page = new Page();
  }

  public VisitPage waitForPatientInfo() {
    page.sectionPatientInfo.waitUntilVisible();
    return this;
  }

  static class Page {
    public WebLocator sectionPatientInfo = new WebLocator(By.id("patient-info-area"));
  }
}
