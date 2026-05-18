package Tests;

import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.ExtentManager;

public class LoginTest {

    //variables
    WebDriver driver;
    ExtentReports extent;
    //Tests
    @Test(priority = 1)
    public void successfulLogin() {
        new LoginPage(driver)
                .enterUsername("problem_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertSuccessfulLogin();
    }
    @Test(priority = 2)
    public void verifyLoginWithInvalidPassword() {

        new LoginPage(driver)
                .enterUsername("standard_user")
                .enterPassword("wrong_password")
                .clickLoginButton()
                .assertErrorMessageDisplayed();
    }
    @Test(priority = 3)
    public void verifyLoginWithInvalidUsername() {

        new LoginPage(driver)
                .enterUsername("wrong_user")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertErrorMessageDisplayed();
    }

    @Test(priority = 4)
    public void verifyLoginWithEmptyUsername() {

        new LoginPage(driver)
                .enterUsername("")
                .enterPassword("secret_sauce")
                .clickLoginButton()
                .assertUsernameRequiredMessage();
    }

    @Test(priority = 5)
    public void verifyLoginWithEmptyPassword() {

        new LoginPage(driver)
                .enterUsername("standard_user")
                .enterPassword("")
                .clickLoginButton()
                .assertPasswordRequiredMessage();
    }

    @Test(priority = 6)
    public void verifyLoginWithEmptyCredentials() {

        new LoginPage(driver)
                .enterUsername("")
                .enterPassword("")
                .clickLoginButton()
                .assertUsernameRequiredMessage();
    }
    //configurations
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        //annonyms object of login page and navigate to login page
        new Pages.LoginPage(driver).navigateToLoginPage();

    }
    @BeforeSuite
    public void startReport() {

        extent = ExtentManager.getInstance();
    }
    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(5000);
         driver.quit();
    }

    @AfterSuite
    public void flushReport() {

        extent.flush();
    }

}