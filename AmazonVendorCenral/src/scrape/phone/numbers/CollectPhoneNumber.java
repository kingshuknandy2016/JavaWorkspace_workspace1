package scrape.phone.numbers;

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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
 
public class CollectPhoneNumber {

private static String  Company,PhoneNumber,Website,Country;


static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
        .getInstance().getTime());
static String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
        + "Phone Number Details " + timeStamp + " Summary" + ".xls";
private static HSSFWorkbook hwb = new HSSFWorkbook();
private static HSSFSheet sheet = hwb.createSheet("Summary");
int row;
static int i=1,arr=0;

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
rowhead.createCell(1).setCellValue("Company");
rowhead.createCell(2).setCellValue("Phone Number");
rowhead.createCell(3).setCellValue("Website");
rowhead.createCell(4).setCellValue("Country");

}


public static void resultoutput_pass() throws Exception 
{
HSSFRow row = sheet.createRow(i);
row.createCell(0).setCellValue(i);
row.createCell(1).setCellValue(Company);
row.createCell(2).setCellValue(PhoneNumber);
row.createCell(3).setCellValue(Website);
row.createCell(4).setCellValue(Country);

i++;
 }



public static void Scraper (WebDriver driver) throws IOException {

	
  String baseUrl = "http://www.google.com";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

 
FileInputStream file = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Result\\TestData.xls");
HSSFWorkbook workbook = new HSSFWorkbook(file);
HSSFSheet sheet1 = workbook.getSheet("MASTERDATA");

for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
{
try
{	
	  
String Companyexcel = sheet1.getRow(row).getCell(1).getStringCellValue(); 
System.out.println(Companyexcel);
WebElement element = driver.findElement(By.name("q"));
element.click();
element.sendKeys(Companyexcel);
element.submit();
Thread.sleep(2000);

WebElement properlink = null;
try
{
properlink=  driver.findElement(By.xpath("//cite"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (properlink != null)
{
driver.findElement(By.className("r")).findElement(By.tagName("a")).click();
Thread.sleep(1000);


/*String[] Contactext = {"CONTACT","CONTACT US","Contact","Contact Us","contact","contact us"};

for (int pq=0;pq<Contactext.length;pq++){
	
		
	WebElement Contactus = null;

	try
	{
	Contactus=  driver.findElement(By.xpath("//a[contains(text(),'"+Contactext[pq]+"')]"));		
	}
	catch (Exception e)
	{
	System.out.println("error "+e);
	}
	if (Contactus.isDisplayed())
	{
	//ContactusCapital.click();
	driver.findElement(By.tagName("a")).findElement(By.linkText(Contactext[pq])).click();
	}	
	else{System.out.println("no element");}
}*/
	
	
/*if(driver.findElement(By.xpath("//a[text()='"+Contactext[pq]+"']")).isDisplayed())
{
 driver.findElement(By.tagName("a")).findElement(By.linkText(Contactext[pq])).click();	
}*/




WebElement ContactusCapital = null;

try
{
ContactusCapital=  driver.findElement(By.xpath("(//*/a[contains(text(),'CONTACT')])[1]"));		
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (ContactusCapital != null)
{
	try
	{
ContactusCapital.click();
//driver.findElement(By.linkText("CONTACT US")).click();
	}
	catch (Exception e)
	{
	System.out.println("error "+e);
	}
}


WebElement ContactusSmall = null;
try
{
ContactusSmall =   driver.findElement(By.xpath("(//*/a[contains(text(),'Contact')])[1]"));
}
catch (Exception e)
{
System.out.println("error "+e);
}
if (ContactusSmall != null)
{
	try
	{	
ContactusSmall.click();	
//driver.findElement(By.linkText("Contact Us")).click();
}
catch (Exception e)
{
System.out.println("error "+e);
}


}


WebElement ContactusAllSmall = null;

try
{
ContactusAllSmall =   driver.findElement(By.xpath("(//*/a[contains(text(),'contact')])[1]"));
	
}
catch (Exception e)
{
System.out.println("error "+e);
}

if (ContactusAllSmall != null)
{
	
	try
	{	
 ContactusAllSmall.click(); 
 //driver.findElement(By.linkText("contact us")).click();
}
catch (Exception e)
{
System.out.println("error "+e);
}
 
 
}



  WebElement PhoneNumberWeb=  driver.findElement(By.tagName("body"));

  String phone =PhoneNumberWeb.getText();   
    
  
  if(phone.contains("Tel:")||phone.contains("Telephone:")||phone.contains("Phone:")){
  
  String[] part1 = phone.split("Tel:|Telephone:|Phone:");
  
  String AfterTel = part1[1];
  //String[] part2 = AfterTel.split("(?<=[0-9])(?=[a-zA-Z])");
  
  String[] part2 = AfterTel.split("(?<=\\G.{17})");
  
  //String[] part2 = AfterTel.split("(?<=\\G..................)");
  String Actualphone = part2[0];
  System.out.println(Actualphone);
  
  
  Company=Companyexcel;
  PhoneNumber=Actualphone;
  Website=driver.getCurrentUrl();
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);

  }
 
}

driver.get(baseUrl + "/");

}catch (Exception e)
{
System.out.println("error "+e);
}
}// end of for loop
driver.close(); 

}
}