package org.techpanda.live.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.techpanda.live.utils.ExtentReporter;
import org.techpanda.live.utils.Utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListener implements ITestListener {
	private ExtentReports extentReport;
	private ExtentTest extentTest;
	private WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " test is starting");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		getWebDriver(result);


//	    // Zvýraznenie prvku pred screenshotom
//	    WebElement failedElement = (WebElement) result.getAttribute("failedElement");
//	    if (failedElement != null) {
//	        highlightElement(driver, failedElement);
//	    }
//        String screenshotPath = captureAndAttachScreenshot(result.getName());
//
//        extentTest.log(Status.FAIL, result.getName() + " failed");
//        if (screenshotPath != null) {
//            extentTest.addScreenCaptureFromPath(screenshotPath);
//        }
//        extentTest.log(Status.INFO, result.getThrowable());
//	}
//	// Metóda na zvýraznenie prvku
//	public void highlightElement(WebDriver driver, WebElement element) {
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//	    js.executeScript("arguments[0].style.border='3px solid red'", element);
//	
//	}
	
		 // Získaš inštanciu testovacej triedy a prístup k jej premennej
	    Object testInstance = result.getInstance();
	    WebElement failedElement = null;

	    try {
	        // Získa failedElement z triedy CartTest
	        failedElement = (WebElement) testInstance.getClass().getDeclaredField("failedElement").get(testInstance);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Zvýrazni prvok ak existuje
	    if (failedElement != null) {
	        highlightElement(driver, failedElement);
	    }

	    String screenshotPath = captureAndAttachScreenshot(result.getName());
	    extentTest.log(Status.FAIL, result.getName() + " failed");
	    if (screenshotPath != null) {
	        extentTest.addScreenCaptureFromPath(screenshotPath);
	    }
	    extentTest.log(Status.INFO, result.getThrowable());
	}

	// Metóda na zvýraznenie prvku
	public void highlightElement(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].style.border='3px solid red'", element);
	
	}
		

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName() + " test skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
//		extentReport.flush();
//		String pathOfExtendReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
//		File extentReportFile = new File(pathOfExtendReport);
//
//		try {
//			Desktop.getDesktop().browse(extentReportFile.toURI());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		  extentReport.flush();
	        openExtentReport();
	}
	
	private void getWebDriver(ITestResult result) {
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            System.err.println("Error retrieving WebDriver: " + e.getMessage());
        }
    }

    public String captureAndAttachScreenshot(String testName) {
        String screenshotPath = Utilities.saveScreenshot(driver, testName);
        return screenshotPath;
    }

    private void openExtentReport() {
        String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
        File extentReportFile = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
            System.err.println("Error opening extent report: " + e.getMessage());
        }
    }

}
