package com.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
 
public class CalendarAndBookings {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Calendar and Bookings " + timeStamp + " Summary" + ".xls";
static HSSFWorkbook hwb = new HSSFWorkbook();
static HSSFSheet sheet = hwb.createSheet("Summary");
static String ScreenShotfilePath ="E:\\Selenium_Practise\\Aurora_Automation\\Screen\\";
static int row, i=1;
static String  TestCaseName,Expected,Actual,Status,month,year,Day,MonthYear,previousMonthYear;

static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
static FirefoxProfile firefoxProfile = new FirefoxProfile();
static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);


/***********************************************************
Execution Start Here
*********************************************************************/ 

public static void main(String[] args)  throws Exception {
                ExcelColumnName();
                CredentialValidation();
}
 /***********************************************************
Method to export Pass & Fail Result in excel
*********************************************************************/

private static void ExcelColumnName() throws Exception 
                {         
                HSSFRow rowhead = sheet.createRow(0);
                rowhead.createCell(0).setCellValue("Sr. No");
                rowhead.createCell(1).setCellValue("Test Case Name");
                rowhead.createCell(2).setCellValue("Expected");
                rowhead.createCell(3).setCellValue("Actual");
                rowhead.createCell(4).setCellValue("Status");
                }


 public static  void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(TestCaseName);
                row.createCell(2).setCellValue(Expected);
                row.createCell(3).setCellValue(Actual);
                row.createCell(4).setCellValue(Status);
                FileOutputStream fileOut = new FileOutputStream(filename);
                hwb.write(fileOut);            
                i++;
                 }
 
 
 public static void takeScreenShot(String TestCaseName) {
 
 	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     try 
     {
	 FileUtils.copyFile(scrFile, new File(ScreenShotfilePath+TestCaseName+".png"));
	 } 
     catch (IOException e)
     {
	 e.printStackTrace();
	 }
 }
 
 
 
/********************************************************
Launch Application
***************************************************************/
public static void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "https://sqa.velloe.com";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}

 /************************************************************************************************************************
* Project:           Velloe Automation Script

* Author:     Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/


public static void CredentialValidation() throws Exception 
  {
 
  applicationLaunch(driver);
   
  Calendar cal = Calendar.getInstance();
  SimpleDateFormat sdf= new SimpleDateFormat("MMM");
  month=sdf.format(cal.getTime());
  System.out.println(month);  
  
  SimpleDateFormat sdf1= new SimpleDateFormat("YYYY");
  year=sdf1.format(cal.getTime());
  System.out.println(year);
  
  SimpleDateFormat sdf2= new SimpleDateFormat("dd");
  String day=sdf2.format(cal.getTime());
  Day= day.replaceFirst("^0+(?!$)", "");
  
  SimpleDateFormat sdf3= new SimpleDateFormat("MMMM YYYY");
  MonthYear=sdf3.format(cal.getTime());
  System.out.println(MonthYear);
  
  cal.add(Calendar.DATE, 1);
  SimpleDateFormat sdf4= new SimpleDateFormat("MM/dd/YYYY");
  String tommorrowDate=sdf4.format(cal.getTime());
  System.out.println(tommorrowDate);
  
  cal.add(Calendar.MONTH ,-1);
  previousMonthYear  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());
  System.out.println(previousMonthYear);
  
  cal.add(Calendar.MONTH ,+2);
  String NextMonthYear  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());
  System.out.println(NextMonthYear);
  
    Calendar cal1 = Calendar.getInstance(); 	
	cal1.add(Calendar.DATE, -1);
	SimpleDateFormat sdf5= new SimpleDateFormat("MM/dd/YYYY");
	String yesterdayDate=sdf5.format(cal1.getTime());
	System.out.println(yesterdayDate);
	
	Calendar calendar = Calendar.getInstance();
	Date date = calendar.getTime();
    String todayw = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    System.out.println(todayw);
  
  /********* Test Case-1: *********************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate that after login by Default Calendar Page is displayed
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  WebElement UserName = driver.findElement(By.id("input_17_1"));
  UserName.click();
  UserName.clear();
  UserName.sendKeys("Siddrock12345@gmail.com");
  
  WebElement PassWord = driver.findElement(By.id("input_17_2"));
  PassWord.click();
  PassWord.clear();
  PassWord.sendKeys("password");
  
  WebElement LoginButton = driver.findElement(By.id("gform_submit_button_17"));
  LoginButton.click();
  Thread.sleep(4000);
     
  WebElement Calendar = null;
  try {
	  
  driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
  
  Calendar =driver.findElement(By.id("calendar")).findElement(By.tagName("h2"));
 
  if(Calendar.isDisplayed()){
  String CalendarDate=Calendar.getText();	  
  TestCaseName = "TC001_Validate that after login by Default Calendar Page is displayed";
  Expected ="“After login by Default Calendar Page is displayed";
  Status= "Pass";
  Actual= "Calendar Page with date "+CalendarDate+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC001_Validate that after login by Default Calendar Page is displayed";
  Expected ="“After login by Default Calendar Page is displayed";	  
  Status= "Fail";
  Actual= "“by Default Calendar Page is not displayed”";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC001_Validate that after login by Default Calendar Page is displayed";
	  Expected ="“After login by Default Calendar Page is displayed";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
   
 System.out.println("TC_001");
 
  /********* Test Case-2: *********************************************/
 
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate all required widgets, buttons and check boxes
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
 WebElement ResrcChkbx = null;
 WebElement ServicesChkbx = null;
 WebElement Weekbutton = null;
 WebElement monthbutton = null;
 WebElement daybutton = null;
 WebElement agendabutton = null;
 
  try {
	  
  ResrcChkbx = driver.findElement(By.id("ul_resource"));
  ServicesChkbx = driver.findElement(By.id("ul_services"));	  
  Weekbutton = driver.findElement(By.xpath("//button[text()='week']"));
  monthbutton = driver.findElement(By.xpath("//button[text()='month']"));
  daybutton = driver.findElement(By.xpath("//button[text()='day']"));
  agendabutton = driver.findElement(By.xpath("//button[text()='agenda']"));
  	  
  if(ResrcChkbx.isDisplayed()&&ServicesChkbx.isDisplayed()&&Weekbutton.isDisplayed()&&monthbutton.isDisplayed()&&daybutton.isDisplayed()&&agendabutton.isDisplayed())
  {
  String  ResrcChkbxlabel =driver.findElement(By.xpath("//label[@for='ul_resource']")).getText();
  String  ServicesChkbxlabel =driver.findElement(By.xpath("//label[@for='ul_services']")).getText();
  
  TestCaseName = "TC002_Validate all required widgets, buttons and check boxes";
  Expected ="All required widgets, buttons and checkboxes should be presented";	  
  Status= "Pass";
  Actual= "Check boxes '"+ResrcChkbxlabel+"' and '"+ServicesChkbxlabel+"' and Button '"+Weekbutton.getText()+"','"
  		+monthbutton.getText()+ "','"+daybutton.getText()+"','"+agendabutton.getText()+" are displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC002_Validate all required widgets, buttons and check boxes";
  Expected ="All required widgets, buttons and checkboxes should be presented";  
  Status= "Fail";
  Actual= "All required widgets, buttons and checkboxes are not presented";
  resultoutput_pass();
  }
   } catch (Exception e){
	  TestCaseName = "TC002_Validate all required widgets, buttons and check boxes";
	  Expected ="All required widgets, buttons and checkboxes should be presented"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
   }
  System.out.println("TC_002"); 
  
  /********* Test Case-3: **************************************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate The current month and year
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On:
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  
  
  try {
  String CalendarDate=Calendar.getText();    
  if(CalendarDate.startsWith(month)&&CalendarDate.endsWith(year)){	  
  TestCaseName = "TC003_Validate The current month and year";
  Expected ="The current month and year should be  displayed on top of the Calendar";	  
  Status= "Pass";
  Actual= "Current month and year '"+CalendarDate+"' is displayed on top of the Calendar";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC003_Validate The current month and year";
  Expected ="The current month and year should be  displayed on top of the Calendar";	  
  Status= "Fail";
  Actual= "Current month and year is not displayed on top of the Calendar";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC003_Validate The current month and year";
	  Expected ="The current month and year should be  displayed on top of the Calendar";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
   
  System.out.println("TC_003");
  
/********* Test Case-4: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate the default Calendar view
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  
  WebElement SunDayWeekView=null;
  
  try {
  String CalendarDate=Calendar.getText();

  SunDayWeekView=driver.findElement(By.xpath("//th[contains(text(),'Sun')]")); 
  
  if(SunDayWeekView.isDisplayed()){
  TestCaseName = "TC004_Validate the default Calendar view";
  Expected ="The Default Calendar view should be in Week view";	  
  Status= "Pass";
  Actual= "The Default Calendar view is in Week view for:: '"+CalendarDate;
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC004_Validate the default Calendar view";
  Expected ="The Default Calendar view should be in Week view";		  
  Status= "Fail";
  Actual= "The Default Calendar view is not in Week view";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC004_Validate the default Calendar view";
	  Expected ="The Default Calendar view should be in Week view";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  
  System.out.println("TC_004");
  
  /********************************* Test Case-5: ********************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when Month button is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
   try {
  monthbutton.click();
  String monthyear = driver.findElement(By.id("calendar")).findElement(By.tagName("h2")).getText();
  if(monthyear.equalsIgnoreCase(MonthYear)){
  TestCaseName = "TC005_Validate when Month button is clicked";
  Expected ="The calendar should be presented in month view";	  
  Status= "Pass";
  Actual= "The calendar of '"+monthyear+"'  is presented in month view";
  resultoutput_pass();
 
  }
  else{
  TestCaseName = "TC005_Validate when Month button is clicked";
  Expected ="The calendar should be presented in month view";		  
  Status= "Fail";
  Actual= "The calendar is not presented in month view";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC005_Validate when Month button is clicked";
	  Expected ="The calendar should be presented in month view";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  System.out.println("TC_005");
  
  
  
  /********************************* Test Case-6: ********************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate the date highlighted in calendar
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  WebElement highltdate =null;
  try {
 
  highltdate =  driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2] "));   	  
  String tdate =highltdate.getText();
  if(tdate.equalsIgnoreCase(Day)){  
  TestCaseName = "TC006_Validate the date highlighted in calendar";
  Expected ="Today’s date should be highlighted in calendar";	  
  Status= "Pass";
  Actual= "Today’s date '"+tdate+"' is highlighted in calendar";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC006_Validate the date highlighted in calendar";
  Expected ="Today’s date should be highlighted in calendar";		  
  Status= "Fail";
  Actual= "Date '"+tdate+"' is highlighted in calendar";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC006_Validate the date highlighted in calendar";
	  Expected ="Today’s date should be highlighted in calendar";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  System.out.println("TC_006"); 
  
  
  /********* Test Case-7: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when when back button is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/ 
  Thread.sleep(4000);

  WebElement Previousbuttn=null;
  try {  
  Previousbuttn =  driver.findElement(By.xpath("//div[@id='calendar']//button[contains(@class,'fc-prev-button')]"));
  Previousbuttn.click(); 
  String prevmonthyear = driver.findElement(By.id("calendar")).findElement(By.tagName("h2")).getText();

  if(prevmonthyear.equalsIgnoreCase(previousMonthYear)){
  TestCaseName = "TC007_Validate when when back button is clicked";
  Expected ="The calendar should displays previous month";	  
  Status= "Pass";
  Actual= "The calendar displays previous month '"+prevmonthyear+"'";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC007_Validate when when back button is clicked";
  Expected ="The calendar should displays previous month";		  
  Status= "Fail";
  Actual= "The calendar displays month '"+prevmonthyear+"'";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC007_Validate when when back button is clicked";
	  Expected ="The calendar should displays previous month";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  
  System.out.println("TC_007");
  
/********* Test Case-8: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when when front button is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
      Thread.sleep(4000);
      
      WebElement Nextbuttn=null;
      try {  
      Nextbuttn =  driver.findElement(By.xpath("//div[@id='calendar']//button[contains(@class,'fc-next-button')]"));
      Nextbuttn.click();
      driver.findElement(By.xpath("//div[@id='calendar']//button[contains(@class,'fc-next-button')]")).click();
      String nextmonthyear = driver.findElement(By.id("calendar")).findElement(By.tagName("h2")).getText();
          
      if(nextmonthyear.equalsIgnoreCase(NextMonthYear)){
      TestCaseName = "TC008_Validate when when front button is clicked";
      Expected ="The calendar should displays next month";	  
      Status= "Pass";
      Actual= "The calendar displays next month '"+nextmonthyear+"'";
      resultoutput_pass();    
      }
      else{
      TestCaseName = "TC008_Validate when when front button is clicked";
      Expected ="The calendar should displays next month";		  
      Status= "Fail";
      Actual= "The calendar displays month '"+nextmonthyear+"'";
      resultoutput_pass();
      takeScreenShot(TestCaseName);
      }
      } catch (Exception e){
          TestCaseName = "TC008_Validate when when front button is clicked";
          Expected ="The calendar should displays next month";	
    	  Status= "Fail";
    	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
    	  resultoutput_pass();
    	  takeScreenShot(TestCaseName);
      }
	  
	  System.out.println("TC_008");
	  
/********* Test Case-9: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when Today button is clicked
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                        
	   ************************************************************************************************************************/	  
	   	  
	      Thread.sleep(4000);
	  
	      WebElement todayButton1=null;
	      WebElement todaysdate =null;
	      try {
	      todayButton1 = driver.findElement(By.xpath("//div[@id='calendar']//button[text()='today']"));
	      todayButton1.click();
	      todaysdate =  driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2] "));   	  
		  String tdate =todaysdate.getText();
		  if(tdate.equalsIgnoreCase(Day)){  
		  TestCaseName = "TC009_Validate when Today button is clicked";
		  Expected ="The calendar should go to Today’s date";	  
		  Status= "Pass";
		  Actual= "The calendar goes to Today’s date '"+tdate+"'";
		  resultoutput_pass();
		  }
		  else{
		  TestCaseName = "TC009_Validate when Today button is clicked";
		  Expected ="The calendar should go to Today’s date";		  
		  Status= "Fail";
		  Actual= "The calendar goes to date '"+tdate+"'";
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
		  }
		  } catch (Exception e){
			  TestCaseName = "TC009_Validate when Today button is clicked";
			  Expected ="The calendar should go to Today’s date";		
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		  }
	  
	  System.out.println("TC_009");
	  
	  
	  
/********* Test Case-10: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when “day” button is clicked
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/  
		  
	  Thread.sleep(4000);
      WebElement DayButton=null;
      try {
	  
      DayButton=driver.findElement(By.xpath("//div[@id='calendar']//button[text()='day']"));
      DayButton.click();
      String MonthDayYear = driver.findElement(By.id("calendar")).findElement(By.tagName("h2")).getText();
	  
	  if(MonthDayYear.startsWith(month)&&MonthDayYear.endsWith(year)&&MonthDayYear.contains(Day)){	  
	  TestCaseName = "TC010_alidate when “day” button is clicked";
	  Expected ="The calendar should be presented in day view";	  
	  Status= "Pass";
	  Actual= "The calendar is presented in day view for day '"+MonthDayYear+"'";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC010_alidate when “day” button is clicked";
	  Expected ="The calendar should be presented in day view";			  
	  Status= "Fail";
	  Actual= "The calendar is presented in day view for day '"+MonthDayYear+"'";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	  } catch (Exception e){
		  TestCaseName = "TC010_alidate when “day” button is clicked";
		  Expected ="The calendar should be presented in day view";			
		  Status= "Fail";
		  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }

	  System.out.println("TC_010");
	  
	  
	  /********* Test Case-11: *********************************************/
	  /* Project  : Velloe Automation Script     need to modify
	   * Module   : Authentication
	   * Test Case: Validate when time of a Day is clicked in a calendar
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	   Thread.sleep(4000);
	   
	   WebElement today =null;
	   WebElement CreateBkn =null;
	   try{	
		  driver.findElement(By.xpath("//div[@class='fc-slats']//tr[1]//td[@class='ui-widget-content']")).click();
		  //today =  driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2]"));
		  //today.click();
		  Thread.sleep(2000);
		 // driver.switchTo().defaultContent();
		  //driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
		  CreateBkn =  driver.findElement(By.id("addCustomerName"));
		  String CreateBknTitle =CreateBkn.getAttribute("innerHTML");
		  System.out.println(CreateBknTitle);
		  if(CreateBkn.isDisplayed()&&CreateBknTitle.equalsIgnoreCase("Customer Search")){	  
		  TestCaseName = "TC011_Validate when time of a Day is clicked in a calendar";
		  Expected ="New Booking form should be displayed";	  
		  Status= "Pass";
		  Actual= "New Booking form '"+CreateBknTitle+"' is displayed";
		  resultoutput_pass();
		  }
		  else{
		  TestCaseName = "TC011_Validate when time of a Day is clicked in a calendar";
		  Expected ="New Booking form should be displayed";		  
		  Status= "Fail";
		  Actual= "New Booking form is not displayed";
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
		  }
		  } catch (Exception e){
			  TestCaseName = "TC011_Validate when time of a Day is clicked in a calendar";
			  Expected ="New Booking form should be displayed";		
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		  }
	  System.out.println("TC_011");
	  
	  
	  
/********* Test Case-12: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate required fields
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	 
	  Thread.sleep(4000);
	  WebElement BookButton = null;
	  WebElement CustomerNameErr = null;
	  WebElement DateErr = null;
	  WebElement TimeErr = null;
	  WebElement DurationErr = null;	  
  
	  try{
		  BookButton =  driver.findElement(By.id("btnBook"));
		  BookButton.click();
		  
		  CustomerNameErr =  driver.findElement(By.id("customerName-error"));
		  //DateErr =  driver.findElement(By.id("dateAdd-error"));
		  //TimeErr =  driver.findElement(By.id("timeAdd-error"));
		 // DurationErr =  driver.findElement(By.id("durationAdd-error"));
		  String CstnmeError=CustomerNameErr.getText();
		  //String dateerror=DateErr.getText();
		  //String timeerror=TimeErr.getText();
		  //String durationerror=DurationErr.getText();
		  
         if(CustomerNameErr.isDisplayed()&&CstnmeError.equalsIgnoreCase("This field is required.")){	  
			  TestCaseName = "TC012_Validate required fields";
			  Expected ="Customer Name, Customer Email, Service, Resource, Date, Time, Duration are required, If not entered, error message should be returned";	  
			  Status= "Pass";
			  Actual= "Error '"+CstnmeError+"' for Customer Name, Date, Time and Duration are displayed";
			  resultoutput_pass();
			  }
			  else{
			  TestCaseName = "TC012_Validate required fields";
			  Expected ="Customer Name, Customer Email, Service, Resource, Date, Time, Duration are required, If not entered, error message should be returned";		  
			  Status= "Fail";
			  Actual= "Error for Customer Name, Date, Time and Duration are not displayed";
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
			  }
			  } catch (Exception e){
				  TestCaseName = "TC012_Validate required fields";
				  Expected ="Customer Name, Customer Email, Service, Resource, Date, Time, Duration are required, If not entered, error message should be returned";
				  Status= "Fail";
				  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
				  resultoutput_pass();
				  takeScreenShot(TestCaseName);
			  }
	  System.out.println("TC_012");
	  
	  /********* Test Case-13: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: VValidate Customer Email format
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  	  
	  Thread.sleep(4000);
	  WebElement EmailField = null;
	  WebElement EmailErr = null;
	  
	  try{		 
	  EmailField = driver.findElement(By.id("customerEmailAddress"));
	  EmailField.click();
	  EmailField.clear();
	  EmailField.sendKeys("xyz");
	  BookButton.click();	  
	  EmailErr = driver.findElement(By.id("customerEmailAddress-error"));
	  String EmailErrText = EmailErr.getText();
      if(EmailErrText.equalsIgnoreCase("Please enter a valid email address.")){
	  TestCaseName = "TC013_Validate Customer Email format";
	  Expected ="An error message should be returned";	  
	  Status= "Pass";
	  Actual= "An error message '"+EmailErrText+"' returned";
	  resultoutput_pass();	  
	  }
	  else{
	  TestCaseName = "TC013_Validate Customer Email format";
	  Expected ="An error message should be returned";	  
	  Status= "Fail";
	  Actual= "An error message '"+EmailErrText+"' returned";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);

	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC013_Validate Customer Email format";
		  Expected ="An error message should be returned";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }  
	  
System.out.println("TC_013");


/********* Test Case-14: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Customer Telephone text-field
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement TelephoneDropdwn= null;
try {

String TelTextbfchnge =driver.findElement(By.id("customerTelephoneNumber")).getAttribute("placeholder");	
TelephoneDropdwn=driver.findElement(By.xpath("//div[@class='arrow']"));
TelephoneDropdwn.click();
WebElement OtherCountry =driver.findElement(By.xpath("//ul[@class='country-list']/li[2]"));
OtherCountry.click();
String TelTextaftchnge =driver.findElement(By.id("customerTelephoneNumber")).getAttribute("placeholder");
if(TelTextbfchnge!=TelTextaftchnge){
TestCaseName = "TC014_Validate Customer Telephone text-field";
Expected ="The customer Telephone number should be set to the user’s local country code ";	  
Status= "Pass";
Actual= "The customer Telephone number set from '"+TelTextbfchnge+"' to the user’s local country code '"+TelTextaftchnge+"'";
resultoutput_pass();
}
else{
TestCaseName = "TC014_Validate Customer Telephone text-field";
Expected ="The customer Telephone number should be set to the user’s local country code ";		  
Status= "Fail";
Actual= "The customer Telephone number not set to the user’s local country code";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC014_Validate Customer Telephone text-field";
	  Expected ="The customer Telephone number should be set to the user’s local country code ";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}

System.out.println("TC_014");


/********* Test Case-15: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Customer Telephone format
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement PhoneNmberfield= null;
	
	try {
		PhoneNmberfield = driver.findElement(By.id("customerTelephoneNumber"));
		PhoneNmberfield.click();
		PhoneNmberfield.clear();
		PhoneNmberfield.sendKeys("1234567890");
		BookButton.click();		
		String InvPhoneError = driver.findElement(By.id("customerTelephoneNumber-error")).getText();
		if(InvPhoneError.equalsIgnoreCase("Please enter a valid telephone number")){
		TestCaseName = "TC015_Validate Customer Telephone format";
		Expected ="An error message should be returned";	  
		Status= "Pass";
		Actual= "An error message '"+InvPhoneError+"' is returned";
		resultoutput_pass();
		}
		else{
		TestCaseName = "TC015_Validate Customer Telephone format";
		Expected ="An error message should be returned";		  
		Status= "Fail";
		Actual= "An error message '"+InvPhoneError+"' is returned";
		resultoutput_pass();
		takeScreenShot(TestCaseName);
		}
		} catch (Exception e){
			  TestCaseName = "TC015_Validate Customer Telephone format";
			  Expected ="An error message should be returned";	
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		}

System.out.println("TC_015");


/********* Test Case-16: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when Cancel button is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Cancel = null;
WebElement displynone = null;

try{
Cancel = driver.findElement(By.id("btnCancel"));
Cancel.click();
displynone =  driver.findElement(By.xpath("//div[@id='createEventModal' and @style='display: none;']"));	

if(displynone!=null){
TestCaseName = "TC016_Validate when Cancel button is clicked";
Expected ="Create New Booking form should be closed";	  
Status= "Pass";
Actual= "Create New Booking form is closed";
resultoutput_pass();
}
else{
TestCaseName = "TC016_Validate when Cancel button is clicked";
Expected ="Create New Booking form should be closed"; 
Status= "Fail";
Actual= "Create New Booking form is not closed";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC016_Validate when Cancel button is clicked";
	  Expected ="Create New Booking form should be closed";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  
System.out.println("TC_016");


/********* Test Case-17: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when “Book It” button is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

    WebElement customerName = null;
    WebElement customerEmail = null;
    WebElement serviceAdd = null;
    WebElement resourceAdd = null;    
	WebElement dateAdd = null;
	WebElement timeAdd = null;
	WebElement durationAdd = null;
	WebElement NewlyAddedBooking = null;
	WebElement NlyAddBkingCalendar = null;
	WebElement CloseModal = null;

	try{
		
	driver.findElement(By.xpath("//div[@class='fc-slats']//tr[1]//td[@class='ui-widget-content']")).click();	
		
	//driver.findElement(By.xpath("//div[@id='calendar']//button[text()='month']")).click();	
	//driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2]")).click();
	
    customerName = driver.findElement(By.id("customerName"));
    customerName.clear();
    customerName.click();
    customerName.sendKeys("Test");
    
    customerEmail  = driver.findElement(By.id("customerEmailAddress"));
    customerEmail.clear();
    customerEmail.click();
    customerEmail.sendKeys("Siddrock12345@gmail.com");
    
    serviceAdd = driver.findElement(By.id("serviceAdd_chosen"));
    serviceAdd.click();
    driver.findElement(By.xpath("//ul[@class='chosen-results']/*[last()]")).click();
    
    resourceAdd = driver.findElement(By.id("resourceAdd_chosen"));
    resourceAdd.click();
    driver.findElement(By.xpath("//div[@id='resourceAdd_chosen']//ul[@class='chosen-results']/*[last()]")).click();
    
    dateAdd = driver.findElement(By.id("dateAdd"));
    dateAdd.clear();
    dateAdd.click();
	dateAdd.sendKeys(tommorrowDate);

	timeAdd = driver.findElement(By.id("timeAdd"));
	timeAdd.clear();
	timeAdd.click();
	timeAdd.sendKeys("12:00 am");
	
	driver.findElement(By.xpath("//button[text()='Done']")).click();
	
	durationAdd = driver.findElement(By.id("durationAdd"));
	durationAdd.clear();
	durationAdd.click();
	durationAdd.sendKeys("30");
	
	driver.findElement(By.id("btnBook")).click();
	
	NewlyAddedBooking = driver.findElement(By.id("Notifications")).findElement(By.tagName("a"));
	
	driver.findElement(By.xpath("//div[@id='calendar']//button[text()='month']")).click();	
	
	NlyAddBkingCalendar = driver.findElement(By.xpath("//div[@class='fc-content']"));
	NlyAddBkingCalendar.click();
	
	String ConfirmationCode = driver.findElement(By.id("confirmationIdView")).getText();

	if(NewlyAddedBooking.isDisplayed()&&NlyAddBkingCalendar.isDisplayed()){
	TestCaseName = "TC017_Validate when “Book It” button is clicked";
	Expected ="A successful pop-up message should be presented with unique confirmation I.d";	  
	Status= "Pass";
	Actual= " A successful pop-up message is presented with unique confirmation I.d :"+ConfirmationCode+"";
	resultoutput_pass();
	CloseModal = driver.findElement(By.xpath("//div[@id='editEventModal']//button[@class='close']"));
	CloseModal.click();
	}
	else{
	TestCaseName = "TC017_Validate when “Book It” button is clicked";
	Expected ="A successful pop-up message should be presented with unique confirmation I.d";	 
	Status= "Fail";
	Actual= "A successful pop-up message is not presented";
	resultoutput_pass();
	takeScreenShot(TestCaseName);
	}

	} catch (Exception e){
		  TestCaseName = "TC017_Validate when “Book It” button is clicked";
		  Expected ="A successful pop-up message should be presented with unique confirmation I.d";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	}    

System.out.println("TC_017");

/********* Test Case-18: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Create a successful booking
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement ResrceColour = null;

try{
ResrceColour = driver.findElement(By.xpath("//a[@style='background-color:#FDF054;color:#000']"));
String colour= driver.findElement(By.xpath("//td[@class='fc-event-container']/a")).getAttribute("style");
if(ResrceColour.isDisplayed()){
TestCaseName = "TC018_Create a successful booking";
Expected ="The booking date should represented by Resource colour in the calendar";	  
Status= "Pass";
Actual= "The booking date is represented by Resource colour '"+colour+"' in the calendar";
resultoutput_pass();
}
else{
TestCaseName = "TC018_Create a successful booking";
Expected ="The booking date should represented by Resource colour in the calendar"; 
Status= "Fail";
Actual= "The booking date is not represented by Resource colour in the calenda";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC018_Create a successful booking";
	  Expected ="The booking date should represented by Resource colour in the calendar"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}

System.out.println("TC_018");

/********* Test Case-19: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Create a booking using past date
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


	try{
		
		driver.findElement(By.xpath("//div[@id='calendar']//button[text()='day']")).click();
		
		driver.findElement(By.xpath("//div[@class='fc-slats']//tr[1]//td[@class='ui-widget-content']")).click();
		
		//driver.findElement(By.xpath("//div[@id='calendar']//button[text()='month']")).click();		
		//driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2]")).click();
		
	    customerName = driver.findElement(By.id("customerName"));
	    customerName.click();
	    customerName.sendKeys("Test");
	    
	    customerEmail  = driver.findElement(By.id("customerEmailAddress"));
	    customerEmail.click();
	    customerEmail.sendKeys("Siddrock12345@gmail.com");
	    
	    serviceAdd = driver.findElement(By.id("serviceAdd_chosen"));
	    serviceAdd.click();
	    driver.findElement(By.xpath("//ul[@class='chosen-results']/*[last()]")).click();
	    
	    resourceAdd = driver.findElement(By.id("resourceAdd_chosen"));
	    resourceAdd.click();
	    driver.findElement(By.xpath("//div[@id='resourceAdd_chosen']//ul[@class='chosen-results']/*[last()]")).click();
	    
	    dateAdd = driver.findElement(By.id("dateAdd"));
	    dateAdd.clear();
	    dateAdd.click();
		dateAdd.sendKeys(yesterdayDate);

		timeAdd = driver.findElement(By.id("timeAdd"));
		timeAdd.clear();
		timeAdd.click();
		timeAdd.sendKeys("12:00 am");
		
		durationAdd = driver.findElement(By.id("durationAdd"));
		durationAdd.click();
		durationAdd.clear();
		durationAdd.sendKeys("120");		
		BookButton.click();
		
		
		//String ConfirmationAlert1 = driver.findElement(By.className("EventErrorConfirmationContent")).getAttribute("innerHTML");
		
		String baseWindowHdl = driver.getWindowHandle();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
				
		String ConfirmationAlert1 = driver.findElement(By.xpath("//div[@id='EventErrorConfirmation']//p")).getAttribute("innerHTML");
		String ConfirmationAlert = driver.findElement(By.xpath("//div[@id='EventErrorConfirmation']//p")).getText();
		System.out.println(ConfirmationAlert);
		System.out.println(ConfirmationAlert1);
		
		if(ConfirmationAlert.equalsIgnoreCase("This booking is in the past. Would you like to override this and proceed with the booking?")){
		TestCaseName = "TC019_Create a booking using past date";
		Expected ="Confirmation dialog should be presented to notify user that the booking is in the past and gives a user an option to proceed with the booking or not";	  
		Status= "Pass";
		Actual= " A Confirmation dialog with text '"+ConfirmationAlert+"' is presented ";
		resultoutput_pass();
		driver.findElement(By.xpath("//button[@class='close errorConfirmation']")).click();
		driver.switchTo().window(baseWindowHdl);
		driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
		}
		else{
		TestCaseName = "TC019_Create a booking using past date";
		Expected ="Confirmation dialog should be presented to notify user that the booking is in the past and gives a user an option to proceed with the booking or not";	 
		Status= "Fail";
		Actual= " A Confirmation dialog with different text '"+ConfirmationAlert+"' is presented ";
		resultoutput_pass();
		takeScreenShot(TestCaseName);
		driver.findElement(By.xpath("//button[@class='close errorConfirmation']")).click();
		driver.switchTo().window(baseWindowHdl);
		driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
		}

		} catch (Exception e){
			  TestCaseName = "TC019_Create a booking using past date";
			  Expected ="Confirmation dialog should be presented to notify user that the booking is in the past and gives a user an option to proceed with the booking or not";	 
			  Status= "Fail";
			  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		}
System.out.println("TC_019");



/********* Test Case-20: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Create 2 simultaneous  bookings for one resource, with same time and date  
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


try{
	
	driver.findElement(By.xpath("//div[@id='calendar']//button[text()='day']")).click();
	driver.findElement(By.xpath("//div[@class='fc-slats']//tr[1]//td[@class='ui-widget-content']")).click();

    customerName = driver.findElement(By.id("customerName"));
    customerName.click();
    customerName.sendKeys("Test");
    
    customerEmail  = driver.findElement(By.id("customerEmailAddress"));
    customerEmail.click();
    customerEmail.sendKeys("Siddrock12345@gmail.com");
    
    serviceAdd = driver.findElement(By.id("serviceAdd_chosen"));
    serviceAdd.click();
    driver.findElement(By.xpath("//ul[@class='chosen-results']/*[last()]")).click();
    
    resourceAdd = driver.findElement(By.id("resourceAdd_chosen"));
    resourceAdd.click();
    driver.findElement(By.xpath("//div[@id='resourceAdd_chosen']//ul[@class='chosen-results']/*[last()]")).click();
    
    dateAdd = driver.findElement(By.id("dateAdd"));
    dateAdd.clear();
    dateAdd.click();
	dateAdd.sendKeys(tommorrowDate);

	timeAdd = driver.findElement(By.id("timeAdd"));
	timeAdd.clear();
	timeAdd.click();
	timeAdd.sendKeys("12:00 am");
	
	durationAdd = driver.findElement(By.id("durationAdd"));
	durationAdd.click();
	durationAdd.clear();
	durationAdd.sendKeys("30");		
	BookButton.click();
	
	String baseWindowHdl = driver.getWindowHandle();	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
			
	String ConfirmationAlert1 = driver.findElement(By.xpath("//div[@id='EventErrorConfirmation']//p")).getAttribute("innerHTML");
	String ConfirmationAlert = driver.findElement(By.xpath("//div[@id='EventErrorConfirmation']//p")).getText();
	System.out.println(ConfirmationAlert);
	System.out.println(ConfirmationAlert1);
	
	if(ConfirmationAlert.contains("The maximum number of simultaneous bookings has been exceeded for")){
	TestCaseName = "TC020_Create 2 simultaneous  bookings for one resource, with same time and date";
	Expected ="Confirmation dialog should be presented to notify user that maximum simultaneous bookings exceeded and gives a user an option to proceed with the booking or not";	  
	Status= "Pass";
	Actual= " A Confirmation dialog with text '"+ConfirmationAlert+"' is presented ";
	resultoutput_pass();
	driver.findElement(By.xpath("//button[@class='close errorConfirmation']")).click();
	driver.switchTo().window(baseWindowHdl);
	//driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
	}
	else{
	TestCaseName = "TC020_Create 2 simultaneous  bookings for one resource, with same time and date";
	Expected ="Confirmation dialog should be presented to notify user that maximum simultaneous bookings exceeded and gives a user an option to proceed with the booking or not";	 
	Status= "Fail";
	Actual= " A Confirmation dialog with different text '"+ConfirmationAlert+"' is presented ";
	resultoutput_pass();
	takeScreenShot(TestCaseName);
	driver.findElement(By.xpath("//button[@class='close errorConfirmation']")).click();
	driver.switchTo().window(baseWindowHdl);
	//driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
	}

	} catch (Exception e){
		  TestCaseName = "TC020_Create 2 simultaneous  bookings for one resource, with same time and date";
		  Expected ="Confirmation dialog should be presented to notify user that maximum simultaneous bookings exceeded and gives a user an option to proceed with the booking or not";		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	}
System.out.println("TC_020");


/********* Test Case-21: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Enter a Customer Name of an existing Customer
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement custPopUp = null;

try{
	driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
	
	driver.findElement(By.xpath("//div[@id='calendar']//button[text()='day']")).click();
	driver.findElement(By.xpath("//div[@class='fc-slats']//tr[1]//td[@class='ui-widget-content']")).click();

    customerName = driver.findElement(By.id("customerSearch"));
    customerName.click();
    customerName.sendKeys("Test");
    customerName.click();
    Thread.sleep(2000);
    
    custPopUp = driver.findElement(By.xpath("//ul[contains(@class,'ui-autocomplete')]"));
    	
	if(custPopUp.isDisplayed()){
	TestCaseName = "TC021_Enter a Customer Name of an existing Customer";
	Expected ="A pop-up should be presented with customer’s details";	  
	Status= "Pass";
	Actual= " A pop-up should is presented with customer’s details ";
	resultoutput_pass();

	//driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
	}
	else{
	TestCaseName = "TC021_Enter a Customer Name of an existing Customer";
	Expected ="A pop-up should be presented with customer’s details";	 
	Status= "Fail";
	Actual= " A pop-up should is not presented with customer’s details ";
	resultoutput_pass();
	takeScreenShot(TestCaseName);
	//driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
	}

	} catch (Exception e){
		  TestCaseName = "TC021_Enter a Customer Name of an existing Customer";
		  Expected ="A pop-up should be presented with customer’s details";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	}
System.out.println("TC_021");


/********* Test Case-22: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Hover  on existing booking for 5 seconds
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement ExistingBkng=null;
WebElement BkngDetForm=null;
WebElement BkngDetHeader=null;

try{

driver.findElement(By.id("btnCancel")).click();
driver.findElement(By.xpath("//div[@id='calendar']//button[text()='week']")).click();
ExistingBkng = driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a"));

Actions action = new Actions(driver);
action.moveToElement(ExistingBkng).build().perform();

BkngDetForm = driver.findElement(By.id("EditEventForm"));
BkngDetHeader = driver.findElement(By.xpath("//h4[@id='EditBookingDetails']"));

if(BkngDetForm.isDisplayed()){
TestCaseName = "TC022_Hover  on existing booking for 5 seconds";
Expected ="View Bookings Details form should be presented";
Status= "Pass";
Actual= "View Bookings Details form  '"+BkngDetHeader.getText()+"'  is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC022_Hover  on existing booking for 5 seconds";
Expected ="View Bookings Details form should be presented";	
Status= "Fail";
Actual= "View Bookings Details form is not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC022_Hover  on existing booking for 5 seconds";
	Expected ="View Bookings Details form should be presented";	
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_022");

/********* Test Case-23: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when “Cancel”  label is clicked before Information gets saved
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement ExistingBkng1=null;
WebElement BkngDetForm1=null;
WebElement BkngDetHeader1=null;

try{

driver.findElement(By.id("btnCancelEdit")).click();
ExistingBkng1 = driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a"));

ExistingBkng1.click();

BkngDetForm1 = driver.findElement(By.id("EditEventForm"));
BkngDetHeader1 = driver.findElement(By.xpath("//h4[@id='EditBookingDetails']"));

if(BkngDetForm1.isDisplayed()){
TestCaseName = "TC023_Validate when clicking on existing booking";
Expected ="View Bookings Details form should be presented";
Status= "Pass";
Actual= "View Bookings Details form  '"+BkngDetHeader1.getAttribute("innerHTML")+"'  is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC023_Validate when clicking on existing booking";
Expected ="View Bookings Details form should be presented";	
Status= "Fail";
Actual= "View Bookings Details form is not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC023_Validate when clicking on existing booking";
	Expected ="View Bookings Details form should be presented";	
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_023");


/********* Test Case-23: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when “Remove" label is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement CustomerName=null;
WebElement CustomerEmail=null;
WebElement CustomerTel=null;

try{

CustomerName = driver.findElement(By.id("customerNameView"));
CustomerEmail = driver.findElement(By.id("customerEmailAddressView"));
CustomerTel = driver.findElement(By.id("customerTelephoneNumberView"));

if(CustomerName.isDisplayed()&&CustomerEmail.isDisplayed()&&CustomerTel.isDisplayed()){
TestCaseName = "TC024_Validate when View Booking Details is presented";
Expected ="Customer Information should be  presented";
Status= "Pass";
Actual= "Customer Information  '"+CustomerName.getText()+"', '"+CustomerEmail.getText()+"', '"+CustomerTel.getText()+"'  are presented";
resultoutput_pass();
}
else{
TestCaseName = "TC024_Validate when View Booking Details is presented";
Expected ="Customer Information should be  presented";	
Status= "Fail";
Actual= "Customer Information are not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC024_Validate when View Booking Details is presented";
	Expected ="Customer Information should be  presented";	
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_024");

/********* Test Case-25: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate  Cancel button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement CancelBttn = null;
WebElement DisplayNone = null;

try{

CancelBttn	= driver.findElement(By.id("btnCancelEdit"));
CancelBttn.click();
	
DisplayNone= driver.findElement(By.xpath("//form[@id='EditEventForm']/div[@style='display: none;']"));
  
if(DisplayNone!=null){
TestCaseName = "TC025_Validate  Cancel button";
Expected ="View Booking Details form should be closed";	  
Status= "Pass";
Actual= "View Booking Details form is closed";
resultoutput_pass();
}
else{
TestCaseName = "TC025_Validate  Cancel button";
Expected ="View Booking Details form should be closed";	 	  
Status= "Fail";
Actual= "View Booking Details form is not closed";
resultoutput_pass();
takeScreenShot(TestCaseName);

}

} catch (Exception e){
	  TestCaseName = "TC025_Validate  Cancel button";
	  Expected ="View Booking Details form should be closed";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_025");


/********* Test Case-26: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Edit button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement Editbttn=null;
WebElement UpdateReschbttn=null;
WebElement CancelBookbttn=null;
WebElement Undobttn=null;

try{

driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a")).click();

Editbttn = driver.findElement(By.id("btnEdit"));
Editbttn.click();

UpdateReschbttn = driver.findElement(By.id("btnChange"));
CancelBookbttn = driver.findElement(By.id("btnCancelBooking"));
Undobttn = driver.findElement(By.id("btnUndo"));

String updtbtnName =UpdateReschbttn.getText();
String canbtnName =CancelBookbttn.getText();
String undobtnName =Undobttn.getText();

if(UpdateReschbttn.isDisplayed()&&CancelBookbttn.isDisplayed()&&Undobttn.isDisplayed()){
TestCaseName = "TC026_Validate Edit button";
Expected ="View Bookings Details form should be presented";
Status= "Pass";
Actual= "Edit Booking Details form with options '"+updtbtnName+"', '"+canbtnName+"', '"+undobtnName+"'   is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC026_Validate Edit button";
Expected ="View Bookings Details form should be presented";	
Status= "Fail";
Actual= "Edit Booking Details form is not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC026_Validate Edit button";
	Expected ="View Bookings Details form should be presented";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_026");

/********* Test Case-27: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when Edit Booking Details is presented
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement CustName=null;
WebElement CustEmail=null;
WebElement CustTel=null;
WebElement ServResInform=null;

try{
	
CustName = driver.findElement(By.id("OrderCustomerNameEdit"));
CustEmail = driver.findElement(By.id("OrderCustomerEmailAddressEdit"));
CustTel = driver.findElement(By.id("OrderCustomerTelephoneNumberEdit"));
ServResInform = driver.findElement(By.id("OrderServiceEdit"));

String CustNameValue = CustName.getText();
String CustEmailValue = CustEmail.getText();
String CustTelValue = CustTel.getText();
String ServResInformValue = ServResInform.getText();

if(CustName.isDisplayed()&&CustEmail.isDisplayed()&&CustTel.isDisplayed()&&ServResInform.isDisplayed()){
TestCaseName = "TC027_Validate when Edit Booking Details is presented";
Expected ="Customer Name, Customer Email, Customer Telephone, Service and Resource Information should be presented by default";
Status= "Pass";
Actual= "Customer details '"+CustNameValue+"', '"+CustEmailValue+"', '"+CustTelValue+"', '"+ServResInformValue+"'   are presented";
resultoutput_pass();
}
else{
TestCaseName = "TC027_Validate when Edit Booking Details is presented";
Expected ="Customer Name, Customer Email, Customer Telephone, Service and Resource Information should be presented by default";	
Status= "Fail";
Actual= "Edit Booking Details form is not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC027_Validate when Edit Booking Details is presented";
	Expected ="Customer Name, Customer Email, Customer Telephone, Service and Resource Information should be presented by default";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_027");



/********* Test Case-28: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Reschedule button 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


try{

driver.findElement(By.id("btnChange")).click();

String baseWindowHdl = driver.getWindowHandle();	
driver.switchTo().defaultContent();
driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
String ReschduleAlert = driver.findElement(By.id("EventConfirmationContent")).getText();

if(ReschduleAlert.equalsIgnoreCase("Are you sure that you would like to reschedule this booking?")){
TestCaseName = "TC028_Validate Reschedule button";
Expected ="Edit Confirmation dialog should be presented";
Status= "Pass";
Actual= "Edit Confirmation dialog '"+ReschduleAlert+"'   is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC028_Validate Reschedule button";
Expected ="Edit Confirmation dialog should be presented";
Status= "Fail";
Actual= "Edit Confirmation dialog is presented with different text '"+ReschduleAlert+"' ";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC028_Validate Reschedule button";
	Expected ="Edit Confirmation dialog should be presented";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_028");

/********* Test Case-29: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate  “Yes” button in Edit Confirmation dialog 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Reshcnotifc= null;
WebElement notifcTime= null;

try{
driver.findElement(By.xpath("//div[@id='EventConfirmation']//button[text()='Yes']")).click();
Reshcnotifc=driver.findElement(By.xpath("//div[@id='Notifications']/a[1]/div[text()='Booking Rescheduled']"));
notifcTime = driver.findElement(By.xpath("//div[@id='Notifications']/a[1]/div[contains(@id,'notificationTime')]"));
String NotiAddTime = notifcTime.getText();

if(Reshcnotifc.isDisplayed()){
TestCaseName = "TC029_Validate  “Yes” button in Edit Confirmation dialog";
Expected ="Booking should be rescheduled";
Status= "Pass";
Actual= "Booking is rescheduled '"+NotiAddTime+"' ";
resultoutput_pass();
}
else{
TestCaseName = "TC029_Validate  “Yes” button in Edit Confirmation dialog";
Expected ="Booking should be rescheduled";
Status= "Fail";
Actual= "Booking is not rescheduled";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC029_Validate  “Yes” button in Edit Confirmation dialog";
	Expected ="Booking should be rescheduled";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_029");


/********* Test Case-30: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate  “No” button in Edit Confirmation dialog 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{
	
driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a")).click();

driver.findElement(By.id("btnEdit")).click();

driver.findElement(By.id("btnChange")).click();

String baseWindowHdl = driver.getWindowHandle();	
driver.switchTo().defaultContent();
driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));

List <WebElement> BkngReschduledBFC = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Booking Rescheduled']"));
int ReschBfCancel= BkngReschduledBFC.size();

driver.findElement(By.xpath("//div[@id='EventConfirmation']//button[text()='No']")).click();


List <WebElement> BkngReschduledAFC = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Booking Rescheduled']"));
int ReschAfCancel= BkngReschduledAFC.size();

if(ReschBfCancel==ReschAfCancel){
TestCaseName = "TC030_Validate  “No” button in Edit Confirmation dialog";
Expected ="Booking should not be rescheduled";
Status= "Pass";
Actual= "Booking is not rescheduled";
resultoutput_pass();
}
else{
TestCaseName = "TC030_Validate  “No” button in Edit Confirmation dialog";
Expected ="Booking should not be rescheduled";
Status= "Fail";
Actual= "Booking is rescheduled"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC030_Validate  “No” button in Edit Confirmation dialog";
	Expected ="Booking should not be rescheduled";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_030");


/********* Test Case-31: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate “Undo Changes” button 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement EdtFormClose= null;

try{
	
driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a")).click();

driver.findElement(By.id("btnEdit")).click();
driver.findElement(By.id("btnUndo")).click();

EdtFormClose=driver.findElement(By.xpath("//div[@id='editEventModal' and @style='display: none;']"));

if(EdtFormClose!=null){
TestCaseName = "TC031_Validate “Undo Changes” button";
Expected ="Edit Booking Details form should be closed";
Status= "Pass";
Actual= "Edit Booking Details form is closed";
resultoutput_pass();
}
else{
TestCaseName = "TC031_Validate “Undo Changes” button";
Expected ="Edit Booking Details form should be closed";
Status= "Fail";
Actual= "Edit Booking Details form is not closed";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC031_Validate “Undo Changes” button";
	Expected ="Edit Booking Details form should be closed";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_031");


/********* Test Case-32: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Reschedule button 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{
	
driver.findElement(By.xpath("//div[@id='calendar']//div[@class='fc-event-container']/a")).click();

driver.findElement(By.id("btnEdit")).click();
driver.findElement(By.id("btnCancelBooking")).click();

String baseWindowHdl = driver.getWindowHandle();	
driver.switchTo().defaultContent();
driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
String CancelAlert = driver.findElement(By.id("EventConfirmationContent")).getText();

if(CancelAlert.equalsIgnoreCase("Are you sure that you would like to cancel this booking?")){
TestCaseName = "TC032_Validate “Cancel Booking”";
Expected ="Cancel Booking Confirmation dialog should be presented";
Status= "Pass";
Actual= "Cancel Booking Confirmation dialog '"+CancelAlert+"'   is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC032_Validate “Cancel Booking”";
Expected ="Cancel Booking Confirmation dialog should be presented";
Status= "Fail";
Actual= "Cancel Booking Confirmation dialog is presented with different text '"+CancelAlert+"' ";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC032_Validate “Cancel Booking”";
	Expected ="Cancel Booking Confirmation dialog should be presented";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_032");

/********* Test Case-33: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate “No” button in Cancel Booking dialog 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{
	
List <WebElement> CancelBfrClickNo = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Cancellation']"));
int CanBfrClickNo= CancelBfrClickNo.size();
System.out.println(CanBfrClickNo);

driver.findElement(By.xpath("//div[@id='EventConfirmation']//button[text()='No']")).click();

List <WebElement> CancelAfrClickNo = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Cancellation']"));
int CanAfrClickNo= CancelAfrClickNo.size();
System.out.println(CanAfrClickNo);

if(CanBfrClickNo==CanAfrClickNo){
TestCaseName = "TC033_Validate “No” button in Cancel Booking dialog";
Expected ="Booking should not be cancelled";
Status= "Pass";
Actual= "Booking is not cancelled";
resultoutput_pass();
}
else{
TestCaseName = "TC033_Validate “No” button in Cancel Booking dialog";
Expected ="Booking should not be cancelled";
Status= "Fail";
Actual= "Booking is cancelled"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC033_Validate “No” button in Cancel Booking dialog";
	Expected ="Booking should not be cancelled";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_033");

/********* Test Case-34: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate “Yes” button in Cancel Booking dialog 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{
		
driver.findElement(By.id("btnCancelBooking")).click();

String baseWindowHdl = driver.getWindowHandle();	
driver.switchTo().defaultContent();
driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));	
	
List <WebElement> CancelBfrClickNo = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Cancellation']"));
int CanBfrClickYes= CancelBfrClickNo.size();
System.out.println(CanBfrClickYes);

driver.findElement(By.xpath("//div[@id='EventConfirmation']//button[text()='Yes']")).click();

List <WebElement> CancelAfrClickNo = driver.findElements(By.xpath("//div[@id='Notifications']//div[text()='Cancellation']"));
int CanAfrClickYes= CancelAfrClickNo.size();
System.out.println(CanAfrClickYes);

if(CanAfrClickYes>CanBfrClickYes){
TestCaseName = "TC034_Validate Yes button in Cancel Booking dialog";
Expected ="Booking should be cancelled";
Status= "Pass";
Actual= "Booking is cancelled";
resultoutput_pass();
}
else{
TestCaseName = "TC034_Validate Yes button in Cancel Booking dialog";
Expected ="Booking should be cancelled";
Status= "Fail";
Actual= "Booking is not cancelled"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC034_Validate Yes button in Cancel Booking dialog";
	Expected ="Booking should be cancelled";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_034");

/********* Test Case-35: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Resources check-box 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement ResCheckBx = null;
WebElement AvailResCheckBx = null;
boolean isChecked;
try{
		
ResCheckBx =driver.findElement(By.id("ul_resource"));
ResCheckBx.click();
AvailResCheckBx = driver.findElement(By.xpath("//div[@class='filterResourceItem']/input"));
if(isChecked =AvailResCheckBx.isSelected()){
TestCaseName = "TC035_Validate Resources check-box";
Expected ="All available resources should be checked";
Status= "Pass";
Actual= "All available resources are checked";
resultoutput_pass();
}
else{
TestCaseName = "TC035_Validate Resources check-box";
Expected ="All available resources should be checked";;
Status= "Fail";
Actual= "All available resources are not checked"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC035_Validate Resources check-box";
	Expected ="All available resources should be checked";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_035");

/********* Test Case-36: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Resources check-box 
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement DisBrkLveReCheckBx = null;
try{
driver.findElement(By.id("ul_resource")).click();
driver.findElement(By.xpath("//div[@class='filterResourceItem']/input")).click();
DisBrkLveReCheckBx = driver.findElement(By.xpath("//input[@id='breakLeave' and @type='checkbox']"));
String disbrkcheck = driver.findElement(By.xpath("//label[@class='leaveBreak']")).getText(); 

if(DisBrkLveReCheckBx.isDisplayed()&&disbrkcheck.equalsIgnoreCase("Display Breaks/Leave")){
TestCaseName = "TC036_Validate Resource filter check-box";
Expected ="“Display Breaks/Leave” filter check-box should be presented and calendar should displays only the services that the checked resource provides";
Status= "Pass";
Actual= "Check-box '"+disbrkcheck+"'  is presented and calendar displays only the services that the checked resource provides";
resultoutput_pass();
}
else{
TestCaseName = "TC036_Validate Resource filter check-box";
Expected ="“Display Breaks/Leave” filter check-box should be presented and calendar should displays only the services that the checked resource provides";
Status= "Fail";
Actual= "All available resources are not checked"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC036_Validate Resource filter check-box";
	Expected ="“Display Breaks/Leave” filter check-box should be presented and calendar should displays only the services that the checked resource provides";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_036");

/********* Test Case-37: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Check “Display Breaks/Leave“ filter check-box
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
boolean Checked;
try{

DisBrkLveReCheckBx.click();

if(Checked=DisBrkLveReCheckBx.isSelected()){
TestCaseName = "TC037_Check “Display Breaks/Leave“ filter check-box";
Expected ="It should display breaks and leaves of the selected resource only";
Status= "Pass";
Actual= "It display breaks and leaves of the selected resource only";
resultoutput_pass();
}
else{
TestCaseName = "TC037_Check “Display Breaks/Leave“ filter check-box";
Expected ="It should display breaks and leaves of the selected resource only";
Status= "Fail";
Actual= "All available resources are not checked"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC037_Check “Display Breaks/Leave“ filter check-box";
	Expected ="It should display breaks and leaves of the selected resource only";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_037");

/********* Test Case-38: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Check Services check-box
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement ServCheckBx = null;
WebElement AvlServCheckBx = null;
boolean ServChecked;
try{

DisBrkLveReCheckBx.click();
driver.findElement(By.xpath("//div[@class='filterResourceItem']/input")).click();
ServCheckBx = driver.findElement(By.id("ul_services"));
ServCheckBx.click();
AvlServCheckBx = driver.findElement(By.xpath("//input[@class='filterCategoryItem']"));

if(ServChecked=AvlServCheckBx.isSelected()){
TestCaseName = "TC038_Check Services check-box";
Expected ="All available services and its categories should be checked and displayed on calendar";
Status= "Pass";
Actual= "All available services and its categories are checked and displayed on calendar";
resultoutput_pass();
}
else{
TestCaseName = "TC038_Check Services check-box";
Expected ="All available services and its categories should be checked and displayed on calendar";
Status= "Fail";
Actual= "All available services and its categories are not checked and displayed on calendar"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC038_Check Services check-box";
	Expected ="All available services and its categories should be checked and displayed on calendar";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_038");

/********* Test Case-39: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Check Category filter check-box
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement CatCheckBx = null;
boolean catChecked;
try{

driver.findElement(By.id("ul_services")).click();
CatCheckBx = driver.findElement(By.xpath("//input[@class='filterCategoryItem']"));
CatCheckBx.click();

if(catChecked=CatCheckBx.isSelected()){
TestCaseName = "TC039_Check Category filter check-box";
Expected ="Only services that belong to the checked category should be displayed on calendar";
Status= "Pass";
Actual= "Only services belong to the checked category are displayed on calendar";
resultoutput_pass();
}
else{
TestCaseName = "TC039_Check Category filter check-box";
Expected ="Only services that belong to the checked category should be displayed on calendar";
Status= "Fail";
Actual= "Only services belong to the checked category are not displayed on calendar"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC039_Check Category filter check-box";
	Expected ="Only services that belong to the checked category should be displayed on calendar";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_039");

/********* Test Case-40: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Check Service filter check-box
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement ServFltCheckBx = null;
boolean SrvfltChecked;
try{
driver.findElement(By.xpath("//input[@class='filterCategoryItem']")).click();
ServFltCheckBx = driver.findElement(By.className("filterService")).findElement(By.tagName("input"));
ServFltCheckBx.click();

if(SrvfltChecked=ServFltCheckBx.isSelected()){
TestCaseName = "TC040_Check Service filter check-box";
Expected ="Only checked service that should  be displayed on calendar";
Status= "Pass";
Actual= "Only checked service that is displayed on calendar";
resultoutput_pass();
}
else{
TestCaseName = "TC040_Check Service filter check-box";
Expected ="Only checked service that should  be displayed on calendar";
Status= "Fail";
Actual= "Only checked service that is not displayed on calendar"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC040_Check Service filter check-box";
	Expected ="Only checked service that should  be displayed on calendar";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_040");

/********* Test Case-41: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Check Groups check-box
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement GrpCheckBx = null;
boolean GrpChecked;
try{
ServFltCheckBx.click();
GrpCheckBx = driver.findElement(By.id("ul_groups"));
GrpCheckBx.click();
if(GrpChecked=GrpCheckBx.isSelected()){
TestCaseName = "TC041_Check Groups check-box";
Expected ="All available Groups should be checked and displayed on calendar";
Status= "Pass";
Actual= "All available Groups are checked and displayed on calendar";
resultoutput_pass();
}
else{
TestCaseName = "TC041_Check Groups check-box";
Expected ="All available Groups should be checked and displayed on calendar";
Status= "Fail";
Actual= "All available Groups are not checked and displayed on calendar"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC041_Check Groups check-box";
	Expected ="All available Groups should be checked and displayed on calendar";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_041");

/********* Test Case-42: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Click a date that is not greyed-out in calendar
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement todayCalendar =null;
WebElement todayDay =null;
try{
	
driver.findElement(By.xpath("//div[@id='calendar']//button[text()='month']")).click();
today =  driver.findElement(By.xpath("(//*/.[contains(@class,'fc-today ui-state-highlight')])[2]"));
today.click();
todayDay =  driver.findElement(By.xpath("//div[@id='calendar']//th[contains(@class,'fc-day-header')]"));
String TodaysDay = todayDay.getText();

if(todayDay.isDisplayed()&&TodaysDay.equalsIgnoreCase(todayw)){
TestCaseName = "TC042_Click a date that is not greyed-out in calendar";
Expected ="The calendar should change to day view";
Status= "Pass";
Actual= "The calendar changes to day view for '"+TodaysDay+"";
resultoutput_pass();
}
else{
TestCaseName = "TC042_Click a date that is not greyed-out in calendar";
Expected ="The calendar should change to day view";
Status= "Fail";
Actual= "The calendar not changed to day view"; 
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	TestCaseName = "TC042_Click a date that is not greyed-out in calendar";
	Expected ="The calendar should change to day view";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_042");

}

public static void SelectCurrentDate(WebDriver driver){

    DateFormat dateFormat2 = new SimpleDateFormat("dd"); 
    Date date2 = new Date();

    String today = dateFormat2.format(date2); 
    System.out.println(today);
    String today1 = today.replaceFirst ("^0*", "");
    System.out.println(today1);
    
    WebElement StartTextField1 = driver.findElement(By.xpath("//input[@id='date_leave_start_0']"));
    StartTextField1.click();
    
    WebElement Widget = driver.findElement(By.className("ui-datepicker-calendar"));
    Widget.click();

    List<WebElement> day=Widget.findElements(By.tagName("td"));

    boolean blchek = false;
    
    for (WebElement cell : day)
    {
  
       if (cell.getText().equalsIgnoreCase(today1))
       {
          cell.click();
          blchek = true;
          break;
       }
    
    }

}
}