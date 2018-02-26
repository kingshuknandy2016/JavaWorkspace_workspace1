package com.vello.modules;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.velloe.repository.OR;
import com.velloe.repository.Parameter;
import com.velloe.repository.config;

public class Login {
	
	
	static String TestId, Actual, Status, ModuleName, UserName, Password, ExpectedRes, SecurityQA,Pw1,Pw2;
	static String baseWindowHdl;	
	//static ExtentReports extent;

	public void Authentication() throws IOException, InterruptedException {
		
	/*********************************TC_001********************************************/
				
		baseWindowHdl = config.driver.getWindowHandle();
		ModuleName = "Authentication";
		TestId = "TC001_Valid_Username_Invalid_Password";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		Password = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement ErrorduetoWrongPW = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			ErrorduetoWrongPW = config.driver.findElement(OR.GetLocator("Authentication", "ErrorForWrongPw"));
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
		
	/**************************************TC_002************************************************/
		
		TestId = "TC002_Inavlid_Username_Valid_Password";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		Password = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement ErrorduetoWrongUser = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).click();
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
	
/**************************************TC_003************************************************/
		
		TestId = "TC003_Valid_BusinessUser_Credential";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		Password = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement Logoff = null;
			
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			Thread.sleep(6000);
			Logoff = config.driver.findElement(OR.GetLocator("Authentication", "LogOff"));				 
			
			if(Logoff.isDisplayed()){
				Actual= "Home Page is opened and "+Logoff.getText() + " button displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				Logoff.click();						
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
	   
	   Thread.sleep(4000);
				
		TestId = "TC004_BusinessUser_Can_Login_as_Public";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		Password = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement Logoffbuttn = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys(Password);
			config.driver.findElement(OR.GetLocator("Authentication", "PublicProfile")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
			Thread.sleep(6000);		
			Logoffbuttn = config.driver.findElement(OR.GetLocator("Authentication", "LogOff"));		
			if(Logoffbuttn.isDisplayed()){
				Actual= "Home Page is opened and "+Logoffbuttn.getText() + " button displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "LogOff")).click();
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		Thread.sleep(4000);
		//config.driver.navigate().refresh();
		
		TestId = "TC005_Password_Reset_Click";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
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
		//config.driver.navigate().refresh();
		
		TestId = "TC006_Password_Reset_Invalid_Email";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 6);
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
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		String PWResetContinue = null;
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			PWResetContinue = config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).getText();			
			if(PWResetContinue.equalsIgnoreCase("Continue")){
				Actual= "User presented '" +PWResetContinue+"' page";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		TestId = "TC008_Click_Continue_With_Valid_Email";
		UserName = Parameter.GetInput(ModuleName, TestId, 5);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetEmail")).sendKeys(UserName);
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(2000);
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "PWResetFor")).getText();			
			if(Actual.equalsIgnoreCase(ExpectedRes)){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		TestId = "TC009_Cancel_Button_Present_In_PW_Reset";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			String buttonName = config.driver.findElement(OR.GetLocator("Authentication", "CancelButton")).getText();			
			if(buttonName.equalsIgnoreCase("Cancel")){
				Actual= "Button'"+buttonName+"' is present";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Actual= "Button'"+buttonName+"' is present";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		TestId = "TC010_Continue_WithOut_Security_Answer";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(2000);
			config.driver.switchTo().defaultContent();
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "AlertMessage")).getText();
				
			if(Actual.equalsIgnoreCase(ExpectedRes)){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			config.driver.switchTo().window(baseWindowHdl);
		}
		
		TestId = "TC011_Continue_With_Wrong_Security_Answer";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		SecurityQA = Parameter.GetInput(ModuleName, TestId, 5);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "SecurityQA")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "SecurityQA")).sendKeys(SecurityQA);;
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(2000);
			config.driver.switchTo().defaultContent();
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "AlertMessage")).getText();				
			if(Actual.equalsIgnoreCase(ExpectedRes)){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			config.driver.switchTo().window(baseWindowHdl);
		}
		
		TestId = "TC012_Continue_With_Valid_Security_Answer";
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		SecurityQA = Parameter.GetInput(ModuleName, TestId, 5);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "SecurityQA")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "SecurityQA")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "SecurityQA")).sendKeys(SecurityQA);;
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(2000);
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "PasswordResetPage")).getText();				
			if(Actual.equalsIgnoreCase(ExpectedRes)){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		TestId = "TC013_Error_For_Diffrent_Reset_Password";
		Pw1 = Parameter.GetInput(ModuleName, TestId, 5);
		Pw2 = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset1")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset1")).sendKeys(Pw1);
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset2")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset2")).sendKeys(Pw2);
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(4000);
			config.driver.switchTo().defaultContent();
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "AlertMessage")).getText();				
			if(Actual.equalsIgnoreCase(ExpectedRes)){				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			config.driver.switchTo().window(baseWindowHdl);
		}
		
		Thread.sleep(2000);
		TestId = "TC014_Reset_Password_with_Valid_Data";
		Pw1 = Parameter.GetInput(ModuleName, TestId, 5);
		Pw2 = Parameter.GetInput(ModuleName, TestId, 6);
		ExpectedRes = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset1")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset1")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset1")).sendKeys(Pw1);
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset2")).click();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset2")).clear();
			config.driver.findElement(OR.GetLocator("Authentication", "PasswordReset2")).sendKeys(Pw2);
			config.driver.findElement(OR.GetLocator("Authentication", "PWResetContinue")).click();
			Thread.sleep(4000);
			config.driver.switchTo().defaultContent();
			Actual = config.driver.findElement(OR.GetLocator("Authentication", "AlertMessage")).getText();				
			if(Actual.equalsIgnoreCase(ExpectedRes)){
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				config.driver.findElement(OR.GetLocator("Authentication", "AlertModalClose")).click();
				config.driver.switchTo().window(baseWindowHdl);
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			config.driver.switchTo().window(baseWindowHdl);
		}
		
		config.driver.close();
	}
}