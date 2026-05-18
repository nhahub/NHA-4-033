package Tests;

import Base.BaseTest;
import Pages.ProductPage;
import utils.BrowserActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    private ProductPage productsPage;

    @BeforeMethod
    public void loginBeforeProductTests() {
        loginWithValidUser();
        productsPage = new ProductPage(driver);
    }

    @Test(description = "TC-P01: Products page title is 'Products'")
    public void testProductsPageTitle() {
        String title = productsPage.getPageTitleText();
        Assert.assertEquals(title, "Products", "Page title should be Products");
    }

    @Test(description = "TC-P02: Products page shows 6 items")
    public void testProductCount() {
        int count = productsPage.getProductCount();
        Assert.assertEquals(count, 6, "There should be 6 products on the page");
    }

    @Test(description = "TC-P03: Each product has a name and price")
    public void testProductNamesAndPricesVisible() {
        Assert.assertTrue(productsPage.areProductNamesVisible(), "Product names should be visible");
        Assert.assertTrue(productsPage.areProductPricesVisible(), "Product prices should be visible");
    }

    @Test(description = "TC-P04: Sort products by price (low to high)")
    public void testSortByPriceLowToHigh() {
        productsPage.sortBy("lohi");
        String firstProduct = productsPage.getFirstProductName();
        Assert.assertEquals(firstProduct, "Sauce Labs Onesie", "Cheapest product should be first");
    }

    @Test(description = "TC-P05: Sort products by name (A to Z)")
    public void testSortByNameAtoZ() {
        productsPage.sortBy("az");
        String firstProduct = productsPage.getFirstProductName();
        Assert.assertEquals(firstProduct, "Sauce Labs Backpack", "First product should be Backpack (A-Z)");
    }

    @Test(description = "TC-P06: Menu button is visible on products page")
    public void testMenuButtonVisible() {
        Assert.assertTrue(productsPage.isMenuButtonVisible(), "Menu button should be visible");
    }

    @Test(description = "TC-P07: Page URL contains inventory after login")
    public void testProductsPageUrl() {
        String url = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(url.contains("inventory.html"), "URL should be the inventory page");
    }
}
