package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private ElementActions() {

    }

    //send keys
    public static void sendData(WebDriver driver, By locator, String data) {
        Waits.waitForElementVisible(driver, locator).sendKeys(data);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver,locator).clear();
        findElement(driver,locator).sendKeys(data);

    }

    //click
    public static void clickElement(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
    }

    public static void typeText(WebDriver driver, By locator, String text) {
        WebElement element = Waits.waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return findElement(driver, locator).getText();
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        Waits.waitForElementVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElement(locator);
    }
    public static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = Waits.waitForElementVisible(driver, locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
