package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_TPL = "//android.view.View[@content-desc=\"{SUBSTRING}\"]",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            ARTICLE_BOOKMARK = "org.wikipedia:id/article_menu_bookmark",
            ONBOARDING_BTN = "org.wikipedia:id/onboarding_button",
            CREATE_BTN = "org.wikipedia:id/create_button",
            ARTICLE_TEXT_INPUT = "org.wikipedia:id/text_input",
            OK_BTN = "android:id/button1",
            CLOSE_ARTICLE_BTN = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            BTN_2 = "android:id/button2",
            LIST_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_TITLE}']"
    ;

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
        return this.waitForElementPresent(MobileBy.xpath(title));
    }

    public String getArticleTitle(String substring) {
        WebElement element = waitForTitleElement(substring);
        return element.getAttribute("content-desc");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), 20);
    }

    public void addArticleToMyNewList(String folderName, Boolean onboarding) {
        this.waitForElementAndClick(By.id(ARTICLE_BOOKMARK), 5);
        if (onboarding){
            this.waitForElementAndClick(By.id(ONBOARDING_BTN), 5);
        }
        this.waitForElementAndClick(By.id(CREATE_BTN), 5);
        this.waitForElementAndSendKeys(By.id(ARTICLE_TEXT_INPUT), folderName, 5);
        this.waitForElementAndClick(By.id(OK_BTN), 5);
    }
    public void addArticleToMyList(String folderName) {
        this.waitForElementAndClick(By.id(ARTICLE_BOOKMARK), 5);
        this.waitForElementAndClick(By.xpath(getListTitleTpl(folderName)), 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BTN), 5);
        this.waitForElementAndClick(By.id(BTN_2), 5);
    }

    public void assertTitle(String title ){
        this.assertElementPresent(By.xpath(getTitleElement(title)));
    }
}
