package com.stubhub;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class stubhub {

	
	static String baseUrl;
	static int p,row;
	          

	public static void main(String [] args) throws InterruptedException, BiffException, IOException {
	 System.setProperty("webdriver.chrome.driver", "D:/OdeskSelenium/chromedriver_win32/chromedriver.exe");
	   WebDriver driver = new ChromeDriver();
	   
	   

		
	   /* File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);*/
		
	  baseUrl = "http://www.google.com";
	  
	  driver.get(baseUrl + "/"); 
      WebElement element = driver.findElement(By.name("q"));
      element.sendKeys("https://www.stubhub.com/");
      element.sendKeys(Keys.ENTER);
	  
	  driver.manage().window().maximize();
	  
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//a[text()='Tickets at StubHub! Where Fans Buy and Sell Tickets']")).click();
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  WebElement element1 = wait.until(
	          ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1][@id='usrmncont']/ul[@id='headermenu']/li[1]/a[text()='Sign in']")));
	  
	  element1.click();
	  
	  driver.findElement(By.xpath("//a[text()='My Account']")).click();
	                 
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  

	}

}
