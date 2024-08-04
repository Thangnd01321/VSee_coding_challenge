package selenium.pageobject.provider;

import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class ProviderHeader extends BasePage {
  Header header;

  public ProviderHeader() {
    super();
    header = new Header();
  }

  public ProviderHeader verifyProviderName(String value) {
    header.lblUserName.waitUntilVisible().waitUntilElementTextContains(value);
    return this;
  }

  static class Header {
    public WebLocator header = new WebLocator(By.id("top_menu-navbar-collapse"));
    public WebLocator lnkDashboard = new WebLocator(By.partialLinkText("Dashboard"));
    public WebLocator lnkPatients = new WebLocator(By.partialLinkText("Patients"));
    public WebLocator lnkSchedule = new WebLocator(By.partialLinkText("Schedule"));
    public WebLocator dropDownUser = new WebLocator(By.id("top_menu_content_user"));
    public WebLocator dropDownMenu = new WebLocator(By.id("top-dropdown-menu"));
    public WebLocator sltDropdownMyAccount = new WebLocator(By.partialLinkText("My Account"));
    public WebLocator sltDropdownMyClinic = new WebLocator(By.partialLinkText("My Clinic"));
    public WebLocator sltDropdownWaitingRoomNotification =
        new WebLocator(By.partialLinkText("Waiting Room Notifications"));
    public WebLocator sltDropdownInvitePatient =
        new WebLocator(By.partialLinkText("Invite Patient"));
    public WebLocator sltDropdownHelp = new WebLocator(By.partialLinkText("Help"));
    public WebLocator sltDropdownTestDevice = new WebLocator(By.partialLinkText("Test Device"));
    public WebLocator sltDropdownLogout = new WebLocator(By.partialLinkText("Logout"));

    public WebLocator lblUserName = new WebLocator(By.xpath("//span[@class='username']"));
  }
}
