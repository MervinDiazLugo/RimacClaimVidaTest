package cp_Rimac_Claim_Vida;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
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

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CP_05_DatosCobertura {
  WebDriver driver;
  WebElement tarea;
  boolean acceptNextAlert = true,  isPresente, chBox, chBox1;
  StringBuffer verificationErrors = new StringBuffer();
  String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, URLInicial, ID;
  static String IdTarea2;
  String ExcelPath= ".\\DataProvider\\inputData.xlsx";
  int CaptureN;
  ExtentReports report;
  ExtentTest TestBPM; 
  ExcelDataConfig EscribirExcel, LeerExcel;

	//http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=2118%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe



	
@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//Inicializar Reporte
	NombreReporte= "Datos de Cobertura";  
	Reportes.CrearReporte(NombreReporte);
	
	//Inicializar Excel
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	 //Inicializa tarea segun ID de Tarea
		
		//Capturar IDtarea desde el Metodo	
		//ID=String.valueOf(Integer.valueOf(CP_01_02_DatosContacto.IdTarea1)+1);
		
	//Capturar IDtarea desde Excel
		ID=String.valueOf(Integer.valueOf(LeerExcel.GetData(0, 9, 1))+1);
		EscribirExcel.WriteData(0, 8, 2, "Determinar Cobertura");
		EscribirExcel.WriteData(0, 9, 2, ID);
		IdTarea2= ID;
		
		String IdTareaFijo= "2290";
		
		
		
	  	URLInicial= "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=" + IdTarea2 + "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";
		
	
	GChrome();
	
  }
	
 @Test
  public void T06_DatosCobertura() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
	NombreReporte= "T06 Datos Cobertura";
	TestCaptura="T06_DatosCobertura";
	
	Reportes.CrearPrueba(NombreReporte);
	Reportes.EnviarStatus(LogStatus.INFO, "Se inicio el Componente de Pantalla Datos Cobertura");

	
	
	
	WebDriverWait wait = new WebDriverWait(driver, 15);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='div_3_1_1_1_5_1_Border']//h2[.='Datos Cobertura']")));
	//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[1]/table/tbody/tr/td[6]/div/div[2]/div[2]/div[3]/input[1]
	
	int fila=12;

	
	 for (int i = 1; i <= 10; i++) {
		
		   try {
			   
		    tarea = driver.findElement(By.xpath(
		      "//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[1]/div/div[2]/div[1]/span"));
		   
		    	System.out.println(tarea.getText());
		    	
			   
		    //Acciones
			    driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[6]/div/div[2]/div[2]/div[3]/input[1]")).click();
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[6]/div/div[2]/div[2]/div[3]/input[1]")).sendKeys(LeerExcel.GetData(0, fila, 1));
				
				System.out.println(LeerExcel.GetData(0, fila, 1));
				Thread.sleep(3000);
				
			//Establecer Moneda
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[7]/div/div[2]/div[2]/div[3]/input[1]")).click();
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[7]/div/div[2]/div[2]/div[3]/input[1]")).sendKeys(LeerExcel.GetData(0, fila, 2));
				System.out.println(LeerExcel.GetData(0, fila, 2));
				Thread.sleep(3000);
				
			//Monto a Pagar
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[8]/div/div[2]/div[2]/div[3]/input")).clear();
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[1]/div/div[2]/div/div/div/div/div[" + i + "]/table/tbody/tr/td[8]/div/div[2]/div[2]/div[3]/input")).sendKeys(LeerExcel.GetData(0, fila, 3));	
				System.out.println(LeerExcel.GetData(0, fila, 3));
				Thread.sleep(3000);
			
			//Cargar el valor del parametro de la matriz
				fila++;
				
		   } catch (Exception e) {
		    
			  System.out.println("No existen mas elementos");
			  break;
		  
		   }

		  }
	

	//valor del Cambio
	driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[4]/div/div/div/div[2]/div[2]/div[2]/input[1]")).clear();
	driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[5]/div/div/div/div[4]/div/div/div/div[2]/div[2]/div[2]/input[1]")).sendKeys(LeerExcel.GetData(0, 12, 4));
	
	
	
	tag1 = driver.findElement(By.xpath("//span[contains(@id,'div_3_1_1_1_5_1_1_3_1_2_span')]")).getText();
	System.out.println(tag1);
	AssertJUnit.assertEquals(String.valueOf(tag1), String.valueOf(LeerExcel.GetData(0, 12, 5)));
	
	
	
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


