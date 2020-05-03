package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class IOSWelcomePageObject extends WelcomePageObject {
    static {
        SKIP_BTN = "accessibilityId:Skip";
        LEARN_MORE = "accessibilityId:Learn more about Wikipedia";
        NEXT_BTN = "accessibilityId:Next";
        WAIT_FOR_NEW = "accessibilityId:New ways to explore";
        ADD_OR_EDIT = "accessibilityId:Add or edit preferred languages";
        LEARN_MORE_ABOUT_DATA = "accessibilityId:Learn more about data collected";
        GET_STARTED_BTN = "accessibilityId:Get started";
    }
    public IOSWelcomePageObject(AppiumDriver driver) {
        super(driver);
    }
}
