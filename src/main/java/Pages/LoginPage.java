package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class LoginPage {
    private final WebDriver driver;

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

//actions
    public LoginPage enterUsername(String username) {
        ElementActions.typeText(driver, usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        ElementActions.typeText(driver, passwordField, password);
        return this;
    }

    public void clickLogin() {
        ElementActions.click(driver, loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }


    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isUsernameFieldVisible() {
        return ElementActions.isDisplayed(driver, usernameField);
    }
    public boolean isPasswordFieldVisible() {
        return ElementActions.isDisplayed(driver, passwordField);
    }

}