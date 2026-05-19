package Tests;
import Base.BaseTest;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductPage;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {

  private ProductPage products_page;
    private CartPage cart_page;

  @BeforeMethod
    public void login_before_cart_tests() {
     loginPage = new LoginPage(driver);
     loginWithValidUser();
        products_page = new ProductPage(driver);
  cart_page = new CartPage(driver);
    }

  @Test
    public void test_add_one_product_to_cart() {
        products_page.add_product_to_cart_by_id("sauce-labs-backpack");

     Assert.assertTrue(products_page.is_cart_badge_displayed(), "Cart badge should appear");
  Assert.assertEquals(products_page.get_cart_badge_count(), "1", "Cart badge should show 1");
    }

 

  @Test
    public void test_remove_product_from_products_page() {
        products_page.add_product_to_cart_by_id("sauce-labs-backpack");
     products_page.remove_product_from_cart_by_id("sauce-labs-backpack");

  Assert.assertFalse(products_page.is_cart_badge_displayed(), "Cart badge should disappear");
    }

  @Test
    public void test_cart_page_shows_product() {
     products_page.add_product_to_cart_by_id("sauce-labs-backpack");
        products_page.open_cart();

  Assert.assertEquals(cart_page.get_page_title_text(), "Your Cart", "Cart page title should be Your Cart");
     Assert.assertEquals(cart_page.get_cart_item_count(), 1, "Cart should have 1 item");
    }

}
