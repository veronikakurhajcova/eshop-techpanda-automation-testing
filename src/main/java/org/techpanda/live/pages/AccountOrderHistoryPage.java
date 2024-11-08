package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountOrderHistoryPage {
	public WebDriver driver;
	
	@FindBy(linkText = "VIEW ORDER")
	private WebElement viewOrder;
	
	public AccountOrderHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickViewOrder() {
		viewOrder.click();
	}
	
    public boolean isViewOrderLinkDisplayed() {
        return viewOrder.isDisplayed();
    }

}
