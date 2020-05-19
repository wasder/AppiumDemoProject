package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_TPL = "xpath://android.view.View[@content-desc=\"{SUBSTRING}\"]";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        ARTICLE_BOOKMARK = "id:org.wikipedia:id/article_menu_bookmark";
        ONBOARDING_BTN = "id:org.wikipedia:id/onboarding_button";
        CREATE_BTN = "id:org.wikipedia:id/create_button";
        ARTICLE_TEXT_INPUT = "id:org.wikipedia:id/text_input";
        OK_BTN = "id:android:id/button1";
        CLOSE_ARTICLE_BTN = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
        BTN_2 = "id:android:id/button2";
        LIST_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_TITLE}']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
