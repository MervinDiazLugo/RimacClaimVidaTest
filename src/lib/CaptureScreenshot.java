package lib;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
 
 public static String ScreenShot(WebDriver driver, String TestCaptura)
  {
  
        Date fechaActual = new Date();
        System.out.println(fechaActual);
               
        //Formateando la fecha:
        DateFormat formatoHora = new SimpleDateFormat("HHmmss");
        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String hora = formatoHora.format(fechaActual);
        String fecha = formatoFecha.format(fechaActual);
        
   try {
    TakesScreenshot ts= (TakesScreenshot)driver;
    
    File source= ts.getScreenshotAs(OutputType.FILE);
    
    String ruta= "C:\\workspace\\RimacClaimVidaTest\\Reportes\\ScreenShots"+"-"+fecha+"\\"+TestCaptura+"-"+hora+".png";
    
    File destino= new File (ruta);
    
    FileUtils.copyFile(source, destino);
    
    System.out.println("Se tomo la Captura de pantalla");
    
    return ruta;
    
   } catch (Exception e) {
    System.out.println("Error Mientras se Tomaba la Captura de Pantalla"+e.getMessage());
   }
   return TestCaptura;
   
  }

}