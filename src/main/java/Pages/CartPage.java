package Pages;

import utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitleText() {
        return ElementActions.getText(driver, pageTitle);
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void clickCheckout() {
        ElementActions.clickElement(driver, checkoutButton);
    }

    public void clickContinueShopping() {
        ElementActions.clickElement(driver, continueShoppingButton);
    }

    public void removeItemById(String productId) {
        By removeButton = By.id("remove-" + productId);
        ElementActions.clickElement(driver, removeButton);
    }
}
