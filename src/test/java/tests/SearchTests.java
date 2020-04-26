package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SearchTests extends CoreTestCase {
    @Test
    void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    void cancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults();
        searchPageObject.waitForCancelBtnToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelBtnToDisappear();
    }


    @Test
    void testSearchTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForSearchInputToHaveText("Search Wikipedia");
    }


    @Test
    void testSearchResults() {
        String searchText = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);

        //Затем убеждается, что в каждом результате поиска есть это слово.
        List<WebElement> searchResults = searchPageObject.getSearchResults();

        for (WebElement searchResult : searchResults) {
            String actualText = searchResult.getAttribute("text");
            assertThat(actualText, is(containsString(searchText)));
        }

    }


    @Test
    void testAssertTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult("Appium");

        new ArticlePageObject(driver).assertTitle("Appium");
    }

}
