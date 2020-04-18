import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


class MainClassTest {

    private AppiumDriver driver;

    @BeforeEach
    void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Users\\ivang\\IdeaProjects\\AppiumDemoProject\\apks\\Wikipedia+2.7.50278-r-2019-12-12.apk");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability("clearSystemFiles", "true");
        desiredCapabilities.setCapability("udid","emulator-5556");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        waitForElementAndClick(
                By.id( "org.wikipedia:id/fragment_onboarding_skip_button"),
                5);
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


    @Test
    public void testSearchResults() {
        String searchText = "Java";
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchText,
                5);

        //Затем убеждается, что в каждом результате поиска есть это слово.
        List<WebElement> searchResults = waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                15);

        for (WebElement searchResult : searchResults) {
            String actualText = searchResult.getAttribute("text");
            assertThat(actualText, is(containsString(searchText)));
        }

    }

    @Test
    void testSwipeArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                5);

        waitForElementAndClick(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                5);

        WebElement title = waitForElementPresent(
                MobileBy.AccessibilityId("Appium"),
                20
        );
        swipeUoToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                10);
    }

    @Test
    void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                5);
        waitForElementAndClick(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                5);
        waitForElementPresent(
                MobileBy.AccessibilityId("Appium"),
                20
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/create_button"),
                5);
        String folderName = "Learning appium";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folderName,
                5);
        waitForElementAndClick(
                By.id("android:id/button1"),
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                5);
        waitForElementAndClick(
                By.id("android:id/button2"),
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/view_onboarding_action_negative"),
                5);
        waitForElementAndClick(
                By.xpath( "//*[@text='"+ folderName +"']"),
                5);
        swipeElementToLeft(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']")
        );
        waitForElementNotPresent(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                10);
    }

    @Test
    void saveTwoArticlesToMyList() {
        //Search and save first
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/create_button"),
                5);
        String folderName = "Learning appium";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folderName,
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                5);
        waitForElementAndClick(
                By.id("android:id/button2"),
                5);

        //Search and save second
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
        String javaTitle = waitForElementAndGetAttribute(
                MobileBy.xpath("//android.view.View[@content-desc=\"Java (programming language)\"]"),
                "content-desc",
                20
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                5);
        waitForElementAndClick(
                By.xpath( "//*[@text='"+ folderName +"']"),
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                5);
        waitForElementAndClick(
                By.id("android:id/button2"),
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"),
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/view_onboarding_action_negative"),
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='"+ folderName +"']"),
                5);
        String appiumInListXpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']/..";
        swipeElementToLeft(
                By.xpath(appiumInListXpath)
        );
        waitForElementNotPresent(
                By.xpath(appiumInListXpath),
                10);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']/.."),
                10);
        String javaTitleAfter = waitForElementAndGetTagName(
                MobileBy.AccessibilityId("Java (programming language)"),
                20
        );

        assertEquals(javaTitle, javaTitleAfter,"Title не совпадает");
    }

    @Test
    void testAssertTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                5);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                5);
        waitForElementAndClick(
                By.xpath( "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                5);
        assertElementPresent(By.xpath("//android.view.View[@content-desc=\"Appium\"]"));
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
    private boolean waitForElementNotPresent(By by, long timeoutInSeconds) {
        String errorMsg = "Element " + by.toString() + " is still present";
        return waitForElementNotPresent(by,  errorMsg , timeoutInSeconds);
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

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(point(x, startY))
                .waitAction(waitOptions(ofSeconds(timeOfSwipe)))
                .moveTo(point(x, startY))
                .release()
                .perform();

    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUoToFindElement(By by, int maxSwipes){
        int swipesCounter = 0;
        while(driver.findElements(by).size() == 0){
            if (swipesCounter>maxSwipes){
                waitForElementPresent(by, 0);
                return;
            }
            swipeUpQuick();
            ++swipesCounter;
        }
    }

    protected void swipeElementToLeft(By by){
        WebElement webElement =  waitForElementPresent(by,10);
        int leftX = webElement.getLocation().getX();
        int rightX = leftX + webElement.getSize().getWidth() - 1;
        int upperY = webElement.getLocation().getY();
        int lowerY = upperY + webElement.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(point(rightX, middleY))
                .waitAction(waitOptions(ofSeconds(3)))
                .moveTo(point(leftX, middleY))
                .release()
                .perform();
    }

    protected String waitForElementAndGetAttribute(By by, String attribute, long timeout){
        WebElement webElement = waitForElementPresent(by, timeout);
        return webElement.getAttribute(attribute);
    }
    protected String waitForElementAndGetTagName(By by, long timeout){
        WebElement webElement = waitForElementPresent(by, timeout);
        return webElement.getTagName();
    }

    private void assertElementPresent(By by){
        WebElement element = driver.findElement(by);
        assertNotNull(element);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
