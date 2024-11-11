package org.techpanda.live.testcases;

import org.openqa.selenium.WebDriver;
import org.techpanda.live.base.Base;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.MobilePage;
import org.techpanda.live.pages.SonyXperiaPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SonyXperiaTest extends Base {
	WebDriver driver;
	HomePage homePage;
	MobilePage mobilePage;
	SonyXperiaPage sonyXperiaPage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		mobilePage = new MobilePage(driver);
		sonyXperiaPage = new SonyXperiaPage(driver);
	}

	@Test(priority=1)
	public void verifyCostOfProduct() {
		homePage.clickOnMobileLink();
		mobilePage.clickOnProductSonyXperia();
		Assert.assertEquals(mobilePage.productSonyXperiaPrice(),sonyXperiaPage.getPriceSonyXperia(),"The price of the Sony Xperia does not match. Expected: "  + mobilePage.productSonyXperiaPrice());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
