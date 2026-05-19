package Tests;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import Pages.FilterPage;

public class FilterTest {

    WebDriver driver;

    FilterPage filterPage;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        // Login first because filter exists after login

        new LoginPage(driver)
                .enterUsername("problem_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        filterPage = new FilterPage(driver);
    }

    @Test(priority = 1)
    public void verifyNameAToZSorting() {

        filterPage
                .selectNameAToZ()
                .assertProductsSortedAscending();
    }

    @Test(priority = 2)
    public void verifyNameZToASorting() {

        filterPage
                .selectNameZToA()
                .assertProductsSortedDescending();
    }

    @Test(priority = 3)
    public void verifyPriceLowToHighSorting() {

        filterPage
                .selectPriceLowToHigh()
                .assertPricesSortedLowToHigh();
    }

    @Test(priority = 4)
    public void verifyPriceHighToLowSorting() {

        filterPage
                .selectPriceHighToLow()
                .assertPricesSortedHighToLow();
    }

    @AfterMethod
    public void tearDown() {

    //    driver.quit();
    }
}