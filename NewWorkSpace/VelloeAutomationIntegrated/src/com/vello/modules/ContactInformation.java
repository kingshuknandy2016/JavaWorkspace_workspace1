package com.vello.modules;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.velloe.repository.OR;
import com.velloe.repository.Parameter;
import com.velloe.repository.config;

public class ContactInformation {
	static String ModuleName,TestId,Expected,Actual;

	public void ContactInformationCheck() throws FileNotFoundException {

		ModuleName = "ContactInformation";
		TestId = "TC001_Check_Title";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys("qa1@velloe.com");
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys("testqa123");
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			Thread.sleep(7000);
			 WebDriverWait wait1 = new WebDriverWait(config.driver, 15);
			 wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='menu-item-211']/a")));
			
			 Actions action = new Actions(config.driver);
			 WebElement we = config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a"));
			 action.moveToElement(we).build().perform();
			//config.driver.findElement(By.xpath(".//*[@id='menu-item-211']/a")).click();
			Thread.sleep(4000);
			config.driver.findElement(By.xpath("//li[@id='menu-item-85']")).click();
			//System.out.println("++++++++++++++++++++++++++");
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
		
		//----------TC002_Default salutation
		ModuleName = "ContactInformation";
		TestId = "TC002_Default salutation";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement SalutationDefault=null;
		try {
			//SalutationDefault=config.driver.findElement(OR.GetLocator("ContactInformation", "ProfileTypeBusiness"));
			SalutationDefault=config.driver.findElement(By.xpath("//select[@id='input_26_30']//option[4]"));
			String Salutation=SalutationDefault.getText();
			System.out.println("-------------"+Salutation);
			if(Salutation.equalsIgnoreCase("Mr.")){
				Actual ="Default Salutation '"+ Salutation +"' Should come";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
			}else{
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		//----------TC003_Name Field Blank
		ModuleName = "ContactInformation";
		TestId = "TC003_Name Field Blank";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement ErrorMsg=null;
		try {

			config.driver.findElement(OR.GetLocator("ContactInformation", "NameFeild")).clear();
			config.driver.findElement(OR.GetLocator("ContactInformation", "UpdateButton")).click();
			ErrorMsg=config.driver.findElement(By.xpath("//div[@class='gfield_description validation_message']"));
			Actual=ErrorMsg.getText();


			if (Actual.equalsIgnoreCase(Expected)) {
				
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
