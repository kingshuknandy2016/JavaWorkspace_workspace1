package com.amazon.uk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xpath.operations.String;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Amazonbook {
 


 private String baseUrl;
 String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
                                                 .getInstance().getTime());
 String timeStamp1 = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
         .getInstance().getTime());
 String filename111 = "D:\\AmazonUKBook\\AmazonProductDetails1"+timeStamp+".xls"; 
 String filename222 = "D:\\AmazonUKBook\\AmazonProductDetails2"+timeStamp1+".xls";
 HSSFWorkbook hwb1 = new HSSFWorkbook();
 HSSFSheet sheet1 = hwb1.createSheet("Summary1");
 
 HSSFWorkbook hwb2 = new HSSFWorkbook();
 HSSFSheet sheet2 = hwb2.createSheet("Summary2");

 int row, i=1;
  static int t;
  static String href;
 private String  ProductDescrption,Asin;

 /*File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
 FirefoxProfile firefoxProfile = new FirefoxProfile();
 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);*/
 
 @Test           
 public void AuroraSmokeTest1() throws Exception {
                 ExcelColumnName1();
                 applicationLaunch();
               
 }
 
 
@Test           
 public void AuroraSmokeTest2() throws Exception {
                 ExcelColumnName2();
                 applicationLaunch1();
               
 }

 private void ExcelColumnName1() throws Exception 
                 {         
                 HSSFRow rowhead = sheet1.createRow(0);
                 rowhead.createCell(0).setCellValue("Sr. No");
                 rowhead.createCell(1).setCellValue("ProductDescrption");
                 rowhead.createCell(2).setCellValue("ISBN");
   
                 }
 
 private void ExcelColumnName2() throws Exception 
 {         
 HSSFRow rowhead = sheet2.createRow(0);
 rowhead.createCell(0).setCellValue("Sr. No");
 rowhead.createCell(1).setCellValue("ProductDescrption");
 rowhead.createCell(2).setCellValue("ISBN");

 }


  public void resultoutput_pass1() throws Exception 
                 {
                 HSSFRow row = sheet1.createRow(i);
                 row.createCell(0).setCellValue(i);
                 row.createCell(1).setCellValue(ProductDescrption);
                 row.createCell(2).setCellValue(Asin);                              
                 i++;
                  }
  
  
  public void resultoutput_pass2() throws Exception 
  {
  HSSFRow row = sheet2.createRow(i);
  row.createCell(0).setCellValue(i);
  row.createCell(1).setCellValue(ProductDescrption);
  row.createCell(2).setCellValue(Asin);               
  i++;
   } 
  
 
 public void applicationLaunch() throws Exception {
                 WebDriver driver=new FirefoxDriver();
                 baseUrl = "http://www.amazon.co.uk/gp/search/ref=sr_nr_n_2?fst=as%3Aoff&rh=n%3A266239%2Cp_n_publication_date%3A182242031%2Cp_n_binding_browse-bin%3A492564011%2Cn%3A%211025612%2Cn%3A67&bbn=1025612&sort=price-asc-rank&ie=UTF8&qid=1457892237&rnid=1025612&lo=stripbooks";
                 driver.get(baseUrl);
                 driver.manage().window().maximize();
                 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);   
                
                 List<String> hrefs = new ArrayList<String>();
                 List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
                 for ( WebElement anchor : anchors ) {
                     hrefs.add(anchor.getAttribute("href"));
                     
                      t =hrefs.size();
                 }
                 for ( int b=0;b<t;b++ ) {
                  String url=hrefs.get(b);
                  driver.get(url);
   loop20:             
   for(int p=2;p<20;p++){              
   try {
   List<WebElement> maincategory=driver.findElements(By.xpath("//a[contains(@class,'s-access-detail-page')]"));
   System.out.println(maincategory.size());
   for(WebElement prod:maincategory)
   {
  String ProdUrl = prod.getAttribute("href");
  System.out.println(ProdUrl);
  String[] parts = ProdUrl.split("/");
  System.out.println(parts[5]);
  System.out.println(parts[3]);
  ProductDescrption = parts[3];
  Asin = parts[5];  
     resultoutput_pass1();
     FileOutputStream fileOut = new FileOutputStream(filename111);
     hwb1.write(fileOut);
     fileOut.close();
  }  
     }
       catch ( StaleElementReferenceException e) {
       WebElement NextPage=null;
       try
       {
     NextPage = driver.findElement(By.id("pagnNextString"));
    }
    catch (Exception e1)
    {
    System.out.println("error "+e1);
    }
    if (NextPage != null)
    {
    NextPage.click();
    Thread.sleep(5000);
    } 
    else{System.out.println("last page reached");
    }
   }
   
   WebElement NextPage=null;
      try
      {
    NextPage = driver.findElement(By.id("pagnNextString"));
   }
   catch (Exception e1)
   {
   System.out.println("error "+e1);
   driver.navigate().refresh();
   }
   if (NextPage != null)
   {
   NextPage.click();
   Thread.sleep(5000);
   } 
   else{System.out.println("last page reached");
   break loop20;
   
   }
  }
}
}
                 
 public void applicationLaunch1() throws Exception {
     WebDriver driver1=new FirefoxDriver();
     baseUrl = "http://www.amazon.co.uk/s/ref=sr_nr_n_7?fst=as%3Aoff&rh=n%3A266239%2Cp_n_publication_date%3A182242031%2Cp_n_binding_browse-bin%3A492564011%2Cn%3A%211025612%2Cn%3A71&bbn=1025612&sort=featured-rank&ie=UTF8&qid=1457892114&rnid=1025612&lo=stripbooks";
     driver1.get(baseUrl);
     driver1.manage().window().maximize();
     driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);    
     List<String> hrefs = new ArrayList<String>();
     List<WebElement> anchors = driver1.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
     for ( WebElement anchor : anchors ) {
         hrefs.add(anchor.getAttribute("href"));
         
          t =hrefs.size();
     }
     for ( int b=7;b<t;b++ ) {
      String url=hrefs.get(b);
      driver1.get(url);
loop20:             
for(int p=0;p<20;p++){              
try {
List<WebElement> maincategory=driver1.findElements(By.xpath("//a[contains(@class,'s-access-detail-page')]"));
System.out.println(maincategory.size());
for(WebElement prod:maincategory)
{
String ProdUrl = prod.getAttribute("href");
System.out.println(ProdUrl);
String[] parts = ProdUrl.split("/");
System.out.println(parts[5]);
System.out.println(parts[3]);
ProductDescrption = parts[3];
Asin = parts[5];  
resultoutput_pass2();
FileOutputStream fileOut1 = new FileOutputStream(filename222);
hwb2.write(fileOut1);
fileOut1.close();
}  
}
catch ( StaleElementReferenceException e) {
WebElement NextPage=null;
try
{
NextPage = driver1.findElement(By.id("pagnNextString"));
}
catch (Exception e1)
{
System.out.println("error "+e1);
}
if (NextPage != null)
{
NextPage.click();
Thread.sleep(5000);
} 
else{System.out.println("last page reached");
}
}

WebElement NextPage=null;
try
{
NextPage = driver1.findElement(By.id("pagnNextString"));
}
catch (Exception e1)
{
System.out.println("error "+e1);
driver1.navigate().refresh();
}
if (NextPage != null)
{
NextPage.click();
Thread.sleep(5000);
} 
else{System.out.println("last page reached");
break loop20;

}
}
}               
                
                 
                 
}  
}