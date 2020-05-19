package lib.ui.ios;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

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
    public IOSWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
