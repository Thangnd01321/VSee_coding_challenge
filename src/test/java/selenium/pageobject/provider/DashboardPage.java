package selenium.pageobject.provider;

import org.openqa.selenium.By;
import selenium.core.WebLocator;

public class DashboardPage extends ProviderCommonPage<DashboardPage> {
  public ProviderHeader header;
  RoomSection roomSection;
  ProviderDashboardAction providerDashboardAction;
  TodaySchedule todaySchedule;

  public DashboardPage() {
    roomSection = new RoomSection();
    providerDashboardAction = new ProviderDashboardAction();
    todaySchedule = new TodaySchedule();
    header = new ProviderHeader();
  }

  public DashboardPage expandGettingReady() {
    if (roomSection
        .sectionGettingReady
        .findElement(roomSection.sectionWalkinList)
        .getAttributeValue("style")
        .contains("display: none;"))
      roomSection
          .sectionGettingReady
          .findElement(roomSection.iconExpand())
          .waitUntilClickable()
          .click();
    return this;
  }

  public DashboardPage waitForPatientInGettingReadyList(String patientName) {
    roomSection
        .sectionGettingReady
        .findElement(roomSection.sectionVisit(patientName))
        .waitUntilVisible();
    return this;
  }

  public DashboardPage waitForReadyForVisitList(){
    roomSection.sectionReadyForVisits.waitUntilVisible();
    return this;
  }

  public DashboardPage waitForPatientInReadyForVisitList(String patientName) {
    roomSection
        .sectionReadyForVisits
        .findElement(roomSection.sectionVisit(patientName))
        .waitUntilVisible();
    return this;
  }

  public DashboardPage clickOnPatientCallButtonInReadyForVisitList(String patientName) {
    roomSection
        .sectionReadyForVisits
        .findElement(roomSection.sectionVisit(patientName))
        .findElement(roomSection.iconCall)
        .waitUntilClickable()
        .click();
    return this;
  }

  public class ProviderDashboardAction {
    public WebLocator section = new WebLocator(By.xpath("providerDashboard_action"));
    public WebLocator btnInvitePatient = new WebLocator(By.partialLinkText("Invite Patient"));
    public WebLocator btnSchedule = new WebLocator(By.partialLinkText("Schedule"));
  }

  class RoomSection {
    public WebLocator sectionReadyForVisits =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'panel panel-body') and .//h4[contains(text(), 'Ready for Visits')]]"));
    public WebLocator sectionGettingReady =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'panel panel-body') and .//h4[contains(text(), 'Getting Ready')]]"));
    public WebLocator sectionInProgress =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'panel panel-body') and .//h4[contains(text(), 'In Progress')]]"));
    public WebLocator sectionRecent =
        new WebLocator(
            By.xpath(
                "//div[contains(@class, 'panel panel-body') and .//h4[contains(text(), 'Recent')]]"));
    public WebLocator sectionWalkinList =
        new WebLocator(By.xpath("//div[contains(@class, 'walkin-list')]"));
    public WebLocator iconCall = new WebLocator(By.xpath("//a[@title='Call']"));
    public WebLocator iconChat = new WebLocator(By.xpath("//a[contains(@class, 'btn-vchat')]"));
    public WebLocator linkVisitId = new WebLocator(By.xpath("//a[contains(@class, 'visit_id')]"));
    public WebLocator lblRoomName =
        new WebLocator(By.xpath("//span[contains(@class, 'room-name')]"));
    public WebLocator lblPatientStatus = new WebLocator(By.xpath("//span[@data-vsee='status']"));
    public WebLocator linkPatient =
        new WebLocator(By.xpath("\"//a[contains(@class, 'text-full_name')]\""));

    public WebLocator sectionVisit(String patientName) {
      String xpath =
          String.format(
              "//div[contains(@class, 'section_visit') and .//a[normalize-space() = '%s']]",
              patientName);
      return new WebLocator(By.xpath(xpath));
    }

    public WebLocator iconExpand() {
      return new WebLocator(By.xpath("//i[contains(@class, 'visit-group-operator')]"));
    }
  }

  class TodaySchedule {
    public WebLocator lblTodaySchedule =
        new WebLocator(By.xpath("//h4[text()=\"Today's Schedule\"]"));
    public WebLocator sectionAppointmentList =
        new WebLocator(By.xpath("//div[contains(@class, 'todayScheduleWrap')]"));
  }
}
