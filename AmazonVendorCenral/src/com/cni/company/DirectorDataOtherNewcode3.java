package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.Select;
 
public class DirectorDataOtherNewcode3 {
	
private static String  CIN,DIN_DPIN_PAN,NAME,CIN_LLPIN,COMPANY_LLP,DESIGNATION2,DATE_APPOINTED_IN_CURRENT_DESIGNATION;
private static String ORIGINAL_APPOINTMENT_DATE,CESSATION_DATE,COMPANY_LLP_STATUS,DEFAULTING_STATUS;


static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
        .getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CNI Director Details " + timeStamp + " Summary" + ".xls";
private static HSSFWorkbook hwb = new HSSFWorkbook();
private static HSSFSheet sheet = hwb.createSheet("Summary");
int row;
static int i=1;


static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
static FirefoxProfile firefoxProfile = new FirefoxProfile();
static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);


public static void main(String[] args)  throws Exception {
    ExcelColumnName();
    Scraper(driver);
}



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
row.createCell(2).setCellValue(DIN_DPIN_PAN);
row.createCell(3).setCellValue(NAME);
row.createCell(4).setCellValue(CIN_LLPIN);
row.createCell(5).setCellValue(COMPANY_LLP);
row.createCell(6).setCellValue(DESIGNATION2);
row.createCell(7).setCellValue(DATE_APPOINTED_IN_CURRENT_DESIGNATION);
row.createCell(8).setCellValue(ORIGINAL_APPOINTMENT_DATE);
row.createCell(9).setCellValue(CESSATION_DATE);
row.createCell(10).setCellValue(COMPANY_LLP_STATUS);
row.createCell(11).setCellValue(DEFAULTING_STATUS);

i++;
 }



              

public static void Scraper (WebDriver driver) throws IOException, InterruptedException {
	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb";
  driver.get(baseUrl + "");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  
  //driver.findElement(By.id("mmlink0")).click();
  //driver.findElement(By.xpath("//a[text()='Login']")).click();
  driver.findElement(By.id("reqduserid")).click();
  driver.findElement(By.id("reqduserid")).sendKeys("mymca911");
  driver.findElement(By.id("password")).click();
  driver.findElement(By.id("password")).sendKeys("P@ssw0rd");
/*  driver.findElement(By.id("reqduserCategory")).click();
  driver.findElement(By.id("//select[@id='reqduserCategory']//option[@value='REGU']")).click();*/
  
  
  Select dropdown = new Select(driver.findElement(By.id("reqduserCategory")));
  dropdown.selectByIndex(4); 
  
  Thread.sleep(20000);
  driver.findElement(By.id("Default")).click();
  
  driver.findElement(By.xpath("//table[@class='main-forms']/tbody/tr[3]/td[2]/a")).click();
 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("DIRECTORSDETAILS");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell(3).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("DIN")).clear();
driver.findElement(By.id("DIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(1000);

ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
WebDriver ndriver= driver.switchTo().window(tabs2.get(1));


/*WebElement chargelist = null;
try
{
	chargelist=  driver.findElement(By.xpath("//tr[contains(@class,'RowData')]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}*/
//if (chargelist != null)
{
List<WebElement> Chargelist = ndriver.findElements(By.xpath("//tr[contains(@class,'RowData')]"));
for(int pq=2;pq<=Chargelist.size();pq++)
{
		
	DIN_DPIN_PAN = ndriver.findElement(By.xpath("//table[1]//tr[1]/td[1]/h3")).getText();
		
	NAME =ndriver.findElement(By.xpath("//table[2]//tr[1]/td[2]/h3")).getText();
		
	CIN_LLPIN =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[2]")).getText();
	
	System.out.println(DIN_DPIN_PAN);
	
	COMPANY_LLP =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[3]")).getText();
				
	DESIGNATION2 =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[4]")).getText();
			
	DATE_APPOINTED_IN_CURRENT_DESIGNATION =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[5]")).getText();
		
	ORIGINAL_APPOINTMENT_DATE =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[6]")).getText();
		
	CESSATION_DATE =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[7]")).getText();
	
	COMPANY_LLP_STATUS =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[8]")).getText();
		
	DEFAULTING_STATUS =ndriver.findElement(By.xpath("//tr["+pq+"][contains(@class,'RowData')]/td[9]")).getText();
		
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
}
ndriver.close(); 
driver.switchTo().window(tabs2.get(0));

driver.findElement(By.id("reset")).click();
Thread.sleep(2000);
}
}catch (Exception e){}
}
driver.close();
}
}
