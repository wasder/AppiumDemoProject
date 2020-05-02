package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_TPL = "xpath://android.view.View[@content-desc=\"{SUBSTRING}\"]",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            ARTICLE_BOOKMARK = "id:org.wikipedia:id/article_menu_bookmark",
            ONBOARDING_BTN = "id:org.wikipedia:id/onboarding_button",
            CREATE_BTN = "id:org.wikipedia:id/create_button",
            ARTICLE_TEXT_INPUT = "id:org.wikipedia:id/text_input",
            OK_BTN = "id:android:id/button1",
            CLOSE_ARTICLE_BTN = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            BTN_2 = "id:android:id/button2",
            LIST_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_TITLE}']";

    private static String getTitleElement(String substring) {
        return TITLE_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getListTitleTpl(String substring) {
        return LIST_TITLE_TPL.replace("{LIST_TITLE}", substring);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(String substring) {
        String title = getTitleElement(substring);
        return this.waitForElementPresent(title);
    }

    public String getArticleTitle(String substring) {
        WebElement element = waitForTitleElement(substring);
        return element.getAttribute("content-desc");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, 20);
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
