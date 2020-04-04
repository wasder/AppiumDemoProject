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

import static org.junit.jupiter.api.Assertions.assertEquals;


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
                "Cannot find search input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5);
        waitForElementPresent(
                By.xpath( "//android.view.ViewGroup//*[contains(@text, 'Object')]"),
                "Cannot find '//android.view.ViewGroup//*[contains(@text, 'Object')]'",
                15
        );
    }

    @Test
    public void cancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find org.wikipedia:id/search_container",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search input",
                5);


        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present",
                5
        );

    }

    @Test
    void compareArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find org.wikipedia:id/search_container",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath( "//android.view.ViewGroup//*[contains(@text, 'Object-oriented')]"),
                "Cannot find search input",
                5);

        WebElement title = waitForElementPresent(
                MobileBy.AccessibilityId("Java (programming language)"),
                "Cannot find title",
                20
        );

        String articleTitle = title.getAttribute("text");
        assertEquals(
                "Java (programming language)",
                articleTitle,
                "Unexpected title");
    }

    private WebElement waitForElementPresent(By by, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMsg) {
        return waitForElementPresent(by, errorMsg, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMsg, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, errorMsg, timeoutInSeconds);
        webElement.click();
        return webElement;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String errorMsg, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, errorMsg, timeoutInSeconds);
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

    private WebElement waitForElementAndClear(By by, String errorMsg, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, errorMsg, timeoutInSeconds);
        webElement.clear();
        return webElement;
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
