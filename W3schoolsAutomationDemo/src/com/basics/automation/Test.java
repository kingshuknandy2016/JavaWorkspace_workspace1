package com.basics.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Test {

	public void check() {
		System.out.println("**************");
		WebDriver driver=new FirefoxDriver();
		driver.get("http://spiproject/spiproject/");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		driver.manage().window().maximize() ;
		WebElement username=driver.findElement(By.xpath("//input[@id='Login1_UserName']"));
		username.sendKeys("kingshuk.nandy");
		
		WebElement password=driver.findElement(By.xpath("//input[@id='Login1_Password']"));
		password.sendKeys("9007438097.kingshuk");
		
		WebElement button1=driver.findElement(By.xpath("//input[@id='Login1_LoginButton']"));
		button1.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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

}
