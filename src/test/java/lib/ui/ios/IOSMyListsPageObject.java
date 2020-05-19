package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        CLOSE_ONBOARDING = "accessibilityId:Close";
        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeStaticText[@name='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{ARTICLE_TITLE}']";
        READING_LISTS_TAB = "accessibilityId:Reading lists";
        SWIPE_ACTION_DELETE = "accessibilityId:swipe action delete";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public void openFolderByName(String folderName) {
        this.waitForElementAndClick(READING_LISTS_TAB,10);
        this.waitForElementAndClick(getFolderByNameTpl(folderName), 5);
    }

    @Override
    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppear(articleTitle);
        this.swipeElementToLeft(getArticleByTitleTpl(articleTitle));
        this.waitForElementAndClick(SWIPE_ACTION_DELETE, 10);
        this.waitForArticleToDisappear(articleTitle);
    }
}
