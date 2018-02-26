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
 
public class OwnersDirect {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "Rental Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

private int row, i=1,qa;
private String  CountryName,Area,CityName,PropertyName,Owner,OwnerPhone,Speaks,PropertyUrl,PricePerWeek,Guest;

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
        
                baseUrl = "http://www.homeaway.co.uk";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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
  FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\HomeRentalData.xls");
  Workbook w = Workbook.getWorkbook(fi);
  Sheet s = w.getSheet(0);
  for(int row=1; row <s.getRows();row++) ///first for loop
  {
  try
  {	  
  String Country = s.getCell(1, row).getContents();
  System.out.println(Country);
  driver.findElement(By.id("searchKeywords")).sendKeys(Country);
  driver.findElement(By.xpath("//form[@id='keywordSearchForm-responsive']/button")).click();
  //Thread.sleep(500);
  CountryName=  driver.findElement(By.xpath("//ol/*[last()]")).getText();
  System.out.println(CountryName);
  
 
  /****************** Get the area list*****************************/
  List<WebElement> area=driver.findElements(By.xpath("//div[@id='linguaSibling']//ul[contains(@class,'gbs-node-list gbs-node-list-cols')]/li/a"));
  for(int p=0;p<area.size();p++)
  {
	  
	  List<WebElement> area1=driver.findElements(By.xpath("//div[@id='linguaSibling']//ul[contains(@class,'gbs-node-list gbs-node-list-cols')]/li/a"));
	  Area = area1.get(p).getText();
	  System.out.println(Area);
	  area1.get(p).click();
	  
	  Thread.sleep(500);

 /************** Get the City*************************/   
  List<WebElement> City=driver.findElements(By.xpath("//div[@id='linguaSibling']//ul[contains(@class,'gbs-node-list gbs-node-list')]/li/a"));
  for(int q=5;q<City.size();q++)
  {
  List<WebElement> City1=driver.findElements(By.xpath("//div[@id='linguaSibling']//ul[contains(@class,'gbs-node-list gbs-node-list')]/li/a"));	  
  CityName= City1.get(q).getText();
  System.out.println(CityName);
  
  City1.get(q).click();
  
  
 String Result=  driver.findElement(By.xpath("//div[@id='linguaSibling']//div[contains(@class,'number-results-text')]")).getText();
 Matcher matcher = Pattern.compile("\\d+").matcher(Result);
 if(matcher.find())
 Result = matcher.group();
 int num = Integer.parseInt(Result);
 int qar = (num/30);
 System.out.println(qar);
  
 for(int abc=0;abc<=qar;abc++)
	  
  { 
  
 // Thread.sleep(500);
  
  /********* Get the Property**********/ 
  
  List<WebElement> Property=driver.findElements(By.xpath("//h3/a[@class='listing-url']"));
 
  int prop = Property.size();
    
  for (int r=0;r<prop;r++)
  {
  List<WebElement> Property1=driver.findElements(By.xpath("//h3/a[@class='listing-url']"));
	
  Property1.get(r).click();
  //Thread.sleep(500);

  PropertyName = driver.findElement(By.xpath("//div[@id='wrapper']//h1")).getText();
  System.out.println(PropertyName);

  WebElement ownername = null;
  try
  {
  ownername=  driver.findElement(By.xpath("//h4[@class='owner-name']"));
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
  speakin=  driver.findElement(By.xpath("//div[@class='contact-info-wrapper']/div[2]"));
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
  Phonefield= driver.findElement(By.xpath("//div[@class='owner-contact-box']//a[@href='#']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (Phonefield != null)
  {
  Phonefield.click();
  
  } 
    
  WebElement emailfield = null;
  try
  {
  emailfield= driver.findElement(By.xpath("//div[@class='owner-contact-box']//input[@name='inquirerEmailAddress']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (emailfield != null)
  {
	  emailfield.sendKeys("siddharthamon@gmail.com");
	  emailfield.sendKeys(Keys.ENTER);
  }
  
  WebElement OwnerPhoneNumber = null;
  try
  {
	  OwnerPhoneNumber= driver.findElement(By.xpath("//div[@class='owner-contact-box']//div[@class='phone']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (OwnerPhoneNumber != null)
  {
	  
	  OwnerPhone = OwnerPhoneNumber.getText();
  }
  
  
  PropertyUrl =driver.getCurrentUrl();
  
  
  
  WebElement price = null;
  try
  {
	  price= driver.findElement(By.xpath("//div[@id='wrapper']//div[contains(@class,'from-price js-fromPriceContainer')]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (price != null)
  {
	  
  PricePerWeek = price.getText();
  }
  
  System.out.println(PricePerWeek);
  
    
  WebElement guest = null;
  try
  {
	  guest= driver.findElement(By.xpath("//div[@id='wrapper']//div[@class='hidden-phone hidden-tablet']/table/tbody/tr[1]/td[2]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (guest != null)
  {
	  
	  Guest = guest.getText();
  }
    
  
  System.out.println(OwnerPhone);
  
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  driver.navigate().back();
  //Thread.sleep(500);
  
  }//property
  
  WebElement NextPage =null;
  try
  {
   NextPage = driver.findElement(By.xpath("//div[@id='linguaSibling']//li[@class='next']/a"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (NextPage != null)
  {
  NextPage.click();	  
  }else {driver.navigate().back();}
  
  }//pgination
  
   
  }//city
  }//area
  
  } //end of try
  catch (Exception e)
  {
  System.out.println("error "+e);
  }      
}/// end of first for loop

}

public void SelectCurrentDate(WebDriver driver){

    DateFormat dateFormat2 = new SimpleDateFormat("dd"); 
    Date date2 = new Date();

    String today = dateFormat2.format(date2); 

    //find the calendar
    WebElement dateWidget = driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkin_monthday']"));
    dateWidget.click();
    List<WebElement> day=driver.findElements(By.xpath("//form[@id='frm']//select[@name='checkin_monthday']/option"));  

    //comparing the text of cell with today's date and clicking it.
    for (WebElement cell : day)
    {
       if (cell.getText().equalsIgnoreCase(today))
       {
          cell.click();
          break;
       }
    
    }
    
    driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkin_year_month']/option[2]")).click();
    Select dropdown = new Select(driver.findElement(By.xpath("//fieldset//select[@name='']")));
    dropdown.selectByIndex(1);
}
}
