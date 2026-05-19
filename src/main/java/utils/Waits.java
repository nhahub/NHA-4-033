package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {


    private static final int WAIT_SECONDS = 10;

    public Waits(WebDriver driver) {
    }

    //1-wait for element to be present
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(driver1 -> driver1.findElement(locator));

    }

    //2-wait for element to be visible
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(driver1 ->
                        {
                            WebElement element = waitForElementPresent(driver, locator);
                            return element.isDisplayed() ? element : null;
                        }
                );
    }

    //3-wait for element to be clickable
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 ->
                        {
                            WebElement element = waitForElementVisible(driver, locator);
                            return element.isEnabled() ? element : null;
                        }

                );
    }
    public static boolean waitForUrlContains(WebDriver driver, String textInUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        return wait.until(ExpectedConditions.urlContains(textInUrl));
    }

}
