package org.techpanda.live.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCustomerPage {
	public WebDriver driver;
	
	@FindBy(xpath="//h1[text()='My Dashboard']")
	private WebElement accountDashboardHeading;
	
	@FindBy(xpath="//li[contains(@class,'success-msg')]//span")
	private WebElement successMessage;
	
	@FindBy(xpath="//a[text()='TV']")
	private WebElement tvMenu;
	
	@FindBy(linkText = "MY WISHLIST")
	private WebElement myWishlistLink;
	
	@FindBy(linkText="MY ORDERS")
	private WebElement myOrdersLink;
	
	@FindBy(xpath="//*[@id=\"header\"]/div/div[2]/div/div/a/span[2]")
	private WebElement cartElement;
	
	@FindBy(xpath="//*[@id=\"cart-sidebar\"]/li/div/a[2]")
	private WebElement removeItemLink;
	
	public AccountCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Clear Shopping Cart
	public void clearCart() {
		cartElement.click();
		removeItemLink.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	//Verify registration
	public boolean isRegistrationSuccessful() {
		return driver.getCurrentUrl().contains("customer/account/index");
	}
	
	public boolean successRegisterMessageIsDisplayed() {
		return successMessage.isDisplayed();
	}
	
	public boolean isAccountDashboardHeadingDisplayed() {
		return accountDashboardHeading.isDisplayed();
	}
	
	//Navigate to my Orders
    public AccountOrderHistoryPage navigateToMyOrders() {
        myOrdersLink.click();
        return new AccountOrderHistoryPage(driver);
    }
	
   //Click my Wishlist
    public WishlistPage clickMyWishlistLink() {
        myWishlistLink.click();
        return new WishlistPage(driver);
    }
	//Click TV Menu
    public TvMenuPage clickOnTvMenu() {
        tvMenu.click();
        return new TvMenuPage(driver);
    }
	
    
    

}
