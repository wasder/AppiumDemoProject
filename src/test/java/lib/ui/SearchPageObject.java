package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.view.ViewGroup//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
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


}
