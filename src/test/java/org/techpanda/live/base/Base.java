package org.techpanda.live.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	WebDriver driver;
	public Properties dataProp;
	public WebElement failedElement;

	public Base() {
		dataProp = new Properties();
		loadProperties();
	}
	
	public void loadProperties() {
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\org\\techpanda\\live\\testdata\\datatest.properties");

		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
			;
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

	 public WebDriver initializeBrowserAndOpenUrl(String browserName) {
	        switch (browserName.toLowerCase()) {
	            case "firefox":
	                System.setProperty("webdriver.gecko.driver",
	                        "C:\\Users\\Veronika\\Downloads\\geckodriver-v0.35.0-win64\\geckodriver.exe");
	                FirefoxOptions firefoxOptions = new FirefoxOptions();
	                driver = new FirefoxDriver(firefoxOptions);
	                break;
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                ChromeOptions chromeOptions = new ChromeOptions();
	                chromeOptions.addArguments("--disable-search-engine-choice-screen");
	                driver = new ChromeDriver(chromeOptions);
	                break;
	            default:
	                throw new IllegalArgumentException("Browser not supported: " + browserName);
	        }

	        configureDriver();
	        driver.get(dataProp.getProperty("url"));
	        return driver;
	    }

	 private void configureDriver() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.get(dataProp.getProperty("url"));
	}

}
