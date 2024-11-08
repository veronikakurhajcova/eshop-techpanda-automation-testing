package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCartPage {
	public WebDriver driver;
	
	//Shopping Cart Elements
	
	@FindBy(xpath="//input[@title='Qty']")
	private WebElement cartQuantityField;
	
	@FindBy(xpath="//button[@title='Update']")
	private WebElement updateButton;

	@FindBy(xpath="//p[contains(@class,'item-msg error')]")
	private WebElement errorMaximumQuantityMessage;
	
	@FindBy(xpath="//button[@value='empty_cart']")
	private WebElement emptyCartButton;
	
	@FindBy(xpath="//h1[text()='Shopping Cart is Empty']")
	private WebElement emptyCartHeading;
	
	@FindBy(xpath="//li[contains(@class,'success-msg')]//span[text()='LG LCD was added to your shopping cart.']")
	private WebElement successMessageAddedProductToShoppingCart;
	
	
	// Procees to Checkout
	
	@FindBy(xpath="(//button[@title='Proceed to Checkout'])[1]")
	private WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//td/span[contains(@class,'price')][text()='$5.00']")
	private WebElement shippingPriceNumber;
	
	@FindBy(xpath="//span[contains(@class,'price')][text()='$620.00']")
	private WebElement totalPrice;
	
	@FindBy(xpath="(//button[@title='Proceed to Checkout'])[2]")
	private WebElement proceedToCheckoutTotalButton;
	
	@FindBy(xpath="//h2/a[contains(text(), 'Sony Xperia')]")
	private WebElement sonyXperia;
	
	@FindBy(xpath="(//a[text()='Remove Item'])[2]")
	private WebElement deleteCartBtn;
	
	
	public CheckoutCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public CheckoutOnePagePage clickProceedToCheckoutTotalButton() {
		proceedToCheckoutTotalButton.click();
		return new CheckoutOnePagePage(driver);
	}
	
	public WebElement getErrorQuantityMessageElement() {
	    return errorMaximumQuantityMessage; 
	}
	
	//Shopping cart Methods
	
	public void deleteShoppingCart() {
		deleteCartBtn.click();
	}
	
	public boolean isProductInCart() {
		return sonyXperia.isDisplayed();
    }
	
	// Clicks the empty cart button to clear the cart.
    
    public void clickOnEmptyCartButton() {
        emptyCartButton.click();
    }
	
   // Checks if the shipping price is displayed.
     
    public boolean isShippingPriceDisplayed() {
        return shippingPriceNumber.isDisplayed();
    }
	
	// Clicks the proceed to checkout button and navigates to the CheckoutOnePagePage.
    
    public CheckoutOnePagePage clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
        return new CheckoutOnePagePage(driver);
    }
	
    // Checks if the success message for added product is displayed.
    
    public boolean isSuccessMessageDisplayed() {
        return successMessageAddedProductToShoppingCart.isDisplayed();
    }
	
    // Retrieves the heading text when the shopping cart is empty.
    
    public String getShoppingCartEmptyHeading() {
        return emptyCartHeading.getText();
    }
    
   // Retrieves the error message when the quantity exceeds the limit.
    
    public String getActualErrorQuantityMessage() {
        return errorMaximumQuantityMessage.getText();
    }
    
    // Sets the quantity in the cart.
    
    public void addQuantity(String quantity) {
        cartQuantityField.sendKeys(quantity);
    }
	
   // Clicks the update button to refresh the cart with the new quantity.
     
    public void updateQuantity() {
        updateButton.click();
    }

}
