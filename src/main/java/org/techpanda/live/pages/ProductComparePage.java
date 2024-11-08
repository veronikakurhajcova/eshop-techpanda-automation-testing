package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductComparePage {
	public WebDriver driver;
	
	@FindBy(xpath="//h1[text()='Compare Products']")
	private WebElement compareProductsHeading;
	
	@FindBy(xpath="//a[@title='Sony Xperia']")
	private WebElement sonyXperiaElement;
	
	@FindBy(xpath="//a[@title='IPhone']")
	private WebElement iphoneElement;
	
	public ProductComparePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

   // Verifies if the compare products heading is displayed.
     
    public boolean isCompareProductsHeadingDisplayed() {
        return compareProductsHeading.isDisplayed();
    }

   // Returns the text of the compare products heading.
    
    public String getCompareProductsHeadingText() {
        return compareProductsHeading.getText();
    }
	
   // Checks if the Sony Xperia element is displayed.
    
    public boolean isSonyXperiaDisplayed() {
        return sonyXperiaElement.isDisplayed();
    }
	
   // Checks if the iPhone element is displayed.
    
    public boolean isIphoneDisplayed() {
        return iphoneElement.isDisplayed();
    }
	
	

}
