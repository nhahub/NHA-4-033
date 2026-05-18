package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LogoutPage {

    WebDriver driver;

    // Locators

    private final By hamburgerMenuButton = By.id("react-burger-menu-btn");
    private final By logoutButton =By.id("logout_sidebar_link");
    private final By hamburgerMenuContainer =By.className("bm-menu-wrap");
    // Constructor

    public LogoutPage(WebDriver driver) {

        this.driver = driver;
    }

    ////////////////// Actions //////////////////

    public LogoutPage openHamburgerMenu() {

        driver.findElement(hamburgerMenuButton).click();

        return this;
    }

    public LogoutPage clickLogoutButton() {

        driver.findElement(logoutButton).click();

        return this;
    }

    public LogoutPage logout() {

        openHamburgerMenu();

        clickLogoutButton();

        return this;
    }

    ////////////////// Validations //////////////////

    public LogoutPage assertLogoutSuccessful() {

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/"
        );

        return this;
    }

    public LogoutPage assertHamburgerMenuOpened() {

        Assert.assertTrue(
                driver.findElement(hamburgerMenuContainer)
                        .isDisplayed()
        );

        return this;
    }
}