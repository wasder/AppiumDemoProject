package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class WelcomePageObject extends MainPageObject {

    protected static String SKIP_BTN;
    protected static String LEARN_MORE;
    protected static String NEXT_BTN;
    protected static String WAIT_FOR_NEW;
    protected static String ADD_OR_EDIT;
    protected static String LEARN_MORE_ABOUT_DATA;
    protected static String GET_STARTED_BTN;

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(LEARN_MORE);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BTN, 10);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BTN, 10);
    }

    public void waitForNewWaysText() {
        this.waitForElementPresent(WAIT_FOR_NEW);
    }

    public void waitForAddOrEditLink() {
        this.waitForElementPresent(ADD_OR_EDIT);
    }

    public void waitForLearnMoreAboutData() {
        this.waitForElementPresent(LEARN_MORE_ABOUT_DATA);
    }

    public void clickGetStarted() {
        this.waitForElementAndClick(GET_STARTED_BTN, 10);
    }
}
