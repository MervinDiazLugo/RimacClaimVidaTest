package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import lib.CaptureScreenshot;
import lib.ExcelDataConfig;
import lib.Reportes;
import pageObjects.DatosSiniestro;

public class CP_09_DatosSiniestro {
  WebDriver driver;
  WebElement tarea;
  boolean acceptNextAlert = true,  isPresente, chBox, chBox1;
  StringBuffer verificationErrors = new StringBuffer();
  String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, URLInicial, ID;
  static String IdTarea3;
  String ExcelPath= ".\\DataProvider\\inputData.xlsx";
  int CaptureN;
  ExtentReports report;
  ExtentTest TestBPM; 
  ExcelDataConfig EscribirExcel, LeerExcel;

//Declaracion de Datos Excel Datos de Cabecera
  String Lugar, Tramite, Prioridad;
  
//Data Provider Variables
  String TipoSiniestro, TipoPagoSiniestro, FechaOcurrencia, DescripcionGL;

	//http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=1756%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe


	
@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//Inicializar Reporte
	NombreReporte= "Datos del Siniestro";  
	Reportes.CrearReporte(NombreReporte);
	
	//Inicializar Excel
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	 //Inicializa tarea segun ID de Tarea
		ID=LeerExcel.GetData(0, 9, 2);
		System.out.println(ID);
	  	IdTarea3= ID;
  	 	
   	
	  	String IdTareaFijo= "2290";
		
	  	URLInicial= "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=" + IdTarea3 + "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";
		IExplorer();
	
  }
	

 @Test
 public void T07_DatosSiniestro() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
	NombreReporte= "T07 Datos Del Siniestro";
	TestCaptura="T07_DatosSiniestro";
	
	Reportes.CrearPrueba(NombreReporte);
	Reportes.EnviarStatus(LogStatus.INFO, "Inicio Datos Del Siniestro");
	
	DatosSiniestro();
	
	//Tipo Siniestro
	
	DatosSiniestro.DC_txtTipoSiniestroXpath(driver).clear();
	DatosSiniestro.DC_txtTipoSiniestroXpath(driver).sendKeys(TipoSiniestro);
	System.out.println(TipoSiniestro);
	Thread.sleep(3000);
	
	//Tipo Pago de Siniestro
	
	DatosSiniestro.DC_txtTTipoPagoSiniestroXpath(driver).clear();
	DatosSiniestro.DC_txtTTipoPagoSiniestroXpath(driver).sendKeys(TipoPagoSiniestro);
	System.out.println(TipoPagoSiniestro);
	Thread.sleep(3000);

	//Fecha Ocurrencia
	DatosSiniestro.DC_txtFechaOcurrenciaXpath(driver).clear();
	DatosSiniestro.DC_txtFechaOcurrenciaXpath(driver).sendKeys(FechaOcurrencia);
	System.out.println(FechaOcurrencia);
	Thread.sleep(3000);
		
	//Lugar Descripción Genérica del Lugar
	DatosSiniestro.DC_txtDescripcionGLXpath(driver).clear();
	DatosSiniestro.DC_txtDescripcionGLXpath(driver).sendKeys(DescripcionGL);
	System.out.println(DescripcionGL);
	Thread.sleep(3000);

   }  
 

	///////EVALUAR RESULTADOS DEL TEST//////////
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {

			Reportes.TomarCaptura(driver, LogStatus.INFO,ScreenShot_Path, TestCaptura);
			Reportes.EnviarStatus(LogStatus.FAIL, "Ha fallado la Prueba");
			Msj = "NOK";
			Thread.sleep(5000);

		} else {
			
			Reportes.TomarCaptura(driver, LogStatus.INFO,ScreenShot_Path, TestCaptura);
			Reportes.EnviarStatus(LogStatus.PASS, "Se culmino la prueba Exitosamente");

		}

		Reportes.CerrarReporte();

	}

//////////////DATA PROVIDER//////////////
public void DatosSiniestro(){

	
	
	TipoSiniestro=		LeerExcel.GetData(0,21,1);
	TipoPagoSiniestro=	LeerExcel.GetData(0,21,2);
	FechaOcurrencia=	LeerExcel.GetData(0,21,3);
	DescripcionGL=		LeerExcel.GetData(0,21,4);
	
	
}


//////////////NAVEGADORES//////////////

public void IExplorer()
{

System.setProperty("webdriver.ie.driver", ".\\librerias\\IExplorer\\IEDriverServer.exe");
DesiredCapabilities cap = new DesiredCapabilities();
cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

driver = new InternetExplorerDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get(URLInicial);

}

public void GChrome()
{

System.setProperty ("webdriver.chrome.driver", ".\\librerias\\chromedriver\\chromedriver.exe");
driver = new ChromeDriver(); 
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get(URLInicial);

}  


///////OTRAS CONFIGURACIONES//////////
@AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.quit();
    
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}


