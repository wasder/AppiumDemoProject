package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION,
            LOGIN_BTN;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementsWithFewAttempts(MY_LISTS_LINK, 5);
        } else {
            this.waitForElementAndClick(MY_LISTS_LINK, 5);

        }

    }

    public void clickLogin() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementsWithFewAttempts(LOGIN_BTN, 10);
        }
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, 5);
        } else {
            System.out.println("Method openNavigation() doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }

}
