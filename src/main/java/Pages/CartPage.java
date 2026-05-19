package Pages;

import utils.ElementActions;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

  private final WebDriver driver;

    private final By page_title = By.className("title");
<<<<<<< HEAD
    private final By cart_items = By.className("cart_item");
    private final By checkout_button = By.id("checkout");
    private final By continue_shopping_button = By.id("continue-shopping");
=======
  private final By cart_items = By.className("cart_item");
     private final By checkout_button = By.id("checkout");
  private final By continue_shopping_button = By.id("continue-shopping");
>>>>>>> 542618504cf8247e2ec90c9d7bd08823f7fed60d

    public CartPage(WebDriver driver) {
      this.driver = driver;
    }

<<<<<<< HEAD
    public String get_page_title_text() {
        return ElementActions.getText(driver, page_title);
    }

    public int get_cart_item_count() {
        List<WebElement> items = driver.findElements(cart_items);
        return items.size();
    }

    public void click_checkout() {
        ElementActions.click(driver, checkout_button);
    }

    public void click_continue_shopping() {
        ElementActions.click(driver, continue_shopping_button);
    }

    public void remove_item_by_id(String product_id) {
        By remove_button = By.id("remove-" + product_id);
        ElementActions.click(driver, remove_button);
    }
}
=======
  public String get_page_title_text() {
        return ElementActions.getText(driver, page_title);
  }

    public int get_cart_item_count() {
     List<WebElement> items = driver.findElements(cart_items);
        return items.size();
    }

  public void click_checkout() {
      ElementActions.clickElement(driver, checkout_button);
  }

    public void click_continue_shopping() {
   ElementActions.clickElement(driver, continue_shopping_button);
    }

  public void remove_item_by_id(String product_id) {
        By remove_button = By.id("remove-" + product_id);
     ElementActions.clickElement(driver, remove_button);
  }
}
>>>>>>> 542618504cf8247e2ec90c9d7bd08823f7fed60d
