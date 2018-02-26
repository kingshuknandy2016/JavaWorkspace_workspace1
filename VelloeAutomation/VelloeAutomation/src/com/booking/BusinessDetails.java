package com.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
 
public class BusinessDetails {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Velloe Business Details Module " + timeStamp + " Summary" + ".xls";
static HSSFWorkbook hwb = new HSSFWorkbook();
static HSSFSheet sheet = hwb.createSheet("Summary");
static String ScreenShotfilePath ="E:\\Selenium_Practise\\Aurora_Automation\\Screen\\";
static int row, i=1;
static String  TestCaseName,Expected,Actual,Status;

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
   
  
  
  /********* Test Case-1: *********************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate  Holiday headings
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
  //Thread.sleep(4000);
  WebDriverWait wait1 = new WebDriverWait(driver, 15);
  wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='menu-item-211']/a")));
  //wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
  
  System.out.println(driver.findElement(By.xpath(".//*[@id='menu-item-211']/a")).getText());
  
  WebElement ManageSetting = driver.findElement(By.xpath(".//*[@id='menu-item-211']/a"));
  Actions builder = new Actions(driver);
  Actions  hoverOverlink  = builder.moveToElement(ManageSetting);
  hoverOverlink.perform();

  WebElement BusinessHours = driver.findElement(By.xpath("//li[@id='menu-item-84']/a"));
  BusinessHours.click();
    
  WebElement HolidayTab = null;
  try {
  HolidayTab = driver.findElement(By.id("tab_-1"));
  if(HolidayTab.isDisplayed()){
  HolidayTab.click();
  
  WebElement Holidayheadings = null;
  WebElement Holidayheadings1 = null;
  WebElement Holidayheadings2 = null;
  WebElement Holidayheadings3 = null;
  WebElement Holidayheadings4 = null;

  try {
  Holidayheadings = driver.findElement(By.xpath("//li[@id='field_5_10']//table[@class='leaveDetails']//tr"));
 
  if(Holidayheadings.isDisplayed()){
	  
  Holidayheadings1 = driver.findElement(By.xpath("//li[@id='field_5_10']//table[@class='leaveDetails']//tr/th[1]"));
  Holidayheadings2 = driver.findElement(By.xpath("//li[@id='field_5_10']//table[@class='leaveDetails']//tr/th[2]"));
  Holidayheadings3 = driver.findElement(By.xpath("//li[@id='field_5_10']//table[@class='leaveDetails']//tr/th[3]"));
  Holidayheadings4 = driver.findElement(By.xpath("//li[@id='field_5_10']//table[@class='leaveDetails']//tr/th[4]"));	  

  String HoliText1= Holidayheadings1.getText();
  String HoliText2= Holidayheadings2.getText();
  String HoliText3= Holidayheadings3.getText();
  String HoliText4= Holidayheadings4.getText();
  String HolidayHeader=HoliText1+", "+HoliText2+", "+HoliText3+", "+HoliText4;
  System.out.println(HolidayHeader);
  
  if(HoliText1.equalsIgnoreCase("Description")&&HoliText2.equalsIgnoreCase("Start")&&HoliText3.equalsIgnoreCase("End")&&HoliText4.equalsIgnoreCase("Action")){
  TestCaseName = "TC001_Validate  Holiday headings";
  Expected ="Holiday headings(i.e Description, Start, End, Action) should be visible";	  
  Status= "Pass";
  Actual= "Holiday headings "+HolidayHeader+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC001_Validate  Holiday headings";
  Expected ="Holiday headings(i.e Description, Start, End, Action) should be visible";	  
  Status= "Fail";
  Actual= "Holiday headings(i.e Description, Start, End, Action) is not visible";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  }
  } catch (Exception e){
	  TestCaseName = "TC001_Validate  Holiday headings";
	  Expected ="Holiday headings(i.e Description, Start, End, Action) should be visible";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
  }
  }catch (Exception e){
	  TestCaseName = "TC001_Validate  Holiday headings";
	  Expected ="Holiday headings(i.e Description, Start, End, Action) should be visible";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
   
 System.out.println("TC_001");
 
  /********* Test Case-2: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when “Add Holidays Button” is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  
  WebElement AddHoliButton = null;
  try {
  AddHoliButton = driver.findElement(By.xpath("//input[@value='Add Holidays']"));
  if(AddHoliButton.isDisplayed())
  {
  AddHoliButton.click();
  
  WebElement RemoveHoliButton = null;
  WebElement HolidayTextField1 = null;
  WebElement HolidayTextField2 = null;
  WebElement HolidayTextField3 = null;
  try {  
  RemoveHoliButton = driver.findElement(By.xpath("//input[@value='Remove']"));
  HolidayTextField1 = driver.findElement(By.xpath("//table[@class='leaveDetails']//tr[2]/td[1]/input"));
  HolidayTextField2 = driver.findElement(By.xpath("//table[@class='leaveDetails']//tr[2]/td[2]/input"));
  HolidayTextField3 = driver.findElement(By.xpath("//table[@class='leaveDetails']//tr[2]/td[3]/input"));
 
  if(RemoveHoliButton.isDisplayed()&&HolidayTextField1.isDisplayed()&&HolidayTextField2.isDisplayed()&&HolidayTextField3.isDisplayed())
  {
  String Holidayfield1 =HolidayTextField1.getAttribute("id");
  String Holidayfield2 =HolidayTextField2.getAttribute("id");
  String Holidayfield3 =HolidayTextField3.getAttribute("id");
  String RemoveBttn =RemoveHoliButton.getAttribute("value");

  TestCaseName = "TC002_Validate when “Add Holidays Button” is clicked";
  Expected ="Holiday text-fields and “Remove Holiday” button should be presented";	  
  Status= "Pass";
  Actual= "Holiday text-fields "+Holidayfield1+", "+Holidayfield2+", "+Holidayfield3+", "+"and button "+RemoveBttn+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC002_Validate when “Add Holidays Button” is clicked";
  Expected ="Holiday text-fields and “Remove Holiday” button should be presented";	  
  Status= "Fail";
  Actual= "Holiday text-fields and “Remove Holiday” button are presented";
  resultoutput_pass();
  }
   } catch (Exception e){
	  TestCaseName = "TC002_Validate when “Add Holidays Button” is clicked";
	  Expected ="Holiday text-fields and “Remove Holiday” button should be presented";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
   }  
  }
  }catch (Exception e){
	  TestCaseName = "TC002_Validate when “Add Holidays Button” is clicked";
	  Expected ="Holiday text-fields and “Remove Holiday” button should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
  System.out.println("TC_002"); 
  
/********* Test Case-3: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when Start text-fields is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(5000);
  //driver.get(baseUrl + "/");
  WebElement StartTextField = driver.findElement(By.xpath("//input[@id='date_leave_start_0']"));
  StartTextField.click();  
 
  WebElement StartCalendarWidget = null;
  try {
  StartCalendarWidget = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
  if(StartCalendarWidget.isDisplayed()){
  String StartCalendarMonth =driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();  
  TestCaseName = "TC003_Validate when Start text-fields is clicked";
  Expected ="A calendar widget should be presented when one clicks on Start text-field";	  
  Status= "Pass";
  Actual= "A calendar widget for the month "+StartCalendarMonth+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC003_Validate when Start text-fields is clicked";
  Expected ="A calendar widget should be presented when one clicks on Start text-field";		  
  Status= "Fail";
  Actual= "A calendar widget is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC003_Validate when Start text-fields is clicked";
	  Expected ="A calendar widget should be presented when one clicks on Start text-field";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
   
  System.out.println("TC_003");
  
/********* Test Case-4: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when End text-fields is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);

  WebElement EndTextField = driver.findElement(By.xpath("//input[@id='date_leave_end_0']"));
  EndTextField.click();  
 
  WebElement EndCalendarWidget = null; 
  try {
  EndCalendarWidget = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
  if(EndCalendarWidget.isDisplayed()){
  String EndCalendarMonth =driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();  
  TestCaseName = "TC004_Validate when End text-fields is clicked";
  Expected ="A calendar widget should be presented when one clicks on End text-field";	  
  Status= "Pass";
  Actual= "A calendar widget for the month "+EndCalendarMonth+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC004_Validate when End text-fields is clicked";
  Expected ="A calendar widget should be presented when one clicks on End text-field";		  
  Status= "Fail";
  Actual= "A calendar widget is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC004_Validate when Start or End text-fields is clicked";
	  Expected ="A calendar widget should be presented when one clicks on Start text-field";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  
  System.out.println("TC_004");
  
  /********************************* Test Case-5: ********************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate error message or incomplete fields in Start and End text-fields
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
 
  SelectCurrentDate(driver);
  WebElement EndTextField1 = driver.findElement(By.xpath("//input[@id='date_leave_end_0']"));
  
  
  EndTextField1.click();
  EndTextField1.clear();
  driver.findElement(By.id("gform_submit_button_5")).click();

  try {
	  
	  String baseWindowHdl = driver.getWindowHandle();
	  driver.switchTo().defaultContent();
	  String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();


  if(PopUptextElement.equalsIgnoreCase("You must specify an End date.")){
  TestCaseName = "TC005_Validate error message or incomplete fields in Start and End text-fields";
  Expected ="A popup should be presented with message “An End date is required”";	  
  Status= "Pass";
  Actual= "A popup with message "+PopUptextElement+" is displayed";
  resultoutput_pass();
  driver.findElement(By.xpath("//button[text()='Close']")).click();
  driver.switchTo().window(baseWindowHdl);
  }
  else{
  TestCaseName = "TC005_Validate error message or incomplete fields in Start and End text-fields";
  Expected ="A popup should be presented with message “An End date is required”";		  
  Status= "Fail";
  Actual= "A Pop Up with message 'You must specify an End date.' is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  driver.findElement(By.xpath("//button[text()='Close']")).click();
  driver.switchTo().window(baseWindowHdl);
  }
  } catch (Exception e){
	  TestCaseName = "TC005_Validate error message or incomplete fields in Start and End text-fields";
	  Expected ="A popup should be presented with message “An End date is required”";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  System.out.println("TC_005");
  
  /********* Test Case-6: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Click on “Remove Holiday” button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/ 
  Thread.sleep(4000);
  
  WebElement ManageSetting1 = driver.findElement(By.xpath("//li[@id='menu-item-211']/a"));
  Actions action1 = new Actions(driver);
  action1.moveToElement(ManageSetting1).build().perform();
  
  WebElement BusinessHours1 = driver.findElement(By.xpath("//li[@id='menu-item-84']/a"));
  BusinessHours1.click();
  
  Thread.sleep(4000);
  
  WebElement HolidayTab1 = driver.findElement(By.xpath("//input[@value='Add Holidays']"));
  HolidayTab1.click();
  
  WebElement HolidayTab2 = driver.findElement(By.xpath("//input[@value='Add Holidays']"));
  HolidayTab2.click();
  
  List<WebElement> HolidayTableRow = driver.findElements(By.xpath("//tr[contains(@class,'schedule_leave')]"));
  int totalHoliBeforeRemove = HolidayTableRow.size();
  try {
  WebElement FirstRemoveBttn = driver.findElement(By.xpath(".//*[@id='td_leave_date_0']/input"));
  FirstRemoveBttn.click();
  List<WebElement> HolidayTableRow1 = driver.findElements(By.xpath("//tr[contains(@class,'schedule_leave')]"));
  int totalHoliAfterRemove = HolidayTableRow1.size();
  
	  if(totalHoliAfterRemove<totalHoliBeforeRemove){  
	  TestCaseName = "TC006_Click on “Remove Holiday” button";
	  Expected ="“Remove Holiday” button should remove a Holiday.";	  
	  Status= "Pass";
	  Actual= "Holiday is reduced from "+totalHoliBeforeRemove+" to"+totalHoliAfterRemove;
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC006_Click on “Remove Holiday” button";
	  Expected ="“Remove Holiday” button should remove a Holiday.";		  
	  Status= "Fail";
	  Actual= "Holiday' is not getting removed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	  } catch (Exception e){
		  TestCaseName = "TC006_Click on “Remove Holiday” button";
		  Expected ="“Remove Holiday” button should remove a Holiday.";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }

  
  System.out.println("TC_006");
  
/********* Test Case-7: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when “Add Siesta” button is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
      Thread.sleep(4000);
      
      driver.navigate().refresh();
	
      WebElement AddSiestaButton= driver.findElement(By.xpath("//input[@value='Add Siesta']"));
      AddSiestaButton.click();
      
	  WebElement SiestaStart = null;
	  WebElement SiestaEnd = null;
	  try {
	  SiestaStart = driver.findElement(By.xpath("//td[text()='Siesta Start:']"));
	  SiestaEnd = driver.findElement(By.xpath("//td[text()='Siesta Finish:']"));
	  
	  if(SiestaStart.isDisplayed()&&SiestaEnd.isDisplayed()){  
	  TestCaseName = "TC007_Validate when “Add Siesta” button is clicked";
	  Expected ="Siesta Start and  Siesta Finish time fields should be presented for each checked weekday in Default Operating hours tab";	  
	  Status= "Pass";
	  Actual= "Field " +SiestaStart.getText()+ "and Field "+SiestaEnd.getText()+" are presented";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC007_Validate when “Add Siesta” button is clicked";
	  Expected ="Siesta Start and  Siesta Finish time fields should be presented for each checked weekday in Default Operating hours tab";	   
	  Status= "Fail";
	  Actual= "Siesta Start and  Siesta Finish time fields are not presented";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC007_Validate when “Add Siesta” button is clicked";
		  Expected ="Siesta Start and  Siesta Finish time fields should be presented for each checked weekday in Default Operating hours tab";	   
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  
	  System.out.println("TC_007");
	  
/********* Test Case-8: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when Siesta Start text box is clicked
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                        
	   ************************************************************************************************************************/	  
	   	  
	  Thread.sleep(4000);
	  WebElement  SieStartBox = driver.findElement(By.id("time_break_0_0_Start_1"));
	  SieStartBox.click();
	  
	  WebElement StartTimeWidget = null;
	  try{		  
	  StartTimeWidget = driver.findElement(By.xpath("//table[@class='ui-timepicker-table ui-widget-content ui-corner-all']"));	  
	  if(StartTimeWidget.isDisplayed()){  
	  TestCaseName = "TC008_Validate when Siesta Start text box is clicked";
	  Expected ="A time widget should be presented";	  
	  Status= "Pass";
	  Actual= "A time widget is presented";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC008_Validate when Siesta Start text box is clicked";
	  Expected ="A time widget should be presented";	  
	  Status= "Fail";
	  Actual= "A time widget is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC008_Validate when Siesta Start text box is clicked";
		  Expected ="A time widget should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  
	  System.out.println("TC_008");
/********* Test Case-9: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when Siesta Finish text box is clicked
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/  
		  
	  Thread.sleep(4000);
	  WebElement  SieEndBox = driver.findElement(By.id("time_break_0_0_End_1"));
	  SieEndBox.click();
	  
	  WebElement EndTimeWidget = null;
	  try{		  
	  EndTimeWidget = driver.findElement(By.xpath("//table[@class='ui-timepicker-table ui-widget-content ui-corner-all']"));	  
	  if(EndTimeWidget.isDisplayed()){  
	  TestCaseName = "TC009_Validate when Siesta Finish text box is clicked";
	  Expected ="A time widget should be presented";	  
	  Status= "Pass";
	  Actual= "A time widget is presented";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC009_Validate when Siesta Finish text box is clicked";
	  Expected ="A time widget should be presented";	  
	  Status= "Fail";
	  Actual= "A time widget is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC009_Validate when Siesta Start text box is clicked";
		  Expected ="A time widget should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  System.out.println("TC_009");
	  
	  /********* Test Case-10: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when the first checked “Siesta Start” weekday time is selected
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  Thread.sleep(2000);
	  	  
	  WebElement  SieStartBox1 = driver.findElement(By.id("time_break_0_0_Start_1"));
	  SieStartBox1.click();
	  try{	
	  WebElement TimeWidget = driver.findElement(By.className("ui-timepicker")).findElement(By.tagName("tr")).findElement(By.tagName("td"));
	  TimeWidget.click();
	  driver.findElement(By.xpath("//button[text()='Done']")).click();
	  String SelectedTime =TimeWidget.getText();
	  System.out.println(SelectedTime);
	  List <WebElement> SiestaStartTimeList = driver.findElements(By.xpath("//input[contains(@name,'time_break_0_0_Start_')]"));
	  int totalstart =SiestaStartTimeList.size();
	  if(totalstart==7){
	  TestCaseName = "TC010_Validate when the first checked “Siesta Start” weekday time is selected";
	  Expected ="All weekdays will should have the same time automatically";	  
	  Status= "Pass";
	  Actual= "All weekdays are showing the same time automatically";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC010_Validate when the first checked “Siesta Start” weekday time is selected";
	  Expected ="All weekdays will should have the same time automatically";	  
	  Status= "Fail";
	  Actual= "All weekdays are not showing the same time automatically";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC010_Validate when the first checked “Siesta Start” weekday time is selected";
		  Expected ="All weekdays will should have the same time automatically";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }	  
	  System.out.println("TC_010");
	  
/********* Test Case-11: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when the “Siesta Finish” weekday time is selected
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  	  
	  
	  Thread.sleep(4000);
	  
	  try{
	  driver.findElement(By.xpath("//input[@value='Remove']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[@value='Add Siesta']")).click();
	  
	  
	  WebElement  SieFinishBox1 = driver.findElement(By.id("time_break_0_0_End_1"));
	  SieFinishBox1.click();
	  
	  WebElement TimeWidget = driver.findElement(By.className("ui-timepicker")).findElement(By.tagName("tr")).findElement(By.tagName("td"));
	  TimeWidget.click();
	  driver.findElement(By.xpath("//button[text()='Done']")).click();
	  
	  String SelectedTime =TimeWidget.getText();
	  System.out.println(SelectedTime);
	  List <WebElement> SiestaEndTimeList = driver.findElements(By.xpath("//input[contains(@name,'time_break_0_0_Start_')]"));
	  int totalstart =SiestaEndTimeList.size();	  
  
	  if(totalstart==7){
	  TestCaseName = "TC011_Validate when the “Siesta Finish” weekday time is selected";
	  Expected ="All weekdays will should have the same time automatically";	  
	  Status= "Pass";
	  Actual= "All weekdays are showing the same time automatically";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC011_Validate when the “Siesta Finish” weekday time is selected";
	  Expected ="All weekdays will should have the same time automatically";	  
	  Status= "Fail";
	  Actual= "All weekdays are not showing the same time automatically";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC011_Validate when the “Siesta Finish” weekday time is selected";
		  Expected ="All weekdays will should have the same time automatically";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  System.out.println("TC_011");
	  
	  /********* Test Case-12: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate error message on Siesta Start and Siesta Finish
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  	  
	  Thread.sleep(4000);
	  
	  try{	
	  WebElement  SieStartBox2 = driver.findElement(By.id("time_break_0_0_Start_1"));
	  SieStartBox2.click();
	
	  WebElement TimeWidget = driver.findElement(By.className("ui-timepicker")).findElement(By.xpath("//tr[4]//a"));
	  TimeWidget.click();
	  //driver.findElement(By.linkText("Done")).click();
	  
	  String baseWindowHdl = driver.getWindowHandle();
	  driver.switchTo().defaultContent();
	  String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();
	
      if(PopUptextElement.equalsIgnoreCase("Please specify an End time that is after the Start time")){
	  TestCaseName = "TC012_Validate error message on Siesta Start and Siesta Finish";
	  Expected ="A popup should be presented with message “Please specify an End time  that is after the Start time";	  
	  Status= "Pass";
	  Actual= "'A pop-up with message "+PopUptextElement+" ' is displayed";
	  resultoutput_pass();
	  driver.findElement(By.xpath("//button[text()='Close']")).click();
	  driver.switchTo().window(baseWindowHdl);
	  
	  }
	  else{
	  TestCaseName = "TC012_Validate error message on Siesta Start and Siesta Finish";
	  Expected ="A popup should be presented with message “Please specify an End time  that is after the Start time";	  
	  Status= "Fail";
	  Actual= "'A pop-up with message 'Please specify an End time  that is after the Start time' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  driver.findElement(By.xpath("//button[text()='Close']")).click();
	  driver.switchTo().window(baseWindowHdl);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC012_Validate error message on Siesta Start and Siesta Finish";
		  Expected ="A popup should be presented with message “Please specify an End time  that is after the Start time";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }  
	  
System.out.println("TC_012");


/********* Test Case-13: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when Add Alternative Operating Hours is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement  addaltOpHours = driver.findElement(By.linkText("Add Alternate Operating Hours"));
addaltOpHours.click();

WebElement dfltophours = null;
WebElement EffectiveFrom = null;
WebElement EffectiveTo = null;


try{
	
dfltophours = driver.findElement(By.id("tab_1"));
EffectiveFrom = driver.findElement(By.id("date_effective_workday_Start_1"));
EffectiveTo = driver.findElement(By.id("date_effective_workday_End_1"));


if(dfltophours.isDisplayed()&&EffectiveFrom.isDisplayed()&&EffectiveTo.isDisplayed()){
TestCaseName = "TC013_Validate when Add Alternative Operating Hours is clicked";
Expected ="Default Operating Hours time and Effective From -to date text fields should be  presented";	  
Status= "Pass";
Actual= "Default Operating Hours time and Effective From -to date text fields are presented";
resultoutput_pass();
}
else{
TestCaseName = "TC013_Validate error message on Siesta Start and Siesta Finish";
Expected ="Default Operating Hours time and Effective From -to date text fields should be  presented";	  
Status= "Fail";
Actual= "Default Operating Hours time and Effective From -to date text fields are not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC013_Validate error message on Siesta Start and Siesta Finish";
	  Expected ="Default Operating Hours time and Effective From -to date text fields should be  presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}

System.out.println("TC_013");


/********* Test Case-14: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when “Effective From” date text field is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
driver.findElement(By.id("date_effective_workday_Start_1")).click();
WebElement EffectiveFromCal = null;

try{
	
EffectiveFromCal = driver.findElement(By.id("ui-datepicker-div"));

if(EffectiveFromCal.isDisplayed()){

String EffFromMonth	= driver.findElementByClassName("ui-datepicker-month").getText();
String EffFromYear	= driver.findElementByClassName("ui-datepicker-year").getText();
	
TestCaseName = "TC014_Validate when “Effective From” date text field is clicked";
Expected ="Calendar widget should be presented";	  
Status= "Pass";
Actual= "Calendar widget for the month "+EffFromMonth+" and Year "+EffFromYear+ " is presented";
resultoutput_pass();
}
else{
TestCaseName = "TC014_Validate when “Effective From” date text field is clicked";
Expected ="Calendar widget should be presented";	  
Status= "Fail";
Actual= "Calendar widget is not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC014_Validate when “Effective From” date text field is clicked";
	  Expected ="Calendar widget should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}    

System.out.println("TC_014");


/********* Test Case-15: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate an error message for incorrect  date in  “Effective From -to” date text fields
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement fromdate =driver.findElement(By.id("date_effective_workday_End_1"));
fromdate.sendKeys("2015-20-09");

WebElement todate =driver.findElement(By.id("date_effective_workday_End_1"));
todate.sendKeys("2015-01-09");

try{
	
String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("The Effective To date must be at least one week after the")){

TestCaseName = "TC015_Validate an error message for incorrect  date in  “Effective From -to” date text fields";
Expected ="A popup should be presented with message “The Effective To date must be on or after the Effective From date.";	  
Status= "Pass";
Actual= "A popup with message "+PopUptextElement+"  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}
else{
TestCaseName = "TC015_Validate an error message for incorrect  date in  “Effective From -to” date text fields";
Expected ="A popup should be presented with message “The Effective To date must be on or after the Effective From date.";  
Status= "Fail";
Actual= "A popup with message" +PopUptextElement+ "is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}

} catch (Exception e){
	  TestCaseName = "TC015_Validate an error message for incorrect  date in  “Effective From -to” date text fields";
	  Expected ="A popup should be presented with message “The Effective To date must be on or after the Effective From date.";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}    


System.out.println("TC_015");


/********* Test Case-16: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate “Remove Selected Schedule” button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
try{

driver.findElement(By.xpath("//a[contains(text(),'Add Alternate Operating Hours')]")).click();

List <WebElement> beforeremove =driver.findElements(By.xpath("//a[contains(@class,'scheduleTab tab')]"));
int tabcountbr = beforeremove.size();

driver.findElement(By.xpath("//input[@value='Remove Selected Schedule']")).click();

List <WebElement> aftereremove =driver.findElements(By.xpath("//a[contains(@class,'scheduleTab tab')]"));
int tabcountar = aftereremove.size();

if(tabcountbr>tabcountar){

TestCaseName = "TC016_Validate “Remove Selected Schedule” button";
Expected ="Remove Selected Schedule” button should remove the selected Alternate Operating hours schedule";	  
Status= "Pass";
Actual= "Alternate Operating hours schedule is reduced from "+tabcountbr+" to "+tabcountar+ " ";
resultoutput_pass();

}
else{
TestCaseName = "TC016_Validate “Remove Selected Schedule” button";
Expected ="Remove Selected Schedule” button should remove the selected Alternate Operating hours schedule";	 
Status= "Fail";
Actual= "Alternate Operating hours schedule is not removed";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC016_Validate “Remove Selected Schedule” button";
	  Expected ="Remove Selected Schedule” button should remove the selected Alternate Operating hours schedule";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}

System.out.println("TC_016");

/********* Test Case-17: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate “Submit” button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

driver.findElement(By.id("gform_submit_button_5")).click();
WebElement ServicesResources = null;

try{

ServicesResources = driver.findElement(By.tagName("h2"));
String ServicesResourcestext =ServicesResources.getText();

if(ServicesResourcestext.equalsIgnoreCase("Services and Resources")){

TestCaseName = "TC017_Validate “Submit” button";
Expected ="“Submit” button should proceed to Business Services Wizard page when clicked";	  
Status= "Pass";
Actual= "“Submit” button should proceed to" +ServicesResourcestext+ " page when clicked";
resultoutput_pass();

}
else{
TestCaseName = "TC017_Validate “Submit” button";
Expected ="“Submit” button should proceed to Business Services Wizard page when clicked"; 
Status= "Fail";
Actual= "Submit” button not proceed to Business Services Wizard page when clicked";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC017_Validate “Submit” button";
	  Expected ="“Submit” button should proceed to Business Services Wizard page when clicked";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}    

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