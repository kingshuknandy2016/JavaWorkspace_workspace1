package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


/*********************************Class**************************************/
public class WriteToExcel {

private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
        .getInstance().getTime());
private static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CIN Company Details " + timeStamp + " Summary" + ".xls";

private static String  CIN,CompanyName;
private static int i,j;
private static FileInputStream file;
private static HSSFWorkbook workbook;
private static HSSFSheet sheet;
private static HSSFRow row;
private static HSSFCell cell;

/*********************************Constructor
 * @throws IOException **************************************/

public WriteToExcel () throws IOException {

	file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\OutPut\\Book1.xls");
	workbook = new HSSFWorkbook(file);
	sheet = workbook.createSheet("Test");		
	
}

/*********************************Main Method**************************************/

public static void main(String[] args) throws IOException {

             
	String Name="Manish Sir";
	int i = 1;
	int j=1;
	
	WriteToExcel test = new WriteToExcel();
	
	WriteExcel(Name,i,j);
	
	
              
              
              
                   
      }
  








/********************************* Method to Write Excel *************************************/

public static  void WriteExcel(String rowcolumnvalue, int i, int j) throws IOException {
	try{	
	/*FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\OutPut\\Book1.xls");
	HSSFWorkbook workbook = null;
	workbook = new HSSFWorkbook(file);		
	HSSFSheet sheet = workbook.createSheet("Test");*/
		
		row = sheet.createRow(i);
		cell = row.createCell(j);
		cell.setCellValue(rowcolumnvalue);
	
		
		
			
		FileOutputStream out = new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\OutPut\\output.xls"));
		workbook.write(out);
		//out.flush();
				      
		}catch (Exception e)
	    {e.printStackTrace();}	             
		}
}

