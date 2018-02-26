package com.selenium.amazon;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AmazonAutomation {

	
	static File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
	static FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	static FirefoxProfile firefoxProfile = new FirefoxProfile();
	static FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
	//static String baseUrl = "http://www.amazon.com/Coupons";
	static String baseUrl = "http://www.w3schools.com";
	
	public static void main(String[] args) {
		
		
		
		
	}
	
	public static void openWindow(){
		System.out.println("--------------------------------------------");
		/*driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println("test");*/
       // driver.findElement(By.xpath("//span[contains(text(),'Hello')]")).click();
       // System.out.println("Test1");
		
	}
	
	
	
	
	
	
}
