package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainPageObject {
    protected AppiumDriver driver;

    private static final String
            ONBOARDING_SKIP_BTN = "org.wikipedia:id/fragment_onboarding_skip_button";

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find element " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Can't find elements " + by.toString());
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }


    public WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by, 5);
    }

    public WebElement waitForElementAndClick(By by, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.click();
        return webElement;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.sendKeys(value);
        return webElement;
    }

    public boolean waitForElementNotPresent(By by, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, long timeoutInSeconds) {
        String errorMsg = "Element " + by.toString() + " is still present";
        return waitForElementNotPresent(by, errorMsg, timeoutInSeconds);
    }

    public WebElement waitForElementAndClear(By by, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
        webElement.clear();
        return webElement;
    }

    public WebElement waitForElementAndCheckText(By by, String expectedText, long timeoutInSeconds) {
        WebElement webElement = waitForElementPresent(by, timeoutInSeconds);
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

    public void swipeUpToFindElement(By by, int maxSwipes) {
        int swipesCounter = 0;
        while (driver.findElements(by).size() == 0) {
            if (swipesCounter > maxSwipes) {
                waitForElementPresent(by, 0);
                return;
            }
            swipeUpQuick();
            ++swipesCounter;
        }
    }

    public void swipeElementToLeft(By by) {
        WebElement webElement = waitForElementPresent(by, 10);
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

    public String waitForElementAndGetAttribute(By by, String attribute, long timeout) {
        WebElement webElement = waitForElementPresent(by, timeout);
        return webElement.getAttribute(attribute);
    }

    public String waitForElementAndGetTagName(By by, long timeout) {
        WebElement webElement = waitForElementPresent(by, timeout);
        return webElement.getTagName();
    }

    public void assertElementPresent(By by) {
        WebElement element = driver.findElement(by);
        assertNotNull(element);
    }

    public void skipOnboarding() {
        this.waitForElementAndClick(By.id(ONBOARDING_SKIP_BTN), 5);
    }
}
