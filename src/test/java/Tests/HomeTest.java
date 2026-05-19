package Tests;

import Base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Pages.HomePage;
import Pages.LoginPage;
import utils.ExcelReader;

import java.util.List;

public class HomeTest extends BaseTest {
    private static
    java.util.Map<String,String>
            baselineImages;
    private static
    java.util.Map<String,String>
            baselineDescriptions;

    private static
    java.util.Map<String,String>
            baselinePrices;


    @DataProvider(name = "users")
    public Object[][] users(){

        List<List<String>> excelData =
                ExcelReader.readSheet(
                        "Login_Data");

        Object[][] data =
                new Object[excelData.size()][3];

        for(int i = 0;
            i < excelData.size();
            i++){

            String username =
                    excelData.get(i).get(0);

            String password =
                    excelData.get(i).get(1);

            data[i][0] = username;

            data[i][1] = password;

            data[i][2] =
                    "Homepage Test - "
                            + username;
        }

        return data;
    }


    @Test(dataProvider = "users")
    public void verifyProductsAgainstExcel(

            String username,
            String password,
            String testName) {

        SoftAssert softAssert =
                new SoftAssert();

        LoginPage loginPage =
                new LoginPage(getDriver());

        long startTime =
                System.currentTimeMillis();

        loginPage.login(
                username,
                password);

        long endTime =
                System.currentTimeMillis();

        long loginDuration =
                endTime - startTime;

        System.out.println(
                "\n==========================");

        System.out.println(
                testName);

        System.out.println(
                "LOGIN TIME: "
                        + loginDuration
                        + " ms");

        // Locked user validation

        if (username.equals(
                "locked_out_user")) {

            boolean loginSuccess =

                    getDriver()
                            .getCurrentUrl()
                            .contains(
                                    "inventory");

            softAssert.assertFalse(

                    loginSuccess,

                    "Locked user should not login"
            );

            System.out.println(

                    "LOGIN RESULT: "
                            +

                            (loginSuccess
                                    ?

                                    "FAILED: User logged in unexpectedly"

                                    :

                                    "PASSED: Login blocked as expected")
            );

            softAssert.assertAll();

            return;
        }
        HomePage homePage =
                new HomePage(getDriver());

        // =====================
        // HEADER
        // =====================

        boolean homeDisplayed =
                homePage
                        .isHomePageDisplayed();

        softAssert.assertTrue(
                homeDisplayed);

        System.out.println(
                "Homepage Display Test: "
                        +
                        (homeDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean hamburgerClickable =
                homePage
                        .isHamburgerMenuClickable();

        softAssert.assertTrue(
                hamburgerClickable);

        System.out.println(
                "Hamburger Clickable Test: "
                        +
                        (hamburgerClickable
                                ? "PASSED"
                                : "FAILED"));


        boolean cartClickable =
                homePage
                        .isShoppingCartClickable();

        softAssert.assertTrue(
                cartClickable);

        System.out.println(
                "Shopping Cart Test: "
                        +
                        (cartClickable
                                ? "PASSED"
                                : "FAILED"));


        boolean logoDisplayed =
                homePage
                        .isLogoDisplayed();

        softAssert.assertTrue(
                logoDisplayed);

        System.out.println(
                "Logo Display Test: "
                        +
                        (logoDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean filterDisplayed =
                homePage
                        .isFilterDisplayed();

        softAssert.assertTrue(
                filterDisplayed);

        System.out.println(
                "Filter Display Test: "
                        +
                        (filterDisplayed
                                ? "PASSED "
                                : "FAILED"));


        boolean filterClickable =
                homePage
                        .isFilterClickable();

        softAssert.assertTrue(
                filterClickable);

        System.out.println(
                "Filter Clickable Test: "
                        +
                        (filterClickable
                                ? "PASSED"
                                : "FAILED"));

// =====================
// PRODUCTS
// =====================

        boolean productsExist =
                homePage.getProductsCount() > 0;

        softAssert.assertTrue(
                productsExist);

        System.out.println(
                "Products Count Test: "
                        +
                        (productsExist
                                ? "PASSED"
                                : "FAILED"));


        boolean namesDisplayed =
                homePage
                        .areProductNamesDisplayed();

        softAssert.assertTrue(
                namesDisplayed);

        System.out.println(
                "Product Names Test: "
                        +
                        (namesDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean descriptionsDisplayed =
                homePage
                        .areDescriptionsDisplayed();

        softAssert.assertTrue(
                descriptionsDisplayed);

        System.out.println(
                "Product Descriptions Test: "
                        +
                        (descriptionsDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean pricesDisplayed =
                homePage
                        .arePricesDisplayed();

        softAssert.assertTrue(
                pricesDisplayed);

        System.out.println(
                "Product Prices Test: "
                        +
                        (pricesDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean pricesInDollar =
                homePage
                        .arePricesInDollar();

        softAssert.assertTrue(
                pricesInDollar);

        System.out.println(
                "Currency Test ($): "
                        +
                        (pricesInDollar
                                ? "PASSED"
                                : "FAILED"));


        boolean imagesDisplayed =
                homePage
                        .areImagesDisplayed();

        softAssert.assertTrue(
                imagesDisplayed);

        System.out.println(
                "Product Images Test: "
                        +
                        (imagesDisplayed
                                ? "PASSED"
                                : "FAILED"));


        boolean addButtonsDisplayed =
                homePage
                        .areAddToCartButtonsDisplayed();

        softAssert.assertTrue(
                addButtonsDisplayed);

        System.out.println(
                "Add To Cart Buttons Test: "
                        +
                        (addButtonsDisplayed
                                ? "PASSED"
                                : "FAILED"));
// =====================
// HOVER TEST
// =====================

        boolean hoverSuccess =
                homePage
                        .hoverOnAllProducts();

        softAssert.assertTrue(
                hoverSuccess);

        System.out.println(
                "Hover Test: "
                        +
                        (hoverSuccess
                                ? "PASSED"
                                : "FAILED"));

        // =====================
// PRODUCT BASELINE VALIDATION
// =====================

        java.util.Map<String, String>
                currentImages =

                homePage
                        .getProductsImages();


        java.util.Map<String, String>
                currentDescriptions =

                homePage
                        .getProductsDescriptions();


        java.util.Map<String, String>
                currentPrices =

                homePage
                        .getProductsPrices();


        if (username.equals(
                "standard_user")) {

            baselineImages =

                    new java.util.HashMap<>(
                            currentImages);

            baselineDescriptions =

                    new java.util.HashMap<>(
                            currentDescriptions);

            baselinePrices =

                    new java.util.HashMap<>(
                            currentPrices);

            System.out.println(
                    "BASELINE PRODUCT DATA SAVED");
        } else {

            // =====================
            // IMAGE COMPARISON
            // =====================

            for (String product :

                    baselineImages
                            .keySet()) {

                boolean imageCorrect =

                        currentImages
                                .get(product)

                                .equals(

                                        baselineImages
                                                .get(product)
                                );

                softAssert.assertTrue(

                        imageCorrect,

                        "Wrong Image for "
                                + product
                );

                System.out.println(

                        product
                                + " Image Test: "

                                +

                                (imageCorrect
                                        ? "PASSED"
                                        : "FAILED"));
            }


            // =====================
            // DESCRIPTION COMPARISON
            // =====================

            for (String product :

                    baselineDescriptions
                            .keySet()) {

                boolean descriptionCorrect =

                        currentDescriptions
                                .get(product)

                                .equals(

                                        baselineDescriptions
                                                .get(product)
                                );

                softAssert.assertTrue(

                        descriptionCorrect,

                        "Wrong Description for "
                                + product
                );

                System.out.println(

                        product
                                + " Description Test: "

                                +

                                (descriptionCorrect
                                        ? "PASSED"
                                        : "FAILED"));
            }


            // =====================
// PRICE COMPARISON
// =====================

            for (String product :

                    baselinePrices
                            .keySet()) {

                String expectedPrice =

                        baselinePrices
                                .get(product);

                String actualPrice =

                        currentPrices
                                .get(product);

                boolean priceCorrect =

                        actualPrice
                                .trim()

                                .equals(

                                        expectedPrice
                                                .trim()
                                );

                softAssert.assertTrue(

                        priceCorrect,

                        "Wrong Price for "
                                + product

                                + " | Expected: "
                                + expectedPrice

                                + " | Actual: "
                                + actualPrice
                );

                System.out.println(

                        product
                                + " Price Test: "

                                +

                                (priceCorrect

                                        ?

                                        "PASSED"

                                        :

                                        "FAILED")

                                +

                                " | Expected: "

                                + expectedPrice

                                +

                                " | Actual: "

                                + actualPrice
                );
            }
        }

        // =====================
// ADD TO CART
// =====================

        java.util.Map<String,Boolean>
                addResults =

                homePage
                        .addAllProductsToCart();

        for(java.util.Map.Entry<
                String,
                Boolean>

                entry :

                addResults.entrySet()){

            softAssert.assertTrue(

                    entry.getValue(),

                    "Couldn't add product: "
                            + entry.getKey()
            );

            System.out.println(

                    entry.getKey()
                            + " Add To Cart Test: "

                            +

                            (entry.getValue()

                                    ?

                                    "PASSED"

                                    :

                                    "FAILED"));
        }



        softAssert.assertEquals(

                homePage
                        .getCartBadgeCount(),

                "6",

                "Wrong Cart Badge Count"
        );

        System.out.println(
                "Cart Badge Test: "
                        + homePage
                        .getCartBadgeCount());




// =====================
// REMOVE PRODUCTS
// =====================

        java.util.Map<String,Boolean>
                removeResults =

                homePage
                        .removeAllProducts();

        for(java.util.Map.Entry<
                String,
                Boolean>

                entry :

                removeResults.entrySet()){

            softAssert.assertTrue(

                    entry.getValue(),

                    "Couldn't remove product: "
                            + entry.getKey()
            );

            System.out.println(

                    entry.getKey()
                            + " Remove Test: "

                            +

                            (entry.getValue()

                                    ?

                                    "PASSED"

                                    :

                                    "FAILED"));
        }



        softAssert.assertTrue(

                homePage
                        .isCartEmpty(),

                "Cart should be empty"
        );

        System.out.println(
                "Cart Empty Test: PASSED");


        // =====================
        // ADD TO CART
        // =====================

//        Map<String,Boolean> addResults =
//                homePage
//                        .addAllProductsToCart();
//
//        for(Map.Entry<String,Boolean>
//                entry :
//                addResults.entrySet()){
//
//            softAssert.assertTrue(
//
//                    entry.getValue(),
//
//                    "Couldn't add product: "
//                            + entry.getKey()
//            );
//        }
//
//        softAssert.assertEquals(
//
//                homePage
//                        .getCartBadgeCount(),
//
//                "6"
//        );

        // =====================
        // REMOVE PRODUCTS
        // =====================

//        Map<String,Boolean> removeResults =
//                homePage
//                        .removeAllProducts();
//
//        for(Map.Entry<String,Boolean>
//                entry :
//                removeResults.entrySet()){
//
//            softAssert.assertTrue(
//
//                    entry.getValue(),
//
//                    "Couldn't remove product: "
//                            + entry.getKey()
//            );
//        }
//
//        softAssert.assertTrue(
//                homePage.isCartEmpty());

        softAssert.assertAll();
    }
}