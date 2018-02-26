package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
 
public class DirectorDataOther3 {
	

private  static int DirectorDet=1;
private static String  CIN,DIN_DPIN_PAN,NAME,CIN_LLPIN,COMPANY_LLP,DESIGNATION2,DATE_APPOINTED_IN_CURRENT_DESIGNATION;
private static String ORIGINAL_APPOINTMENT_DATE,CESSATION_DATE,COMPANY_LLP_STATUS,DEFAULTING_STATUS;
              

public static void main(String[] args) throws IOException, InterruptedException {


 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
 FirefoxProfile firefoxProfile = new FirefoxProfile();
 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb";
  driver.get(baseUrl + "");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  
  //driver.findElement(By.id("mmlink0")).click();
  //driver.findElement(By.xpath("//a[text()='Login']")).click();
  driver.findElement(By.id("reqduserid")).click();
  driver.findElement(By.id("reqduserid")).sendKeys("mymca911");
  driver.findElement(By.id("password")).click();
  driver.findElement(By.id("password")).sendKeys("P@ssw0rd");
/*  driver.findElement(By.id("reqduserCategory")).click();
  driver.findElement(By.id("//select[@id='reqduserCategory']//option[@value='REGU']")).click();*/
  
  
  Select dropdown = new Select(driver.findElement(By.id("reqduserCategory")));
  dropdown.selectByIndex(4); 
  
  Thread.sleep(20000);
  driver.findElement(By.id("Default")).click();
  
  driver.findElement(By.xpath("//table[@class='main-forms']/tbody/tr[3]/td[2]/a")).click();
 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("DIRECTORSDETAILS");
HSSFSheet sheet = workbook.getSheet("DIRECTOTHER");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell((short) 3).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("DIN")).clear();
driver.findElement(By.id("DIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(1000);

ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
WebDriver ndriver= driver.switchTo().window(tabs2.get(1));


/*WebElement chargelist = null;
try
{
	chargelist=  driver.findElement(By.xpath("//tr[contains(@class,'RowData')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}*/
//if (chargelist != null)
{
List<WebElement> Chargelist = ndriver.findElements(By.xpath("//tr[contains(@class,'RowData')]"));
for(int pq=2;pq<=Chargelist.size();pq++)
{
	sheet.getRow(DirectorDet).createCell((short) 0).setCellValue(DirectorDet);
	
	DIN_DPIN_PAN = ndriver.findElement(By.xpath("//table[1]//tr[1]/td[1]/h3")).getText();
	sheet.getRow(DirectorDet).createCell((short) 1).setCellValue(DIN_DPIN_PAN);
	
	NAME =ndriver.findElement(By.xpath("//table[2]//tr[1]/td[2]/h3")).getText();
	sheet.getRow(DirectorDet).createCell((short) 2).setCellValue(NAME);
	
	CIN_LLPIN =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[2]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 3).setCellValue(CIN_LLPIN);
	System.out.println(DIN_DPIN_PAN);
	
	COMPANY_LLP =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[3]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 4).setCellValue(COMPANY_LLP);
			
	DESIGNATION2 =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[4]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 5).setCellValue(DESIGNATION2);
		
	DATE_APPOINTED_IN_CURRENT_DESIGNATION =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[5]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 6).setCellValue(DATE_APPOINTED_IN_CURRENT_DESIGNATION);
	
	ORIGINAL_APPOINTMENT_DATE =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[6]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 7).setCellValue(ORIGINAL_APPOINTMENT_DATE);
	
	CESSATION_DATE =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[7]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 8).setCellValue(CESSATION_DATE);
	
	
	COMPANY_LLP_STATUS =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[8]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 9).setCellValue(COMPANY_LLP_STATUS);
	
	DEFAULTING_STATUS =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[9]")).getText();
	sheet.getRow(DirectorDet).createCell((short) 10).setCellValue(DEFAULTING_STATUS);
	
	 FileOutputStream out =
	          new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls"));
	      workbook.write(out);
	      DirectorDet++;
}
ndriver.close(); 
driver.switchTo().window(tabs2.get(0));

driver.findElement(By.id("reset")).click();
Thread.sleep(2000);
}
}catch (Exception e){}
}
driver.close();
}
}
