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

public class iOSTestCase {
    protected AppiumDriver driver;
    private static String appiumURL = "http://localhost:4723/wd/hub";

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 8");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("app", "/Users/digital_qa/IdeaProjects/AppiumDemoProject/apks/Wikipedia.app");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability("clearSystemFiles", "true");

        driver = new IOSDriver(new URL(appiumURL), desiredCapabilities);
        this.rotateScreenPortrait();
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
