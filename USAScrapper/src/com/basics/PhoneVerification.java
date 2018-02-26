package com.basics;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoneVerification {

	public static String downloadPath = "C:\\Users\\Kingshuk Nandy\\workspace\\USAScrapper\\Results"; 

	 
	 static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	         .getInstance().getTime());
	 static String filename = "K:\\NewProject\\"
	         + "course Details " + timeStamp + " Summary" + ".xls";
	 private static HSSFWorkbook hwb = new HSSFWorkbook();
	 int row;
	 static int i=1,arr=0;
	 
	 



	 /*************************Main Method**********************************************************/
	 public static void main(String[] args)  throws Exception {
	    Scraper();
	 }

	 
	 public static void Scraper () throws Exception {  
	   // WebDriver driver=new FirefoxDriver(FirefoxDriverProfile());
	  
	/*  File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	  FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	  FirefoxProfile firefoxProfile = new FirefoxProfile();
	  FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile); */
	  
	  
	 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
	 WebDriver driver = new ChromeDriver(); 
	  
	  
	  
	 FileInputStream file = new FileInputStream("C:\\Users\\Kingshuk Nandy\\workspace\\USAScrapper\\TestData.xls");
	 HSSFWorkbook workbook = new HSSFWorkbook(file);
	 HSSFSheet sheet1 = workbook.getSheet("Sheet11"); 
	 String baseUrl = "https://salesdatalist.com";
	 driver.get(baseUrl);
	 driver.manage().window().maximize();
	 //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	// Thread.sleep(4000);
	 
	 WebDriverWait wait = new WebDriverWait(driver, 120);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
	 
	// driver.findElement(By.xpath("(//a[contains(text(),'Login')])[1]")).click();
	 
	 driver.findElement(By.id("login")).click();
	 driver.findElement(By.id("login")).sendKeys("tburns");
	 driver.findElement(By.id("pass")).click();
	 driver.findElement(By.id("pass")).sendKeys("tburns97");
	 driver.findElement(By.xpath("(//input)[3]")).click(); //driver.findElement(By.id("member-resources")).findElement(By.xpath("(//a[text()='Consumer Lists'])[1]")).click();
	 
	    for(int row=1; row <sheet1.getPhysicalNumberOfRows();row++) ///first for loop
	  {
	  try
	  {
	   
	 driver.get("http://salesdatalist.com/search/cell/");
	 driver.findElement(By.id("zipans")).click();
	 driver.findElement(By.id("zipans")).clear();  
	    String ZipCode = sheet1.getRow(row).getCell((short) 1).getStringCellValue();
	    driver.findElement(By.id("zipans")).sendKeys(ZipCode);
	    
	    driver.findElement(By.xpath("//input[@value='View Results']")).click();
	    //Thread.sleep(1000);
	    
	    //driver.findElement(By.xpath("//select[@name='file_format']")).click();
	    
	    //Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='file_format']")));
	    //dropdown.selectByIndex(1);
	   // Thread.sleep(2000);
	    //driver.findElement(By.xpath("//select//option[2]")).click();
	   
	    try {
	    	 driver.findElement(By.xpath("//input[@type='submit']")).click();
		} catch (Exception e) {
			System.out.println("Exception:Data Not found in Zip::::"+ ZipCode);
		}
	    
	    
	    String downloadFilepath = "/path/to/download";
	    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory", downloadFilepath);
	    ChromeOptions options = new ChromeOptions();
	    HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
	    options.setExperimentalOption("prefs", chromePrefs);
	    options.addArguments("--test-type");
	    DesiredCapabilities cap = DesiredCapabilities.chrome();
	    cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
	    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    cap.setCapability(ChromeOptions.CAPABILITY, options);
	    //WebDriver driver = new ChromeDriver(cap);
	 
	    
	    
	    
	  }catch(Exception e)
	   {e.printStackTrace();}
	  
	  }
	
}

}
