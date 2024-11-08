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
	        
	        // Vytvor adres�r pre report, ak neexistuje
	        File reportDir = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports");
	        if (!reportDir.exists()) {
	            reportDir.mkdirs(); // Vytvor adres�r
	        }
	        
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	        
	        // Nastav konfigur�ciu reportu
	        sparkReporter.config().setReportName("Techpanda Automation Tests Results");
	        sparkReporter.config().setDocumentTitle("Automation Report");
	        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	        
	        // Nastav syst�mov� info
	        extentReport.setSystemInfo("author", "Veronika");
	        
	        // Pripoj report�r k Extent reportu
	        extentReport.attachReporter(sparkReporter);
	        
	        return extentReport; // Vr�ti ExtentReport objekt
	    }

}
