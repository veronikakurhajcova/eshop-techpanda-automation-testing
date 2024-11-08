package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TvMenuPage {
	
	public WebDriver driver;
	
	@FindBy(xpath="(//a[text()='Add to Wishlist'])[1]")
	private WebElement lgLcdProduct;	
	
	public TvMenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Adds the LG LCD product to the wishlist.
    
    public boolean addLgLcdProductToWishlist() {
        if (lgLcdProduct.isDisplayed()) {
            lgLcdProduct.click();
            return true;
        } else {
            System.out.println("LG LCD Product is not visible.");
            return false;
        }
    }
    
    // Checks if the LG LCD product is displayed on the page.
   
    public boolean isLgLcdProductDisplayed() {
        return lgLcdProduct.isDisplayed();
    }
}
