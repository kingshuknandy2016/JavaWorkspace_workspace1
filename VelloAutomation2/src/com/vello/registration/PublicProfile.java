package com.vello.registration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.velloe.automation.LaunchApplication;
import com.velloe.automation.OR;
import com.velloe.automation.Parameter;
import com.velloe.automation.config;

public class PublicProfile {
	
	static String ModuleName,TestId,Expected,Actual,VelloeId;

	public static void main(String[] args) throws Exception {
		
		LaunchApplication.applicationLaunch();
		PublicProfile pub=new PublicProfile();
		pub.CheckPublicProfile();
     
	}
	
	
	public void CheckPublicProfile() throws IOException, InterruptedException{
		config.driver.findElement(OR.GetLocator("Authentication", "UserName")).sendKeys("qa1@velloe.com");
		config.driver.findElement(OR.GetLocator("Authentication", "PassWord")).sendKeys("testqa123");
		config.driver.findElement(OR.GetLocator("Authentication", "Button")).click();
		Thread.sleep(4000);
		
		ModuleName = "PublicProfile";
		TestId="TC001_Check_Title";
		
		//--------------------TC001_Check_Title
		TestId="TC001_Check_Title";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			 WebDriverWait wait1 = new WebDriverWait(config.driver, 15);
			 wait1.until(ExpectedConditions.elementToBeClickable(OR.GetLocator(ModuleName, "MenuItemMarketing")));
			 Actions action = new Actions(config.driver);
			 WebElement we = config.driver.findElement(OR.GetLocator(ModuleName, "MenuItemMarketing"));
			 action.moveToElement(we).build().perform();
			Thread.sleep(3000);
			config.driver.findElement(OR.GetLocator(ModuleName, "MenuItemPublicProfile")).click();
            String actualTitle = config.driver.getTitle();
			//System.out.println("Check////////////+++++++++++++++++++++"+actualTitle);
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
		
		
		//---------------TC002_Check with a valid Velloe Id
		TestId="TC002_Check with a valid Velloe Id";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		VelloeId=Parameter.GetInput(ModuleName, TestId, 5);
		WebElement CorrectMsg1=null;
		
		try {
			config.driver.findElement(OR.GetLocator(ModuleName, "VelloeId")).clear();
			config.driver.findElement(OR.GetLocator(ModuleName, "VelloeId")).sendKeys(VelloeId);
			config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButton")).click();
			Thread.sleep(4000);
			CorrectMsg1=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			Actual=CorrectMsg1.getText();
			if (Actual.equalsIgnoreCase(Expected)) {
					
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		config.driver.findElement(By.xpath("(//button[contains(text(),'Close')])[1]")).click();
        
		
		//-----------TC003_Check with a valid About Information
		TestId="TC003_Check with a valid About Information";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		String AboutMsg="This is the Demo Of our Work,we work on the following feilds.";
		WebElement CorrectMsg2=null;
		
		try {

			config.driver.findElement(OR.GetLocator(ModuleName, "About")).clear();
			config.driver.findElement(OR.GetLocator(ModuleName, "About")).sendKeys(AboutMsg);
			config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButton")).click();
			Thread.sleep(4000);
			CorrectMsg2=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			Actual=CorrectMsg2.getText();
			if (Actual.equalsIgnoreCase(Expected)) {
					
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		config.driver.findElement(By.xpath("(//button[contains(text(),'Close')])[1]")).click();

		//-----------TC004_Check when Theme Color_Checkbox clicked
		TestId="TC004_Check when Theme Color_Checkbox clicked";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		//String AboutMsg="This is the Demo Of our Work,we work on the following feilds.";
		WebElement ThemeColorWidget = null;
		
		try {

			//config.driver.findElement(OR.GetLocator(ModuleName, "About")).clear();
			//config.driver.findElement(OR.GetLocator(ModuleName, "About")).sendKeys(AboutMsg);
			config.driver.findElement(OR.GetLocator(ModuleName, "ThemeColour")).click();
			Thread.sleep(4000);
			
			ThemeColorWidget=config.driver.findElement(OR.GetLocator(ModuleName, "GeneralColorDisplay"));
			String ThemeColorWidgetHeading=ThemeColorWidget.getText();
			
			if (ThemeColorWidgetHeading.equalsIgnoreCase("Colour Themes")) {
				Actual="Theme Colour Widged is displayed";	
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Actual="Theme Colour Widged is not displayed";
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

		//-----------
		//-----------
		
	}

}
