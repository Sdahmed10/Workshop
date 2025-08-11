package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;

    private final String Page_Text = "OrangeHRM";
    private final String Username_Field = "//input[@placeholder='Username']";
    private final String Pwd_Field = "//input[@placeholder='Password']";
    private final String Login_Button = "//button[normalize-space()='Login']";
    private final String Dashboard_Text = "//h6[normalize-space()='Dashboard']";
    private final String DropDownList_Button = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']";
    private final String Logout_Button = "//a[normalize-space()='Logout']";
    private final String Login_Text = "//h5[normalize-space()='Login']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void NavigateToLoginPage() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        assertThat(page).hasTitle(Page_Text);
        System.out.println("Navigated to Login Page");
    }

    public void LoginWithValidCredentials() {
        page.locator(Username_Field).fill("Admin");
        page.locator(Pwd_Field).fill("admin123");
        page.locator(Login_Button).click();
        Locator Profile = page.locator(Dashboard_Text);
        page.waitForSelector(Dashboard_Text);
        assertThat(Profile).isVisible();
        System.out.println("Login Successful");
        page.waitForTimeout(1000);
        takeScreenshot("Login Successful");
    }

    public void Logout() {
        page.locator(DropDownList_Button).click();
        page.locator(Logout_Button).click();
        Locator Confirm_Logout = page.locator(Login_Text);
        page.waitForSelector(Login_Text);
        assertThat(Confirm_Logout).isVisible();
        System.out.println("Logout Successful");
        takeScreenshot("Logout Successful");
    }

    public void takeScreenshot(String fileName) {
        try {
            String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            String filePath = "/Users/sdiriahmed/IdeaProjects/PlaywrightPOM_Workshop/src/test/java/Utils/" + fileName + "_" + timestamp + ".png";
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(filePath))
                    .setFullPage(true));
            System.out.println("Screenshot Saved Successfully: " + filePath);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
        }
    }
}

