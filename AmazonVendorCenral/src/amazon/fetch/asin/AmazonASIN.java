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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonASIN {
	 
	/* public static void main(String[] args) throws Exception {
	  AmazonASIN as=new AmazonASIN();
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

	 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	 FirefoxProfile firefoxProfile = new FirefoxProfile();
	 FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	 
	               
	 public void AuroraSmokeTest() throws Exception {
	                 ExcelColumnName();
	                 applicationLaunch();
	               
	 }

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
	                              
	                 i++;
	                  }
	 
	 public void applicationLaunch() throws Exception {
	              WebDriver driver=new FirefoxDriver();
	                 baseUrl = "http://www.amazon.com/s/ref=sr_pg_273?fst=as%3Aoff&rh=n%3A5174%2Cp_n_binding_browse-bin%3A387645011%2Cp_n_format_browse-bin%3A1294043011&page=273&bbn=5174&ie=UTF8&qid=1457547992&lo=popular";
	                 driver.get(baseUrl);
	                 driver.manage().window().maximize();
	                 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   boolean blchk = false;    
	   for (int q=272;q<400;q++){
	   try {  
	   List<WebElement> maincategory=driver.findElements(By.xpath("//div[@id='atfResults']//li[contains(@id,'result_')]"));
	   System.out.println(maincategory.size());
	   if (maincategory.size()==60){
	    blchk =true;
	 breakLoop:   
	   for(WebElement prod:maincategory)
	   {   
	  Asin = prod.getAttribute("data-asin");
	  System.out.println(Asin);  
	  ProductDescrption = prod.findElement(By.className("s-item-container")).findElement(By.tagName("h2")).getText();
	  System.out.println(ProductDescrption);
	     resultoutput_pass();
	     FileOutputStream fileOut = new FileOutputStream(filename);
	     hwb.write(fileOut);
	   }
	   
	   
	   
	   //WebElement NextPage1=null;
	       try
	       {
	        WebElement NextPage1 = driver.findElement(By.id("pagnNextString"));
	        NextPage1.click();
	     Thread.sleep(4000);
	     
	    }
	    catch (Exception e1)
	    {
	    System.out.println("error "+e1);
	    }
	    
	    }
	   
	   else{
		   try
	       {
	        WebElement NextPage1 = driver.findElement(By.id("pagnNextString"));
	        NextPage1.click();
	     Thread.sleep(4000);
	     
	    }
	    catch (Exception e1)
	    {
	    System.out.println("error "+e1);
	    }
		   
	   }
	   
	   
	  
	   }
	    catch ( StaleElementReferenceException e ) {   
	    blchk =false;      
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
	    Thread.sleep(4000);
	    } 
	    else{System.out.println("last page reached");
	    }
	   }
	   }
	}*/
	
	
	
	
	
	//---------------------------------------------------------------
	public static void main(String[] args) throws Exception {
		AmazonASIN as=new AmazonASIN();
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
		 
		               
		 public void AuroraSmokeTest() throws Exception {
		                 ExcelColumnName();
		                 applicationLaunch();
		               
		 }

		 private void ExcelColumnName() throws Exception 
		                 {         
		                 HSSFRow rowhead = sheet.createRow(0);
		                 rowhead.createCell((short) 0).setCellValue("Sr. No");
		                 rowhead.createCell((short) 1).setCellValue("ProductDescrption");
		                 rowhead.createCell((short) 2).setCellValue("ISBN");   
		                 }


		  public void resultoutput_pass() throws Exception 
		                 {
		                 HSSFRow row = sheet.createRow(i);
		                 row.createCell((short)0).setCellValue(i);
		                 row.createCell((short)1).setCellValue(ProductDescrption);
		                 row.createCell((short)2).setCellValue(Asin);                              
		                 i++;
		                  }
		 
		 public void applicationLaunch() throws Exception {
		              WebDriver driver=new FirefoxDriver();
		                 baseUrl = "http://www.amazon.com/s/ref=sr_pg_99?fst=as%3Aoff&rh=n%3A5174%2Cp_n_binding_browse-bin%3A387645011%2Cp_n_condition-type%3A1294427011%2Cp_n_format_browse-bin%3A12572623011&page=99&bbn=5174&ie=UTF8&qid=1457630977&lo=popular&ajr=1";
		                 driver.get(baseUrl);
		                 driver.manage().window().maximize();
		                 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   boolean blchk = false;
		   for (int q=0;<171;q++){
		   try {  
		    List<WebElement> maincategory=driver.findElements(By.xpath("//a[contains(@class,'s-access-detail-page')]"));
		    System.out.println(maincategory.size());
		    if (maincategory.size()==60){
		     
		  breakLoop:   
		    for(WebElement prod:maincategory)
		    {
		   String ProdUrl = prod.getAttribute("href");
		   System.out.println(ProdUrl);
		   String[] parts = ProdUrl.split("/");
		   System.out.println(parts[5]);
		   System.out.println(parts[3]);
		   ProductDescrption = parts[3];
		   Asin = parts[5];
		   
		      resultoutput_pass();
		      FileOutputStream fileOut = new FileOutputStream(filename);
		      hwb.write(fileOut);
		   }
		    
		       try
		       {
		        WebElement NextPage1 = driver.findElement(By.id("pagnNextString"));
		        NextPage1.click();
		     Thread.sleep(4000);
		     
		    }
		    catch (Exception e1)
		    {
		    System.out.println("error "+e1);
		    }
		    
		    }else{
		     try
		        {
		         WebElement NextPage1 = driver.findElement(By.id("pagnNextString"));
		         NextPage1.click();
		      Thread.sleep(4000);
		      
		     }
		     catch (Exception e1)
		     {
		     System.out.println("error "+e1);
		     }

		     
		    }
		   
		   
		  
		   }
		    catch ( StaleElementReferenceException e ) {   
		    blchk =false;      
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
		    Thread.sleep(4000);
		    } 
		    else{System.out.println("last page reached");; 
		    }
		   }
		   }
		}
}
