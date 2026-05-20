package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BrowserActions;
import utils.DataReader;

public class CheckoutTest extends BaseTest {

  private ProductPage products_page;
    private CartPage cart_page;
  private CheckoutPage checkout_page;

  @DataProvider(name = "checkoutData")
    public Object[][] get_checkout_data() {
     return DataReader.readCsv("checkout-data.csv");
    }

  @BeforeMethod
  public void login_and_add_product() {
        loginPage = new LoginPage(driver);
        loginWithValidUser();
     products_page = new ProductPage(driver);
  cart_page = new CartPage(driver);
        checkout_page = new CheckoutPage(driver);

    products_page.add_product_to_cart_by_id("sauce-labs-backpack");
  products_page.open_cart();
     cart_page.click_checkout();
    }

  @Test
    public void test_checkout_step_one_opens() {
        Assert.assertTrue(checkout_page.is_on_checkout_step_one(), "Should be on checkout step one");
  }

    @Test(dataProvider = "checkoutData")
  public void test_complete_checkout(String first_name, String last_name, String postal_code,
                                     String should_pass, String expected_message_part) {
     checkout_page.fill_checkout_info(first_name, last_name, postal_code);

        if (Boolean.parseBoolean(should_pass)) {
            checkout_page.click_finish();
            String message = checkout_page.get_complete_message();
            Assert.assertTrue(message.contains(expected_message_part),
                    "Message should contain: " + expected_message_part);
        } else {
            String error = checkout_page.get_checkout_error_message();
            Assert.assertTrue(error.contains(expected_message_part),
                    "Error should contain: " + expected_message_part);
        }
    }


  @Test
    public void test_checkout_url() {
     String url = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(url.contains("checkout-step-one"), "URL should be checkout step one");
    }
}
