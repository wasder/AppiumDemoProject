package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyListsTests extends CoreTestCase {

    @Test
    void saveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement("Appium");
        String articleTitle = articlePageObject.getArticleTitle("Appium");
        String folderName = "Learning appium";
        articlePageObject.addArticleToMyNewList(folderName, true);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.closeOnboarding();
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    void saveTwoArticlesToMyList() {
        String appium = "Appium";
        String selenium = "selenium";
        String seleniumTitle = "Selenium (software)";
        String folderName = "Learning appium";

        //Search and save first
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult(appium);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement(appium);
        articlePageObject.addArticleToMyNewList(folderName, true);
        articlePageObject.closeArticle();

        //Search and save second
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(selenium);
        searchPageObject.clickSearchResult("Testing framework for web applications");

        articlePageObject.waitForTitleElement(seleniumTitle);
        String javaTitleBefore = articlePageObject.getArticleTitle(seleniumTitle);
        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.closeOnboarding();
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(appium);
        myListsPageObject.clickArticleByName(seleniumTitle);

        String javaTitleAfter = articlePageObject.getArticleTitle(seleniumTitle);

        assertEquals(javaTitleBefore, javaTitleAfter, "Title не совпадает");
    }

}
