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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class ChargeHolderData5 {

private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar
        .getInstance().getTime());
private static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "CIN Company Details " + timeStamp + " Summary" + ".xls";


private  static int chgholder =1;
private static String  CIN,Charge_ID,CompanyName,CreationOrModDate,AMOUNTSECURED,CHARGEHOLDER,ADDRESS,SRN;

public static void main(String[] args) throws IOException {


 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
 FirefoxProfile firefoxProfile = new FirefoxProfile();
 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
  String baseUrl = "http://www.mca.gov.in/DCAPortalWeb/dca/MyMCALogin.do?method=setDefaultProperty&mode=32";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\MasterData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("MASTERDATA");
HSSFSheet sheet = workbook.getSheet("CHARGEHOLDER");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String CINId = sheet1.getRow(row).getCell((short) 1).getStringCellValue(); 
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
	
looplbl: for(int t=0; t<99;t++){	
	
List<WebElement> Chargelist = driver.findElements(By.xpath("//tr[contains(@id,'rowlist')]"));
for(int q=2;q<Chargelist.size()+2;q++)
{
	
	HSSFCell cc= sheet.getRow(chgholder).createCell((short) 0);
	cc.setCellValue(chgholder);
	CIN = driver.findElement(By.xpath("//table[@class='main-forms']//tr[1]/td[2]")).getText();
	sheet.getRow(chgholder).createCell((short) 1).setCellValue(CIN);
	
	CompanyName =driver.findElement(By.xpath("//table[@class='main-forms']/tbody/tr[2]/td[2]")).getText();
	sheet.getRow(chgholder).createCell((short) 2).setCellValue(CompanyName);
	
	Charge_ID =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[2]")).getText();
	sheet.getRow(chgholder).createCell((short) 3).setCellValue(Charge_ID);
	System.out.println(Chargelist);
	
	CreationOrModDate =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[3]")).getText();
	sheet.getRow(chgholder).createCell((short) 4).setCellValue(CreationOrModDate);
			
	AMOUNTSECURED =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[4]")).getText();
	sheet.getRow(chgholder).createCell((short) 5).setCellValue(AMOUNTSECURED);
		
	CHARGEHOLDER =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[5]")).getText();
	sheet.getRow(chgholder).createCell((short) 6).setCellValue(CHARGEHOLDER);
	
	ADDRESS =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[6]")).getText();
	sheet.getRow(chgholder).createCell((short) 7).setCellValue(ADDRESS);
	
	SRN =driver.findElement(By.xpath("//tr["+q+"][contains(@id,'rowlist')]/td[7]")).getText();
	sheet.getRow(chgholder).createCell((short) 8).setCellValue(SRN);
	
	
	 FileOutputStream out =
	          new FileOutputStream(new File(filename));
	      workbook.write(out);
	      chgholder++;
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
}catch (Exception e){}
}
driver.close();
}
}
