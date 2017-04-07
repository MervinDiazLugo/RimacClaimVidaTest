package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DatosContacto {
	public static WebElement element = null;

	 public static WebElement DC_txtTipoDocumentoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_9')]"));

		    return element;

	}	 
	 
	 public static WebElement DC_txtNumeroDocumentoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_ComboBox_3']"));

		    return element;

	}	
	 
	 public static WebElement DC_txtApellidosXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_4')]"));

		    return element;

	}	
	 
	 public static WebElement DC_txtNombresXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_5')]"));

		    return element;

	}	
	 
	 public static WebElement DC_txtDireccionXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_6')]"));

		    return element;

	}
	 
	 public static WebElement DC_OptPeruXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_0')]"));

		    return element;

	}	 
	 
	 public static WebElement DC_OptExtranjeroXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_0')]"));

		    return element;

	}	
	 
	 public static WebElement DC_dropDepartamentoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_10')]"));

		    return element;

	}
	 
	 public static WebElement DC_dropProvinciaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_11')]"));

		    return element;

	}
	 
	 public static WebElement DC_dropDistritoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_12')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtTelefono1Xpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_7')]"));

		    return element;

	}
	
	 public static WebElement DC_txtTelefono2Xpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_8')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtEmail1Xpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_9')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtEmail2Xpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_10')]"));

		    return element;

	}
	 
	 public static WebElement DC_optNotificacionesSiXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_2')]"));

		    return element;

	}
	 
	 public static WebElement DC_optNotificacionesNoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_RadioButton_3')]"));

		    return element;

	}
	 
	 public static WebElement DC_btnAgregarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[@id='div_3_1_1_1_1_1_1_3_1_3']//button[.='Agregar']"));

		    return element;

	}
	 
	 public static WebElement DC_btnGuardarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//button[contains(.,'Guardar')]"));

		    return element;

	}
	 
	 public static WebElement DC_btnEditarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//button[contains(.,'Editar')]"));

		    return element;

	}
	 
	 
	 public static WebElement DC_optGridPrimerElementoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[contains(@id,'dojox_grid_EnhancedGrid_0_rowSelector_0')]"));

		    return element;

	} 
	 
	 public static WebElement DC_btnEliminarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//button[contains(.,'Eliminar')]"));

		    return element;

	} 
	
	 public static WebElement DC_btnAvanzarExpedienteXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//button[contains(.,'Avanzar')]"));

		    return element;

	} 
	 
}
/**
 
	
**/
