package main.controller;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AutomationAmazon {
	
	
	

	static File pathToBinary = new File(
			"C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	static FirefoxDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
	
	

	public static void openWindow() {
		System.out.println("inside function");
		driver.get("http://www.amazon.com/Coupons");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[contains(text(),'Hello')]"))
				.click();

		WebElement Email = driver.findElement(By.id("ap_email"));
		Email.click();
		Email.clear();
		Email.sendKeys("Siddharthamon@gmail.com");

		WebElement pass = driver.findElement(By.id("ap_password"));
		pass.click();
		pass.clear();
		pass.sendKeys("kolkata5");

		WebElement butt = driver.findElement(By.id("signInSubmit"));
		butt.click();
		
		try {
			Thread.sleep(20000);
	

		List<WebElement> CpnHead = driver.findElements(By.id("merchandised-content").className("a-section coupon-shoveler").tagName("h3"));
		int totalcpnhd = CpnHead.size();
		System.out.println("Total coupon Head" + totalcpnhd);
		
		
		
		for (int i = 1; i <= 2; i++) {
			String CoupnHead = CpnHead.get(i).getText();
			
			System.out.println(CoupnHead);
						
				List<WebElement> CpnList = driver.findElements(By
						.xpath("//div[@class='a-section coupon-shoveler']["+i+"]//input[@class='a-button-input']"));
				int totalCpnlst = CpnList.size();
				System.out.println("Per Header Coupon List: " + totalCpnlst);
				int cpnperpg = totalCpnlst / 6;
				System.out.println("Coupn in each page: " + cpnperpg);
				for (int j = 0; j <cpnperpg; j++) {
					j=j+1;
					List<WebElement> CpnListNtHidden = driver.findElements(By
							.xpath("//div[@class='a-section coupon-shoveler']["
									+j+ "]//li[@aria-hidden='false']//span[@class='a-button-inner']"));
					int cuponlstvisble = CpnListNtHidden.size();
					for (int k = 0; k < cuponlstvisble; k++) {
						CpnListNtHidden.get(k).click();	

					}
					driver.findElement(By.id("a-autoid-4")).click();
					
				}
				

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		System.out.println("test-------------------------------");

	}
	
	public static void main(String[] args) {
		openWindow();
		
		
	}
}
