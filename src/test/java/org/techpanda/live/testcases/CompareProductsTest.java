package org.techpanda.live.testcases;

import org.openqa.selenium.WebDriver;
import org.techpanda.live.base.Base;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.MobilePage;
import org.techpanda.live.pages.ProductComparePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompareProductsTest extends Base {
	
	public WebDriver driver;
	HomePage homePage;
	MobilePage mobilePage;
	ProductComparePage productComparePage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		mobilePage = new MobilePage( driver);
		productComparePage = new ProductComparePage(driver);
		
	}
	
	@Test(priority = 1)
	public void verifyCompareTwoProducts() {
		homePage.clickOnMobileLink();
	
		mobilePage.clickAddToCompareSonyXperia();
		mobilePage.clickAddToCompareIphone();
		
		mobilePage.clickOnCompareButton();
		mobilePage.openPopUpCompareWindow();
		Assert.assertEquals(productComparePage.getCompareProductsHeadingText(), dataProp.getProperty("titleHeading"),
				"Title compare products is different");
		Assert.assertTrue(productComparePage.isSonyXperiaDisplayed());
		Assert.assertTrue(productComparePage.isIphoneDisplayed());

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
