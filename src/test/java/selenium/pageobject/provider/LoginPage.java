package selenium.pageobject.provider;

import org.openqa.selenium.By;
import selenium.core.WebLocator;

public class LoginPage extends ProviderCommonPage<LoginPage> {
    Page page;

    public LoginPage() {
        super();
        page = new Page();
    }

    public LoginPage verifyPageHeader() throws InterruptedException {
        page.lblPageHeader().waitUntilVisible().hightlight();
        return this;
    }

    public LoginPage enterUsername(String userName) throws InterruptedException {
        page.inputUsername().waitUntilClickable().hightlight().sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) throws InterruptedException {
        page.inputPassword().waitUntilClickable().hightlight().sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginButton() throws InterruptedException {
        page.btnLogin().waitUntilClickable().hightlight().click();
        return this;
    }

    class Page {
        public WebLocator lblPageHeader() {
            return new WebLocator(By.xpath("//h4[text()='Log In']"));
        }

        public WebLocator inputUsername() {
            return new WebLocator(By.id("AppUserUsername"));
        }

        public WebLocator inputPassword() {
            return new WebLocator(By.id("AppUserPassword"));
        }

        public WebLocator btnLogin() {
            return new WebLocator(By.id("btnSignIn"));
        }

    }
}
