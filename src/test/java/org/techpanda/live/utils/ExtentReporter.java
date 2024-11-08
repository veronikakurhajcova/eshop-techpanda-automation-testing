package org.techpanda.live.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
//	ExtentReports extentReport = new ExtentReports();
//	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//
//	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
//
//	sparkReporter.config().setReportName("Techpanda Automation Tests Results");
//	sparkReporter.config().setDocumentTitle("Automation Report");
//	sparkReporter.config().setTimeStampFormat("dd/MM/yyy hh:mm:ss");
//	extentReport.setSystemInfo("autor", "Veronika");
//	
//	extentReport.attachReporter(sparkReporter);
//	
//	return extentReport;
//	}
		  ExtentReports extentReport = new ExtentReports();
	        
	        // Definuj cestu k reportu
	        File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
	        
	        // Vytvor adresár pre report, ak neexistuje
	        File reportDir = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports");
	        if (!reportDir.exists()) {
	            reportDir.mkdirs(); // Vytvor adresár
	        }
	        
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	        
	        // Nastav konfiguráciu reportu
	        sparkReporter.config().setReportName("Techpanda Automation Tests Results");
	        sparkReporter.config().setDocumentTitle("Automation Report");
	        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	        
	        // Nastav systémové info
	        extentReport.setSystemInfo("author", "Veronika");
	        
	        // Pripoj reportér k Extent reportu
	        extentReport.attachReporter(sparkReporter);
	        
	        return extentReport; // Vráti ExtentReport objekt
	    }

}
