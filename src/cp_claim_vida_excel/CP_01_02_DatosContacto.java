package cp_claim_vida_excel;
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
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CP_01_02_DatosContacto  {
  WebDriver driver;
  boolean acceptNextAlert = true,  isPresente, chBox, chBox1;
  StringBuffer verificationErrors = new StringBuffer();
  String NombreReporte, TestCaptura, baseUrl, ScreenShot_Path, tag1, Msj, URLInicial, ID;
static String IdTarea1;
  String ExcelPath= ".\\DataProvider\\inputData.xlsx";
  int CaptureN, Reporte;
  ExtentReports report;
  ExtentTest TestBPM; 
  ExcelDataConfig EscribirExcel, LeerExcel;

//Declaracion de Datos Excel Datos de Cabecera
  String Lugar, Tramite, Prioridad;
  
  //Declaracion de Datos Excel Datos Contacto
  String tDocumento, nDocumento, Apellidos, Nombres, Direccion, Departamento, Provincia, Distrito, Telefono1, Telefono2, Email1, Email2;
  
  

	//http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=1756%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe


	

@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//Inicializar Reporte
	NombreReporte= "Admitir Expediente";  
	report= new ExtentReports(".\\Reportes\\" + NombreReporte + ".html");
	TestBPM=report.startTest(NombreReporte);
	
	//Inicializar Excel
	LeerExcel = new ExcelDataConfig(ExcelPath);
	EscribirExcel = new ExcelDataConfig(ExcelPath);
	
	 //Inicializa tarea segun ID de Tarea
	//Capturar IDtarea desde el Metodo	
	//ID=String.valueOf(CP_00_IniciarTarea.Tarea);
	
	//Capturar IDtarea desde Excel
	ID= LeerExcel.GetData(0, 9, 1);
	
	
	System.out.println(ID);
  	IdTarea1= ID;

  	URLInicial= "http://bpm8502fix:9080/teamworks/redirect-login.jsp?credentials=bWVydmluZA%3D%3D%3AMTIzNDU2&j_forward=process.lsw?zWorkflowState=1%26zTaskId=" + IdTarea1 + "%26applicationId=2%26applicationInstanceId=guid:850bbec95ddcfaaf:7300daf5:15aa3b068d2:-7ffe";
  	
  	/**
		 String IdTarea2= "2037";
		 System.out.println(IdTarea2);
	*/
  	GChrome();
  }
	
  @Test 
  public void T01_AgregarDatosConctacto() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////	  
	NombreReporte= "T01 Agregar Datos de Conctacto";
	TestCaptura="T01_AgregarDatosContacto";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Agregar Datos de Conctacto");
	
	//Cargar Matriz de Excel 
		DatosContacto();
  
		//driver.switchTo().frame(2);
	
	//Lugar Notificación Real
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_0')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_0')]")).sendKeys(Lugar);
    
	//Tipo Trámite 
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_1']")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_1']")).sendKeys(Tramite);
	
	//Prioridad
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_2')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_2')]")).sendKeys(Prioridad);
    
	// Tipo de Documento
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]")).sendKeys(tDocumento);
    
	// Numero de Documento
	driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']")).sendKeys(nDocumento);
    
	// Apellidos
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).sendKeys(Apellidos);
    
	// Nombres
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).sendKeys(Nombres);
	
	//Direccion
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).sendKeys(Direccion);
    
	//Pais Perú
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_0')]")).click();
    
    //Extranjero
    //driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_1')]")).click();
    
    //Departamento
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]")).clear();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]")).sendKeys(Departamento);
    Thread.sleep(3000);
    
    //Provincia
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]")).clear();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]")).sendKeys(Provincia);
    Thread.sleep(3000);
    
    //Distrito
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]")).clear();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]")).sendKeys(Distrito);
    Thread.sleep(3000);
    
    //Telefono1
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]")).sendKeys(Telefono1);
    
    //Telefono2
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]")).sendKeys(Telefono2);
    
    //Email1
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]")).sendKeys(Email1);
    
    //Email2
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]")).sendKeys(Email2);
    
    //Notificaciones SI
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_2')]")).click();
    
    //Notificaciones NO
    //driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_3')]")).click();
    
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div[@id='div_3_1_1_1_1_1_1_3_1_3']//button[.='Agregar']")).click();
    
    Thread.sleep(20000);
    
	
  //Recargar la pagina
    
    WebElement boton = driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div/div/div/div/div[1]/div/div/div/div[3]/div/div/div[4]/button"));
    AssertJUnit.assertEquals(true, boton.isDisplayed());
    
    //Enviar Valor a Excel
    EscribirExcel.WriteData(0, 3, 13, Msj);
    EscribirExcel.WriteData(0, 5, 13, Msj);
    
    ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
    TestBPM.log(LogStatus.INFO, "Agregar Datos de Contacto Finalizo Correctamente", ScreenShot_Path);
    

	
  }
 

  @Test
  public void T02_AgregarOtroDatoConctacto() throws Exception {
//////////////CONFIGURACION DE REPORTES////////////////////////////
	TestBPM.log(LogStatus.INFO, "Se completo el paso Cargar Listas del proceso");
	NombreReporte= "T02 Agregar Otro Dato de Conctacto";
	TestCaptura="T02_AgregarOtroDatoConctacto";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Agregar Otro Dato de Conctacto");
	
	//Cargar Matriz de Excel
		otroDatoContacto();
	
	    
		// Tipo de Documento
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]")).sendKeys(tDocumento);
	    
		// Numero de Documento
		driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']")).clear();
		driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']")).sendKeys(nDocumento);
	    
		// Apellidos
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).sendKeys(Apellidos);
	    
		// Nombres
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).sendKeys(Nombres);
		
		//Direccion
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).sendKeys(Direccion);
	    
		//Pais Perú
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_0')]")).click();
	    
	    //Extranjero
	    //driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_1')]")).click();
	    
	    //Departamento
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]")).clear();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]")).sendKeys(Departamento);
	    Thread.sleep(3000);
	    
	    //Provincia
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]")).clear();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]")).sendKeys(Provincia);
	    Thread.sleep(3000);
	    
	    //Distrito
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]")).clear();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]")).sendKeys(Distrito);
	    Thread.sleep(3000);
	    
	    //Telefono1
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]")).clear();
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]")).sendKeys(Telefono1);
	    
	    //Telefono2
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]")).clear();
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]")).sendKeys(Telefono2);
	    
	    //Email1
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]")).clear();
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]")).sendKeys(Email1);
	    
	    //Email2
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]")).clear();
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]")).sendKeys(Email2);
	    
	    //Notificaciones SI
	    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_2')]")).click();
	    
	    //Notificaciones NO
	    //driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_3')]")).click();
	    
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//div[@id='div_3_1_1_1_1_1_1_3_1_3']//button[.='Agregar']")).click();
	    
	    Thread.sleep(20000);
    
	
  //Assersion para verificar el boton editar
    WebElement boton = driver.findElement(By.xpath("//button[contains(.,'Editar')]"));
    AssertJUnit.assertEquals(true, boton.isDisplayed());
    
    ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
    TestBPM.log(LogStatus.INFO, "Agregar Datos de Contacto Finalizo Correctamente", ScreenShot_Path);
	
    EscribirExcel.WriteData(0, 6, 13, Msj);
  }

  @Test
  public void T03_EditarDatosContacto() throws Exception {
  //////////////CONFIGURACION DE REPORTES////////////////////////////
	 NombreReporte= "T03 Editar Datos de Conctacto";
	 TestCaptura="T03_EditarDatosContacto";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Editar Datos de Conctacto");

	//Borrar Datos de Contacto
		BorrarDatosdeContacto();
		
		//Cargar datos desde Excel 
		editarDatoContacto();
		
        driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).click();
        //driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[1]/div/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div")).click();
    	Thread.sleep(5000);
	
    	//Seleccionar un elemento de la Grid
	
    chBox = driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).getAttribute("aria-checked").equals("true");
	System.out.println(chBox);
	
		 if (chBox==true)
		 {
			 System.out.println("chBox: El elemento ya se encuentra seleccionado");
			 datosEditar();
	         
	     } else if (chBox==false)
	     
	     {
	    	 driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).click();
	    	 Thread.sleep(20000);
	    	 datosEditar();
	     }
		
	    //WebElement boton = driver.findElement(By.xpath("//button[contains(.,'Editar')]"));
	   // Assert.assertEquals(true, boton.isDisplayed());

		ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
	    TestBPM.log(LogStatus.INFO, "Editar Datos de Contacto Finalizo Correctamente", ScreenShot_Path);
	    
	    //Enviar Resultado a Excel
	    EscribirExcel.WriteData(0, 7, 13, Msj);
    }

  @Test
  public void T04_EliminarDatosContacto() throws Exception {
	NombreReporte= "T04 Eliminar Datos de Conctacto";
	TestCaptura="T04_EliminarDatosContacto";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Eliminar Datos de Conctacto");
	
	ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura.concat(Integer.toString(CaptureN++))) + ">"; 
    TestBPM.log(LogStatus.INFO, "Inicio de la Funcion Eliminar datos del Contacto", ScreenShot_Path);
	
	//Borrar los campos del formulario
    BorrarDatosdeContacto();
    driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).click();
    //driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div/div/div/div/div[1]/div/div/div/div[1]/div/div/div[1]/div/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div")).click();
	Thread.sleep(5000);
	
	 //Seleccionar un elemento de la Grid
	chBox1 = driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).getAttribute("aria-checked").equals("true");
	System.out.println(chBox1);
	
		 if (chBox1==true)
		 {
			 System.out.println("chBox1: El elemento ya se encuentra seleccionado");
			//Eliminar el elemento
			 
			driver.findElement(By.xpath("//button[contains(.,'Eliminar')]")).click();
			Thread.sleep(20000);
	         
	     } else if (chBox1==false)
	     
	     {
	    	 driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]")).click();
	    	 Thread.sleep(15000);
	    	 
	    	//Eliminar el elemento
			driver.findElement(By.xpath("//button[contains(.,'Eliminar')]")).click();
			Thread.sleep(20000);
	     }
		
		
		
		 ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">";
		 TestBPM.log(LogStatus.INFO, "Se elimino el Dato de Contacto", ScreenShot_Path);

    }

  @Test
  public void T05_ZAvanzarAdmitirExpediente() throws Exception {
	NombreReporte= "T05 Avanzar Admitir Expediente";
	TestCaptura="T05_AvanzarAdmitirExpediente";
	TestBPM=report.startTest(NombreReporte);
	TestBPM.log(LogStatus.INFO, "Enviar Formulario de Admitir Expediente");
		
		driver.findElement(By.xpath("//button[contains(.,'Avanzar')]")).click();
		Thread.sleep(15000);
		
		ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
	    TestBPM.log(LogStatus.INFO, "Avanzar Admitir Expediente, Finalizo Correctamente", ScreenShot_Path);
		
	    //Esperar a crear nueva instancia del Proceso
	    Thread.sleep(30000);
    }
   
   
public void datosEditar() throws Exception  

{
	//Clic en Boton Editar
	driver.findElement(By.xpath("//button[contains(.,'Editar')]")).click();
	Thread.sleep(15000);
	
	// Apellidos
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).sendKeys(Apellidos);
    
	// Nombres
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).sendKeys(Nombres);
	
	//Direccion
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).sendKeys(Direccion);

	//Guardar
	driver.findElement(By.xpath("//button[contains(.,'Guardar')]")).click();
	
	
	ScreenShot_Path= "<img src="+ CaptureScreenshot.ScreenShot(driver, TestCaptura) + ">"; 
    TestBPM.log(LogStatus.INFO, "Editar Datos de Contacto Finalizo Correctamente", ScreenShot_Path);
	Thread.sleep(20000);
	
}

public void BorrarDatosdeContacto(){
	
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]")).clear();
	driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]")).clear();
	driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]")).clear();
    driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]")).clear();
}
  

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
		  
		  Msj="OK";
	  }
	  
	  report.endTest(TestBPM);
	  report.flush();
	  
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


