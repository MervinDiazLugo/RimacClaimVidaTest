package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatosBeneficiario {
	
	public static WebElement element = null;
	
	 public static WebElement DC_txtCodPersonaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_13')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtNombreXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_14')]"));

		    return element;

	}
	
	 public static WebElement DC_txtApellidoPaternoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_15')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtApellidoMaternoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_16')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtTipoDocumentoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_12']"));

		    return element;

	}
	 
	 public static WebElement DC_txtNumeroDocumentoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_ComboBox_17')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtFechaNacimientoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'uniqName_1_6')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtSexoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_13']"));

		    return element;

	}
	 
	 public static WebElement DC_txtParentescoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_14')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtMontoXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'uniqName_2_2')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtNombreCoberturaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_15']"));

		    return element;

	}
	 
	 public static WebElement DC_btnAgregarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[@id='div_3_1_1_1_9_1_1_3_1_3']//button[.='Agregar']"));

		    return element;

	}
//////////AVANZAR/////////
	 public static WebElement DC_txtAprobarControlXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[@id='div_5_1_1']"));

		    return element;

	}	 
	 
	 public static WebElement DC_btnAvanzarXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[1]/div[4]/div/div/div[2]/div/div/div[3]/button"));

		    return element;

	}

}
