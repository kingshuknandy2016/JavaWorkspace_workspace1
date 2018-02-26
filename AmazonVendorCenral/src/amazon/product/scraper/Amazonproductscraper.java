package amazon.product.scraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
 
public class Amazonproductscraper {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "AmazonProduct Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

int row, i=1;
private String  Imgurl,ProductDescrption,Asin,Price,Soldby,Availability,Bulletpoints;

File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
FirefoxProfile firefoxProfile = new FirefoxProfile();
FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);

              
public void AuroraSmokeTest() throws Exception {
                ExcelColumnName();
                getbooking();
}
 /*#######################################################
Method to export Pass & Fail Result in excel
###############################################################*/

private void ExcelColumnName() throws Exception 
                {         
                HSSFRow rowhead = sheet.createRow(0);
                rowhead.createCell((short) 0).setCellValue("Sr. No");
                rowhead.createCell((short) 1).setCellValue("ProductDescrption");
                rowhead.createCell((short) 2).setCellValue("Image Url");
                rowhead.createCell((short) 3).setCellValue("Asin");
                rowhead.createCell((short) 4).setCellValue("Price");
                rowhead.createCell((short) 5).setCellValue("Sold by");
                rowhead.createCell((short) 6).setCellValue("Availability");
                rowhead.createCell((short) 7).setCellValue("Bulletpoints");
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(ProductDescrption);
                row.createCell((short)2).setCellValue(Imgurl);
                row.createCell((short)3).setCellValue(Asin);
                row.createCell((short)4).setCellValue(Price);
                row.createCell((short)5).setCellValue(Soldby);
                row.createCell((short)6).setCellValue(Availability);
                row.createCell((short)7).setCellValue(Bulletpoints);                
                i++;
                 }
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://www.amazon.com";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
}

 /************************************************************************************************************************
* Project:           Booking.com

* Author:     Siddhartha Mondal (QA Automation Analyst )
****************************************************     Modification Section     **************************************
 * Last Modified On: 
 * Last Modified By:
 * Reason:                                          
 ************************************************************************************************************************/
public void getbooking() throws Exception 
  {
 
  applicationLaunch(driver);  
  
  
  try
  { 
  
  WebElement ShopByDept = driver.findElement(By.xpath("//a[@id='nav-link-shopall']/span[@class='nav-line-1']"));

	  
	  Actions action = new Actions(driver);
	  action.moveToElement(ShopByDept).build().perform();

  
  WebElement fulldirectory = null;
  try
  {
	  fulldirectory=  driver.findElement(By.xpath("id('nav-flyout-shopAll')//span[text()='Full Store Directory']"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (fulldirectory != null)
  {
	  fulldirectory.click();
  }
  
 Thread.sleep(2000);
  
  /****************** Get the Main Category list*****************************/
  List<WebElement> maincategory=driver.findElements(By.xpath("//table[@id='shopAllLinks']//tr/td[3]/div/ul/li/a"));
  System.out.println(maincategory.size());
  for(int p=0;p<maincategory.size();p++)
  {
	  maincategory.get(p).click();
	  
	  Thread.sleep(2000);

 /************** Get the Sub Category list*************************/   
  List<WebElement> SubCategory=driver.findElements(By.xpath("//div[@id='refinements']/div[@class='categoryRefinementsSection']//li/a"));
  for(int q=0;q<SubCategory.size();q++)
  {
 
  SubCategory.get(q).click();
  
  Thread.sleep(4000);
  
  /********* Get the Product**********/ 
  
  String totalpage = driver.findElement(By.xpath("//div[@id='pagn']/*[last()-2]")).getText();
  
  int pgn = Integer.parseInt(totalpage);
  System.out.println(pgn);
  for(int t=0;t<pgn;t++)
  {	  
  
  List<WebElement> Property1=driver.findElements(By.xpath("//*[contains(@id,'result_')]//img[@alt='Product Details']"));
 
 for (WebElement Prop:Property1)
  {
  //List<WebElement> Property=driver.findElements(By.xpath("//div[contains(@id,'result_')]"));	 
  ProductDescrption = Prop.findElement(By.tagName("h3")).findElement(By.tagName("a")).getText();
  System.out.println(ProductDescrption);
	 
  Imgurl = Prop.findElement(By.tagName("h3")).findElement(By.tagName("a")).getAttribute("href");
  System.out.println(Imgurl);
  
  Asin = Prop.getAttribute("name");
  System.out.println(Asin);
  
  Price = Prop.findElement(By.tagName("ul")).findElement(By.tagName("span")).getText();
  System.out.println(Price);
  

	  Actions act = new Actions(driver);
	  WebElement onElement = Prop.findElement(By.tagName("h3")).findElement(By.tagName("a"));
	  act.contextClick(onElement).perform();
	  act.sendKeys("w").perform();
	  
	  ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	  
	  WebDriver ndriver= driver.switchTo().window(tabs2.get(1));
	    Soldby = ndriver.findElement(By.id("merchant-info")).getText();
		  System.out.println(Soldby);
		  
		  Availability = ndriver.findElement(By.id("availability")).getText();
		  System.out.println(Availability);
		  
		  Bulletpoints = ndriver.findElement(By.id("feature-bullets")).getText();
		  System.out.println(Bulletpoints);
	    
		  ndriver.close();
	    driver.switchTo().window(tabs2.get(0));
	    
	  Thread.sleep(2000);
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
	  }
     WebElement NextPage=null;
     
     try
 
     {
	   NextPage = driver.findElement(By.xpath("//div[@id='pagn']/*[last()-1]"));
	  }
	  catch (Exception e)
	  {
	  System.out.println("error "+e);
	  }
	  if (NextPage != null)
	  {
	  NextPage.click();
	  Thread.sleep(2000);
	  } 
	  else{System.out.println("last page reached");; 
	  }
  }
  }
  }
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }      
}/// end of first for loop

}





         
                          
 
