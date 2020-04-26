package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.view.ViewGroup//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_CANCEL_BTN = "org.wikipedia:id/search_close_btn",
    //            SEARCH_RESULTS = "//*[contains(@resource-id, 'org.wikipedia:id/search_results_list')]/android.view.ViewGroup",
    SEARCH_RESULTS = "org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), 5);
    }

    public void waitForCancelBtnToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BTN), 5);
    }

    public void waitForCancelBtnToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BTN), 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BTN), 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), searchLine, 5);
    }

    public void waitForSearchResults() {
        this.waitForElementsPresent(By.id(SEARCH_RESULTS), 15);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), 15);
    }

    public void clickSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), 15);
    }

    public void waitForSearchInputToHaveText(String text) {
        this.waitForElementAndCheckText(By.id(SEARCH_INPUT), text, 5);
    }

    public List<WebElement> getSearchResults() {
        return this.waitForElementsPresent(By.id(SEARCH_RESULTS), 15);
    }


}
