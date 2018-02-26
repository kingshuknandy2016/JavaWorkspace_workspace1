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
 
public class Amazonproductscrapernew {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "AmazonProduct Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");

int row, i=1;
private String  Imgurl,ProductDescrption,Asin,Price,TotalReview,ReviewScore,Bulletpoints;

/*File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
FirefoxProfile firefoxProfile = new FirefoxProfile();
FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);*/
WebDriver driver=new FirefoxDriver();
              
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
                rowhead.createCell((short) 5).setCellValue("TotalReview");
                rowhead.createCell((short) 6).setCellValue("ReviewScore");
                //rowhead.createCell((short) 7).setCellValue("Bulletpoints");
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(ProductDescrption);
                row.createCell((short)2).setCellValue(Imgurl);
                row.createCell((short)3).setCellValue(Asin);
                row.createCell((short)4).setCellValue(Price);
                row.createCell((short)5).setCellValue(TotalReview);
                row.createCell((short)6).setCellValue(ReviewScore);
                //row.createCell((short)7).setCellValue(Bulletpoints);                
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
  
  else {ShopByDept.click();}
  Thread.sleep(2000);
  
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
  
  Thread.sleep(2000);
  
  /********* Get the Product**********/ 
  
  String totalpage = driver.findElement(By.xpath("//div[@id='pagn']/*[last()-2]")).getText();
  
  int pgn = Integer.parseInt(totalpage);
  System.out.println(pgn);
  for(int t=0;t<pgn;t++)
  {	  
  
  List<WebElement> Prop=driver.findElements(By.xpath("//*[contains(@id,'result_')]"));
  System.out.println(Prop.size());
  for(int s=0;s<Prop.size();s++)
 //for (WebElement Prop:Property1)
  {
  //List<WebElement> Property=driver.findElements(By.xpath("//div[contains(@id,'result_')]"));	 
  ProductDescrption = Prop.get(s).findElement(By.tagName("h3")).findElement(By.tagName("a")).getText();
  System.out.println(ProductDescrption);
	 
  Imgurl = Prop.get(s).findElement(By.tagName("h3")).findElement(By.tagName("a")).getAttribute("href");
  System.out.println(Imgurl);
  
  Asin = Prop.get(s).getAttribute("name");
  System.out.println(Asin);
  
  Price = Prop.get(s).findElement(By.tagName("ul")).findElement(By.tagName("span")).getText();
  System.out.println(Price);
	  
  TotalReview = Prop.get(s).findElement(By.className("rvwCnt")).findElement(By.tagName("a")).getText();
  System.out.println(TotalReview);
		  
  ReviewScore = Prop.get(s).findElement(By.xpath("//a[contains(@href,'customerReviews')]")).getAttribute("alt");
  System.out.println(ReviewScore);
 
  resultoutput_pass();
  FileOutputStream fileOut = new FileOutputStream(filename);
  hwb.write(fileOut);
  Thread.sleep(2000);
   }
  WebElement NextPage=null;
   try
     {
	   //NextPage = driver.findElement(By.xpath("//div[@id='pagn']/*[last()-1]"));
	   NextPage = driver.findElement(By.id("pagnNextString"));
	   
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





         
                          
 
