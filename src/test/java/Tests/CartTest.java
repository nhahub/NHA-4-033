package Tests;
import Base.BaseTest;
import Pages.CartPage;
import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void loginBeforeCartTests() {
        loginWithValidUser();
        productsPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(description = "TC-C01: Add one product shows cart badge with 1")
    public void testAddOneProductToCart() {
        productsPage.addProductToCartById("sauce-labs-backpack");

        Assert.assertTrue(productsPage.isCartBadgeDisplayed(), "Cart badge should appear");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "Cart badge should show 1");
    }

    @Test(description = "TC-C02: Add two products shows cart badge with 2")
    public void testAddTwoProductsToCart() {
        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.addProductToCartById("sauce-labs-bike-light");

        Assert.assertEquals(productsPage.getCartBadgeCount(), "2", "Cart badge should show 2");
    }

    @Test(description = "TC-C03: Remove product from cart on products page")
    public void testRemoveProductFromProductsPage() {
        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.removeProductFromCartById("sauce-labs-backpack");

        Assert.assertFalse(productsPage.isCartBadgeDisplayed(), "Cart badge should disappear");
    }

    @Test(description = "TC-C04: Cart page shows added product")
    public void testCartPageShowsProduct() {
        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.openCart();

        Assert.assertEquals(cartPage.getPageTitleText(), "Your Cart", "Cart page title should be Your Cart");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should have 1 item");
    }

    @Test(description = "TC-C05: Remove product from cart page")
    public void testRemoveProductFromCartPage() {
        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.openCart();
        cartPage.removeItemById("sauce-labs-backpack");

        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty");
    }

    @Test(description = "TC-C06: Continue shopping goes back to products page")
    public void testContinueShopping() {
        productsPage.addProductToCartById("sauce-labs-backpack");
        productsPage.openCart();
        cartPage.clickContinueShopping();

        Assert.assertEquals(productsPage.getPageTitleText(), "Products", "Should return to products page");
    }
}
