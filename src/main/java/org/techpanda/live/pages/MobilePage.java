package org.techpanda.live.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobilePage {
	WebDriver driver;

	@FindBy(xpath = "//h1[text()='Mobile']")
	private WebElement mobilePageHeading;

	@FindBy(xpath = "//select[@title='Sort By']")
	private WebElement dropDownSelectByName;

	@FindBy(xpath = "//h2[contains(@class,'product-name')]")
	private List<WebElement> products;
	
	@FindBy(xpath="//*[@id='product-price-1']")
	private WebElement priceValueProductSony;
	
	@FindBy(xpath="//*[@id='product-collection-image-1']")
	private WebElement productSonyXperiaImage;
	
	@FindBy(xpath="(//button[@title='Add to Cart'])[2]")
	private WebElement addToCartSonyXperiaButton;
	
	@FindBy(xpath="(//a[contains(@class,'link-compare')])[2]")
	private WebElement addToCompareSonyXperia;
	
	@FindBy(xpath="(//a[contains(@class,'link-compare')])[3]")
	private WebElement addtoCompareIphone;
	
	@FindBy(xpath="//p[contains(@class,'product-name')]//a[text()='Sony Xperia']")
	private WebElement sonyXperiaInCompareProducts;
	
	@FindBy(xpath="//p[contains(@class,'product-name')]//a[text()='IPhone']")
	private WebElement iphoneInCompareProducts;

	@FindBy(xpath="//button[@title='Compare']")
	private WebElement compareButton;
	
	public MobilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Opens the comparison popup window.
    
    public void openPopUpCompareWindow() {
        String originalWindow = driver.getWindowHandle();
        compareButton.click(); 
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.getWindowHandles().size() > 1);

        for(String windowHandle : driver.getWindowHandles()) {
            if(!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
	
  
     // Clicks on the compare button.
 
    public void clickOnCompareButton() {
        clickIfVisible(compareButton);
    }
	
    /**
     * Verifies if Sony Xperia was added to comparison.
     *
     * @return true if added, false otherwise.
     */
    public boolean verifyAddXperiaToCompare() {
        return isElementDisplayed(sonyXperiaInCompareProducts); // Returns true if displayed
    }

	
    /**
     * Verifies if iPhone was added to comparison.
     *
     * @return true if added, false otherwise.
     */
    public boolean verifyAddIphoneToCompare() {
        return isElementDisplayed(iphoneInCompareProducts); // Returns true if displayed
    }
	

    /**
     * Clicks to add Sony Xperia to comparison.
     */
    public void clickAddToCompareSonyXperia() {
        clickIfVisible(addToCompareSonyXperia);
    }
	
    /**
     * Clicks to add iPhone to comparison.
     */
    public void clickAddToCompareIphone() {
        clickIfVisible(addtoCompareIphone);
    }
	
    /**
     * Adds Sony Xperia to the cart.
     */
    public CheckoutCartPage addToCartSonyXperia() {
    	addToCartSonyXperiaButton.click();
    	return new CheckoutCartPage(driver);
    }
	
    /**
     * Clicks on the Sony Xperia product image.
     */
    public void clickOnProductSonyXperia() {
        clickIfVisible(productSonyXperiaImage);
    }
	
    /**
     * Gets the price of Sony Xperia.
     *
     * @return price as a string.
     */
    public String productSonyXperiaPrice() {
        return priceValueProductSony.getText(); // Returns the price as text
    }
    
    /**
     * Gets the heading of the mobile page.
     *
     * @return heading text.
     */
    public String getMobilePageHeading() {
        return mobilePageHeading.getText(); // Returns heading text
    }

    /**
     * Selects products by name from the dropdown.
     */
    public void selectProductsByName() {
    	 Select select = new Select(dropDownSelectByName);
         select.selectByVisibleText("Name"); // Selects "Name" from the list
    }

    /**
     * Gets the names of all products on the page.
     *
     * @return list of product names.
     */
    public List<String> getProductsNames() {
        return products.stream().map(WebElement::getText).collect(Collectors.toList()); 
    }
    // Helper method to click on elements if they are visible
    private void clickIfVisible(WebElement element) {
        if (isElementDisplayed(element)) {
            element.click();
        } else {
            throw new RuntimeException("Element not clickable: " + element.toString());
        }
    }

    // Helper method to check if an element is displayed
    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false; // Return false if element is not found
        }
    }

}
