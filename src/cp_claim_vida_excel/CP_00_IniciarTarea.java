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


public class CP_00_IniciarTarea {
  WebDriver driver;
  boolean acceptNextAlert = true,  isPresente, chBox, chBox1;
  StringBuffer verificationErrors = new StringBuffer();
  String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, url, ExcelPath, URLInicial;
  static String Tarea;
  int CaptureN;
  ExtentReports report;
  ExtentTest TestBPM; 
  ExcelDataConfig EscribirExcel, LeerExcel;

 //Inicializa tarea segun ID de Tarea
  //CP_01_02_DatosContacto DatosContacto = new CP_01_02_DatosContacto();
 // String IdTarea2= String.valueOf(Integer.valueOf(DatosContacto.IdTarea1) + 1);
  //Integer nReporte2= DatosContacto.Reporte;
  
  
	
@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	TestCaptura="T00_InicializarTarea";
	//Inicializar Reporte
	NombreReporte= "Inicializar Tarea";  
	report= new ExtentReports(".\\Reportes\\" + NombreReporte + ".html");
	TestBPM=report.startTest(NombreReporte);
	
	//Inicializar Excel
	ExcelPath= ".\\DataProvider\\inputData.xlsx";
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	URLInicial = "https://192.168.5.56:9443/ProcessPortal/login.jsp";
	
	GChrome();
	
  }
	
 @Test
  public void T00_InicializarTarea() throws Exception {
	 
	 // Inicio de una Tarea Admitir Expediente
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys("mervind");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='log_in']")).click();

	  Thread.sleep(2000); 
	  
	  driver.findElement(By.xpath("//a[contains(.,'Análisis y Coberturas Vida')]")).click();
	   
	  Thread.sleep(4000);
	  
	  WebElement mTareas = driver.findElement(By.xpath("//button[@title='Mostrar más tareas.']"));
	 
	  if (mTareas.isDisplayed()==true){
		  
		  driver.findElement(By.xpath("//button[@title='Mostrar más tareas.']")).click();
	  }
		  
 
	  
	  for (int i = 0; i <= 1000; i++) {

	   try {
	    WebElement tarea = driver.findElement(By.xpath(
	      "//a[@id='taskrowsubjectbuttoncom_ibm_bpm_social_widgets_task_list_TaskRow_" + i + "']"));
	    tarea.isDisplayed();
	    
	    
	    System.out.println(i + ":" + tarea.getText());

	   } catch (Exception e) {
	    i = i - 1;
	    System.out.println("Selecciona la ultima tarea " + i);
	    driver.findElement(By.xpath("//a[@id='taskrowsubjectbuttoncom_ibm_bpm_social_widgets_task_list_TaskRow_" + i + "']")).click();
	    break;
	   }

	  }
	  Thread.sleep(2000); 
	  driver.findElement(By.xpath("//div[@class='bpm-task-dialog-claimButtonDiv']//button[.='Reclamar tarea']")).click();
	  Thread.sleep(15000); 
	
	  ObtenerTarea(url);
	  
	  
	  //Enviar Resultado a Excel
	  Msj=String.valueOf(Tarea);
	  EscribirExcel.WriteData(0, 9, 1, Msj);
	  
 }
 
//////////////Procesos//////////////
public String ObtenerTarea (String url){
	
	  url=driver.getCurrentUrl();
	  System.out.println(url);
	  
	  String[] a= url.split("taskid=");
	  Tarea= (a[1]).toString();

	  System.out.println(Tarea);
	
	return Tarea;
	
	
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

//Evaluar Resultados del Test
@AfterMethod(alwaysRun = true)
public void tearDown(ITestResult result) throws IOException
{
	ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
	  
	  if(result.getStatus()==ITestResult.FAILURE)
	  {
		  
		  TestBPM.log(LogStatus.FAIL, "Ha fallado la Prueba");
		  TestBPM.log(LogStatus.INFO, "Ha fallado la Prueba", ScreenShot_Path);
		  Msj="NOK";
		  
	  }else {
		  
		  TestBPM.log(LogStatus.PASS, "Se culmino la prueba Exitosamente");
		  TestBPM.log(LogStatus.INFO, "Se culmino la prueba Exitosamente", ScreenShot_Path);

		  //Escribir la ID de tarea en Excel
		  Msj=String.valueOf(Tarea);
		  EscribirExcel.WriteData(0, 9, 1, Msj);
		 
	  }
	  
	  report.endTest(TestBPM);
	  report.flush();
	  
}



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
