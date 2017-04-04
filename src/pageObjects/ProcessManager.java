package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProcessManager {
	
	public static WebElement element = null;

	 public static WebElement PM_txtUsernameID(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='username']"));

		    return element;

	}

	 public static WebElement PM_txtPasswordXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='password']"));

		    return element;

	}
	 
	 public static WebElement PM_btnLoginXpath(WebDriver driver){

		    element = driver.findElement(By.xpath("//input[@id='log_in']"));

		    return element;

	}
	 
}



/**
	  
	  
	  
**/