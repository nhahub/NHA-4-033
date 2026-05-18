package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HamburgerMenuPage {

    WebDriver driver;

    // Locators

    private final By menuButton =
            By.id("react-burger-menu-btn");

    private final By closeMenuButton =
            By.id("react-burger-cross-btn");

    private final By logoutLink =
            By.id("logout_sidebar_link");

    private final By menuContainer =
            By.className("bm-menu-wrap");

    // Constructor

    public HamburgerMenuPage(WebDriver driver) {

        this.driver = driver;
    }

    ////////////////// Actions //////////////////

    public HamburgerMenuPage openMenu() {

        driver.findElement(menuButton).click();

        return this;
    }

    public HamburgerMenuPage closeMenu() {

        driver.findElement(closeMenuButton).click();

        return this;
    }

    public HamburgerMenuPage clickLogout() {

        driver.findElement(logoutLink).click();

        return this;
    }

    ////////////////// Validations //////////////////

    public HamburgerMenuPage assertMenuOpened() {

        Assert.assertTrue(
                driver.findElement(menuContainer)
                        .isDisplayed()
        );

        return this;
    }

    public HamburgerMenuPage assertLogoutSuccessful() {

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/"
        );

        return this;
    }
}