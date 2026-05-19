package Tests;

import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.LoginPage;
import utils.BrowserActions;
import utils.DataReader;
import utils.ExcelReader;
import java.util.List;
import Pages.TestData;
import Pages.HomePage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    // DataProvider pulls arrays from your custom DataReader utility class
    @DataProvider(name = "sauceDemoUsers")
    public Object[][] getUserData() {
        return DataReader.readCsv("users.csv");
    }


    @Test(dataProvider = "sauceDemoUsers")
    public void testLoginWithMultipleUsers(String username, String password, String expectedResult) {
        // Execute the login action using POM
        loginPage.login(username, password);

        // Conditional assertion logic based on CSV configuration
        if (expectedResult.equalsIgnoreCase("success")) {
            // Successful profiles should navigate directly to the store index
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                    "Login flow failed for standard functional profile: " + username);
        } else if (expectedResult.equalsIgnoreCase("locked_out")) {
            // Invalid/Blocked users should remain on screen and flag a UI error banner
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                    "An error notification was expected but not found on the login form.");
            Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out"),
                    "The text description inside the validation banner was incorrect for: " + username);
        }
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData()
    {
        return DataReader.readCsv("login-data.csv");
    }


    @Test(description = "TC-L01: Login page shows username and password fields")
    public void testLoginPageShowsFields() {
        Assert.assertTrue(loginPage.isUsernameFieldVisible(), "Username field should be visible");
        Assert.assertTrue(loginPage.isPasswordFieldVisible(), "Password field should be visible");
    }

    @Test(description = "TC-L02: Valid login with standard_user opens products page")
    public void testValidLogin() {
        loginWithValidUser();

        String currentUrl = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(currentUrl.contains("inventory.html"), "User should land on products page");
    }

    @Test(description = "TC-L03 to TC-L06: Login with different data from CSV file",
            dataProvider = "loginData")
    public void testLoginWithData(String username, String password,
                                  String shouldLogin,
                                  String expectedUrlPart) {

        loginPage.login(username, password);

        String currentUrl = BrowserActions.getCurrentUrl(driver);
        Assert.assertTrue(currentUrl.contains(expectedUrlPart),
                "URL should contain: " + expectedUrlPart + " but was: " + currentUrl);

        boolean loginSuccess = Boolean.parseBoolean(shouldLogin);
        if (loginSuccess) {
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Login should succeed");
        } else {
            Assert.assertFalse(currentUrl.contains("inventory.html"), "Login should fail");
        }
    }

    ///
    @DataProvider(name="loginData2")
    public Object[][] loginData(){

        List<List<String>> excelData =
                ExcelReader.readSheet(
                        "Login_Data");

        Object[][] data =
                new Object[excelData.size()][3];

        for(int i=0;
            i<excelData.size();
            i++){

            data[i][0] =
                    excelData.get(i).get(0);

            data[i][1] =
                    excelData.get(i).get(1);

            data[i][2] =
                    excelData.get(i).get(2);
        }

        return data;
    }


    @Test(dataProvider="loginData2")
    public void loginWithExcelData(
            String username,
            String password){

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                username,
                password);

        if(driver.getCurrentUrl()
                .contains("inventory")){

            System.out.println(
                    "LOGIN SUCCESS: "
                            + username);
        }

        else{

            System.out.println(
                    "LOGIN FAILED: "
                            + username);
        }
    }


    @Test
    public void validLogin(){

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(
                TestData.VALID_USERNAME,
                TestData.VALID_PASSWORD);

        HomePage homePage =
                new HomePage(getDriver());

        Assert.assertTrue(
                homePage.isHomePageDisplayed());
    }

}