package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


/*********************************Class**************************************/
public class ChargeHolderDataNeeee {

static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
            .getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
            + "CNI Charge Holder Details " + timeStamp + " Summary" + ".xls";
private static HSSFWorkbook hwb = new HSSFWorkbook();
private static HSSFSheet sheet = hwb.createSheet("Summary");
int row;
static int i=1;

private static String CIN,CompanyName,ChargeID,Creation_Modification,amount,Holder,Address,SRN;

static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
static FirefoxProfile firefoxProfile = new FirefoxProfile();
static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);


public static void main(String[] args)  throws Exception {
    ExcelColumnName();
    Scraper(driver);
}


/********************************* Method to Write Excel *************************************/

private static void ExcelColumnName() throws Exception 
{         
HSSFRow rowhead = sheet.createRow(0);
rowhead.createCell(0).setCellValue("Sr. No");
rowhead.createCell(1).setCellValue("CIN");
rowhead.createCell(2).setCellValue("Company Name");
rowhead.createCell(3).setCellValue("Charge ID");
rowhead.createCell(4).setCellValue("Creation_Modification");
rowhead.createCell(5).setCellValue("amount");
rowhead.createCell(6).setCellValue("Holder");
rowhead.createCell(7).setCellValue("Address");
rowhead.createCell(8).setCellValue("SRN");
}


public static void resultoutput_pass() throws Exception 
{
HSSFRow row = sheet.createRow(i);
row.createCell(0).setCellValue(i);
row.createCell(1).setCellValue(CIN);
row.createCell(2).setCellValue(CompanyName);
row.createCell(3).setCellValue(ChargeID);
row.createCell(4).setCellValue(Creation_Modification);
row.createCell(5).setCellValue(amount);
row.createCell(6).setCellValue(Holder);
row.createCell(7).setCellValue(Address);
row.createCell(8).setCellValue(SRN);
i++;
 }


/*********************************Main Method**************************************/
/**
 * @return ***************************************************************************************/

public static void Scraper (WebDriver driver) throws IOException {

	
String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=32";
driver.get(baseUrl + "/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet  sheet1 = workbook.getSheet("MASTERDATA");
		

for(int row1=1; row1 <sheet1.getPhysicalNumberOfRows();row1++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row1).getCell(1).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("companyCIN")).clear();
driver.findElement(By.id("companyCIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(100);

WebElement chargelist = null;
try
{
chargelist=  driver.findElement(By.xpath("//tr[contains(@id,'rowlist')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (chargelist != null)
{
	
looplbl: for(int t=0; t<99;t++)
{
	
/*******************************Get Total Row***************************************/
List<WebElement> TotalTableRows = driver.findElements(By.xpath("//tr[contains(@id,'rowlist')]"));
int rowSize = TotalTableRows.size();
System.out.println(rowSize);


   for(WebElement record : TotalTableRows)
  //for (int p=1; p<=rowSize; p++) 
  {
	   
	  CIN = driver.findElement(By.xpath("//table[@class='main-forms']//tr[1]/td[2]")).getText();
	  CompanyName =driver.findElement(By.xpath("//table[@class='main-forms']/tbody/tr[2]/td[2]")).getText();
	  ChargeID= record.findElement(By.xpath("//td[2][@class='Field']")).getAttribute("innerHTML");
	  System.out.println(ChargeID);
	  Creation_Modification= record.findElement(By.xpath("//td[3][@class='Field']")).getText();            
	  amount= record.findElement(By.xpath("//td[4][@class='Field']")).getText();
	  Holder= record.findElement(By.xpath("//td[5][@class='Field']")).getText();
	  Address= record.findElement(By.xpath("//td[6][@class='Field']")).getText();
	  SRN= record.findElement(By.xpath("//td[7][@class='Field']")).getText();
	  System.out.println(SRN);
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
	  
  }
 

WebElement pgnext = null;
try
{
pgnext=  driver.findElement(By.xpath("//a[@id='nextlist1']"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (pgnext != null)
{
pgnext.click();
Thread.sleep(1000);
}
else if (pgnext == null)
{
driver.findElement(By.id("Default")).click();
Thread.sleep(1000);
break looplbl;
}
}
}
}catch (Exception e){e.printStackTrace();}
}
driver.close();
}
}


