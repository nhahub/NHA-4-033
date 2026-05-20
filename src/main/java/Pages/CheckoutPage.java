package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;
import utils.Waits;



public class CheckoutPage {

    private final WebDriver driver;

  private final By first_name_field = By.id("first-name");
     private final By last_name_field = By.id("last-name");
  private final By postal_code_field = By.id("postal-code");
    private final By continue_button = By.id("continue");
  private final By cancel_button = By.id("cancel");
   private final By checkout_error = By.cssSelector("[data-test='error']");

  private final By finish_button = By.id("finish");
    private final By overview_title = By.className("title");

     private final By complete_header = By.className("complete-header");
  private final By back_home_button = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
      this.driver = driver;
    }

  public void enter_first_name(String first_name) {
        ElementActions.typeText(driver, first_name_field, first_name);
  }

    public void enter_last_name(String last_name) {
     ElementActions.typeText(driver, last_name_field, last_name);
    }

  public void enter_postal_code(String postal_code) {
      ElementActions.typeText(driver, postal_code_field, postal_code);
  }

    public void click_continue() {
   ElementActions.click(driver, continue_button);
    }

  public void fill_checkout_info(String first_name, String last_name, String postal_code) {
        enter_first_name(first_name);
     enter_last_name(last_name);
        enter_postal_code(postal_code);
  click_continue();
    }

    public String get_checkout_error_message() {
      return ElementActions.getText(driver, checkout_error);
    }

  public String get_overview_title() {
        return ElementActions.getText(driver, overview_title);
  }

    public void click_finish() {
     ElementActions.click(driver, finish_button);
    }

  public String get_complete_message() {
      return ElementActions.getText(driver, complete_header);
  }

    public void click_back_home() {
   ElementActions.click(driver, back_home_button);
    }

  public boolean is_on_checkout_step_one() {
        return Waits.waitForUrlContains(driver, "checkout-step-one");
  }
}
