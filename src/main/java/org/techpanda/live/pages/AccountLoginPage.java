package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountLoginPage {
	public WebDriver driver;

	//Account Login Elements
	@FindBy(xpath = "(//*[@id=\"login-form\"]//a)[1]")
	private WebElement createAccount;

	@FindBy(xpath = "//div[contains(@class,'page-title')]/h1")
	private WebElement loginOrCreateHeading;
	
	@FindBy(id="email")
	private WebElement registeredEmail;
	
	@FindBy(id="pass")
	private WebElement registeredPassword;
	
	@FindBy(id="send2")
	private WebElement loginButton;

	public AccountLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Account login
	public void login(String email, String password) {
		registeredEmail.sendKeys(email);
		registeredPassword.sendKeys(password);
		loginButton.click();
	}

	public AccountCreatePage clickOnCreateAccount() {
		createAccount.click();
		return new AccountCreatePage(driver);
	}

	// Account login or create Headings
	public String getLoginOrCreateAccountHeading() {
		return loginOrCreateHeading.getText();
	}
	
	public boolean isLoginOrCreateAccountHeadingDisplayed() {
		return loginOrCreateHeading.isDisplayed();
	}
	
    public boolean isCreateAccountLinkDisplayed() {
        return createAccount.isDisplayed();
    }
}
