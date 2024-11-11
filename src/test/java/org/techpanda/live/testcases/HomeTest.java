package org.techpanda.live.testcases;

import org.openqa.selenium.WebDriver;
import org.techpanda.live.base.Base;
import org.techpanda.live.pages.HomePage;
import org.techpanda.live.pages.MobilePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends Base {
	public WebDriver driver;
	HomePage homePage;
	MobilePage mobilePage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenUrl(dataProp.getProperty("browserName"));
		homePage = new HomePage(driver);
		mobilePage = new MobilePage(driver);
	}

	@Test
	public void verifyHomePageTitle() {
		Assert.assertEquals(homePage.getTitle(), dataProp.getProperty("expectedHomePageTitle"),
				"HomePage title did not match");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
