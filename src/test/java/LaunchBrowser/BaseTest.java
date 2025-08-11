package LaunchBrowser;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String browserName = "chromium";

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
        }
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterTest
    public void tearDown() {
        try {
            if (context != null) context.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        } catch (Exception e) {
            System.err.println("Erreur lors du tearDown: " + e.getMessage());
        }
    }
}
