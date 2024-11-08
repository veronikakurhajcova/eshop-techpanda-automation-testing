package org.techpanda.live.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
//	public static String captureScreenshot(WebDriver driver, String testName) {
//		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		//urobim manualne folder pre tento projekt
//		String destinationScreenshotPath =System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
//		try {
//			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return destinationScreenshotPath;
//	}
	 // Metóda na zachytávanie screenshotu a jeho uloženie
    public static String saveScreenshot(WebDriver driver, String testName) {
        // Získaj screenshot ako súbor
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Definuj cestu, kam sa screenshot uloží
        String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
        
        // Vytvor adresár, ak neexistuje
        File screenshotDir = new File(System.getProperty("user.dir") + "\\Screenshots\\");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs(); // Vytvor adresár
        }
        
        try {
            // Skopíruj screenshot do definovanej cesty
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            System.err.println("Error while saving screenshot: " + e.getMessage());
        }
        return destinationScreenshotPath; // Vráti cestu k screenshotu
    }
}
