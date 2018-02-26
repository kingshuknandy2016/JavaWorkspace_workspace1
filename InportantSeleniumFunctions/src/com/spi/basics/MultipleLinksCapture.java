package com.spi.basics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultipleLinksCapture {

	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
    	//WebDriver driver = new ChromeDriver();

	    WebDriver driver=new FirefoxDriver();
		driver.get("http://www.w3schools.com/");
		Thread.sleep(2000); 
		driver.manage().window().maximize() ;
		
		
		try {
			List<WebElement> href = driver.findElements(By.xpath("//nav[@class='w3-sidenav w3-card-2 w3-animate-left']//a"));
			List<String> li=new ArrayList<String>();
	         Iterator ite=href.iterator();
	         while(ite.hasNext()){
	        	 WebElement acor=(WebElement) ite.next();
	        	 li.add(acor.getAttribute("href"));

	         }
	         
	         Iterator linkload=li.iterator();
	         while(linkload.hasNext()){
	        	 driver.get((String) linkload.next());
	        	// Thread.sleep(5000);
	         }
			
		} catch (Exception e) {
			System.out.println("Error"+e);
		} 
		
		
		try {
			
		}catch (Exception e1) {
			System.out.println("Error"+e1);
		} 
		
		
		
	}

}
