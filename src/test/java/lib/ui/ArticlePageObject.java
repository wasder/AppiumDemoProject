package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE_TPL,
            FOOTER_ELEMENT,
            ARTICLE_BOOKMARK,
            ONBOARDING_BTN,
            CREATE_BTN,
            ARTICLE_TEXT_INPUT,
            OK_BTN,
            CLOSE_ARTICLE_BTN,
            BTN_2,
            LIST_TITLE_TPL,
            CREATE_NEW_LIST;

    protected static String getTitleElement(String substring) {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }

    protected static String getListTitleTpl(String substring) {
        return LIST_TITLE_TPL.replace("{LIST_TITLE}", substring);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(String substring) {
        String title = getTitleElement(substring);
        return this.waitForElementPresent(title, 15);
    }

    public String getArticleTitle(String substring) {
        WebElement element = waitForTitleElement(substring);
        if (Platform.getInstance().isAndroid()){
            return element.getAttribute("content-desc");
        } else {
            return element.getAttribute("label");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, 20);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, 20);
        }
    }

    public void addArticleToMyNewList(String folderName, Boolean onboarding) {
        this.waitForElementAndClick(ARTICLE_BOOKMARK, 5);
        if (onboarding) {
            this.waitForElementAndClick(ONBOARDING_BTN, 5);
        }
        this.waitForElementAndClick(CREATE_BTN, 5);
        this.waitForElementAndSendKeys(ARTICLE_TEXT_INPUT, folderName, 5);
        this.waitForElementAndClick(OK_BTN, 5);
    }

    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(ARTICLE_BOOKMARK, 5);
        this.waitForElementAndClick(getListTitleTpl(folderName), 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BTN, 5);
        this.waitForElementAndClick(BTN_2, 5);
    }

    public void assertTitle(String title) {
        this.assertElementPresent(getTitleElement(title));
    }
}
