package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE_TPL = "xpath://XCUIElementTypeOther[@name='{SUBSTRING}']";
        FOOTER_ELEMENT = "accessibilityId:View article in browser";
        ARTICLE_BOOKMARK = "accessibilityId:Save for later";
        CREATE_BTN = "xpath://XCUIElementTypeStaticText[contains(@name, 'to a reading list?')]";
        ARTICLE_TEXT_INPUT = "accessibilityId:reading list title";
        OK_BTN = "accessibilityId:Create reading list";
        CLOSE_ARTICLE_BTN = "accessibilityId:Wikipedia, return to Explore";
        LIST_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{LIST_TITLE}']";
        CREATE_NEW_LIST = "accessibilityId:Create a new list";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void addArticleToMyNewList(String folderName, Boolean onboarding) {
        this.waitForElementAndClick(ARTICLE_BOOKMARK, 10);
        this.waitForElementAndClick(CREATE_BTN, 15);
        this.waitForElementAndClick(CREATE_NEW_LIST, 10);
        this.waitForElementAndSendKeys(ARTICLE_TEXT_INPUT, folderName, 10);
        this.waitForElementAndClick(OK_BTN, 10);
    }

    @Override
    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(ARTICLE_BOOKMARK, 5);
        this.waitForElementAndClick(CREATE_BTN, 15);
        this.waitForElementAndClick(getListTitleTpl(folderName), 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BTN, 5);
    }

}
