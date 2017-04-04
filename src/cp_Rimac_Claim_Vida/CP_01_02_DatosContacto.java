package cp_Rimac_Claim_Vida;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
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
import pageObjects.DatosCabecera;
import pageObjects.DatosContacto;

public class CP_01_02_DatosContacto {
	WebDriver driver;
	boolean acceptNextAlert = true, isPresente, chBox, chBox1;
	StringBuffer verificationErrors = new StringBuffer();
	String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, URLInicial, ID;
	static String IdTarea1;
	String ExcelPath = ".\\DataProvider\\inputData.xlsx";
	int CaptureN, Reporte;
	ExtentReports report;
	ExtentTest TestBPM;
	ExcelDataConfig EscribirExcel, LeerExcel;

	// Declaracion de Datos Excel Datos de Cabecera
	String Lugar, Tramite, Prioridad;

	// Declaracion de Datos Excel Datos Contacto
	String tDocumento, nDocumento, Apellidos, Nombres, Direccion, Departamento, Provincia, Distrito, Telefono1,
			Telefono2, Email1, Email2;

	// http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=1756%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		// Inicializar Reporte
		NombreReporte = "Admitir Expediente";
		Reportes.CrearReporte(NombreReporte);

		// Inicializar Excel
		LeerExcel = new ExcelDataConfig(ExcelPath);
		EscribirExcel = new ExcelDataConfig(ExcelPath);

		// Inicializa tarea segun ID de Tarea
		// Capturar IDtarea desde el Metodo
		// ID=String.valueOf(CP_00_IniciarTarea.Tarea);

		// Capturar IDtarea desde Excel
		ID = LeerExcel.GetData(0, 9, 1);
		System.out.println(ID);
		IdTarea1 = ID;

		String IdTareaFijo = "2186";

		URLInicial = "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId="
				+ IdTarea1
				+ "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";

		GChrome();
	}

	@Test
	public void T01_AgregarDatosContacto() throws Exception {
		////////////// CONFIGURACION DE REPORTES////////////////////////////
		NombreReporte = "T01 Agregar Datos de Contacto";
		TestCaptura = "T01_AgregarDatosContacto";
		Reportes.CrearPrueba(NombreReporte);
		Reportes.EnviarStatus(LogStatus.INFO, "Se inicio Agregar Datos de Contacto");

		// Cargar Matriz de Excel
		DatosContacto();

		// driver.switchTo().frame(2);

		// Lugar Notificación Real
		DatosCabecera.DC_txtLugarNotificacionRealXpath(driver).clear();
		DatosCabecera.DC_txtLugarNotificacionRealXpath(driver).sendKeys(Lugar);

		// Tipo Trámite
		DatosCabecera.DC_txtTipoTramiteXpath(driver).clear();
		DatosCabecera.DC_txtTipoTramiteXpath(driver).sendKeys(Tramite);

		// Prioridad
		DatosCabecera.DC_txtPrioridadXpath(driver).clear();
		DatosCabecera.DC_txtPrioridadXpath(driver).sendKeys(Prioridad);

		// Tipo de Documento
		DatosContacto.DC_txtTipoDocumentoXpath(driver).clear();
		DatosContacto.DC_txtTipoDocumentoXpath(driver).sendKeys(tDocumento);

		// Numero de Documento
		DatosContacto.DC_txtNumeroDocumentoXpath(driver).clear();
		DatosContacto.DC_txtNumeroDocumentoXpath(driver).sendKeys(nDocumento);

		// Apellidos
		DatosContacto.DC_txtApellidosXpath(driver).clear();
		DatosContacto.DC_txtApellidosXpath(driver).sendKeys(Apellidos);

		// Nombres
		DatosContacto.DC_txtNombresXpath(driver).clear();
		DatosContacto.DC_txtNombresXpath(driver).sendKeys(Nombres);

		// Direccion
		DatosContacto.DC_txtDireccionXpath(driver).clear();
		DatosContacto.DC_txtDireccionXpath(driver).sendKeys(Direccion);

		// Pais Perú
		DatosContacto.DC_OptPeruXpath(driver).click();

		// Extranjero
		// DatosContacto.DC_OptExtranjeroXpath(driver).click();

		// Departamento
		DatosContacto.DC_dropDepartamentoXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropDepartamentoXpath(driver).sendKeys(Departamento);
		Thread.sleep(3000);

		// Provincia
		DatosContacto.DC_dropProvinciaXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropProvinciaXpath(driver).sendKeys(Provincia);
		Thread.sleep(3000);

		// Distrito
		DatosContacto.DC_dropDistritoXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropDistritoXpath(driver).sendKeys(Distrito);
		Thread.sleep(3000);

		// Telefono1
		DatosContacto.DC_txtTelefono1Xpath(driver).clear();
		DatosContacto.DC_txtTelefono1Xpath(driver).sendKeys(Telefono1);

		// Telefono2
		DatosContacto.DC_txtTelefono2Xpath(driver).clear();
		DatosContacto.DC_txtTelefono2Xpath(driver).sendKeys(Telefono2);

		// Email1
		DatosContacto.DC_txtEmail1Xpath(driver).clear();
		DatosContacto.DC_txtEmail1Xpath(driver).sendKeys(Email1);

		// Email2
		DatosContacto.DC_txtEmail2Xpath(driver).clear();
		DatosContacto.DC_txtEmail2Xpath(driver).sendKeys(Email2);

		// Notificaciones SI
		DatosContacto.DC_optNotificacionesSiXpath(driver).click();

		// Notificaciones NO
		// DatosContacto.DC_optNotificacionesNoXpath(driver).click();

		Thread.sleep(10000);

		DatosContacto.DC_btnAgregarXpath(driver).click();

		Thread.sleep(10000);
		
		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
		
		// Recargar la pagina

		WebElement boton = driver.findElement(By.xpath(
				"//div[1]/div[3]/div[1]/div/div[3]/div/div/div/div/div[1]/div/div/div/div[3]/div/div/div[4]/button"));
		AssertJUnit.assertEquals(true, boton.isDisplayed());

		// Enviar Valor a Excel
		EscribirExcel.WriteData(0, 3, 13, Msj);
		EscribirExcel.WriteData(0, 5, 13, Msj);

		Reportes.EnviarStatus(LogStatus.INFO,"Agregar Datos de Contacto Finalizo Correctamente");

		
	}

	@Test
	public void T02_AgregarOtroDatoContacto() throws Exception {
		////////////// CONFIGURACION DE REPORTES////////////////////////////
		NombreReporte = "T02 Agregar Otro Dato de Contacto";
		TestCaptura = "T02_AgregarOtroDatoContacto";
		Reportes.CrearPrueba(NombreReporte);
		Reportes.EnviarStatus(LogStatus.INFO, "Se inicio Agregar Otro Dato de Contacto");

		// Cargar Matriz de Excel
		otroDatoContacto();

		// Tipo de Documento
		DatosContacto.DC_txtTipoDocumentoXpath(driver).clear();
		DatosContacto.DC_txtTipoDocumentoXpath(driver).sendKeys(tDocumento);

		// Numero de Documento
		DatosContacto.DC_txtNumeroDocumentoXpath(driver).clear();
		DatosContacto.DC_txtNumeroDocumentoXpath(driver).sendKeys(nDocumento);

		// Apellidos
		DatosContacto.DC_txtApellidosXpath(driver).clear();
		DatosContacto.DC_txtApellidosXpath(driver).sendKeys(Apellidos);

		// Nombres
		DatosContacto.DC_txtNombresXpath(driver).clear();
		DatosContacto.DC_txtNombresXpath(driver).sendKeys(Nombres);

		// Direccion
		DatosContacto.DC_txtDireccionXpath(driver).clear();
		DatosContacto.DC_txtDireccionXpath(driver).sendKeys(Direccion);

		// Pais Perú
		DatosContacto.DC_OptPeruXpath(driver).click();

		// Extranjero
		// DatosContacto.DC_OptExtranjeroXpath(driver).click();

		// Departamento
		DatosContacto.DC_dropDepartamentoXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropDepartamentoXpath(driver).sendKeys(Departamento);
		Thread.sleep(3000);

		// Provincia
		DatosContacto.DC_dropProvinciaXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropProvinciaXpath(driver).sendKeys(Provincia);
		Thread.sleep(3000);

		// Distrito
		DatosContacto.DC_dropDistritoXpath(driver).clear();
		Thread.sleep(3000);
		DatosContacto.DC_dropDistritoXpath(driver).sendKeys(Distrito);
		Thread.sleep(3000);

		// Telefono1
		DatosContacto.DC_txtTelefono1Xpath(driver).clear();
		DatosContacto.DC_txtTelefono1Xpath(driver).sendKeys(Telefono1);

		// Telefono2
		DatosContacto.DC_txtTelefono2Xpath(driver).clear();
		DatosContacto.DC_txtTelefono2Xpath(driver).sendKeys(Telefono2);

		// Email1
		DatosContacto.DC_txtEmail1Xpath(driver).clear();
		DatosContacto.DC_txtEmail1Xpath(driver).sendKeys(Email1);

		// Email2
		DatosContacto.DC_txtEmail2Xpath(driver).clear();
		DatosContacto.DC_txtEmail2Xpath(driver).sendKeys(Email2);

		// Notificaciones SI
		// DatosContacto.DC_optNotificacionesSiXpath(driver).click();

		// Notificaciones NO
		DatosContacto.DC_optNotificacionesNoXpath(driver).click();

		Thread.sleep(6000);

		DatosContacto.DC_btnAgregarXpath(driver).click();

		Thread.sleep(6000);
		
		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);

		// Assersion para verificar el boton editar

		// WebElement boton =
		// driver.findElement(By.xpath("//button[contains(.,'Editar')]"));

		AssertJUnit.assertEquals(true, DatosContacto.DC_btnEditarXpath(driver).isDisplayed());

		Reportes.EnviarStatus(LogStatus.INFO,"Agregar Otro Dato de Contacto Finalizo Correctamente");

		EscribirExcel.WriteData(0, 6, 13, Msj);
	}

	@Test
	public void T03_EditarDatosContacto() throws Exception {
		////////////// CONFIGURACION DE REPORTES////////////////////////////
		NombreReporte = "T03 Editar Datos de Contacto";
		TestCaptura = "T03_EditarDatosContacto";
		Reportes.CrearPrueba(NombreReporte);
		Reportes.EnviarStatus(LogStatus.INFO, "Se inicio Editar Datos de Contacto");

		// Borrar Datos de Contacto
		BorrarDatosdeContacto();

		// Cargar datos desde Excel
		editarDatoContacto();

		// Seleccionar el Primer elemento de la Grid
		DatosContacto.DC_optGridPrimerElementoXpath(driver).click();
		// driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[1]/div/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div")).click();
		Thread.sleep(5000);

		// Seleccionar un elemento de la Grid

		chBox = DatosContacto.DC_optGridPrimerElementoXpath(driver).getAttribute("aria-checked").equals("true");
		System.out.println(chBox);

		if (chBox == true) {
			System.out.println("chBox: El elemento ya se encuentra seleccionado");
			datosEditar();

		} else if (chBox == false)

		{
			DatosContacto.DC_optGridPrimerElementoXpath(driver).click();
			Thread.sleep(5000);
			datosEditar();
		}

		// WebElement boton =
		// driver.findElement(By.xpath("//button[contains(.,'Editar')]"));
		// Assert.assertEquals(true, boton.isDisplayed());

		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);

		// Enviar Resultado a Excel
		EscribirExcel.WriteData(0, 7, 13, Msj);
	}

	@Test
	public void T04_EliminarDatosContacto() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
		NombreReporte = "T04 Eliminar Datos de Contacto";
		TestCaptura = "T04_EliminarDatosContacto";
		Reportes.CrearPrueba(NombreReporte);
		Reportes.EnviarStatus(LogStatus.INFO, "Se inicio Eliminar Datos de Contacto");
		

		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
		Reportes.EnviarStatus(LogStatus.INFO,"Inicio de la Funcion Eliminar datos del Contacto");

		// Borrar los campos del formulario
		BorrarDatosdeContacto();
		DatosContacto.DC_optGridPrimerElementoXpath(driver).click();
		Thread.sleep(5000);

		// Seleccionar un elemento de la Grid
		chBox1 = DatosContacto.DC_optGridPrimerElementoXpath(driver).getAttribute("aria-checked").equals("true");
		System.out.println(chBox1);

		if (chBox1 == true) {
			System.out.println("chBox1: El elemento ya se encuentra seleccionado");
			// Eliminar el elemento

			DatosContacto.DC_btnEliminarXpath(driver).click();
			Thread.sleep(5000);

		} else if (chBox1 == false)

		{
			DatosContacto.DC_optGridPrimerElementoXpath(driver).click();
			Thread.sleep(15000);

			// Eliminar el elemento
			DatosContacto.DC_btnEliminarXpath(driver).click();
			Thread.sleep(15000);
		}

		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
		Reportes.EnviarStatus(LogStatus.INFO,"Se elimino el Dato de Contacto");

	}

	@Test
	public void T05_ZAvanzarAdmitirExpediente() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
		NombreReporte = "T05 Avanzar Admitir Expediente";
		TestCaptura = "T05_AvanzarAdmitirExpediente";
		Reportes.CrearPrueba(NombreReporte);
		Reportes.EnviarStatus(LogStatus.INFO, "Enviar Formulario de Admitir Expediente");
		
		

		DatosContacto.DC_btnAvanzarExpedienteXpath(driver).click();
		Thread.sleep(15000);

		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
		Reportes.EnviarStatus(LogStatus.INFO,"Avanzar Admitir Expediente, Finalizo Correctamente");

		// Esperar a crear nueva instancia del Proceso
		Thread.sleep(15000);
	}

	public void datosEditar() throws Exception

	{
		// Clic en Boton Editar
		DatosContacto.DC_btnEditarXpath(driver).click();
		Thread.sleep(15000);

		// Apellidos
		DatosContacto.DC_txtApellidosXpath(driver).clear();
		DatosContacto.DC_txtApellidosXpath(driver).sendKeys(Apellidos);

		// Nombres
		DatosContacto.DC_txtNombresXpath(driver).clear();
		DatosContacto.DC_txtNombresXpath(driver).sendKeys(Nombres);

		// Direccion
		DatosContacto.DC_txtDireccionXpath(driver).clear();
		DatosContacto.DC_txtDireccionXpath(driver).sendKeys(Direccion);

		// Guardar
		DatosContacto.DC_btnGuardarXpath(driver).click();

		Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
		Reportes.EnviarStatus(LogStatus.INFO,"Editar Datos de Contacto Finalizo Correctamente");
		Thread.sleep(15000);

	}

	public void BorrarDatosdeContacto() {

		// Tipo de Documento
		DatosContacto.DC_txtTipoDocumentoXpath(driver).clear();

		// Numero de Documento
		DatosContacto.DC_txtNumeroDocumentoXpath(driver).clear();

		// Apellidos
		DatosContacto.DC_txtApellidosXpath(driver).clear();

		// Nombres
		DatosContacto.DC_txtNombresXpath(driver).clear();

		// Direccion
		DatosContacto.DC_txtDireccionXpath(driver).clear();

		// Departamento
		DatosContacto.DC_dropDepartamentoXpath(driver).clear();

		// Provincia
		DatosContacto.DC_dropProvinciaXpath(driver).clear();

		// Distrito
		DatosContacto.DC_dropDistritoXpath(driver).clear();

		// Telefono1
		DatosContacto.DC_txtTelefono1Xpath(driver).clear();

		// Telefono2
		DatosContacto.DC_txtTelefono2Xpath(driver).clear();

		// Email1
		DatosContacto.DC_txtEmail1Xpath(driver).clear();

		// Email2
		DatosContacto.DC_txtEmail2Xpath(driver).clear();

	}

	/////// EVALUAR RESULTADOS DEL TEST//////////
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {

			Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
			Reportes.EnviarStatus(LogStatus.FAIL, "Ha fallado la Prueba");
			Msj = "NOK";
			Thread.sleep(5000);

		} else {

			Reportes.TomarCaptura(driver, LogStatus.INFO, ScreenShot_Path, TestCaptura);
			Reportes.EnviarStatus(LogStatus.PASS, "Se culmino la prueba Exitosamente");

		}

		Reportes.CerrarReporte();

	}

//////////////DATA PROVIDER//////////////
public void DatosContacto(){
	///Datos De Cabecera
	Lugar= LeerExcel.GetData(0, 3, 1);
	Tramite= LeerExcel.GetData(0, 3, 2);
	Prioridad= LeerExcel.GetData(0, 3, 3);
	
	//Datos De Contacto
	  tDocumento= 	LeerExcel.GetData(0, 5, 1);
	  nDocumento= 	LeerExcel.GetData(0, 5, 2);
	  Apellidos= 	LeerExcel.GetData(0, 5, 3);
	  Nombres= 		LeerExcel.GetData(0, 5, 4);
	  Direccion= 	LeerExcel.GetData(0, 5, 5);
	  Departamento= LeerExcel.GetData(0, 5, 6);
	  Provincia= 	LeerExcel.GetData(0, 5, 7);
	  Distrito= 	LeerExcel.GetData(0, 5, 8);
	  Telefono1= 	LeerExcel.GetData(0, 5, 9);
	  Telefono2=	LeerExcel.GetData(0, 5, 10);
	  Email1= 		LeerExcel.GetData(0, 5, 11);
	  Email2= 		LeerExcel.GetData(0, 5, 12);
	  
	  System.out.println(Lugar);
	  System.out.println(tDocumento);
}

public void otroDatoContacto(){

	///Datos De Contacto
	  tDocumento= 	LeerExcel.GetData(0, 6, 1);
	  nDocumento= 	LeerExcel.GetData(0, 6, 2);
	  Apellidos= 	LeerExcel.GetData(0, 6, 3);
	  Nombres= 		LeerExcel.GetData(0, 6, 4);
	  Direccion= 	LeerExcel.GetData(0, 6, 5);
	  Departamento= LeerExcel.GetData(0, 6, 6);
	  Provincia= 	LeerExcel.GetData(0, 6, 7);
	  Distrito= 	LeerExcel.GetData(0, 6, 8);
	  Telefono1= 	LeerExcel.GetData(0, 6, 9);
	  Telefono2= 	LeerExcel.GetData(0, 6, 10);
	  Email1= 		LeerExcel.GetData(0, 6, 11);
	  Email2= 		LeerExcel.GetData(0, 6, 12);
	  
	  System.out.println(tDocumento);
}

public void editarDatoContacto(){

	///Datos De Contacto

	  Apellidos= 	LeerExcel.GetData(0, 7, 3);
	  Nombres= 		LeerExcel.GetData(0, 7, 4);
	  Direccion= 	LeerExcel.GetData(0, 7, 5);
	 
	  System.out.println(Apellidos);
}

	////////////// NAVEGADORES//////////////

	public void IExplorer() {

		System.setProperty("webdriver.ie.driver", ".\\librerias\\IExplorer\\IEDriverServer.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URLInicial);

	}

	public void GChrome() {

		System.setProperty("webdriver.chrome.driver", ".\\librerias\\chromedriver\\chromedriver.exe");
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


