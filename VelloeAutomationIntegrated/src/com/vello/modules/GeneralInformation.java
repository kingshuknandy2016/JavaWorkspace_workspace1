package com.vello.modules;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.velloe.repository.OR;
import com.velloe.repository.Parameter;
import com.velloe.repository.config;

public class GeneralInformation {
	
	static String ModuleName,TestId,Expected,Actual;
	
	public void checkGeneralInformation() throws IOException, InterruptedException{
		
		config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys("kingshuknandy1990@gmail.com");
		config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys("testqa@123");
		config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
		Thread.sleep(4000);
		
		
		//-----------------------TC001_Check_Title-------------------------------------------
		ModuleName = "GeneralInformation"; 
		TestId = "TC001_Check_Title";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {

			 WebDriverWait wait1 = new WebDriverWait(config.driver, 15);
			 wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='menu-item-211']/a")));
			
			 Actions action = new Actions(config.driver);
			 WebElement we = config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a"));
			 action.moveToElement(we).build().perform();
			//config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a")).click();
			Thread.sleep(3000);
			config.driver.findElement(By.xpath("//li[@id='menu-item-86']")).click();
            String actualTitle = config.driver.getTitle();
			System.out.println("Check////////////"+actualTitle);
			if (actualTitle.equalsIgnoreCase(Expected)) {
				Actual =actualTitle;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			}else{
				Actual ="Blank";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		//----------------------TC002_Business Name Field_Blank
		TestId = "TC002_Business Name Field_Blank";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement BusinessNameFieldErrorMsg=null;
		try {
			config.driver.findElement(OR.GetLocator(ModuleName, "BusinessName")).clear();
			config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButton")).click();
			Thread.sleep(2000);
			BusinessNameFieldErrorMsg=config.driver.findElement(OR.GetLocator(ModuleName, "BusinessNameBlankErrorMsg"));
			Actual=BusinessNameFieldErrorMsg.getText();
			if(Actual.equalsIgnoreCase(Expected)){
				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		//----------------------TC003_Telephone Number field_Blank
		TestId = "TC003_Telephone Number field_Blank";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement TelephoneNumberFieldErrorMsg=null;
		try {
			config.driver.findElement(By.xpath("//button[@class='btn btn-primary btn-default generalAlertClose']")).click();
			Thread.sleep(2000);
			//config.driver.findElement(OR.GetLocator(ModuleName, "BusinessName")).sendKeys("TestBusiness");
			config.driver.findElement(OR.GetLocator(ModuleName, "TelephoneNumber")).clear();
			config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButton")).click();
			config.driver.findElement(By.xpath("//button[@class='btn btn-primary btn-default generalAlertClose']")).click();
			TelephoneNumberFieldErrorMsg=config.driver.findElement(OR.GetLocator(ModuleName, "TelephoneNumberBlankMsg"));
			Actual=TelephoneNumberFieldErrorMsg.getText();
			System.out.println("Actual value is"+Actual);
			if(Actual.equalsIgnoreCase(Expected)){
				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

		
		//----------------------TC004_Address Blank
		//input[@id='input_4_16_1']
	
		TestId = "TC004_Address Blank";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement AddressFieldErrorMsg=null;
		try {
			//config.driver.findElement(By.xpath("//button[@class='btn btn-primary btn-default generalAlertClose']")).click();
			Thread.sleep(2000);
			//config.driver.findElement(OR.GetLocator(ModuleName, "BusinessName")).sendKeys("TestBusiness");
			//config.driver.findElement(OR.GetLocator(ModuleName, "TelephoneNumber")).clear();
			config.driver.findElement(By.xpath("//input[@id='input_4_16_1']")).clear();
			config.driver.findElement(By.xpath("//input[@id='input_4_16_3']")).clear();
			config.driver.findElement(By.xpath("//input[@id='input_4_16_4']")).clear();
			config.driver.findElement(By.xpath("//input[@id='input_4_16_5']")).clear();
			config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButton")).click();
			config.driver.findElement(By.xpath("//button[@class='btn btn-primary btn-default generalAlertClose']")).click();
			AddressFieldErrorMsg=config.driver.findElement(OR.GetLocator(ModuleName, "AddressBlankErrorMsg"));
			Actual=AddressFieldErrorMsg.getText();
			System.out.println("Actual value is"+Actual);
			if(Actual.equalsIgnoreCase(Expected)){
				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		config.driver.close();
		
	}
	}