package co.spi.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Test {
	

	public static void main(String[] args) {
	   /* try {
	        FileInputStream file = new FileInputStream(new File("C:/Users/Kingshuk Nandy/workspace/Book1.xlsx"));
	       // HSSFWorkbook workbook = new HSSFWorkbook(file);
	       // HSSFSheet sheet = workbook.getSheetAt(0);
	        //XSS
	        
	        Iterator<Row> rowIterator = sheet.iterator();
	        while(rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while(cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                switch(cell.getCellType()) {
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        System.out.print(cell.getBooleanCellValue() + "\t\t");
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                        System.out.print(cell.getNumericCellValue() + "\t\t");
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                        System.out.print(cell.getStringCellValue() + "\t\t");
	                        break;
	                }
	            }
	            System.out.println("");
	        }
	        file.close();    
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException ae) {
	        ae.printStackTrace();
	    }
		
*/
	}

}
