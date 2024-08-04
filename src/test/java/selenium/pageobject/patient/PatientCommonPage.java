package selenium.pageobject.patient;

import selenium.pageobject.common.CommonPage;
import selenium.pageobject.common.Webchat;

public class PatientCommonPage<T extends PatientCommonPage<T>> extends CommonPage<T> {
  public Webchat webchat;

  public PatientCommonPage() {
    super();
    webchat = new Webchat();
  }
}
