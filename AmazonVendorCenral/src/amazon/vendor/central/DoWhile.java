package amazon.vendor.central;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoWhile {

static String baseUrl;
static int p,row;
          

public static void main(String [] args) throws InterruptedException {
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
  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("joyguru5%");
  driver.findElement(By.xpath("//button[@id='login-button-container']")).click();
  driver.findElement(By.xpath("//div/p[text()='Payments']")).click();
  driver.findElement(By.xpath("//div[@id='invoice-management-santana_text']")).click();
  driver.findElement(By.xpath("//a[text()='Review/dispute shortages']")).click();
  
  List<WebElement> elementactionbutton = driver.findElements(By.xpath("//span[@class='link_popover sprite-button']"));
  for (int i=1; i<= elementactionbutton.size();i++){
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div["+i+"]/div[13]/div/span[@class='link_popover sprite-button']")));
	  driver.findElement(By.xpath("//div["+i+"]/div[13]/div/span[@class='link_popover sprite-button']")).click();
	  
	  Thread.sleep(2000);
      driver.findElement(By.xpath("//a[text()='Dispute shortage']")).click();
      
      ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
      driver.switchTo().window(tabs2.get(1));
      
      driver.findElementByXPath("//a[@class='disputeByASINLink']").click();
      
      WebDriverWait wait1 = new WebDriverWait(driver, 60);
      wait1.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div/img[@class='grid-overlay grid-overlay-spinner']"))));
      
      
      List<WebElement> ASINcode = driver.findElements(By.xpath("//div[3]/div[contains(@class,'cellContent')]"));
      System.out.println(ASINcode.size());
      
      for (p=1; p<=ASINcode.size();p++){
      
      WebElement asincode = driver.findElement(By.xpath("//div["+p+"]/div[3]/div[@class='cellContent']"));
      System.out.println(asincode);
      String ASINinWeb = asincode.getText();
      System.out.println(ASINinWeb);
      System.out.println("**********************************");
      
      try {
          FileInputStream fi = new FileInputStream("D:\\Selenium_Practise\\Aurora_Automation\\Data\\ReferenceUPCGTINsASINs.xls");
          Workbook w = Workbook.getWorkbook(fi);
          Sheet s = w.getSheet(0);
          myloop: for(row=1; row <=s.getRows();row++){      
          String ASINinExcel = s.getCell(2, row).getContents();
                
          if(ASINinWeb.equals(ASINinExcel)){
          	  
          String GTIN = s.getCell(4, row).getContents();
          System.out.println("GTIN of ASIN "+ASINinWeb +" is " +GTIN);
          String test = driver.findElement(By.xpath("//div["+p+"]/div[5]/div[@class='cellContent']")).getText();
          System.out.println(test);
          
          WebElement myInputElm1  = null;
          WebElement myInputElm2  = null;
          
          try{
          myInputElm1 = driver.findElement(By.xpath("//div["+p+"]/div[13]/div[contains(@class,'cellContent')]"));  
          Actions act = new Actions(driver);
          act.moveToElement(myInputElm1).click().build().perform();
          act.moveToElement(myInputElm1).release().build().perform();
          myInputElm2= driver.findElement(By.xpath("//div["+p+"]/div[13]/input[contains(@class,'editor-text')]"));
          
          }
          catch(Exception e){}
          if(myInputElm2!= null){
          
        	  myInputElm2.clear();
        	  myInputElm2.sendKeys("xxxx2");
        	  Actions act1 = new Actions(driver);
        	  act1.moveToElement(myInputElm2).release().build().perform();
          } else if (myInputElm2== null && myInputElm1 != null)  {
        	   
        	   myInputElm1.sendKeys("xxxx1");
          }
                    
          
          break myloop;
          
          }else {continue;}
          
          }}catch(Exception e){System.out.println("error" +e);}
      
      }
      Thread.sleep(4000);
      driver.close();
      driver.switchTo().window(tabs2.get(0));
	  }  
      driver.close();
}
}



         
                          
 
