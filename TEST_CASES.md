# Swag Labs Test Cases

**Application:** [https://www.saucedemo.com/](https://www.saucedemo.com/)  
**Primary test user:** `standard_user`  
**Password:** `secret_sauce`

This document lists manual test cases for four main flows. Each test case has a matching automated test in the `src/test/java/com/swaglabs/tests/` folder.

---

## 1. Login Flow

| ID | Test Case | Steps | Expected Result | Automated Test |
|----|-----------|-------|-----------------|----------------|
| TC-L01 | Login page shows input fields | 1. Open saucedemo.com | Username and password fields are visible | `LoginTests.testLoginPageShowsFields` |
| TC-L02 | Valid login with standard_user | 1. Open saucedemo.com<br>2. Enter `standard_user`<br>3. Enter `secret_sauce`<br>4. Click Login | User lands on Products page (`inventory.html`) | `LoginTests.testValidLogin` |
| TC-L03 | Valid login (data-driven) | Same as TC-L02 using CSV row 1 | Login succeeds, URL contains `inventory.html` | `LoginTests.testLoginWithData` (row 1) |
| TC-L04 | Wrong password | 1. Enter `standard_user`<br>2. Enter wrong password<br>3. Click Login | Error shown, user stays on login page | `LoginTests.testLoginWithData` (row 2) |
| TC-L05 | Empty username | 1. Leave username empty<br>2. Enter password<br>3. Click Login | Error shown, user stays on login page | `LoginTests.testLoginWithData` (row 3) |
| TC-L06 | Empty password | 1. Enter username<br>2. Leave password empty<br>3. Click Login | Error shown, user stays on login page | `LoginTests.testLoginWithData` (row 4) |

**Test data file:** `src/main/resources/login-data.csv`

---

## 2. Product Page Flow

| ID | Test Case | Steps | Expected Result | Automated Test |
|----|-----------|-------|-----------------|----------------|
| TC-P01 | Products page title | 1. Login with valid user | Page title text is **Products** | `ProductTests.testProductsPageTitle` |
| TC-P02 | Product count | 1. Login with valid user | 6 products are displayed | `ProductTests.testProductCount` |
| TC-P03 | Product names and prices | 1. Login with valid user | All products show name and price | `ProductTests.testProductNamesAndPricesVisible` |
| TC-P04 | Sort by price (low to high) | 1. Login<br>2. Select "Price (low to high)" from sort dropdown | First product is **Sauce Labs Onesie** (cheapest) | `ProductTests.testSortByPriceLowToHigh` |
| TC-P05 | Sort by name (A to Z) | 1. Login<br>2. Select "Name (A to Z)" from sort dropdown | First product is **Sauce Labs Backpack** | `ProductTests.testSortByNameAtoZ` |
| TC-P06 | Menu button visible | 1. Login with valid user | Hamburger menu button is visible | `ProductTests.testMenuButtonVisible` |
| TC-P07 | Products page URL | 1. Login with valid user | URL contains `inventory.html` | `ProductTests.testProductsPageUrl` |

---

## 3. Add to Cart Flow

| ID | Test Case | Steps | Expected Result | Automated Test |
|----|-----------|-------|-----------------|----------------|
| TC-C01 | Add one product | 1. Login<br>2. Click "Add to cart" on Backpack | Cart badge shows **1** | `CartTests.testAddOneProductToCart` |
| TC-C02 | Add two products | 1. Login<br>2. Add Backpack and Bike Light to cart | Cart badge shows **2** | `CartTests.testAddTwoProductsToCart` |
| TC-C03 | Remove from products page | 1. Login<br>2. Add Backpack<br>3. Click "Remove" on Backpack | Cart badge disappears | `CartTests.testRemoveProductFromProductsPage` |
| TC-C04 | Cart page shows items | 1. Login<br>2. Add Backpack<br>3. Click cart icon | Cart page title is **Your Cart**, 1 item shown | `CartTests.testCartPageShowsProduct` |
| TC-C05 | Remove from cart page | 1. Login<br>2. Add Backpack<br>3. Open cart<br>4. Click Remove | Cart has 0 items | `CartTests.testRemoveProductFromCartPage` |
| TC-C06 | Continue shopping | 1. Login<br>2. Add product<br>3. Open cart<br>4. Click Continue Shopping | Returns to Products page | `CartTests.testContinueShopping` |

---

## 4. Checkout Flow

| ID | Test Case | Steps | Expected Result | Automated Test |
|----|-----------|-------|-----------------|----------------|
| TC-CH01 | Open checkout step one | 1. Login<br>2. Add product to cart<br>3. Open cart<br>4. Click Checkout | URL contains `checkout-step-one` | `CheckoutTests.testCheckoutStepOneOpens` |
| TC-CH02 | Complete order | 1. Go to checkout<br>2. Enter First Name, Last Name, Postal Code<br>3. Click Continue<br>4. Click Finish | Message: **Thank you for your order!** | `CheckoutTests.testCompleteCheckout` |
| TC-CH03 | Back to products after order | 1. Complete order (TC-CH02)<br>2. Click Back Home | Products page is displayed | `CheckoutTests.testBackHomeAfterCheckout` |
| TC-CH04 | Valid checkout (data-driven) | Fill all fields from CSV row 1, Continue, Finish | Thank you message appears | `CheckoutTests.testCheckoutWithData` (row 1) |
| TC-CH05 | Missing first name | Leave First Name empty, click Continue | Error: First Name is required | `CheckoutTests.testCheckoutWithData` (row 2) |
| TC-CH06 | Missing last name | Leave Last Name empty, click Continue | Error: Last Name is required | `CheckoutTests.testCheckoutWithData` (row 3) |
| TC-CH07 | Missing postal code | Leave Postal Code empty, click Continue | Error: Postal Code is required | `CheckoutTests.testCheckoutWithData` (row 4) |
| TC-CH08 | Checkout overview title | 1. Fill valid checkout info<br>2. Click Continue | Title is **Checkout: Overview** | `CheckoutTests.testCheckoutOverviewTitle` |
| TC-CH09 | Checkout URL | 1. Add product and click Checkout | URL contains `checkout-step-one` | `CheckoutTests.testCheckoutUrl` |

**Test data file:** `src/main/resources/checkout-data.csv`

---

## How to Run Automated Tests

1. Install **Google Chrome** browser.
2. Open terminal in the project folder.
3. Run:

```bash
mvn clean test
```

Tests use **Page Object Model**, **TestNG**, and **CSV data-driven** inputs from the `resources` folder.

---

## Project Structure

```
src/main/java/com/swaglabs/
├── drivers/       → DriverManager (opens/closes Chrome)
├── pages/         → LoginPage, ProductsPage, CartPage, CheckoutPage
└── utils/         → BrowserActions, ElementActions, Waits, Scrolling, DataReader

src/test/java/com/swaglabs/tests/
├── BaseTest.java
├── LoginTests.java
├── ProductTests.java
├── CartTests.java
└── CheckoutTests.java

src/main/resources/
├── login-data.csv
└── checkout-data.csv
```
