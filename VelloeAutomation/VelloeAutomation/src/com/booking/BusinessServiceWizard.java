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
 
public class BusinessServiceWizard {
private static String baseUrl;
static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar.getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"+ "Velloe Business Service Wizard " + timeStamp + " Summary" + ".xls";
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
   * Test Case: Validate when Category  is dragged and dropped  to Service Categories space
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
     
  WebElement MangSetting = driver.findElement(By.xpath("//li[@id='menu-item-211']/a"));
  MangSetting.click();
  Thread.sleep(4000);
  
  WebElement NewcatWidget = null;    
  WebElement GreyBox = null;
  
  try {
	  
  driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
  
  NewcatWidget = driver.findElement(By.xpath("//div[contains(@class,'newCategory newWidget')]"));
  String ncw = NewcatWidget.getText();
  System.out.println(ncw);  
  GreyBox = driver.findElement(By.xpath("//a[contains(@class,'categoriesInfo infoBorder btn')]"));
  
  Actions builder = new Actions(driver);  // Configure the Action  
  Action dragAndDrop = builder.clickAndHold(NewcatWidget)  
    .moveToElement(GreyBox)  
    .release(GreyBox)  
    .build();  // Get the action  
  dragAndDrop.perform(); // Execute the Action 
    
  WebElement CategoryNameLabel = null;
  WebElement CategorynameTextField = null;
  try {
  CategoryNameLabel = driver.findElement(By.xpath("//div[@class='widgetInputFieldLabel']"));
  CategorynameTextField = driver.findElement(By.id("ServiceCategoryName"));
  String CategoryNameLheader = CategoryNameLabel.getText();
 
  if(CategoryNameLheader.equalsIgnoreCase("* Category name:")&&CategorynameTextField.isDisplayed()){  
  TestCaseName = "TC001_Validate when Category  is dragged and dropped  to Service Categories space";
  Expected ="“Category name” text field should be presented";	  
  Status= "Pass";
  Actual= "Text field "+CategoryNameLheader+" is displayed";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC001_Validate when Category  is dragged and dropped  to Service Categories space";
  Expected ="“Category name” text field should be presented";	  
  Status= "Fail";
  Actual= "“Category name” text field is presented";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC001_Validate when Category  is dragged and dropped  to Service Categories space";
	  Expected ="“Category name” text field should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
 
  }catch (Exception e){
	  TestCaseName = "TC001_Validate when Category  is dragged and dropped  to Service Categories space";
	  Expected ="“Category name” text field should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
      resultoutput_pass();
      takeScreenShot(TestCaseName);
  }
   
 System.out.println("TC_001");
 
  /********* Test Case-2: *********************************************/
 
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate Category name
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
 
  driver.findElement(By.id("ServiceCategoryName")).sendKeys(testname);
  WebElement SaveButon =driver.findElement(By.xpath("//button[contains(@class,'saveWidget')]"));
  SaveButon.click();
  WebElement CategoryName = null;  
  try {	  
  CategoryName =driver.findElement(By.xpath("//span[@class='detailText']"));
  String CategoryNametext = CategoryName.getText();
  if(CategoryNametext.equalsIgnoreCase(testname))
  {
  TestCaseName = "TC002_Validate Category name";
  Expected ="Category name should be saved when “Save” label is clicked";	  
  Status= "Pass";
  Actual= "Category name is saved when “Save” label is clicked";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC002_Validate Category name";
  Expected ="Category name should be saved when “Save” label is clicked";  
  Status= "Fail";
  Actual= "Category name is not saved when “Save” label is clicked";
  resultoutput_pass();
  }
   } catch (Exception e){
	  TestCaseName = "TC002_Validate Category name";
	  Expected ="Category name should be saved when “Save” label is clicked"; 
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
   }
  System.out.println("TC_002"); 
  
  /********* Test Case-3: **************************************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when drop-down icon is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On:
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  
  WebElement CatgoryTextField = null;
  WebElement RemoveButton =null;
  WebElement closeButton =null;
  WebElement dropdownicon = null;
  try {
  dropdownicon=driver.findElement(By.xpath("//a[@href='#']"));
  dropdownicon.click();
  CatgoryTextField = driver.findElement(By.id("ServiceCategoryName"));
  RemoveButton = driver.findElement(By.xpath("//button[text()='Remove']"));
  closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
    
  if(CatgoryTextField.isDisplayed()&&RemoveButton.isDisplayed()&&closeButton.isDisplayed()){	  
  TestCaseName = "TC003_Validate when drop-down icon is clicked";
  Expected ="Category widget should open and “Category name” text-field ”Remove” and “Cancel ” label are presented";	  
  Status= "Pass";
  Actual= "Category widget opens and “Category name” text-field ”Remove” and “Cancel ” label are presented";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC003_Validate when drop-down icon is clicked";
  Expected ="Category widget should open and “Category name” text-field ”Remove” and “Close ” label are presented";		  
  Status= "Fail";
  Actual= "Category widget not opens and “Category name” text-field ”Remove” and “Close ” label are not presented";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC003_Validate when drop-down icon is clicked";
	  Expected ="Category widget should open and “Category name” text-field ”Remove” and “Close ” label are presented";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
   
  System.out.println("TC_003");
  
/********* Test Case-4: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when drop-down icon is clicked while the widget is in open state
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  Thread.sleep(4000);

  WebElement widgetclose= null;
  
  try {
	  
  dropdownicon=driver.findElement(By.xpath("//a[@href='#']"));
  dropdownicon.click();
  widgetclose =driver.findElement(By.xpath("//div[contains(@style,'display: none')]"));

  if(widgetclose!=null){
  TestCaseName = "TC004_Validate when drop-down icon is clicked while the widget is in open state";
  Expected ="Category widget should fold-up and not show Category widget fields";	  
  Status= "Pass";
  Actual= "Category widget is folded-up and is not showing  Category widget fields";
  resultoutput_pass();
  }
  else{
  TestCaseName = "TC004_Validate when drop-down icon is clicked while the widget is in open state";
  Expected ="Category widget should fold-up and not show Category widget fields";		  
  Status= "Fail";
  Actual= "Category widget is not folded-up and is showing  Category widget fields";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC004_Validate when drop-down icon is clicked while the widget is in open state";
	  Expected ="Category widget should fold-up and not show Category widget fields";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  }
  
  System.out.println("TC_004");
  
  /********************************* Test Case-5: ********************************************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when Category name is changed
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
  Thread.sleep(4000);
  WebElement RemoveButton1 = null;
  WebElement CancelButton1 = null;
  WebElement SaveButton1 = null;
  try {
	  
  dropdownicon=driver.findElement(By.xpath("//a[@href='#']"));
  dropdownicon.click();	  
  CategoryName = driver.findElement(By.id("ServiceCategoryName"));
  CategoryName.click();
  CategoryName.clear();  
  CategoryName .sendKeys("NewData");
   
  RemoveButton1 = driver.findElement(By.xpath("//button[text()='Remove']"));
  CancelButton1 = driver.findElement(By.xpath("//button[text()='Cancel']"));
  SaveButton1 = driver.findElement(By.xpath("//button[text()='Save']"));


  if(CancelButton1.isEnabled()&&RemoveButton1.isEnabled()&&SaveButton1.isEnabled()){
  TestCaseName = "TC005_Validate when Category name is changed";
  Expected ="“Save”,”Remove” and “Cancel ” label should be presented";	  
  Status= "Pass";
  Actual= "“Save”,”Remove” and “Cancel ” label are presented";
  resultoutput_pass();
 
  }
  else{
  TestCaseName = "TC005_Validate when Category name is changed";
  Expected ="“Save”,”Remove” and “Cancel ” label should be presented";		  
  Status= "Fail";
  Actual= "“Save”,”Remove” and “Cancel ” label are not presented";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  }
  } catch (Exception e){
	  TestCaseName = "TC005_Validate when Category name is changed";
	  Expected ="“Save”,”Remove” and “Cancel ” label should be presented";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  System.out.println("TC_005");
  
  /********* Test Case-6: *********************************************/
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
  
  driver.findElement(By.xpath("//button[text()='Remove']")).click();
  
  try {
	  
  String baseWindowHdl = driver.getWindowHandle();
  driver.switchTo().defaultContent();
  String PopUptextElement = driver.findElement(By.id("generalConfirmContent")).getText();
  
  System.out.println(PopUptextElement);
  if(PopUptextElement.equalsIgnoreCase("Are you sure that you would like to remove this Category?")){
  TestCaseName = "TC006_Validate when “Remove label is clicked";
  Expected ="A pop-up with text 'Are you sure that you would like to remove this Category?' should be presented";	  
  Status= "Pass";
  Actual= "A pop-up with text "+PopUptextElement+" is presented";
  resultoutput_pass();
  driver.findElement(By.xpath("//button[@class='close generalConfirmClose']")).click();
  driver.switchTo().window(baseWindowHdl);
 
  }
  else{
  TestCaseName = "TC006_Validate when “Remove label is clicked";
  Expected ="A pop-up with text 'Are you sure that you would like to remove this Category?' should be presented";		  
  Status= "Fail";
  Actual= "A pop-up with text "+PopUptextElement+" is presented";
  resultoutput_pass();
  takeScreenShot(TestCaseName);
  driver.findElement(By.xpath("//button[@class='close generalConfirmClose']")).click();
  driver.switchTo().window(baseWindowHdl);
  }
  } catch (Exception e){
	  TestCaseName = "TC006_Validate when “Remove label is clicked";
	  Expected ="A pop-up with text 'Are you sure that you would like to remove this Category?' should be presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
  } 
  
  System.out.println("TC_006");
  
/********* Test Case-7: *********************************************/
  /* Project  : Velloe Automation Script
   * Module   : Authentication
   * Test Case: Validate when “Remove label is clicked
   * Author   : Siddhartha Mondal (QA Automation Analyst )
  ****************************************************     Authentication Section     **************************************
   * Last Modified On: 
   * Last Modified By:
   * Reason:                                          
   ************************************************************************************************************************/
  
      Thread.sleep(4000);
      
      driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
      
      driver.findElement(By.xpath("//button[text()='Remove']")).click();
      
      WebElement YesButton=null;
      WebElement NoButton =null;
      
      try {
    	  
      String baseWindowHdl = driver.getWindowHandle();
      driver.switchTo().defaultContent();
      YesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
      NoButton = driver.findElement(By.xpath("//button[text()='No']"));
    
      if(YesButton.isDisplayed()&&NoButton.isDisplayed()){
      TestCaseName = "TC007_Validate when “Remove label is clicked";
      Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";	  
      Status= "Pass";
      Actual= "A confirmation pop-up with Yes and Cancel button are presented";
      resultoutput_pass();
      driver.findElement(By.xpath("//button[@class='close generalConfirmClose']")).click();
      driver.switchTo().window(baseWindowHdl);
     
      }
      else{
      TestCaseName = "TC007_Validate when “Remove label is clicked";
      Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";		  
      Status= "Fail";
      Actual= "A confirmation pop-up with Yes and Cancel button are not presented";
      resultoutput_pass();
      takeScreenShot(TestCaseName);
      driver.findElement(By.xpath("//button[@class='close generalConfirmClose']")).click();
      driver.switchTo().window(baseWindowHdl);
      }
      } catch (Exception e){
          TestCaseName = "TC007_Validate when “Remove label is clicked";
          Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";	
    	  Status= "Fail";
    	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
    	  resultoutput_pass();
    	  takeScreenShot(TestCaseName);
      }
	  
	  System.out.println("TC_007");
	  
/********* Test Case-8: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate if “No” is clicked in Confirmation pop-up
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                        
	   ************************************************************************************************************************/	  
	   	  
	      Thread.sleep(4000);
	  
	      WebElement NoButton1=null;
	      try {
		  driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
		  List<WebElement> elemntsbeforeremove = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
		  int bfrNo =elemntsbeforeremove.size();
		  
		  System.out.println(bfrNo);
		  
	      driver.findElement(By.xpath("//button[text()='Remove']")).click();
	      String baseWindowHdl = driver.getWindowHandle();
	      driver.switchTo().defaultContent();
	      NoButton1 = driver.findElement(By.xpath("//button[text()='No']"));
	      NoButton1.click();
	      driver.switchTo().window(baseWindowHdl);
	      driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));	      

		  List<WebElement> elemntsafterRemove = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
		  int aftrNo =elemntsafterRemove.size();
		  System.out.println(aftrNo);
		  
		  if(bfrNo==aftrNo){  
		  TestCaseName = "TC008_Validate if “No” is clicked in Confirmation pop-up";
		  Expected ="Category should not be deleted";	  
		  Status= "Pass";
		  Actual= "Category is not removed";
		  resultoutput_pass();
		  }
		  else if (bfrNo>aftrNo){
		  TestCaseName = "TC008_Validate if “No” is clicked in Confirmation pop-up";
		  Expected ="Category should not be deleted";		  
		  Status= "Fail";
		  Actual= "Category is removed";
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
		  }
		  } catch (Exception e){
			  TestCaseName = "TC008_Validate if “No” is clicked in Confirmation pop-up";
			  Expected ="Category should not be deleted";		
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		  }
	  
	  System.out.println("TC_008");
	  
	  
	  
/********* Test Case-9: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate if “Yes” is clicked in Confirmation pop-up
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/  
		  
	  Thread.sleep(4000);
      WebElement YesButton1=null;
      try {
	 
	  List<WebElement> elemntsbeforeremove = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
	  int bfrRem =elemntsbeforeremove.size();
	  
	  System.out.println(bfrRem);
	  
      driver.findElement(By.xpath("//button[text()='Remove']")).click();
      String baseWindowHdl = driver.getWindowHandle();
      driver.switchTo().defaultContent();
      YesButton1 = driver.findElement(By.xpath("//button[text()='Yes']"));
      YesButton1.click();
      driver.switchTo().window(baseWindowHdl);
      driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));	      

	  List<WebElement> elemntsafterRemove = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
	  int aftrRem =elemntsafterRemove.size();
	  System.out.println(aftrRem);
	  
	  if(bfrRem>aftrRem){	  
	  TestCaseName = "TC009_Validate if “Yes” is clicked in Confirmation pop-up";
	  Expected ="Category  should be removed";	  
	  Status= "Pass";
	  Actual= "Category is removed";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC009_Validate if “Yes” is clicked in Confirmation pop-up";
	  Expected ="Category  should be removed";		  
	  Status= "Fail";
	  Actual= "Category is not removed";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }
	  } catch (Exception e){
		  TestCaseName = "TC009_Validate if “Yes” is clicked in Confirmation pop-up";
		  Expected ="Category  should be removed";		
		  Status= "Fail";
		  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }
  

	  System.out.println("TC_009");
	  
	  /********* Test Case-10: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when “Cancel”  label is clicked before “Save” button
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  Thread.sleep(2000);
	  
	  try{
	  NewcatWidget = driver.findElement(By.xpath("//div[contains(@class,'newCategory newWidget')]"));
	  String ncw = NewcatWidget.getText();
	  System.out.println(ncw);  
	  GreyBox = driver.findElement(By.xpath("//a[contains(@class,'categoriesInfo infoBorder btn')]"));
	  
	  Actions builder = new Actions(driver);  // Configure the Action  
	  Action dragAndDrop = builder.clickAndHold(NewcatWidget)  
	    .moveToElement(GreyBox)  
	    .release(GreyBox)  
	    .build();  // Get the action  
	  dragAndDrop.perform(); // Execute the Action 
	  
		  driver.findElement(By.id("ServiceCategoryName")).sendKeys("Testqa");
		  List<WebElement> elemntsbeforecancel = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
		  int bfrcan =elemntsbeforecancel.size();
		  WebElement CancelButon =driver.findElement(By.xpath("//button[contains(@class,'cancelWidget')]"));
		  CancelButon.click();  
		  List<WebElement> elemntsaftercancel = driver.findElements(By.xpath("//div[@class='servicesAndResources']//div"));
		  int aftrcan =elemntsaftercancel.size();
		  if(bfrcan>aftrcan){	  
		  TestCaseName = "TC010_Validate when “Cancel”  label is clicked before “Save” button";
		  Expected ="Category  should be removed";	  
		  Status= "Pass";
		  Actual= "Category is removed";
		  resultoutput_pass();
		  }
		  else{
		  TestCaseName = "TC010_Validate when “Cancel”  label is clicked before “Save” button";
		  Expected ="Category  should be removed";		  
		  Status= "Fail";
		  Actual= "Category is not removed";
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
		  }
		  } catch (Exception e){
			  TestCaseName = "TC010_Validate when “Cancel”  label is clicked before “Save” button";
			  Expected ="Category  should be removed";		
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		  }
	  System.out.println("TC_010");
	  
	  
	  
/********* Test Case-11: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when sorting created categories
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
************************************************************************************************************************/
	  	  
	  
	  Thread.sleep(4000);
	  WebElement FirstCat = null;
	  WebElement LastCat = null;
	  
	  try{
		  
		  NewcatWidget = driver.findElement(By.xpath("//div[contains(@class,'newCategory newWidget')]"));
		  String ncw = NewcatWidget.getText();
		  System.out.println(ncw);  
		  GreyBox = driver.findElement(By.xpath("//a[contains(@class,'categoriesInfo infoBorder btn')]"));
		  
		  Actions builder = new Actions(driver);  // Configure the Action  
		  Action dragAndDrop = builder.clickAndHold(NewcatWidget)  
		    .moveToElement(GreyBox)  
		    .release(GreyBox)  
		    .build();  // Get the action  
		  dragAndDrop.perform(); // Execute the Action 
		  
		  driver.findElement(By.id("ServiceCategoryName")).sendKeys("xyz1"+testname);
		  driver.findElement(By.xpath("//button[contains(@class,'saveWidget')]")).click();
		  
		  
		  
		  Actions builder1 = new Actions(driver);  // Configure the Action  
		  Action dragAndDrop1 = builder1.clickAndHold(NewcatWidget)  
		    .moveToElement(GreyBox)  
		    .release(GreyBox)  
		    .build();  // Get the action  
		  dragAndDrop1.perform(); // Execute the Action 
		  
		  driver.findElement(By.id("ServiceCategoryName")).sendKeys("xyz2"+testname);
		  driver.findElement(By.xpath("//button[contains(@class,'saveWidget')]")).click();
		  
		  
		  LastCat = driver.findElement(By.xpath("(//*/.//div[contains(@class,'category clearfix widgetMenuItem ui-draggable ui-draggable-handle ui-droppable')]/div[@class='widget'])[last()]"));
		
		  
		  String LastCatNamebeforemove = driver.findElement(By.xpath("//div[@id='categoriesAndServicesContent']/*[last()]//span[@class='detailText']")).getText();
		  System.out.println(LastCatNamebeforemove);  
		  FirstCat = driver.findElement(By.xpath("(//*/.//div[contains(@class,'ui-draggable-handle ui-droppable')]/div[@class='widget'])[1]"));
		  
		  Actions builder2 = new Actions(driver);  // Configure the Action  
		  Action dragAndDrop2 = builder2.clickAndHold(LastCat)  
		    .moveToElement(FirstCat)  
		    .release(FirstCat)  
		    .build();  // Get the action
		  dragAndDrop2.perform(); // Execute the Action 
		  
		  String FirstCatNameaftremove = driver.findElement(By.xpath("//div[@id='categoriesAndServicesContent']/div[1]//span[@class='detailText']")).getText();
		  System.out.println(FirstCatNameaftremove); 
		  
		  
			  if(LastCatNamebeforemove.equalsIgnoreCase(FirstCatNameaftremove)){	  
			  TestCaseName = "TC011_Validate when sorting created categories";
			  Expected ="The category should shift from  its current position to the desired position";	  
			  Status= "Pass";
			  Actual= "The category shifts from  its current position to the desired position";
			  resultoutput_pass();
			  }
			  else{
			  TestCaseName = "TC011_Validate when sorting created categories";
			  Expected ="The category should shift from  its current position to the desired position";		  
			  Status= "Fail";
			  Actual= "The category not shifted from  its current position to the desired position";
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
			  }
			  } catch (Exception e){
				  TestCaseName = "TC011_Validate when sorting created categories";
				  Expected ="The category should shift from  its current position to the desired position";	
				  Status= "Fail";
				  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
				  resultoutput_pass();
				  takeScreenShot(TestCaseName);
			  }
	  System.out.println("TC_011");
	  
	  /********* Test Case-12: *********************************************/
	  /* Project  : Velloe Automation Script
	   * Module   : Authentication
	   * Test Case: Validate when Service is dragged and dropped  onto Category widget
	   * Author   : Siddhartha Mondal (QA Automation Analyst )
	  ****************************************************     Authentication Section     **************************************
	   * Last Modified On: 
	   * Last Modified By:
	   * Reason:                                          
	   ************************************************************************************************************************/
	  	  
	  Thread.sleep(4000);
	  WebElement ServiceWidget = null;
	  WebElement FirstCategory = null;
	  
	  
	  try{	
		  ServiceWidget = driver.findElement(By.xpath("//div[text()='New Service Widget']"));
	  
		  FirstCategory = driver.findElement(By.xpath("//div[@id='categoriesAndServicesContent']/div[1]"));
		  
		  Actions builder = new Actions(driver);  // Configure the Action  
		  Action dragAndDrop = builder.clickAndHold(ServiceWidget)  
		    .moveToElement(FirstCategory)  
		    .release(FirstCategory)
		    .build();  // Get the action  
		  dragAndDrop.perform(); // Execute the Action
	
		  WebElement AddedServiceWidget =driver.findElement(By.xpath("//div[contains(@class,'linkedService')]"));
		  
      if(AddedServiceWidget.isDisplayed()){
	  TestCaseName = "TC012_Validate when Service is dragged and dropped  onto Category widget";
	  Expected ="The Service widget  should open";	  
	  Status= "Pass";
	  Actual= "The Service widget opens";
	  resultoutput_pass();
	  }
	  else{
	  TestCaseName = "TC012_Validate when Service is dragged and dropped  onto Category widget";
	  Expected ="The Service widget  should open";	  
	  Status= "Fail";
	  Actual= "The Service widget not opens";
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
	  }	 
	  } catch (Exception e){
		  TestCaseName = "TC012_Validate when Service is dragged and dropped  onto Category widget";
		  Expected ="The Service widget  should open";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	  }  
	  
System.out.println("TC_012");


/********* Test Case-13: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when drop-down icon is clicked while the widget is in open state
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Servicewidgetfoldup= null;
WebElement Servicewiddropdownicon= null;

try {
	  
Servicewiddropdownicon=driver.findElement(By.xpath("//div[contains(@class,'linkedService')]//a[@href='#']"));
Servicewiddropdownicon.click();
Servicewidgetfoldup =driver.findElement(By.xpath("//div[contains(@style,'display: none;')]"));

if(Servicewidgetfoldup!=null){
TestCaseName = "TC013_Validate when drop-down icon is clicked while the widget is in open state";
Expected ="Service widget should fold-up and not show Service widget fields";	  
Status= "Pass";
Actual= "Service widget is folded-up and is not showing Service widget fields";
resultoutput_pass();
}
else{
TestCaseName = "TC013_Validate when drop-down icon is clicked while the widget is in open state";
Expected ="Service widget should fold-up and not show Service widget fields";		  
Status= "Fail";
Actual= "Service widget is not folded-up and is showing Service widget fields";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC013_Validate when drop-down icon is clicked while the widget is in open state";
	  Expected ="Service widget should fold-up and not show Service widget fields";		
	  Status= "Fail";
	  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}

System.out.println("TC_013");


/********* Test Case-14: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when drop-down icon is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement Servicewidgetopenup= null;
	
	try {
		  
		Servicewiddropdownicon=driver.findElement(By.xpath("//div[contains(@class,'linkedService')]//a[@href='#']"));
		Servicewiddropdownicon.click();
		Servicewidgetopenup =driver.findElement(By.xpath("//div[@id='new-widget']//div[contains(@style,'display: block;')]"));

		if(Servicewidgetopenup.isDisplayed()){
		TestCaseName = "TC014_Validate when drop-down icon is clicked";
		Expected ="Service widget should open and  widget’s text-fields are presented";	  
		Status= "Pass";
		Actual= "Service widget has opened and  widget’s text-fields are presented";
		resultoutput_pass();
		}
		else{
		TestCaseName = "TC014_Validate when drop-down icon is clicked";
		Expected ="Service widget should open and  widget’s text-fields are presented";			  
		Status= "Fail";
		Actual= "Service widget not opened and  widget’s text-fields are not presented";
		resultoutput_pass();
		takeScreenShot(TestCaseName);
		}
		} catch (Exception e){
			  TestCaseName = "TC014_Validate when drop-down icon is clicked";
			  Expected ="Service widget should open and  widget’s text-fields are presented";	
			  Status= "Fail";
			  Actual= "Exception Occured"+"Error"+e.getLocalizedMessage();
			  resultoutput_pass();
			  takeScreenShot(TestCaseName);
		}

System.out.println("TC_014");


/********* Test Case-15: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate required Fields-Name
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Description = null;
WebElement Save = null;

try{
	
Description = driver.findElement(By.id("ServiceDescription"));
Description.sendKeys("Test");

Save = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Service name cannot be blank.")){

TestCaseName = "TC015_Validate required Fields-Name";
Expected ="A popup should be presented with message “Service Name cannot be blank.";	  
Status= "Pass";
Actual= "A popup with message '"+PopUptextElement+"'  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC015_Validate required Fields-Name";
Expected ="A popup should be presented with message “Service Name cannot be blank."; 
Status= "Fail";
Actual= "A popup with message '"+PopUptextElement+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC015_Validate required Fields-Name";
	  Expected ="A popup should be presented with message “Service Name cannot be blank.";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}
System.out.println("TC_015");


/********* Test Case-16: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate required Fields-Price
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

    WebElement Name = null;
	WebElement Price = null;
	WebElement Save1 = null;

	try{
	Name = driver.findElement(By.id("ServiceName"));
	Name.sendKeys("Test");
		
	Price = driver.findElement(By.id("ServicePrice"));
	Price.click();
	Price.clear();

	Save1 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
	Save1.click();

	String baseWindowHdl = driver.getWindowHandle();
	driver.switchTo().defaultContent();
	String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();
	
	if(PopUptextElement.contains("Price must have a value of 0 or greater, and cannot be blank.")){

	TestCaseName = "TC016_Validate required Fields-Price";
	Expected ="A popup should be presented with message “Service Price cannot be blank.";	  
	Status= "Pass";
	Actual= "A popup with message '"+PopUptextElement+"'  is presented";
	resultoutput_pass();
	driver.findElement(By.xpath("//button[text()='Close']")).click();
	driver.switchTo().window(baseWindowHdl);
	driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
	}
	else{
	TestCaseName = "TC016_Validate required Fields-Price";
	Expected ="A popup should be presented with message “Service Price cannot be blank."; 
	Status= "Fail";
	Actual= "A popup with message '"+PopUptextElement+"' is presented";
	resultoutput_pass();
	takeScreenShot(TestCaseName);
	driver.findElement(By.xpath("//button[text()='Close']")).click();
	driver.switchTo().window(baseWindowHdl);
	driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
	}

	} catch (Exception e){
		  TestCaseName = "TC016_Validate required Fields-Price";
		  Expected ="A popup should be presented with message “Service Price cannot be blank.";
		  Status= "Fail";
		  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
		  resultoutput_pass();
		  takeScreenShot(TestCaseName);
	}    

System.out.println("TC_016");

/********* Test Case-17: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate required Fields-Duration
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Price1 = null;
WebElement Duration = null;
WebElement Save2 = null;

try{	
		
Price1 = driver.findElement(By.id("ServicePrice"));
Price1.click();
Price1.sendKeys("2000");
	
Duration = driver.findElement(By.id("ServiceDuration"));
Duration.click();
Duration.clear();

Save2 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save2.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Service duration must have a value of 15 (minutes) greater, and cannot be blank.")){

TestCaseName = "TC017_Validate required Fields-Duration";
Expected ="A popup should be presented with message “Service Price cannot be blank.";	  
Status= "Pass";
Actual= "A popup with message '"+PopUptextElement+"'  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC017_Validate required Fields-Duration";
Expected ="A popup should be presented with message “Service Price cannot be blank."; 
Status= "Fail";
Actual= "A popup with message '"+PopUptextElement+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC017_Validate required Fields-Duration";
	  Expected ="A popup should be presented with message “Service Price cannot be blank.";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}
System.out.println("TC_017");



/********* Test Case-18: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate required Fields-ServiceMaximumSimultaneousBookings
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Duration1 = null;
WebElement MaxSimBookings = null;
WebElement Save3 = null;

try{

Duration1 = driver.findElement(By.id("ServiceDuration"));
Duration1.sendKeys("15");

MaxSimBookings = driver.findElement(By.id("ServiceMaximumSimultaneousBookings"));
MaxSimBookings.click();
MaxSimBookings.clear();

Save3 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save3.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Maximum simultaneous bookings must have a value of 1 or greater and cannot be blank.")){

TestCaseName = "TC018_Validate required Fields-ServiceMaximumSimultaneousBookings";
Expected ="A popup should be presented with message “Maximum simultaneous bookings must have a value of 1 or greater and cannot be blank.";	  
Status= "Pass";
Actual= "A popup with message '"+PopUptextElement+"'  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC018_Validate required Fields-ServiceMaximumSimultaneousBookings";
Expected ="A popup should be presented with message “Maximum simultaneous bookings must have a value of 1 or greater and cannot be blank."; 
Status= "Fail";
Actual= "A popup with message '"+PopUptextElement+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC018_Validate required Fields-ServiceMaximumSimultaneousBookings";
	  Expected ="A popup should be presented with message “Maximum simultaneous bookings must have a value of 1 or greater and cannot be blank.";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}
System.out.println("TC_018");

/********* Test Case-19: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Duration text field-Enter alpha-numeric data
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Duration2 = null;
WebElement MaxSimBookings1 = null;
WebElement Save4 = null;

try{

Duration2 = driver.findElement(By.id("ServiceDuration"));
Duration2.click();
Duration2.clear();
Duration1.sendKeys("1abc5");

MaxSimBookings1 = driver.findElement(By.id("ServiceMaximumSimultaneousBookings"));
MaxSimBookings1.click();
MaxSimBookings1.sendKeys("2");

Save4 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save4.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Service duration must have a value of 15 (minutes) greater, and cannot be blank.")){

TestCaseName = "TC019_Validate Duration text field-Enter alpha-numeric data";
Expected ="A popup should be presented with message “Service duration must numeric";	  
Status= "Pass";
Actual= "A popup with message '"+PopUptextElement+"'  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC019_Validate Duration text field-Enter alpha-numeric data";
Expected ="A popup should be presented with message “Service duration must numeric";
Status= "Fail";
Actual= "A popup with message '"+PopUptextElement+"' is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}

} catch (Exception e){
	TestCaseName = "TC019_Validate Duration text field-Enter alpha-numeric data";
	Expected ="A popup should be presented with message “Service duration must numeric";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}

System.out.println("TC_019");


/********* Test Case-20: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Maximum simultaneous bookings text-field-Enter alpha-numeric data
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement Duration3 = null;
WebElement MaxSimBookings2 = null;
WebElement Save5 = null;

try{


Duration3 = driver.findElement(By.id("ServiceDuration"));
Duration3.click();
Duration3.clear();
Duration3.sendKeys("15");

MaxSimBookings2 = driver.findElement(By.id("ServiceMaximumSimultaneousBookings"));
MaxSimBookings2.click();
MaxSimBookings2.clear();
MaxSimBookings2.sendKeys("2abc3");

Save5 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save5.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Maximum simultaneous bookings must have a value of 1 or greater and cannot be blank.")){

TestCaseName = "TC020_Validate Maximum simultaneous bookings text-field-Enter alpha-numeric data";
Expected ="A popup should be presented with message “Maximum simultaneous bookings must numeric";	  
Status= "Pass";
Actual= "A popup with message "+PopUptextElement+"  is presented";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC020_Validate Maximum simultaneous bookings text-field-Enter alpha-numeric data";
Expected ="A popup should be presented with message “Maximum simultaneous bookings must numeric";
Status= "Fail";
Actual= "A popup with message " +PopUptextElement+ " is presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}

} catch (Exception e){
	TestCaseName = "TC020_Validate Maximum simultaneous bookings text-field-Enter alpha-numeric data";
	Expected ="A popup should be presented with message “Maximum simultaneous bookings must numeric";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}

System.out.println("TC_020");


/********* Test Case-21: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate Service text-fields
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);


WebElement MaxSimBookings3 = null;
WebElement Save6 = null;
WebElement ServiceHeader=null;

try{

MaxSimBookings3 = driver.findElement(By.id("ServiceMaximumSimultaneousBookings"));
MaxSimBookings3.click();
MaxSimBookings3.clear();
MaxSimBookings3.sendKeys("2");

Save6 = driver.findElement(By.xpath("//div[@id='new-widget']//Button[text()='Save']"));
Save6.click();

ServiceHeader = driver.findElement(By.xpath("//div[contains(@class,'linkedService')]//span[@class='detailText']"));

if(ServiceHeader.isDisplayed()){
TestCaseName = "TC021_Validate Service text-fields";
Expected ="Service text-fields should be saved when “Save” label is clicked";	  
Status= "Pass";
Actual= "Service  '"+ServiceHeader.getText()+"'  is saved";
resultoutput_pass();
}
else{
TestCaseName = "TC021_Validate Service text-fields";
Expected ="Service text-fields should be saved when “Save” label is clicked";
Status= "Fail";
Actual= "Service text-fields are not saved when “Save” label is clicked";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	TestCaseName = "TC021_Validate Service text-fields";
	Expected ="Service text-fields should be saved when “Save” label is clicked";
	Status= "Fail";
	Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	resultoutput_pass();
	takeScreenShot(TestCaseName);
}
System.out.println("TC_021");

/********* Test Case-22: *********************************************/
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
WebElement ServiceWidget1 = null;
WebElement FirstCategory1 = null;

try{
	
	List<WebElement> BefCancel= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
	int  befcansize =BefCancel.size();
	  ServiceWidget1 = driver.findElement(By.xpath("//div[text()='New Service Widget']"));
	  FirstCategory1 = driver.findElement(By.xpath("//div[@id='categoriesAndServicesContent']/div[1]"));
	  
	  Actions builder = new Actions(driver);  // Configure the Action  
	  Action dragAndDrop = builder.clickAndHold(ServiceWidget1)  
	    .moveToElement(FirstCategory1)  
	    .release(FirstCategory1)
	    .build();  // Get the action  
	  dragAndDrop.perform(); // Execute the Action	  
		  

System.out.println(befcansize);
driver.findElement(By.xpath("//div[@id='new-widget']//button[text()='Cancel']")).click();
List<WebElement> AftCancel= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  aftcansize =AftCancel.size();
System.out.println(aftcansize);  
if(befcansize==aftcansize){
TestCaseName = "TC022_Validate when “Cancel”  label is clicked before Information gets saved";
Expected ="The entered Information should not be saved and Service should be removed";	  
Status= "Pass";
Actual= "The entered Information is not saved and Service is removed";
resultoutput_pass();
}
else{
TestCaseName = "TC022_Validate when “Cancel”  label is clicked before Information gets saved";
Expected ="The entered Information should not be saved and Service should be removed";	  
Status= "Fail";
Actual= "The entered Information is saved and Service is not removed";
resultoutput_pass();
takeScreenShot(TestCaseName);

}

} catch (Exception e){
	  TestCaseName = "TC022_Validate when “Cancel”  label is clicked before Information gets saved";
	  Expected ="The entered Information should not be saved and Service should be removed";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_022");

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

//driver.navigate().refresh();

//driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));

WebElement Yesbttn= null;
WebElement Nobttn =null;

try{

driver.navigate().refresh();
Thread.sleep(4000);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
driver.findElement(By.xpath("//div[contains(@class,'category')][1]//div[2][contains(@class,'linkedService ')]//a")).click();
driver.findElement(By.xpath("//div[contains(@class,'category')][1]//button[text()='Remove']")).click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();


String PopUptextElement = driver.findElement(By.id("generalConfirmContent")).getText();
Yesbttn = driver.findElement(By.id("generalAlertContent")).findElement(By.xpath("//button[text()='Yes']"));
Nobttn = driver.findElement(By.id("generalAlertContent")).findElement(By.xpath("//button[text()='No']"));
  
if(PopUptextElement.equalsIgnoreCase("Are you sure that you would like to remove this Service?")&&Yesbttn.isDisplayed()&&Nobttn.isDisplayed()){
TestCaseName = "TC023_Validate when Remove label is clicked";
Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";	  
Status= "Pass";
Actual= "A Pop up with text "+PopUptextElement+ " and with Yes and Cancel button presented";
resultoutput_pass();
}
else{
TestCaseName = "TC023_Validate when Remove label is clicked";
Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";	  
Status= "Fail";
Actual= "A Pop up with text "+PopUptextElement+ " and with Yes and Cancel button presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}

} catch (Exception e){
	  TestCaseName = "TC023_Validate when Remove label is clicked";
	  Expected ="A confirmation pop-up with Yes and Cancel  button should be presented";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_023");

/********* Test Case-24: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate if “Cancel” is clicked in Confirmation pop-up
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{

driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
List<WebElement> BefNo= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  BefNosize =BefNo.size();
System.out.println(BefNosize);

driver.switchTo().defaultContent();
driver.findElement(By.id("generalConfirm")).findElement(By.xpath("//button[text()='No']")).click();
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));

List<WebElement> AftNo= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  aftnosize =AftNo.size();
System.out.println(aftnosize); 
  
if(BefNosize==aftnosize){
TestCaseName = "TC024_Validate if “Cancel” is clicked in Confirmation pop-up";
Expected ="When “Cancel” is clicked in Confirmation pop-up, the Service should not be deleted";	  
Status= "Pass";
Actual= "“Cancel” is clicked in Confirmation pop-up, the Service is not deleted";
resultoutput_pass();
}
else if(BefNosize>aftnosize){
TestCaseName = "TC024_Validate if “Cancel” is clicked in Confirmation pop-up";
Expected ="When “Cancel” is clicked in Confirmation pop-up, the Service should not be deleted";		  
Status= "Fail";
Actual= "“Cancel” is clicked in Confirmation pop-up, the Service is deleted";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC024_Validate if “Cancel” is clicked in Confirmation pop-up";
	  Expected ="When “Cancel” is clicked in Confirmation pop-up, the Service should not be deleted";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_024");

/********* Test Case-25: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate if “Yes” is clicked in Confirmation pop-up
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

try{

driver.findElement(By.xpath("//div[contains(@class,'category')][1]//button[text()='Remove']")).click();	

List<WebElement> BefYes= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  BefYessize =BefYes.size();
System.out.println(BefYessize);

driver.switchTo().defaultContent();
driver.findElement(By.id("generalConfirm")).findElement(By.xpath("//button[text()='Yes']")).click();
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));

List<WebElement> AftYes= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  AftYessize =AftYes.size();
System.out.println(AftYessize); 
  
if(BefYessize>AftYessize){
TestCaseName = "TC025_Validate if “Yes” is clicked in Confirmation pop-up";
Expected ="When “Yes” is clicked in Confirmation pop-up, the Service should be deleted";	  
Status= "Pass";
Actual= "“Yes” is clicked in Confirmation pop-up, the Service is deleted";
resultoutput_pass();
}
else{
TestCaseName = "TC025_Validate if “Yes” is clicked in Confirmation pop-up";
Expected ="When “Yes” is clicked in Confirmation pop-up, the Service should be deleted";	  
Status= "Fail";
Actual= "“Yes” is clicked in Confirmation pop-up, the Service is not deleted";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC025_Validate if “Yes” is clicked in Confirmation pop-up";
	  Expected ="When “Yes” is clicked in Confirmation pop-up, the Service should be deleted";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_025");



/********* Test Case-26: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate user can add more than one service
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement ServiceWidget2 = null;
WebElement FirstCategory2 = null;
WebElement ServiceName = null;
WebElement ServicePrice = null;
WebElement ServiceDuration = null;
WebElement ServiceMaximumSimultaneousBookings = null;

try{
	
	List<WebElement> BefCancel= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
	int  befcansize =BefCancel.size();
	System.out.println(befcansize);
	ServiceWidget2 = driver.findElement(By.xpath("//div[text()='New Service Widget']"));
	FirstCategory2 = driver.findElement(By.xpath("//div[@id='categoriesAndServicesContent']/div[1]"));
	  
	  Actions builder = new Actions(driver);  // Configure the Action  
	  Action dragAndDrop = builder.clickAndHold(ServiceWidget2)  
	    .moveToElement(FirstCategory2)  
	    .release(FirstCategory2)
	    .build();  // Get the action  
	  dragAndDrop.perform(); // Execute the Action
	  
ServiceName= driver.findElement(By.id("ServiceName"));
ServiceName.click();
ServiceName.sendKeys("pqrs"+testname);
ServicePrice=driver.findElement(By.id("ServicePrice"));
ServicePrice.click();
ServicePrice.clear();
ServicePrice.sendKeys("5000");
ServiceDuration=driver.findElement(By.id("ServiceDuration"));
ServiceDuration.click();
ServiceDuration.clear();
ServiceDuration.sendKeys("30");
ServiceMaximumSimultaneousBookings=driver.findElement(By.id("ServiceMaximumSimultaneousBookings"));
ServiceMaximumSimultaneousBookings.click();
ServiceMaximumSimultaneousBookings.clear();
ServiceMaximumSimultaneousBookings.sendKeys("10");

driver.findElement(By.xpath("//div[@id='new-widget']//button[text()='Save']")).click();

List<WebElement> AftCancel= driver.findElements(By.xpath("//div[@ id='categoriesAndServicesContent']//div"));
int  aftcansize =AftCancel.size();
System.out.println(aftcansize);  
if(befcansize<aftcansize){
TestCaseName = "TC026_Validate user can add more than one service ";
Expected ="User should be able to create more than one service";	  
Status= "Pass";
Actual= "The entered Information is saved and Service is Created";
resultoutput_pass();
}
else{
TestCaseName = "TC026_Validate user can add more than one service ";
Expected ="User should be able to create more than one service"	 ; 
Status= "Fail";
Actual= "The entered Information is not saved and Service is not created";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC026_Validate user can add more than one service ";
	  Expected ="User should be able to create more than one service";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
}  

System.out.println("TC_026");

/********* Test Case-27: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate a New Resource is created
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement NewRes = null;
WebElement ResTobeadded = null;

try{

List <WebElement> ResBfrAdd = driver.findElements(By.xpath("//span[@class='updateSchedule widgetButton']"));
int TotalResBfrAdd =ResBfrAdd.size();
System.out.println(TotalResBfrAdd);

NewRes = driver.findElement(By.xpath("//div[text()='New Resource Widget']"));
ResTobeadded = driver.findElement(By.xpath("//a[contains(@class,'resourcesInfo infoBorder btn btn-default dropContainer ui-droppable')]"));

Actions builder = new Actions(driver);  // Configure the Action  
Action dragAndDrop = builder.clickAndHold(NewRes)  
  .moveToElement(ResTobeadded)  
  .release(ResTobeadded)
  .build();  // Get the action  
dragAndDrop.perform(); // Execute the Action

List <WebElement> ResAfrAdd = driver.findElements(By.xpath("//span[@class='updateSchedule widgetButton']"));
int TotalResAfrAdd =ResAfrAdd.size();
System.out.println(TotalResAfrAdd);

if(TotalResAfrAdd>TotalResBfrAdd){
TestCaseName = "TC027_Validate a New Resource is created";
Expected ="New Resource should be created";	  
Status= "Pass";
Actual= "New Resource is created";
resultoutput_pass();
}
else{
TestCaseName = "TC027_Validate a New Resource is created";
Expected ="New Resource should be created";		  
Status= "Fail";
Actual= "New Resource is not created";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC027_Validate a New Resource is created";
	  Expected ="New Resource should be created";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_027");

/********* Test Case-28: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when drop-down icon is clicked while the widget is in open state
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement NewResDropdown = null;
WebElement NewResfoldup = null;
try{

NewResDropdown = driver.findElement(By.id("resourcesContent")).findElement(By.id("new-widget")).findElement(By.tagName("a"));
NewResDropdown.click();

NewResfoldup = driver.findElement(By.xpath("//div[@class='resourceDetail widgetDetail' and @style='display: none;']"));

if(NewResfoldup!=null){
TestCaseName = "TC028_Validate when drop-down icon is clicked while the widget is in open state";
Expected ="Resource widget should fold-up and not show Resource widget fields";	  
Status= "Pass";
Actual= "Resource widget fold-up and not show Resource widget fields";
resultoutput_pass();
}
else{
TestCaseName = "TC028_Validate when drop-down icon is clicked while the widget is in open state";
Expected ="Resource widget should fold-up and not show Resource widget fields";		  
Status= "Fail";
Actual= "Resource widget not fold-up and shows Resource widget fields";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC028_Validate when drop-down icon is clicked while the widget is in open state";
	  Expected ="Resource widget should fold-up and not show Resource widget fields";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_028");

/********* Test Case-29: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when drop-down icon is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement NewResDropdown1 = null;
WebElement NewResfoldup1 = null;
WebElement NametextFld1 = null;
WebElement EmltextFld = null;
try{

NewResDropdown1 = driver.findElement(By.id("resourcesContent")).findElement(By.id("new-widget")).findElement(By.tagName("a"));
NewResDropdown1.click();

NewResfoldup1 = driver.findElement(By.xpath("//div[@class='resourceDetail widgetDetail' and @style='display: block;']"));
NametextFld1 = driver.findElement(By.id("PersonDisplayName"));
EmltextFld = driver.findElement(By.id("PersonEmailAddress"));

if(NewResfoldup1.isDisplayed()){
TestCaseName = "TC029_Validate when drop-down icon is clicked";
Expected ="Resource widget should open and  widget’s text-fields are presented";	  
Status= "Pass";
Actual= "Resource widget has opened and  widget’s text-fields are presented";
resultoutput_pass();
}
else{
TestCaseName = "TC029_Validate when drop-down icon is clicked";
Expected ="Resource widget should open and  widget’s text-fields are presented";;		  
Status= "Fail";
Actual= "Resource widget has not opened and  widget’s text-fields are not presented";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC029_Validate when drop-down icon is clicked";
	  Expected ="Resource widget should open and  widget’s text-fields are presented";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_029");

/********* Test Case-30: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Leave Name text-field blank and click ‘“Save”
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement SaveBttn1 = null;
WebElement NametextFld2 = null;

try{

NametextFld1 = driver.findElement(By.xpath("//div[@id='resourcesContent']//input[@class='widgetInputField']"));
NametextFld1.click();
NametextFld1.sendKeys("Test");
NametextFld1.clear();
/*EmltextFld = driver.findElement(By.xpath("//input[@id='PersonEmailAddress']"));
EmltextFld.click();
EmltextFld.sendKeys("Test@g.com");*/

SaveBttn1 = driver.findElement(By.xpath("//div[@id='resourcesContent']//button[text()='Save']"));
SaveBttn1.click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.equalsIgnoreCase("Name cannot be blank.")){
TestCaseName = "TC030_Leave Name text-field blank and click ‘“Save”";
Expected ="A pop-up dialog should be returned if Name is left blank";	  
Status= "Pass";
Actual= "A pop-up dialog with text '"+PopUptextElement+"' is returned if Name is left blank";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC030_Leave Name text-field blank and click ‘“Save”";
Expected ="A pop-up dialog should be returned if Name is left blank";		  
Status= "Fail";
Actual= "A pop-up dialog with different text '"+PopUptextElement+"' is returned if Name is left blank";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	 TestCaseName = "TC030_Leave Name text-field blank and click ‘“Save”";
	 Expected ="A pop-up dialog should be returned if Name is left blank";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_030");

/********* Test Case-31: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate user can Save a newly created Resource
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement NametextFld = null;
WebElement SaveBttn = null;

try{
	
NametextFld = driver.findElement(By.xpath("//div[@id='resourcesContent']//input[@class='widgetInputField']"));
NametextFld.click();
NametextFld.sendKeys("pqr"+testname);

SaveBttn = driver.findElement(By.xpath("//div[@id='resourcesContent']//button[text()='Save']"));
SaveBttn.click();

driver.findElement(By.id("resourcesContent")).findElement(By.tagName("a")).click();

String AutoColor = driver.findElement(By.xpath("(.//div[contains(@class,'evo-pointer evo-colorind-ff')])[1]")).getAttribute("style");
driver.findElement(By.id("resourcesContent")).findElement(By.tagName("a")).click();

if(AutoColor!=null){
TestCaseName = "TC031_Validate user can Save a newly created Resource";
Expected ="User sould be able to Save a newly created Resource";	  
Status= "Pass";
Actual= "User sould able to Save a newly created Resource";
resultoutput_pass();
}
else{
TestCaseName = "TC031_Validate user can Save a newly created Resource";
Expected ="User sould be able to Save a newly created Resource";		  
Status= "Fail";
Actual= "Calendar display colour is assigned automatically when a Resource is created";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC031_Validate user can Save a newly created Resource";
	  Expected ="Calendar display colour should be  assigned automatically when a Resource is created";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_031");

/********* Test Case-32: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate calendar display colour
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement NametextF3 = null;
WebElement SaveBttn3 = null;
WebElement NewRes2 = null;
WebElement ResTobeadded2 = null;


try{
	NewRes2 = driver.findElement(By.xpath("//div[text()='New Resource Widget']"));
	ResTobeadded2 = driver.findElement(By.xpath("//a[contains(@class,'resourcesInfo infoBorder btn btn-default dropContainer ui-droppable')]"));

	Actions builder = new Actions(driver);  // Configure the Action  
	Action dragAndDrop = builder.clickAndHold(NewRes2)  
	  .moveToElement(ResTobeadded2)  
	  .release(ResTobeadded2)
	  .build();  // Get the action  
	dragAndDrop.perform();

NametextF3 = driver.findElement(By.xpath("//div[@id='resourcesContent']//input[@class='widgetInputField']"));
NametextF3.click();
NametextF3.sendKeys("pqr"+testname);

SaveBttn3 = driver.findElement(By.xpath("//div[@id='resourcesContent']//button[text()='Save']"));
SaveBttn3.click();

driver.findElement(By.id("resourcesContent")).findElement(By.tagName("a")).click();

String AutoColor = driver.findElement(By.xpath("(.//div[contains(@class,'evo-pointer evo-colorind-ff')])[1]")).getAttribute("style");

if(AutoColor!=null){
TestCaseName = "TC032_Validate calendar display colour";
Expected ="Calendar display colour should be  assigned automatically when a Resource is created";	  
Status= "Pass";
Actual= "Calendar display colour '"+AutoColor+"' is assigned automatically when a Resource is created";
resultoutput_pass();
}
else{
TestCaseName = "TC032_Validate calendar display colour";
Expected ="Calendar display colour should be  assigned automatically when a Resource is created";		  
Status= "Fail";
Actual= "Calendar display colour is assigned automatically when a Resource is created";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC032_Validate calendar display colour";
	  Expected ="Calendar display colour should be  assigned automatically when a Resource is created";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_032");

/********* Test Case-33: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when calendar display colour box is clicked
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);
WebElement colorBx = null;

try{

colorBx = driver.findElement(By.xpath("(.//div[contains(@class,'evo-pointer evo-colorind-ff')])[1]"));
colorBx.click();

if(colorBx!=null){
TestCaseName = "TC033_Validate calendar display colour";
Expected ="Calendar display colour should be  assigned automatically when a Resource is created";	  
Status= "Pass";
Actual= "Calendar display colour '"+colorBx+"' is assigned automatically when a Resource is created";
resultoutput_pass();
}
else{
TestCaseName = "TC033_Validate calendar display colour";
Expected ="Calendar display colour should be  assigned automatically when a Resource is created";		  
Status= "Fail";
Actual= "Calendar display colour is assigned automatically when a Resource is created";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC033_Validate calendar display colour";
	  Expected ="Calendar display colour should be  assigned automatically when a Resource is created";	
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_033");


/********* Test Case-34: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Drag and drop Resource widget to Service widget
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
	  
Thread.sleep(4000);

WebElement FirstService = null;
WebElement FirstResource = null;
WebElement AddedResource = null;

try{

FirstService = driver.findElement(By.xpath("(//*/.//div[contains(@class,'linkedService clearfix widgetMenuItem ui-draggable ui-draggable-handle ui-droppable')]//div[@class='widgetHeading'])[1]"));
FirstResource = driver.findElement(By.xpath("(//*/.//div[contains(@class,'resource widgetMenuItem ui-draggable ui-draggable-handle')]//div[@class='widgetHeading'])[1]"));

String FrstResName = FirstResource.findElement(By.className("resourceDetailNameContainer")).getText();

Actions builder = new Actions(driver);  // Configure the Action  
Action dragAndDrop = builder.clickAndHold(FirstResource)  
  .moveToElement(FirstService)  
  .release(FirstService)
  .build();  // Get the action  
dragAndDrop.perform(); // Execute the Action

AddedResource = driver.findElement(By.xpath("(//*/.//div[contains(@class,'linkedResource widgetMenuItem')]//div[@class='widgetHeading'])[1]"));

String AddedResName = AddedResource.findElement(By.className("resourceDetailNameContainer")).getText();


if(AddedResource.isDisplayed()&&FrstResName.equalsIgnoreCase(AddedResName)){
TestCaseName = "TC034_Drag and drop Resource widget to Service widget";
Expected ="A Resource widget should be attached to Service";	  
Status= "Pass";
Actual= "A Resource widget '"+AddedResName+"' is attached to Service";
resultoutput_pass();
}
else{
TestCaseName = "TC034_Drag and drop Resource widget to Service widget";
Expected ="A Resource widget should be attached to Service";		  
Status= "Fail";
Actual= "Resource widget is not attached to Service";
resultoutput_pass();
takeScreenShot(TestCaseName);
}
} catch (Exception e){
	  TestCaseName = "TC034_Drag and drop Resource widget to Service widget";
	  Expected ="A Resource widget should be attached to Service";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_034");


/********* Test Case-35: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when deleting Service widget with its attached Resources
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/

try{

driver.findElement(By.xpath("//div[contains(@class,'category')][1]//div[2][contains(@class,'linkedService')]//a")).click();
driver.findElement(By.xpath("//div[contains(@class,'category')][1]//button[text()='Remove']")).click();	

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
String PopUptextElement = driver.findElement(By.id("generalAlertContent")).getText();

if(PopUptextElement.contains("Unable to remove Service. Please remove all Groups and Resources first.")){
TestCaseName = "TC035_Validate when deleting Service widget with its attached Resources";
Expected ="A dialog message should be presented with message like “Unable to remove Service. Please remove all Groups and Resources first”";	  
Status= "Pass";
Actual= "A dialog message is presented with text '"+PopUptextElement+"'";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC035_Validate when deleting Service widget with its attached Resources";
Expected ="A dialog message should be presented with message like “Unable to remove Service. Please remove all Groups and Resources first”";	  
Status= "Fail";
Actual= "“Yes” is clicked in Confirmation pop-up, the Service is not deleted";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC035_Validate when deleting Service widget with its attached Resources";
	  Expected ="A dialog message should be presented with message like “Unable to remove Service. Please remove all Groups and Resources first”";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_035");

/********* Test Case-36: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when deleting a Resource from attached Service.
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/

try{
	
List <WebElement> AddedResBfRemv = driver.findElements(By.xpath("//div[@id='categoriesAndServicesContent']//div[contains(@class,'linkedResource')]"));
int totalresBfRemv=AddedResBfRemv.size();
System.out.println(totalresBfRemv);

driver.findElement(By.xpath("(//*/.//div[contains(@class,'linkedResource widgetMenuItem')]//button[text()='Unlink'])[1]")).click();
driver.findElement(By.xpath("//div[contains(@class,'category')][1]//button[text()='Remove']")).click();	

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
driver.findElement(By.id("generalConfirm")).findElement(By.xpath("//button[text()='Yes']")).click();
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));

List <WebElement> AddedResAftRemv = driver.findElements(By.xpath("//div[@id='categoriesAndServicesContent']//div[contains(@class,'linkedResource')]"));
int totalresAftRemv=AddedResAftRemv.size();
System.out.println(totalresAftRemv);

if(totalresBfRemv>totalresAftRemv){
TestCaseName = "TC036_Validate when deleting a Resource from attached Service.";
Expected ="Resource should be removed from group";	  
Status= "Pass";
Actual= "Resource is removed from group";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC036_Validate when deleting a Resource from attached Service.";
Expected ="Resource should be removed from group";
Status= "Fail";
Actual= "“Yes” is clicked in Confirmation pop-up, the Service is not deleted";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC036_Validate when deleting a Resource from attached Service.";
	  Expected ="Resource should be removed from group";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_036");


/********* Test Case-37: *********************************************/
/* Project  : Velloe Automation Script
 * Module   : Authentication
 * Test Case: Validate when Schedule is clicked.
 * Author   : Siddhartha Mondal (QA Automation Analyst )
****************************************************     Authentication Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
WebElement schedule=null;
try{
	
driver.findElement(By.id("resourcesContent")).findElement(By.xpath("//span[text()='Schedule']")).click();

String baseWindowHdl = driver.getWindowHandle();
driver.switchTo().defaultContent();
schedule=driver.findElement(By.id("modalScheduleWidgetContent"));


driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));


if(schedule!=null){
TestCaseName = "TC037_Validate when Schedule is clicked";
Expected ="A schedule widget Should be  presented with Resource name attached to it";	  
Status= "Pass";
Actual= "A schedule widget is presented with Resource name attached to it";
resultoutput_pass();
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
else{
TestCaseName = "TC037_Validate when Schedule is clicked";
Expected ="A schedule widget Should be  presented with Resource name attached to it";
Status= "Fail";
Actual= "A schedule widget is not presented with Resource name attached to it";
resultoutput_pass();
takeScreenShot(TestCaseName);
driver.findElement(By.xpath("//button[text()='Close']")).click();
driver.switchTo().window(baseWindowHdl);
driver.switchTo().frame(driver.findElement(By.id("businessServicesSetup")));
}
} catch (Exception e){
	  TestCaseName = "TC037_Validate when Schedule is clicked";
	  Expected ="A schedule widget Should be  presented with Resource name attached to it";
	  Status= "Fail";
	  Actual= "Exception Occured"+"  Error "+e.getLocalizedMessage();
	  resultoutput_pass();
	  takeScreenShot(TestCaseName);
} 
System.out.println("TC_037");
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