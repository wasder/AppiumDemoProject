package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            CLOSE_ONBOARDING,
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            READING_LISTS_TAB,
            SWIPE_ACTION_DELETE;

    protected static String getFolderByNameTpl(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    protected static String getArticleByTitleTpl(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void closeOnboarding() {
        this.waitForElementAndClick(CLOSE_ONBOARDING, 5);
    }

    public void openFolderByName(String folderName) {
        this.waitForElementAndClick(getFolderByNameTpl(folderName), 5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppear(articleTitle);
        this.swipeElementToLeft(getArticleByTitleTpl(articleTitle));
        this.waitForArticleToDisappear(articleTitle);
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
