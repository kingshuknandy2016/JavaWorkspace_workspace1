package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class DirectorData2 {
	
private static String  CIN,CompanyName;
              

public static void main(String[] args) throws IOException {


 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
 FirefoxProfile firefoxProfile = new FirefoxProfile();
 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=39";
  driver.get(baseUrl + "");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("MASTERDATA");
HSSFSheet sheet = workbook.getSheet("DIRECTORSDETAILS");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell(1).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("companyCIN")).clear();
driver.findElement(By.id("companyCIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(2000);

WebElement chargelist = null;
try
{
	chargelist=  driver.findElement(By.xpath("//table[@class='main-forms']//tr[contains(@class,'RowData')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (chargelist != null)
{
	List<WebElement> TotalTableRows = driver.findElements(By.xpath("//table[@class='main-forms']//tr[contains(@class,'RowData')]"));
	//List<WebElement> TotalTableRows = driver.findElements(By.xpath("//table[@class='main-forms']//tr[@class='RowData' or @class='RowDataS']"));

System.out.println(TotalTableRows.size());

//for (WebElement chglist:Chargelist)
for(int rownum=1;rownum<TotalTableRows.size();rownum++)
{
	
	List<WebElement> columns=TotalTableRows.get(rownum).findElements(By.tagName("td"));
	System.out.println("Number of columns:"+columns.size());
	
	for(int cnum=0;cnum<columns.size();cnum++)
	{
			
		
		try{
		
		
		
		sheet.createRow(rownum).createCell(0).setCellValue(rownum);
		
		
		CIN = driver.findElement(By.id("companyCIN")).getAttribute("value");
		sheet.createRow(rownum).createCell(1).setCellValue(CIN);
				
		CompanyName =driver.findElement(By.id("companyName")).getAttribute("value");
		sheet.createRow(rownum).createCell(2).setCellValue(CompanyName);
		
		String colvalue= columns.get(cnum).getText();
		System.out.println(colvalue);
		
		sheet.createRow(rownum).createCell((cnum+3)).setCellValue(colvalue);

		
		 FileOutputStream out =
		          new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData1.xls"));
		      workbook.write(out);
		      Thread.sleep(1000);
		      
		} catch(Exception e) {e.printStackTrace();}
		  
	}
	
  }driver.findElement(By.id("button6")).click();
	Thread.sleep(1000);

}
else {driver.get(baseUrl + "");}

}catch (Exception e){e.printStackTrace();}
}
driver.close();
}
}


