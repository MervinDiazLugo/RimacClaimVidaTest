package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFWorkbook wb;
	XSSFSheet sheet1;
	File src;
	public String configuracion="config";
	
	public ExcelDataConfig(String ExcelPath) throws FileNotFoundException{
		
		try {
			src= new File(ExcelPath);
			
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}


public String GetData(int sheetNumber, int row, int colunm){
	
	sheet1 = wb.getSheetAt(sheetNumber);
	
	String data=sheet1.getRow(row).getCell(colunm).getStringCellValue();
	
	return data;
	
}

public int GetRowCount(int sheetIndex)
{
	int row = wb.getSheetAt(sheetIndex).getLastRowNum()+1;
	
	return row;
}


public void WriteData(int sheetNumber, int row, int colunm, String Msj) throws IOException{
	

		sheet1 = wb.getSheetAt(sheetNumber);
		sheet1.getRow(row).createCell(colunm).setCellValue(Msj);
		FileOutputStream fout = new FileOutputStream(src);
		wb.write(fout);

	
}

public void WriteConfig(String configuracion, int row, int colunm, String Msj) throws IOException{
	

	sheet1 = wb.getSheet(configuracion);
	sheet1.getRow(row).createCell(colunm).setCellValue(Msj);
	FileOutputStream fout = new FileOutputStream(src);
	wb.write(fout);


}

public String GetConfig(String configuracion, int row, int colunm){
	
	sheet1 = wb.getSheet(configuracion);
	
	String data=sheet1.getRow(row).getCell(colunm).getStringCellValue();
	
	return data;
	
}

public void closeExcel() throws IOException{
	wb.close();
}


}