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

    private final By page_title = By.className("title");
    private final By inventory_items = By.className("inventory_item");
    private final By product_names = By.className("inventory_item_name");
    private final By product_prices = By.className("inventory_item_price");
    private final By sort_dropdown = By.className("product_sort_container");
    private final By cart_badge = By.className("shopping_cart_badge");
    private final By cart_link = By.className("shopping_cart_link");
    private final By menu_button = By.id("react-burger-menu-btn");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String get_page_title_text() {
        return ElementActions.getText(driver, page_title);
    }

    public int get_product_count() {
        List<WebElement> items = driver.findElements(inventory_items);
        return items.size();
    }

    public boolean are_product_names_visible() {
        List<WebElement> names = driver.findElements(product_names);
        return !names.isEmpty();
    }

    public boolean are_product_prices_visible() {
        List<WebElement> prices = driver.findElements(product_prices);
        return !prices.isEmpty();
    }

    public void sort_by(String option_value) {
        WebElement dropdown = Waits.waitForElementVisible(driver, sort_dropdown);
        Select select = new Select(dropdown);
        select.selectByValue(option_value);
    }

    public String get_first_product_name() {
        List<WebElement> names = driver.findElements(product_names);
        return names.get(0).getText();
    }

    public void add_product_to_cart_by_id(String product_id) {
        By add_button = By.id("add-to-cart-" + product_id);
        ElementActions.click(driver, add_button);
    }

    public void remove_product_from_cart_by_id(String product_id) {
        By remove_button = By.id("remove-" + product_id);
        ElementActions.click(driver, remove_button);
    }

    public String get_cart_badge_count() {
        return ElementActions.getText(driver, cart_badge);
    }

    public boolean is_cart_badge_displayed() {
        return ElementActions.isDisplayed(driver, cart_badge);
    }

    public void open_cart() {
        ElementActions.click(driver, cart_link);
    }

    public void open_product_by_name(String product_name) {
        By product_link = By.linkText(product_name);
        ElementActions.click(driver, product_link);
    }

    public boolean is_menu_button_visible() {
        return ElementActions.isDisplayed(driver, menu_button);
    }
}