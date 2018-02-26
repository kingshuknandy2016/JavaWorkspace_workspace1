package com.spi.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;

public class SpiProjectLogin {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 1; i++) {
			
			WebDriver driver=new FirefoxDriver();
			driver.get("http://www.spiproject.com/spiproject/");
			Thread.sleep(2000);  
			
			
			driver.manage().window().maximize() ;
			WebElement username=driver.findElement(By.xpath("//input[@id='Login1_UserName']"));
			username.sendKeys("kingshuk.nandy");
			
			WebElement password=driver.findElement(By.xpath("//input[@id='Login1_Password']"));
			password.sendKeys("9007438097.kingshuk");
			
			WebElement button1=driver.findElement(By.xpath("//input[@id='Login1_LoginButton']"));
			button1.click();
			
			Thread.sleep(10000);
		
			WebElement button2=driver.findElement(By.xpath("//a[text()='Reports']"));
			button2.click();
			
			
			
			driver.findElement(By.id("ctl00_body_MyProjectsDropDown")).click();
			//Action 
			Actions action = new Actions(driver);
			
			WebElement drop= driver.findElement(By.id("ctl00_body_MyProjectsDropDown")).findElement(By.xpath("//option[2]"));
			action.doubleClick(drop).perform();
			
			WebElement button3=driver.findElement(By.linkText("Project Access Card Report"));
			button3.click();
			
		}
		
		
		
		
		//Select dropdown=new Select(driver.findElement(By.id("ctl00_body_MyProjectsDropDown")).findElement(By.tagName("option")));
		
	//	dropdown.selectByIndex(1);
		
		
		
		
		
	/*	WebElement button3=driver.findElement(By.xpath("//a[@id='reportId-101']"));
		button3.click();*/
	}

}
