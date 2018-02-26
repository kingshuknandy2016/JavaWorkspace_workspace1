package com.app.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.testngexamples.BaseExample;

public class CouponsClippingAction extends BaseExample {
	
	
	
	File pathToBinary = new File(
			"C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	
	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile();
    FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
    int i,p;

    
    public static void main(String[] args) {
    	CouponsClippingAction cpn = new CouponsClippingAction();
    	cpn.Clipping(null);
    	
		
	}
    
    
    
    //@Test
	public void Clipping(String st){
    	String filePath = "C:\\filepath.html";
    	extent = new ExtentReports(filePath, true);
    	    	
    	
		System.out.println("****************************Inside Clipping function********************************"+st);
		System.out.println("inside function");
		driver.get("http://www.amazon.com/Coupons");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[contains(text(),'Hello')]"))
				.click();

		/*WebElement Email = driver.findElement(By.id("ap_email"));
		Email.click();
		Email.clear();
		Email.sendKeys("Siddharthamon@gmail.com");

		WebElement pass = driver.findElement(By.id("ap_password"));
		pass.click();
		pass.clear();
		pass.sendKeys("kolkata5");*/

		//WebElement butt = driver.findElement(By.id("signInSubmit"));
		//butt.click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-yourAccount")));
		
		System.out.println("Logged In");
		
		
		try {		
		
			
		WebElement Mainhead= driver.findElement(By.id("merchandised-content")).findElement(By.tagName("h1"));	
		Mainhead.sendKeys(Keys.PAGE_DOWN);	
		Thread.sleep(2000);
		List<WebElement> CpnHead = driver.findElements(By.xpath("//div[@id='merchandised-content']//div[@class='a-section coupon-shoveler']"));
		
		int totalcpnhd = CpnHead.size();
		System.out.println("Total coupon Head" + totalcpnhd);
		
		for ( i=1;i<=totalcpnhd;i++){
			
			WebElement cpn = CpnHead.get(i).findElement(By.tagName("h3"));
			String cpnn = cpn.getText();
			System.out.println(cpnn);
			p=i+1;
			List<WebElement> CpnList = driver.findElements(By.xpath("//div[@id='merchandised-content']//div[@class='a-section coupon-shoveler']["+p+"]//div[contains(@id,'coupon-items')]//div[contains(@id,'anonCarouse')]//li"));			
			System.out.println(CpnList);
			int totalCpnlst = CpnList.size();
			System.out.println("Coupon List for: "+cpnn+ ":: "+ totalCpnlst);
			int cpnperpg = totalCpnlst / 6;
			System.out.println("Total page: " + cpnperpg);
			for (int j = 0; j <=cpnperpg; j++){				
				List<WebElement> CpnListNtHidden = driver.findElements(By
						.xpath("//div[@id='merchandised-content']//div[@class='a-section coupon-shoveler']["+p+"]//ol[@class='a-carousel']//li[@aria-hidden='false']//span[@class='a-button-inner']"));
				int CpnListToClick = CpnListNtHidden.size();
				System.out.println("Total Coupon to be Clicked: "+CpnListToClick);
				
				
				int counter =0;
				for(WebElement cpntoclick:CpnListNtHidden){
					
					ExtentTest test = extent.startTest("Total Coupon Click", "Summary");
					
					cpntoclick.click();
					counter++;
			        test.log(LogStatus.INFO, "Total '"+counter+"' coupon clicked(logStatus, details)");
			        
			        extent.endTest(test);
			        
			        // calling flush writes everything to the log file
			        extent.flush();
					
				}
						
				driver.findElement(By.xpath("//div[@id='merchandised-content']//div[@class='a-section coupon-shoveler']["+p+"]//i[@class='a-icon a-icon-next']")).click();
			}
			cpn.sendKeys(Keys.PAGE_DOWN);
			Thread.sleep(2000);		
		}
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			
			File htmlFile = new File("C:\\filepath.html");
			try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
				}
		
		
		//driver.quit();
		
		
		System.out.println("test-------------------------------");
	}
}
