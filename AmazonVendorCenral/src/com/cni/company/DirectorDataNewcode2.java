package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class DirectorDataNewcode2 {
	

static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
        .getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CNI Director Details " + timeStamp + " Summary" + ".xls";
private static HSSFWorkbook hwb = new HSSFWorkbook();
private static HSSFSheet sheet = hwb.createSheet("Summary");
int row;
static int i=1;

private static String CIN,CompanyName,DIN_DPIN_PAN,FullName,Address,Designation,DateofAppointment,DSCRegistered,ExpiryDateofDSC;

static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
static FirefoxProfile firefoxProfile = new FirefoxProfile();
static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);

/*************************Main Method**********************************************************/
public static void main(String[] args)  throws Exception {
    ExcelColumnName();
    Scraper(driver);
}


/********************************* Method to Write Excel *************************************/

private static void ExcelColumnName() throws Exception 
{         
HSSFRow rowhead = sheet.createRow(0);
rowhead.createCell(0).setCellValue("Sr No");
rowhead.createCell(1).setCellValue("CIN");
rowhead.createCell(2).setCellValue("Company Name");
rowhead.createCell(3).setCellValue("DIN/DPIN/PAN");
rowhead.createCell(4).setCellValue("Full Name");
rowhead.createCell(5).setCellValue("Address");
rowhead.createCell(6).setCellValue("Designation");
rowhead.createCell(7).setCellValue("Date of Appointment");
rowhead.createCell(8).setCellValue("DSC Registered");
rowhead.createCell(9).setCellValue("Expiry Date of DSC");
}


public static void resultoutput_pass() throws Exception 
{
HSSFRow row = sheet.createRow(i);
row.createCell(0).setCellValue(i);
row.createCell(1).setCellValue(CIN);
row.createCell(2).setCellValue(CompanyName);
row.createCell(3).setCellValue(DIN_DPIN_PAN);
row.createCell(4).setCellValue(FullName);
row.createCell(5).setCellValue(Address);
row.createCell(6).setCellValue(Designation);
row.createCell(7).setCellValue(DateofAppointment);
row.createCell(8).setCellValue(DSCRegistered);
row.createCell(9).setCellValue(ExpiryDateofDSC);

i++;
 }




public static void Scraper (WebDriver driver) throws IOException {

	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=39";
  driver.get(baseUrl + "");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("MASTERDATA");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell(1).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("companyCIN")).clear();
driver.findElement(By.id("companyCIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(2000);

WebElement chargelist = null;
try
{
	chargelist=  driver.findElement(By.xpath("//table[@class='main-forms']//tr[contains(@class,'RowData')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (chargelist != null)
{
List<WebElement> TotalTableRows = driver.findElements(By.xpath("//table[@class='main-forms']//tr[contains(@class,'RowData')]"));
//List<WebElement> TotalTableRows = driver.findElements(By.xpath("//table[@class='main-forms']//tr[@class='RowData' or @class='RowDataS']"));

System.out.println(TotalTableRows.size());

//for (WebElement Signatories:TotalTableRows)
for(int rownum=2;rownum<=TotalTableRows.size();rownum++)
{

	  CIN = driver.findElement(By.id("companyCIN")).getAttribute("value");
	  CompanyName =driver.findElement(By.id("companyName")).getAttribute("value");
	  DIN_DPIN_PAN= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[1][contains(@style,'font-size')]")).getText();
	  System.out.println(DIN_DPIN_PAN);
	  FullName= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[2][contains(@style,'font-size')]")).getText();           
	  Address= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[3][contains(@style,'font-size')]")).getText();
	  Designation= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[4][contains(@style,'font-size')]")).getText();
	  DateofAppointment= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[5][contains(@style,'font-size')]")).getText();
	  DSCRegistered= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[6][contains(@style,'font-size')]")).getText();
	  ExpiryDateofDSC= driver.findElement(By.xpath("//tr["+rownum+"][contains(@class,'RowData')]/td[7][contains(@style,'font-size')]")).getText();
	  System.out.println(ExpiryDateofDSC);
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);

	
  }driver.findElement(By.id("button6")).click();
	Thread.sleep(1000);

}
else {driver.get(baseUrl + "");}

}catch (Exception e){e.printStackTrace();}
}
driver.close();
}
}


