package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatosSiniestro {
	public static WebElement element = null;

	 public static WebElement DC_txtTipoSiniestroXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_10']"));

		    return element;

	}
	 
	 public static WebElement DC_txtTTipoPagoSiniestroXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='dijit_form_FilteringSelect_11']"));

		    return element;

	}
	 
	 public static WebElement DC_txtFechaOcurrenciaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[contains(@id,'uniqName_1_4')]"));

		    return element;

	}
	 
	 public static WebElement DC_txtDescripcionGLXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[1]/div[3]/div[1]/div/div[3]/div[1]/div/div/div/div[8]/div/div/div/div[3]/div/div/div/textarea"));

		    return element;

	}

}
