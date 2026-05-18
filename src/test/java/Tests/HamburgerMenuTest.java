package Tests;

import Pages.HamburgerMenuPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class HamburgerMenuTest {

    WebDriver driver;

    HamburgerMenuPage hamburgerMenuPage;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        // Login first

        new LoginPage(driver)
                .enterUsername("standard_user")
                .enterPassword("secret_sauce").clickLoginButton();


        hamburgerMenuPage =
                new HamburgerMenuPage(driver);
    }

    @Test(priority = 1)
    public void verifyMenuCanBeOpened() {

        hamburgerMenuPage
                .openMenu()
                .assertMenuOpened();
    }

    @Test(priority = 2)
    public void verifyUserCanLogout() {

        hamburgerMenuPage
                .openMenu()
                .clickLogout()
                .assertLogoutSuccessful();
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}