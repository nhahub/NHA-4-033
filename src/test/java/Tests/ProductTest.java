package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.ProductPage;
import utils.BrowserActions;

import org.testng.Assert;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    private ProductPage products_page;

    @BeforeMethod
    public void login_before_product_tests() {
        loginPage = new LoginPage(driver);
        loginWithValidUser();
        products_page = new ProductPage(driver);
    }

    @Test
    public void test_products_page_title() {
        String title = products_page.get_page_title_text();
        Assert.assertEquals(title, "Products", "Page title should be Products");
    }

    @Test
    public void test_product_names_and_prices_visible() {
        Assert.assertTrue(products_page.are_product_names_visible(), "Product names should be visible");
        Assert.assertTrue(products_page.are_product_prices_visible(), "Product prices should be visible");
    }


    @Test
    public void test_products_page_url() {
        String url = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(url.contains("inventory.html"), "URL should be the inventory page");
    }
}
