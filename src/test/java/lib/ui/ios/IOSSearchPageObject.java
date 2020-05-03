package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "accessibilityId:Search Wikipedia";
        SEARCH_INPUT = "accessibilityId:Search Wikipedia";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "accessibilityId:Cancel";
        SEARCH_RESULTS = "xpath://XCUIElementTypeCell";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}

//XCUIElementTypeStaticText[@name="Java (programming language)"]