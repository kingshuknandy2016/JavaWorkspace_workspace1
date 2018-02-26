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

public class AuthorAmazonASIN {
	
	 
	  
	  public static void main(String[] args) throws Exception {
		  AuthorAmazonASIN as=new AuthorAmazonASIN();
	        as.AuroraSmokeTest();
	}

	    private String baseUrl;
	    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	                                                    .getInstance().getTime());
	    String filename = "D:\\AmazonUKBook22\\" + "modified AmazonProduct Details " + timeStamp + " Summary" + ".xls";
	    HSSFWorkbook hwb = new HSSFWorkbook();
	    HSSFSheet sheet = hwb.createSheet("Summary");

	    int row, i=1;
	     static int t;
	     static String href;
	    private String  ProductDescrption,Asin,Author;

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
	                    rowhead.createCell((short) 3).setCellValue("Author");      
	                    }


	     public void resultoutput_pass() throws Exception 
	                    {
	                    HSSFRow row = sheet.createRow(i);
	                    row.createCell((short)0).setCellValue(i);
	                    row.createCell((short)1).setCellValue(ProductDescrption);
	                    row.createCell((short)2).setCellValue(Asin);
	                    row.createCell((short)3).setCellValue(Author);             
	                    i++;
	                     }
	    
	    public void applicationLaunch() throws Exception {
	                    //WebDriver driver=new FirefoxDriver();
	         WebDriver driver=null;
	         
	         System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	                 driver = new ChromeDriver();
	        
	                    baseUrl = "http://www.amazon.co.uk/gp/search/other/ref=lp_266239_sa_p_lbr_books_authors_?rh=n%3A266239&bbn=266239&pickerToList=lbr_books_authors_browse-bin&ie=UTF8&qid=1459965299";
	                    driver.get(baseUrl);
	                    driver.manage().window().maximize();
	                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	                    System.out.println("//////////////////");
	      
	                   
	                    List<String> hrefs = new ArrayList<String>();
	                    //xpath ouside
	                    
	                  //
	                    //List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
	                    
	                    List<WebElement> anchors = driver.findElements(By.xpath("//div[@id='refinementList']//a"));
	                    
	                    // xpath inside
	                    //List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 6px']//a"));
	                   //or
	                   // List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 14px']//a"));
	                    
	                    
	                    for ( WebElement anchor : anchors ) {
	                        //----------------new code
	                        
	                        //List<WebElement> anchors1 = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
	                       
	                        //--------------------------
	                        hrefs.add(anchor.getAttribute("href"));                        
	                         t =hrefs.size();
	                         
	                         System.out.println("authors count::"+t);
	                    }
	                    for ( int b=0;b<10;b++ ) {
	                        String url=hrefs.get(b);
	                        driver.get(url);
	                        Thread.sleep(1000);
	                        String ImgView= driver.findElement(By.xpath("//span/a[@title='Image View']")).getAttribute("href");
	                        driver.get(ImgView);
	                        
	                        
	      
	          
	          
	          
	          String st=driver.findElement(By.xpath("//h2[@id='s-result-count']")).getText();
	      
	        String[] arr=st.split(" ");
	        int countt=Integer.parseInt(arr[3]);
	        
	        int page = countt/60;
	          
	      
	        loop20:
	       for(int p=0;p<page;p++){       
	      try {
	      //List<WebElement> maincategory=driver.findElements(By.xpath("//a[contains(@class,'s-access-detail-page')]"));
	      List<WebElement> maincategory=driver.findElements(By.xpath("//ul[@id='s-results-list-atf']//li[contains(@id,'result_')]//a[contains(@class,'a-link-normal s-access-detail-page  a-text-normal')]"));      
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
	        Author = driver.findElement(By.xpath("//h2[@id='s-result-count']//span//span")).getText();
	        
	        resultoutput_pass();
	        FileOutputStream fileOut = new FileOutputStream(filename);
	        hwb.write(fileOut);
	        }     
	        }
	          catch (StaleElementReferenceException e) {
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
	          Thread.sleep(6000);
	          } 
	          else{System.out.println("last page reached");
	          }
	      }
	      
	      WebElement NextPage=null;
	      try
	      {
	       //NextPage = driver.findElement(By.id("pagnNextString"));
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
	      Thread.sleep(6000);
	      } 
	      else{System.out.println("last page reached");
	      break loop20;
	      
	      }
	     }
	}
	}    

}
