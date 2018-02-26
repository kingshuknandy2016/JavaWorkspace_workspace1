package com.vello.registration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jxl.Sheet;
import jxl.Workbook;

public class ParameterRegistration {
	private static String InputValue;
	
	public static String GetInput(String ModuleName, String TestId,int ColumnName ) throws FileNotFoundException {

		//FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\ObjectRepository\\ValloeTestData.xls");
		FileInputStream fi = new FileInputStream("C:\\Users\\Kingshuk Nandy\\workspace\\ValloeTestData.xls");
		System.out.println("*******************");
		
		try {
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet(ModuleName);
			for (int row = 11; row < s.getRows(); row++) {

				/***************Read The Module Name ********************************/
				String ModuleName1 = s.getCell(0, row).getContents();
				System.out.println("Module name:"+ModuleName1);

				/***************Read The test Name ********************************/
				String TestName1 = s.getCell(1, row).getContents();
				System.out.println("Test name:"+TestName1);

				if (ModuleName1.equalsIgnoreCase(ModuleName) && TestName1.equalsIgnoreCase(TestId)) {

					/***************Read The Xpath Expression Name ********************************/
					InputValue = s.getCell(ColumnName, row).getContents();
					System.out.println("Feild value:"+InputValue);
					break;
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InputValue;
		
	}

}
