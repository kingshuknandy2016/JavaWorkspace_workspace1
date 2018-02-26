package amazon.vendor.central;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class VehcleDetails {

	 private static String RefAGC, Eurocode, Description, Price;

	    static String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss")
	   .format(Calendar.getInstance().getTime());
	 static String filename = "C:\\Users\\Kingshuk Nandy\\workspace\\VechileDetails\\"
	   + "Vehicle Details " + timeStamp + " Summary" + ".xls";
	 private static HSSFWorkbook hwb = new HSSFWorkbook();
	 private static HSSFSheet sheet = hwb.createSheet("Summary");

	 int row;
	 static int i = 1, arr = 0;

	/*    static File pathToBinary = new File(
	   "C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	 static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	 static FirefoxProfile firefoxProfile = new FirefoxProfile();
	 static FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);*/

	//****Main Method *********************************/
	 public static void main(String[] args) throws Exception {
	  ExcelColumnName();
	  Scraper();
	 }

	 //--- Method to Write Excel ****/

	 private static void ExcelColumnName() throws Exception {
	  HSSFRow rowhead = sheet.createRow(0);
	  rowhead.createCell((short) 0).setCellValue("Sr No");
	  rowhead.createCell((short) 1).setCellValue("RefAGC");
	  rowhead.createCell((short) 2).setCellValue("Eurocode");
	  rowhead.createCell((short) 3).setCellValue("Description");
	  rowhead.createCell((short) 4).setCellValue("Price");

	 }

	 public static void resultoutput_pass() throws Exception {
	  HSSFRow row = sheet.createRow(i);
	  row.createCell((short) 0).setCellValue(i);
	  row.createCell((short) 1).setCellValue(RefAGC);
	  row.createCell((short) 2).setCellValue(Eurocode);
	  row.createCell((short) 3).setCellValue(Description);
	  row.createCell((short) 4).setCellValue(Price);
	  i++;
	 }

	 public static void Scraper() throws IOException {
	        
	   File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	   FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	   FirefoxProfile firefoxProfile = new FirefoxProfile();
	   FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
	  
	  String baseUrl = "http://www.agc-arg.eu/France/fr_FR/EUR/CATALOG/A;pgid=d2K0xHfBO2dSR0J2MA08Lp5s0000JhGoV22d;sid=9-iboBDgH_xGwEZ2orYTUJTqNfc4WdL_VBE=";
	  driver.get(baseUrl);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	  try {
	   
	        driver.findElement(By.xpath("(//div[@id='workingTemplate']//div[@class='head']//h4)[1]")).click(); 
	        driver.findElement(By.id("LoginForm_Login")).click();
	        driver.findElement(By.id("LoginForm_Login")).sendKeys("3002112001");
	        driver.findElement(By.id("LoginForm_Password")).click();
	        driver.findElement(By.id("LoginForm_Password")).sendKeys("babb18");
	        driver.findElement(By.xpath("//button[@name='LoginForm_LoginButton']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//li[@id='navCatalog']/a")).click();
	        Thread.sleep(2000);
	        
	        List<WebElement> CarList = driver.findElements(By.xpath("//table[@class='agc-products-table']//tbody//tr"));
	        
	        for(int i=1;i<=CarList.size();i++){
	        
	        //for(WebElement item : CarList )
	        
	         
	         RefAGC = driver.findElement(By.xpath("//table[@class='agc-products-table']//tbody//tr["+i+"]//td[2]")).getText();
	         Eurocode = driver.findElement(By.xpath("//table[@class='agc-products-table']//tbody//tr["+i+"]//td[3]")).getText();
	         Description = driver.findElement(By.xpath("//table[@class='agc-products-table']//tbody//tr["+i+"]//td[5]")).getText();
	         Price = driver.findElement(By.xpath("//table[@class='agc-products-table']//tbody//tr["+i+"]//td[8]")).getText();
	         
	         resultoutput_pass();
	    FileOutputStream fileOut = new FileOutputStream(
	        filename);
	      hwb.write(fileOut);
	 

	        }
	   

	   } catch (Exception e) {
	    System.out.println("error " + e);
	   }
	 } 
	
	
	

}
