package org.techpanda.live.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.techpanda.live.base.Base;
import org.techpanda.live.pages.CheckoutCartPage;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.MobilePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends Base {

	public WebDriver driver;
	HomePage homePage;
	MobilePage mobilePage;
	CheckoutCartPage checkoutCartPage;
    public WebElement failedElement;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		mobilePage = new MobilePage(driver);
		checkoutCartPage = new CheckoutCartPage(driver);

	}

	@Test(priority=1)
	public void addSonyXperiaToCart() {
		homePage.clickOnMobileLink();
		mobilePage.addToCartSonyXperia();
        Assert.assertTrue(checkoutCartPage.isProductInCart(), "Sony Xperia not found in cart");
	}
	
	@Test(priority=2)
	public void updateQuantitySonyXperia() {
		homePage.clickOnMobileLink();
		mobilePage.addToCartSonyXperia();
		checkoutCartPage.addQuantity(dataProp.getProperty("sonyXperiaQuantity"));
		checkoutCartPage.updateQuantity();

	    failedElement = checkoutCartPage.getErrorQuantityMessageElement(); 
	   
		Assert.assertEquals(checkoutCartPage.getActualErrorQuantityMessage(), dataProp.getProperty("expectedErrorQuantityMessage"),
				"Error Quantity Messages are not equals");
	}
	
	@Test(priority=3)
	public void verifyEmptyShoppingCart() {
		homePage.clickOnMobileLink();
		mobilePage.addToCartSonyXperia();
		checkoutCartPage.addQuantity(dataProp.getProperty("sonyXperiaQuantity"));
		checkoutCartPage.updateQuantity();
		checkoutCartPage.clickOnEmptyCartButton();
		Assert.assertEquals(checkoutCartPage.getShoppingCartEmptyHeading(), dataProp.getProperty("expectedShoppingCartEmptyMessage"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
