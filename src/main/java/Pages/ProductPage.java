package Pages;

import utils.ElementActions;
import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage {

    private final WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By inventoryItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitleText() {
        return ElementActions.getText(driver, pageTitle);
    }

    public int getProductCount() {
        List<WebElement> items = driver.findElements(inventoryItems);
        return items.size();
    }

    public boolean areProductNamesVisible() {
        List<WebElement> names = driver.findElements(productNames);
        return !names.isEmpty();
    }

    public boolean areProductPricesVisible() {
        List<WebElement> prices = driver.findElements(productPrices);
        return !prices.isEmpty();
    }

    public void sortBy(String optionValue) {
        WebElement dropdown = Waits.waitForElementVisible(driver, sortDropdown);
        Select select = new Select(dropdown);
        select.selectByValue(optionValue);
    }

    public String getFirstProductName() {
        List<WebElement> names = driver.findElements(productNames);
        return names.get(0).getText();
    }

    public void addProductToCartById(String productId) {
        By addButton = By.id("add-to-cart-" + productId);
        ElementActions.clickElement(driver, addButton);
    }

    public void removeProductFromCartById(String productId) {
        By removeButton = By.id("remove-" + productId);
        ElementActions.clickElement(driver, removeButton);
    }

    public String getCartBadgeCount() {
        return ElementActions.getText(driver, cartBadge);
    }

    public boolean isCartBadgeDisplayed() {
        return ElementActions.isDisplayed(driver, cartBadge);
    }

    public void openCart() {
        ElementActions.clickElement(driver, cartLink);
    }

    public void openProductByName(String productName) {
        By productLink = By.linkText(productName);
        ElementActions.clickElement(driver, productLink);
    }

    public boolean isMenuButtonVisible() {
        return ElementActions.isDisplayed(driver, menuButton);
    }
}
