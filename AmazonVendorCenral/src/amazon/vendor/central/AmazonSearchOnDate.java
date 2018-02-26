package amazon.vendor.central;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchOnDate {

static String baseUrl;
static int p,row;
          

public static void main(String [] args) throws InterruptedException, BiffException, IOException {
/*   System.setProperty("webdriver.chrome.driver", "D:/OdeskSelenium/chromedriver_win32/chromedriver.exe");
   WebDriver driver = new ChromeDriver();*/
	
    File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile();
	FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	
  baseUrl = "https://vendorcentral.amazon.com";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
                 
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  
  driver.findElement(By.xpath("//input[@id='username']")).sendKeys("siddharth869a@gmail.com");
  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("koustav5%");
  driver.findElement(By.xpath("//button[@id='login-button-container']")).click();
  driver.findElement(By.xpath("//div/p[text()='Payments']")).click();
  driver.findElement(By.xpath("//div[@id='invoice-management-santana_text']")).click();
  driver.findElement(By.xpath("//div[@id='invoiceNumberPODiv']//button")).click();
  Thread.sleep(2000);
  
 /* WebElement dropdown= driver.findElement(By.id("searchKey"));
    dropdown.click();
    dropdown.findElement(By.xpath("//option[2]")).click();
  */
  WebElement element=driver.findElement(By.id("searchKey"));
  Select se=new Select(element);
  se.selectByValue("shortageCriteria");
  
  Thread.sleep(2000);
    
  FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\ReferenceUPCGTINsASINs.xls");
  Workbook w = Workbook.getWorkbook(fi);
  Sheet s = w.getSheet(1);
  for(int row=1; row <s.getRows();row++)  
  {
	  try
	  {	  
	  String StartDate = s.getCell(1, row).getContents();
	  System.out.println(StartDate);
	  driver.findElement(By.id("startDate")).clear();
	  driver.findElement(By.id("startDate")).sendKeys(StartDate);
	  	  
	  String EndDate = s.getCell(2, row).getContents();
	  System.out.println(EndDate);
	  driver.findElement(By.id("endDate")).clear();
	  driver.findElement(By.id("endDate")).sendKeys(EndDate);	  
	   
      driver.findElement(By.xpath("//span[@id='invoiceSearchButton']")).click();
            
      Thread.sleep(4000);
      
      String strScore= driver.findElement(By.xpath("//div[contains(@id,'vssGridPagination')]//div/span[contains(@class,'slick-pager-status')]")).getText();
      String intValue = strScore.replaceAll("[^0-9]", "");
      System.out.println(intValue);
      String s2= "1";
      String ss= intValue.replaceAll(s2,"");
      System.out.println(ss);
      int score = Integer.parseInt(ss);
      System.out.println(score);
          
      
      Thread.sleep(2000);
  
  int contr =1;
  
  System.out.println(score);
  
  for (int p=0; p<score;p++){
  
  List<WebElement> elementactionbutton = driver.findElements(By.xpath("//span[@class='link_popover sprite-button']"));
  for (int i=0; i< elementactionbutton.size();i++){
	  
	  System.out.println(elementactionbutton.size());
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span[@class='link_popover sprite-button']")));
	  
	  elementactionbutton.get(i).click();	  
	  
	  WebDriverWait wait1 = new WebDriverWait(driver, 15);
	  wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View invoice details']")));
	  
	  WebElement DisputeShortageLink = null;
	  try
	  {
	  DisputeShortageLink = driver.findElementByXPath("//a[text()='Dispute shortage']");
	  }
	  catch(Exception e)
	  {
	  }
	  if(DisputeShortageLink != null){
      driver.findElement(By.xpath("//a[text()='Dispute shortage']")).click();
      
      ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
      
      WebDriver ndriver= driver.switchTo().window(tabs2.get(contr));
      contr++;
      
      Thread.sleep(2000);
      ndriver.findElement(By.xpath("//a[@class='disputeByASINLink']")).click();
      
      WebDriverWait wait2 = new WebDriverWait(ndriver, 60);
      wait2.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div/img[@class='grid-overlay grid-overlay-spinner']"))));
      
      //System.out.println(driver.findElement(By.xpath("//div[contains(@id,'vssDisplayGrid')]")).findElement(By.className("grid-canvas")).getAttribute("innerHTML"));
      List<WebElement> ASINcode = ndriver.findElement(By.xpath("//div[contains(@id,'vssDisplayGrid')]//div[@class='grid-canvas']")).findElements(By.xpath("div"));
      System.out.println(ASINcode.size());
      //int cntr=0;
      for(WebElement divel:ASINcode)
      {
    	 String ASINinWeb= divel.findElements(By.xpath("div")).get(3).getText();
    	
    	 System.out.println(ASINinWeb);	
         System.out.println("**********************************");
         
         ReadExcel(ASINinWeb, ndriver,divel);
         ////cntr++;
      }   
      ndriver.findElements(By.xpath("//input[@type='checkbox']")).get(0).click();
      Thread.sleep(2000);
      ndriver.findElement(By.id("agree")).click();
      //ndriver.findElement(By.id("cancel"));
      
     // ndriver.close(); 
      driver.switchTo().window(tabs2.get(0));
	  }
	  else 
	  {
	  System.out.println("DisputeShortage Not there");	  
	  }
	  }  //end second for loop
      driver.findElement(By.xpath("//span[contains(@class,'ui-icon ui-icon-seek-next')]")).click();
      }//end first for loop
      driver.close();
	  }catch(Exception e){System.out.println("Error" + e);}
   }
  }
private static void ReadExcel(String ASINinWeb,WebDriver ndriver,WebElement divel) throws BiffException, IOException, InterruptedException{
	Boolean blch=false;
	String GTINinExcel="";
	FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\ReferenceUPCGTINsASINs.xls");
    Workbook w = Workbook.getWorkbook(fi);
    Sheet s = w.getSheet(0);
    for(row=1; row <s.getRows();row++){ 
    	
   System.out.println(s.getCell(2, row).getContents()); 
	   if(s.getCell(2, row).getContents().equalsIgnoreCase(ASINinWeb))
	   {
	     GTINinExcel = s.getCell(4, row).getContents();
	     blch=true;
	   }
	   if(blch) { break;  }
	   
	   
  }// End of for loop
    if(blch) { 
    
    
    int shipindexref=0;
    List<WebElement> ColumnName = ndriver.findElement(By.xpath("//div[contains(@id,'vssDisplayGrid')]//div[@class='slick-header-columns ui-sortable']")).findElements(By.xpath("div"));
    System.out.println(ColumnName.size());
    try{
    for(int p=0; p< ColumnName.size();p++)
    {
    	
  	 String ColumnName1= ColumnName.get(p).getText();
  	 if(ColumnName1.equalsIgnoreCase("Shipped product"))
  	 {
  		shipindexref=p;
  	 }
  	 
  	 System.out.println(ColumnName1);
     System.out.println("**********************************");
     
    }    
    
    divel.findElements(By.xpath("div")).get(shipindexref).click();
    divel.findElements(By.xpath("div")).get(shipindexref).findElement(By.tagName("input")).sendKeys(GTINinExcel);
    System.out.println(divel.findElements(By.xpath("div")).get(shipindexref).findElement(By.tagName("input")).getAttribute("value"));
    Thread.sleep(2000);
    System.out.println(divel.findElements(By.xpath("div")).get(0).getAttribute("outerHTML"));
    System.out.println(divel.findElements(By.xpath("div")).get(0).findElement(By.tagName("input")).getAttribute("outerHTML"));
    
    
    System.out.println(ndriver.findElement(By.xpath("//div/h1")).getText());
    ndriver.findElement(By.xpath("//div/h1[text()='Dispute by ASINs']")).click();
    System.out.println(ndriver.findElement(By.xpath("//div/h1")).getText());
    Thread.sleep(2000);
    //divel.findElements(By.xpath("div")).get(0).findElement(By.tagName("input")).click();
    
   /// System.out.println(divel.findElements(By.xpath("div")).get(0).getAttribute("outerHTML"));
    
    } catch (Exception e){System.out.println("Error" + e);}
        
   }
   
}//end  of function

}//end f class



         
                          
 
