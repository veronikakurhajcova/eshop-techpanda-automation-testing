package org.techpanda.live.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOnePagePage {
	public WebDriver driver;

	//Billing informations elements
	
	@FindBy(id = "billing:firstname")
	private WebElement bFirstname;

	@FindBy(id = "billing:lastname")
	private WebElement bLastname;

	@FindBy(id = "billing:street1")
	private WebElement bAddress;

	@FindBy(id = "billing:city")
	private WebElement bCity;

	@FindBy(id = "billing:postcode")
	private WebElement bZipCode;

	@FindBy(xpath = "//select[@id='billing:region_id']")
	private WebElement bState;

	@FindBy(id = "billing:country_id")
	private WebElement bCountry;

	@FindBy(id = "billing:telephone")
	private WebElement bTelephone;

	@FindBy(xpath = ".//*[@id='billing-buttons-container']/button")
	private WebElement billingContinueButton;
	
	// Shipping informations elements
	
	@FindBy(id="shipping:firstname")
	private WebElement sFirstname;
	
	@FindBy(id="shipping:lastname")
	private WebElement sLastname;
	
	@FindBy(id="shipping:street1")
	private WebElement sAddress;
	
	@FindBy(id="shipping:city")
	private WebElement sCity;
	
	@FindBy(id="shipping:region_id")
	private WebElement sState;
	
	@FindBy(id="shipping:postcode")
	private WebElement sZipCode;
	
	@FindBy(id="shipping:country_id")
	private WebElement sCountry;
	
	@FindBy(id="shipping:telephone")
	private WebElement sTelephone;

	// Shipping method elements
	@FindBy(xpath="//div[contains(@class,'main-container')]")
	private WebElement shippingMethodArea;
	
	@FindBy(xpath = "//*[@id=\"checkout-shipping-method-load\"]//span[contains(@class,'price')]")
	private WebElement shippingPriceElement;

	@FindBy(xpath = "//a/span[text()='Account']")
	private WebElement accountLink;

	@FindBy(xpath = "//*[@id=\"header-account\"]/div/ul/li[3]/a")
	private WebElement myCartItemLink;

	// Payments elements
	@FindBy(xpath = "//*[@id=\"dt_method_checkmo\"]/label")
	private WebElement paymentCheckMoneyOrder;

	@FindBy(xpath = "//*[@id=\"payment-buttons-container\"]/button/span/span")
	private WebElement paymentContinueButton;

	@FindBy(xpath = "//button[@title='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//*[@id=\"top\"]/body/div/div/div[2]/div/div/p[1]")
	private WebElement orderMessage;

	@FindBy(xpath="//*[@id=\"co-billing-form\"]//label[contains(text(), 'Ship to different address')]")
	private WebElement differentAddress;
	
	@FindBy(xpath="//*[@id='billing-buttons-container']//span[contains(text(), 'Continue')]")
	private WebElement addressContinueButton;
	
	@FindBy(id="billing-address-select")
	private WebElement drowpdownSelectNewAddress;
	
	@FindBy(id="shipping-address-select")
	private WebElement dropdownShippingAddress;
	
	@FindBy(id="shipping:same_as_billing")
	private WebElement shippingSameBillingAddress;
	
	@FindBy(xpath="//*[@id='shipping-buttons-container']//span[contains(text(), 'Continue')]")
	private WebElement shippingContinueButton;
	
	@FindBy(xpath=".//*[@id='shipping-method-buttons-container']/button")
	private WebElement shippingMethodContinueButton;

	@FindBy(xpath="//*[@id=\"checkout-review-table\"]//span[contains(text(),'$620.00')]")
	private WebElement grandTotalPriceElement;
	
	@FindBy(xpath="//h1[text()='Your order has been received.']")
	private WebElement successOrderMessage;
	
	@FindBy(xpath="//*[@id=\"top\"]//span[contains(text(),'Continue Shopping')]")
	private WebElement continueShoppingButton;

	
	public CheckoutOnePagePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void scrollToButton(WebDriver driver, WebElement buttonElement) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    
	    // Posuòte stránku na tlaèidlo
	    js.executeScript("arguments[0].scrollIntoView(true);", shippingMethodContinueButton);
	}
	
	public void highlightedShippingArea() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollBy(0,-500)");
		highlightElement(driver, shippingMethodArea);
	}
	
	public void clickShippingAddress() {
		shippingSameBillingAddress.click();
	}
	public void clickNewAddress() {
		Select select = new Select(drowpdownSelectNewAddress);
		select.selectByVisibleText("New Address");
	}
	
	public WebElement shippingPriceElement() {
		return shippingPriceElement;
	}
	
	
	public void clickContinue() {
		addressContinueButton.click();
	}
	
	public AccountCustomerPage clickContinueShoppingButton() {
		continueShoppingButton.click();
		return new AccountCustomerPage(driver);
	}

	/**
	 * Clicks the billing continue button.
	 */
	public void clickBillingContinueButton() {
		scrollToButton(driver, billingContinueButton);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		billingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton));
		billingContinueButton.click();
	}

	/**
	 * Verifies if the order message is displayed.
	 * 
	 * @return true if displayed, false otherwise.
	 */
	public boolean isOrderMessageDisplayed() {
		return orderMessage.isDisplayed();
	}

	/**
	 * Clicks the place order button.
	 */
	public void clickPlaceOrderButton() {
		placeOrderButton.click();
	}

	/**
	 * Clicks the payment continue button.
	 */
	public void clickPaymentContinueButton() {
		paymentContinueButton.click();
	}

	/**
	 * Clicks the payment check money order option.
	 */
	public void clickPaymentCheckMoneyOrder() {
		scrollToButton(driver, paymentCheckMoneyOrder);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		shippingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(paymentCheckMoneyOrder));
		paymentCheckMoneyOrder.click();
	}

	/**
	 * Clicks the shipping continue button.
	 */
	public void clickShippingMethodContinueButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		shippingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueButton));
		scrollToButton(driver, shippingMethodContinueButton);
		shippingMethodContinueButton.click();
	}

	/**
	 * Navigates to the shopping cart page.
	 * 
	 * @return an instance of CheckoutCartPage.
	 */
	public CheckoutCartPage navigateToMyCart() {
		accountLink.click();
		myCartItemLink.click();
		return new CheckoutCartPage(driver);
	}

	/**
	 * Retrieves the generated shipping price as a string.
	 * 
	 * @return the shipping price text.
	 */
	public String getShippingPrice() {
		return shippingPriceElement.getText();
	}
    
	public String getGrandTotalPrice() {
		return grandTotalPriceElement.getText();
	}
	
    public boolean isGrandTotalPriceDisplayed() {
    	return grandTotalPriceElement.isDisplayed();
    }
    
    public boolean isSuccessOrderMessageDisplayed() {
    	return successOrderMessage.isDisplayed();
    }

	/**
	 * Checks if the generated shipping cost is displayed.
	 * 
	 * @return true if displayed, false otherwise.
	 */

	public boolean shippingPriceIsDisplayed() {
		return shippingPriceElement.isDisplayed();
	}

	/**
	 * Selects a country from the dropdown.
	 * 
	 * @param value the visible text of the country to select.
	 */
	public void selectCountry(String value) {
		Select select = new Select(bCountry);
		select.selectByVisibleText(value);
	}

	/**
	 * Selects a state from the dropdown.
	 * 
	 * @param value the visible text of the state to select.
	 */
	public void selectState(String value) {
		Select select = new Select(bState);
		select.selectByVisibleText(value);
	}

	/**
	 * Fills in the billing information and clicks the continue button.
	 * 
	 * @param firstname the first name of the customer.
	 * @param lastname  the last name of the customer.
	 * @param address   the address of the customer.
	 * @param city      the city of the customer.
	 * @param state     the state of the customer.
	 * @param zipCode   the zip code of the customer.
	 * @param country   the country of the customer.
	 * @param telephone the telephone number of the customer.
	 */
	public void fillBillingInformation(String firstname, String lastname, String address, String city, String state,
			String zipCode, String country, String telephone) {
		clickNewAddress();
		clickContinue();
		bFirstname.clear();
		bFirstname.sendKeys(firstname);
		bLastname.clear();
		bLastname.sendKeys(lastname);
		bAddress.clear();
		bAddress.sendKeys(address);
		bCity.clear();
		bCity.sendKeys(city);
		selectState(state);
		bZipCode.clear();
		bZipCode.sendKeys(zipCode);
		selectCountry(country);
		//bTelephone.clear();
		bTelephone.sendKeys(telephone);
		billingContinueButton.click();
	}
	

	public void fillShippingInformation(String firstname, String lastname, String address, String city, String state,
			String zipCode, String country, String telephone) {
		clickShippingAddress();
		sFirstname.clear();
		sFirstname.sendKeys(firstname);
		sLastname.clear();
		sLastname.sendKeys(lastname);
		sAddress.clear();
		sAddress.sendKeys(address);
		sCity.clear();
		sCity.sendKeys(city);
		selectState(state);
		sZipCode.clear();
		sZipCode.sendKeys(zipCode);
		selectCountry(country);
		sTelephone.clear();
		sTelephone.sendKeys(telephone);
		shippingContinueButton.click();
	}
	
	// Metóda na zvýraznenie prvku
		public void highlightElement(WebDriver driver, WebElement element) {
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].style.border='3px solid red'", element);
		
		}
			
}
