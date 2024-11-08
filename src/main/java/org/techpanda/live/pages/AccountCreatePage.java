package org.techpanda.live.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatePage {
	public WebDriver driver;
	
	//Registration elements
	
	@FindBy(id = "firstname")
	private WebElement firstnameField;

	@FindBy(id = "lastname")
	private WebElement lastnameField;

	@FindBy(id = "email_address")
	private WebElement emailAddressField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "confirmation")
	private WebElement confirmationPasswordField;

	@FindBy(xpath = "//button[@title='Register']")
	private WebElement registerButton;

	public AccountCreatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Filling Registration
	
	public AccountCustomerPage registerAccount(String firstname, String lastname, String password,
			String confirmationPassword) {
		String uniqueValidEmail = "arunmotoori" + System.currentTimeMillis()+"@gmail.com";
		fillRegistrationForm(firstname, lastname, uniqueValidEmail, password, confirmationPassword);
		registerButton.click();

		return new AccountCustomerPage(driver);
	}

	private void fillRegistrationForm(String firstname, String lastname, String email, String password,
			String confirmationPassword) {
		firstnameField.sendKeys(firstname);
		lastnameField.sendKeys(lastname);
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmationPasswordField.sendKeys(confirmationPassword);
	}


}
