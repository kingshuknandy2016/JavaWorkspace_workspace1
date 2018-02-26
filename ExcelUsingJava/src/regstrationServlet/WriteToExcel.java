package regstrationServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {
	/*public static void main(String[] args) {
		customWrite("adada","daad","dad");
	}*/
	//String fname,l,email;
	public  void customWrite(String fname,String lname,String email){
		
		System.out.println("inside function -------");
		System.out.println("Name----"+ fname +"" + lname+" "+ " email---" + email);
		try {
			File exlFile = new File("C:/Users/ABHI/workspace/Basis.xlsx");
			// FileInputStream fis= new FileInputStream(exlFile);
			FileInputStream fi = new FileInputStream(exlFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fi);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			Row row = sheet.createRow(1);
			Cell cell = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);
			//cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(fname);
			cell1.setCellValue(lname);
			cell2.setCellValue(email);
			System.out.println("Succesfully done");
			FileOutputStream fos = new FileOutputStream(new File(
					"C:/Users/ABHI/workspace/Basis.xlsx"));
			workbook.write(fos);
		} catch (Exception e) {
			System.out.println("error------" + e);
		}
	}

}
