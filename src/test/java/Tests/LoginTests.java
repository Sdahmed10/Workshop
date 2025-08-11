package Tests;

import LaunchBrowser.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.NavigateToLoginPage();
        loginPage.LoginWithValidCredentials();
    }

    @Test(priority = 2)
    public void Logout(){
        LoginPage loginPage = new LoginPage(page);
        loginPage.Logout();
    }
}
