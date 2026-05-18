package Pages;

import utils.ElementActions;
import utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By checkoutError = By.cssSelector("[data-test='error']");

    private final By finishButton = By.id("finish");
    private final By overviewTitle = By.className("title");

    private final By completeHeader = By.className("complete-header");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        ElementActions.typeText(driver, firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        ElementActions.typeText(driver, lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        ElementActions.typeText(driver, postalCodeField, postalCode);
    }

    public void clickContinue() {
        ElementActions.clickElement(driver, continueButton);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    public String getCheckoutErrorMessage() {
        return ElementActions.getText(driver, checkoutError);
    }

    public String getOverviewTitle() {
        return ElementActions.getText(driver, overviewTitle);
    }

    public void clickFinish() {
        ElementActions.clickElement(driver, finishButton);
    }

    public String getCompleteMessage() {
        return ElementActions.getText(driver, completeHeader);
    }

    public void clickBackHome() {
        ElementActions.clickElement(driver, backHomeButton);
    }

    public boolean isOnCheckoutStepOne() {
        return Waits.waitForUrlContains(driver, "checkout-step-one");
    }
}
