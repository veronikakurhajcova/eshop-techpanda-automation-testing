package org.techpanda.live.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountViewOrderPage {
	public WebDriver driver;
	OrderPrintPage orderPrintPage;
	
	@FindBy(xpath="//h1")
	private WebElement orderNumber;
	
	@FindBy(linkText="Print Order")
	private WebElement printOrderLink;
	
	public AccountViewOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Order Methods
	
    public void clickPrintOrder() {
        printOrderLink.click();
    }

    public String getOrderNumber() {
        return orderNumber.getText();
    }
	
    public boolean isOrderNumberDisplayed() {
        return orderNumber.isDisplayed();
    }
    // Cancels the print dialog if it appears.
     
    public void cancelPrintDialog() {
        String originalWindow = driver.getWindowHandle(); 
        try {
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle); 
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    break;
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (Exception e) {
           
            e.printStackTrace();
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}
