package com.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
 
public class Booking {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "Booking Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

int row, i=1;
private String  Hotelname,Address,RoomTypeandbreakfast,Rating,Price,DateofJourneey,Distance;

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
                rowhead.createCell((short) 1).setCellValue("Hotel Name");
                rowhead.createCell((short) 2).setCellValue("Distance");
                rowhead.createCell((short) 3).setCellValue("Address");
                rowhead.createCell((short) 4).setCellValue("RoomType And Breakfast");
                rowhead.createCell((short) 5).setCellValue("Rating");
                rowhead.createCell((short) 6).setCellValue("Price");
                rowhead.createCell((short) 7).setCellValue("DateofJourneey");
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(Hotelname);
                row.createCell((short)2).setCellValue(Distance);
                row.createCell((short)3).setCellValue(Address);
                row.createCell((short)4).setCellValue(RoomTypeandbreakfast);
                row.createCell((short)5).setCellValue(Rating);
                row.createCell((short)6).setCellValue(Price);
                row.createCell((short)7).setCellValue(DateofJourneey);                
                i++;
                 }
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://www.booking.com";
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
  FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\BookingData.xls");
  Workbook w = Workbook.getWorkbook(fi);
  Sheet s = w.getSheet(0);
  for(int row=1; row <s.getRows();row++) ///first for loop
  {
  try
  {	  
  String City = s.getCell(1, row).getContents();
  System.out.println(City);
  driver.findElement(By.id("destination")).click();
  driver.findElement(By.id("destination")).sendKeys(City);
  driver.findElement(By.xpath("//ul[@role='listbox']/li[1]/a")).click();
  SelectCurrentDate(driver);
  driver.findElement(By.xpath("//div[@id='searchboxInc']//button[@type='submit']")).click();
 /* driver.get(baseUrl + "/");
  driver.manage().window().maximize();
  Thread.sleep(2000);*/
  
  driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkout_monthday']/option[2]")).click();

  /* Get the month*/
  List<WebElement> month=driver.findElements(By.xpath("//form[@id='frm']//select[@name='checkin_year_month']/option"));
  for(int p=1;p<=month.size();p++)
  {
  //month.get(p).click();
	
  String mn = driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkin_year_month']/option["+p+"]")).getText();
  
  System.out.println(mn);
  driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkin_year_month']/option["+p+"]")).click();
  Thread.sleep(200);
  driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkout_year_month']/option["+p+"]")).click();
  Thread.sleep(2000);
  
 /* Get the Day*/   
  List<WebElement> day=driver.findElements(By.xpath("//form[@id='frm']//select[@name='checkin_monthday']/option"));
  for(int q=1;q<day.size();q++)
  {
 driver.findElement(By.xpath("//form[@id='frm']//select[@name='checkin_monthday']/option[@value="+q+"]")).click();
  
  //day.get(q).click();
  
  Thread.sleep(2000); 
  Select dropdown = new Select(driver.findElement(By.xpath("//fieldset//select[@name='']")));
  dropdown.selectByIndex(1); 
  driver.findElement(By.xpath("//div[@id='searchboxInc']//button[@type='submit']")).click();
  Thread.sleep(2000);
  
  WebElement pastdatecheck = null;
  try
  {	
  pastdatecheck = driver.findElement(By.xpath("//form[@id='frm']")).findElement(By.className("b-form-group__error-messages_list-item"));
  }
  catch(Exception e)
  {	
  System.out.println("Error" +e.getMessage());
  }
  if(pastdatecheck != null)
  {  
  System.out.println("past ignore");
  }
  else 
  {
  
  driver.findElement(By.xpath("//div[@id='filter_class']//span[text()='3 stars']")).click();
  Thread.sleep(2000);
  driver.findElement(By.xpath("//a[text()='Distance from downtown']")).click();
  
  /* Get the page*/
  String pagenumber=driver.findElement(By.xpath("//div[@id='search_results_table']//ul/*[last()]")).getText();
  int lastpage = Integer.parseInt(pagenumber);
  
  for(int r=0;r<lastpage;r++)
  {	  
  System.out.println(lastpage);
  List<WebElement> hotel=driver.findElements(By.xpath("//div[@id='hotellist_inner']//div[contains(@class,'sr_item sr_item_new')]"));
  
  for (WebElement hoteldetails : hotel)
  {
  String doubleroom=  hoteldetails.findElement(By.className("room_link")).getText();
  if (doubleroom.contains("Double")){
  Hotelname = hoteldetails.findElement(By.tagName("h3")).findElement(By.tagName("a")).getText();
  System.out.println(Hotelname);
  Distance = hoteldetails.findElement(By.xpath("//span[@class='distfromdest_clean']/span")).getText();
  System.out.println(Distance);  
  Address = hoteldetails.findElement(By.className("address")).getText();
  System.out.println(Address);
  Price = hoteldetails.findElement(By.tagName("tbody")).findElement(By.xpath("tr/*[last()]")).getText();
  System.out.println(Price);
  RoomTypeandbreakfast = hoteldetails.findElement(By.tagName("tbody")).findElement(By.xpath("tr/td[2]")).getText();
  Rating = hoteldetails.findElement(By.className("reviewFloater")).findElement(By.tagName("a")).getText();
  System.out.println(Rating);
  DateofJourneey = driver.findElement(By.id("frm")).findElement(By.tagName("em")).getText();
 
  resultoutput_pass();
  }
  }
    
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  WebElement NextPage = null;
  
  try
  {
   NextPage = driver.findElement(By.xpath("//div[@id='search_results_table']//a[text()='Next page']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (NextPage != null)
  {
  NextPage.click();	  
  } 
  else{driver.get(baseUrl + "/");
  driver.manage().window().maximize(); 
  }/// for loop of hotel details end 
  }//page
  }//past date

  }//day
  }/// end of 2nd for loop month
  }//end of try
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

public  void GetBreakfast(WebDriver driver, String  BreakFastDetails){
	WebElement breakfast = null;
	try{
	breakfast = driver.findElement(By.xpath("//sup[@class='sr_room_reinforcement']"));
	}catch(Exception e)
	  {
	  }
	if(breakfast != null){		  
		BreakFastDetails= driver.findElement(By.xpath("//sup[@class='sr_room_reinforcement']")).getText();
		System.out.println(BreakFastDetails);
	  }
	else {
		BreakFastDetails="Breakfast not Included";
        } 
   
      }
    
    }



         
                          
 
