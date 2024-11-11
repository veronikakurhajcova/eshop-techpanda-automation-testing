package org.techpanda.live.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.techpanda.live.base.Base;
import org.techpanda.live.listeners.MyListener;
import org.techpanda.live.pages.AccountCreatePage;
import org.techpanda.live.pages.AccountCustomerPage;
import org.techpanda.live.pages.AccountLoginPage;
import org.techpanda.live.pages.AccountOrderHistoryPage;
import org.techpanda.live.pages.AccountViewOrderPage;
import org.techpanda.live.pages.CheckoutCartPage;
import org.techpanda.live.pages.CheckoutOnePagePage;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.OrderPrintPage;
import org.techpanda.live.pages.TvMenuPage;
import org.techpanda.live.pages.WishlistPage;
import org.techpanda.live.pages.WishlistSharePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountTest extends Base {
	public WebDriver driver;
	HomePage homePage;
	AccountLoginPage accountLoginPage;
	AccountCreatePage accountCreatePage;
	AccountCustomerPage accountCustomerPage;
	TvMenuPage tvMenuPage;
	WishlistPage wishlistPage;
	WishlistSharePage wishlistSharePage;
	CheckoutCartPage checkoutCartPage;
	CheckoutOnePagePage checkoutOnePagePage;
	AccountOrderHistoryPage accountOrderHistoryPage;
	AccountViewOrderPage accountViewOrderPage;
	OrderPrintPage orderPrintPage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		accountLoginPage = new AccountLoginPage(driver);
		accountCreatePage = new AccountCreatePage(driver);
		accountCustomerPage = new AccountCustomerPage(driver);
		tvMenuPage = new TvMenuPage(driver);
		wishlistPage = new WishlistPage(driver);
		wishlistSharePage = new WishlistSharePage(driver);
		checkoutCartPage = new CheckoutCartPage(driver);
		checkoutOnePagePage = new CheckoutOnePagePage(driver);
		accountOrderHistoryPage = new AccountOrderHistoryPage(driver);
		accountViewOrderPage = new AccountViewOrderPage(driver);
		orderPrintPage = new OrderPrintPage(driver);
	}

	@Test(priority = 1)
	public void verifyCreateAccount() {
		homePage.navigateToMyAccount();
		Assert.assertTrue(accountLoginPage.isLoginOrCreateAccountHeadingDisplayed(), "Heading is not display");
	}

	@Test(priority = 2)
	public void verifyRegistrationAccount() {
		homePage.navigateToMyAccount();
		accountLoginPage.clickOnCreateAccount();
		accountCreatePage.registerAccount(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"),
				
				dataProp.getProperty("password"), dataProp.getProperty("confirmPassword"));
	
		Assert.assertTrue(accountCustomerPage.isRegistrationSuccessful(), "Registration was not successful");
		Assert.assertTrue(accountCustomerPage.successRegisterMessageIsDisplayed(),
				"Success registered message is not displayed");
		Assert.assertTrue(accountCustomerPage.isAccountDashboardHeadingDisplayed(),
				"Account Dashboard heading is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginToAccount() {
		homePage.navigateToMyAccount();
		accountLoginPage.login(dataProp.getProperty("emailAddress"), dataProp.getProperty("password"));
		Assert.assertTrue(accountCustomerPage.isAccountDashboardHeadingDisplayed(), "Dashboard heading is not displayed");
	}

	@Test(priority = 4) 
	public void verifyAddProductToWishlist() {
		homePage.navigateToMyAccount();
		accountLoginPage.login(dataProp.getProperty("emailAddress"), dataProp.getProperty("password"));
		accountCustomerPage.clickOnTvMenu();
		boolean isProductAdded = tvMenuPage.addLgLcdProductToWishlist();
		Assert.assertTrue(isProductAdded, "Product was not added to the wishlist");
	}

	@Test(priority = 5)
	public void verifyShareWishlist() {
		homePage.navigateToMyAccount();
		accountLoginPage.login(dataProp.getProperty("emailAddress"), dataProp.getProperty("password"));
		accountCustomerPage.clickOnTvMenu();
		tvMenuPage.addLgLcdProductToWishlist();
		wishlistPage.clickShareWishlist();
		wishlistSharePage.sharingWishlistInformation(dataProp.getProperty("emailAddress"),
				dataProp.getProperty("wishlistMessage"));
		Assert.assertTrue(wishlistPage.isWishlistSuccessMessageDisplayed(),
				"Wishlist success message is not displayed");
	}

	@Test(priority = 6)
	public void verifyUserCanPurchaseProductWithRegisteredEmail() throws InterruptedException {
		homePage.navigateToMyAccount();
		accountLoginPage.login(dataProp.getProperty("emailAddress"), dataProp.getProperty("password"));
		accountCustomerPage.clearCart();
		accountCustomerPage.clickOnTvMenu();
		tvMenuPage.addLgLcdProductToWishlist();
		wishlistPage.clickShareWishlist();
		wishlistSharePage.sharingWishlistInformation(dataProp.getProperty("emailAddress"),
				dataProp.getProperty("wishlistMessage"));
		accountCustomerPage.clickMyWishlistLink();
		wishlistPage.clickAddToCart();
		Assert.assertTrue(checkoutCartPage.isSuccessMessageDisplayed(),
				"Success message added product to shopping cart is not displayed");

		checkoutCartPage.clickProceedToCheckoutButton();
	
		checkoutOnePagePage.fillBillingInformation(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"),
				dataProp.getProperty("address"), dataProp.getProperty("city"), dataProp.getProperty("state"),
				dataProp.getProperty("zip"), dataProp.getProperty("country"), dataProp.getProperty("telephone"));

		Assert.assertTrue(checkoutOnePagePage.shippingPriceIsDisplayed(), "Shipping price is not displayed");
		checkoutOnePagePage.highlightedShippingArea();
		Assert.assertEquals(checkoutOnePagePage.getShippingPrice(), dataProp.getProperty("shippingPrice"),
				"Shipping price is not equals");
		 for (String handle : driver.getWindowHandles()) {  
		    	driver.switchTo().window(handle);
		    	}  
		    Thread.sleep(2000);
		checkoutOnePagePage.clickShippingMethodContinueButton();
		checkoutOnePagePage.clickPaymentCheckMoneyOrder();
		checkoutOnePagePage.clickPaymentContinueButton();

		Assert.assertTrue(checkoutOnePagePage.isGrandTotalPriceDisplayed());
		assertEquals(checkoutOnePagePage.getGrandTotalPrice(), dataProp.getProperty("totalPrice"),
				"Grand total price is not equal");
		checkoutOnePagePage.clickPlaceOrderButton();
		Assert.assertTrue(checkoutOnePagePage.isSuccessOrderMessageDisplayed());
		checkoutOnePagePage.clickContinueShoppingButton();
		  
	}

//	@Test(priority = 7)
//	public void verifySavePreviouslyOrderAsPdfFile() {
//		homePage.navigateToMyAccount();
//		accountLoginPage.login(dataProp.getProperty("emailAddress"), dataProp.getProperty("password"));
//		accountPage.navigateToMyOrders();
//		accountOrderHistoryPage.clickViewOrder();
//		Assert.assertTrue(accountViewOrderPage.isOrderNumberDisplayed(), "Number of order is not displayed");
//		accountViewOrderPage.clickPrintOrder();
//		accountViewOrderPage.cancelPrintDialog();
//	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
