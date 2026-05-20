// ===============================
// BasePage.java
// ===============================

package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10));

        actions = new Actions(driver);
    }

    public void click(By locator) {

        WebElement element = wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator));

        element.click();
    }

    public void type(By locator, String text){

        WebElement element = wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator){

        return wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(locator))
                .getText();
    }

    public boolean isDisplayed(By locator){

        return wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(locator))
                .isDisplayed();
    }

    public boolean isElementClickable(By locator){

        try {

            WebElement element = wait.until(
                    ExpectedConditions
                            .elementToBeClickable(locator));

            return element.isDisplayed()
                    &&
                    element.isEnabled();

        } catch (Exception e){

            return false;
        }
    }

    public void hover(By locator){

        actions.moveToElement(
                        driver.findElement(locator))
                .perform();
    }
}