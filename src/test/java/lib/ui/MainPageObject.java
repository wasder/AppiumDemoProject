package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainPageObject {
    protected AppiumDriver driver;

    private static final String
            ONBOARDING_SKIP_BTN = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find element " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresent(String locator, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find elements " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }


    public WebElement waitForElementPresent(String locator) {
        return waitForElementPresent(locator, 5);
    }

    public WebElement waitForElementAndClick(String locator, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(locator, timeoutInSeconds);
        webElement.click();
        return webElement;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(locator, timeoutInSeconds);
        webElement.sendKeys(value);
        return webElement;
    }

    public boolean waitForElementNotPresent(String locator, String errorMsg, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(String locator, long timeoutInSeconds) {
        String errorMsg = "Element " + locator + " is still present";
        return waitForElementNotPresent(locator, errorMsg, timeoutInSeconds);
    }

    public WebElement waitForElementAndClear(String locator, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(locator, timeoutInSeconds);
        webElement.clear();
        return webElement;
    }

    public WebElement waitForElementAndCheckText(String locator, String expectedText, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(locator, timeoutInSeconds);
        String actualText = webElement.getAttribute("text");
        assertEquals(
                expectedText,
                actualText,
                "Unexpected text");
        return webElement;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(point(x, startY))
                .waitAction(waitOptions(ofSeconds(timeOfSwipe)))
                .moveTo(point(x, endY))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(1);
    }

    public void swipeUpToFindElement(String locator, int maxSwipes) {
        int swipesCounter = 0;
        By by = getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            if (swipesCounter > maxSwipes) {
                waitForElementPresent(locator, 0);
                return;
            }
            swipeUpQuick();
            ++swipesCounter;
        }
    }

    public void swipeElementToLeft(String locator) {
        WebElement webElement = waitForElementPresent(locator, 10);
        int leftX = webElement.getLocation().getX();
        int rightX = leftX + webElement.getSize().getWidth() - 1;
        int upperY = webElement.getLocation().getY();
        int lowerY = upperY + webElement.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(point(rightX, middleY))
                .waitAction(waitOptions(ofSeconds(1)))
                .moveTo(point(leftX, middleY))
                .release()
                .perform();
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, long timeout) {
        WebElement webElement = waitForElementPresent(locator, timeout);
        return webElement.getAttribute(attribute);
    }

    public String waitForElementAndGetTagName(String locator, long timeout) {
        WebElement webElement = waitForElementPresent(locator, timeout);
        return webElement.getTagName();
    }

    public void assertElementPresent(String locator) {
        By by = getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        assertNotNull(element);
    }

    public void skipOnboarding() {
        this.waitForElementAndClick(ONBOARDING_SKIP_BTN, 5);
    }

    private By getLocatorByString(String locatorWithType){
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"),2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        switch (byType) {
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "accessibilityId":
                return MobileBy.AccessibilityId(locator);
            default:
                throw new IllegalArgumentException("Can't get locator type: " + locatorWithType);
        }
    }
}
