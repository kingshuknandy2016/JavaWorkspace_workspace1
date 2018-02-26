package com.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
 
public class BusinessInformationContact {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Velloe Business Informaion Module " + timeStamp + " Summary" + ".xls";
static HSSFWorkbook hwb = new HSSFWorkbook();
static HSSFSheet sheet = hwb.createSheet("Summary");

static int row, i=1;
static String  TestCaseName,Expected,Actual,Status,Second;



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
                             
                i++;
                 }
 
/********************************************************
Launch Application
***************************************************************/
public static void applicationLaunch(WebDriver driver) throws Exception {
	 
        
                baseUrl = "https://sdemo.velloe.com";
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
   
 /********* Test Case-1: ***************************************************************************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Business Information Page-Validate that Business User Navigated to Continue Page once submit the email password and Security Question
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Business Information Page     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  WebElement RegisterLink = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink.click();
  Thread.sleep(2000);

  Calendar cal = Calendar.getInstance();
  SimpleDateFormat sdf= new SimpleDateFormat("ddssms");
  Second=sdf.format(cal.getTime());
  
  driver.findElement(By.id("input_3_3")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_4")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_6")).sendKeys("Test"+Second);
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(2000);
 // System.out.println("test1");
  String baseWindowHdl = driver.getWindowHandle();
  driver.switchTo().defaultContent();
  
  driver.findElement(By.xpath("//button[text()='Close']")).click();
  
  driver.switchTo().window(baseWindowHdl);
  
  //System.out.println("test2");
  
  WebElement  ContnueButton = null;
  try {
  ContnueButton = driver.findElement(By.id("gform_submit_button_4"));
  
  if(ContnueButton.isDisplayed() ){
  String ContinueButtonName=  ContnueButton.getAttribute("value");
  TestCaseName = "Business User Navigated to Continue Page";
  Expected ="Business User should be navigated to Continue Page once submit the email password and Security Question";	  
  Status= "Pass";
  Actual= " Business User should be navigated to Continue Page and button "+ContinueButtonName+"  is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Business User Navigated to Continue Page";
  Expected ="Business User should be navigated to Continue Page once submit the email password and Security Question";
  Status= "Fail";
  Actual= "Error is not displayed for all blank fields";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "Business User Navigated to Continue Page";
	  Expected ="Business User should be navigated to Continue Page once submit the email password and Security Question";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();  
		  
  }
  
  System.out.println("TC -001");
  
 /********* Test Case-2: ***************************************************************************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Validate equired fields for Business User in Continue Page
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Business Information Page     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
 
  Thread.sleep(2000);

  WebElement ContnueButton1 = driver.findElement(By.id("gform_submit_button_4"));
  ContnueButton1.click();
  Thread.sleep(2000);
  List<WebElement>  ErrorduetoBlankField = null;
 
  try {
  ErrorduetoBlankField = driver.findElements(By.xpath("//div[contains(text(),'This field is required.')]"));
  if(ErrorduetoBlankField.size()==4 ){
  String Errormessage= ((WebElement) ErrorduetoBlankField).getText();
  if(Errormessage.equalsIgnoreCase("This field is required.")){
  TestCaseName = "Keep required Information blank and click “Continue” button";
  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";	  
  Status= "Pass";
  Actual= " Error for all "+ErrorduetoBlankField.size()+" blank fields with text"+Errormessage+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Keep required Information blank and click “Continue” button";
  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";
  Status= "Fail";
  Actual= "Error is not displayed for all blank fields";
  resultoutput_pass();
  }
  }
  } catch (Exception e){
	  TestCaseName = "Keep required Information blank and click “Continue” button";
	  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();  
		  
  } 
 
  System.out.println("TC -002");
  
 /********* Test Case-3: ***************************************************************************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Validate that user navigated to Contact Information if enter required details and click on Continue
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Business Information Page     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
 
  Thread.sleep(2000);
  /*driver.navigate().refresh();
  Thread.sleep(2000);*/
  driver.findElement(By.id("input_4_2")).sendKeys("Test"+Second);
  
  driver.findElement(By.id("input_4_3_chosen")).findElement(By.tagName("span")).click();
  WebElement SelectBussinessCat = driver.findElement(By.xpath("//li[text()='Dog Grooming']"));
  SelectBussinessCat.click();
    
  driver.findElement(By.id("input_4_14_chosen")).findElement(By.tagName("span")).click();
  WebElement SelectCountry = driver.findElement(By.xpath("//li[text()='Canada']"));
  SelectCountry.click();
  Thread.sleep(2000);
  WebElement PhoneNumber = driver.findElement(By.id("input_4_10"));
  PhoneNumber.click();
  Thread.sleep(2000);
  PhoneNumber.sendKeys("1234567890");
  
  driver.findElement(By.id("input_4_13_1")).sendKeys("test1 "+Second);
  driver.findElement(By.id("input_4_13_2")).sendKeys("test2 "+Second);
  driver.findElement(By.id("input_4_13_3")).sendKeys("test3 "+Second);
  
  WebElement element=driver.findElement(By.id("input_4_13_4"));
  Select se=new Select(element);
  se.selectByIndex(2);
   
  driver.findElement(By.id("input_4_13_5")).sendKeys("test3 "+Second);
  
  
  WebElement ContnueButton2 = driver.findElement(By.id("gform_submit_button_4"));
  ContnueButton2.click();
  Thread.sleep(2000);
  WebElement ContactInformationPage = null;
 
  try {
  ContactInformationPage = driver.findElement(By.xpath("//h2[contains(text(),'Contact Information')]"));
  if(ContactInformationPage.isDisplayed()){
  String ContactInformationPagetext= ContactInformationPage.getText();
  if(ContactInformationPagetext.equalsIgnoreCase("Contact Information")){
  TestCaseName = "user navigated to Contact Information if enter required details and click on Continue";
  Expected ="User should be navigated to Contact Information Page";	  
  Status= "Pass";
  Actual= " User navigated to "+ContactInformationPagetext+" Page";
  resultoutput_pass();
  }
  else{
  TestCaseName = "user navigated to Contact Information if enter required details and click on Continue";
  Expected ="User should be navigated to Contact Information Page";	  
  Status= "Fail";
  Actual= "User is not navigated to Contact Information Page";
  resultoutput_pass();
  }
  }
  } catch (Exception e){
	  TestCaseName = "user navigated to Contact Information if enter required details and click on Continue";
	  Expected ="User should be navigated to Contact Information Page";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();  
		  
  }
  
  System.out.println("TC -003");
  
  
 /********* Test Case-4: ***************************************************************************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Validate that user is navigated to Business Details page if enter the contact information and click Continue
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Business Information Page     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
 
  Thread.sleep(2000);
  
  WebElement Salutation=driver.findElement(By.id("input_26_30"));
  Select Salut=new Select(Salutation);
  Salut.selectByIndex(3);
   
  driver.findElement(By.id("input_26_59")).sendKeys("test4 "+Second);
  driver.findElement(By.id("input_26_32")).sendKeys("test5 "+Second);
  
  WebElement ContnueButton3 = driver.findElement(By.id("gform_submit_button_26"));
  ContnueButton3.click();
  Thread.sleep(2000);
  WebElement BusinessDetailsPage = null;
 
  try {
  BusinessDetailsPage = driver.findElement(By.xpath("//h2[contains(text(),'Business Hours')]"));
  if(BusinessDetailsPage.isDisplayed()){
  String BusinessDetailsPagetext= BusinessDetailsPage.getText();
  if(BusinessDetailsPagetext.equalsIgnoreCase("Business Hours")){
  TestCaseName = "User navigated to Business Details Page if enter required details and click on Continue";
  Expected ="User should be navigated to Business Details Page";	  
  Status= "Pass";
  Actual= " User should be navigated to Business Details Page as Header "+BusinessDetailsPagetext+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "User navigated to Business Details Page if enter required details and click on Continue";
  Expected ="User should be navigated to Business Details Page";  
  Status= "Fail";
  Actual= "User is not navigated to Business Details Page";
  resultoutput_pass();
  }
  }
  } catch (Exception e){
	  TestCaseName = "User navigated to Business Details Page if enter required details and click on Continue";
	  Expected ="User should be navigated to Business Details Page"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();  
		  
  }
  
  System.out.println("TC -004");
  
  /********* Test Case-5: ***************************************************************************************************/
  
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Business Information Page-Validate all required fields
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Business Information Page     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(2000);
  driver.get(baseUrl + "/");
  
  WebElement RegisterLink1 = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink1.click();
  Thread.sleep(2000);
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
	  
  WebElement SelectCustomer1 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer1.click();
  

  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(2000);
  List<WebElement>  ErrorduetoBlankField2 = null;
  try {
	  ErrorduetoBlankField2 = driver.findElements(By.xpath("//div[contains(text(),'This field is required.')]"));
  
  if(ErrorduetoBlankField2.size()==5 ){
  String Errormessage= ((WebElement) ErrorduetoBlankField2).getText();
  if(Errormessage.equalsIgnoreCase("This field is required.")){
  TestCaseName = "Validate all required fields";
  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";	  
  Status= "Pass";
  Actual= " Error for all "+ErrorduetoBlankField2.size()+" blank fields with text"+Errormessage+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Validate all required fields";
  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";
  Status= "Fail";
  Actual= "Error is not displayed for all blank fields";
  resultoutput_pass();
  }
  }  
  } catch (Exception e){
	  TestCaseName = "Validate all required fields";
	  Expected ="Business name, Business category, Country, Timezone, Telephone number and Adress fields are required, If not entered, Error message is returned";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();  
		  
  }
  
  System.out.println("TC -005");
  
  /********* Test Case-6: ******************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Business Information Page-Validate “Continue” button
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(2000);
  driver.get(baseUrl + "/");
  Thread.sleep(2000);
  WebElement RegisterLink2 = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink2.click();
  Thread.sleep(2000);
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
	  
  WebElement SelectCustomer2 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer2.click();
  

  
  driver.findElement(By.id("input_3_16")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_3")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_14")).sendKeys("12"+Second);
  driver.findElement(By.id("input_3_4")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_6")).sendKeys("Test"+Second);
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(2000);
  WebElement ContinueButton = null;
  try {
  ContinueButton = driver.findElement(By.xpath("//a[contains(@class,'Continue')]"));
  if(ContinueButton.isDisplayed()){
  String ContinueButtonName= ContinueButton.getText();
  TestCaseName = "Validate “Continue” button";
  Expected ="“Continue” button should be displayed";	  
  Status= "Pass";
  Actual= "Button "+ContinueButtonName+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Validate “Continue” button";
  Expected ="“Continue” button should be displayed"; 
  Status= "Fail";
  Actual= "Error 'This field is required.' is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "Validate “Continue” button";
	  Expected ="“Continue” button should be displayed";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass(); 
  }
  
  System.out.println("TC -006");
  
  /********* Test Case-7: ******************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Business Information Page-Validate Telephone number-Enter more than 10 digits
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(2000);
  driver.get(baseUrl + "/");
  Thread.sleep(2000);
  WebElement RegisterLink3 = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink3.click();
  Thread.sleep(2000);
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
	  
  WebElement SelectCustomer3 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer3.click();
  
  
  driver.findElement(By.id("input_3_16")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_3")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_14")).sendKeys("12345678"+Second);
  driver.findElement(By.id("input_3_4")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_6")).sendKeys("Test"+Second);
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(2000);
  WebElement ErrorInvalidPhone = null;
  try {
  ErrorInvalidPhone = driver.findElement(By.xpath("//div[contains(@class,'Invalid telephone number')]"));
  if(ErrorInvalidPhone.isDisplayed()){
  String ErrorMsgInvalidPhone= ErrorInvalidPhone.getText();
  TestCaseName = "Validate Telephone number-Enter more than 10 digits";
  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number” below the text field.";	  
  Status= "Pass";
  Actual= "Error "+ErrorMsgInvalidPhone+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Validate Telephone number-Enter more than 10 digits";
  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number” below the text field."; 
  Status= "Fail";
  Actual= "Error ”Invalid telephone number” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "Validate Telephone number-Enter more than 10 digits";
	  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number” below the text field.";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass(); 
  }
  System.out.println("TC -007");
  
  /********* Test Case-8: ******************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Business Information Page
   * Test Case: Business Information Page-Validate Telephone number format
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(2000);
  driver.get(baseUrl + "/");
  Thread.sleep(2000);
  WebElement RegisterLink4 = driver.findElement(By.id("gform_submit_button_17_1"));
  RegisterLink4.click();
  Thread.sleep(2000);
  driver.findElement(By.id("input_3_2_chosen")).findElement(By.tagName("span")).click();
	  
  WebElement SelectCustomer4 = driver.findElement(By.xpath("//li[text()='Customer']"));
  SelectCustomer4.click();
  
  driver.findElement(By.id("input_3_16")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_3")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_3_2")).sendKeys("Test"+Second+"@g.com");
  driver.findElement(By.id("input_3_14")).sendKeys("A123b$"+Second);
  driver.findElement(By.id("input_3_4")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_4_2")).sendKeys("Test"+Second);
  driver.findElement(By.id("input_3_6")).sendKeys("Test"+Second);
  driver.findElement(By.id("choice_3_7_1")).click();
  driver.findElement(By.id("gform_submit_button_3")).click();
  Thread.sleep(2000);
  WebElement ErrorInvalidPhoneFormat = null;
  try {
  ErrorInvalidPhoneFormat = driver.findElement(By.xpath("//div[contains(@class,'Invalid telephone number format')]"));
  if(ErrorInvalidPhoneFormat.isDisplayed()){
  String ErrorMsgInvalidPhoneFormat= ErrorInvalidPhoneFormat.getText();
  TestCaseName = "Validate Telephone number format-Enter Alpha-numeric keys";
  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number format” below the text field";	  
  Status= "Pass";
  Actual= "Error "+ErrorMsgInvalidPhoneFormat+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "Validate Telephone number format";
  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number format” below the text field."; 
  Status= "Fail";
  Actual= "Error ”Invalid telephone number format” is not displayed";
  resultoutput_pass();
  }
  } catch (Exception e){
	  TestCaseName = "Validate Telephone number-Enter more than 10 digits";
	  Expected ="Telephone number field should be highlighted red with error message like :”Invalid telephone number format” below the text field.";
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass(); 
  }
  System.out.println("TC-008");
  
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  driver.close();
}
}
