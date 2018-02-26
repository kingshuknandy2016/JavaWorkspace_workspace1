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
 
public class AmazonHomeKitchen {
private String baseUrl;
String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                .getInstance().getTime());
String filename = "E:\\Selenium_Practise\\Aurora_Automation\\Result\\"
                                                + "AmazonProduct Details " + timeStamp + " Summary" + ".xls";
HSSFWorkbook hwb = new HSSFWorkbook();
HSSFSheet sheet = hwb.createSheet("Summary");
int row, i=1;


private String  Producttitle,Imgurl,Price,Soldby,Availability,Bulletpoints,ProductDescrption,Asin,ProductSpecification;
private String Otherrl1,Otherrl2,Otherrl3,Otherrl4,Otherrl5,Otherrl6;

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
                rowhead.createCell((short) 1).setCellValue("Product Title");
                rowhead.createCell((short) 2).setCellValue("Main Image Url");
                rowhead.createCell((short) 3).setCellValue("Price");
                rowhead.createCell((short) 4).setCellValue("Ship and Sold by");
                rowhead.createCell((short) 5).setCellValue("Availability");
                rowhead.createCell((short) 6).setCellValue("Bulletpoints");
                rowhead.createCell((short) 7).setCellValue("ProductDescrption");
                rowhead.createCell((short) 8).setCellValue("Asin");
                rowhead.createCell((short) 9).setCellValue("Product Specification");
                
                rowhead.createCell((short) 10).setCellValue("OtherImgurl1");
                rowhead.createCell((short) 11).setCellValue("OtherImgurl2");
                rowhead.createCell((short) 12).setCellValue("OtherImgurl3");
                rowhead.createCell((short) 13).setCellValue("OtherImgurl4");
                rowhead.createCell((short) 14).setCellValue("OtherImgurl5");
                rowhead.createCell((short) 15).setCellValue("OtherImgurl6");
                
                }


 public void resultoutput_pass() throws Exception 
                {
                HSSFRow row = sheet.createRow(i);
                row.createCell((short)0).setCellValue(i);
                row.createCell((short)1).setCellValue(Producttitle);
                row.createCell((short)2).setCellValue(Imgurl);
                row.createCell((short)3).setCellValue(Price);
                row.createCell((short)4).setCellValue(Soldby);
                row.createCell((short)5).setCellValue(Availability);
                row.createCell((short)6).setCellValue(Bulletpoints);
                row.createCell((short)7).setCellValue(ProductDescrption);
                row.createCell((short)8).setCellValue(Asin);
                row.createCell((short)9).setCellValue(ProductSpecification);                       
                row.createCell((short)10).setCellValue(Otherrl1);
                row.createCell((short)11).setCellValue(Otherrl2);
                row.createCell((short)12).setCellValue(Otherrl3);
                row.createCell((short)13).setCellValue(Otherrl4);
                row.createCell((short)14).setCellValue(Otherrl5);
                row.createCell((short)15).setCellValue(Otherrl6);
                i++;
                 }
 
/*#########################################
Launch Application
##################################################*/
public void applicationLaunch(WebDriver driver) throws Exception {
        
                baseUrl = "http://www.amazon.com";
                driver.get(baseUrl + "/");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
  //Thread.sleep(2000);
  //ShopByDept.click();
  
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
  
  List<WebElement> Property1=driver.findElements(By.xpath("//div[contains(@id,'result_')]"));
 
  
  for(int s=0;s<Property1.size();s++)
 //for (WebElement Prop:Property1)
  {
  List<WebElement> Property=driver.findElements(By.xpath("//div[contains(@id,'result_')]/h3/a"));
  
  Property.get(s).click();
  Thread.sleep(2000);
  
  Producttitle = driver.findElement(By.id("productTitle")).getText();
  System.out.println(Producttitle);
 
  /******************************Image*****************************/
  
   Imgurl=  driver.findElement(By.xpath("//li[2]//span[contains(@id,'a-autoid')]/img")).getAttribute("src");
   System.out.println(Imgurl);
   
   WebElement Imgurl1 = null;
   try
   {
	Imgurl1=  driver.findElement(By.xpath("//li[3]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl1 != null)
   {
	   Otherrl1=Imgurl1.getAttribute("src");
 	  System.out.println(Otherrl1);
   }
   
   
   WebElement Imgurl2 = null;
   try
   {
	   Imgurl2=  driver.findElement(By.xpath("//li[4]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl2 != null)
   {
	   Otherrl2=Imgurl2.getAttribute("src");
 	  System.out.println(Otherrl2);
   }
   
 
   WebElement Imgurl3 = null;
   try
   {
	   Imgurl3=  driver.findElement(By.xpath("//li[5]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl3 != null)
   {
	   Otherrl3=Imgurl3.getAttribute("src");
 	  System.out.println(Otherrl3);
   }  
   
   
   WebElement Imgurl4 = null;
   try
   {
	   Imgurl4=  driver.findElement(By.xpath("//li[6]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl4 != null)
   {
	   Otherrl4=Imgurl4.getAttribute("src");
 	  System.out.println(Otherrl4);
   }  
   
   
   WebElement Imgurl5 = null;
   try
   {
	   Imgurl5=  driver.findElement(By.xpath("//li[7]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl5 != null)
   {
	   Otherrl5=Imgurl5.getAttribute("src");
 	  System.out.println(Otherrl5);
   }  
   
   
   WebElement Imgurl6 = null;
   try
   {
	   Imgurl6=  driver.findElement(By.xpath("//li[7]//span[contains(@id,'a-autoid')]/img")); 	 
   }
   catch (Exception e)
   {
   System.out.println("error "+e);
   }
   if (Imgurl6 != null)
   {
	   Otherrl6=Imgurl6.getAttribute("src");
 	  System.out.println(Otherrl6);
   }  
   
   /******************************Image*****************************/
  
  
  WebElement price1 = null;
  WebElement SalePrice = null;

  try
  {
	  price1=  driver.findElement(By.id("priceblock_ourprice"));
	  SalePrice=  driver.findElement(By.id("priceblock_saleprice"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (price1 != null)
  {
	  Price=price1.getText();
	  System.out.println(Price);
  }
  
  else if (SalePrice != null)
  {
	  Price=SalePrice.getText();
	  System.out.println(Price);
  }   
  

  Soldby = driver.findElement(By.id("merchant-info")).getText();
  System.out.println(Soldby);
		  
  Availability = driver.findElement(By.id("availability")).getText();
  System.out.println(Availability);
		  
  Bulletpoints = driver.findElement(By.id("feature-bullets")).getText();
  System.out.println(Bulletpoints);
  
  
  WebElement ProductDescn = null;
  try
  {
	  ProductDescn=  driver.findElement(By.id("productDescription")); 	 
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (ProductDescn != null)
  {
	  ProductDescrption=ProductDescn.getText();
	  System.out.println(ProductDescrption);
  }
  
  
  WebElement Asin1 = null;
  WebElement Asin2 = null;

  try
  {
	Asin1=  driver.findElement(By.xpath("//div[contains(@class,'column col2')]//table/tbody/tr[1]/td[last()]"));
	Asin2=  driver.findElement(By.xpath("//div[@id='detail-bullets']//li[6]"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (Asin1 != null)
  {
	  Asin=Asin1.getText();
	  System.out.println(Asin);
  }
  
  else if (Asin2 != null)
  {
	  Asin=Asin2.getText();
	  System.out.println(Asin);
  }
    
  
  WebElement ProductSpec = null;
  WebElement ProductSpec2 = null;
  try
  {
	  ProductSpec=  driver.findElement(By.xpath("//div[contains(@class,'column col1')]//table/tbody"));
	  ProductSpec2 = driver.findElement(By.id("detail-bullets")).findElement(By.tagName("table")).findElement(By.tagName("tbody"));
  }
  catch (Exception e)
  {
  System.out.println("error "+e);
  }
  if (ProductSpec != null)
  {
	  ProductSpecification=ProductSpec.getText();
	  System.out.println(ProductSpecification);
  } else if  (ProductSpec2 != null)
	  
  {
	  ProductSpecification=ProductSpec2.getText();
	  System.out.println(ProductSpecification); 
  }
  
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
	  
	  driver.navigate().back();
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





         
                          
 
