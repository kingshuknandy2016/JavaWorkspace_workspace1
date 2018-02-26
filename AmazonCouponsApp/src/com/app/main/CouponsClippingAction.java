package com.app.main;

import java.io.File;
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

public class CouponsClippingAction {
	
	static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	static FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
	//static int i;
	static int i,p;

	public void Clipping(String st){
		System.out.println("****************************Inside Clipping function********************************");
		
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
		pass.sendKeys("kolkata5");

		WebElement butt = driver.findElement(By.id("signInSubmit"));
		butt.click();*/
		
		
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
				for(WebElement cpntoclick:CpnListNtHidden){
					cpntoclick.click();
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
		
		driver.quit();
		
		
		System.out.println("test-------------------------------");
	}
}
