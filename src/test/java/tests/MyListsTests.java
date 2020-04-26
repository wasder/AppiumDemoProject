package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyListsTests extends CoreTestCase {

    @Test
    void saveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickSearchResult("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        String articleTitle = articlePageObject.getArticleTitle("Java (programming language)");
        String folderName = "Learning appium";
        articlePageObject.addArticleToMyNewList(folderName, true);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.closeOnboarding();
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    void saveTwoArticlesToMyList() {
        String appium = "Appium";
        String java = "Java";
        String javaTitle = "Java (programming language)";
        String folderName = "Learning appium";

        //Search and save first
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickSearchResult(appium);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement(appium);
        articlePageObject.addArticleToMyNewList(folderName, true);
        articlePageObject.closeArticle();

        //Search and save second
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(java);
        searchPageObject.clickSearchResult("Object-oriented programming language");

        articlePageObject.waitForTitleElement(javaTitle);
        String javaTitleBefore = articlePageObject.getArticleTitle(javaTitle);
        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.closeOnboarding();
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(appium);
        myListsPageObject.clickArticleByName(javaTitle);

        String javaTitleAfter = articlePageObject.getArticleTitle(javaTitle);

        assertEquals(javaTitleBefore, javaTitleAfter, "Title не совпадает");
    }

}
