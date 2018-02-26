package com.basics;

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


public class AmazonASIN {
	
	
	public static void main(String[] args) throws Exception {
		AmazonASIN as=new AmazonASIN();
		as.AuroraSmokeTest();
	}

	private String baseUrl;
	String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(Calendar
	                                                .getInstance().getTime());
	String filename = "D:\\AmazonUKBook22\\"
	                                                + "AmazonProduct Details " + timeStamp + " Summary" + ".xls";
	HSSFWorkbook hwb = new HSSFWorkbook();
	HSSFSheet sheet = hwb.createSheet("Summary");

	int row, i=1;
	 static int t;
	 static String href;
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
     //WebDriver driver=new FirefoxDriver();
	WebDriver driver=null;
	
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        	driver = new ChromeDriver();
     baseUrl = "http://www.amazon.co.uk/s/ref=sr_il_ti_stripbooks?fst=as%3Aoff&rh=n%3A266239%2Cp_n_feature_browse-bin%3A405231011&bbn=266239&ie=UTF8&qid=1460324852&lo=stripbooks";
     driver.get(baseUrl);
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    
     List<String> hrefs = new ArrayList<String>();
     
   //xpath ouside
     
     
      List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
      
      // xpath inside
    // List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 6px']//a"));
     //or
     // List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 14px']//a"));
     
     //------------------------------------------
     //List<WebElement> anchors = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 6px']//a"));
     
     
     // driver.findElement(By.xpath("(//span[@class='s-layout-toggle-picker'])[2]")).click();
     for ( WebElement anchor : anchors ) {
     	//----------------new code
     	
     	//List<WebElement> anchors1 = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//a"));
    	// List<WebElement> anchors1 = driver.findElements(By.xpath("//div[@class='categoryRefinementsSection']//li[@style='margin-left: 14px']//a"));
        
     	//--------------------------
     	hrefs.add(anchor.getAttribute("href"));	                    
          t =hrefs.size();
     }
     for ( int b=0;b<t;b++ ) {
     	String url=hrefs.get(b);
     	driver.get(url);
     	//driver.findElement(By.xpath("(//span[@class='s-layout-toggle-picker'])[2]")).click();
loop20:             
for(int p=0;p<20;p++){	             
try {
List<WebElement> maincategory=driver.findElements(By.xpath("//a[contains(@class,'s-access-detail-page')]"));
	//or
	//List<WebElement> maincategory=driver.findElements(By.xpath("//li[@style='margin-left: 14px']//span[@class='refinementLink']"));
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
resultoutput_pass();
FileOutputStream fileOut = new FileOutputStream(filename);
hwb.write(fileOut);
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
Thread.sleep(6000);
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
Thread.sleep(6000);
} 
else{System.out.println("last page reached");
break loop20;

}
}
}
}

}
