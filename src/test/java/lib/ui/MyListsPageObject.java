package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            CLOSE_ONBOARDING,
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            READING_LISTS_TAB,
            SWIPE_ACTION_DELETE,
            REMOVE_FROM_SAVED_BTN;

    protected static String getFolderByNameTpl(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    protected static String getArticleByTitleTpl(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    protected static String getRemoveLocator(String articleTitle) {
        return REMOVE_FROM_SAVED_BTN.replace("{ARTICLE_TITLE}", articleTitle);
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void closeOnboarding() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(CLOSE_ONBOARDING, 5);
        }
    }

    public void openFolderByName(String folderName) {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(getFolderByNameTpl(folderName), 5);
        }
    }

    public void swipeByArticleToDelete(String articleTitle) {
        if (Platform.getInstance().isMW()) {
            this.waitForArticleToAppear(articleTitle);
            String removeLocator = getRemoveLocator(articleTitle);
            this.waitForElementAndClick(removeLocator, 5);
            driver.navigate().refresh();
            this.waitForArticleToDisappear(articleTitle);
        } else {
            this.waitForArticleToAppear(articleTitle);
            this.swipeElementToLeft(getArticleByTitleTpl(articleTitle));
            this.waitForArticleToDisappear(articleTitle);
        }

    }

    public void waitForArticleToAppear(String articleTitle) {
        this.waitForElementPresent(getArticleByTitleTpl(articleTitle), 10);
    }

    public void waitForArticleToDisappear(String articleTitle) {
        this.waitForElementNotPresent(getArticleByTitleTpl(articleTitle), 10);
    }

    public void clickArticleByName(String articleTitle) {
        this.waitForElementAndClick(getArticleByTitleTpl(articleTitle), 10);
    }


}
