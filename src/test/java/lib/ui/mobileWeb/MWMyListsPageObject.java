package lib.ui.mobileWeb;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@title, '{ARTICLE_TITLE}')]";
        REMOVE_FROM_SAVED_BTN = "xpath://li[contains(@title, '{ARTICLE_TITLE}')]//a[contains(@class, 'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
