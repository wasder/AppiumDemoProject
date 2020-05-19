package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
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
        String actualText = null;
        if (Platform.getInstance().isAndroid()) {
            actualText = webElement.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            actualText = webElement.getAttribute("value");
        } else if (Platform.getInstance().isMW()) {
            actualText = webElement.getAttribute("placeholder");
        }
        assertEquals(
                expectedText,
                actualText,
                "Unexpected text");
        return webElement;
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
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
        } else {
            System.out.println("Method swipeUp doesn't support for " + Platform.getInstance().getPlatformVar());
        }
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

    public void swipeUpTillElementAppear(String locator, int maxSwipes) {
        int swipesCounter = 0;
        while (!this.isElementLocatedOnScreen(locator)) {
            if (swipesCounter > maxSwipes) {
                assertTrue(isElementLocatedOnScreen(locator), "Element is not located on screen");
            }
            swipeUpQuick();
            ++swipesCounter;
        }
    }

    public boolean isElementLocatedOnScreen(String locator) {
        if (Platform.getInstance().isMW()) {
            int elementLocationY = this.waitForElementPresent(locator, 10).getLocation().getY();
            int screenSizeByY = driver.manage().window().getSize().getHeight();
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Object jsResult = javascriptExecutor.executeScript("return window.pageYOffset");
            elementLocationY -= Integer.parseInt(jsResult.toString());
            return elementLocationY < screenSizeByY;
        } else
            return Boolean.parseBoolean(this.waitForElementAndGetAttribute(locator, "visible", 10));
    }


    public void swipeElementToLeft(String locator) {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
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
        } else {
            System.out.println("Method swipeElementToLeft doesn't support for " + Platform.getInstance().getPlatformVar());
        }


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

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        switch (byType) {
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "accessibilityId":
                return MobileBy.AccessibilityId(locator);
            case "css":
                return By.cssSelector(locator);
            default:
                throw new IllegalArgumentException("Can't get locator type: " + locatorWithType);
        }
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeAsyncScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp doesn't support for " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, int maxSwipes) {
        int swipesCounter = 0;
        while (!this.isElementLocatedOnScreen(locator)) {
            if (swipesCounter > maxSwipes) {
                assertTrue(isElementLocatedOnScreen(locator), "Element is not located on screen");
            }
            scrollWebPageUp();
            ++swipesCounter;
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementsWithFewAttempts(String locator, int amount) {
        int attempts = 0;
        boolean needMore = true;

        while(needMore){
            try {
                this.waitForElementAndClick(locator, 2);
                needMore = false;
            } catch (Exception e){
                if (attempts > amount){
                    this.waitForElementAndClick(locator,2);
                }
            }
            ++attempts;
        }
    }

}


