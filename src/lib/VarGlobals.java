package lib;

import java.io.FileNotFoundException;

public class VarGlobals {
	public static String idProject;
	public static String PathReportes;
	public static String PathProyecto;
	public static String nombreproject;
	public static String ProcessCenter;
	static ExcelDataConfig LeerConfig;
	static String configuracion;
	// Inicializar Excel
	public static String ExcelPath = PathProyecto + "\\DataProvider\\inputData.xlsx";

	
	 public static void leerExcelVarGlobals(){
		// Inicializar Excel
							configuracion= "config";
							try {
								LeerConfig = new ExcelDataConfig(ExcelPath);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								
							}
							
		idProject= 		LeerConfig.GetConfig(configuracion,1,1);
		nombreproject=	LeerConfig.GetConfig(configuracion,2,1);
		PathProyecto= 	LeerConfig.GetConfig(configuracion,3,1);
		PathReportes= 	LeerConfig.GetConfig(configuracion,4,1);
		ProcessCenter=	LeerConfig.GetConfig(configuracion,5,1);
		
		
		
	}


}
