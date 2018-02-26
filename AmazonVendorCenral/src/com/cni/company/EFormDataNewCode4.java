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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class EFormDataNewCode4 {

private static String  CIN,CINtext,CompanyName,NAME,ID,Date;


static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
        .getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CNI Eform Details " + timeStamp + " Summary" + ".xls";
private static HSSFWorkbook hwb = new HSSFWorkbook();
private static HSSFSheet sheet = hwb.createSheet("Summary");
int row;
static int i=1;

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
rowhead.createCell(3).setCellValue(" Form Name");
rowhead.createCell(4).setCellValue("ID");
rowhead.createCell(5).setCellValue("Date");

}


public static void resultoutput_pass() throws Exception 
{
HSSFRow row = sheet.createRow(i);
row.createCell(0).setCellValue(i);
row.createCell(1).setCellValue(CIN);
row.createCell(2).setCellValue(CompanyName);
row.createCell(3).setCellValue(NAME);
row.createCell(4).setCellValue(ID);
row.createCell(5).setCellValue(Date);

i++;
 }



public static void Scraper (WebDriver driver) throws IOException {

	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=12";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

 
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
driver.findElement(By.xpath("//table[@id='list1']//td/a")).click();

CINtext =driver.findElement(By.xpath("//table[@id='list1']//td/a")).getText();

System.out.println(CIN+"sidd");
Thread.sleep(100);

ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
WebDriver ndriver= driver.switchTo().window(tabs2.get(1));
ndriver.manage().window().maximize();
ndriver.findElement(By.xpath("//table[@id='DataBlock1']//a[text()='Other Documents Eform']")).click();

WebElement doclist = null;
try
{
doclist=  ndriver.findElement(By.xpath("//table[@id='list1']//tr[@height='21px']"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (doclist != null)
{


looplbl: for(int t=0; t<99;t++){
List<WebElement> docomentlist = ndriver.findElements(By.xpath("//table[@id='list1']//tr[@height='21px']"));

System.out.println(docomentlist.size());


for(int q=2;q<docomentlist.size()+2;q++)
{
	  CIN = CINtext;
	  CompanyName =ndriver.findElement(By.xpath("//table[@class='main-forms']//tr[1]/td[2]")).getText();
	  NAME = ndriver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[1]")).getText();
	  System.out.println(NAME);
	  ID= driver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[2]")).getText();           
	  Date= ndriver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[3]")).getText();
	  System.out.println(Date);
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
}

WebElement pgnext = null;
try
{
pgnext=  ndriver.findElement(By.xpath("//a[@id='nextlist1']"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (pgnext != null)
{
	pgnext.click();
	Thread.sleep(100);
}
else if (pgnext == null){ndriver.close(); 
driver.switchTo().window(tabs2.get(0));
driver.findElement(By.id("button5")).click();
break looplbl;
}
}
}
else{ndriver.close(); 
driver.switchTo().window(tabs2.get(0));
driver.findElement(By.id("button5")).click();
}
}catch (Exception e){
	System.out.println("error "+e);
}
}
driver.close();
}
}
