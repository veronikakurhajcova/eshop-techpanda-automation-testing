package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'Mobile')]")
	private WebElement mobileLink;
	
	@FindBy(xpath="(//a[@title='My Account'])[2]")
	private WebElement myAccountLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Navigate to my Account
    public AccountCreatePage navigateToMyAccount() {
        myAccountLink.click();
        return new AccountCreatePage(driver);
    }

    //Title of the HomePage
    public String getTitle() {
        return driver.getTitle();
    }

     // Clicks on the 'Mobile' link and navigates to the MobilePage.
    public MobilePage clickOnMobileLink() {
        mobileLink.click();
        return new MobilePage(driver);
    }

}
