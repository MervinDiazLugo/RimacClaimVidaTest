package cp_Rimac_Claim_Vida;

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
import lib.CaptureScreenshot;
import lib.ExcelDataConfig;
import lib.Reportes;
import pageObjects.DatosBeneficiario;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CP_12_DatosBeneficiarioSiniestro {
  WebDriver driver;
  WebElement tarea;
  boolean acceptNextAlert = true,  isPresente, chBox, chBox1;
  StringBuffer verificationErrors = new StringBuffer();
  String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, URLInicial, IdTarea4;
  String ExcelPath= ".\\DataProvider\\inputData.xlsx";
  int CaptureN;
  ExtentReports report;
  ExtentTest TestBPM; 
  ExcelDataConfig EscribirExcel, LeerExcel;

//Data Provider variables
  String CodPersona, NombreBenficiario, ApellidoPaterno, ApellidoMaterno, TipoDocumento, NumeroDocumento, FechaNacimiento, Sexo, Parentesco, MontoPagar, NombreCobertura; 

	//http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=1756%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe



	
@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//Inicializar Reporte
	NombreReporte= "Datos de Cobertura";  
	Reportes.CrearReporte(NombreReporte);
	
	//Inicializar Excel
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	 //Inicializa tarea segun ID de Tarea
		String ID=LeerExcel.GetData(0, 9, 2);
		System.out.println(ID);
		IdTarea4= ID;
		
		String IdTareaFijo= "2290";

		
	  	URLInicial= "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=" + IdTarea4 + "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";

			
	
	GChrome();
	
  }
	

 @Test
 public void T08_DatosBeneficiarios() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
	NombreReporte= "T08 Datos Beneficiarios del Siniestro";
	TestCaptura="T08_BeneficiariosSiniestro";
	
	Reportes.CrearPrueba(NombreReporte);
	Reportes.EnviarStatus(LogStatus.INFO, "Inicio Datos Beneficiarios del Siniestro");
	
	datosBeneficiarioSiniesto();
	
	//Cód. Persona
	DatosBeneficiario.DC_txtCodPersonaXpath(driver).clear();
	DatosBeneficiario.DC_txtCodPersonaXpath(driver).sendKeys(CodPersona);
	
	//Nombre
	DatosBeneficiario.DC_txtNombreXpath(driver).clear();
	DatosBeneficiario.DC_txtNombreXpath(driver).sendKeys(NombreBenficiario);
	
	//Apellido Paterno
	DatosBeneficiario.DC_txtApellidoPaternoXpath(driver).clear();
	DatosBeneficiario.DC_txtApellidoPaternoXpath(driver).sendKeys(ApellidoPaterno);
	
	//Apellido Materno 
	DatosBeneficiario.DC_txtApellidoMaternoXpath(driver).clear();
	DatosBeneficiario.DC_txtApellidoMaternoXpath(driver).sendKeys(ApellidoMaterno);
	
	//Tipo Documento
	DatosBeneficiario.DC_txtTipoDocumentoXpath(driver).clear();
	DatosBeneficiario.DC_txtTipoDocumentoXpath(driver).sendKeys(TipoDocumento);
	Thread.sleep(3000);
	
	//Numero de documento
	DatosBeneficiario.DC_txtNumeroDocumentoXpath(driver).clear();
	DatosBeneficiario.DC_txtNumeroDocumentoXpath(driver).sendKeys(NumeroDocumento);
	
	//Fecha de Nacimiento
	DatosBeneficiario.DC_txtFechaNacimientoXpath(driver).clear();
	DatosBeneficiario.DC_txtFechaNacimientoXpath(driver).sendKeys(FechaNacimiento);
	Thread.sleep(3000);
	
	//Sexo
	DatosBeneficiario.DC_txtSexoXpath(driver).clear();
	DatosBeneficiario.DC_txtSexoXpath(driver).sendKeys(Sexo);
	Thread.sleep(3000);
	
	//Parentesco
	DatosBeneficiario.DC_txtParentescoXpath(driver).clear();
	DatosBeneficiario.DC_txtParentescoXpath(driver).sendKeys(Parentesco);
	Thread.sleep(3000);
	
	//Monto a Pagar
	DatosBeneficiario.DC_txtMontoXpath(driver).clear();
	DatosBeneficiario.DC_txtMontoXpath(driver).sendKeys(MontoPagar);
	
	//Nombre de Cobertura
	DatosBeneficiario.DC_txtNombreCoberturaXpath(driver).clear();
	DatosBeneficiario.DC_txtNombreCoberturaXpath(driver).sendKeys(NombreCobertura);
	Thread.sleep(15000);
	
	//Boton Agregar
	DatosBeneficiario.DC_btnAgregarXpath(driver).click();
	Thread.sleep(10000);
	
	//Tomar Screen
	Reportes.TomarCaptura(driver, LogStatus.INFO,ScreenShot_Path, TestCaptura);
	Reportes.EnviarStatus(LogStatus.INFO, "T08 Datos Beneficiarios del Siniestro");
    

   }  
   
public void AvanzarExpediente(){
//////////////CONFIGURACION DE REPORTES////////////////////////////	
	NombreReporte= "09 Avanzar Expediente Datos de Cobertura";
	TestCaptura="T09_AvanzarExpedienteDatosCobertura";
	
	Reportes.CrearPrueba(NombreReporte);
	Reportes.EnviarStatus(LogStatus.INFO, "Inicio Expediente Datos de Cobertura");
	
	DatosBeneficiario.DC_txtAprobarControlXpath(driver).sendKeys("Aprobar");
	//div[1]/div[4]/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/input[1]
	
	
	DatosBeneficiario.DC_btnAvanzarXpath(driver).click();
	
}
 
	///////EVALUAR RESULTADOS DEL TEST//////////
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException, InterruptedException {
		ScreenShot_Path = "<img src=" + CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">";

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
public void datosBeneficiarioSiniesto(){

	CodPersona = 		LeerExcel.GetData(0,25,1);
	NombreBenficiario =	LeerExcel.GetData(0,25,2);
	ApellidoPaterno =	LeerExcel.GetData(0,25,3);
	ApellidoMaterno =	LeerExcel.GetData(0,25,4);
	TipoDocumento 	=	LeerExcel.GetData(0,25,5);
	NumeroDocumento =	LeerExcel.GetData(0,25,6);
	FechaNacimiento =	LeerExcel.GetData(0,25,7);
	Sexo =				LeerExcel.GetData(0,25,8);
	Parentesco =		LeerExcel.GetData(0,25,9);
	MontoPagar =		LeerExcel.GetData(0,25,10);
	NombreCobertura =	LeerExcel.GetData(0,25,11);


		
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

/*
Esperar que aparezca el elemento
WebDriverWait wait = new WebDriverWait(driver, 15);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(.,'El servicio ha finalizado.')]")));

WebElement msj = driver.findElement(By.xpath("//b[contains(.,'El servicio ha finalizado.')]"));
System.out.println(msj);
Assert.assertEquals("El servicio ha finalizado.", msj);

driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_29')]"));//Aprobar
    
div[@id='div_4_1_2_1_2']//button[.='Avanzar']


*/
