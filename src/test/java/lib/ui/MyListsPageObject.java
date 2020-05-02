package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final String
            CLOSE_ONBOARDING = "id:org.wikipedia:id/view_onboarding_action_negative",
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{ARTICLE_TITLE}']";

    private static String getFolderByNameTpl(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getArticleByTitleTpl(String articleTitle) {
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
