package Base;

import Pages.LoginPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


public class BaseTest {

    public WebDriver driver;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    protected static final String VALID_USERNAME = "standard_user";
    protected static final String VALID_PASSWORD = "secret_sauce";
    protected LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


      /*  ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");*/
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver(){

        return driver;
    }
    public void loginWithValidUser() {
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
    }
}