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
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
 
public class Authentication {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Velloe Authentication Module " + timeStamp + " Summary" + ".xls";
static HSSFWorkbook hwb = new HSSFWorkbook();
static HSSFSheet sheet = hwb.createSheet("Summary");
static String ScreenShotfilePath ="E:\\Selenium_Practise\\Aurora_Automation\\Screen\\";
static int row, i=1;
static String  TestCaseName,Expected,Actual,Status,Second,testname;

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
   * Test Case: Enter existing Username and Incorrect Password
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  WebElement UserName = driver.findElement(By.id("input_17_1"));
  UserName.click();
  UserName.clear();
  UserName.sendKeys("stestqa1234567890@gmail.com");
  
  WebElement PassWord = driver.findElement(By.id("input_17_2"));
  PassWord.click();
  PassWord.clear();
  PassWord.sendKeys("Wrong");
  
  WebElement LoginButton = driver.findElement(By.id("gform_submit_button_17"));
  LoginButton.click();
  Thread.sleep(4000);
  
  WebElement ErrorduetoWrongPW = null;
  try {
  ErrorduetoWrongPW = driver.findElement(By.xpath("//div[contains(@class,'validation_message')]"));
  if(ErrorduetoWrongPW != null){
  String Errormessage= ErrorduetoWrongPW.getText();
  if(Errormessage.equalsIgnoreCase("Username or password is invalid.")){
  TestCaseName = "TC001_Enter existing Username and Incorrect Password";
  Expected ="Error 'Username or password is invalid.' should be displayed";	  
  Status= "Pass";
  Actual= "Error '"+Errormessage+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC001_Enter existing Username and Incorrect Password";
  Expected ="Error 'Username or password is invalid.' should be displayed";	  
  Status= "Fail";
  Actual= "Error 'This field is required.' is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  }  
  } catch (Exception e){
	  TestCaseName = "TC001_Enter existing Username and Incorrect Password";
	  Expected ="Error 'Username or password is invalid.' should be displayed";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
   
 System.out.println("TC_001");
 
  /********* Test Case-2: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Enter non-existing Username and correct Password
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  //driver.get(baseUrl + "/");
   WebElement UserName1 = driver.findElement(By.id("input_17_1"));
  UserName1.click();
  UserName1.clear();
  UserName1.sendKeys("wrong");
  
  
  WebElement PassWord1 = driver.findElement(By.id("input_17_2"));
  PassWord1.click();
  PassWord1.clear();
  PassWord1.sendKeys("Password");
  WebElement LoginButton1 = driver.findElement(By.id("gform_submit_button_17"));
  LoginButton1.click();
  
  WebElement ErrorduetoWrongEmail = null;
  try {
  ErrorduetoWrongEmail = driver.findElement(By.className("gfield_description"));
  if(ErrorduetoWrongEmail != null){
  String Errormessage= ErrorduetoWrongEmail.getText();
  if(Errormessage.contains("Please enter your Velloe username")){
  TestCaseName = "TC002_Enter non-existing Username and correct Password";
  Expected ="Error 'Please enter your Velloe username' should be displayed";	  
  Status= "Pass";
  Actual= "Error '"+Errormessage+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC002_Enter non-existing Username and correct Password";
  Expected ="Error 'Please enter your Velloe username' should be displayed";	  
  Status= "Fail";
  Actual= "Error 'Please enter your Velloe username' is not displayed";
  resultoutput_pass();
  }
  }
  } catch (Exception e){
	  TestCaseName = "TC002_Enter non-existing Username and correct Password";
	  Expected ="Error 'Please enter your Velloe username' should be displayed";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  
  }
  System.out.println("TC_002"); 
  
/********* Test Case-3: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Enter existing Service provider credentials
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(5000);
  //driver.get(baseUrl + "/");
  WebElement UserName2 = driver.findElement(By.id("input_17_1"));
  UserName2.click();
  UserName2.clear();
  UserName2.sendKeys("Siddrock12345@gmail.com");
  
  
  WebElement PassWord2 = driver.findElement(By.id("input_17_2"));
  PassWord2.click();
  PassWord2.clear();
  PassWord2.sendKeys("password");
  WebElement LoginButton2 = driver.findElement(By.id("gform_submit_button_17"));
  LoginButton2.click();
  Thread.sleep(3000);
  
  WebElement BookingFrame=null;
  String baseWindowHdl = driver.getWindowHandle();
  WebElement BookingNCalendar = null;
  try {
	  
  BookingFrame=driver.findElement(By.id("bookingCenter"));	
  driver.switchTo().frame(BookingFrame);
  
  BookingNCalendar = driver.findElement(By.id("calendar")).findElement(By.tagName("h2"));
  if(BookingNCalendar.isDisplayed()){
  String BookingNCalendartext= BookingNCalendar.getText();
  TestCaseName = "TC003_Enter existing Service provider credentials";
  Expected ="Calendar and Bookings page should be presented";	  
  Status= "Pass";
  Actual= "Bookings calendar for '"+BookingNCalendartext+"' is displayed";
  resultoutput_pass();
  driver.switchTo().window(baseWindowHdl);
  }
  else{
  TestCaseName = "TC003_Enter existing Service provider credentials";
  Expected ="Calendar and Bookings page should be presented";	  
  Status= "Fail";
  Actual= "Calendar and Bookings page is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC003_Enter existing Service provider credentials";
	  Expected ="Calendar and Bookings page should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  driver.findElement(By.xpath("//a[text()='Log Off']")).click();
  
  System.out.println("TC_003");
  
/********* Test Case-4: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Enter existing Client credentials
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);
  WebElement UserNamec = driver.findElement(By.id("input_17_1"));
  UserNamec.click();
  UserNamec.clear();
  UserNamec.sendKeys("qacustomer@velloe.com");
  
  WebElement PassWordc = driver.findElement(By.id("input_17_2"));
  PassWordc.click();
  PassWordc.clear();
  PassWordc.sendKeys("test");
  
  WebElement LoginButtonC = driver.findElement(By.id("gform_submit_button_17"));
  LoginButtonC.click();
  Thread.sleep(8000);
  WebElement ClientApplicationFrame = null;
  try {
  ClientApplicationFrame = driver.findElement(By.id("mobileWeb"));  
  if(ClientApplicationFrame.isDisplayed()){
 // driver.switchTo().frame(ClientApplicationFrame);	  
  TestCaseName = "TC004_Enter existing Client credentials";
  Expected ="Velloe Client Application Should be presente";	  
  Status= "Pass";
  Actual= "Velloe Client Application is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC004_Enter existing Client credentials";
  Expected ="Velloe Client Application Should be presente";	  
  Status= "Fail";
  Actual= "Velloe Client Application page is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC004_Enter existing Service provider credentials";
	  Expected ="Calendar and Bookings page should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  
  System.out.println("TC_004");
  
  /********************************* Test Case-5: ********************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Click “I forgot my password” label
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  driver.get(baseUrl + "/");
  Thread.sleep(2000);
  
  WebElement PasswordResetLink = null;
  try {
  PasswordResetLink = driver.findElement(By.xpath("//a[contains(text(),'forgot my password')]"));
  if(PasswordResetLink != null){
  PasswordResetLink.click();
  Thread.sleep(2000);
  driver.switchTo().frame(driver.findElement(By.id("passwordResetForm")));
  WebElement PasswordResetText = null;  
  try {
  PasswordResetText = driver.findElement(By.className("form-group")).findElement(By.tagName("h4"));
  if(PasswordResetText != null){  
  String PasswordResetpageText= PasswordResetText.getText();
  System.out.println(PasswordResetpageText);
  if(PasswordResetpageText.contains("Velloe Password Reset")){
  TestCaseName = "TC005_Validate “I forgot my password”";
  Expected ="Password-reset page should be presented";	  
  Status= "Pass";
  Actual= "PW Reset Page Text: '"+PasswordResetpageText+"' is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC005_Validate “I forgot my password”";
  Expected ="Password-reset page should be presented";	  
  Status= "Fail";
  Actual= "Password-reset page is not displayed";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  }
  } catch (Exception e){
	  TestCaseName = "TC005_Validate “I forgot my password”";
	  Expected ="Password-reset page should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  }
  }
  catch (Exception e){
	  TestCaseName = "TC005_Validate “I forgot my password”";
	  Expected ="Password-reset page should be presented";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  System.out.println("TC_005");
  /********* Test Case-6: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Enter email address that does not exist
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/ 
   
  Thread.sleep(4000);
  WebElement PasswordResetTextBox = null;
  
  try {
	  PasswordResetTextBox = driver.findElement(By.id("txtEmailAddress"));
	  if(PasswordResetTextBox != null){
	  PasswordResetTextBox.click();
	  PasswordResetTextBox.clear();
	  PasswordResetTextBox.sendKeys("wrong");
	  Thread.sleep(4000);
	  WebElement ContinueResetButton = null;  
	  try {
	  ContinueResetButton = driver.findElement(By.id("btnGo"));
	  ContinueResetButton.click();
	  if(ContinueResetButton.isEnabled()){  
	  TestCaseName = "TC006_Enter email address that does not exist";
	  Expected ="A pop-up message should be presented";	  
	  Status= "Pass";
	  Actual= "A pop-up 'Please enter an email address.' is displayed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC006_Enter email address that does not exist";
	  Expected ="A pop-up message should be presented";		  
	  Status= "Fail";
	  Actual= "A pop-up 'Please enter an email address.' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC006_Enter email address that does not exist";
		  Expected ="A pop-up message should be presented";	
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  }
	  }
	  catch (Exception e){
		  TestCaseName = "TC006_Validate “I forgot my password”";
		  Expected ="Password-reset page should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
  
  System.out.println("TC_006");
  
/********* Test Case-7: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Leave the text field blank and click “Submit ” button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
      Thread.sleep(4000);
	  PasswordResetTextBox.click();
	  PasswordResetTextBox.clear();
	  Thread.sleep(2000);
	  WebElement ContinueResetButton = null;  
	  try {
	  ContinueResetButton = driver.findElement(By.id("btnGo"));
	  ContinueResetButton.click();
	  if(ContinueResetButton.isEnabled()){  
	  TestCaseName = "TC007_Leave the text field blank and click “Submit ” button";
	  Expected ="A pop-up message should be presented";	  
	  Status= "Pass";
	  Actual= "A pop-up 'Please enter an email address.' is displayed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC007_Leave the text field blank and click “Submit ” button";
	  Expected ="A pop-up message should be presented";		  
	  Status= "Fail";
	  Actual= "A pop-up 'Please enter an email address.' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC007_Leave the text field blank and click “Submit ” button";
		  Expected ="A pop-up message should be presented";	
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  
	  System.out.println("TC_007");
	  
/********* Test Case-8: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Enter existing email address and click “Submit ” button
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/	  
	   	  
	  Thread.sleep(4000);
	  PasswordResetTextBox.click();
	  PasswordResetTextBox.clear();
	  PasswordResetTextBox.sendKeys("Siddharthamon@gmail.com");
	  ContinueResetButton.click();
	  Thread.sleep(2000);
	  WebElement SecurityQATextField = null;
	  try{		  
	  SecurityQATextField = driver.findElement(By.id("txtSecurityQuestionAnswer"));	  
	  if(SecurityQATextField.isDisplayed()){  
	  TestCaseName = "TC008_Enter existing email address and click “Submit ” button";
	  Expected ="Security question and Answer text field should be presented";	  
	  Status= "Pass";
	  Actual= "'Security question and Answer text' is displayed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC008_Enter existing email address and click “Submit ” button";
	  Expected ="Security question and Answer text field should be presented";	  
	  Status= "Fail";
	  Actual= "'Security question and Answer text' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC008_Enter existing email address and click “Submit ” button";
		  Expected ="Security question and Answer text field should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }	  
	  System.out.println("TC_008");
/********* Test Case-9: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Enter existing email address and click “Submit ” button-Continue Button Should be Presented
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/  
		  
	  Thread.sleep(4000);  
	  WebElement SubmitButton = null;
	  try{		  
	  SubmitButton = driver.findElement(By.id("btnGo"));	  
	  if(SubmitButton.isEnabled()){
	  String sbmitbuttonname= SubmitButton.getText();	  
	  TestCaseName = "TC009_Enter existing email address and click “Submit ” button";
	  Expected ="Continue Button should be presented";	  
	  Status= "Pass";
	  Actual= "Continue Button  '" +sbmitbuttonname+"' is displayed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC009_Enter existing email address and click “Submit ” button";
	  Expected ="Continue Button should be presented";	  
	  Status= "Fail";
	  Actual= "'Continue Button' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	  } catch (Exception e){
		  TestCaseName = "TC009_Enter existing email address and click “Submit ” button";
		  Expected ="Continue Button should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  System.out.println("TC_009");
	  
	  /********* Test Case-10: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Enter existing email address and click “Submit ” button-Cancel Button Should be Presented
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  Thread.sleep(2000);
	  WebElement CancelButton = null;
	  try{		  
	  CancelButton = driver.findElement(By.id("btnCancel"));	  
	  if(CancelButton.isEnabled()){
	  String Cancelbuttonname= CancelButton.getText();	  
	  TestCaseName = "TC010_Enter existing email address and click “Submit ” button";
	  Expected ="Cancel Button should be presented";	  
	  Status= "Pass";
	  Actual= "Cancel Button  '" +Cancelbuttonname+"' is displayed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC010_Enter existing email address and click “Submit ” button";
	  Expected ="Cancel Button should be presented";	  
	  Status= "Fail";
	  Actual= "'Cancel Button' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC010_Enter existing email address and click “Submit ” button";
		  Expected ="Cancel Button should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }	  
	  System.out.println("TC_010");
	  
/********* Test Case-11: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Enter Incorrect Security Answer and click “Submit ” button
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  
	  Thread.sleep(2000);
	  WebElement ErrorSecurityQAWrong = null;	  
	  try{
	  
	  SecurityQATextField.click();
	  SecurityQATextField.clear();
	  SecurityQATextField.sendKeys("Wrong");
	  SubmitButton.click();
	  Thread.sleep(2000);
	  
	  driver.switchTo().defaultContent();	  
	 	 		  
	  ErrorSecurityQAWrong = driver.findElement(By.id("generalAlertContent")); ///neeed to modify
	  String ErrMsg =ErrorSecurityQAWrong.getText();
	  System.out.println(ErrMsg);
	  if(ErrMsg.equalsIgnoreCase("Incorrect answer. Please try again.")){  
	  TestCaseName = "TC011_Enter Incorrect Security Answer and click “Submit ” button";
	  Expected ="A pop-up error message should be presented";	  
	  Status= "Pass";
	  Actual= "'A pop-up with error '"+ErrMsg+"' is displayed";
	  resultoutput_pass();
	  driver.findElement(By.xpath("//button[text()='Close']")).click();
	  driver.switchTo().window(baseWindowHdl);
	  driver.switchTo().frame(driver.findElement(By.id("passwordResetForm")));
	  }
	  else{
	  TestCaseName = "TC011_Enter Incorrect Security Answer and click “Submit ” button";
	  Expected ="A pop-up error message should be presented";	  
	  Status= "Fail";
	  Actual= "'A pop-up error message' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  driver.findElement(By.xpath("//button[text()='Close']")).click();
	  driver.switchTo().window(baseWindowHdl);
	  driver.switchTo().frame(driver.findElement(By.id("passwordResetForm")));
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "Enter Incorrect Security Answer and click “Submit ” button";
		  Expected ="A pop-up error message should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
	  System.out.println("TC_011");
	  
	  /********* Test Case-12: *********************************************/
	  /* Project  : Velloe Automation Script 
	   * Module   : Authentication
	   * Test Case: Enter correct Security Answer and click “Submit” button
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  	  
	  Thread.sleep(2000);
	  WebElement SQAtextField = null;
	  WebElement NewPw = null;
	  WebElement CnfmPw = null;
	  try{
	  SQAtextField = driver.findElement(By.id("txtSecurityQuestionAnswer"));
	  SQAtextField.click();
	  SQAtextField.clear();
	  SQAtextField.sendKeys("test");
	  SubmitButton.click();
	  Thread.sleep(2000);
	  
	  NewPw = driver.findElement(By.id("txtPassword"));
	  CnfmPw = driver.findElement(By.id("txtConfirmPassword"));
	  
	  if(NewPw.isDisplayed()&&CnfmPw.isDisplayed()){
	  String NewPwfieldName = NewPw.getAttribute("placeholder");
	  String CnfmPwfieldName = CnfmPw.getAttribute("placeholder");
		  
	  TestCaseName = "TC012_Enter correct Security Answer and click “Submit” button";
	  Expected ="New password and Confirm password text-fields should be presented";	  
	  Status= "Pass";
	  Actual= "'"+NewPwfieldName+"' and '"+CnfmPwfieldName+"' text-fields are presented";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC012_Enter correct Security Answer and click “Submit” button";
	  Expected ="New password and Confirm password text-fields are not presented";	  
	  Status= "Fail";
	  Actual= "'A pop-up error message' is not displayed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	 
	  } catch (Exception e){
		  TestCaseName = "TC012_Enter correct Security Answer and click “Submit” button";
		  Expected ="New password and Confirm password text-fields should be presented";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }  
	  
System.out.println("TC_012");

/********* Test Case-13: *********************************************/
/* Project  : Velloe Automation Script 
 * Module   : Authentication
 * Test Case: Enter different passwords in both fields and click Submit button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf= new SimpleDateFormat("ddssms");
Second=sdf.format(cal.getTime());
testname= "Testqa"+Second;
WebElement ContinueButtn= null;
WebElement PWReset= null;
try{
NewPw.sendKeys(testname);
CnfmPw.sendKeys("abcd");
ContinueButtn = driver.findElement(By.id("btnGo"));
ContinueButtn.click();
driver.switchTo().defaultContent();

PWReset = driver.findElement(By.id("generalAlertContent"));
String PWReseterrrMsg =PWReset.getText();
System.out.println(PWReseterrrMsg);
if(PWReseterrrMsg.equalsIgnoreCase("Your passwords do not match.")){	  
TestCaseName = "TC013_Enter different passwords in both fields and click Submit button";
Expected ="A pop-up should display with ,message”Passwords do not match”";	  
Status= "Pass";
Actual= "A pop-up with text'"+PWReseterrrMsg+"' is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}
else{
TestCaseName = "TC013_Enter different passwords in both fields and click Submit button";
Expected ="A pop-up should display with ,message”Passwords do not match”";	  
Status= "Fail";
Actual= "A pop-up with text'"+PWReseterrrMsg+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}

} catch (Exception e){
	  TestCaseName = "TC013_Enter different passwords in both fields and click Submit button";
	  Expected ="A pop-up should display with ,message”Passwords do not match”";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_013");


/********* Test Case-14: *********************************************/
/* Project  : Velloe Automation Script 
 * Module   : Authentication
 * Test Case: Enter similar passwords in both text-fields and click Submit button
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement ContinueButtn1= null;
WebElement PWReset1= null;
try{
	
driver.findElement(By.id("txtPassword")).sendKeys(testname);
driver.findElement(By.id("txtConfirmPassword")).sendKeys(testname);
ContinueButtn1 = driver.findElement(By.id("btnGo"));
ContinueButtn1.click();

driver.switchTo().defaultContent();

PWReset1 = driver.findElement(By.id("generalAlertContent"));
String PWResetMsg =PWReset1.getText();
System.out.println(PWResetMsg);
if(PWResetMsg.equalsIgnoreCase("Your password has been reset.")){	  
TestCaseName = "TC014_Enter similar passwords in both text-fields and click Submit button";
Expected ="Password should be reset and text 'Your password has been reset.' should be displayed";	  
Status= "Pass";
Actual= "Password reset and text'"+PWResetMsg+"' is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}
else{
TestCaseName = "TC014_Enter similar passwords in both text-fields and click Submit button";
Expected ="Password should be reset and text 'Your password has been reset.' should be displayed";		  
Status= "Fail";
Actual= "Password not reset and text'"+PWResetMsg+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
}

} catch (Exception e){
	  TestCaseName = "TC014_Enter similar passwords in both text-fields and click Submit button";
	  Expected ="Password should be reset and text 'Your password has been reset.' should be displayed";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_014");


/********* Test Case-15: *********************************************/
/* Project  : Velloe Automation Script 
 * Module   : Authentication
 * Test Case: Validate that after password reset user is navigated to Authentication page
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{	
String pageTile = driver.getTitle();	
if(pageTile.equalsIgnoreCase("Velloe Authentication | Velloe Business Portal")){	  
TestCaseName = "TC015_Validate that after password reset user is navigated to Authentication page";
Expected ="After password reset user should be navigated to Authentication page";	  
Status= "Pass";
Actual= "User navigated to'"+pageTile+"' page";
resultoutput_pass();
}
else{
TestCaseName = "TC015_Validate that after password reset user is navigated to Authentication page";
Expected ="After password reset user should be navigated to Authentication page";		  
Status= "Fail";
Actual= "User navigated to'"+pageTile+"' page";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC015_Validate that after password reset user is navigated to Authentication page";
	  Expected ="After password reset user should be navigated to Authentication page";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_015");
  
  driver.close();
}
}
