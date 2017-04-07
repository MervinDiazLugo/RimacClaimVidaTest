package lib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Navegadores {
	static WebDriver driver;
	static String URLInicial;
	static String navegadorPathIE= ".\\librerias\\IExplorer\\IEDriverServer.exe";
	static String navegadorPathGC= ".\\librerias\\chromedriver\\chromedriver.exe";
	
	
	
//////////////NAVEGADORES//////////////

	public static void IExplorer() {
		 
		System.setProperty("webdriver.ie.driver", navegadorPathIE );
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	 public static void GChrome() {

		System.setProperty("webdriver.chrome.driver", navegadorPathGC);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	 
	 public static void IrURL(){
		 
		 driver.get(URLInicial);
		 
	 }
	 
	 

}
