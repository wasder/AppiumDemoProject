package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.ui.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    protected AppiumDriver driver;
    private static String appiumURL = "http://localhost:4723/wd/hub";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = this.getCapabilitiesByPlatformEnv();

        driver = getMobileDriver(desiredCapabilities);

        this.rotateScreenPortrait();
        //Skip onboarding on clean start
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.skipOnboarding();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception{
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (platform) {
            case PLATFORM_ANDROID:
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
                desiredCapabilities.setCapability("platformVersion", "8.0");
                desiredCapabilities.setCapability("automationName", "UiAutomator2");
                desiredCapabilities.setCapability("appPackage", "org.wikipedia");
                desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
                desiredCapabilities.setCapability("app", "C:\\Users\\ivang\\IdeaProjects\\AppiumDemoProject\\apks\\Wikipedia+2.7.50278-r-2019-12-12.apk");
                desiredCapabilities.setCapability("noReset", "false");
                desiredCapabilities.setCapability("clearSystemFiles", "true");
                desiredCapabilities.setCapability("udid", "emulator-5556");
                break;
            case PLATFORM_IOS:
                desiredCapabilities.setCapability("platformName", "iOS");
                desiredCapabilities.setCapability("deviceName", "iPhone 8");
                desiredCapabilities.setCapability("automationName", "XCUITest");
                desiredCapabilities.setCapability("app", "/Users/digital_qa/IdeaProjects/AppiumDemoProject/apks/Wikipedia.app");
                desiredCapabilities.setCapability("noReset", "false");
                desiredCapabilities.setCapability("clearSystemFiles", "true");
                break;
            default:
                throw new Exception("Can't find platform env var:" + platform);
        }
        return desiredCapabilities;
    }

    private AppiumDriver getMobileDriver(DesiredCapabilities desiredCapabilities) throws Exception{
        String platform = System.getenv("PLATFORM");
        switch (platform) {
            case PLATFORM_ANDROID:
                return new AndroidDriver(new URL(appiumURL), desiredCapabilities);
            case PLATFORM_IOS:
                return new IOSDriver(new URL(appiumURL), desiredCapabilities);
            default:
                throw new Exception("Can't find platform env var:" + platform);
        }
    }

}
