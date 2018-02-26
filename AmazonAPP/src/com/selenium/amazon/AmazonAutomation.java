package com.selenium.amazon;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AmazonAutomation {
	
	static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	//static String baseUrl = "http://www.amazon.com/Coupons";
	//static String baseUrl = "http://www.w3schools.com";
	
	
	public static void openWindow(){
		System.out.println("--------------------------------------------");
	
		
		driver.get("http://www.amazon.com/Coupons");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("test");
        driver.findElement(By.xpath("//span[contains(text(),'Hello. Sign in')]")).click();
        WebElement Email = driver.findElement(By.id("ap_email"));
        Email.click();
        Email.clear();
        Email.sendKeys("Siddharthamon@gmail.com");
        WebElement Password = driver.findElement(By.id("ap_password"));
        Password.click();
        Password.clear();
        Password.sendKeys("kolkata5");
		driver.findElement(By.id("signInSubmit")).click();
        
		List  <WebElement> CouponHeader = driver.findElements(By.xpath("//div[@class='a-section coupon-shoveler']"));
		
		int TotalListHeader=CouponHeader.size();
		System.out.println("no of:"+TotalListHeader);
		
		for (int i = 0; i <TotalListHeader; i++) {
			
			String cpnhead =CouponHeader.get(i).findElement(By.tagName("h3")).getText();
			System.out.println(cpnhead);
			
			
			
		}
		
		System.out.println("End");
        
        
        
	}
	AmazonAutomation(){
		System.out.println("----------------545454545454545----------------------------");
	}
	
	public static void main(String[] args) {
		AmazonAutomation.openWindow();
	}
	
	
	

}
