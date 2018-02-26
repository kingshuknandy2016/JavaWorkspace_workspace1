package com.spi.WriteFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class WriteFunction {
	String fname, lname, email;

	
/*	  public static void main(String[] args) { 
		  WriteFunction wr=new WriteFunction(); 
		  wr.write("","ada"); }*/
	

	public static void write(String fname, String lname, String email) {
		System.out.println("Name----" + fname + "" + lname + " " + " email---"
				+ email);
		System.out.println("inside write");
		/*
		 * String file = "C:/Users/ABHI/workspace/chars.txt";
		 * 
		 * PrintWriter out = new PrintWriter(new FileWriter(file));
		 * out.println(88); out.close();
		 */
		System.out.println("inside excel");
		try {
			File exlFile = new File("C:\\Users\\Kingshuk Nandy\\workspace\\Basis.xlsx");
			// FileInputStream fis= new FileInputStream(exlFile);
			FileInputStream fi = new FileInputStream(exlFile);
			//XSSFWorkbook workbook = new XSSFWorkbook(fi);
			HSSFWorkbook workbook=new  HSSFWorkbook(fi);
			HSSFSheet sheet=workbook.getSheet("Sheet1");
			//XSSFSheet sheet = workbook.getSheet("Sheet1");
			Row row = sheet.getRow(2);
			Cell cell = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);
			cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(fname);
			cell1.setCellValue(lname);
			cell2.setCellValue(email);
			System.out.println("Succesfully done");
			FileOutputStream fos = new FileOutputStream(new File(
					"C:\\Users\\Kingshuk Nandy\\workspace\\Basis.xlsx"));
			workbook.write(fos);
		} catch (Exception e) {
			System.out.println("error------" + e);
		}

	}
	/*
	 * public static void write() {
	 * 
	 * try { File exlFile = new File("C:/Users/ABHI/workspace/Basis.xlsx");
	 * FileInputStream fis= new FileInputStream(exlFile); FileInputStream fi =
	 * new FileInputStream(exlFile); XSSFWorkbook workbook = new
	 * XSSFWorkbook(fi); XSSFSheet sheet = workbook.getSheet("Sheet1"); Row row
	 * = sheet.getRow(2); Cell cell = row.createCell(1);
	 * cell.setCellType(cell.CELL_TYPE_STRING); cell.setCellValue("abhishek");
	 * System.out.println("Succesfully done"); FileOutputStream fos = new
	 * FileOutputStream(new File("C:/Users/ABHI/workspace/Basis.xlsx"));
	 * workbook.write(fos);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * System.out.println("error--"+e); } }
	 */

}
