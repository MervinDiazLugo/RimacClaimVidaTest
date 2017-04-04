package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcessPortal {

	public static WebElement element = null;

	 public static WebElement PM_NuevaTareaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//a[contains(.,'An�lisis y Coberturas Vida')]"));

		    return element;

	}
	 
	 public static WebElement PM_MostrarTareaXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//button[@title='Mostrar m�s tareas.']"));

		    return element;

	}
	 
	 public static WebElement PM_ReclamarTareaID(WebDriver driver){

		    element = driver.findElement(By.xpath("//div[@class='bpm-task-dialog-claimButtonDiv']//button[.='Reclamar tarea']"));

		    return element;

	}
	
	

/**

 
 	
 */
}
