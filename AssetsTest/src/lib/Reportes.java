package lib;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import lib.CaptureScreenshot;

public class Reportes {
	static String NombreReporte;
	static ExtentReports report;
	static ExtentTest TestReport; 
	
	 public static void CrearReporte(String ReportName){
		
		NombreReporte= ReportName;  
		report= new ExtentReports(".\\Reportes\\" + ReportName + ".html");
		TestReport=report.startTest(ReportName);
		
	}
	 
	 public static void CerrarReporte(){
			
		 report.endTest(TestReport);
		 report.flush();
		
	}
	 
	 public static void CrearPrueba(String ReportName){
			
			NombreReporte= ReportName;  
			TestReport=report.startTest(ReportName);
			
	}
	 
	 public static void EnviarStatus(LogStatus Result, String mensaje){
			
		 TestReport.log(Result, mensaje);
		
		 
	}
	 
	 
	 public static void TomarCaptura(WebDriver driver, LogStatus Result, String ScreenShot_Path,String TestCaptura){
		 
		 ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
		 TestReport.log(Result, ScreenShot_Path);
		
		 
	}
	 

}
