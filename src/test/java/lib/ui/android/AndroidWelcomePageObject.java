package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject {
    static {
        SKIP_BTN = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }

    public AndroidWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
