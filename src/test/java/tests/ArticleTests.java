package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTests extends CoreTestCase {
    @Test
    void compareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickSearchResult("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String expectedTitle = "Java (programming language)";
        String actualTitle = articlePageObject.getArticleTitle(expectedTitle);

        assertEquals(expectedTitle, actualTitle, "Unexpected title");
    }

    @Test
    void testSwipeArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement("Appium");
        articlePageObject.swipeToFooter();
    }

}
