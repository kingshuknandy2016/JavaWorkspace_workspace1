package com.cni.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class EFormData4 {
	
private  static int Eform=1;
private static String  CIN,CompanyName,NAME,ID,Date;

public static void main(String[] args) throws IOException {

 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
 FirefoxProfile firefoxProfile = new FirefoxProfile();
 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=12";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("MASTERDATA");
HSSFSheet sheet = workbook.getSheet("EFORMS");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell((short) 1).getStringCellValue(); 
System.out.println(CINId);
driver.findElement(By.id("companyCIN")).clear();
driver.findElement(By.id("companyCIN")).sendKeys(CINId);
driver.findElement(By.id("Default")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//table[@id='list1']//td/a")).click();

CIN =driver.findElement(By.xpath("//table[@id='list1']//td/a")).getText();

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
	sheet.getRow(Eform).createCell((short) 0).setCellValue(Eform);
	
	sheet.getRow(Eform).createCell((short) 1).setCellValue(CIN);
	
	CompanyName =ndriver.findElement(By.xpath("//table[@class='main-forms']//tr[1]/td[2]")).getText();
	sheet.getRow(Eform).createCell((short) 2).setCellValue(CompanyName);	
	System.out.println(CompanyName);
	NAME =ndriver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[1]")).getText();
	sheet.getRow(Eform).createCell((short) 3).setCellValue(NAME);
			
	ID =ndriver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[2]")).getText();
	sheet.getRow(Eform).createCell((short) 4).setCellValue(ID);
	
	System.out.println(ID);
		
	Date =ndriver.findElement(By.xpath("//table[@id='list1']//tr["+q+"][@height='21px']/td[3]")).getText();
	sheet.getRow(Eform).createCell((short) 5).setCellValue(Date);
	
			
	 FileOutputStream out =
	          new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls"));
	      workbook.write(out);
	      Eform++;
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
