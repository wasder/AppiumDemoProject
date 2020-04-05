import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MainClassTest {

    private AppiumDriver driver;

    @BeforeEach
    void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Users\\ivang\\IdeaProjects\\AppiumDemoProject\\apks\\Wikipedia+2.7.50278-r-2019-12-12.apk");
        desiredCapabilities.setCapability("noReset", "true");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                5);
        waitForElementPresent(
                By.xpath( "//android.view.ViewGroup//*[contains(@text, 'Object')]"),
                15
        );
    }

    @Test
    public void cancelSearch() {
        //Ищет какое-то слово
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                5);

        //Убеждается, что найдено несколько статей
        List<WebElement> searchResults = waitForElementsPresent(
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/search_results_list')]/android.view.ViewGroup"),
                15);
        assertTrue(searchResults.size() > 0, "Search results are empty"); //Не обязательная проверка, потому что если результатов не будет, то упадёт раньше, на поиске элементов.

        //Отменяет поиск
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                5
        );

        //Убеждается, что результат поиска пропал
        waitForElementNotPresent(
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/search_results_list')]/android.view.ViewGroup"),
                "X is still present",
                5
        );

    }

    @Test
    void compareArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                5);

        waitForElementAndClick(
                By.xpath( "//android.view.ViewGroup//*[contains(@text, 'Object-oriented')]"),
                5);

        WebElement title = waitForElementPresent(
                MobileBy.AccessibilityId("Java (programming language)"),
                20
        );

        String articleTitle = title.getAttribute("text");
        assertEquals(
                "Java (programming language)",
                articleTitle,
                "Unexpected title");
    }


    @Test
    void testSearchTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );

        waitForElementAndCheckText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                5
        );
    }

    private WebElement waitForElementPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find element " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private List<WebElement> waitForElementsPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find elements " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }


    private WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by, 5);
    }

    private WebElement waitForElementAndClick(By by, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.click();
        return webElement;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.sendKeys(value);
        return webElement;
    }

    private boolean waitForElementNotPresent(By by, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClear(By by, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.clear();
        return webElement;
    }

    private WebElement waitForElementAndCheckText(By by, String expectedText, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        String actualText = webElement.getAttribute("text");
        assertEquals(
                expectedText,
                actualText,
                "Unexpected text");
        return webElement;
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
