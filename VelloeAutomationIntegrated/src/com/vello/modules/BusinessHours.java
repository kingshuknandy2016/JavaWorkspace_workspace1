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

public class BusinessHours {
	
	static String ModuleName,TestId,Expected,Actual;

	public void  BusinessHoursExecution() throws FileNotFoundException, InterruptedException  {
		//baseWindowHdl = config.driver.getWindowHandle();
		ModuleName = "BusinessHours";
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
			config.driver.findElement(By.xpath("//li[@id='menu-item-84']")).click();
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
		
		//----------------TC002_Check Update and Cancel Button is visible or not
		ModuleName = "BusinessHours";
		TestId = "TC002_Check Update and Cancel Button is visible or not";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement updateButtonBusinesshours=null;
		WebElement cancelButtonBusinesshours=null;
		try{
			updateButtonBusinesshours=config.driver.findElement(OR.GetLocator(ModuleName, "UpdateButtonBH"));
			cancelButtonBusinesshours=config.driver.findElement(OR.GetLocator(ModuleName, "CancelButtonBH"));
		if(updateButtonBusinesshours.isEnabled() && cancelButtonBusinesshours.isEnabled() ){
			Actual ="Update and Cancel Button is visible";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");	
		}else{
			Actual ="Update and Cancel Button is not visible";
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
	} catch (Exception e) {
		Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
		Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
	}
		
		//------TC003_Validate Sunday and Saturday checkbox is unchecked
		ModuleName = "BusinessHours";
		TestId = "TC003_Validate Sunday and Saturday checkbox is unchecked";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement SundayCheckbox=null;
		WebElement SaturdayCheckbox=null;
		try {
			SundayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "SundayCheckbox"));
			SaturdayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "SaturdayCheckbox"));
			if(SundayCheckbox.isSelected() && SaturdayCheckbox.isSelected()){
				Actual="Sunday and Saturday checkbox is checked";
			    Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}else{
				Actual="Sunday and Saturday checkbox is unchecked";
			    Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				
			}
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		//-----TC004_Validate Week Days are checked
		
		ModuleName = "BusinessHours";
		TestId = "TC004_Validate Week Days are checked";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement MondayCheckbox=null;
		WebElement TeusdayCheckbox=null;
		WebElement WednesdayCheckbox=null;
		WebElement ThrusdayCheckbox=null;
		WebElement FridayCheckbox=null;
		try {
			MondayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "MondayCheckbox"));
			TeusdayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "TeusdayCheckbox"));
			WednesdayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "WednesdayCheckbox"));
			ThrusdayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "ThrusdayCheckbox"));
			FridayCheckbox=config.driver.findElement(OR.GetLocator(ModuleName, "FridayCheckbox"));
			

			if(MondayCheckbox.isSelected() && TeusdayCheckbox.isSelected() && WednesdayCheckbox.isSelected()
					&& ThrusdayCheckbox.isSelected() && FridayCheckbox.isSelected()){
				
				Actual=" Week Days are checked";
			    Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				

			}else{
				Actual="Week Days are unchecked";
			    Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				
			}
			
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		//-------------TC005_Validate when Open at text-fields is clicked
		ModuleName = "BusinessHours";
		TestId = "TC005_Validate when Open at text-fields is clicked";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement StartCalendarWidget = null;
		try {
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_Start_1']")).click();
			Thread.sleep(4000);
			StartCalendarWidget =config.driver.findElement(By.xpath("//div[@id='ui-timepicker-div']"));
            if (StartCalendarWidget.isDisplayed()) {
            	Actual = "Time-Picker widget is displayed";
    			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Actual = "Time-Picker widget is not displayed";
    			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				
			}
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		

		//-------------TC006_Validate when Close at text-fields is clicked
		ModuleName = "BusinessHours";
		TestId = "TC006_Validate when Close at text-fields is clicked";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement EndCalendarWidget = null;
		try {
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).click();
			
			Thread.sleep(4000);
			EndCalendarWidget =config.driver.findElement(By.xpath("//div[@id='ui-timepicker-div']"));
            if (EndCalendarWidget.isDisplayed()) {
            	Actual = "Time-Picker widget is displayed";
    			Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			}else{
				Actual = "Time-Picker widget is not displayed";
    			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
				
			}
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		//--------------TC007_Open at text-field cannot be empty
		ModuleName = "BusinessHours";
		TestId = "TC007_Open at text-field cannot be empty";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_Start_1']")).click();
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_Start_1']")).clear();
			
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).click();
			WebElement StartError=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			String ErrorMsg1=StartError.getText();
			if(ErrorMsg1.equalsIgnoreCase(Expected)){
				Actual=ErrorMsg1;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

		//--------------TC008_Closed at text-field cannot be empty
		ModuleName = "BusinessHours";
		TestId = "TC008_Closed at text-field cannot be empty";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.navigate().refresh();
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).click();
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).clear();
			
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_Start_1']")).click();
			WebElement EndTimeError=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			String ErrorMsg2=EndTimeError.getText();
			if(ErrorMsg2.equalsIgnoreCase(Expected)){
				Actual=ErrorMsg2;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}


		//--------------TC009_Open time & Closed time cannot be same
		ModuleName = "BusinessHours";
		TestId = "TC009_Open time & Closed time cannot be same";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.navigate().refresh();
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).click();			
			config.driver.findElement(By.xpath("(//div[@id='ui-timepicker-div']//table[@class='ui-timepicker']//td[4])[2]")).click();
			config.driver.findElement(By.xpath("//button[text()='Set Time']")).click();
			WebElement SameTimeError=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			String ErrorMsg3=SameTimeError.getText();
			if(ErrorMsg3.equalsIgnoreCase(Expected)){
				Actual=ErrorMsg3;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");				
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

		
		//--------------TC010_Closed time cannot be less than open time
		ModuleName = "BusinessHours";
		TestId = "TC010_Closed time cannot be less than open time";
		Expected = Parameter.GetInput(ModuleName, TestId, 2);
		try {
			config.driver.navigate().refresh();
			config.driver.findElement(By.xpath("//input[@id='time_workday_0_End_1']")).click();			
			config.driver.findElement(By.xpath("(//div[@id='ui-timepicker-div']//table[@class='ui-timepicker']//td[3])[2]")).click();
			config.driver.findElement(By.xpath("//button[text()='Set Time']")).click();
			WebElement BeforeTimeError=config.driver.findElement(By.xpath("//p[@id='generalAlertContent']"));
			String ErrorMsg4=BeforeTimeError.getText();
			if(ErrorMsg4.equalsIgnoreCase(Expected)){
				Actual=ErrorMsg4;
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
				
			}
			
		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}

       config.driver.close();

		
	}

}
