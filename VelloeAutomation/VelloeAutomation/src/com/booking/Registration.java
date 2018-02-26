package com.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
 
public class Registration {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Velloe Registration Module " + timeStamp + " Summary" + ".xls";
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
                RegistrationPageValidation();
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
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

 /************************************************************************************************************************
 * Project:           Velloe Automation Script

 * Author:     Siddhartha Mondal (QA Automation Analyst )
****************************************************     Registration Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/


public static void RegistrationPageValidation() throws Exception 
  {
 
  applicationLaunch(driver);  
  
  
  /********* Test Case-1: *********************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate if Business  Account is selected by default
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  WebElement BusinessAccountDefault = null;
  try {
  WebElement RegisterLink = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink.click();
  Thread.sleep(4000);
  //driver.switchTo().frame(driver.findElement(By.id("gform_3")));
  BusinessAccountDefault = driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span"));
  if(BusinessAccountDefault.isDisplayed()){
  String AccountTypeName= BusinessAccountDefault.getText();
  if(AccountTypeName.equalsIgnoreCase("Business")){
  TestCaseName = "TC001_Validate if Business  Account is selected by default";
  Expected ="Business Account should be selected by default";	  
  Status= "Pass";
  Actual= "Account Type '"+AccountTypeName+"' is selected by default";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC001_Validate if Business  Account is selected by default";
  Expected ="Business Account should be selected by default";	  
  Status= "Fail";
  Actual= "Account Type '"+AccountTypeName+"' is selected by default";
  resultoutput_pass();
  }
  }  
  } catch (Exception e){
	  TestCaseName = "TC001_Validate if Business  Account is selected by default";
	  Expected ="Business Account should be selected by default"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();	  
  }
  
  System.out.println("TC_001");
  
/********* Test Case-2: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate if Name Text Field is displayed for Customer Account type
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  BusinessAccountDefault.click();
  WebElement SelectCustomer = null;
  try {	 	  
  SelectCustomer = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer.click();
  
  WebElement NameField = null;
  NameField = driver.findElement(By.id("input_3_16"));
  if(NameField.isDisplayed()){
  String NamefieldText = driver.findElement(By.id("field_3_16")).findElement(By.className("gfield_description")).getText();
  TestCaseName = "TC002_Validate Name text-field of Customer Account type ";
  Expected ="Name text-field should be presented";	  
  Status= "Pass";
  Actual= "Name Field with text ' "+NamefieldText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC002_Validate Name text-field of Customer Account type ";
  Expected ="Name text-field should be presented";		  
  Status= "Fail";
  Actual= "Name text-field is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC002_Validate Name text-field of Customer Account type ";
	  Expected ="Name text-field should be presented";		 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
 System.out.println("TC_002");
 
  /********* Test Case-3: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate if Mobile Number Text Field is displayed for Customer Account type
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
   
  WebElement MobileNumberfield = null;
  try {	 	  
  MobileNumberfield = driver.findElement(By.id("input_3_14"));
  
  if(MobileNumberfield.isDisplayed()){
  String MobileNumberfieldText = driver.findElement(By.id("field_3_14")).findElement(By.className("gfield_description")).getText();
  TestCaseName = "TC003_Validate Mobile Number Field for Customer Account type ";
  Expected ="Mobile Number text-field should be presented";	  
  Status= "Pass";
  Actual= "Mobile Number Field with text '"+MobileNumberfieldText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC003_Validate Mobile Number Field for Customer Account type ";
  Expected ="Mobile Number text-field should be presented";		  
  Status= "Fail";
  Actual= "Mobile Number text-field is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC003_Validate Mobile Number Field for Customer Account type ";
	  Expected ="Mobile Number text-field should be presented";		 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_003");
  
  /********* Test Case-4: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter different emails for “enter Email” and “Confirm Email” text-fields
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000); 
  WebElement EmailMismatchError = null;
  try {	 	  
  driver.findElement(By.id("input_3_16")).sendKeys("Test");
  driver.findElement(By.id("input_3_3")).sendKeys("Test@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Testdiffrent@g.com");
  driver.findElement(By.id("input_3_14")).sendKeys("1234567890");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  EmailMismatchError = driver.findElement(By.xpath("//div[contains(text(),'Your emails do not match')]"));
  
  if(EmailMismatchError.isDisplayed()){
  String EmailmismatchErrorMsg = EmailMismatchError.getText();
  TestCaseName = "TC004_Enter different emails for “enter Email” and “Confirm Email” text-fields";
  Expected =" Error message like : “Your emails do not match”  should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' "+EmailmismatchErrorMsg+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC004_Enter different emails for “enter Email” and “Confirm Email” text-fields";
  Expected =" Error message like : “Your emails do not match”  should be displayed";	  
  Status= "Fail";
  Actual= "Error message like : “Your emails do not match” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC004_Enter different emails for “enter Email” and “Confirm Email” text-fields";
	  Expected =" Error message like : “Your emails do not match”  should be displayed";		 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_004");
  
  /********* Test Case-5: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter email address for “Enter Email” field and leave “Confirm Email” blank
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  WebElement EmailBlankError = null;
  
  try {	 	  
   driver.findElement(By.id("input_3_3")).sendKeys("Test@g.com");
  //driver.findElement(By.id("input_3_3_2")).sendKeys("Testdiffrent@g.com");
   driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  EmailBlankError = driver.findElement(By.xpath("//div[contains(text(),'Your emails do not match')]"));
  
  if(EmailBlankError.isDisplayed()){
  String EmailBlankErrorMsg = EmailBlankError.getText();
  TestCaseName = "TC005_Enter email address for “Enter Email” field and leave “Confirm Email” blank";
  Expected =" Error message like : “Your emails do not match”  should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' "+EmailBlankErrorMsg+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC005_Enter email address for “Enter Email” field and leave “Confirm Email” blank";
  Expected =" Error message like : “Your emails do not match”  should be displayed";	  
  Status= "Fail";
  Actual= "Error message like : “Your emails do not match” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC005_Enter email address for “Enter Email” field and leave “Confirm Email” blank";
	  Expected =" Error message like : “Your emails do not match”  should be displayed";		 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_005");
  
/********* Test Case-6: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter the same incorrect email address format on both email text-fields
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/ 
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  WebElement InvalidEmailError = null;  
  try {	 	  
  driver.findElement(By.id("input_3_3")).sendKeys("###$$$$$$");
  driver.findElement(By.id("input_3_3_2")).sendKeys("###$$$$$$");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  InvalidEmailError = driver.findElement(By.id("input_3_3"));
  
  if(InvalidEmailError.isDisplayed()){
  String InvalidEmailErrorMsg = InvalidEmailError.getText();
  TestCaseName = "TC006_Enter the same incorrect email address format on both email  text-fields";
  Expected =" Error message: Please enter a valid email address  should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' please enter email' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC006_Enter the same incorrect email address format on both email  text-fields";
  Expected =" Error message: Please enter a valid email address  should be displayed";	  
  Status= "Fail";
  Actual= "Error message like : “Your emails do not match” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC006_Enter the same incorrect email address format on both email  text-fields";
	  Expected =" Error message: Please enter a valid email address  should be displayed";			 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_006");
  
/********* Test Case-7: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter different passwords for “Enter password” and “Confirm Password” text-field
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  WebElement PWMismatchError = null;  
  try {	 	  
  driver.findElement(By.id("input_3_3")).sendKeys("test@c.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("test@c.com");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test1234567890");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  PWMismatchError = driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]"));
  
  if(PWMismatchError.isDisplayed()){
  String PWMismatchErrorMsg = PWMismatchError.getText();
  TestCaseName = "TC007_Enter different passwords for “Enter password” and “Confirm Password” text-field";
  Expected =" Error message: “Your passwords do not match.” should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' "+PWMismatchErrorMsg+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC007_Enter different passwords for “Enter password” and “Confirm Password” text-field";
  Expected =" Error message: “Your passwords do not match.” should be displayed";	  
  Status= "Fail";
  Actual= "Error message like : “Your passwords do not match.” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC007_Enter different passwords for “Enter password” and “Confirm Password” text-field";
	  Expected =" Error message: “Your passwords do not match.” should be displayed";				 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_007");
/********* Test Case-8: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter passwords for “Enter password” text -field and leave “Confirm Password” text-field empty
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  WebElement PWMEmptyError = null;  
  try {	 	  
  driver.findElement(By.id("input_3_3")).sendKeys("test@c.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("test@c.com");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  PWMEmptyError = driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]"));
  
  if(PWMEmptyError.isDisplayed()){
  String PWMEmptyErrorMsg = PWMEmptyError.getText();
  TestCaseName = "TC008_Enter passwords for “Enter password” text -field and leave “Confirm Password” text-field empty";
  Expected =" Error message: “Your passwords do not match.” should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' "+PWMEmptyErrorMsg+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC008_Enter passwords for “Enter password” text -field and leave “Confirm Password” text-field empty";
  Expected =" Error message: “Your passwords do not match.” should be displayed";	  
  Status= "Fail";
  Actual= "Error message like : “Your passwords do not match.” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC008_Enter passwords for “Enter password” text -field and leave “Confirm Password” text-field empty";
	  Expected =" Error message: “Your passwords do not match.” should be displayed";				 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_008");
  
  /********* Test Case-9: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Register an existing email address with incorrect password
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  
  WebElement ExistinEmailPWIncorrectError = null;  
  try {	 	  
  driver.findElement(By.id("input_3_3")).sendKeys("Siddharthamon@gmail.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Siddharthamon@gmail.com");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  ExistinEmailPWIncorrectError = driver.findElement(By.xpath("//div[contains(text(),'This email address is currently in use')]"));
  
  if(ExistinEmailPWIncorrectError.isDisplayed()){
  String ExistinEmailPWIncorrectErrorMsg = ExistinEmailPWIncorrectError.getText();
  TestCaseName = "TC009_Register an existing email address with incorrect password";
  Expected =" Error message: “User already registered as a Service Provider, but did not provide correct credentials.” should be displayed";	  
  Status= "Pass";
  Actual= "Error message ' "+ExistinEmailPWIncorrectErrorMsg+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC009_Register an existing email address with incorrect password";
  Expected =" Error message: “User already registered as a Service Provider, but did not provide correct credentials.” should be displayed"; 
  Status= "Fail";
  Actual= "Error message like : “Your passwords do not match.” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC009_Register an existing email address with incorrect password";
	  Expected =" Error message: “User already registered as a Service Provider, but did not provide correct credentials.” should be displayed";			 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_009");
  
  /********* Test Case-10: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Register with an existing email address with correct password
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  Thread.sleep(4000);
  WebElement ExistingEmailPWClPage = null;
  String baseWindowHdl = driver.getWindowHandle();
  try {	 	  
  driver.findElement(By.id("input_3_3")).sendKeys("Siddrock12345@gmail.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Siddrock12345@gmail.com");
  driver.findElement(By.id("input_3_4")).sendKeys("password");
  driver.findElement(By.id("input_3_4_2")).sendKeys("password");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(200);
  
  driver.switchTo().frame(driver.findElement(By.id("bookingCenter")));
  
  ExistingEmailPWClPage = driver.findElement(By.id("calendar")).findElement(By.tagName("h2"));
  
  if(ExistingEmailPWClPage.isDisplayed()){
  String CalendarBookingPage = ExistingEmailPWClPage.getText();
  TestCaseName = "TC010_Register with an existing email address with correct password";
  Expected =" User should be navigated to 'Calendar and Bookings page'";	  
  Status= "Pass";
  Actual= "Calendar and Bookings pag for date '"+CalendarBookingPage+"' is displayed";
  resultoutput_pass();
  driver.switchTo().window(baseWindowHdl);
  }
  else{
  TestCaseName = "TC010_Register with an existing email address with correct password";
  Expected =" User should be navigated to 'Calendar and Bookings page'"; 
  Status= "Fail";
  Actual= "User is not navigated to 'Calendar and Bookings page'";
  resultoutput_pass();
  driver.switchTo().window(baseWindowHdl);
  }
  } catch (Exception e){
	  TestCaseName = "TC010_Register with an existing email address with correct password";
	  Expected =" User should be navigated to 'Calendar and Bookings page'";			 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
     }
  System.out.println("TC_010");
  
  /********* Test Case-11: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Register with existing service provider email address and incorrect password in Customer Account type
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.findElement(By.xpath("//a[text()='Log Off']")).click();
  Thread.sleep(2000);
  driver.findElement(By.id("gform_submit_button_17_1")).click();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  WebElement SelectCustomer1 = null;
  try {
	 	  
  SelectCustomer1 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer1.click();
  
  
  driver.findElement(By.id("input_3_16")).sendKeys("Test");
  driver.findElement(By.id("input_3_3")).clear();
  driver.findElement(By.id("input_3_3")).sendKeys("qacustomer@velloe.com");
  driver.findElement(By.id("input_3_3_2")).clear();
  driver.findElement(By.id("input_3_3_2")).sendKeys("qacustomer@velloe.com");
  driver.findElement(By.id("input_3_14")).sendKeys("1234567890");
  driver.findElement(By.id("input_3_4")).sendKeys("Testwrong");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Testwrong");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
    
  WebElement ExistingCustomerError = null;
  ExistingCustomerError = driver.findElement(By.xpath("//div[contains(text(),'User already registered as a Service Provider')]"));
  if(ExistingCustomerError.isDisplayed()){
  String ExistingCustomerErrorText = ExistingCustomerError.getText();
  TestCaseName = "TC011_Register with existing service provider email address and incorrect password in Customer Account type ";
  Expected ="A pop-up should be presented with message “User already registered as a Service Provider, but did not provide correct credentials”";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+ExistingCustomerErrorText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC011_Register with existing service provider email address and incorrect password in Customer Account type ";
  Expected ="A pop-up should be presented with message “User already registered as a Service Provider, but did not provide correct credentials”";	  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC011_Register with existing service provider email address and incorrect password in Customer Account type ";
	  Expected ="A pop-up should be presented with message “User already registered as a Service Provider, but did not provide correct credentials”";	 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
  System.out.println("TC_011");
  
  /********* Test Case-12: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Register with existing service provider email address and correct password in Customer Account type
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.navigate().back();
  driver.findElement(By.id("gform_submit_button_17_1")).click();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  WebElement SelectCustomer2 = null;
  try {
	 	  
  SelectCustomer2 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer2.click();
  
  driver.findElement(By.id("input_3_16")).sendKeys("Test");
  driver.findElement(By.id("input_3_3")).clear();
  driver.findElement(By.id("input_3_3")).sendKeys("qacustomer@velloe.com");
  driver.findElement(By.id("input_3_3_2")).clear();
  driver.findElement(By.id("input_3_3_2")).sendKeys("qacustomer@velloe.com");
  driver.findElement(By.id("input_3_14")).sendKeys("1234567890");
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test");
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
    
  WebElement ExistingCustomerlogin = null;
  ExistingCustomerlogin = driver.findElement(By.xpath("//a[contains(text(),'Manage Calendar and Bookings')]"));
  if(ExistingCustomerlogin.isDisplayed()){
  String ExistingCustomerErrorText = ExistingCustomerlogin.getText();
  TestCaseName = "TC012_Register with existing service provider email address and correct password in Customer Account type";
  Expected ="The user should be signed-in to the system as a Service provider";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+ExistingCustomerErrorText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC012_Register with existing service provider email address and correct password in Customer Account type";
  Expected ="The user should be signed-in to the system as a Service provider";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC012_Register with existing service provider email address and correct password in Customer Account type";
	  Expected ="The user should be signed-in to the system as a Service provider"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    } 
  System.out.println("TC_012");
  
  
  /********* Test Case-13: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate required fields-Do not enter required Information
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  Thread.sleep(2000);
  WebElement SelectCustomer3 = null;
  try {
	 	  
	  SelectCustomer3 = driver.findElement(By.xpath("//li[text()='Customer']"));
	  SelectCustomer3.click();
  
  driver.findElement(By.id("gform_submit_button_3")).click();
     
  
  WebElement ExistingCustomerlogin = null;
  ExistingCustomerlogin = driver.findElement(By.xpath("//a[contains(text(),'Manage Calendar and Bookings')]"));
  if(ExistingCustomerlogin.isDisplayed()){
  String ExistingCustomerErrorText = ExistingCustomerlogin.getText();
  TestCaseName = "TC013_Validate required fields-Do not enter required Information";
  Expected ="Error message is returned if email address, password, security Answer and Terms of services  fields are left blank";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+ExistingCustomerErrorText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC013_Validate required fields-Do not enter required Information";
  Expected ="Error message is returned if email address, password, security Answer and Terms of services  fields are left blank";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC013_Register with existing service provider email address and correct password in Customer Account type";
	  Expected ="Error message is returned if email address, password, security Answer and Terms of services  fields are left blank"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
  System.out.println("TC_013");
  
  /********* Test Case-14: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate required fields-Leave the Name and Mobile number text-field blank and click Create Account button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  Thread.sleep(2000);
  WebElement SelectCustomer4 = null;
  try {
	 	  
  SelectCustomer4 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer4.click();
  
  driver.findElement(By.id("gform_submit_button_3")).click();
     
  
  WebElement ExistingCustomerlogin = null;
  ExistingCustomerlogin = driver.findElement(By.xpath("//a[contains(text(),'Manage Calendar and Bookings')]"));
  if(ExistingCustomerlogin.isDisplayed()){
  String ExistingCustomerErrorText = ExistingCustomerlogin.getText();
  TestCaseName = "TC014_Leave the Name and Mobile number text-field blank and click Create Account button";
  Expected ="An error message should be returned";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+ExistingCustomerErrorText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC014_Leave the Name and Mobile number text-field blank and click Create Account button";
  Expected ="An error message should be returned";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC014_Leave the Name and Mobile number text-field blank and click Create Account button";
	  Expected ="An error message should be returned"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
  System.out.println("TC_014");
  
  /********* Test Case-15: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate required fields-Leave the Name and Mobile number text-field blank and click Create Account button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  Thread.sleep(2000);
  WebElement SelectCustomer5 = null;
  try {
	 	  
  SelectCustomer5 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer5.click();
  driver.findElement(By.id("gform_submit_button_3")).click();
     
  
  WebElement ExistingCustomerlogin = null;
  ExistingCustomerlogin = driver.findElement(By.xpath("//div[contains(text(),'Your passwords do not match.')]")); ///need to modify..
  if(ExistingCustomerlogin.isDisplayed()){
  String ExistingCustomerErrorText = ExistingCustomerlogin.getText();
  TestCaseName = "TC015_Validate required fields-Leave the Name and Mobile number text-field blank and click Create Account button";
  Expected ="The user should be signed-in to the system as a Service provider";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+ExistingCustomerErrorText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC015_Validate required fields-Leave the Name and Mobile number text-field blank and click Create Account button";
  Expected ="The user should be signed-in to the system as a Service provider";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC015_Register with existing service provider email address and correct password in Customer Account type";
	  Expected ="The user should be signed-in to the system as a Service provider"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
  System.out.println("TC_015");
  
  /********* Test Case-16: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Validate Velloe Terms of Service
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  WebElement SelectCustomer6 = null;
  try {
	 	  
  SelectCustomer6 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer6.click();
  driver.findElement(By.id("gform_submit_button_3")).click();
     
  
  WebElement  TermsOfServiceLink = null;
  TermsOfServiceLink = driver.findElement(By.xpath("//a[contains(text(),'Terms of Service')]")); 
  if(TermsOfServiceLink.isDisplayed()){
  TermsOfServiceLink.click();
  
  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
  WebDriver ndriver = driver.switchTo().window(tabs2.get(1));
  WebElement TermNservice = ndriver.findElement(By.xpath("//span[contains(text(),'Velloe Terms of Service')]"));
  TestCaseName = "TC016_Validate Velloe Terms of Service";
  Expected ="Terms of Service page should be presented";	  
  Status= "Pass";
  Actual= "A pop-up with message ' "+TermNservice.getText()+"' is displayed";
  resultoutput_pass();
  ndriver.close();
  driver.switchTo().window(tabs2.get(0));
  }
  else{
  TestCaseName = "TC016_Validate Velloe Terms of Service";
  Expected ="Terms of Service page should be presented";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();

  }
  } catch (Exception e){
	  TestCaseName = "TC016_Validate Velloe Terms of Service";
	  Expected ="Terms of Service page should be presented"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    }
  System.out.println("TC_016");
  
  
  /********* Test Case-17: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Registration
   * Test Case: Enter required Information in input fields and click  “Create Account” button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Registration Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Calendar cal = Calendar.getInstance();
  int Second = cal.get(Calendar.SECOND);
  
  Thread.sleep(4000);
  driver.navigate().refresh();
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
  	 	  
  WebElement SelectCustomer7 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer7.click();
  driver.findElement(By.id("input_3_16")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_3")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_14")).sendKeys("12345678"+Second);
  driver.findElement(By.id("input_3_4")).sendKeys("Test");
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test");
  driver.findElement(By.id("input_3_6")).sendKeys("Test"+Second);
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  
  WebElement BusinessInfoHeader = null;
  try {
  BusinessInfoHeader = driver.findElement(By.xpath("//div[contains(text(),'Business Information')]")); //Need to Modify
  String BusinessInfoHeadertext = BusinessInfoHeader.getText();
  if(BusinessInfoHeadertext.contains("Business Information")){ 
  TestCaseName = "TC017_Enter required Information in input fields and click  “Create Account” button";
  Expected ="When Create Account  is clicked , it should  proceed to Business Information page";  
  Status= "Pass";
  Actual= "A pop-up with message ' "+BusinessInfoHeadertext+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC017_Enter required Information in input fields and click  “Create Account” button";
  Expected ="When Create Account  is clicked , it should  proceed to Business Information page";  
  Status= "Fail";
  Actual= "A pop-up with message is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "TC017_Enter required Information in input fields and click  “Create Account” button";
	  Expected ="When Create Account  is clicked , it should  proceed to Business Information page"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
    } 
  System.out.println("TC_017");
   driver.close();
}
}
