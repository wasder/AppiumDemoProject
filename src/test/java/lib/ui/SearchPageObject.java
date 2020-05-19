package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BTN,
            SEARCH_RESULTS;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, 5);
    }

    public void waitForCancelBtnToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BTN, 5);
    }

    public void waitForCancelBtnToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BTN, 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BTN, 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, 5);
    }

    public void waitForSearchResults() {
        this.waitForElementsPresent(SEARCH_RESULTS, 15);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, 15);
    }

    public void clickSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, 15);
    }

    public void waitForSearchInputToHaveText(String text) {
        this.waitForElementAndCheckText(SEARCH_INPUT, text, 5);
    }

    public List<WebElement> getSearchResults() {
        return this.waitForElementsPresent(SEARCH_RESULTS, 15);
    }

    public void checkResults(String searchText){
        //Затем убеждается, что в каждом результате поиска есть это слово.
        List<WebElement> searchResults = this.getSearchResults();

        for (WebElement searchResult : searchResults) {
            String actualText = null;
            if (Platform.getInstance().isAndroid()){
                actualText =  searchResult.getAttribute("text");
            } else if (Platform.getInstance().isIOS()){
                actualText = searchResult.getAttribute("value");
            }else if (Platform.getInstance().isMW()){
                actualText = searchResult.getText();
            }
            assertThat(actualText, is(containsString(searchText)));
        }
    }
}
