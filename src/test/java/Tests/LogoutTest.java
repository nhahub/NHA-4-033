package Tests;

import Pages.LoginPage;
import Pages.LogoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LogoutTest {

    WebDriver driver;

    LogoutPage logoutPage;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        // Login first

        new LoginPage(driver)
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        logoutPage = new LogoutPage(driver);
    }

    @Test(priority = 1)
    public void verifyUserCanOpenHamburgerMenu() {

        logoutPage
                .openHamburgerMenu()
                .assertHamburgerMenuOpened();
    }

    @Test(priority = 2)
    public void verifyUserCanLogout() {

        logoutPage
                .logout()
                .assertLogoutSuccessful();
    }

    @AfterMethod
    public void tearDown() {

      //  driver.quit();
    }
}