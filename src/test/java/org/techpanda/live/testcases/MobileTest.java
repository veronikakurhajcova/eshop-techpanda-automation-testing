package org.techpanda.live.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.techpanda.live.base.Base;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.MobilePage;
import org.techpanda.live.pages.ProductComparePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MobileTest extends Base {
	public WebDriver driver;
	HomePage homePage;
	MobilePage mobilePage;
	ProductComparePage productComparePage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		mobilePage = new MobilePage(driver);
		productComparePage = new ProductComparePage(driver);
	}

	@Test(priority = 1)
	public void verifyTitleOfThePage() {
		homePage.clickOnMobileLink();
		Assert.assertEquals(mobilePage.getMobilePageHeading(), dataProp.getProperty("expectedMobileHeading"),
				"MobilePage heading did not match");
	}

	@Test(priority = 2)
	public void verifyMobileProductsAreSortedAlphabetically() {
		homePage.clickOnMobileLink();
		mobilePage.selectProductsByName();
		List<String> productNames = mobilePage.getProductsNames();
		List<String> sortedProductNames = new ArrayList<>(productNames);
		Collections.sort(sortedProductNames);

		Assert.assertEquals(productNames, sortedProductNames, "Products are not sorted alphabetically!");
	}

	@Test(priority = 3)
	public void addToCompareTwoMobiles() {
		homePage.clickOnMobileLink();
		mobilePage.clickAddToCompareSonyXperia();
		mobilePage.clickAddToCompareIphone();
		Assert.assertTrue(mobilePage.verifyAddXperiaToCompare(),"Sony Xperia was not added to comparison");
		Assert.assertTrue(mobilePage.verifyAddIphoneToCompare(),"iPhone was not added to comparison");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
