package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SonyXperiaPage {
	WebDriver driver;

	@FindBy(xpath = "//*[@id='product-price-1']")
	private WebElement priceSonyXperia;

	public SonyXperiaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Retrieves the price of the Sony Xperia product.

	public boolean getPriceSonyXperia() {
		return priceSonyXperia.isDisplayed();
	}

}
