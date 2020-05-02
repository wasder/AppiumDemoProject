package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.jupiter.api.Test;

public class GetStartedTest extends iOSTestCase {
    @Test
    void testPassThoughWelcome() {
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWaysText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutData();
        welcomePageObject.clickGetStarted();
    }
}
