package Tests;
import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ProductPage;
import utils.BrowserActions;
import utils.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private ProductPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @DataProvider(name = "checkoutData")
    public Object[][] getCheckoutData() {
        return DataReader.readCsv("checkout-data.csv");
    }

    @BeforeMethod
    public void loginAndAddProduct() {
        loginWithValidUser();
        productsPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.openCart();
        cartPage.clickCheckout();
    }

    @Test(description = "TC-CH01: Checkout step one page opens")
    public void testCheckoutStepOneOpens() {
        Assert.assertTrue(checkoutPage.isOnCheckoutStepOne(), "Should be on checkout step one");
    }

    @Test(description = "TC-CH02: Complete checkout shows thank you message")
    public void testCompleteCheckout() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickFinish();

        String message = checkoutPage.getCompleteMessage();
        Assert.assertEquals(message, "Thank you for your order!", "Order should be complete");
    }

    @Test(description = "TC-CH03: Back home button returns to products page")
    public void testBackHomeAfterCheckout() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickFinish();
        checkoutPage.clickBackHome();

        Assert.assertEquals(productsPage.getPageTitleText(), "Products", "Should be back on products page");
    }

    @Test(description = "TC-CH04 to TC-CH07: Checkout with data from CSV file", dataProvider = "checkoutData")
    public void testCheckoutWithData(String firstName, String lastName, String postalCode,
                                     String shouldPass, String expectedMessagePart) {
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
        checkoutPage.clickContinue();

        boolean pass = Boolean.parseBoolean(shouldPass);

        if (pass) {
            checkoutPage.clickFinish();
            String message = checkoutPage.getCompleteMessage();
            Assert.assertTrue(message.contains(expectedMessagePart),
                    "Message should contain: " + expectedMessagePart);
        } else {
            String error = checkoutPage.getCheckoutErrorMessage();
            Assert.assertTrue(error.contains(expectedMessagePart),
                    "Error should contain: " + expectedMessagePart);
        }
    }

    @Test(description = "TC-CH08: Checkout overview page title is correct")
    public void testCheckoutOverviewTitle() {
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");

        String title = checkoutPage.getOverviewTitle();
        Assert.assertEquals(title, "Checkout: Overview", "Overview title should match");
    }

    @Test(description = "TC-CH09: URL contains checkout after clicking checkout button")
    public void testCheckoutUrl() {
        String url = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(url.contains("checkout-step-one"), "URL should be checkout step one");
    }
}
