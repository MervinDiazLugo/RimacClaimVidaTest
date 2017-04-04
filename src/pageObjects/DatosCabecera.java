package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatosCabecera {
	
	public static WebElement element = null;

	 public static WebElement DC_txtLugarNotificacionRealXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_0')]"));

		    return element;

	}	 
	 
	 public static WebElement DC_txtTipoTramiteXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_1']"));

		    return element;

	}
	 
	 public static WebElement DC_txtPrioridadXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'dijit_form_FilteringSelect_2')]"));

		    return element;

	}
	 
}

/**


*/
