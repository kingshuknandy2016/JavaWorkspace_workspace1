package com.cssmania.scrape;

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
 
public class CssMania2013 {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "CssMania2013 Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

private int row, i=1,qa;
private static String Name,URL,Twitter,Email;

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
                rowhead.createCell((short) 2).setCellValue("Twitter");
                rowhead.createCell((short) 3).setCellValue("Email");
                rowhead.createCell((short) 4).setCellValue("Url");
                
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(Name);
                row.createCell((short)2).setCellValue(Twitter);
                row.createCell((short)3).setCellValue(Email);
                row.createCell((short)4).setCellValue(URL);
                i++;
                 }
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://www.cssmania.com/galleries/2013";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	
  for(int i=0; i<60;i++) ///first for loop
  {
  try
  {
  
  
  List<WebElement> Itemlist = driver.findElements(By.xpath("//div[@id='content']//div[@class='item']//a[@rel='bookmark']"));
  	
  for(int q=0;q<Itemlist.size();q++)
  {
  List<WebElement> Itemlist1 = driver.findElements(By.xpath("//div[@id='content']//div[@class='item']//a[@rel='bookmark']"));	  
  Itemlist1.get(q).click();
  Thread.sleep(2000);
  
  WebElement HeaderName = null;
  try
  {
  HeaderName=  driver.findElement(By.xpath("//div[@id='entry-header']//h1"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (HeaderName != null)
  {
  Name = HeaderName.getText();
  System.out.println(Name);
  }
  
  WebElement TwitterAd = null;
  try
  {
  TwitterAd=  driver.findElement(By.xpath("//ul[@class='info-list']//li[contains(text(),'Twitter')]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (TwitterAd != null)
  {
  String TewtterText =  TwitterAd.getText();
  String twdandtwdid[] = TewtterText.split(":");
  String twd = twdandtwdid[0]; 
  Twitter  = twdandtwdid[1];    
  System.out.println(Twitter);
  }
  
  WebElement SiteUrl = null;
  try
  {
  SiteUrl=  driver.findElement(By.xpath("//ul[@class='info-list']//a[@rel='external nofollow']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (SiteUrl != null)
  {
  URL  = SiteUrl.getAttribute("href");    
  System.out.println(URL);
  }
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  driver.navigate().back();
  }
  
  WebElement NextPage =null;
  try
  {
   NextPage = driver.findElement(By.xpath("//div[@class='wp-pagenavi']//a[text()='»']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (NextPage != null)
  {
  NextPage.click();	
  Thread.sleep(4000);
  
  }else {
  System.out.println("last page");
  }
  }
  catch (Exception e)
  {
	  System.out.println("error "+e);
  }

  }
}
}