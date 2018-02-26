package com.velloe.automation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.velloe.automation.config;
import com.velloe.automation.OR;
import com.velloe.automation.Parameter;

public class Login {
	static String TestId, Actual, Status, ModuleName, UserName, Password, ExpectedRes;

	public void Authentication() throws IOException, InterruptedException {

		ModuleName = "Authentication";
		TestId = "TC001_Valid_Username_Invalid_Password";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		Password = Parameter.GetInput(ModuleName, TestId, 3);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		WebElement ErrorduetoWrongPW = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			ErrorduetoWrongPW = config.driver.findElement(By.xpath("//div[contains(@class,'validation_message')]"));
			Actual = ErrorduetoWrongPW.getText();
			if (Actual.contains(ExpectedRes)) {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		//config.driver.navigate().refresh();
		

		TestId = "TC002_Inavlid_Username_Valid_Password";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		Password = Parameter.GetInput(ModuleName, TestId, 3);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		WebElement ErrorduetoWrongUser = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			ErrorduetoWrongUser = config.driver.findElement(By.id("field_17_1")).findElement(By.tagName("div"));
			Actual = ErrorduetoWrongUser.getText();
			if (Actual.equalsIgnoreCase(ExpectedRes)) {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		//config.driver.navigate().refresh();
		
		
		
/*		TestId = "TC003_Valid_Service_Provider_Credential";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		Password = Parameter.GetInput(ModuleName, TestId, 3);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		WebElement BookingFrame=null;
		WebElement BookingNCalendar = null;
		String baseWindowHdl = config.driver.getWindowHandle();
		
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			
			BookingFrame=config.driver.findElement(By.id("bookingCenter"));	
			config.driver.switchTo().frame(BookingFrame);			 
			BookingNCalendar =config.driver.findElement(By.id("calendar")).findElement(By.tagName("h2"));
			if(BookingNCalendar.isDisplayed()){
				Actual= BookingNCalendar.getText();
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
				config.driver.switchTo().window(baseWindowHdl);				
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
				config.driver.switchTo().window(baseWindowHdl);
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
	   
	   Thread.sleep(2000);
	   config.driver.navigate().refresh();*/	
		
/*		TestId = "TC004_Valid_Client_Credential";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		Password = Parameter.GetInput(ModuleName, TestId, 3);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		WebElement ClientApplicationFrame = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			
			ClientApplicationFrame = config.driver.findElement(By.id("mobileWeb"));
			if(ClientApplicationFrame.isDisplayed()){
				Actual= ClientApplicationFrame.getText();
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}	*/	
		config.driver.navigate().refresh();
		
		TestId = "TC005_Password_Reset_Click";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		String PasswordResetText = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "ForgotPassword")).click();
			PasswordResetText = config.driver.findElement(By.className("form-group")).findElement(By.tagName("h4")).getText();			
			if(PasswordResetText.equalsIgnoreCase("Velloe Password Reset")){
				Actual= PasswordResetText;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		config.driver.navigate().refresh();
		
		TestId = "TC006_Password_Reset_Invalid_Email";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		String PWResetInvalidEmailText = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			PWResetInvalidEmailText = config.driver.findElement(By.xpath("//h4[contains(text(),'Username does not exist')]")).getText();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetTryAgain")).click();			
			if(PWResetInvalidEmailText.equalsIgnoreCase("Username does not exist.")){
				Actual= PWResetInvalidEmailText;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		TestId = "TC007_Click_Continue_WithOut_Email";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		String PWResetContinue = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			PWResetContinue = config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).getText();			
			if(PWResetContinue.equalsIgnoreCase("Continue")){
				Actual= "User presented " +PWResetContinue+" page";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		TestId = "TC008_Click_Continue_With_Valid_Email";
		UserName = Parameter.GetInput(ModuleName, TestId, 2);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 4);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "PWResetFor")).getText();			
			if(Actual.equalsIgnoreCase("ExpectedRes")){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
	}

	public static void main(String[] args) throws Exception {
		LaunchApplication.applicationLaunch();
		Login log = new Login();
		log.Authentication();

	}
}