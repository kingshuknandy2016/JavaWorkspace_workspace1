package com.basics.german;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class GermanScrapping {

/*	public static void main(String[] args) throws Exception {
		GermanScrapping ge=new GermanScrapping();
		ge.AuroraSmokeTest();

	}
	
	
	
	 private String baseUrl;
	    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	                                                    .getInstance().getTime());
	    String filename = "D:\\GermanScrapping\\" + "Organizations_Verbände" + timeStamp + " Summary" + ".xls";
	    HSSFWorkbook hwb = new HSSFWorkbook();
	    HSSFSheet sheet = hwb.createSheet("Summary");

	    int row, i=1;
	     static int t;
	     static String href;
	    private String  OrganizationName,Acronym,Branch,NoMembers,PhoneNumber,EMail,Position,Salutation,Title,FirstName,LastName,StreetAddress,ZipCode,City,website,Fax;
;

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
	                    rowhead.createCell((short) 0).setCellValue("Organization Name");
	                    rowhead.createCell((short) 1).setCellValue("Acronym");
	                    rowhead.createCell((short) 2).setCellValue("Branch"); 
	                    rowhead.createCell((short) 3).setCellValue("Number of Members /Size");  
	                    rowhead.createCell((short) 4).setCellValue("phone number");  
	                    rowhead.createCell((short) 5).setCellValue("Contact E-Mail");  
	                    rowhead.createCell((short) 6).setCellValue("Position");  
	                    rowhead.createCell((short) 7).setCellValue("Herr / Frau (Mr. / Ms.)");
	                    rowhead.createCell((short) 8).setCellValue("Title");
	                    rowhead.createCell((short) 9).setCellValue(" First Name");
	                    rowhead.createCell((short) 10).setCellValue("Last Name");
	                    rowhead.createCell((short) 11).setCellValue("Street Address");
	                    rowhead.createCell((short) 12).setCellValue("Zip Code");
	                    rowhead.createCell((short) 13).setCellValue("City");
	                    rowhead.createCell((short) 14).setCellValue("Website");
	                    rowhead.createCell((short) 15).setCellValue("Fax");
	                    
	                   // System.out.println("created");
	                    }


	     public void resultoutput_pass() throws Exception 
	                    {
	                    HSSFRow row = sheet.createRow(i);	                   
	                    row.createCell((short) 0).setCellValue(OrganizationName);
	                    row.createCell((short) 1).setCellValue(Acronym);
	                    row.createCell((short) 2).setCellValue(Branch); 
	                    row.createCell((short) 3).setCellValue(NoMembers);  
	                    row.createCell((short) 4).setCellValue(PhoneNumber);  
	                    row.createCell((short) 5).setCellValue(EMail);  
	                    row.createCell((short) 6).setCellValue(Position);  
	                    row.createCell((short) 7).setCellValue(Salutation);
	                    row.createCell((short) 8).setCellValue(Title);
	                    row.createCell((short) 9).setCellValue(FirstName);
	                    row.createCell((short) 10).setCellValue(LastName);
	                    row.createCell((short) 11).setCellValue(StreetAddress);
	                    row.createCell((short) 12).setCellValue(ZipCode);
	                    row.createCell((short) 13).setCellValue(City);
	                    row.createCell((short) 14).setCellValue(website);
	                    row.createCell((short) 15).setCellValue(Fax);
	                    i++;
	                     }
	    
	    public void applicationLaunch() throws Exception {
	                    //WebDriver driver=new FirefoxDriver();
	         WebDriver driver=null;
	         
	         System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	                 driver = new ChromeDriver();
	        
	                    baseUrl = "http://verbaende.com/suche/";
	                    driver.get(baseUrl);
	                    driver.manage().window().maximize();
	                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	                    driver.findElement(By.xpath("(//input[@id='q'])[1]")).sendKeys("berlin");
	                    driver.findElement(By.xpath("(//fieldset//button[@type='submit'])[1]")).click();
	                    Thread.sleep(1000);
	      
	                   for(int m=0;m<160;m++){
	                    List<String> hrefs = new ArrayList<String>();
	                    
	                    List<WebElement> anchors = driver.findElements(By.xpath("//ol[@id='page-1']//li/a"));
	                  
	                    for ( WebElement anchor : anchors ) {
	                        hrefs.add(anchor.getAttribute("href"));                        
	                         t =hrefs.size();
	                         
	                         System.out.println("size "+t);
	                        
	                    }

	                    
	                    
	                    
	                    
	                   for ( int b=0;b<10;b++ ) {
	                	   
	                	   //String url1 = driver.getCurrentUrl();
	                	   try {
	                		   String url=hrefs.get(b);	                        
		                        WebDriver driver2 = new ChromeDriver();
		                        driver2.get(url);
		                        driver2.manage().window().maximize();
		                        Thread.sleep(1000);
		                        
		                        OrganizationName=driver2.findElement(By.xpath("//h2[@class='wie_h1']")).getText();
		                        Acronym=null;
		                        Branch=null;
		                        NoMembers=null;
		                        
		                        String St=driver2.findElement(By.xpath("//div[@class='kontaktdaten']")).getText();
		                       String lines[]= St.split("\\r?\\n");
		                        PhoneNumber=lines[3];
		                        EMail=driver2.findElement(By.xpath("(//div[@class='kontaktdaten']/a)[1]")).getText();
		                        //,Position,Salutation,Title,FirstName,LastName,
		                        Position=null;
		                        Salutation=null;
		                        FirstName=null;
		                        LastName=null;
		                        StreetAddress=lines[0];
		                        ZipCode=null;
		                        City=null;
		                        website=driver2.findElement(By.xpath("(//div[@class='kontaktdaten']/a)[2]")).getText();
		                        Fax=lines[4];
		                        resultoutput_pass();
		            	        FileOutputStream fileOut = new FileOutputStream(filename);
		            	        hwb.write(fileOut);
		            	        driver2.quit();
							
						} catch (Exception e) {
							// TODO: handle exception
						}
	                       
	                    
	                   }
							
           
	       
	                    
	                    
	                   // driver.http://verbaende.com/suche/#page-1
	      WebElement NextPage=null;
	      try
	      { 
	       NextPage = driver.findElement(By.xpath("//a[@class='page-link next']"));
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
	     
	}*/
	
	
	  public static void main(String[] args) throws Exception {
	        GermanScrapping ge=new GermanScrapping();
	        ge.AuroraSmokeTest();

	    }
	   
	   
	   
	     private String baseUrl;
	        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	                                                        .getInstance().getTime());
	        String filename = "D:\\GermanScrapping\\" + "Organizations_Verbände" + timeStamp + " Summary" + ".xls";
	        HSSFWorkbook hwb = new HSSFWorkbook();
	        HSSFSheet sheet = hwb.createSheet("Summary");

	        int row, i=1;
	         static int t;
	         static String href;
	        private String  OrganizationName,Acronym,Branch,NoMembers,PhoneNumber,EMail,Position,Salutation,Title,FirstName,LastName,StreetAddress,ZipCode,City,website,Fax;
	

/*	        File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
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
	                        rowhead.createCell((short) 0).setCellValue("Organization Name");
	                        rowhead.createCell((short) 1).setCellValue("Acronym");
	                        rowhead.createCell((short) 2).setCellValue("Branch");
	                        rowhead.createCell((short) 3).setCellValue("Number of Members /Size"); 
	                        rowhead.createCell((short) 4).setCellValue("phone number"); 
	                        rowhead.createCell((short) 5).setCellValue("Contact E-Mail"); 
	                        rowhead.createCell((short) 6).setCellValue("Position"); 
	                        rowhead.createCell((short) 7).setCellValue("Herr / Frau (Mr. / Ms.)");
	                        rowhead.createCell((short) 8).setCellValue("Title");
	                        rowhead.createCell((short) 9).setCellValue(" First Name");
	                        rowhead.createCell((short) 10).setCellValue("Last Name");
	                        rowhead.createCell((short) 11).setCellValue("Street Address");
	                        rowhead.createCell((short) 12).setCellValue("Zip Code");
	                        rowhead.createCell((short) 13).setCellValue("City");
	                        rowhead.createCell((short) 14).setCellValue("Website");
	                        rowhead.createCell((short) 15).setCellValue("Fax");
	                       
	                       // System.out.println("created");
	                        }


	         public void resultoutput_pass() throws Exception
	                        {
	                        HSSFRow row = sheet.createRow(i);                      
	                        row.createCell((short) 0).setCellValue(OrganizationName);
	                        row.createCell((short) 1).setCellValue(Acronym);
	                        row.createCell((short) 2).setCellValue(Branch);
	                        row.createCell((short) 3).setCellValue(NoMembers); 
	                        row.createCell((short) 4).setCellValue(PhoneNumber); 
	                        row.createCell((short) 5).setCellValue(EMail); 
	                        row.createCell((short) 6).setCellValue(Position); 
	                        row.createCell((short) 7).setCellValue(Salutation);
	                        row.createCell((short) 8).setCellValue(Title);
	                        row.createCell((short) 9).setCellValue(FirstName);
	                        row.createCell((short) 10).setCellValue(LastName);
	                        row.createCell((short) 11).setCellValue(StreetAddress);
	                        row.createCell((short) 12).setCellValue(ZipCode);
	                        row.createCell((short) 13).setCellValue(City);
	                        row.createCell((short) 14).setCellValue(website);
	                        row.createCell((short) 15).setCellValue(Fax);
	                        i++;
	                         }
	       
	        public void applicationLaunch() throws Exception {
	                        //WebDriver driver=new FirefoxDriver();
	             WebDriver driver=null;
	            
	             System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	                     driver = new ChromeDriver();
	                        //driver=new FirefoxDriver();
/*	                        baseUrl = "http://verbaende.com/suche/";
	                        driver.get(baseUrl);
	                        driver.manage().window().maximize();
	                        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	                        driver.findElement(By.xpath("(//input[@id='q'])[1]")).sendKeys("Bremen");
	                        driver.findElement(By.xpath("(//fieldset//button[@type='submit'])[1]")).click();
	                        Thread.sleep(1000);*/
	                        
	                        baseUrl = "http://verbaende.com/suche/";
		                    driver.get(baseUrl);
		                    driver.manage().window().maximize();
		                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		                    driver.findElement(By.xpath("(//input[@id='q'])[1]")).sendKeys("berlin");
		                    driver.findElement(By.xpath("(//fieldset//button[@type='submit'])[1]")).click();
		                    Thread.sleep(1000);
	         
	                       for(int m=0;m<160;m++){
	                        List<String> hrefs = new ArrayList<String>();
	                       
	                        List<WebElement> anchors = driver.findElements(By.xpath("//ol[@id='page-1']//li/a"));
	                     
	                        for ( WebElement anchor : anchors ) {
	                            hrefs.add(anchor.getAttribute("href"));                       
	/*                             t =hrefs.size();
	                            
	                             System.out.println("size "+t);*/
	                           
	                        }
	                       
	                       for ( int b=0;b<10;b++ ) {
	                          
	                           //String url1 = driver.getCurrentUrl();
	                          
	                            String url=hrefs.get(b); 
	                            
	                            
/*	                            File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	                            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	                            FirefoxProfile firefoxProfile = new FirefoxProfile();
	                            FirefoxDriver driver2 = new FirefoxDriver(ffBinary,firefoxProfile);*/
	                            
	                            //WebDriver driver2 = new FirefoxDriver();
	           	             WebDriver driver2=null;
	         	            
	        	             System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	        	                     driver2 = new ChromeDriver();
	                            driver2.get(url);
	                            driver2.manage().window().maximize();
	                            driver2.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	                            //Thread.sleep(1000);
	                            try{
	                            OrganizationName=driver2.findElement(By.xpath("//h2[@class='wie_h1']")).getText();
	                            } catch (Exception e){
	                                e.printStackTrace();
	                            }
	                            System.out.println(OrganizationName);
	                            Acronym=null;
	                            Branch=null;
	                            NoMembers=null;
	                            try{
	                            String St=driver2.findElement(By.xpath("//div[contains(@class,'kontaktdaten')]")).getText();                            
	                            String lines[]= St.split("\\r?\\n");
	                            StreetAddress=lines[0];                            
	                            PhoneNumber=lines[3];
	                            Fax=lines[4];
	                            } catch (Exception e){
	                                e.printStackTrace();
	                            }
	                            try{
	                            EMail=driver2.findElement(By.xpath("(//div[@class='kontaktdaten']/a)[1]")).getText();
	                            //,Position,Salutation,Title,FirstName,LastName,
	                            } catch (Exception e){
	                                e.printStackTrace();
	                            }
	                            
	                            Position=null;
	                            Salutation=null;
	                            FirstName=null;
	                            LastName=null;                            
	                            ZipCode=null;
	                            City=null;
	                            try{
	                            website=driver2.findElement(By.xpath("(//div[@class='kontaktdaten']/a)[2]")).getText();
	                            }catch (Exception e){
	                                e.printStackTrace();
	                            }
	                            
	                            resultoutput_pass();
	                            FileOutputStream fileOut = new FileOutputStream(filename);
	                            hwb.write(fileOut);
	                           
	/*                            try {
	                                String[] st1=st.split("(") ;
	                                try {
	                                    OrganizationName=st1[0];
	                                    System.out.println("Name:"+OrganizationName);
	                                     Acronym=st1[1];
	                                      System.out.println("Acronym:"+Acronym);
	                                } catch (Exception e) {
	                                    // TODO: handle exception
	                                }
	                               

	                             
	                            } catch (Exception e) {
	                                OrganizationName=st;
	                                Acronym=null;
	                            }*/

	                           

	                          
	                           
	                            driver2.quit();
	                           
	                            //url1 = driver.getCurrentUrl();
	                           
	                         ///code for information
	                       }
	                           
	          
	          
	                       
	                       
	                       // driver.http://verbaende.com/suche/#page-1
	          WebElement NextPage=null;
	          try
	          {
	           NextPage = driver.findElement(By.xpath("//a[@class='page-link next']"));
	           }
	          catch (Exception e1)
	          {
	          System.out.println("error "+e1);
	         
	          }
	          if (NextPage != null)
	          {
	          NextPage.click();
	          Thread.sleep(3000);
	          }
	          else{System.out.println("last page reached");
	         
	         
	          }
	         }
	        
	    }
	
}



