package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPrintPage {
	public WebDriver driver;
	
	@FindBy(xpath="//*[@class='page-print sales-order-print']")
	private WebElement printOrderHeading;
	
	public OrderPrintPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
     * Checks if the order print page is displayed.
     * 
     * @return true if the order print heading is displayed, false otherwise
     */
    public boolean isOrderPrintPageDisplayed() {
        return printOrderHeading.isDisplayed();
    }

}
