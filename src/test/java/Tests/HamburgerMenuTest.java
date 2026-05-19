package Tests;
import Base.BaseTest;
import Pages.HamburgerMenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class HamburgerMenuTest extends BaseTest {

    private HamburgerMenuPage hamburgerMenuPage;

    @BeforeMethod
    public void navigateToDashboard() {
        loginWithValidUser();
        hamburgerMenuPage = new HamburgerMenuPage(driver);
    }

    @Test(description = "Verify that clicking 'All Items' redirects to the inventory page")
    public void testAllItemsLink() {
        driver.get("https://www.saucedemo.com/cart.html");

        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickAllItems();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "All Items link did not redirect back to the product catalog.");
    }

    @Test(description = "Verify that clicking 'About' redirects to the official Sauce Labs company page")
    public void testAboutLink() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickAbout();

        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/",
                "About link did not redirect to the correct corporate landing page.");
    }

    @Test(description = "Verify that clicking 'Logout' terminates the session and returns to login screen")
    public void testLogoutLink() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickLogout();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL,
                "Logout action did not return the user back to the base authentication portal.");
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(description = "Verify that 'Reset App State' clears active session configurations smoothly")
    public void testResetAppStateLink() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickResetAppState();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Reset App State disrupted the application context state unexpectly.");
    }
}