package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistSharePage {
	public WebDriver driver;
	
	@FindBy(id="email_address")
	private WebElement emailAddressTextarea;
	
	@FindBy(id="message")
	private WebElement messageTextArea;
	
	@FindBy(xpath="//span[text()='Share Wishlist']")
	private WebElement shareWishlistButton;
	
	public WishlistSharePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
     * Shares the wishlist information by entering the email address and message.
     *
     * @param emailAddress the email address to share the wishlist
     * @param message      the message to include with the wishlist
     * @return a new instance of WishlistPage
     * @throws IllegalArgumentException if the email or message is empty
     */
    public WishlistPage sharingWishlistInformation(String emailAddress, String message) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be empty.");
        }

        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }

        emailAddressTextarea.sendKeys(emailAddress);
        messageTextArea.sendKeys(message);

        if (shareWishlistButton.isDisplayed() && shareWishlistButton.isEnabled()) {
            shareWishlistButton.click();
        } else {
            throw new RuntimeException("Share Wishlist button is not clickable.");
        }

        return new WishlistPage(driver);
    }

}
