package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyListsTests extends CoreTestCase {
    private static final String login = "wasdeg";
    private static final String pass = "11111178";

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

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isMW()) {
            navigationUI.openNavigation();
            navigationUI.clickLogin();
            AuthPageObject authPageObject = new AuthPageObject(driver);
            authPageObject.enterLoginData(login, pass);
            authPageObject.submitForm();
            articlePageObject.addArticleToMyList(folderName);
            navigationUI.openNavigation();
        } else {
            articlePageObject.addArticleToMyNewList(folderName, true);
        }
        articlePageObject.closeArticle();

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

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isMW()) {
            navigationUI.openNavigation();
            navigationUI.clickLogin();
            AuthPageObject authPageObject = new AuthPageObject(driver);
            authPageObject.enterLoginData(login, pass);
            authPageObject.submitForm();
            articlePageObject.addArticleToMyList(folderName);
        } else {
            articlePageObject.addArticleToMyNewList(folderName, true);
        }
        articlePageObject.closeArticle();

        //Search and save second
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(selenium);
        searchPageObject.clickSearchResult("Testing framework for web applications");

        articlePageObject.waitForTitleElement(seleniumTitle);
        String secondTitleBefore = articlePageObject.getArticleTitle(seleniumTitle);
        articlePageObject.addArticleToMyList(folderName);
        articlePageObject.closeArticle();

        if (Platform.getInstance().isMW()){
            navigationUI.openNavigation();
        }
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.closeOnboarding();
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(appium);
        myListsPageObject.clickArticleByName(seleniumTitle);

        String secondTitleAfter = articlePageObject.getArticleTitle(seleniumTitle);

        assertEquals(secondTitleBefore, secondTitleAfter, "Title не совпадает");
    }

}
