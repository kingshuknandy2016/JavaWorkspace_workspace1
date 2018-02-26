package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

 
public class MasterData1 {


private static String CIN,ENTITYNAME,DATEOFINCORPORATION,STATE,ROC,CATEGORY,SUBCATEGORY,CLASS,AUTHORISEDCAPITAL;
private static String PAIDUPCAPITAL,NOOFMEMBER,ADD1,ADD2,CITY,PIN,REGISTEREDOFFICEADDRESS,CHARGESREGISTERED,PROSECUTIONDETAILS;

private static int counter=1;

            
public static void main(String[] args) throws IOException, InterruptedException {
	

	File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile();
	FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
	String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=31";
    driver.get(baseUrl + "/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
 
       
    FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\CINofCompany.xls");
    HSSFWorkbook workbook = new HSSFWorkbook(file);
   // HSSFSheet sheet1 = workbook.getSheet("citiesUK");
    HSSFSheet sheet = workbook.getSheet("MASTERDATA");

    for(int row=1; row <=sheet.getPhysicalNumberOfRows();row++) ///first for loop
    {
    try
    {	
    	  
    CIN = sheet.getRow(row).getCell(1).getStringCellValue(); 
    System.out.println(CIN);
    driver.findElement(By.id("cin")).clear();
    driver.findElement(By.id("cin")).sendKeys(CIN);
    driver.findElement(By.id("Default")).click();
    Thread.sleep(2000);

 
    ENTITYNAME = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[3]/td[2]")).getText();
    System.out.println(ENTITYNAME);
    sheet.getRow(counter).createCell(2).setCellValue(ENTITYNAME);
    
    DATEOFINCORPORATION = driver.findElement(By.xpath("//input[@id='IncorporationOrEstablishmentDate']")).getAttribute("value");
    System.out.println(DATEOFINCORPORATION);
    sheet.getRow(counter).createCell(3).setCellValue(DATEOFINCORPORATION);
 
    STATE = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[16]/td[2]")).getText();
    System.out.println(STATE);
    sheet.getRow(counter).createCell(4).setCellValue(STATE);
        
    ROC = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[4]/td[2]//option[@selected='']")).getText();
    System.out.println(ROC);
    sheet.getRow(counter).createCell(5).setCellValue(ROC);
    
    CATEGORY = driver.findElement(By.xpath("//select[@id='CompanyCategory']//option[@selected='']")).getText();
    System.out.println(CATEGORY);
    sheet.getRow(counter).createCell(6).setCellValue(CATEGORY);
    
    SUBCATEGORY = driver.findElement(By.xpath("//select[@id='CompanySubCategory']//option[@selected='']")).getText();
    System.out.println(SUBCATEGORY);
    sheet.getRow(counter).createCell(7).setCellValue(SUBCATEGORY);
    
    CLASS = driver.findElement(By.xpath("//tr[8]//td/input[@checked='checked']")).getAttribute("value");
    System.out.println(CLASS);
    sheet.getRow(counter).createCell(8).setCellValue(CLASS);
    
    AUTHORISEDCAPITAL = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[9]/td[2]")).getText();
    System.out.println(AUTHORISEDCAPITAL);
    sheet.getRow(counter).createCell(9).setCellValue(AUTHORISEDCAPITAL);
    
    PAIDUPCAPITAL = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[10]/td[2]")).getText();
    System.out.println(PAIDUPCAPITAL);
    sheet.getRow(counter).createCell(10).setCellValue(PAIDUPCAPITAL);
 
    NOOFMEMBER = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[11]/td[2]")).getText();
    System.out.println(NOOFMEMBER);
    sheet.getRow(counter).createCell(11).setCellValue(NOOFMEMBER);
    
    
    ADD1 = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[13]/td[2]")).getText();
    System.out.println(ADD1);
    
    ADD2 = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[14]/td[2]")).getText();
    System.out.println(ADD2);
    
    CITY = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[15]/td[2]")).getText();
    System.out.println(CITY);
    
    PIN = driver.findElement(By.xpath("//table[@id='DataBlock1']//tr[18]/td[2]")).getText();
    System.out.println(PIN);
    
    REGISTEREDOFFICEADDRESS = ADD1+ADD2 +CITY+PIN;
    
    sheet.getRow(counter).createCell(12).setCellValue(REGISTEREDOFFICEADDRESS);

  
  FileOutputStream out =
          new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls"));
      workbook.write(out);
      driver.findElement(By.id("Default")).click();
      Thread.sleep(2000);  
  
  counter++;
    
  }catch (Exception e){System.out.println("error "+e);
  } 
  }
    
  driver.close();
   
    
  }

}

