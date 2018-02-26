package com.booking;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReading {

    public static void main(String[] args) {

    	ExcelReading exr= new ExcelReading();
    	exr.excelread("Air","Module","TestId");	
    	

    }
 
    public void excelread(String SheetName,String Module,String TestId){
    	
        try {
        	FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\BookingData.xls");
        	HSSFWorkbook workbook = new HSSFWorkbook(file);
        	HSSFSheet sheet = workbook.getSheet(SheetName);
        	System.out.println(SheetName);
        	Iterator<Row> rows     = sheet.rowIterator ();
        	while (rows.hasNext ())
        	{
        	HSSFRow row = (HSSFRow) rows.next ();
        	System.out.println ("Row No.: " + row.getRowNum ());
        	Iterator<Cell> cells = row.cellIterator ();        	
        	HSSFCell cell = (HSSFCell) cells.next ();
        	        	
        	if(cell.getStringCellValue().equalsIgnoreCase(Module)){
        		
        		
        	for(int p=1; p <sheet.getPhysicalNumberOfRows();p++){
        	String CINId = sheet.getRow(p).getCell((short) 1).getStringCellValue(); 
        	System.out.println(CINId);	
        		
        	}	
        	System.out.println (cell.getStringCellValue());
        	}
        	}
        	
        	
        } catch( Exception e ) {
            e.printStackTrace();
        } 	
    	
    	
    }
    

}