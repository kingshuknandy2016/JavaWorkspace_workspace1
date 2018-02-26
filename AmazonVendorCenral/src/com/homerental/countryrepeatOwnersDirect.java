package com.homerental;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.support.ui.Select;
 
public class countryrepeatOwnersDirect {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "Rental Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

private int row, i=1,qa;
private String  CountryName,Area,CityName,PropertyName,Owner,OwnerPhone,Speaks,PropertyUrl,PricePerWeek,Guest;


private static String PricePerWeek1,PricePerWeek2,PricePerWeek3,PricePerWeek4;

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
                rowhead.createCell((short) 1).setCellValue("Country Name");
                rowhead.createCell((short) 2).setCellValue("Area");
                rowhead.createCell((short) 3).setCellValue("City");
                rowhead.createCell((short) 4).setCellValue("Property Name");
                rowhead.createCell((short) 5).setCellValue("Owner");
                rowhead.createCell((short) 6).setCellValue("Owner Phone");
                rowhead.createCell((short) 7).setCellValue("Speaks");
                rowhead.createCell((short) 8).setCellValue("PropertyUrl");
                rowhead.createCell((short) 9).setCellValue("PricePerWeek");
                rowhead.createCell((short) 10).setCellValue("Guest");
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(CountryName);
                row.createCell((short)2).setCellValue(Area);
                row.createCell((short)3).setCellValue(CityName);
                row.createCell((short)4).setCellValue(PropertyName);
                row.createCell((short)5).setCellValue(Owner);
                row.createCell((short)6).setCellValue(OwnerPhone);
                row.createCell((short)7).setCellValue(Speaks);
                row.createCell((short)8).setCellValue(PropertyUrl);
                row.createCell((short)9).setCellValue(PricePerWeek);
                row.createCell((short)10).setCellValue(Guest);
                i++;
                 }
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://online.surreycc.gov.uk/education/schools.nsf/WebSchoolsByName?OpenForm";
                driver.get(baseUrl);
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
  Thread.sleep(2000);
  
 /* driver.findElement(By.id("btnSearch123")).click();
  Thread.sleep(2000);
  */
  /********* Get the Property**********/ 
  for (int p=2;p<=26;p++)    
  {
  
	try{  
	  
  
  List<WebElement> Property=driver.findElements(By.xpath("//ul[@class='atoz-service-listing']/li/a"));
 
  int prop = Property.size();
    
  for (int r=0;r<prop;r++)
  {
  List<WebElement> Property1=driver.findElements(By.xpath("//ul[@class='atoz-service-listing']/li/a"));
	
  Property1.get(r).click();
  Thread.sleep(1000);

  WebElement CountryName1 = null;
  
  
  
  
  try
  {
	  CountryName1=  driver.findElement(By.xpath("//div[contains(@id,'scc-main-content')]/h1"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (CountryName1 != null)
  {
	  CountryName = CountryName1.getText();
  System.out.println(CountryName);
  }
  

  WebElement ownername = null;
  try
  {
  ownername=  driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[5]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (ownername != null)
  {
  Owner = ownername.getText();
  System.out.println(Owner);
  }
  
  WebElement speakin = null;
  try
  {
  speakin=  driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[6]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (speakin != null)
  {
  Speaks = speakin.getText();
  System.out.println(Speaks);
  } 

  WebElement Phonefield = null;
  try
  {
  Phonefield= driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[10]/a"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (Phonefield != null)
  {
	  Area=Phonefield.getText();
	  System.out.println(Area);
  } 
    
  
  WebElement OwnerPhoneNumber = null;
  try
  {
	  OwnerPhoneNumber= driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[11]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (OwnerPhoneNumber != null)
  {
	  
	  OwnerPhone = OwnerPhoneNumber.getText();
  }
  
 //  PropertyUrl =driver.getCurrentUrl();
  
  
  
  WebElement addr = null;
  try
  {
	  addr= driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[12]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (addr != null)
  {
   PricePerWeek1 = addr.getText();
  }
  
 /* WebElement town = null;
  try
  {
	  town= driver.findElement(By.xpath("//div[@class='content']//div[5]//div[@class='directory-value']//span"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (town != null)
  {
   PricePerWeek2 = town.getText();
  }
  
  
  WebElement postcode = null;
  try
  {
	  postcode= driver.findElement(By.xpath("//div[@class='content']//div[6]//div[@class='directory-value']//span"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (postcode != null)
  {
 PricePerWeek3 = postcode.getText();
  }
  
  WebElement postcode1 = null;
  try
  {
	  postcode1= driver.findElement(By.xpath("//div[@class='content']//div[7]//div[@class='directory-value']//span"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (postcode1 != null)
  {
 PricePerWeek4 = postcode1.getText();
  } */
  
  
  
  PricePerWeek= PricePerWeek1;
  
  
  System.out.println(PricePerWeek);
  
  WebElement type = null;
  try
  {
	  type= driver.findElement(By.xpath("//div[contains(@id,'scc-school-details-tab-box-panel-1')]//li[1]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (type != null)
  {
	  Guest = type.getText();
  }
  
  System.out.println(Guest);  
    
  
  System.out.println(OwnerPhone);
  
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  
  driver.navigate().back();
  Thread.sleep(1000);
  }
  
  WebElement NextPage =null;
  try
  {
   NextPage = driver.findElement(By.xpath("//div[@id='scc-main-content']/div[1][@class='atoz']//li["+p+"]/a"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (NextPage != null)
  {
  NextPage.click();	  
  }else {
	  System.out.println("last page");
	 // driver.navigate().back();
	  
  }
  }
	catch (Exception e)
	  {
	  System.out.println("error "+e);
	  }
  }
  }  
  
  }
  
  
  



