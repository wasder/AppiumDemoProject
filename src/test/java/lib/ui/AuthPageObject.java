package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObject  extends MyListsPageObject{
    private static final String LOGIN_BTN = "xpath://div/a[contains(text(), 'Log in')]";
    private static final String LOGIN_INPUT = "css:#wpName1";
    private static final String PASS_INPUT = "css:#wpPassword1";
    private static final String SUBMIT_BTN = "css:#wpLoginAttempt";

    public AuthPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton(){
        this.tryClickElementsWithFewAttempts(LOGIN_BTN, 5);
    }

    public void enterLoginData(String login, String pass){
        this.waitForElementPresent(LOGIN_INPUT);
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, 5);
        this.waitForElementAndSendKeys(PASS_INPUT, pass, 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BTN, 5);
    }


}
