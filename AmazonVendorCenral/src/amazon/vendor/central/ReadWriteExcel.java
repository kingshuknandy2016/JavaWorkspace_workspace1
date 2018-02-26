package amazon.vendor.central;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadWriteExcel {

	public static void main(String[] args) throws IOException {
	
	File excel= new File("");
	FileInputStream fis = new FileInputStream(excel);
	HSSFWorkbook wb = new HSSFWorkbook(fis);
	HSSFSheet ws = wb.getSheet("");
	
	int rowNum = ws.getLastRowNum()+1;
	int colNum = ws.getRow(0).getLastCellNum();
	int colNumss = 3;
	String [][][] data = new String [rowNum][colNum][colNumss];
	
	

	}

}
