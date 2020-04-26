package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.MainPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    protected AppiumDriver driver;
    private static String appiumURL = "http://localhost:4723/wd/hub";

    @BeforeEach
    public void setUp() throws Exception {
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

        driver = new AndroidDriver(new URL(appiumURL), desiredCapabilities);

        this.rotateScreenPortrait();
        //Skip onboarding on clean start
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.skipOnboarding();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
}
