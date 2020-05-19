package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_TPL = "css:#content h1";
        FOOTER_ELEMENT = "css:footer.mw-footer";
        ARTICLE_BOOKMARK = "xpath://*[@id=\"ca-watch\"]";
        CREATE_BTN = "xpath://XCUIElementTypeStaticText[contains(@name, 'to a reading list?')]";
        OPTIONS_REMOVE_FROM_LIST_BTN = "css:#ca-watch.watched";
    }


    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
