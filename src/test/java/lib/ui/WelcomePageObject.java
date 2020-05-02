package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String LEARN_MORE = "accessibilityId:Learn more about Wikipedia";
    private static final String NEXT_BTN = "accessibilityId:Next";
    private static final String WAIT_FOR_NEW = "accessibilityId:New ways to explore";
    private static final String ADD_OR_EDIT = "accessibilityId:Add or edit preferred languages";
    private static final String LEARN_MORE_ABOUT_DATA = "accessibilityId:Learn more about data collected";
    private static final String GET_STARTED_BTN = "accessibilityId:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(LEARN_MORE);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BTN, 10);
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
