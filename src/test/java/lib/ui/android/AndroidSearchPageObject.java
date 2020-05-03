package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.view.ViewGroup//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
    }


    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
