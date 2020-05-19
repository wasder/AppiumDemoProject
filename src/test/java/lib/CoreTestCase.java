package lib;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipOnboarding();
        this.openWikiWebPageForMW();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotate doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotate doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMW() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method openWikiWebPageForMW doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void skipOnboarding() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
            welcomePageObject.clickSkipButton();
        } else {
            System.out.println("Method skipOnboarding() doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }
}
