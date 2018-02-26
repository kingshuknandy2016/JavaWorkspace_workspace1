package amazon.vendor.central;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class downloadingfile {
 WebDriver driver;
// private String folderpath;
 
 @Test
 public void fileDownload() {
  //Create object of FirefoxProfile in built class to access Its properties.
	 
	 
	  try
	  {
	  FileInputStream fi = new FileInputStream("E:\\Selenium_Practise\\Aurora_Automation\\Data\\AmazonMonthlyReport.xls");
	  Workbook w = Workbook.getWorkbook(fi);
	  Sheet s = w.getSheet(1);
	  for(int row=1; row <s.getRows();row++)  
	  {
		  	  
		  String userName = s.getCell(1, row).getContents();
		  System.out.println(userName);
			  	  
		  String Password = s.getCell(2, row).getContents();
		  System.out.println(Password);
		  
		  String folderpath = s.getCell(3, row).getContents();
	 
	 
		  FirefoxProfile fprofile = new FirefoxProfile(); 
	 
  //Set Location to store files after downloading.
  fprofile.setPreference("browser.download.dir", folderpath);
  fprofile.setPreference("browser.download.folderList", 2);
  //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
  fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
   // "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"//MIME types Of MS Excel File.
   // + "application/pdf;" //MIME types Of PDF File.
    //+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" //MIME types Of MS doc File.
     "text/csv;");
    //+ "text/plain;");//MIME types Of text File.
   /// + "text/csv"); //MIME types Of CSV File.
  fprofile.setPreference( "browser.download.manager.showWhenStarting", false );
  fprofile.setPreference( "pdfjs.disabled", true );
  //Pass fprofile parameter In webdriver to use preferences to download file.
  driver = new FirefoxDriver(fprofile);
 
	  String baseUrl = "https://vendorcentral.amazon.com/st/vendor/members/home";
	  driver.get(baseUrl + "/");
	  driver.manage().window().maximize();
	  
	  	                 
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
	  driver.findElement(By.xpath("//button[@id='login-button-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("reports_text")).click();
	  driver.findElement(By.id("ara-basic-reports-redirect_text")).click();
	  
	  driver.findElement(By.xpath("//span[text()='Sales and Inventory Dashboard']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[text()='Sales and Inventory Product Details']")).click();
	  
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//span[contains(text(),'Week-to-date')]")).click();
	    
	  
	  driver.findElement(By.xpath("//a[contains(text(),'Last reported month')]")).click();
	  
	  driver.findElement(By.id("vxa-export-link")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("logout_topNav")).click();
	  Thread.sleep(2000);
	  driver.quit();
	  }
	  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
 }
 
 @AfterTest
 public void CloseBrowser() {  
  driver.quit();
 }
}