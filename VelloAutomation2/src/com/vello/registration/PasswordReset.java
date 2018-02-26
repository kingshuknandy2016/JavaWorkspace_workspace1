package com.vello.registration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.velloe.automation.LaunchApplication;
import com.velloe.automation.OR;
import com.velloe.automation.config;

public class PasswordReset {

	public static void main(String[] args) throws Exception {

   LaunchApplication.applicationLaunch();
   PasswordReset pss=new PasswordReset();
   pss.PasswordResetModules();
	}
	
	public void PasswordResetModules() throws IOException, InterruptedException{
		config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys("kingshuknandy1990@gmail.com");
		config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys("testqa@123");
		config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
		Thread.sleep(4000);
		
		 WebDriverWait wait1 = new WebDriverWait(config.driver, 15);
		 wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='menu-item-211']/a")));
		
		 Actions action = new Actions(config.driver);
		 WebElement we = config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a"));
		 action.moveToElement(we).build().perform();
		//config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a")).click();
		Thread.sleep(3000);
	
		config.driver.findElement(By.xpath("//li[@id='menu-item-188']")).click();
		
		
	}

}
