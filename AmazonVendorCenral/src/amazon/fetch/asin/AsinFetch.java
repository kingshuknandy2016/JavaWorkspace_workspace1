package amazon.fetch.asin;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class AsinFetch {
	
	public static void main(String[] args) throws Exception {
		AsinFetch as=new AsinFetch();
		as.AuroraSmokeTest();
	}

	private String baseUrl;
	String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	                                                .getInstance().getTime());
	String filename = "C:\\Users\\Kingshuk Nandy\\workspace\\AmazonVendorCenral\\Result\\"
	                                                + "AmazonProduct Details " + timeStamp + " Summary" + ".xls";
	HSSFWorkbook hwb = new HSSFWorkbook();
	HSSFSheet sheet = hwb.createSheet("Summary");

	int row, i=1;
	private String  ProductDescrption,Asin;

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
	                rowhead.createCell((short) 2).setCellValue("ASIN");
	  
	                }


	 public void resultoutput_pass() throws Exception 
	                {
	                HSSFRow row = sheet.createRow(i);
	                row.createCell((short)0).setCellValue(i);
	                row.createCell((short)1).setCellValue(ProductDescrption);
	                row.createCell((short)2).setCellValue(Asin);
	             
	                //row.createCell((short)7).setCellValue(Bulletpoints);                
	                i++;
	                 }
	/*#########################################
	Launch Application
	##################################################*/
	public void applicationLaunch(WebDriver driver) throws Exception {

		baseUrl ="http://www.amazon.com/s/ref=sr_pg_101?rh=n%3A5174%2Cn%3A!301668%2Cn%3A42&page=101&ie=UTF8&qid=1456970753&spIA=B000003JVK,B0002JELRW,B00004U0Q3,B000002C4H,B00004ZEJE,B000054A5B,B000008F1G,B000000OEC,B00003WGNO,B0000029G9,B0000634HV,B00000K3W0,B000V7JD1E,B000002BQD,B00006I09A&lo=popular";
	                driver.get(baseUrl);
	                driver.manage().window().maximize();
	                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
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
	  
	 		  
		for (int q=100;q<400;q++){  
		  
	  List<WebElement> maincategory=driver.findElements(By.xpath("//div[@id='atfResults']//li[contains(@id,'result_')]"));
	  System.out.println(maincategory.size());
	  for(WebElement prod:maincategory)
	  {
		 
		Asin = prod.getAttribute("data-asin");
		System.out.println(Asin);
		
		ProductDescrption = prod.findElement(By.className("s-item-container")).findElement(By.tagName("h2")).getText();

		
		System.out.println(ProductDescrption);
	  
	 
	  resultoutput_pass();
	  FileOutputStream fileOut = new FileOutputStream(filename);
	  hwb.write(fileOut);
	  //Thread.sleep(400);
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
		  Thread.sleep(4500);
		  } 
		  else{System.out.println("last page reached");; 
		  }
	  }
	  
	  
	  }
	  catch (Exception e)
	  {
	  System.out.println("error "+e);
	  }      
	}

}
