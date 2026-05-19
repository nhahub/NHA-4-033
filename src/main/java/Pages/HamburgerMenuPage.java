package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HamburgerMenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By allItemsLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");

    // Explicit wait locator to check if menu is fully open/visible
    private final By sidebarMenu = By.className("bm-menu-wrap");

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openMenu() {
        driver.findElement(menuButton).click();
        // Wait until the sidebar wrapper is visible and active
        wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarMenu));
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItemsLink)).click();
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void clickResetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(resetLink)).click();
    }
}