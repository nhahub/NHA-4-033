package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }

    // ==========================
    // HEADER LOCATORS
    // ==========================

    private final By productsTitle =
            By.className("title");

    private final By hamburgerMenu =
            By.id("react-burger-menu-btn");

    private final By shoppingCart =
            By.className("shopping_cart_link");

    private final By shoppingCartBadge =
            By.className("shopping_cart_badge");

    private final By logo =
            By.className("app_logo");

    private final By filterDropdown =
            By.className("product_sort_container");


    // ==========================
    // HEADER VALIDATIONS
    // ==========================

    public boolean isHomePageDisplayed() {

        return isDisplayed(
                productsTitle);
    }

    public boolean isHamburgerMenuClickable() {

        return isElementClickable(
                hamburgerMenu);
    }

    public boolean isShoppingCartClickable() {

        return isElementClickable(
                shoppingCart);
    }

    public boolean isLogoDisplayed() {

        return isDisplayed(
                logo);
    }

    public boolean isFilterDisplayed() {

        return isDisplayed(
                filterDropdown);
    }

    public boolean isFilterClickable() {

        return isElementClickable(
                filterDropdown);
    }


    // ==========================
    // CART BADGE
    // ==========================

    public String getCartBadgeCount() {

        if (driver.findElements(
                        shoppingCartBadge)
                .isEmpty()) {

            return "0";
        }

        return getText(
                shoppingCartBadge);
    }


    // ==========================
    // POSITION / SIZE DATA
    // ==========================

    public Rectangle getShoppingCartRect() {

        return driver.findElement(
                        shoppingCart)
                .getRect();
    }

    public Rectangle getHamburgerRect() {

        return driver.findElement(
                        hamburgerMenu)
                .getRect();
    }

    public Rectangle getLogoRect() {

        return driver.findElement(
                        logo)
                .getRect();
    }

    public Rectangle getProductsTitleRect() {

        return driver.findElement(
                        productsTitle)
                .getRect();
    }

    public Rectangle getFilterRect() {

        return driver.findElement(
                        filterDropdown)
                .getRect();
    }

    private final By products =
            By.className("inventory_item");

    private final By productNames =
            By.className("inventory_item_name");

    private final By descriptions =
            By.className("inventory_item_desc");

    private final By prices =
            By.className("inventory_item_price");

    private final By images =
            By.cssSelector(".inventory_item_img img");

    private final By addToCartButtons =
            By.cssSelector(
                    ".btn_inventory");
    private final By productNamesHover =
            By.className(
                    "inventory_item_name");

    // ==========================
// PRODUCTS METHODS
// ==========================

    public int getProductsCount() {

        return driver
                .findElements(
                        products)
                .size();
    }


    public boolean areProductNamesDisplayed() {

        return driver
                .findElements(
                        productNames)

                .stream()

                .allMatch(WebElement::isDisplayed);
    }


    public boolean areDescriptionsDisplayed() {

        return driver
                .findElements(
                        descriptions)

                .stream()

                .allMatch(WebElement::isDisplayed);

    }


    public boolean arePricesDisplayed() {

        return driver
                .findElements(
                        prices)

                .stream()
                .allMatch(WebElement::isDisplayed);

    }


    public boolean arePricesInDollar() {

        return driver
                .findElements(
                        prices)

                .stream()

                .allMatch(
                        element ->

                                element
                                        .getText()

                                        .contains("$"));
    }


    public boolean areImagesDisplayed() {

        return driver
                .findElements(
                        images)

                .stream()

                .allMatch(WebElement::isDisplayed);
    }


    public boolean areAddToCartButtonsDisplayed() {

        return driver
                .findElements(
                        addToCartButtons)

                .stream()

                .allMatch(WebElement::isDisplayed);
    }
    public boolean hoverOnAllProducts(){

        org.openqa.selenium.interactions
                .Actions actions =

                new org.openqa.selenium
                        .interactions
                        .Actions(driver);

        try{

            driver.findElements(
                            productNamesHover)

                    .forEach(

                            element ->

                                    actions
                                            .moveToElement(
                                                    element)
                                            .perform()
                    );

            return true;
        }

        catch(Exception e){

            return false;
        }
    }
    public java.util.Map<String,String>
    getProductsImages(){

        java.util.Map<String,String>
                imagesMap =

                new java.util.HashMap<>();

        java.util.List<org.openqa.selenium.WebElement>
                names =

                driver.findElements(
                        productNames);

        java.util.List<org.openqa.selenium.WebElement>
                imgs =

                driver.findElements(
                        images);

        for(int i=0;
            i<names.size();
            i++){

            imagesMap.put(

                    names
                            .get(i)
                            .getText(),

                    imgs
                            .get(i)
                            .getAttribute(
                                    "src")
            );
        }

        return imagesMap;
    }
    public java.util.Map<String,String>
    getProductsDescriptions(){

        java.util.Map<String,String>
                descriptionsMap =

                new java.util.HashMap<>();

        java.util.List<
                org.openqa.selenium.WebElement>
                names =

                driver.findElements(
                        productNames);

        java.util.List<
                org.openqa.selenium.WebElement>
                descs =

                driver.findElements(
                        descriptions);

        for(int i=0;
            i<names.size();
            i++){

            descriptionsMap.put(

                    names
                            .get(i)
                            .getText(),

                    descs
                            .get(i)
                            .getText()
            );
        }

        return descriptionsMap;
    }
    public java.util.Map<String,String>
    getProductsPrices(){

        java.util.Map<String,String>
                pricesMap =

                new java.util.HashMap<>();

        java.util.List<
                org.openqa.selenium.WebElement>
                names =

                driver.findElements(
                        productNames);

        java.util.List<
                org.openqa.selenium.WebElement>
                priceList =

                driver.findElements(
                        prices);

        for(int i=0;
            i<names.size();
            i++){

            pricesMap.put(

                    names
                            .get(i)
                            .getText(),

                    priceList
                            .get(i)
                            .getText()
            );
        }

        return pricesMap;
    }

    public java.util.Map<String,Boolean>
    addAllProductsToCart(){

        java.util.Map<String,Boolean>
                results =

                new java.util.HashMap<>();

        for(int i=0;
            i<driver.findElements(
                            productNames)
                    .size();
            i++){

            String product =

                    driver
                            .findElements(
                                    productNames)
                            .get(i)
                            .getText();

            driver
                    .findElements(
                            addToCartButtons)
                    .get(i)
                    .click();

            String buttonText =

                    driver
                            .findElements(
                                    addToCartButtons)
                            .get(i)
                            .getText();

            boolean added =

                    buttonText
                            .equalsIgnoreCase(
                                    "Remove");

            results.put(
                    product,
                    added);
        }

        return results;
    }



    public java.util.Map<String,Boolean>
    removeAllProducts(){

        java.util.Map<String,Boolean>
                results =

                new java.util.HashMap<>();

        for(int i=0;
            i<driver.findElements(
                            productNames)
                    .size();
            i++){

            String product =

                    driver
                            .findElements(
                                    productNames)
                            .get(i)
                            .getText();

            driver
                    .findElements(
                            addToCartButtons)
                    .get(i)
                    .click();

            String buttonText =

                    driver
                            .findElements(
                                    addToCartButtons)
                            .get(i)
                            .getText();

            boolean removed =

                    buttonText
                            .equalsIgnoreCase(
                                    "Add to cart");

            results.put(
                    product,
                    removed);
        }

        return results;
    }

    public boolean isCartEmpty(){

        return driver
                .findElements(
                        shoppingCartBadge)

                .isEmpty();
    }
}