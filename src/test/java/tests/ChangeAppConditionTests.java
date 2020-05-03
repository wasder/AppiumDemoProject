package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    void testScreenOrientation() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation =  articlePageObject.getArticleTitle("Appium");
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle("Appium");
        assertEquals(titleBeforeRotation, titleAfterRotation, "Title changed");
    }
}
