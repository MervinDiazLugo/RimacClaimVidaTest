package cp_claim_vida_excel;

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
	public void ObtenerIdTarea(){	
		String ID=String.valueOf(CP_09_DatosSiniestro.IdTarea3);
		System.out.println(ID);
	    IdTarea4= ID;
	}
	
@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//Inicializar Reporte
	NombreReporte= "Datos de Cobertura";  
	report= new ExtentReports(".\\Reportes\\"+NombreReporte+ ".html");
	TestBPM=report.startTest(NombreReporte);
	
	//Inicializar Excel
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	 //Inicializa tarea segun ID de Tarea
		ObtenerIdTarea();
	  	 URLInicial= "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=" + IdTarea4 + "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";
	  	
	  	/**
		  String IdTarea2= "2037";
		  System.out.println(IdTarea2);
		 
		*/
			
	
	GChrome();
	
  }
	

 @Test
 public void T08_DatosBeneficiarios() throws Exception {
	NombreReporte= "T08 Datos Beneficiarios del Siniestro";
	TestCaptura="T08_BeneficiariosSiniestro";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Componente de Pantalla Datos Beneficiarios del Siniestro");
	
	datosBeneficiarioSiniesto();
	
	//Cód. Persona
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_13')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_13')]")).sendKeys(CodPersona);
	
	//Nombre
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_14')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_14')]")).sendKeys(NombreBenficiario);
	
	//Apellido Paterno
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_15')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_15')]")).sendKeys(ApellidoPaterno);
	
	//Apellido Materno 
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_16')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_16')]")).sendKeys(ApellidoMaterno);
	
	//Tipo Documento
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_12']")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_12']")).sendKeys(TipoDocumento);
	Thread.sleep(3000);
	
	//Numero de documento
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_17')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_17')]")).sendKeys(NumeroDocumento);
	
	//Fecha de Nacimiento
	driver.findElement(By.xpath("//input[contains(@id,'uniqName_1_6')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'uniqName_1_6')]")).sendKeys(FechaNacimiento);
	Thread.sleep(3000);
	
	//Sexo
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_13']")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_13']")).sendKeys(Sexo);
	Thread.sleep(3000);
	
	//Parentesco
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_14')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_14')]")).sendKeys(Parentesco);
	Thread.sleep(3000);
	
	//Monto a Pagar
	driver.findElement(By.xpath("//input[contains(@id,'uniqName_2_2')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'uniqName_2_2')]")).sendKeys(MontoPagar);
	
	//Nombre de Cobertura
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_15']")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_15']")).sendKeys(NombreCobertura);
	Thread.sleep(15000);
	
	//Boton Agregar
	driver.findElement(By.xpath("//div[@id='div_3_1_1_1_9_1_1_3_1_3']//button[.='Agregar']")).click();
	Thread.sleep(10000);
	
	//Tomar Screen
	ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura.concat(Integer.toString(CaptureN++))) + ">"; 
    TestBPM.log(LogStatus.INFO, "T08 Datos Beneficiarios del Siniestro", ScreenShot_Path);
    

   }  
   
public void AvanzarExpediente(){
	
	NombreReporte= "09 Avanzar Expediente Datos de Cobertura";
	TestCaptura="T09_AvanzarExpedienteDatosCobertura";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Componente de Pantalla Datos Beneficiarios del Siniestro");
	
	//driver.FindElement(By.XPath("//div[@id='div_5_1_1']")).sendKeys("Aprobar");
	//div[1]/div[4]/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/input[1]
	
	
	//driver.FindElement(By.XPath("//div[1]/div[4]/div/div/div[2]/div/div/div[3]/button")).click();
	
}
 
//Evaluar Resultados del Test
@AfterMethod(alwaysRun = true)
public void tearDown(ITestResult result) throws IOException
{
	ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
	  
	  if(result.getStatus()==ITestResult.FAILURE)
	  {
		  
		  TestBPM.log(LogStatus.FAIL, "Ha fallado la Prueba");
		  TestBPM.log(LogStatus.WARNING, "Ha fallado la Prueba", ScreenShot_Path);
		  Msj="NOK";
		  
	  }else {
		  
		  TestBPM.log(LogStatus.PASS, "Se culmino la prueba Exitosamente");
		  TestBPM.log(LogStatus.WARNING, "Se culmino la prueba Exitosamente", ScreenShot_Path);
		  
		  Msj="OK";
	  }
	  
	  report.endTest(TestBPM);
	  report.flush();
	  
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
