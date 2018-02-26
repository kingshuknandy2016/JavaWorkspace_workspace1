package com.scout.scraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
 
public class ScoutContactEngland {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "Scout Contact Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

private int row, i=1,qa;
private static String Name,Email,Url,City,Searchurl2;

File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
FirefoxProfile firefoxProfile = new FirefoxProfile();
FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);

              
public void AuroraSmokeTest() throws Exception {
                ExcelColumnName();
                getbooking();
}
 /*#######################################################
Method to export Pass & Fail Result in excel
###############################################################*/

private void ExcelColumnName() throws Exception 
                {         
                HSSFRow rowhead = sheet.createRow(0);
                rowhead.createCell((short) 0).setCellValue("Sr. No");
                rowhead.createCell((short) 1).setCellValue("Name");
                rowhead.createCell((short) 2).setCellValue("Email");
                rowhead.createCell((short) 3).setCellValue("City");
                rowhead.createCell((short) 4).setCellValue("Url");
                
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(Name);
                row.createCell((short)2).setCellValue(Email);
                row.createCell((short)3).setCellValue(City);
                row.createCell((short)4).setCellValue(Url);
                i++;
                 }
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://scouts.org.uk/home";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

 /************************************************************************************************************************
* Project:           Booking.com

* Author:     Siddhartha Mondal (QA Automation Analyst )
****************************************************     Modification Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
public void getbooking() throws Exception 
  {
 
  applicationLaunch(driver);  
  FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\EnglandScout.xls");
  Workbook w = Workbook.getWorkbook(fi);
  Sheet s = w.getSheet(0);
  for(int row=1; row <s.getRows();row++) ///first for loop
  {
  try
  {	  
  City = s.getCell(1, row).getContents();
  System.out.println(City);
  driver.findElement(By.xpath("//header[@id='header']//input[@class='textinput']")).clear();
  driver.findElement(By.xpath("//header[@id='header']//input[@class='textinput']")).sendKeys(City);
  driver.findElement(By.xpath("//header[@id='header']//input[@type='submit']")).click();
  Thread.sleep(2000);
  
  Searchurl2= driver.getCurrentUrl();
 
 
  
 String Result=  driver.findElement(By.xpath("//p[@class='results']")).getText();
 
 String intValue = Result.replaceAll("[^0-9]", "");
 System.out.println(intValue);
 int num = Integer.parseInt(intValue);
 int qar = (num/10);
 System.out.println(qar);
 for(int abc=0;abc<=qar;abc++)
	  
  { 
  
	 WebElement scoutlist = null;
	    try
	    {
	    	scoutlist=  driver.findElement(By.xpath("//div[@id='pagewrapper']//li[@class='list-item']//a"));
	    }
	    catch (Exception e)
	    {
	    System.out.println("error "+e);
	    }
	    if (scoutlist != null)
	    {
	    List<WebElement> scoutlistotal = driver.findElements(By.xpath("//div[@id='pagewrapper']//li[@class='list-item']/h4/a"));
	    
	    System.out.println(scoutlistotal.size());
	   
	    //for(int pq=0;pq<scoutlistotal.size();pq++)
	    
	    for (WebElement Prop:scoutlistotal)
	    {
	    
	   List<WebElement> scoutlistotal2 = driver.findElements(By.xpath("//div[@id='pagewrapper']//li[@class='list-item']/h4/a"));
	   System.out.println(scoutlistotal2.size());
	   
	      Actions act = new Actions(driver);
		  WebElement onElement = Prop;
		  act.contextClick(onElement).perform();
		  act.sendKeys("w").perform();
		  Thread.sleep(500);
		  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		  WebDriver ndriver= driver.switchTo().window(tabs2.get(1)); 
		  
		  
		  
		  
		  
	   ///scoutlistotal2.get(pq).click();
       Thread.sleep(1000);

	   Name = ndriver.findElement(By.tagName("h1")).getAttribute("innerHTML");
	   System.out.println(Name);

	   WebElement Email1 = null;
	   try
	   {
	 	  Email1= ndriver.findElement(By.xpath("//a[contains(text(),'@')]"));
	   }
	   catch (Exception e)
	   {
	   System.out.println("error "+e);
	   }
	   if (Email1 != null)
	   {
	 	  Email = Email1.getText();
	 	  System.out.println(Email);
	 	  
	   }
  
	   Url = ndriver.getCurrentUrl();
	   System.out.println(Url);
  
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  ndriver.close();
  driver.switchTo().window(tabs2.get(0));
  //driver.navigate().back();
  
  //driver.get(Searchurl2);
  Thread.sleep(1000);
  
  }//property
  
  WebElement NextPage =null;
  try
  {
   NextPage = driver.findElement(By.xpath("//div[1][@class='pagination']//span[contains(text(),'Next')]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (NextPage != null)
  {
  NextPage.click();
  
  }
  else {driver.get(baseUrl + "/");}  
  }
  else {driver.get(baseUrl + "/");}
  }
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }      
  }
  }

}
