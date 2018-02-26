package com.spi.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebDriver driver=new FirefoxDriver();
		driver.get("https://spimail.spi.com/");
		driver.manage().window().maximize() ;
		WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
		username.sendKeys("kingshuk.nandy");
		
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("$picity123456");
		
		WebElement button1=driver.findElement(By.xpath("//input[@value='Sign In']"));
		button1.click();

	}

}
