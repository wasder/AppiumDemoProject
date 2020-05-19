package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:#searchIcon";
        SEARCH_INPUT = "css:form>input[type=search]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://li[contains(@class, 'page-summary')]//*[contains(text(), '{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "css:button.clear";
        SEARCH_RESULTS = "css:ul.page-list > li.page-summary";
    }


    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
