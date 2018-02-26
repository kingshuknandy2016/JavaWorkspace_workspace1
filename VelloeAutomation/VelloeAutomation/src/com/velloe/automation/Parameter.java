package com.velloe.automation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;

public class Parameter {
	
	
	private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
	        .getInstance().getTime());
	private static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
	        + "Velloe Details " + timeStamp + " Summary" + ".xls";
	
	private static String InputValue;
	

	/*************************Method to Read Excel*******************************************************/

public static String GetInput(String ModuleName, String TestId,int ColumnName ) throws FileNotFoundException {

		//FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\ObjectRepository\\ValloeTestData.xls");
		FileInputStream fi = new FileInputStream("E:\\VelloeAutomation\\ValloeTestData.xls");
		try {
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet(ModuleName);
			for (int row = 1; row < s.getRows(); row++) {

				/***************Read The Module Name ********************************/
				String ModuleName1 = s.getCell(0, row).getContents();
				System.out.println(ModuleName1);

				/***************Read The test Name ********************************/
				String TestName1 = s.getCell(1, row).getContents();
				System.out.println(TestName1);

				if (ModuleName1.equalsIgnoreCase(ModuleName) && TestName1.equalsIgnoreCase(TestId)) {

					/***************Read The Xpath Expression Name ********************************/
					InputValue = s.getCell(ColumnName, row).getContents();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InputValue;
		
	}

public static void GetOutput(String ModuleName, String TestId, String Actual,String Status) throws FileNotFoundException {
	try {
	FileInputStream file = new FileInputStream("E:\\VelloeAutomation\\ValloeTestData.xls");
	HSSFWorkbook workbook = new HSSFWorkbook(file);
	HSSFSheet sheet = workbook.getSheet(ModuleName);
	
		for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {

			/***************Read The Module Name ********************************/
			String ModuleName1 = sheet.getRow(row).getCell(0).getStringCellValue();
			System.out.println(ModuleName1);

			/***************Read The test Name ********************************/
			String TestName1 = sheet.getRow(row).getCell(1).getStringCellValue();
			System.out.println(TestName1);

			if (ModuleName1.equalsIgnoreCase(ModuleName) && TestName1.equalsIgnoreCase(TestId)) {

				/***************Read The Xpath Expression Name ********************************/
				sheet.getRow(row).createCell((short) 5).setCellValue(Actual);
				sheet.getRow(row).createCell((short) 6).setCellValue(Status);
				/*FileOutputStream out = new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\VelloeExecutionLog.xls"));
				      workbook.write(out);*/
			}
		}
		FileOutputStream out = new FileOutputStream(new File("E:\\VelloeAutomation\\ValloeTestData.xls"));
	      workbook.write(out);
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

}