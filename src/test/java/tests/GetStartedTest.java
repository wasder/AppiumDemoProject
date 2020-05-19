package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestSkippedException;

public class GetStartedTest extends CoreTestCase {
    @Test
    void testPassThoughWelcome() {
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMW()){
            throw new TestSkippedException();
        }
        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);

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
