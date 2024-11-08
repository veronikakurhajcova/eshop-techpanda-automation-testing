package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistPage {
	
	public WebDriver driver;
	
	@FindBy(xpath="//button[@title='Share Wishlist']")
	private WebElement shareWishlistButton;
	
	@FindBy(xpath="//li/span[text()='Your Wishlist has been shared.']")
	private WebElement shareSuccessMessage;
	
	@FindBy(xpath = "//button//span[text()='Add to Cart']")
	private WebElement addToCartButton;
	
	public WishlistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Clicks the Add to Cart button and returns the CheckoutCartPage.
    
    public CheckoutCartPage clickAddToCart() {
        if (addToCartButton.isDisplayed()) {
            addToCartButton.click();
            return new CheckoutCartPage(driver);
        } else {
            throw new RuntimeException("Add to Cart button is not visible.");
        }
    }
	// Retrieves the success message after sharing the wishlist.
     
    public String shareSuccessMessageText() {
        return shareSuccessMessage.getText();
    }
    
	// Checks if the success message is displayed.
   
    public boolean isWishlistSuccessMessageDisplayed() {
        return shareSuccessMessage.isDisplayed();
    }
	
   //Clicks the Share Wishlist button.
    
    public void clickShareWishlist() {
        if (shareWishlistButton.isDisplayed()) {
            shareWishlistButton.click();
        } else {
            throw new RuntimeException("Share Wishlist button is not visible.");
        }
    }

}
