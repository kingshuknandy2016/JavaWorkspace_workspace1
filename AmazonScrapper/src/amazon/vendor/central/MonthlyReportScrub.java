package amazon.vendor.central;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;



public class MonthlyReportScrub {

static String baseUrl;
static int p,row;
public static String downloadPath = "E:\\Selenium_Practise\\Aurora_Automation\\Result";         

public static void main(String [] args) throws InterruptedException, BiffException, IOException, AWTException {
 
  System.setProperty("webdriver.chrome.driver","C:/chromedriver.exe");
  WebDriver   driver = new ChromeDriver();
	
  baseUrl = "https://solarhealth.prognocis.com";
  driver.get(baseUrl + "/");
  driver.manage().window().maximize();
                 
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  try {
	
	 WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
	 username.sendKeys("smadabushi");
	 
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("runnn");
	  driver.findElement(By.xpath("//input[@id='login']")).click();
	
} catch (Exception e) {
	System.out.println("Error::"+e);
}

  
 /* driver.findElement(By.xpath("//input[@id='username']")).sendKeys("siddharth869a@gmail.com");
  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("tamluk12");
  driver.findElement(By.xpath("//button[@id='login-button-container']")).click();
  Thread.sleep(2000);
  driver.findElement(By.id("reports_text")).click();
  
  
  
  driver.findElement(By.id("ara-basic-reports-redirect_text")).click();
  driver.findElement(By.id("//span[text()='Sales and Inventory Dashboard']")).click();
  Thread.sleep(2000);
  driver.findElement(By.xpath("//a[contains(text(),'Last 12 Months')]")).click();
  Thread.sleep(2000);
  
  driver.switchTo().frame(driver.findElement(By.id("proxyFrame")));
  
  List<WebElement>  getallLink =  driver.findElements(By.tagName("a"));
  
  for (WebElement link :getallLink){
	  
	  System.out.println(link.getText());
  }
  
  WebElement download = driver.findElement(By.xpath("//div[@result='finished']//td[@class='ResultLinksCell']/a"));
  String texxt = download.getText();
  System.out.println(texxt);
  driver.findElement(By.xpath("//a[contains(text(),'Download to Excel 2000')]")).click();
   
}//end  of function



public static FirefoxProfile FirefoxDriverProfile() throws Exception {
	FirefoxProfile profile = new FirefoxProfile();
	profile.setPreference("browser.download.folderList", 2);
	profile.setPreference("browser.download.manager.showWhenStarting", false);
	profile.setPreference("browser.download.dir", downloadPath);
	profile.setPreference("browser.helperApps.neverAsk.openFile",
			"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
	profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
	profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
	profile.setPreference("browser.download.manager.focusWhenStarting", false);
	profile.setPreference("browser.download.manager.useWindow", false);
	profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	profile.setPreference("browser.download.manager.closeWhenDone", false);
	return profile;*/
}

}//end f class



         
                          
 
